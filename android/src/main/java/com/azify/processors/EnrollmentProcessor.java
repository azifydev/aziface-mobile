package com.azify.processors;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import com.azify.utils.DynamicRoute;
import com.azify.azifacemobilesdk.AzifaceMobileSdkModule;
import com.facebook.react.bridge.ReadableMap;
import com.facetec.sdk.*;

public class EnrollmentProcessor extends Processor implements FaceTecFaceScanProcessor {
  private boolean success = false;
  private final AzifaceMobileSdkModule module;
  private final ReadableMap data;

  public EnrollmentProcessor(String sessionToken, Context context, AzifaceMobileSdkModule module,
      ReadableMap data) {
    this.module = module;
    this.data = data;

    this.module.sendEvent("onOpen", true);
    this.module.sendEvent("onClose", false);
    this.module.sendEvent("onCancel", false);
    this.module.sendEvent("onError", false);

    FaceTecSessionActivity.createAndLaunchSession(context, EnrollmentProcessor.this, sessionToken);
  }

  public void processSessionWhileFaceTecSDKWaits(final FaceTecSessionResult sessionResult,
      final FaceTecFaceScanResultCallback faceScanResultCallback) {
    this.module.setLatestSessionResult(sessionResult);

    if (sessionResult.getStatus() != FaceTecSessionStatus.SESSION_COMPLETED_SUCCESSFULLY) {
      NetworkingHelpers.cancelPendingRequests();
      faceScanResultCallback.cancel();
      this.module.sendEvent("onOpen", false);
      this.module.sendEvent("onClose", true);
      this.module.sendEvent("onCancel", true);
      this.module.sendEvent("onError", false);
      this.module.promise.reject("Status is not session completed successfully!",
          "SessionStatusError");
      return;
    }

    DynamicRoute dynamicRoute = new DynamicRoute();
    String pathUrl = dynamicRoute.getPathUrlEnrollment3d("base");

    JSONObject parameters = new JSONObject();
    try {
      if (this.data != null) {
        parameters.put("data", new JSONObject(this.data.toHashMap()));
      }
      parameters.put("faceScan", sessionResult.getFaceScanBase64());
      parameters.put("auditTrailImage", sessionResult.getAuditTrailCompressedBase64()[0]);
      parameters.put("lowQualityAuditTrailImage", sessionResult.getLowQualityAuditTrailCompressedBase64()[0]);
      parameters.put("externalDatabaseRefID", module.getLatestExternalDatabaseRefID());
    } catch (JSONException e) {
      this.module.sendEvent("onOpen", false);
      this.module.sendEvent("onClose", true);
      this.module.sendEvent("onCancel", false);
      this.module.sendEvent("onError", true);
      module.promise.reject("Exception raised while attempting to create JSON payload for upload.",
          "JSONError");
    }

    okhttp3.Request request = new okhttp3.Request.Builder()
        .url(Config.BaseURL + pathUrl)
        .headers(Config.getHeaders("POST"))
        .post(new ProgressRequestBody(
            RequestBody.create(MediaType.parse("application/json; charset=utf-8"), parameters.toString()),
            new ProgressRequestBody.Listener() {
              @Override
              public void onUploadProgressChanged(long bytesWritten, long totalBytes) {
                final float uploadProgressPercent = ((float) bytesWritten) / ((float) totalBytes);
                faceScanResultCallback.uploadProgress(uploadProgressPercent);
              }
            }))
        .build();

    NetworkingHelpers.getApiClient().newCall(request).enqueue(new Callback() {
      @Override
      public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {

        String responseString = response.body().string();
        response.body().close();
        try {
          JSONObject responseJSON = new JSONObject(responseString);
          JSONObject responseJSONData = responseJSON.getJSONObject("data");
          boolean wasProcessed = responseJSONData.getBoolean("wasProcessed");
          boolean error = responseJSONData.getBoolean("error");

          if (error) {
            faceScanResultCallback.cancel();
            module.sendEvent("onOpen", false);
            module.sendEvent("onClose", true);
            module.sendEvent("onCancel", true);
            module.sendEvent("onError", true);
            module.promise.reject("An error occurred while scanning",
              "ProcessorError");
            return;
          }

          String scanResultBlob = responseJSONData.getString("scanResultBlob");

          if (wasProcessed) {
            FaceTecCustomization.overrideResultScreenSuccessMessage = AzifaceMobileSdkModule.AziTheme
              .getEnrollmentMessage("successMessage", "Face Scanned\n3D Liveness Proven");

            success = faceScanResultCallback.proceedToNextStep(scanResultBlob);
            if (success) {
              module.sendEvent("onOpen", false);
              module.sendEvent("onClose", true);
              module.sendEvent("onCancel", false);
              module.sendEvent("onError", false);
              module.promise.resolve(true);
            }
          } else {
            faceScanResultCallback.cancel();
            module.sendEvent("onOpen", false);
            module.sendEvent("onClose", true);
            module.sendEvent("onCancel", true);
            module.sendEvent("onError", true);
            module.promise.reject("AziFace SDK wasn't have to values processed!",
              "SessionNotProcessedError");
          }
        } catch (JSONException e) {
          faceScanResultCallback.cancel();
          module.sendEvent("onOpen", false);
          module.sendEvent("onClose", true);
          module.sendEvent("onCancel", true);
          module.sendEvent("onError", true);
        }
      }

      @Override
      public void onFailure(@NonNull Call call, @Nullable IOException e) {
        faceScanResultCallback.cancel();
        module.sendEvent("onOpen", false);
        module.sendEvent("onClose", true);
        module.sendEvent("onCancel", true);
        module.sendEvent("onError", true);
        module.promise.reject("Exception raised while attempting HTTPS call.", "HTTPSError");
      }
    });
  }

  public boolean isSuccess() {
    return this.success;
  }
}

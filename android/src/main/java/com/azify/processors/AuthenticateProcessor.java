package com.azify.processors;

import android.content.Context;

import androidx.annotation.NonNull;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import com.azify.processors.utils.DynamicRoute;
import com.azify.processors.helpers.ThemeUtils;
import com.azify.azifacemobilesdk.AzifaceMobileSdkModule;
import com.facebook.react.bridge.ReadableMap;
import com.facetec.sdk.*;

public class AuthenticateProcessor extends Processor implements FaceTecFaceScanProcessor {
  private final String principalKey = "autheticanteMessage";
  private final AzifaceMobileSdkModule faceTecModule;
  private final ReadableMap data;
  private final ThemeUtils FaceThemeUtils = new ThemeUtils();
  private boolean success = false;

  public AuthenticateProcessor(String sessionToken, Context context, AzifaceMobileSdkModule faceTecModule,
      ReadableMap data) {
    this.faceTecModule = faceTecModule;
    this.data = data;

    faceTecModule.sendEvent("onCloseModal", true);
    FaceTecSessionActivity.createAndLaunchSession(context, AuthenticateProcessor.this, sessionToken);
  }

  public void processSessionWhileFaceTecSDKWaits(final FaceTecSessionResult sessionResult,
      final FaceTecFaceScanResultCallback faceScanResultCallback) {
    faceTecModule.setLatestSessionResult(sessionResult);

    if (sessionResult.getStatus() != FaceTecSessionStatus.SESSION_COMPLETED_SUCCESSFULLY) {
      NetworkingHelpers.cancelPendingRequests();
      faceScanResultCallback.cancel();
      faceTecModule.sendEvent("onCloseModal", false);
      faceTecModule.processorPromise.reject("Status is not session completed successfully!",
          "AziFaceTecDifferentStatus");
      return;
    }

    JSONObject parameters = new JSONObject();
    try {
      if (this.data != null) {
        parameters.put("data", new JSONObject(this.data.toHashMap()));
      }
      parameters.put("faceScan", sessionResult.getFaceScanBase64());
      parameters.put("auditTrailImage", sessionResult.getAuditTrailCompressedBase64()[0]);
      parameters.put("lowQualityAuditTrailImage", sessionResult.getLowQualityAuditTrailCompressedBase64()[0]);
      parameters.put("externalDatabaseRefID", faceTecModule.getLatestExternalDatabaseRefID());
    } catch (JSONException e) {
      e.printStackTrace();
      faceTecModule.sendEvent("onCloseModal", false);
      faceTecModule.processorPromise.reject("Exception raised while attempting to create JSON payload for upload.",
          "JSONError");
    }

    DynamicRoute dynamicRoute = new DynamicRoute();
    String pathUrl = dynamicRoute.getPathUrl("base", "/Process/" + Config.ProcessId + "/Match3d3d");

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
          String scanResultBlob = responseJSONData.getString("scanResultBlob");

          if (wasProcessed) {
            FaceTecCustomization.overrideResultScreenSuccessMessage = FaceThemeUtils
                .handleMessage(principalKey, "successMessage", "Authenticated");
            success = faceScanResultCallback.proceedToNextStep(scanResultBlob);
            if (success) {
              faceTecModule.processorPromise.resolve(true);
            }
          } else {
            faceScanResultCallback.cancel();
            faceTecModule.sendEvent("onCloseModal", false);
            faceTecModule.processorPromise.reject("FaceTec SDK wasn't have to values processed!",
                "AziFaceValuesWereNotProcessed");
          }
        } catch (JSONException e) {
          e.printStackTrace();
          faceScanResultCallback.cancel();
          faceTecModule.sendEvent("onCloseModal", false);
          faceTecModule.processorPromise.reject("Exception raised while attempting to parse JSON result.",
              "JSONError");
        }
      }

      @Override
      public void onFailure(@NonNull Call call, IOException e) {
        faceScanResultCallback.cancel();
        faceTecModule.sendEvent("onCloseModal", false);
        faceTecModule.processorPromise.reject("Exception raised while attempting HTTPS call.", "HTTPSError");
      }
    });
  }

  public boolean isSuccess() {
    return this.success;
  }
}

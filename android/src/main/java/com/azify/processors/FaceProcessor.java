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
import java.util.Map;

import com.azify.processors.helpers.ThemeUtils;
import com.azify.azifacemobilesdk.AzifaceMobileSdkModule;
import com.facetec.sdk.*;

public class FaceProcessor extends Processor implements FaceTecFaceScanProcessor {
  private final String key;
  private final AzifaceMobileSdkModule aziFaceModule;
  private final FaceConfig faceConfig;
  private final ThemeUtils aziThemeUtils = new ThemeUtils();
  private boolean success = false;

  public FaceProcessor(String sessionToken, Context context, AzifaceMobileSdkModule aziFaceModule,
      FaceConfig faceConfig) {
    this.aziFaceModule = aziFaceModule;
    this.faceConfig = faceConfig;
    this.key = faceConfig.getKey();

    aziFaceModule.sendEvent("onCloseModal", true);
    FaceTecSessionActivity.createAndLaunchSession(context, FaceProcessor.this, sessionToken);
  }

  public void processSessionWhileFaceTecSDKWaits(final FaceTecSessionResult sessionResult,
      final FaceTecFaceScanResultCallback faceScanResultCallback) {
    aziFaceModule.setLatestSessionResult(sessionResult);

    if (sessionResult.getStatus() != FaceTecSessionStatus.SESSION_COMPLETED_SUCCESSFULLY) {
      NetworkingHelpers.cancelPendingRequests();
      faceScanResultCallback.cancel();
      aziFaceModule.sendEvent("onCloseModal", false);
      aziFaceModule.processorPromise.reject("The session status has not been completed!", "AziFaceInvalidSession");
      return;
    }

    JSONObject parameters = new JSONObject();
    try {
      final Map extraParameters = this.faceConfig.getParameters();
      if (extraParameters != null) {
        parameters.put("data", new JSONObject(extraParameters));
      }
      parameters.put("faceScan", sessionResult.getFaceScanBase64());
      parameters.put("auditTrailImage", sessionResult.getAuditTrailCompressedBase64()[0]);
      parameters.put("lowQualityAuditTrailImage", sessionResult.getLowQualityAuditTrailCompressedBase64()[0]);

      final boolean hasExternalDatabaseRefID = this.faceConfig.getHasExternalDatabaseRefID();
      if (hasExternalDatabaseRefID) {
        parameters.put("externalDatabaseRefID", aziFaceModule.getLatestExternalDatabaseRefID());
      }
    } catch (JSONException e) {
      e.printStackTrace();
      aziFaceModule.sendEvent("onCloseModal", false);
      aziFaceModule.processorPromise.reject("Exception raised while attempting to create JSON payload for upload.",
          "JSONError");
    }

    final String endpoint = this.faceConfig.getEndpoint();
    okhttp3.Request request = new okhttp3.Request.Builder()
        .url(Config.BaseURL + endpoint)
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
          boolean wasProcessed = responseJSON.getBoolean("wasProcessed");
          String scanResultBlob = responseJSON.getString("scanResultBlob");

          if (wasProcessed) {
            final String message = faceConfig.getSuccessMessage();
            FaceTecCustomization.overrideResultScreenSuccessMessage = aziThemeUtils
                .handleMessage(key, "successMessage", message);
            success = faceScanResultCallback.proceedToNextStep(scanResultBlob);
            if (success) {
              aziFaceModule.sendEvent("onCloseModal", false);
              aziFaceModule.processorPromise.resolve(true);
            }
          } else {
            faceScanResultCallback.cancel();
            aziFaceModule.sendEvent("onCloseModal", false);
            aziFaceModule.processorPromise.reject("AziFace SDK values were not processed!",
                "AziFaceValuesWereNotProcessed");
          }
        } catch (JSONException e) {
          e.printStackTrace();
          faceScanResultCallback.cancel();
          aziFaceModule.sendEvent("onCloseModal", false);
          aziFaceModule.processorPromise.reject("Exception raised while attempting to parse JSON result.",
              "JSONError");
        }
      }

      @Override
      public void onFailure(@NonNull Call call, IOException e) {
        faceScanResultCallback.cancel();
        aziFaceModule.sendEvent("onCloseModal", false);
        aziFaceModule.processorPromise.reject("Exception raised while attempting HTTPS call.", "HTTPSError");
      }
    });
  }

  public boolean isSuccess() {
    return this.success;
  }
}

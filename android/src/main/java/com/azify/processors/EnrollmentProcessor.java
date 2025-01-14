package com.azify.processors;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import com.azify.processors.helpers.ThemeUtils;
import com.azify.azifacemobilesdk.AzifaceMobileSdkModule;
import com.facebook.react.bridge.ReadableMap;
import com.facetec.sdk.*;

public class EnrollmentProcessor extends Processor implements FaceTecFaceScanProcessor {
  private boolean success = false;
  private final String principalKey = "enrollMessage";
  private final AzifaceMobileSdkModule faceTecModule;
  private final ReadableMap data;
  private final ThemeUtils FaceThemeUtils = new ThemeUtils();

    public EnrollmentProcessor(String sessionToken, Context context, AzifaceMobileSdkModule faceTecModule,
                               ReadableMap data) {
      this.faceTecModule = faceTecModule;
      this.data = data;
      faceTecModule.sendEvent("onCloseModal", true);
      FaceTecSessionActivity.createAndLaunchSession(context, EnrollmentProcessor.this, sessionToken);
    }

    public void processSessionWhileFaceTecSDKWaits(final FaceTecSessionResult sessionResult, final FaceTecFaceScanResultCallback faceScanResultCallback) {
        faceTecModule.setLatestSessionResult(sessionResult);

        if(sessionResult.getStatus() != FaceTecSessionStatus.SESSION_COMPLETED_SUCCESSFULLY) {
            NetworkingHelpers.cancelPendingRequests();
            faceScanResultCallback.cancel();
            faceTecModule.sendEvent("onCloseModal", false);
            faceTecModule.processorPromise.reject("Status is not session completed successfully!", "FaceTecDifferentStatus");
            return;
        }
       var route = "/Process/" + Config.ProcessId + "/Enrollment3d";

        JSONObject parameters = new JSONObject();
        try {
            if (this.data != null) {
                parameters.put("data", new JSONObject(this.data.toHashMap()));
            }
            parameters.put("faceScan", sessionResult.getFaceScanBase64());
            parameters.put("auditTrailImage", sessionResult.getAuditTrailCompressedBase64()[0]);
            parameters.put("lowQualityAuditTrailImage", sessionResult.getLowQualityAuditTrailCompressedBase64()[0]);
            parameters.put("externalDatabaseRefID", faceTecModule.getLatestExternalDatabaseRefID());
        }
        catch(JSONException e) {
            e.printStackTrace();
            Log.d("Aziface", "Exception raised while attempting to create JSON payload for upload.");
              faceTecModule.sendEvent("onCloseModal", false);
            faceTecModule.processorPromise.reject("Exception raised while attempting to create JSON payload for upload.",
                "JSONError");
        }


      okhttp3.Request request = new okhttp3.Request.Builder()
            .url(Config.BaseURL + route)
            .headers(Config.getHeaders("POST"))
            .post(new ProgressRequestBody(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), parameters.toString()),
                    new ProgressRequestBody.Listener() {
                        @Override
                        public void onUploadProgressChanged(long bytesWritten, long totalBytes) {
                            final float uploadProgressPercent = ((float)bytesWritten) / ((float)totalBytes);
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
                        String errorMessage = responseJSONData.optString("errorMessage");
                        Log.d("Aziface", "Error while processing FaceScan. " + errorMessage);
                        faceScanResultCallback.cancel();
                        return;
                    }

                    String scanResultBlob = responseJSONData.getString("scanResultBlob");


                    if(wasProcessed) {

                        FaceTecCustomization.overrideResultScreenSuccessMessage = "Face Scanned\n3D Liveness Proven";
                        success = faceScanResultCallback.proceedToNextStep(scanResultBlob);
                    }
                    else {
                        faceScanResultCallback.cancel();
                    }
                }
                catch(JSONException e) {
                    e.printStackTrace();
                    Log.d("Aziface", "Exception raised while attempting to parse JSON result.");
                    faceScanResultCallback.cancel();
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @Nullable IOException e) {
                Log.d("Aziface", "Exception raised while attempting HTTPS call.");
                faceScanResultCallback.cancel();
            }
        });
    }

    public boolean isSuccess() {
        return this.success;
    }
}

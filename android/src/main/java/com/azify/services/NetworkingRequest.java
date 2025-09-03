package com.azify.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.azify.AzifaceModule;
import com.azify.Config;
import com.azify.SessionRequestProcessor;
import com.facetec.sdk.FaceTecSDK;
import com.facetec.sdk.FaceTecSessionRequestProcessor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NetworkingRequest {
  public static void send(
    @NonNull SessionRequestProcessor referencingProcessor,
    @NonNull String sessionRequestBlob,
    @NonNull FaceTecSessionRequestProcessor.Callback sessionRequestCallback
  ) {
    JSONObject sessionRequestCallPayload = new JSONObject();

    try {
      sessionRequestCallPayload.put("requestBlob", sessionRequestBlob);

      if (!AzifaceModule.demonstrationExternalDatabaseRefID.isEmpty()) {
        sessionRequestCallPayload.put("externalDatabaseRefID", AzifaceModule.demonstrationExternalDatabaseRefID);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), sessionRequestCallPayload.toString());

    Request request = new Request.Builder()
      .url(Config.BaseURL)
      .header("Content-Type", "application/json")
      .header("X-Device-Key", Config.DeviceKeyIdentifier)
      .header("X-Testing-API-Header", FaceTecSDK.getTestingAPIHeader())
      .post(new ProgressRequestBody(requestBody,
        (bytesWritten, totalBytes) -> {
          final float uploadProgressPercent = ((float) bytesWritten) / ((float) totalBytes);
          referencingProcessor.onUploadProgress(uploadProgressPercent, sessionRequestCallback);
        }))
      .build();

    NetworkingLib.getApiClient().newCall(request).enqueue(new okhttp3.Callback() {
      @Override
      public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
        String responseBlob = getResponseBlobOrHandleError(response, referencingProcessor, sessionRequestCallback);
        if (responseBlob != null) {
          referencingProcessor.onResponseBlobReceived(responseBlob, sessionRequestCallback);
        }
      }

      @Override
      public void onFailure(@NonNull Call call, @NonNull IOException e) {
        Log.d("FaceTecSDKSampleApp", "Exception raised while attempting HTTPS call.");

        referencingProcessor.onCatastrophicNetworkError(sessionRequestCallback);
      }
    });
  }

  static String getResponseBlobOrHandleError(okhttp3.Response response, SessionRequestProcessor referencingProcessor, @NonNull FaceTecSessionRequestProcessor.Callback sessionRequestCallback) throws IOException {
    if (response.isSuccessful() && response.body() != null) {
      try {
        JSONObject responseJSON = new JSONObject(response.body().string());

        String responseBlob = responseJSON.getString("responseBlob");
        response.close();
        return responseBlob;
      } catch (JSONException e) {
        logErrorAndCallAbortAndClose("JSON Parsing Failed.  This indicates an issue in your own webservice or API contracts.", referencingProcessor, response, sessionRequestCallback);
      }
    } else {
      logErrorAndCallAbortAndClose("API Response not successful.  Inspect network request and response for more details.", referencingProcessor, response, sessionRequestCallback);
    }

    return null;
  }

  static void logErrorAndCallAbortAndClose(String errorDetail, SessionRequestProcessor referencingProcessor, okhttp3.Response response, @NonNull FaceTecSessionRequestProcessor.Callback sessionRequestCallback) {
    Log.d("FaceTecSDKSampleApp", "Networking Exception raised while attempting HTTPS call. Details: " + errorDetail);
    referencingProcessor.onCatastrophicNetworkError(sessionRequestCallback);
    response.close();
  }
}

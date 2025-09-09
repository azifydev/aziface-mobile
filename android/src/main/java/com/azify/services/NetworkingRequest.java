package com.azify.services;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.azify.AzifaceModule;
import com.azify.Config;
import com.azify.SessionRequestProcessor;
import com.facebook.react.bridge.ReadableMap;
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
    @NonNull FaceTecSessionRequestProcessor.Callback sessionRequestCallback,
    @Nullable ReadableMap data
  ) {
    JSONObject sessionRequestCallPayload = new JSONObject();

    try {
      if (data != null) {
        sessionRequestCallPayload.put("data", new JSONObject(data.toHashMap()));
      }
      sessionRequestCallPayload.put("requestBlob", sessionRequestBlob);

      if (!AzifaceModule.DemonstrationExternalDatabaseRefID.isEmpty()) {
        sessionRequestCallPayload.put("externalDatabaseRefID", AzifaceModule.DemonstrationExternalDatabaseRefID);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), sessionRequestCallPayload.toString());

    Request request = new Request.Builder()
      .url(Config.BaseURL)
      .headers(Config.getHeaders())
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
        callAbortAndClose(referencingProcessor, response, sessionRequestCallback);
      }
    } else {
      callAbortAndClose(referencingProcessor, response, sessionRequestCallback);
    }

    return null;
  }

  static void callAbortAndClose(SessionRequestProcessor referencingProcessor, okhttp3.Response response, @NonNull FaceTecSessionRequestProcessor.Callback sessionRequestCallback) {
    referencingProcessor.onCatastrophicNetworkError(sessionRequestCallback);
    response.close();
  }
}

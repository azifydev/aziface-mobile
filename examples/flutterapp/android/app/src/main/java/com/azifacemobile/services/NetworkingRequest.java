package com.azifacemobile.services;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facetec.sdk.FaceTecSessionRequestProcessor;
import com.azifacemobile.SessionRequestProcessor;
import com.azifacemobile.AzifaceMobileModule;
import com.azifacemobile.Config;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NetworkingRequest {
  public static void send(
    @NonNull SessionRequestProcessor referencingProcessor,
    @NonNull String sessionRequestBlob,
    @NonNull FaceTecSessionRequestProcessor.Callback sessionRequestCallback,
    @Nullable Map<String, Object> data
  ) {
    JSONObject sessionRequestCallPayload = new JSONObject();

    try {
      if (data != null) {
        sessionRequestCallPayload.put("data", new JSONObject(data));
      }
      sessionRequestCallPayload.put("requestBlob", sessionRequestBlob);

      if (!AzifaceMobileModule.DemonstrationExternalDatabaseRefID.isEmpty()) {
        sessionRequestCallPayload.put("externalDatabaseRefID", AzifaceMobileModule.DemonstrationExternalDatabaseRefID);
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
        JSONObject responseJSON = getResponseJSONOrHandleError(response, referencingProcessor, sessionRequestCallback);

        String responseBlob = null;

        try {
          if (responseJSON != null) {
            responseBlob = responseJSON.getString("responseBlob");
          }
        } catch (JSONException ignored) {}

        if (responseBlob != null) {
          referencingProcessor.onResponseBlobReceived(responseJSON, sessionRequestCallback);
        }
      }

      @Override
      public void onFailure(@NonNull Call call, @NonNull IOException e) {
        referencingProcessor.onCatastrophicNetworkError(null, sessionRequestCallback);
      }
    });
  }

  @Nullable
  static JSONObject getResponseJSONOrHandleError(okhttp3.Response response, SessionRequestProcessor referencingProcessor, @NonNull FaceTecSessionRequestProcessor.Callback sessionRequestCallback) throws IOException {
    if (response.isSuccessful() && response.body() != null) {
      try {
        JSONObject responseJSON = new JSONObject(response.body().string());

        response.close();
        return responseJSON;
      } catch (JSONException e) {
        callAbortAndClose(referencingProcessor, response, sessionRequestCallback);
      }
    } else {
      callAbortAndClose(referencingProcessor, response, sessionRequestCallback);
    }

    return null;
  }

  static void callAbortAndClose(SessionRequestProcessor referencingProcessor, okhttp3.Response response, @NonNull FaceTecSessionRequestProcessor.Callback sessionRequestCallback) {
    referencingProcessor.onCatastrophicNetworkError(null, sessionRequestCallback);
    response.close();
  }
}

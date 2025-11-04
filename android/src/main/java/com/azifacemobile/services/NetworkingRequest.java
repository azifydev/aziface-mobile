package com.azifacemobile.services;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.azifacemobile.SessionRequestProcessor;
import com.azifacemobile.AzifaceMobileModule;
import com.azifacemobile.Config;
import com.azifacemobile.models.ProcessorResponse;
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
        ProcessorResponse processorResponse = getResponseBlobOrHandleError(response, referencingProcessor, sessionRequestCallback);
        if (processorResponse.getSuccess()) {
          referencingProcessor.onResponseBlobReceived(processorResponse, sessionRequestCallback);
        }
      }

      @Override
      public void onFailure(@NonNull Call call, @NonNull IOException e) {
        ProcessorResponse processorResponse = new ProcessorResponse();

        referencingProcessor.onCatastrophicNetworkError(processorResponse, sessionRequestCallback);
      }
    });
  }

  static ProcessorResponse getResponseBlobOrHandleError(okhttp3.Response response, SessionRequestProcessor referencingProcessor, @NonNull FaceTecSessionRequestProcessor.Callback sessionRequestCallback) throws IOException {
    ProcessorResponse processorResponse = new ProcessorResponse();

    if (response.isSuccessful() && response.body() != null) {
      try {
        JSONObject responseJSON = new JSONObject(response.body().string());

        processorResponse.setData(responseJSON);
        System.out.println(responseJSON.toString(2));

        response.close();
        return processorResponse;
      } catch (JSONException e) {
        callAbortAndClose(referencingProcessor, response, sessionRequestCallback);
      }
    } else {
      callAbortAndClose(referencingProcessor, response, sessionRequestCallback);
    }

    return processorResponse;
  }

  static void callAbortAndClose(SessionRequestProcessor referencingProcessor, okhttp3.Response response, @NonNull FaceTecSessionRequestProcessor.Callback sessionRequestCallback) {
    ProcessorResponse processorResponse = new ProcessorResponse();

    referencingProcessor.onCatastrophicNetworkError(processorResponse, sessionRequestCallback);
    response.close();
  }
}

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
import java.util.ArrayList;

import com.azify.processors.utils.DynamicRoute;
import com.azify.processors.helpers.ThemeUtils;
import com.azify.azifacemobilesdk.AzifaceMobileSdkModule;
import com.facebook.react.bridge.ReadableMap;
import com.facetec.sdk.*;

public class PhotoIDMatchProcessor extends Processor implements FaceTecFaceScanProcessor, FaceTecIDScanProcessor {
  private final String principalKey = "photoIdMatchMessage";
  private final String latestExternalDatabaseRefID;
  private final ReadableMap data;
  private final AzifaceMobileSdkModule faceTecModule;
  private final ThemeUtils FaceThemeUtils = new ThemeUtils();
  private boolean success = false;
  private boolean faceScanWasSuccessful = false;

  public PhotoIDMatchProcessor(String sessionToken, Context context, AzifaceMobileSdkModule faceTecModule,
      ReadableMap data) {
    this.faceTecModule = faceTecModule;
    this.latestExternalDatabaseRefID = this.faceTecModule.getLatestExternalDatabaseRefID();
    this.data = data;

    FaceTecCustomization.setIDScanUploadMessageOverrides(
        // Upload of ID front-side has started.
        FaceThemeUtils.handleMessage(principalKey, "frontSideUploadStarted", "Uploading\nEncrypted\nID Scan"),
        // Upload of ID front-side is still uploading to Server after an extended period
        // of time.
        FaceThemeUtils.handleMessage(principalKey, "frontSideStillUploading",
            "Still Uploading...\nSlow Connection"),
        // Upload of ID front-side to the Server is complete.
        FaceThemeUtils.handleMessage(principalKey, "frontSideUploadCompleteAwaitingResponse",
            "Upload Complete"),
        // Upload of ID front-side is complete and we are waiting for the Server to
        // finish processing and respond.
        FaceThemeUtils.handleMessage(principalKey, "frontSideUploadCompleteAwaitingProcessing",
            "Processing ID Scan"),
        // Upload of ID back-side has started.
        FaceThemeUtils.handleMessage(principalKey, "backSideUploadStarted",
            "Uploading\nEncrypted\nBack of ID"),
        // Upload of ID back-side is still uploading to Server after an extended period
        // of time.
        FaceThemeUtils.handleMessage(principalKey, "backSideStillUploading",
            "Still Uploading...\nSlow Connection"),
        // Upload of ID back-side to Server is complete.
        FaceThemeUtils.handleMessage(principalKey, "backSideUploadCompleteAwaitingResponse",
            "Upload Complete"),
        // Upload of ID back-side is complete and we are waiting for the Server to
        // finish processing and respond.
        FaceThemeUtils.handleMessage(principalKey, "backSideUploadCompleteAwaitingProcessing",
            "Processing Back of ID"),
        // Upload of User Confirmed Info has started.
        FaceThemeUtils.handleMessage(principalKey, "userConfirmedInfoUploadStarted",
            "Uploading\nYour Confirmed Info"),
        // Upload of User Confirmed Info is still uploading to Server after an extended
        // period of time.
        FaceThemeUtils.handleMessage(principalKey, "userConfirmedInfoStillUploading",
            "Still Uploading...\nSlow Connection"),
        // Upload of User Confirmed Info to the Server is complete.
        FaceThemeUtils.handleMessage(principalKey, "userConfirmedInfoUploadCompleteAwaitingResponse",
            "Upload Complete"),
        // Upload of User Confirmed Info is complete and we are waiting for the Server
        // to finish processing and respond.
        FaceThemeUtils.handleMessage(principalKey, "userConfirmedInfoUploadCompleteAwaitingProcessing",
            "Processing"),
        // Upload of NFC Details has started.
        FaceThemeUtils.handleMessage(principalKey, "nfcUploadStarted",
            "Uploading Encrypted\nNFC Details"),
        // Upload of NFC Details is still uploading to Server after an extended period
        // of time.
        FaceThemeUtils.handleMessage(principalKey, "nfcStillUploading",
            "Still Uploading...\nSlow Connection"),
        // Upload of NFC Details to the Server is complete.
        FaceThemeUtils.handleMessage(principalKey, "nfcUploadCompleteAwaitingResponse",
            "Upload Complete"),
        // Upload of NFC Details is complete and we are waiting for the Server to finish
        // processing and respond.
        FaceThemeUtils.handleMessage(principalKey, "nfcUploadCompleteAwaitingProcessing",
            "Processing\nNFC Details"),
        // Upload of ID Details has started.
        FaceThemeUtils.handleMessage(principalKey, "skippedNFCUploadStarted",
            "Uploading Encrypted\nID Details"),
        // Upload of ID Details is still uploading to Server after an extended period of
        // time.
        FaceThemeUtils.handleMessage(principalKey, "skippedNFCStillUploading",
            "Still Uploading...\nSlow Connection"),
        // Upload of ID Details to the Server is complete.
        FaceThemeUtils.handleMessage(principalKey, "skippedNFCUploadCompleteAwaitingResponse",
            "Upload Complete"),
        // Upload of ID Details is complete and we are waiting for the Server to finish
        // processing and respond.
        FaceThemeUtils.handleMessage(principalKey, "skippedNFCUploadCompleteAwaitingProcessing",
            "Processing\nID Details"));

    faceTecModule.sendEvent("onCloseModal", true);
    FaceTecSessionActivity.createAndLaunchSession(context, PhotoIDMatchProcessor.this, PhotoIDMatchProcessor.this,
        sessionToken);
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
      parameters.put("externalDatabaseRefID", this.latestExternalDatabaseRefID);
    } catch (JSONException e) {
      e.printStackTrace();
      faceTecModule.sendEvent("onCloseModal", false);
      faceTecModule.processorPromise.reject("Exception raised while attempting to create JSON payload for upload.",
          "JSONError");
    }

    DynamicRoute dynamicRoute = new DynamicRoute();
    String pathUrl = dynamicRoute.getPathUrl("base", "/Process/" + Config.ProcessId + "/Enrollment3d");

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
            FaceTecCustomization.overrideResultScreenSuccessMessage = FaceThemeUtils.handleMessage(
                principalKey, "successMessage",
                "Liveness\nConfirmed");
            faceScanWasSuccessful = faceScanResultCallback.proceedToNextStep(scanResultBlob);
          } else {
            faceScanResultCallback.cancel();
            faceTecModule.sendEvent("onCloseModal", false);
            faceTecModule.processorPromise.reject("FaceTec SDK wasn't have to liveness values processed!",
                "FaceTecLivenessWasntProcessed");
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
      public void onFailure(@NonNull Call call, @NonNull IOException e) {
        faceScanResultCallback.cancel();
        faceTecModule.sendEvent("onCloseModal", false);
        faceTecModule.processorPromise.reject("Exception raised while attempting HTTPS call.", "HTTPSError");
      }
    });
  }

  public void processIDScanWhileFaceTecSDKWaits(final FaceTecIDScanResult idScanResult,
      final FaceTecIDScanResultCallback idScanResultCallback) {
    faceTecModule.setLatestIDScanResult(idScanResult);

    if (idScanResult.getStatus() != FaceTecIDScanStatus.SUCCESS) {
      NetworkingHelpers.cancelPendingRequests();
      idScanResultCallback.cancel();
      faceTecModule.sendEvent("onCloseModal", false);
      faceTecModule.processorPromise.reject("Status is not success!", "AziFaceTecDifferentStatus");
      return;
    }

    final int minMatchLevel = 3;

    JSONObject parameters = new JSONObject();
    try {
      parameters.put("externalDatabaseRefID", this.latestExternalDatabaseRefID);
      parameters.put("idScan", idScanResult.getIDScanBase64());
      parameters.put("minMatchLevel", minMatchLevel);

      ArrayList<String> frontImagesCompressedBase64 = idScanResult.getFrontImagesCompressedBase64();
      ArrayList<String> backImagesCompressedBase64 = idScanResult.getBackImagesCompressedBase64();
      if (!frontImagesCompressedBase64.isEmpty()) {
        parameters.put("idScanFrontImage", frontImagesCompressedBase64.get(0));
      }
      if (!backImagesCompressedBase64.isEmpty()) {
        parameters.put("idScanBackImage", backImagesCompressedBase64.get(0));
      }
    } catch (JSONException e) {
      e.printStackTrace();
      faceTecModule.sendEvent("onCloseModal", false);
      faceTecModule.processorPromise.reject("Exception raised while attempting to parse JSON result.",
          "JSONError");
    }

    DynamicRoute dynamicRoute = new DynamicRoute();
    String pathUrl = dynamicRoute.getPathUrl("match", "/Process/" + Config.ProcessId + "/Match3d2dIdScan");

    okhttp3.Request request = new okhttp3.Request.Builder()
        .url(Config.BaseURL + pathUrl)
        .headers(Config.getHeaders("POST"))
        .post(new ProgressRequestBody(
            RequestBody.create(MediaType.parse("application/json; charset=utf-8"), parameters.toString()),
            new ProgressRequestBody.Listener() {
              @Override
              public void onUploadProgressChanged(long bytesWritten, long totalBytes) {
                final float uploadProgressPercent = ((float) bytesWritten) / ((float) totalBytes);
                idScanResultCallback.uploadProgress(uploadProgressPercent);
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
            FaceTecCustomization.setIDScanResultScreenMessageOverrides(
                // Successful scan of ID front-side (ID Types with no back-side).
                FaceThemeUtils.handleMessage(principalKey, "successFrontSide",
                    "ID Scan Complete"),
                // Successful scan of ID front-side (ID Types that have a back-side).
                FaceThemeUtils.handleMessage(principalKey, "successFrontSideBackNext",
                    "Front of ID\nScanned"),
                // Successful scan of ID front-side (ID Types that do have NFC but do not have a
                // back-side).
                FaceThemeUtils.handleMessage(principalKey, "successFrontSideNFCNext",
                    "Front of ID\nScanned"),
                // Successful scan of the ID back-side (ID Types that do not have NFC).
                FaceThemeUtils.handleMessage(principalKey, "successBackSide",
                    "ID Scan Complete"),
                // Successful scan of the ID back-side (ID Types that do have NFC).
                FaceThemeUtils.handleMessage(principalKey, "successBackSideNFCNext",
                    "Back of ID\nScanned"),
                // Successful scan of a Passport that does not have NFC.
                FaceThemeUtils.handleMessage(principalKey, "successPassport",
                    "Passport Scan Complete"),
                // Successful scan of a Passport that does have NFC.
                FaceThemeUtils.handleMessage(principalKey, "successPassportNFCNext",
                    "Passport Scanned"),
                // Successful upload of final IDScan containing User-Confirmed ID Text.
                FaceThemeUtils.handleMessage(principalKey, "successUserConfirmation",
                    "Photo ID Scan\nComplete"),
                // Successful upload of the scanned NFC chip information.
                FaceThemeUtils.handleMessage(principalKey, "successNFC",
                    "ID Scan Complete"),
                // Case where a Retry is needed because the Face on the Photo ID did not Match
                // the User's Face highly enough.
                FaceThemeUtils.handleMessage(principalKey, "retryFaceDidNotMatch",
                    "Face Didn't Match\nHighly Enough"),
                // Case where a Retry is needed because a Full ID was not detected with high
                // enough confidence.
                FaceThemeUtils.handleMessage(principalKey, "retryIDNotFullyVisible",
                    "ID Document\nNot Fully Visible"),
                // Case where a Retry is needed because the OCR did not produce good enough
                // results and the User should Retry with a better capture.
                FaceThemeUtils.handleMessage(principalKey, "retryOCRResultsNotGoodEnough",
                    "ID Text Not Legible"),
                // Case where there is likely no OCR Template installed for the document the
                // User is attempting to scan.
                FaceThemeUtils.handleMessage(principalKey, "retryIDTypeNotSupported",
                    "ID Type Mismatch\nPlease Try Again"),
                // Case where NFC Scan was skipped due to the user's interaction or an
                // unexpected error.
                FaceThemeUtils.handleMessage(principalKey, "skipOrErrorNFC",
                    "ID Details\nUploaded"));

            success = idScanResultCallback.proceedToNextStep(scanResultBlob);
            if (success) {
              faceTecModule.processorPromise.resolve(true);
            }
          } else {
            idScanResultCallback.cancel();
            faceTecModule.sendEvent("onCloseModal", false);
            faceTecModule.processorPromise.reject("FaceTec SDK wasn't have to scan values processed!",
                "AziFaceScanValuesWereNotProcessed");
          }
        } catch (JSONException e) {
          e.printStackTrace();
          idScanResultCallback.cancel();
          faceTecModule.sendEvent("onCloseModal", false);
          faceTecModule.processorPromise.reject("Exception raised while attempting to parse JSON result.",
              "JSONError");
        }
      }

      @Override
      public void onFailure(@NonNull Call call, @NonNull IOException e) {
        idScanResultCallback.cancel();
        faceTecModule.sendEvent("onCloseModal", false);
        faceTecModule.processorPromise.reject("Exception raised while attempting HTTPS call.", "HTTPSError");
      }
    });
  }

  public boolean isSuccess() {
    return this.success;
  }
}

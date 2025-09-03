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

import com.azify.utils.DynamicRoute;
import com.azify.azifacemobilesdk.AzifaceMobileSdkModule;
import com.facebook.react.bridge.ReadableMap;
import com.facetec.sdk.*;

public class PhotoIDMatchProcessor extends Processor implements FaceTecFaceScanProcessor, FaceTecIDScanProcessor {
  private final String latestExternalDatabaseRefID;
  private final ReadableMap data;
  private final AzifaceMobileSdkModule module;
  private boolean success = false;
  private boolean faceScanWasSuccessful = false;

  public PhotoIDMatchProcessor(String sessionToken, Context context, AzifaceMobileSdkModule module,
      ReadableMap data) {
    this.module = module;
    this.latestExternalDatabaseRefID = this.module.getLatestExternalDatabaseRefID();
    this.data = data;

    FaceTecCustomization.setIDScanUploadMessageOverrides(
        // Upload of ID front-side has started.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("frontSide", "uploadStarted", "Uploading\nEncrypted\nID Scan"),
        // Upload of ID front-side is still uploading to Server after an extended period
        // of time.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("frontSide", "stillUploading", "Still Uploading...\nSlow Connection"),
        // Upload of ID front-side to the Server is complete.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("frontSide", "uploadCompleteAwaitingResponse", "Upload Complete"),
        // Upload of ID front-side is complete and we are waiting for the Server to
        // finish processing and respond.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("frontSide", "uploadCompleteAwaitingProcessing", "Processing ID Scan"),
        // Upload of ID back-side has started.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("backSide", "uploadStarted", "Uploading\nEncrypted\nBack of ID"),
        // Upload of ID back-side is still uploading to Server after an extended period
        // of time.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("backSide", "stillUploading", "Still Uploading...\nSlow Connection"),
        // Upload of ID back-side to Server is complete.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("backSide", "uploadCompleteAwaitingResponse", "Upload Complete"),
        // Upload of ID back-side is complete and we are waiting for the Server to
        // finish processing and respond.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("backSide", "uploadCompleteAwaitingProcessing", "Processing Back of ID"),
        // Upload of User Confirmed Info has started.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("userConfirmedInfo", "uploadStarted", "Uploading\nYour Confirmed Info"),
        // Upload of User Confirmed Info is still uploading to Server after an extended
        // period of time.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("userConfirmedInfo", "stillUploading", "Still Uploading...\nSlow Connection"),
        // Upload of User Confirmed Info to the Server is complete.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("userConfirmedInfo", "uploadCompleteAwaitingResponse", "Upload Complete"),
        // Upload of User Confirmed Info is complete and we are waiting for the Server
        // to finish processing and respond.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("userConfirmedInfo", "uploadCompleteAwaitingProcessing", "Processing"),
        // Upload of NFC Details has started.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("nfc", "uploadStarted", "Uploading Encrypted\nNFC Details"),
        // Upload of NFC Details is still uploading to Server after an extended period
        // of time.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("nfc", "stillUploading", "Still Uploading...\nSlow Connection"),
        // Upload of NFC Details to the Server is complete.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("nfc", "uploadCompleteAwaitingResponse", "Upload Complete"),
        // Upload of NFC Details is complete and we are waiting for the Server to finish
        // processing and respond.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("nfc", "uploadCompleteAwaitingProcessing", "Processing\nNFC Details"),
        // Upload of ID Details has started.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("skippedNFC", "uploadStarted", "Uploading Encrypted\nID Details"),
        // Upload of ID Details is still uploading to Server after an extended period of
        // time.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("skippedNFC", "stillUploading", "Still Uploading...\nSlow Connection"),
        // Upload of ID Details to the Server is complete.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("skippedNFC", "uploadCompleteAwaitingResponse", "Upload Complete"),
        // Upload of ID Details is complete and we are waiting for the Server to finish
        // processing and respond.
        AzifaceMobileSdkModule.AziTheme
            .getPhotoIDMatchMessage("skippedNFC", "uploadCompleteAwaitingProcessing", "Processing\nID Details"));

    this.module.sendEvent("onOpen", true);
    this.module.sendEvent("onClose", false);
    this.module.sendEvent("onCancel", false);
    this.module.sendEvent("onError", false);

    FaceTecSessionActivity.createAndLaunchSession(context, PhotoIDMatchProcessor.this, PhotoIDMatchProcessor.this,
        sessionToken);
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
      this.module.sendEvent("onOpen", false);
      this.module.sendEvent("onClose", true);
      this.module.sendEvent("onCancel", false);
      this.module.sendEvent("onError", true);
      this.module.promise.reject("Exception raised while attempting to create JSON payload for upload.",
          "JSONError");
    }

    DynamicRoute dynamicRoute = new DynamicRoute();
    String pathUrl = dynamicRoute.getPathUrlEnrollment3d("base");

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
              .getPhotoIDMatchMessage("successMessage","Liveness\nConfirmed");

            faceScanWasSuccessful = faceScanResultCallback.proceedToNextStep(scanResultBlob);
          } else {
            faceScanResultCallback.cancel();
            module.sendEvent("onOpen", false);
            module.sendEvent("onClose", true);
            module.sendEvent("onCancel", true);
            module.sendEvent("onError", true);
            module.promise.reject("AziFace SDK wasn't have to liveness values processed!",
                "SessionNotProcessedError");
          }
        } catch (JSONException e) {
          faceScanResultCallback.cancel();
          module.sendEvent("onOpen", false);
          module.sendEvent("onClose", true);
          module.sendEvent("onCancel", true);
          module.sendEvent("onError", true);
          module.promise.reject("Exception raised while attempting to parse JSON result.",
              "JSONError");
        }
      }

      @Override
      public void onFailure(@NonNull Call call, @NonNull IOException e) {
        faceScanResultCallback.cancel();
        module.sendEvent("onOpen", false);
        module.sendEvent("onClose", true);
        module.sendEvent("onCancel", true);
        module.sendEvent("onError", true);
        module.promise.reject("Exception raised while attempting HTTPS call.", "HTTPSError");
      }
    });
  }

  public void processIDScanWhileFaceTecSDKWaits(final FaceTecIDScanResult idScanResult,
      final FaceTecIDScanResultCallback idScanResultCallback) {
    this.module.setLatestIDScanResult(idScanResult);

    if (idScanResult.getStatus() != FaceTecIDScanStatus.SUCCESS) {
      NetworkingHelpers.cancelPendingRequests();
      idScanResultCallback.cancel();
      module.sendEvent("onOpen", false);
      module.sendEvent("onClose", true);
      module.sendEvent("onCancel", true);
      module.sendEvent("onError", false);
      this.module.promise.reject("Scan status is not success!", "SessionScanStatusError");
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
      module.sendEvent("onOpen", false);
      module.sendEvent("onClose", true);
      module.sendEvent("onCancel", true);
      module.sendEvent("onError", true);
      this.module.promise.reject("Exception raised while attempting to parse JSON result.",
          "JSONError");
    }

    DynamicRoute dynamicRoute = new DynamicRoute();
    String pathUrl = dynamicRoute.getPathUrlMatch3d2dIdScan("match");

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
          boolean error = responseJSONData.getBoolean("error");

          if (error) {
            idScanResultCallback.cancel();
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
            FaceTecCustomization.setIDScanResultScreenMessageOverrides(
                // Successful scan of ID front-side (ID Types with no back-side).
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("success", "frontSide", "ID Scan Complete"),
                // Successful scan of ID front-side (ID Types that have a back-side).
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("success", "frontSideBackNext", "Front of ID\nScanned"),
                // Successful scan of ID front-side (ID Types that do have NFC but do not have a
                // back-side).
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("success", "frontSideNFCNext", "Front of ID\nScanned"),
                // Successful scan of the ID back-side (ID Types that do not have NFC).
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("success", "backSide", "ID Scan Complete"),
                // Successful scan of the ID back-side (ID Types that do have NFC).
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("success", "backSideNFCNext", "Back of ID\nScanned"),
                // Successful scan of a Passport that does not have NFC.
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("success", "passport", "Passport Scan Complete"),
                // Successful scan of a Passport that does have NFC.
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("success", "passportNFCNext", "Passport Scanned"),
                // Successful upload of final IDScan containing User-Confirmed ID Text.
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("success", "userConfirmation", "Photo ID Scan\nComplete"),
                // Successful upload of the scanned NFC chip information.
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("success", "NFC", "ID Scan Complete"),
                // Case where a Retry is needed because the Face on the Photo ID did not Match
                // the User's Face highly enough.
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("retry", "faceDidNotMatch", "Face Didn't Match\nHighly Enough"),
                // Case where a Retry is needed because a Full ID was not detected with high
                // enough confidence.
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("retry", "IDNotFullyVisible", "ID Document\nNot Fully Visible"),
                // Case where a Retry is needed because the OCR did not produce good enough
                // results and the User should Retry with a better capture.
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("retry", "OCRResultsNotGoodEnough", "ID Text Not Legible"),
                // Case where there is likely no OCR Template installed for the document the
                // User is attempting to scan.
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("retry", "IDTypeNotSupported", "ID Type Mismatch\nPlease Try Again"),
                // Case where NFC Scan was skipped due to the user's interaction or an
                // unexpected error.
                AzifaceMobileSdkModule.AziTheme
                    .getPhotoIDMatchMessage("skipOrErrorNFC", "ID Details\nUploaded"));

            success = idScanResultCallback.proceedToNextStep(scanResultBlob);
            if (success) {
              module.sendEvent("onOpen", false);
              module.sendEvent("onClose", true);
              module.sendEvent("onCancel", false);
              module.sendEvent("onError", false);
              module.promise.resolve(true);
            }
          } else {
            idScanResultCallback.cancel();
            module.sendEvent("onOpen", false);
            module.sendEvent("onClose", true);
            module.sendEvent("onCancel", true);
            module.sendEvent("onError", true);
            module.promise.reject("AziFace SDK wasn't have to scan values processed!",
                "SessionNotProcessedError");
          }
        } catch (JSONException e) {
          idScanResultCallback.cancel();
          module.sendEvent("onOpen", false);
          module.sendEvent("onClose", true);
          module.sendEvent("onCancel", true);
          module.sendEvent("onError", true);
          module.promise.reject("Exception raised while attempting to parse JSON result.",
              "JSONError");
        }
      }

      @Override
      public void onFailure(@NonNull Call call, @NonNull IOException e) {
        idScanResultCallback.cancel();
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

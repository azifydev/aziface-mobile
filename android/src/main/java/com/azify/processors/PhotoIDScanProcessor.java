package com.azify.processors;

import android.content.Context;

import androidx.annotation.NonNull;

import com.azify.azifacemobilesdk.AzifaceMobileSdkModule;
import com.facebook.react.bridge.ReadableMap;
import com.facetec.sdk.FaceTecCustomization;
import com.facetec.sdk.FaceTecIDScanProcessor;
import com.facetec.sdk.FaceTecIDScanResult;
import com.facetec.sdk.FaceTecIDScanResultCallback;
import com.facetec.sdk.FaceTecIDScanStatus;
import com.facetec.sdk.FaceTecSessionActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import com.azify.processors.helpers.ThemeUtils;

public class PhotoIDScanProcessor extends Processor implements FaceTecIDScanProcessor {
	private final String key = "photoIdScanMessage";
	private final ReadableMap data;
	private final AzifaceMobileSdkModule aziFaceModule;
	private final ThemeUtils aziThemeUtils = new ThemeUtils();
	private boolean success = false;

	public PhotoIDScanProcessor(String sessionToken, Context context, AzifaceMobileSdkModule aziFaceModule,
			ReadableMap data) {
		this.aziFaceModule = aziFaceModule;
		this.data = data;

		FaceTecCustomization.setIDScanUploadMessageOverrides(
				// Upload of ID front-side has started.
				aziThemeUtils.handleMessage(key, "frontSideUploadStarted", "Uploading\nEncrypted\nID Scan"),
				// Upload of ID front-side is still uploading to Server after an extended period
				// of time.
				aziThemeUtils.handleMessage(key, "frontSideStillUploading",
						"Still Uploading...\nSlow Connection"),
				// Upload of ID front-side to the Server is complete.
				aziThemeUtils.handleMessage(key, "frontSideUploadCompleteAwaitingResponse",
						"Upload Complete"),
				// Upload of ID front-side is complete and we are waiting for the Server to
				// finish processing and respond.
				aziThemeUtils.handleMessage(key, "frontSideUploadCompleteAwaitingProcessing",
						"Processing ID Scan"),
				// Upload of ID back-side has started.
				aziThemeUtils.handleMessage(key, "backSideUploadStarted",
						"Uploading\nEncrypted\nBack of ID"),
				// Upload of ID back-side is still uploading to Server after an extended period
				// of time.
				aziThemeUtils.handleMessage(key, "backSideStillUploading",
						"Still Uploading...\nSlow Connection"),
				// Upload of ID back-side to Server is complete.
				aziThemeUtils.handleMessage(key, "backSideUploadCompleteAwaitingResponse",
						"Upload Complete"),
				// Upload of ID back-side is complete and we are waiting for the Server to
				// finish processing and respond.
				aziThemeUtils.handleMessage(key, "backSideUploadCompleteAwaitingProcessing",
						"Processing Back of ID"),
				// Upload of User Confirmed Info has started.
				aziThemeUtils.handleMessage(key, "userConfirmedInfoUploadStarted",
						"Uploading\nYour Confirmed Info"),
				// Upload of User Confirmed Info is still uploading to Server after an extended
				// period of time.
				aziThemeUtils.handleMessage(key, "userConfirmedInfoStillUploading",
						"Still Uploading...\nSlow Connection"),
				// Upload of User Confirmed Info to the Server is complete.
				aziThemeUtils.handleMessage(key, "userConfirmedInfoUploadCompleteAwaitingResponse",
						"Upload Complete"),
				// Upload of User Confirmed Info is complete and we are waiting for the Server
				// to finish processing and respond.
				aziThemeUtils.handleMessage(key, "userConfirmedInfoUploadCompleteAwaitingProcessing",
						"Processing"),
				// Upload of NFC Details has started.
				aziThemeUtils.handleMessage(key, "nfcUploadStarted",
						"Uploading Encrypted\nNFC Details"),
				// Upload of NFC Details is still uploading to Server after an extended period
				// of time.
				aziThemeUtils.handleMessage(key, "nfcStillUploading",
						"Still Uploading...\nSlow Connection"),
				// Upload of NFC Details to the Server is complete.
				aziThemeUtils.handleMessage(key, "nfcUploadCompleteAwaitingResponse",
						"Upload Complete"),
				// Upload of NFC Details is complete and we are waiting for the Server to finish
				// processing and respond.
				aziThemeUtils.handleMessage(key, "nfcUploadCompleteAwaitingProcessing",
						"Processing\nNFC Details"),
				// Upload of ID Details has started.
				aziThemeUtils.handleMessage(key, "skippedNFCUploadStarted",
						"Uploading Encrypted\nID Details"),
				// Upload of ID Details is still uploading to Server after an extended period of
				// time.
				aziThemeUtils.handleMessage(key, "skippedNFCStillUploading",
						"Still Uploading...\nSlow Connection"),
				// Upload of ID Details to the Server is complete.
				aziThemeUtils.handleMessage(key, "skippedNFCUploadCompleteAwaitingResponse",
						"Upload Complete"),
				// Upload of ID Details is complete and we are waiting for the Server to finish
				// processing and respond.
				aziThemeUtils.handleMessage(key, "skippedNFCUploadCompleteAwaitingProcessing",
						"Processing\nID Details"));

		aziFaceModule.sendEvent("onCloseModal", true);
		FaceTecSessionActivity.createAndLaunchSession(context, PhotoIDScanProcessor.this, sessionToken);
	}

	public void processIDScanWhileFaceTecSDKWaits(final FaceTecIDScanResult idScanResult,
			final FaceTecIDScanResultCallback idScanResultCallback) {
		aziFaceModule.setLatestIDScanResult(idScanResult);

		if (idScanResult.getStatus() != FaceTecIDScanStatus.SUCCESS) {
			NetworkingHelpers.cancelPendingRequests();
			idScanResultCallback.cancel();
			aziFaceModule.sendEvent("onCloseModal", false);
			aziFaceModule.processorPromise.reject("The scan status has not been completed!", "AziFaceInvalidSession");
			return;
		}

		JSONObject parameters = new JSONObject();
		try {
			if (this.data != null) {
				parameters.put("data", new JSONObject(this.data.toHashMap()));
			}
			parameters.put("idScan", idScanResult.getIDScanBase64());

			ArrayList<String> frontImagesCompressedBase64 = idScanResult.getFrontImagesCompressedBase64();
			ArrayList<String> backImagesCompressedBase64 = idScanResult.getBackImagesCompressedBase64();
			if (frontImagesCompressedBase64.size() > 0) {
				parameters.put("idScanFrontImage", frontImagesCompressedBase64.get(0));
			}
			if (backImagesCompressedBase64.size() > 0) {
				parameters.put("idScanBackImage", backImagesCompressedBase64.get(0));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			aziFaceModule.sendEvent("onCloseModal", false);
			aziFaceModule.processorPromise.reject("Exception raised while attempting to create JSON payload for upload.",
					"JSONError");
		}

		okhttp3.Request request = new okhttp3.Request.Builder()
				.url(Config.BaseURL + "/idscan-only")
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
					boolean wasProcessed = responseJSON.getBoolean("wasProcessed");
					String scanResultBlob = responseJSON.getString("scanResultBlob");

					if (wasProcessed) {
						FaceTecCustomization.setIDScanResultScreenMessageOverrides(
								// Successful scan of ID front-side (ID Types with no back-side).
								aziThemeUtils.handleMessage(key, "successFrontSide",
										"ID Scan Complete"),
								// Successful scan of ID front-side (ID Types that have a back-side).
								aziThemeUtils.handleMessage(key, "successFrontSideBackNext",
										"Front of ID\nScanned"),
								// Successful scan of ID front-side (ID Types that do have NFC but do not have a
								// back-side).
								aziThemeUtils.handleMessage(key, "successFrontSideNFCNext",
										"Front of ID\nScanned"),
								// Successful scan of the ID back-side (ID Types that do not have NFC).
								aziThemeUtils.handleMessage(key, "successBackSide",
										"ID Scan Complete"),
								// Successful scan of the ID back-side (ID Types that do have NFC).
								aziThemeUtils.handleMessage(key, "successBackSideNFCNext",
										"Back of ID\nScanned"),
								// Successful scan of a Passport that does not have NFC.
								aziThemeUtils.handleMessage(key, "successPassport",
										"Passport Scan Complete"),
								// Successful scan of a Passport that does have NFC.
								aziThemeUtils.handleMessage(key, "successPassportNFCNext",
										"Passport Scanned"),
								// Successful upload of final IDScan containing User-Confirmed ID Text.
								aziThemeUtils.handleMessage(key, "successUserConfirmation",
										"Photo ID Scan\nComplete"),
								// Successful upload of the scanned NFC chip information.
								aziThemeUtils.handleMessage(key, "successNFC",
										"ID Scan Complete"),
								// Case where a Retry is needed because the Face on the Photo ID did not Match
								// the User's Face highly enough.
								aziThemeUtils.handleMessage(key, "retryFaceDidNotMatch",
										"Face Didn't Match\nHighly Enough"),
								// Case where a Retry is needed because a Full ID was not detected with high
								// enough confidence.
								aziThemeUtils.handleMessage(key, "retryIDNotFullyVisible",
										"ID Document\nNot Fully Visible"),
								// Case where a Retry is needed because the OCR did not produce good enough
								// results and the User should Retry with a better capture.
								aziThemeUtils.handleMessage(key, "retryOCRResultsNotGoodEnough",
										"ID Text Not Legible"),
								// Case where there is likely no OCR Template installed for the document the
								// User is attempting to scan.
								aziThemeUtils.handleMessage(key, "retryIDTypeNotSupported",
										"ID Type Mismatch\nPlease Try Again"),
								// Case where NFC Scan was skipped due to the user's interaction or an
								// unexpected error.
								aziThemeUtils.handleMessage(key, "skipOrErrorNFC",
										"ID Details\nUploaded"));

						success = idScanResultCallback.proceedToNextStep(scanResultBlob);
						if (success) {
							aziFaceModule.sendEvent("onCloseModal", false);
							aziFaceModule.processorPromise.resolve(true);
						}
					} else {
						idScanResultCallback.cancel();
						aziFaceModule.sendEvent("onCloseModal", false);
						aziFaceModule.processorPromise.reject("AziFace SDK values were not processed!",
								"AziFaceValuesWereNotProcessed");
					}
				} catch (JSONException e) {
					e.printStackTrace();
					idScanResultCallback.cancel();
					aziFaceModule.sendEvent("onCloseModal", false);
					aziFaceModule.processorPromise.reject("Exception raised while attempting to parse JSON result.",
							"JSONError");
				}
			}

			@Override
			public void onFailure(@NonNull Call call, @NonNull IOException e) {
				idScanResultCallback.cancel();
				aziFaceModule.sendEvent("onCloseModal", false);
				aziFaceModule.processorPromise.reject("Exception raised while attempting HTTPS call.", "HTTPSError");
			}
		});
	}

	public boolean isSuccess() {
		return this.success;
	}
}

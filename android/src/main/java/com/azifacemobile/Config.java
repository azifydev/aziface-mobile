package com.azifacemobile;

import android.graphics.Rect;

import com.azifacemobile.theme.*;
import com.facebook.react.bridge.ReadableMap;
import com.facetec.sdk.*;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class Config {
  public static Boolean IsDevelopment = false;
  public static String DeviceKeyIdentifier = null;
  public static String BaseURL = null;
  public static ReadableMap Headers = null;

  private static Map<String, String> parseReadableMapToMap() {
    Map<String, String> headers = new HashMap<>();
    if (Headers == null) {
      return headers;
    }

    for (Map.Entry<String, Object> entry : Headers.toHashMap().entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
      headers.put(key, value == null ? "" : value.toString());
    }

    return headers;
  }

  public static okhttp3.Headers getHeaders() {
    Map<String, String> headers = parseReadableMapToMap();

    okhttp3.Request.Builder buildHeader = new Request.Builder()
      .header("Content-Type", "application/json")
      .header("X-Device-Key", Config.DeviceKeyIdentifier);

    if (IsDevelopment) {
      buildHeader.header("X-Testing-API-Header", FaceTecSDK.getTestingAPIHeader());
    }

    for (Map.Entry<String, String> entry : headers.entrySet()) {
      buildHeader = buildHeader.header(entry.getKey(), entry.getValue());
    }

    okhttp3.Request requestHeader = buildHeader
      .url(Config.BaseURL)
      .build();

    return requestHeader.headers();
  }

  public static void setDeviceKeyIdentifier(String deviceKeyIdentifier) {
    DeviceKeyIdentifier = deviceKeyIdentifier;
  }

  public static void setBaseUrl(String baseUrl) {
    BaseURL = baseUrl;
  }

  public static void setHeaders(ReadableMap headers) {
    Headers = headers;
  }

  public static void setIsDevelopment(Boolean isDevelopment) {
    IsDevelopment = isDevelopment;
  }

  public static boolean isEmpty() {
    if (DeviceKeyIdentifier == null || BaseURL == null) return true;
    return DeviceKeyIdentifier.isEmpty() || BaseURL.isEmpty();
  }

  public static FaceTecCustomization retrieveConfigurationCustomization(Theme theme) {
    Image image = theme.getImage();
    Rect buttonPosition = image.getButtonPosition();
    FaceTecCancelButtonCustomization.ButtonLocation buttonLocation = image.getButtonLocation();

    FaceTecCustomization defaultCustomization = new FaceTecCustomization();

    FaceTecFrameCustomization frameCustomization = defaultCustomization.getFrameCustomization();
    Frame frame = theme.getFrame();
    frameCustomization.cornerRadius = frame.getCornerRadius();
    frameCustomization.backgroundColor = frame.getBackgroundColor();
    frameCustomization.borderColor = frame.getBorderColor();
    frameCustomization.borderWidth = frame.getBorderWidth();
    frameCustomization.elevation = frame.getElevation();

    FaceTecOverlayCustomization overlayCustomization = defaultCustomization.getOverlayCustomization();
    overlayCustomization.brandingImage = image.getSource("branding", R.drawable.facetec_your_app_logo);
    overlayCustomization.showBrandingImage = image.getShowBranding();
    overlayCustomization.backgroundColor = theme.getColor("overlayBackgroundColor");

    FaceTecGuidanceCustomization guidanceCustomization = defaultCustomization.getGuidanceCustomization();
    Guidance guidance = theme.getGuidance();
    Button guidanceButton = guidance.getButton();
    Image guidanceImage = guidance.getImage();
    ReadyScreen guidanceReadyScreen = guidance.getReadyScreen();
    RetryScreen guidanceRetryScreen = guidance.getRetryScreen();
    guidanceCustomization.backgroundColors = guidance.getBackgroundColor();
    guidanceCustomization.foregroundColor = guidance.getForegroundColor();
    guidanceCustomization.buttonBorderWidth = guidanceButton.getBorderWidth();
    guidanceCustomization.buttonBorderColor = guidanceButton.getBorderColor();
    guidanceCustomization.buttonCornerRadius = guidanceButton.getCornerRadius();
    guidanceCustomization.buttonBackgroundNormalColor = guidanceButton.getBackgroundNormalColor();
    guidanceCustomization.buttonBackgroundDisabledColor = guidanceButton.getBackgroundDisabledColor();
    guidanceCustomization.buttonBackgroundHighlightColor = guidanceButton.getBackgroundHighlightColor();
    guidanceCustomization.buttonTextNormalColor = guidanceButton.getTextNormalColor();
    guidanceCustomization.buttonTextDisabledColor = guidanceButton.getTextDisabledColor();
    guidanceCustomization.buttonTextHighlightColor = guidanceButton.getTextHighlightColor();
    guidanceCustomization.cameraPermissionsScreenImage = guidanceImage.getSource("cameraPermission", R.drawable.facetec_camera);
    guidanceCustomization.readyScreenHeaderTextColor = guidanceReadyScreen.getHeaderTextColor();
    guidanceCustomization.readyScreenOvalFillColor = guidanceReadyScreen.getOvalFillColor();
    guidanceCustomization.readyScreenSubtextTextColor = guidanceReadyScreen.getSubtextColor();
    guidanceCustomization.retryScreenImageBorderColor = guidanceRetryScreen.getImageBorderColor();
    guidanceCustomization.retryScreenOvalStrokeColor = guidanceRetryScreen.getOvalStrokeColor();
    guidanceCustomization.retryScreenIdealImage = guidanceImage.getSource("ideal");
    guidanceCustomization.retryScreenSubtextTextColor = guidanceRetryScreen.getSubtextColor();
    guidanceCustomization.retryScreenImageBorderWidth = guidanceRetryScreen.getImageBorderWidth();
    guidanceCustomization.retryScreenImageCornerRadius = guidanceRetryScreen.getImageCornerRadius();

    FaceTecOvalCustomization ovalCustomization = defaultCustomization.getOvalCustomization();
    Oval oval = theme.getOval();
    ovalCustomization.strokeColor = oval.getStrokeColor();
    ovalCustomization.progressColor1 = oval.getFirstProgressColor();
    ovalCustomization.progressColor2 = oval.getSecondProgressColor();
    ovalCustomization.strokeWidth = oval.getStrokeWidth();
    ovalCustomization.progressRadialOffset = oval.getProgressRadialOffset();
    ovalCustomization.progressStrokeWidth = oval.getProgressStrokeWidth();

    FaceTecFeedbackCustomization feedbackCustomization = defaultCustomization.getFeedbackCustomization();
    Feedback feedback = theme.getFeedback();
    feedbackCustomization.backgroundColors = feedback.getBackgroundColor();
    feedbackCustomization.textColor = feedback.getTextColor();
    feedbackCustomization.cornerRadius = feedback.getCornerRadius();
    feedbackCustomization.elevation = feedback.getElevation();
    feedbackCustomization.enablePulsatingText = feedback.getEnablePulsatingText();

    FaceTecCancelButtonCustomization cancelButtonCustomization = defaultCustomization.getCancelButtonCustomization();
    cancelButtonCustomization.customImage = image.getSource("cancel", R.drawable.facetec_cancel);
    cancelButtonCustomization.hideForCameraPermissions = image.getHideForCameraPermissions();
    cancelButtonCustomization.setLocation(buttonLocation);
    cancelButtonCustomization.setCustomLocation(buttonPosition);

    FaceTecResultScreenCustomization resultScreenCustomization = defaultCustomization.getResultScreenCustomization();
    ResultScreen resultScreen = theme.getResultScreen();
    ResultAnimation resultScreenResultAnimation = resultScreen.getResultAnimation();
    SessionAbortAnimation resultScreenSessionAbortAnimation = resultScreen.getSessionAbortAnimation();
    resultScreenCustomization.backgroundColors = resultScreen.getBackgroundColor();
    resultScreenCustomization.foregroundColor = resultScreen.getForegroundColor();
    resultScreenCustomization.activityIndicatorColor = resultScreen.getActivityIndicatorColor();
    resultScreenCustomization.showUploadProgressBar = resultScreen.getShowUploadProgressBar();
    resultScreenCustomization.uploadProgressFillColor = resultScreen.getUploadProgressFillColor();
    resultScreenCustomization.uploadProgressTrackColor = resultScreen.getUploadProgressTrackColor();
    resultScreenCustomization.animationRelativeScale = resultScreen.getAnimationRelativeScale();
    resultScreenCustomization.customActivityIndicatorImage = resultScreen.getActivityIndicatorImage();
    resultScreenCustomization.customActivityIndicatorRotationInterval = resultScreen.getIndicatorRotationInterval();
    resultScreenCustomization.faceScanStillUploadingMessageDelayTime = resultScreen.getFaceScanStillUploadingMessageDelayTime();
    resultScreenCustomization.idScanStillUploadingMessageDelayTime = resultScreen.getIdScanStillUploadingMessageDelayTime();
    resultScreenCustomization.resultAnimationBackgroundColor = resultScreenResultAnimation.getBackgroundColor();
    resultScreenCustomization.resultAnimationForegroundColor = resultScreenResultAnimation.getForegroundColor();
    resultScreenCustomization.resultAnimationDisplayTime = resultScreenResultAnimation.getDisplayTime();
    resultScreenCustomization.resultAnimationIDScanSuccessForegroundColor = resultScreenResultAnimation.getIDScanSuccessForegroundColor();
    resultScreenCustomization.resultAnimationSuccessBackgroundImage = resultScreenResultAnimation.getSuccessBackgroundImage();
    resultScreenCustomization.resultAnimationUnsuccessBackgroundColor = resultScreenResultAnimation.getUnsuccessBackgroundColor();
    resultScreenCustomization.resultAnimationUnsuccessBackgroundImage = resultScreenResultAnimation.getUnsuccessBackgroundImage();
    resultScreenCustomization.resultAnimationUnsuccessForegroundColor = resultScreenResultAnimation.getUnsuccessForegroundColor();
    resultScreenCustomization.sessionAbortAnimationBackgroundColor = resultScreenSessionAbortAnimation.getBackgroundColor();
    resultScreenCustomization.sessionAbortAnimationBackgroundImage = resultScreenSessionAbortAnimation.getBackgroundImage();
    resultScreenCustomization.sessionAbortAnimationForegroundColor = resultScreenSessionAbortAnimation.getForegroundColor();

    defaultCustomization.securityWatermarkImage = FaceTecSecurityWatermarkImage.FACETEC;

    FaceTecIDScanCustomization idScanCustomization = defaultCustomization.getIdScanCustomization();
    IdScan idScan = theme.getIdScan();
    SelectionScreen idScanSelectionScreen = idScan.getSelectionScreen();
    ReviewScreen idScanReviewScreen = idScan.getReviewScreen();
    CaptureScreen idScanCaptureScreen = idScan.getCaptureScreen();
    Button idScanButton = idScan.getButton();
    idScanCustomization.selectionScreenBackgroundColors = idScanSelectionScreen.getBackgroundColor();
    idScanCustomization.selectionScreenForegroundColor = idScanSelectionScreen.getForegroundColor();
    idScanCustomization.reviewScreenBackgroundColors = idScanReviewScreen.getBackgroundColor();
    idScanCustomization.reviewScreenForegroundColor = idScanReviewScreen.getForegroundColor();
    idScanCustomization.reviewScreenTextBackgroundColor = idScanReviewScreen.getTextBackgroundColor();
    idScanCustomization.captureScreenForegroundColor = idScanCaptureScreen.getForegroundColor();
    idScanCustomization.captureScreenTextBackgroundColor = idScanCaptureScreen.getTextBackgroundColor();
    idScanCustomization.buttonBackgroundNormalColor = idScanButton.getBackgroundNormalColor();
    idScanCustomization.buttonBackgroundDisabledColor = idScanButton.getBackgroundDisabledColor();
    idScanCustomization.buttonBackgroundHighlightColor = idScanButton.getBackgroundHighlightColor();
    idScanCustomization.buttonTextNormalColor = idScanButton.getTextNormalColor();
    idScanCustomization.buttonTextDisabledColor = idScanButton.getTextDisabledColor();
    idScanCustomization.buttonTextHighlightColor = idScanButton.getTextHighlightColor();
    idScanCustomization.captureScreenBackgroundColor = idScanCaptureScreen.getBackgroundColor();
    idScanCustomization.captureFrameStrokeColor = idScanCaptureScreen.getFrameStrokeColor();

    return defaultCustomization;
  }

  public static FaceTecCustomization currentCustomization = null;
}

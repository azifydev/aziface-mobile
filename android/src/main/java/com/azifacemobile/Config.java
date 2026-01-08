package com.azifacemobile;

import com.azifacemobile.theme.Theme;
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
    FaceTecCancelButtonCustomization.ButtonLocation buttonLocation = theme.getImage().getButtonLocation();

    FaceTecCustomization defaultCustomization = new FaceTecCustomization();

    defaultCustomization.getFrameCustomization().cornerRadius = theme.getFrame().getCornerRadius();
    defaultCustomization.getFrameCustomization().backgroundColor = theme.getFrame().getBackgroundColor();
    defaultCustomization.getFrameCustomization().borderColor = theme.getFrame().getBorderColor();
    defaultCustomization.getFrameCustomization().borderWidth = theme.getFrame().getBorderWidth();
    defaultCustomization.getFrameCustomization().elevation = theme.getFrame().getElevation();

    defaultCustomization.getOverlayCustomization().brandingImage = theme.getImage().getImg("branding", R.drawable.facetec_your_app_logo);
    defaultCustomization.getOverlayCustomization().showBrandingImage = theme.getImage().getShowBranding();
    defaultCustomization.getOverlayCustomization().backgroundColor = theme.getColor("overlayBackgroundColor");

    defaultCustomization.getGuidanceCustomization().backgroundColors = theme.getGuidance().getBackgroundColor();
    defaultCustomization.getGuidanceCustomization().foregroundColor = theme.getGuidance().getForegroundColor();
    defaultCustomization.getGuidanceCustomization().buttonBackgroundNormalColor = theme.getGuidance()
      .getButton().getBackgroundNormalColor();
    defaultCustomization.getGuidanceCustomization().buttonBackgroundDisabledColor = theme.getGuidance()
      .getButton().getBackgroundDisabledColor();
    defaultCustomization.getGuidanceCustomization().buttonBackgroundHighlightColor = theme.getGuidance()
      .getButton().getBackgroundHighlightColor();
    defaultCustomization.getGuidanceCustomization().buttonTextNormalColor = theme.getGuidance().getButton()
      .getTextNormalColor();
    defaultCustomization.getGuidanceCustomization().buttonTextDisabledColor = theme.getGuidance().getButton()
      .getTextDisabledColor();
    defaultCustomization.getGuidanceCustomization().buttonTextHighlightColor = theme.getGuidance().getButton()
      .getTextHighlightColor();
    defaultCustomization.getGuidanceCustomization().retryScreenImageBorderColor = theme.getGuidance().getRetryScreen()
      .getImageBorderColor();
    defaultCustomization.getGuidanceCustomization().retryScreenOvalStrokeColor = theme.getGuidance().getRetryScreen()
      .getOvalStrokeColor();

    defaultCustomization.getOvalCustomization().strokeColor = theme.getOval().getStrokeColor();
    defaultCustomization.getOvalCustomization().progressColor1 = theme.getOval().getFirstProgressColor();
    defaultCustomization.getOvalCustomization().progressColor2 = theme.getOval().getSecondProgressColor();
    defaultCustomization.getOvalCustomization().strokeWidth = theme.getOval().getStrokeWidth();
    defaultCustomization.getOvalCustomization().progressRadialOffset = theme.getOval().getProgressRadialOffset();
    defaultCustomization.getOvalCustomization().progressStrokeWidth = theme.getOval().getProgressStrokeWidth();

    defaultCustomization.getFeedbackCustomization().backgroundColors = theme.getFeedback().getBackgroundColor();
    defaultCustomization.getFeedbackCustomization().textColor = theme.getFeedback().getTextColor();
    defaultCustomization.getFeedbackCustomization().cornerRadius = theme.getFeedback().getBorderRadius();
    defaultCustomization.getFeedbackCustomization().elevation = theme.getFeedback().getElevation();
    defaultCustomization.getFeedbackCustomization().enablePulsatingText = theme.getFeedback().getEnablePulsatingText();

    defaultCustomization.getCancelButtonCustomization().customImage = theme.getImage().getImg("cancel", R.drawable.facetec_cancel);
    defaultCustomization.getCancelButtonCustomization().hideForCameraPermissions = theme.getImage().getHideForCameraPermissions();
    defaultCustomization.getCancelButtonCustomization().setLocation(buttonLocation);

    defaultCustomization.getResultScreenCustomization().backgroundColors = theme.getResultScreen().getBackgroundColor();
    defaultCustomization.getResultScreenCustomization().foregroundColor = theme.getResultScreen().getForegroundColor();
    defaultCustomization.getResultScreenCustomization().activityIndicatorColor = theme.getResultScreen()
      .getActivityIndicatorColor();
    defaultCustomization.getResultScreenCustomization().resultAnimationBackgroundColor = theme.getResultScreen()
      .getResultAnimation().getBackgroundColor();
    defaultCustomization.getResultScreenCustomization().resultAnimationForegroundColor = theme.getResultScreen()
      .getResultAnimation().getForegroundColor();
    defaultCustomization.getResultScreenCustomization().uploadProgressFillColor = theme.getResultScreen()
      .getUploadProgressFillColor();

    defaultCustomization.securityWatermarkImage = FaceTecSecurityWatermarkImage.FACETEC;

    defaultCustomization.getIdScanCustomization().selectionScreenBackgroundColors = theme.getIdScan()
      .getSelectionScreen().getBackgroundColor();
    defaultCustomization.getIdScanCustomization().selectionScreenForegroundColor = theme.getIdScan()
      .getSelectionScreen().getForegroundColor();
    defaultCustomization.getIdScanCustomization().reviewScreenBackgroundColors = theme.getIdScan().getReviewScreen()
      .getBackgroundColor();
    defaultCustomization.getIdScanCustomization().reviewScreenForegroundColor = theme.getIdScan().getReviewScreen()
      .getForegroundColor();
    defaultCustomization.getIdScanCustomization().reviewScreenTextBackgroundColor = theme.getIdScan().getReviewScreen()
      .getTextBackgroundColor();
    defaultCustomization.getIdScanCustomization().captureScreenForegroundColor = theme.getIdScan().getCaptureScreen()
      .getForegroundColor();
    defaultCustomization.getIdScanCustomization().captureScreenTextBackgroundColor = theme.getIdScan().getCaptureScreen()
      .getTextBackgroundColor();
    defaultCustomization.getIdScanCustomization().buttonBackgroundNormalColor = theme.getIdScan().getButton()
      .getBackgroundNormalColor();
    defaultCustomization.getIdScanCustomization().buttonBackgroundDisabledColor = theme.getIdScan().getButton()
      .getBackgroundDisabledColor();
    defaultCustomization.getIdScanCustomization().buttonBackgroundHighlightColor = theme.getIdScan().getButton()
      .getBackgroundHighlightColor();
    defaultCustomization.getIdScanCustomization().buttonTextNormalColor = theme.getIdScan().getButton()
      .getTextNormalColor();
    defaultCustomization.getIdScanCustomization().buttonTextDisabledColor = theme.getIdScan().getButton()
      .getTextDisabledColor();
    defaultCustomization.getIdScanCustomization().buttonTextHighlightColor = theme.getIdScan().getButton()
      .getTextHighlightColor();
    defaultCustomization.getIdScanCustomization().captureScreenBackgroundColor = theme.getIdScan().getCaptureScreen()
      .getBackgroundColor();
    defaultCustomization.getIdScanCustomization().captureFrameStrokeColor = theme.getIdScan().getCaptureScreen()
      .getFrameStrokeColor();

    return defaultCustomization;
  }

  public static FaceTecCustomization currentCustomization = null;
}

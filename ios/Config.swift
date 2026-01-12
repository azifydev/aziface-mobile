import FaceTecSDK
import Foundation
import UIKit

public class Config {
  public static var IsDevelopment: Bool = false
  public static var DeviceKeyIdentifier: String!
  public static var BaseURL: String!
  public static var Headers: NSDictionary?

  public static func setDeviceKeyIdentifier(_ deviceKeyIdentifier: String) {
    Config.DeviceKeyIdentifier = deviceKeyIdentifier
  }

  public static func setBaseUrl(_ baseUrl: String) {
    Config.BaseURL = baseUrl
  }

  public static func setIsDevelopment(_ isDevelopment: Bool) {
    Config.IsDevelopment = isDevelopment
  }

  public static func setHeaders(_ headers: NSDictionary?) {
    Config.Headers = headers
  }

  public static func isEmpty() -> Bool {
    if Config.BaseURL == nil || Config.DeviceKeyIdentifier == nil {
      return true
    }
    return Config.BaseURL.isEmpty || Config.DeviceKeyIdentifier.isEmpty
  }

  public static func getRequest() -> URLRequest {
    let request = NSMutableURLRequest(url: NSURL(string: BaseURL)! as URL)
    request.httpMethod = "POST"
    request.addValue("application/json", forHTTPHeaderField: "Content-Type")
    request.addValue(DeviceKeyIdentifier, forHTTPHeaderField: "X-Device-Key")

    if IsDevelopment {
      request.addValue(
        FaceTec.sdk.getTestingAPIHeader(), forHTTPHeaderField: "X-Testing-API-Header")
    }

    if Headers != nil {
      for key in Headers!.allKeys {
        request.addValue(
          Headers![key] != nil ? Headers![key] as! String : "", forHTTPHeaderField: key as! String)
      }
    }

    return request as URLRequest
  }

  public static func retrieveConfigurationWizardCustomization() -> FaceTecCustomization {
    let theme = Theme()

    let defaultCustomization = FaceTecCustomization()

    defaultCustomization.frameCustomization.cornerRadius = theme.getFrame().getCornerRadius()
    defaultCustomization.frameCustomization.backgroundColor = theme.getFrame().getBackgroundColor()
    defaultCustomization.frameCustomization.borderColor = theme.getFrame().getBorderColor()
    defaultCustomization.frameCustomization.borderWidth = theme.getFrame().getBorderWidth()
    defaultCustomization.frameCustomization.shadow = theme.getFrame().getShadow()

    defaultCustomization.overlayCustomization.brandingImage = theme.getImage().getImg(
      "branding", defaultImage: "facetec_your_app_logo")
    defaultCustomization.overlayCustomization.showBrandingImage = theme.getImage().getShowBranding()
    defaultCustomization.overlayCustomization.backgroundColor = theme.getColor(
      "overlayBackgroundColor")

    defaultCustomization.guidanceCustomization.backgroundColors = theme.getGuidance()
      .getBackgroundColors()
    defaultCustomization.guidanceCustomization.foregroundColor = theme.getGuidance()
      .getForegroundColor()
    defaultCustomization.guidanceCustomization.buttonBackgroundNormalColor = theme.getGuidance()
      .getButton().getBackgroundNormalColor()
    defaultCustomization.guidanceCustomization.buttonBackgroundDisabledColor = theme.getGuidance()
      .getButton().getBackgroundDisabledColor()
    defaultCustomization.guidanceCustomization.buttonBackgroundHighlightColor = theme.getGuidance()
      .getButton().getBackgroundHighlightColor()
    defaultCustomization.guidanceCustomization.buttonTextNormalColor = theme.getGuidance()
      .getButton().getTextNormalColor()
    defaultCustomization.guidanceCustomization.buttonTextDisabledColor = theme.getGuidance()
      .getButton().getTextDisabledColor()
    defaultCustomization.guidanceCustomization.buttonTextHighlightColor = theme.getGuidance()
      .getButton().getTextHighlightColor()
    defaultCustomization.guidanceCustomization.retryScreenImageBorderColor = theme.getGuidance()
      .getRetryScreen().getImageBorderColor()
    defaultCustomization.guidanceCustomization.retryScreenOvalStrokeColor = theme.getGuidance()
      .getRetryScreen().getOvalStrokeColor()

    defaultCustomization.ovalCustomization.strokeColor = theme.getOval().getStrokeColor()
    defaultCustomization.ovalCustomization.progressColor1 = theme.getOval().getFirstProgressColor()
    defaultCustomization.ovalCustomization.progressColor2 = theme.getOval().getSecondProgressColor()
    defaultCustomization.ovalCustomization.strokeWidth = theme.getOval().getStrokeWidth()
    defaultCustomization.ovalCustomization.progressStrokeWidth = theme.getOval().getProgressStrokeWidth()
    defaultCustomization.ovalCustomization.progressRadialOffset = theme.getOval().getProgressRadialOffset()

    defaultCustomization.feedbackCustomization.backgroundColor = theme.getFeedback()
      .getGradientBackgroundColors()
    defaultCustomization.feedbackCustomization.textColor = theme.getFeedback().getTextColor()
    defaultCustomization.feedbackCustomization.cornerRadius = theme.getFeedback().getBorderRadius()
    defaultCustomization.feedbackCustomization.shadow = theme.getFeedback().getShadow()
    defaultCustomization.feedbackCustomization.enablePulsatingText = theme.getFeedback().getEnablePulsatingText()

    defaultCustomization.cancelButtonCustomization.customImage = theme.getImage().getImg(
      "cancel", defaultImage: "facetec_cancel")
    defaultCustomization.cancelButtonCustomization.hideForCameraPermissions = theme.getImage().getHideForCameraPermissions()
    defaultCustomization.cancelButtonCustomization.location = theme.getImage().getButtonLocation()

    defaultCustomization.resultScreenCustomization.backgroundColors = theme.getResultScreen()
      .getBackgroundColors()
    defaultCustomization.resultScreenCustomization.foregroundColor = theme.getResultScreen()
      .getForegroundColor()
    defaultCustomization.resultScreenCustomization.activityIndicatorColor = theme.getResultScreen()
      .getActivityIndicatorColor()
    defaultCustomization.resultScreenCustomization.resultAnimationBackgroundColor =
      theme.getResultScreen().getResultAnimation().getBackgroundColor()
    defaultCustomization.resultScreenCustomization.resultAnimationForegroundColor =
      theme.getResultScreen().getResultAnimation().getForegroundColor()
    defaultCustomization.resultScreenCustomization.uploadProgressFillColor = theme.getResultScreen()
      .getUploadProgressFillColor()

    let securityWatermarkImage: FaceTecSecurityWatermarkImage = .faceTec
    defaultCustomization.securityWatermarkImage = securityWatermarkImage

    defaultCustomization.idScanCustomization.selectionScreenBackgroundColors = theme.getIdScan()
      .getSelectionScreen().getBackgroundColors()
    defaultCustomization.idScanCustomization.selectionScreenForegroundColor = theme.getIdScan()
      .getSelectionScreen().getForegroundColor()
    defaultCustomization.idScanCustomization.reviewScreenForegroundColor = theme.getIdScan()
      .getReviewScreen().getForegroundColor()
    defaultCustomization.idScanCustomization.reviewScreenTextBackgroundColor = theme.getIdScan()
      .getReviewScreen().getTextBackgroundColor()
    defaultCustomization.idScanCustomization.captureScreenForegroundColor = theme.getIdScan()
      .getCaptureScreen().getForegroundColor()
    defaultCustomization.idScanCustomization.captureScreenTextBackgroundColor = theme.getIdScan()
      .getCaptureScreen().getTextBackgroundColor()
    defaultCustomization.idScanCustomization.buttonBackgroundNormalColor = theme.getIdScan()
      .getButton().getBackgroundNormalColor()
    defaultCustomization.idScanCustomization.buttonBackgroundDisabledColor = theme.getIdScan()
      .getButton().getBackgroundDisabledColor()
    defaultCustomization.idScanCustomization.buttonBackgroundHighlightColor = theme.getIdScan()
      .getButton().getBackgroundHighlightColor()
    defaultCustomization.idScanCustomization.buttonTextNormalColor = theme.getIdScan().getButton()
      .getTextNormalColor()
    defaultCustomization.idScanCustomization.buttonTextDisabledColor = theme.getIdScan().getButton()
      .getTextDisabledColor()
    defaultCustomization.idScanCustomization.buttonTextHighlightColor = theme.getIdScan()
      .getButton().getTextHighlightColor()
    defaultCustomization.idScanCustomization.captureScreenBackgroundColor = theme.getIdScan()
      .getCaptureScreen().getBackgroundColor()
    defaultCustomization.idScanCustomization.captureFrameStrokeColor = theme.getIdScan()
      .getCaptureScreen().getFrameStrokeColor()

    return defaultCustomization
  }

  public static func retrieveLowLightConfigurationWizardCustomization() -> FaceTecCustomization {
    return retrieveConfigurationWizardCustomization()
  }

  public static func retrieveDynamicDimmingConfigurationWizardCustomization()
    -> FaceTecCustomization
  {
    return retrieveConfigurationWizardCustomization()
  }

  static var currentCustomization: FaceTecCustomization = retrieveConfigurationWizardCustomization()
  static var currentLowLightCustomization: FaceTecCustomization =
    retrieveLowLightConfigurationWizardCustomization()
  static var currentDynamicDimmingCustomization: FaceTecCustomization =
    retrieveDynamicDimmingConfigurationWizardCustomization()
}

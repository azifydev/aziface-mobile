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

    let image = theme.getImage()

    let defaultCustomization = FaceTecCustomization()

    let frameCustomization = defaultCustomization.frameCustomization
    let frame = theme.getFrame()
    frameCustomization.cornerRadius = frame.getCornerRadius()
    frameCustomization.backgroundColor = frame.getBackgroundColor()
    frameCustomization.borderColor = frame.getBorderColor()
    frameCustomization.borderWidth = frame.getBorderWidth()
    frameCustomization.shadow = frame.getShadow()

    let overlayCustomization = defaultCustomization.overlayCustomization
    overlayCustomization.brandingImage = image.getSource(
      "branding", defaultImage: "facetec_your_app_logo")
    overlayCustomization.showBrandingImage = image.getShowBranding()
    overlayCustomization.backgroundColor = theme.getColor("overlayBackgroundColor")

    let guidanceCustomization = defaultCustomization.guidanceCustomization
    let guidance = theme.getGuidance()
    let guidanceButton = guidance.getButton()
    let guidanceImage = guidance.getImage()
    let guidanceReadyScreen = guidance.getReadyScreen()
    let guidanceRetryScreen = guidance.getRetryScreen()
    guidanceCustomization.backgroundColors = guidance.getBackgroundColors()
    guidanceCustomization.foregroundColor = guidance.getForegroundColor()
    guidanceCustomization.headerFont = guidance.getHeaderFont()
    guidanceCustomization.subtextFont = guidance.getSubtextFont()
    guidanceCustomization.buttonBorderColor = guidanceButton.getBorderColor()
    guidanceCustomization.buttonBorderWidth = guidanceButton.getBorderWidth()
    guidanceCustomization.buttonCornerRadius = guidanceButton.getCornerRadius()
    guidanceCustomization.buttonBackgroundNormalColor = guidanceButton.getBackgroundNormalColor()
    guidanceCustomization.buttonBackgroundDisabledColor =
      guidanceButton.getBackgroundDisabledColor()
    guidanceCustomization.buttonBackgroundHighlightColor =
      guidanceButton.getBackgroundHighlightColor()
    guidanceCustomization.buttonTextNormalColor = guidanceButton.getTextNormalColor()
    guidanceCustomization.buttonTextDisabledColor = guidanceButton.getTextDisabledColor()
    guidanceCustomization.buttonTextHighlightColor = guidanceButton.getTextHighlightColor()
    guidanceCustomization.buttonFont = guidanceButton.getFont()
    guidanceCustomization.cameraPermissionsScreenImage = guidanceImage.getSource(
      "cameraPermission", defaultImage: "facetec_camera")
    guidanceCustomization.readyScreenSubtextTextColor = guidanceReadyScreen.getSubtextColor()
    guidanceCustomization.readyScreenSubtextFont = guidanceReadyScreen.getSubtextFont()
    guidanceCustomization.readyScreenHeaderTextColor = guidanceReadyScreen.getHeaderTextColor()
    guidanceCustomization.readyScreenHeaderFont = guidanceReadyScreen.getHeaderFont()
    guidanceCustomization.readyScreenOvalFillColor = guidanceReadyScreen.getOvalFillColor()
    guidanceCustomization.readyScreenTextBackgroundColor = guidanceReadyScreen.getTextBackgroudColor()
    guidanceCustomization.readyScreenTextBackgroundCornerRadius = guidanceReadyScreen.getTextBackgroudColorCornerRadius()
    guidanceCustomization.retryScreenImageBorderColor = guidanceRetryScreen.getImageBorderColor()
    guidanceCustomization.retryScreenOvalStrokeColor = guidanceRetryScreen.getOvalStrokeColor()
    guidanceCustomization.retryScreenIdealImage = guidanceImage.getSource("ideal")
    guidanceCustomization.retryScreenHeaderTextColor = guidanceRetryScreen.getHeaderTextColor()
    guidanceCustomization.retryScreenHeaderFont = guidanceRetryScreen.getHeaderFont()
    guidanceCustomization.retryScreenSubtextTextColor = guidanceRetryScreen.getSubtextColor()
    guidanceCustomization.retryScreenSubtextFont = guidanceRetryScreen.getSubtextFont()
    guidanceCustomization.retryScreenImageBorderWidth = guidanceRetryScreen.getImageBorderWidth()
    guidanceCustomization.retryScreenImageCornerRadius = guidanceRetryScreen.getImageCornerRadius()

    let ovalCustomization = defaultCustomization.ovalCustomization
    let oval = theme.getOval()
    ovalCustomization.strokeColor = oval.getStrokeColor()
    ovalCustomization.progressColor1 = oval.getFirstProgressColor()
    ovalCustomization.progressColor2 = oval.getSecondProgressColor()
    ovalCustomization.strokeWidth = oval.getStrokeWidth()
    ovalCustomization.progressStrokeWidth = oval.getProgressStrokeWidth()
    ovalCustomization.progressRadialOffset = oval.getProgressRadialOffset()

    let feedbackCustomization = defaultCustomization.feedbackCustomization
    let feedback = theme.getFeedback()
    feedbackCustomization.backgroundColor = feedback.getGradientBackgroundColors()
    feedbackCustomization.textColor = feedback.getTextColor()
    feedbackCustomization.textFont = feedback.getFont()
    feedbackCustomization.cornerRadius = feedback.getCornerRadius()
    feedbackCustomization.shadow = feedback.getShadow()
    feedbackCustomization.enablePulsatingText = feedback.getEnablePulsatingText()

    defaultCustomization.cancelButtonCustomization.customImage = image.getSource(
      "cancel", defaultImage: "facetec_cancel")
    defaultCustomization.cancelButtonCustomization.hideForCameraPermissions =
      image.getHideForCameraPermissions()
    defaultCustomization.cancelButtonCustomization.location = image.getButtonLocation()
    defaultCustomization.cancelButtonCustomization.customLocation = image.getButtonPosition()

    let resultScreenCustomization = defaultCustomization.resultScreenCustomization
    let resultScreen = theme.getResultScreen()
    let resultScreenResultAnimation = resultScreen.getResultAnimation()
    let resultScreenSessionAbortAnimation = resultScreen.getSessionAbortAnimation()
    resultScreenCustomization.backgroundColors = resultScreen.getBackgroundColors()
    resultScreenCustomization.foregroundColor = resultScreen.getForegroundColor()
    resultScreenCustomization.messageFont = resultScreen.getFont()
    resultScreenCustomization.activityIndicatorColor = resultScreen.getActivityIndicatorColor()
    resultScreenCustomization.uploadProgressFillColor = resultScreen.getUploadProgressFillColor()
    resultScreenCustomization.uploadProgressTrackColor = resultScreen.getUploadProgressTrackColor()
    resultScreenCustomization.animationRelativeScale = resultScreen.getAnimationRelativeScale()
    resultScreenCustomization.showUploadProgressBar = resultScreen.getShowUploadProgressBar()
    resultScreenCustomization.customActivityIndicatorImage =
      resultScreen.getActivityIndicatorImage()
    resultScreenCustomization.customActivityIndicatorRotationInterval =
      resultScreen.getIndicatorRotationInterval()
    resultScreenCustomization.faceScanStillUploadingMessageDelayTime =
      resultScreen.getFaceScanStillUploadingMessageDelayTime()
    resultScreenCustomization.idScanStillUploadingMessageDelayTime =
      resultScreen.getIdScanStillUploadingMessageDelayTime()
    resultScreenCustomization.resultAnimationBackgroundColor =
      resultScreenResultAnimation.getBackgroundColor()
    resultScreenCustomization.resultAnimationForegroundColor =
      resultScreenResultAnimation.getForegroundColor()
    resultScreenCustomization.resultAnimationDisplayTime =
      resultScreenResultAnimation.getDisplayTime()
    resultScreenCustomization.resultAnimationIDScanSuccessForegroundColor =
      resultScreenResultAnimation.getIDScanSuccessForegroundColor()
    resultScreenCustomization.resultAnimationSuccessBackgroundImage =
      resultScreenResultAnimation.getSuccessBackgroundImage()
    resultScreenCustomization.resultAnimationUnsuccessBackgroundColor =
      resultScreenResultAnimation.getUnsuccessBackgroundColor()
    resultScreenCustomization.resultAnimationUnsuccessBackgroundImage =
      resultScreenResultAnimation.getUnsuccessBackgroundImage()
    resultScreenCustomization.resultAnimationUnsuccessForegroundColor =
      resultScreenResultAnimation.getUnsuccessForegroundColor()
    resultScreenCustomization.sessionAbortAnimationBackgroundColor =
      resultScreenSessionAbortAnimation.getBackgroundColor()
    resultScreenCustomization.sessionAbortAnimationBackgroundImage =
      resultScreenSessionAbortAnimation.getBackgroundImage()
    resultScreenCustomization.sessionAbortAnimationForegroundColor =
      resultScreenSessionAbortAnimation.getForegroundColor()

    defaultCustomization.securityWatermarkImage = .faceTec

    let idScanCustomization = defaultCustomization.idScanCustomization
    let idScan = theme.getIdScan()
    let idScanSelectionScreen = idScan.getSelectionScreen()
    let idScanReviewScreen = idScan.getReviewScreen()
    let idScanCaptureScreen = idScan.getCaptureScreen()
    let idScanButton = idScan.getButton()
    idScanCustomization.headerFont = idScan.getHeaderFont()
    idScanCustomization.subtextFont = idScan.getSubtextFont()
    idScanCustomization.selectionScreenBackgroundColors =
      idScanSelectionScreen.getBackgroundColors()
    idScanCustomization.selectionScreenForegroundColor = idScanSelectionScreen.getForegroundColor()
    idScanCustomization.reviewScreenForegroundColor = idScanReviewScreen.getForegroundColor()
    idScanCustomization.reviewScreenBackgroundColors = idScanReviewScreen.getBackgroundColors()
    idScanCustomization.reviewScreenTextBackgroundColor =
      idScanReviewScreen.getTextBackgroundColor()
    idScanCustomization.buttonBackgroundNormalColor = idScanButton.getBackgroundNormalColor()
    idScanCustomization.buttonBackgroundDisabledColor = idScanButton.getBackgroundDisabledColor()
    idScanCustomization.buttonBackgroundHighlightColor = idScanButton.getBackgroundHighlightColor()
    idScanCustomization.buttonTextNormalColor = idScanButton.getTextNormalColor()
    idScanCustomization.buttonTextDisabledColor = idScanButton.getTextDisabledColor()
    idScanCustomization.buttonTextHighlightColor = idScanButton.getTextHighlightColor()
    idScanCustomization.buttonFont = idScanButton.getFont()
    idScanCustomization.captureScreenForegroundColor = idScanCaptureScreen.getForegroundColor()
    idScanCustomization.captureScreenTextBackgroundColor = idScanCaptureScreen.getTextBackgroundColor()
    idScanCustomization.captureScreenBackgroundColor = idScanCaptureScreen.getBackgroundColor()
    idScanCustomization.captureFrameStrokeColor = idScanCaptureScreen.getFrameStrokeColor()
    idScanCustomization.captureScreenFocusMessageFont = idScanCaptureScreen.getFont()

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

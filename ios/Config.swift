//
//  Config.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 13/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import FaceTecSDK
import Foundation
import UIKit

public class Config {
  public static var DeviceKeyIdentifier: String!
  public static var BaseURL: String!
  public static var ProcessId: String!
  public static var PublicFaceScanEncryptionKey: String!
  public static var ProductionKeyText: String!
  public static var ProcessorPathURL: [String: String]! = [:]
  public static var Headers: NSDictionary?
  
  public static func setDevice(_ device: String) {
    Config.DeviceKeyIdentifier = device
  }
  
  public static func setUrl(_ url: String) {
    Config.BaseURL = url
  }
  
  public static func setProcessId(_ id: String) {
    Config.ProcessId = id
  }
  
  public static func setKey(_ key: String) {
    Config.PublicFaceScanEncryptionKey = key
  }
  
  public static func setProductionKeyText(_ keyText: String) {
    Config.ProductionKeyText = keyText
  }
  
  public static func setProcessorPathURL(_ key: String, pathUrl: String) {
    Config.ProcessorPathURL[key] = pathUrl
  }
  
  public static func setHeaders(_ headers: NSDictionary?) {
    Config.Headers = headers
  }
  
  public static func hasConfig() -> Bool {
    return Config.BaseURL != nil && Config.DeviceKeyIdentifier != nil
    && Config.ProductionKeyText != nil && Config.PublicFaceScanEncryptionKey != nil
  }
  
  public static func makeRequest(url: String, httpMethod: String) -> URLRequest {
    let endpoint = BaseURL + url
    let request = NSMutableURLRequest(url: NSURL(string: endpoint)! as URL)
    request.httpMethod = httpMethod
    request.addValue(DeviceKeyIdentifier, forHTTPHeaderField: "X-Device-Key")
    request.addValue(
      FaceTec.sdk.createFaceTecAPIUserAgentString(""), forHTTPHeaderField: "X-User-Agent")
    
    if httpMethod != "GET" {
      request.addValue("application/json", forHTTPHeaderField: "Content-Type")
    }
    
    if Headers != nil {
      for key in Headers!.allKeys {
        request.addValue(
          Headers![key] != nil ? Headers![key] as! String : "", forHTTPHeaderField: key as! String)
      }
    }
    
    return request as URLRequest
  }
  
  static func initialize(_ isDeveloper: Bool, completion: @escaping (Bool) -> Void) {
    if isDeveloper {
      FaceTec.sdk.initializeInDevelopmentMode(
        deviceKeyIdentifier: DeviceKeyIdentifier,
        faceScanEncryptionKey: PublicFaceScanEncryptionKey,
        completion: { initializationSuccessful in
          completion(initializationSuccessful)
        })
    } else {
      FaceTec.sdk.initializeInProductionMode(
        productionKeyText: ProductionKeyText, deviceKeyIdentifier: DeviceKeyIdentifier,
        faceScanEncryptionKey: PublicFaceScanEncryptionKey,
        completion: { initializationSuccessful in
          completion(initializationSuccessful)
        })
    }
  }
  
  public static func retrieveConfigurationWizardCustomization() -> FaceTecCustomization {
    let theme: Theme = AzifaceMobileSdk.theme
    
    let securityWatermarkImage: FaceTecSecurityWatermarkImage = .faceTec
    
    let defaultCustomization = FaceTecCustomization()
    
    defaultCustomization.frameCustomization.cornerRadius = theme.getFrame().getCornerRadius()
    defaultCustomization.frameCustomization.backgroundColor = theme.getFrame().getBackgroundColor()
    defaultCustomization.frameCustomization.borderColor = theme.getFrame().getBorderColor()
    
    defaultCustomization.overlayCustomization.brandingImage = theme.getImage(
      "logo", defaultImage: "facetec_your_app_logo")
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
    
    defaultCustomization.feedbackCustomization.backgroundColor = theme.getFeedback()
      .getGradientBackgroundColor()
    defaultCustomization.feedbackCustomization.textColor = theme.getFeedback().getTextColor()
    
    defaultCustomization.cancelButtonCustomization.customImage = theme.getImage(
      "cancel", defaultImage: "facetec_cancel")
    defaultCustomization.cancelButtonCustomization.location = theme.getGeneral().getButtonLocation(
      "cancelButtonLocation")
    
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
    defaultCustomization.idScanCustomization.captureScreenBackgroundColor = theme.getIdScan()
      .getCaptureScreen().getBackgroundColor()
    defaultCustomization.idScanCustomization.captureFrameStrokeColor = theme.getIdScan()
      .getCaptureScreen().getFrameStrokeColor()
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

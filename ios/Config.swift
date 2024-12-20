//
//  Config.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 13/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import UIKit
import Foundation
import FaceTecSDK

import UIKit
import Foundation
import FaceTecSDK

public class Config {
    public static var DeviceKeyIdentifier: String!
    public static var BaseURL: String!
    public static var ProcessId: String!
    public static var PublicFaceScanEncryptionKey: String!
    public static var ProductionKeyText: String!
    public static var Headers: NSDictionary?
    public static var Theme: NSDictionary?
    private static let AziThemeUtils: ThemeUtils! = ThemeUtils();

    public static func setDevice(_ device: String) {
        Config.DeviceKeyIdentifier = device;
    }

    public static func setUrl(_ url: String) {
        Config.BaseURL = url;
    }

    public static func setProcessId(_ id: String) {
        Config.ProcessId = id;
    }

    public static func setKey(_ key: String) {
        Config.PublicFaceScanEncryptionKey = key;
    }

    public static func setProductionKeyText(_ keyText: String) {
        Config.ProductionKeyText = keyText;
    }

    public static func setHeaders(_ headers: NSDictionary?) {
        Config.Headers = headers;
    }

    public static func setTheme(_ theme: NSDictionary?) {
        Config.Theme = theme;
    }

    public static func hasConfig() -> Bool {
        return Config.BaseURL != nil && Config.DeviceKeyIdentifier != nil && Config.ProductionKeyText != nil && Config.PublicFaceScanEncryptionKey != nil
    }

    public static func makeRequest(url: String, httpMethod: String) -> URLRequest {
        let endpoint = BaseURL + url;
        let request = NSMutableURLRequest(url: NSURL(string: endpoint)! as URL)
        request.httpMethod = httpMethod;
        request.addValue(DeviceKeyIdentifier, forHTTPHeaderField: "X-Device-Key");
        request.addValue(FaceTec.sdk.createFaceTecAPIUserAgentString(""), forHTTPHeaderField: "X-User-Agent");

        if (httpMethod != "GET") {
            request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        }

        if (Headers != nil) {
            for key in Headers!.allKeys {
                request.addValue(Headers![key] != nil ? Headers![key] as! String : "", forHTTPHeaderField: key as! String);
            }
        }

        return request as URLRequest;
    }

    static func initialize(_ isDeveloperMode: Bool, completion: @escaping (Bool)->()) {
        if (isDeveloperMode) {
            FaceTec.sdk.initializeInDevelopmentMode(deviceKeyIdentifier: DeviceKeyIdentifier, faceScanEncryptionKey: PublicFaceScanEncryptionKey, completion: { initializationSuccessful in
                completion(initializationSuccessful)
            })
        } else {
            FaceTec.sdk.initializeInProductionMode(productionKeyText: ProductionKeyText, deviceKeyIdentifier: DeviceKeyIdentifier, faceScanEncryptionKey: PublicFaceScanEncryptionKey, completion: { initializationSuccessful in
                completion(initializationSuccessful)
            })
        }
    }

    public static func retrieveConfigurationWizardCustomization() -> FaceTecCustomization {
        let securityWatermarkImage: FaceTecSecurityWatermarkImage = .faceTec;

        let defaultCustomization = FaceTecCustomization();

        defaultCustomization.frameCustomization.cornerRadius = AziThemeUtils.handleBorderRadius("frameCornerRadius");
        defaultCustomization.frameCustomization.backgroundColor = AziThemeUtils.handleColor("frameBackgroundColor");
        defaultCustomization.frameCustomization.borderColor = AziThemeUtils.handleColor("frameBorderColor");

        defaultCustomization.overlayCustomization.brandingImage = AziThemeUtils.handleImage("logoImage", defaultImage: "facetec_your_app_logo");
        defaultCustomization.overlayCustomization.backgroundColor = AziThemeUtils.handleColor("overlayBackgroundColor");

        defaultCustomization.guidanceCustomization.backgroundColors = AziThemeUtils.handleSimpleLinearGradient("guidanceBackgroundColorsIos");
        defaultCustomization.guidanceCustomization.foregroundColor = AziThemeUtils.handleColor("guidanceForegroundColor", defaultColor: "#272937");
        defaultCustomization.guidanceCustomization.buttonBackgroundNormalColor = AziThemeUtils.handleColor("guidanceButtonBackgroundNormalColor", defaultColor: "#026ff4");
        defaultCustomization.guidanceCustomization.buttonBackgroundDisabledColor = AziThemeUtils.handleColor("guidanceButtonBackgroundDisabledColor", defaultColor: "#b3d4fc");
        defaultCustomization.guidanceCustomization.buttonBackgroundHighlightColor = AziThemeUtils.handleColor("guidanceButtonBackgroundHighlightColor", defaultColor: "#0264dc");
        defaultCustomization.guidanceCustomization.buttonTextNormalColor = AziThemeUtils.handleColor("guidanceButtonTextNormalColor");
        defaultCustomization.guidanceCustomization.buttonTextDisabledColor = AziThemeUtils.handleColor("guidanceButtonTextDisabledColor");
        defaultCustomization.guidanceCustomization.buttonTextHighlightColor = AziThemeUtils.handleColor("guidanceButtonTextHighlightColor");
        defaultCustomization.guidanceCustomization.retryScreenImageBorderColor = AziThemeUtils.handleColor("guidanceRetryScreenImageBorderColor");
        defaultCustomization.guidanceCustomization.retryScreenOvalStrokeColor = AziThemeUtils.handleColor("guidanceRetryScreenOvalStrokeColor");

        defaultCustomization.ovalCustomization.strokeColor = AziThemeUtils.handleColor("ovalStrokeColor", defaultColor: "#026ff4");
        defaultCustomization.ovalCustomization.progressColor1 = AziThemeUtils.handleColor("ovalFirstProgressColor", defaultColor: "#0264dc");
        defaultCustomization.ovalCustomization.progressColor2 = AziThemeUtils.handleColor("ovalSecondProgressColor", defaultColor: "#0264dc");

        defaultCustomization.feedbackCustomization.backgroundColor = AziThemeUtils.handleCAGradient("feedbackBackgroundColorsIos");
        defaultCustomization.feedbackCustomization.textColor = AziThemeUtils.handleColor("feedbackTextColor");

        defaultCustomization.cancelButtonCustomization.customImage = AziThemeUtils.handleImage("cancelImage", defaultImage: "facetec_cancel");
        defaultCustomization.cancelButtonCustomization.location = AziThemeUtils.handleButtonLocation("cancelButtonLocation");

        defaultCustomization.resultScreenCustomization.backgroundColors = AziThemeUtils.handleSimpleLinearGradient("resultScreenBackgroundColorsIos");
        defaultCustomization.resultScreenCustomization.foregroundColor = AziThemeUtils.handleColor("resultScreenForegroundColor", defaultColor: "#272937");
        defaultCustomization.resultScreenCustomization.activityIndicatorColor = AziThemeUtils.handleColor("resultScreenActivityIndicatorColor", defaultColor: "#026ff4");
        defaultCustomization.resultScreenCustomization.resultAnimationBackgroundColor = AziThemeUtils.handleColor("resultScreenResultAnimationBackgroundColor", defaultColor: "#026ff4");
        defaultCustomization.resultScreenCustomization.resultAnimationForegroundColor = AziThemeUtils.handleColor("resultScreenResultAnimationForegroundColor");
        defaultCustomization.resultScreenCustomization.uploadProgressFillColor = AziThemeUtils.handleColor("resultScreenUploadProgressFillColor", defaultColor: "#026ff4");

        defaultCustomization.securityWatermarkImage = securityWatermarkImage;

        defaultCustomization.idScanCustomization.selectionScreenBackgroundColors = AziThemeUtils.handleSimpleLinearGradient("idScanSelectionScreenBackgroundColorsIos");
        defaultCustomization.idScanCustomization.selectionScreenForegroundColor = AziThemeUtils.handleColor("idScanSelectionScreenForegroundColor", defaultColor: "#272937");
        defaultCustomization.idScanCustomization.reviewScreenForegroundColor = AziThemeUtils.handleColor("idScanReviewScreenForegroundColor");
        defaultCustomization.idScanCustomization.reviewScreenTextBackgroundColor = AziThemeUtils.handleColor("idScanReviewScreenTextBackgroundColor", defaultColor: "#026ff4");
        defaultCustomization.idScanCustomization.captureScreenForegroundColor = AziThemeUtils.handleColor("idScanCaptureScreenForegroundColor");
        defaultCustomization.idScanCustomization.captureScreenTextBackgroundColor = AziThemeUtils.handleColor("idScanCaptureScreenTextBackgroundColor", defaultColor: "#026ff4");
        defaultCustomization.idScanCustomization.buttonBackgroundNormalColor = AziThemeUtils.handleColor("idScanButtonBackgroundNormalColor", defaultColor: "#026ff4");
        defaultCustomization.idScanCustomization.buttonBackgroundDisabledColor = AziThemeUtils.handleColor("idScanButtonBackgroundDisabledColor", defaultColor: "#b3d4fc");
        defaultCustomization.idScanCustomization.buttonBackgroundHighlightColor = AziThemeUtils.handleColor("idScanButtonBackgroundHighlightColor", defaultColor: "#0264dc");
        defaultCustomization.idScanCustomization.buttonTextNormalColor = AziThemeUtils.handleColor("idScanButtonTextNormalColor");
        defaultCustomization.idScanCustomization.buttonTextDisabledColor = AziThemeUtils.handleColor("idScanButtonTextDisabledColor");
        defaultCustomization.idScanCustomization.buttonTextHighlightColor = AziThemeUtils.handleColor("idScanButtonTextHighlightColor");
        defaultCustomization.idScanCustomization.captureScreenBackgroundColor = AziThemeUtils.handleColor("idScanCaptureScreenBackgroundColor");
        defaultCustomization.idScanCustomization.captureFrameStrokeColor = AziThemeUtils.handleColor("idScanCaptureFrameStrokeColor");

        return defaultCustomization;
    }

    public static func retrieveLowLightConfigurationWizardCustomization() -> FaceTecCustomization {
        return retrieveConfigurationWizardCustomization();
    }


    public static func retrieveDynamicDimmingConfigurationWizardCustomization() -> FaceTecCustomization {
        return retrieveConfigurationWizardCustomization();
    }

    static var currentCustomization: FaceTecCustomization = retrieveConfigurationWizardCustomization();
    static var currentLowLightCustomization: FaceTecCustomization = retrieveLowLightConfigurationWizardCustomization();
    static var currentDynamicDimmingCustomization: FaceTecCustomization = retrieveDynamicDimmingConfigurationWizardCustomization();
}

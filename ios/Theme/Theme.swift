//
//  Theme.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

import FaceTecSDK
import Foundation
import UIKit

public class Theme {
  private let color: Color
  private let image: Image
  private let message: Message
  public static var Style: NSDictionary?;
  
  init() {
    self.color = Color()
    self.image = Image()
    self.message = Message()
  }
  
  @available(iOS 8.2, *)
  public class func setAppTheme(_ style: NSDictionary?) {
    Theme.Style = style
    
    Config.currentCustomization = getCustomizationForTheme()
    Config.currentLowLightCustomization = getLowLightCustomizationForTheme()
    Config.currentDynamicDimmingCustomization = getDynamicDimmingCustomizationForTheme()
    
    FaceTec.sdk.setCustomization(Config.currentCustomization)
    FaceTec.sdk.setLowLightCustomization(Config.currentLowLightCustomization)
    FaceTec.sdk.setDynamicDimmingCustomization(Config.currentDynamicDimmingCustomization)
  }
  
  @available(iOS 8.2, *)
  class func getCustomizationForTheme() -> FaceTecCustomization {
    var currentCustomization = FaceTecCustomization()
    currentCustomization = Config.retrieveConfigurationWizardCustomization()
    
    return currentCustomization
  }
  
  @available(iOS 8.2, *)
  class func getLowLightCustomizationForTheme() -> FaceTecCustomization {
    var currentLowLightCustomization: FaceTecCustomization = getCustomizationForTheme()
    currentLowLightCustomization = Config.retrieveLowLightConfigurationWizardCustomization()
    
    return currentLowLightCustomization
  }
  
  @available(iOS 8.2, *)
  class func getDynamicDimmingCustomizationForTheme() -> FaceTecCustomization {
    var currentDynamicDimmingCustomization: FaceTecCustomization = getCustomizationForTheme()
    currentDynamicDimmingCustomization =
    Config.retrieveDynamicDimmingConfigurationWizardCustomization()
    
    return currentDynamicDimmingCustomization
  }
  
  public func getAuthenticateMessage(_ key: String, defaultMessage: String) -> String {
      return self.message.getMessage("authenticateMessage", key: key, defaultMessage: defaultMessage)
  }

  public func getEnrollmentMessage(_ key: String, defaultMessage: String) -> String {
      return self.message.getMessage("enrollMessage", key: key, defaultMessage: defaultMessage)
  }

  public func getPhotoIDScanMessage(_ key: String, defaultMessage: String) -> String {
      return self.message.getMessage("scanMessage", key: key, defaultMessage: defaultMessage)
  }

  public func getPhotoIDScanMessage(_ parent: String, key: String, defaultMessage: String) -> String {
      return self.message.getMessage("scanMessage", parent: parent, key: key, defaultMessage: defaultMessage)
  }

  public func getPhotoIDMatchMessage(_ key: String, defaultMessage: String) -> String {
      return self.message.getMessage("matchMessage", key: key, defaultMessage: defaultMessage)
  }

  public func getPhotoIDMatchMessage(_ parent: String, key: String, defaultMessage: String) -> String {
      return self.message.getMessage("matchMessage", parent: parent, key: key, defaultMessage: defaultMessage)
  }
  
  public func getColor(_ key: String) -> UIColor {
    return self.color.getColor(key)
  }
  
  public func getImage(_ key: String, defaultImage: String) -> UIImage? {
    return self.image.getImage(key, defaultImage: defaultImage)
  }
}

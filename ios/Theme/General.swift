//
//  FaceTec.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

import Foundation
import FaceTecSDK

public class General {
  private let style: Style
  
  init() {
    self.style = Style()
  }
  
  public func getBorderRadius(_ theme: NSDictionary?, key: String) -> Int32 {
    let defaultBorderRadius: Int32 = 10
    if !self.style.exists(theme, key: key) {
      return defaultBorderRadius
    }
    
    let borderRadius = (theme?[key] as? Int32) ?? defaultBorderRadius
    return borderRadius < 0 ? defaultBorderRadius : borderRadius
  }
  
  public func getButtonLocation(_ key: String) -> FaceTecCancelButtonLocation {
    let defaultButtonLocation = FaceTecCancelButtonLocation.topRight
    if !self.style.exists(key) {
      return defaultButtonLocation
    }
    
    let buttonLocation = (Theme.Style?[key] as? String) ?? ""
    if buttonLocation.isEmpty {
      return defaultButtonLocation
    }
    
    switch buttonLocation {
    case "TOP_RIGHT":
      do {
        return FaceTecCancelButtonLocation.topRight
      }
    case "TOP_LEFT":
      do {
        return FaceTecCancelButtonLocation.topLeft
      }
    case "DISABLED":
      do {
        return FaceTecCancelButtonLocation.disabled
      }
    default:
      do {
        return defaultButtonLocation
      }
    }
  }
  
  public func getStatusBarStyle(_ key: String) -> UIStatusBarStyle {
    var defaultStatusBarStyle = UIStatusBarStyle.default
    if #available(iOS 13, *) {
      defaultStatusBarStyle = UIStatusBarStyle.darkContent
    }
    
    if !self.style.exists(key) {
      return defaultStatusBarStyle
    }
    
    let statusBarColor = (Theme.Style?[key] as? String) ?? ""
    if statusBarColor.isEmpty {
      return defaultStatusBarStyle
    }
    
    if #available(iOS 13, *) {
      switch statusBarColor {
      case "DARK_CONTENT":
        do {
          return defaultStatusBarStyle
        }
      case "LIGHT_CONTENT":
        do {
          return UIStatusBarStyle.lightContent
        }
      case "DEFAULT":
        do {
          return UIStatusBarStyle.default
        }
      default:
        do {
          return defaultStatusBarStyle
        }
      }
    }
    
    return defaultStatusBarStyle
  }
}

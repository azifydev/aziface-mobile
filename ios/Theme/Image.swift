import Foundation
import FaceTecSDK

public class Image {
  private let style: Style
  private let target: NSDictionary?

  init() {
    self.style = Style()
    
    self.target = self.style.getTarget("image")
  }
  
  private func getBool(key: String) -> Bool {
    if !self.style.exists(self.target, key: key) {
      return true
    }

    return self.target?[key] as! Bool
  }

  public func getImg(_ key: String, defaultImage: String) -> UIImage? {
    if !self.style.exists(self.target, key: key) {
      return UIImage(named: defaultImage)
    }

    let imageName = self.target?[key] as? String ?? ""
    if imageName.isEmpty {
      return UIImage(named: defaultImage)
    }

    return UIImage(named: imageName)
  }

  public func getShowBranding() -> Bool {
    return self.getBool(key: "isShowBranding")
  }
  
  public func getHideForCameraPermissions() -> Bool {
    return self.getBool(key: "isHideForCameraPermissions")
  }
  
  public func getButtonLocation() -> FaceTecCancelButtonLocation {
    let key = "cancelLocation"
    let defaultLocation = FaceTecCancelButtonLocation.topRight
    
    if !self.style.exists(self.target, key: key) {
      return defaultLocation
    }

    let buttonLocation = (self.target?[key] as? String) ?? ""
    if buttonLocation.isEmpty {
      return defaultLocation
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
        return defaultLocation
      }
    }
  }
}

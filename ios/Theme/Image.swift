import Foundation
import FaceTecSDK

public class Image: ImageStyle {
  private static let KEY: String = "image"
  private let style: Style
  private let target: NSDictionary?
  private var isPosition: Bool

  init() {
    self.style = Style()
    
    self.target = self.style.getTarget(Image.KEY)
    self.isPosition = false
    
    super.init(key: Image.KEY)
  }
  
  override init(target: NSDictionary?) {
    self.style = Style()
    
    self.target = target
    self.isPosition = false
    
    super.init(target: target)
  }
  
  override init(target: NSDictionary?, key: String) {
    self.style = Style()
    
    self.target = target
    self.isPosition = false
    
    super.init(target: target, key: key)
  }
  
  private func getBool(key: String) -> Bool {
    if !self.style.exists(self.target, key: key) {
      return true
    }

    return self.target?[key] as! Bool
  }

  public func getShowBranding() -> Bool {
    return self.getBool(key: "isShowBranding")
  }
  
  public func getHideForCameraPermissions() -> Bool {
    return self.getBool(key: "isHideForCameraPermissions")
  }
  
  public func getButtonPosition() -> CGRect {
    let cancelPosition = self.target?["cancelPosition"] as? NSDictionary
    let ios = cancelPosition?["ios"] as? NSDictionary
    
    if (cancelPosition == nil || ios == nil) {
      self.isPosition = false
      
      return .zero
    }
    
    let x = (ios?["x"] ?? 0) as! Int
    let y = (ios?["y"] ?? 0) as! Int
    let width = (ios?["width"] ?? 0.0) as! Double
    let height = (ios?["height"] ?? 0.0) as! Double
    
    let point = CGPoint(x: x, y: y)
    let size = CGSize(width: width, height: height)
    
    self.isPosition = true
    
    return CGRect(origin: point, size: size)
  }
  
  public func getButtonLocation() -> FaceTecCancelButtonLocation {
    let defaultLocation = FaceTecCancelButtonLocation.topRight

    let cancelLocation = self.isPosition ? "CUSTOM" : (self.target?["cancelLocation"] as? String) ?? ""

    switch cancelLocation {
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
    case "CUSTOM":
      do {
        return FaceTecCancelButtonLocation.custom
      }
    default:
      do {
        return defaultLocation
      }
    }
  }
}

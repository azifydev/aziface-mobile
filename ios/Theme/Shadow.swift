import Foundation
import FaceTecSDK

public class Shadow {
  private let style: Style
  private let target: NSDictionary?
  private let color: Color

  init(target: NSDictionary?) {
    self.style = Style()
    self.color = Color()
    
    self.target = self.style.getTarget(target, key: "shadow")
  }
  
  private func getColor() -> UIColor {
    return self.color.getColor(self.target, key: "color", defaultColor: "#000000")
  }
  
  private func getOpacity() -> Float {
    let key = "opacity"
    let defaultOpacity: Float = 1
    
    if !self.style.exists(self.target, key: key) {
      return defaultOpacity
    }

    let opacity = (self.target?[key] as? Float) ?? defaultOpacity
    return max(0, min(opacity, defaultOpacity))
  }
  
  private func getRadius() -> Float {
    let key = "radius"
    let defaultRadius: Float = 10
    
    if !self.style.exists(self.target, key: key) {
      return defaultRadius
    }

    let radius = (self.target?[key] as? Float) ?? defaultRadius
    return max(0, min(radius, 100))
  }
  
  private func getOffset() -> CGSize {
    let key = "offset"
    let defaultOffset: CGSize = .zero
    
    if !self.style.exists(self.target, key: key) {
      return defaultOffset
    }
    
    let offset = (self.target?[key] as? [String: Double]) ?? [:]
    let width = offset["width"] ?? 0
    let height = offset["height"] ?? 0
    
    let size = CGSize(width: width, height: height)
    return size
  }
  
  private func getInsets() -> UIEdgeInsets {
    let key = "insets"
    let defaultInsets: UIEdgeInsets = .zero
    
    if !self.style.exists(self.target, key: key) {
      return defaultInsets
    }
    
    let insets = (self.target?[key] as? [String: CGFloat]) ?? [:]
    let top = insets["top"] ?? 0
    let left = insets["left"] ?? 0
    let bottom = insets["bottom"] ?? 0
    let right = insets["right"] ?? 0
    
    let edgeInsets = UIEdgeInsets(top: top, left: left, bottom: bottom, right: right)
    return edgeInsets
  }
  
  public func getShadow() -> FaceTecShadow? {
    if (self.target == nil) {
      return nil
    }
    
    let color = self.getColor()
    let opacity = self.getOpacity()
    let radius = self.getRadius()
    let offset = self.getOffset()
    let insets = self.getInsets()
    
    let shadow = FaceTecShadow(color: color, opacity: opacity, radius: radius, offset: offset, insets: insets)
    return shadow
  }
}

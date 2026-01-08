import FaceTecSDK

public class Frame: CommonViewStyle {
  private static let KEY: String = "frame"
  private let style: Style
  private let target: NSDictionary?
  private let general: General
  private let shadow: Shadow
  private let color: Color

  init() {
    self.style = Style()
    self.general = General()
    self.color = Color()
    
    self.target = self.style.getTarget(Frame.KEY)
    self.shadow = Shadow(theme: self.target)

    super.init(key: Frame.KEY)
  }

  public func getCornerRadius() -> Int32 {
    return self.general.getBorderRadius(self.target, key: "cornerRadius")
  }

  public func getBorderColor() -> UIColor {
    return self.color.getColor(self.target, key: "borderColor")
  }
  
  public func getShadow() -> FaceTecShadow? {
    return self.shadow.getShadow()
  }
  
  public func getBorderWidth() -> Int32 {
    let key = "borderWidth"
    let defaultBorderWidth: Int32 = -1
    
    if !self.style.exists(self.target, key: key) {
      return defaultBorderWidth
    }

    let borderWidth = (self.target?[key] as? Int32) ?? defaultBorderWidth
    return borderWidth < 0 ? defaultBorderWidth : borderWidth
  }
}

import FaceTecSDK

public class ScrollIndicator: ViewStyle {
  private static let KEY: String = "scrollIndicator"
  private let style: Style
  private let target: NSDictionary?
  private let color: Color
  private let font: Font
  private let shadow: Shadow

  init(target: NSDictionary?) {
    self.style = Style()

    self.target = self.style.getTarget(target, key: ScrollIndicator.KEY)
    self.color = Color()
    self.font = Font()
    self.shadow = Shadow(target: self.target)

    super.init(key: ScrollIndicator.KEY)
  }

  override public func getCornerRadius() -> Int32 {
    return super.getCornerRadius(-1)
  }

  public func getBackgroundNormalColor() -> UIColor {
    return self.color.getColor(self.target, key: "backgroundNormalColor", defaultColor: "#026ff4")
  }

  public func getBackgroundHighlightColor() -> UIColor {
    return self.color.getColor(self.target, key: "backgroundHighlightColor", defaultColor: "#0264dc")
  }

  public func getForegroundNormalColor() -> UIColor {
    return self.color.getColor(self.target, key: "foregroundNormalColor", defaultColor: "#ffffff")
  }

  public func getForegroundHighlightColor() -> UIColor {
    return self.color.getColor(self.target, key: "foregroundHighlightColor", defaultColor: "#ffffff")
  }

  public func getEnableScrollIndicator() -> Bool {
    let key = "showsScrollIndicator"
    if !self.style.exists(self.target, key: key) {
      return true
    }

    return self.target?[key] as? Bool ?? true
  }

  public func getEnableScrollIndicatorTextAnimation() -> Bool {
    let key = "showsScrollTextAnimation"
    if !self.style.exists(self.target, key: key) {
      return true
    }

    return self.target?[key] as? Bool ?? true
  }

  public func getShowScrollIndicatorImage() -> Bool {
    let key = "showsScrollImage"
    if !self.style.exists(self.target, key: key) {
      return true
    }

    return self.target?[key] as? Bool ?? true
  }

  public func getFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "font")
  }

  public func getShadow() -> FaceTecShadow {
    return self.shadow.getShadow()
  }
}

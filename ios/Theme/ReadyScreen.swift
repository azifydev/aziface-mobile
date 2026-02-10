public class ReadyScreen {
  private static let TRANSPARENT: String = "#00000000"
  private let style: Style
  private let target: NSDictionary?
  private let font: Font
  private let color: Color

  init(target: NSDictionary?) {
    self.style = Style()
    self.font = Font()
    self.color = Color()

    self.target = self.style.getTarget(target, key: "readyScreen")
  }

  public func getHeaderTextColor() -> UIColor {
    return self.color.getColor(self.target, key: "headerTextColor", defaultColor: "#000000")
  }
  
  public func getHeaderFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "headerFont")
  }

  public func getSubtextColor() -> UIColor {
    return self.color.getColor(self.target, key: "subtextColor", defaultColor: "#000000")
  }
  
  public func getSubtextFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "subtextFont")
  }

  public func getOvalFillColor() -> UIColor {
    return self.color.getColor(self.target, key: "ovalFillColor", defaultColor: ReadyScreen.TRANSPARENT)
  }
  
  public func getTextBackgroudColor() -> UIColor {
    return self.color.getColor(self.target, key: "textBackgroundColor", defaultColor: ReadyScreen.TRANSPARENT)
  }
  
  public func getTextBackgroudColorCornerRadius() -> Int32 {
    let key = "textBackgroundColorCornerRadius"
    let defaultCornerRadius: Int32 = -1
    if !self.style.exists(self.target, key: key) {
        return defaultCornerRadius
    }
      
    return (target?[key] as? Int32) ?? defaultCornerRadius
  }
}

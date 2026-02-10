import Foundation

public class Button: ViewStyle {
  private static let KEY: String = "guidance"
  private let target: NSDictionary?
  private let font: Font
  private let color: Color

  init(target: NSDictionary?) {
    self.target = Style().getTarget(target, key: Button.KEY)
    self.font = Font()
    self.color = Color()
    
    super.init(key: Button.KEY)
  }
  
  override public func getCornerRadius() -> Int32 {
    return super.getCornerRadius(-1)
  }
  
  public func getFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "font")
  }

  public func getBackgroundNormalColor() -> UIColor {
    return self.color.getColor(self.target, key: "backgroundNormalColor", defaultColor: "#026ff4")
  }

  public func getBackgroundDisabledColor() -> UIColor {
    return self.color.getColor(self.target, key: "backgroundDisabledColor", defaultColor: "#b3d4fc")
  }

  public func getBackgroundHighlightColor() -> UIColor {
    return self.color.getColor(self.target, key: "backgroundHighlightColor", defaultColor: "#0264dc")
  }

  public func getTextNormalColor() -> UIColor {
    return self.color.getColor(self.target, key: "textNormalColor")
  }

  public func getTextDisabledColor() -> UIColor {
    return self.color.getColor(self.target, key: "textDisabledColor")
  }

  public func getTextHighlightColor() -> UIColor {
    return self.color.getColor(self.target, key: "textHighlightColor")
  }
}

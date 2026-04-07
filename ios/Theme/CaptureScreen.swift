public class CaptureScreen: ViewStyle {
  private static let KEY: String = "captureScreen"
  private let target: NSDictionary?
  private let font: Font
  private let color: Color

  init(target: NSDictionary?) {
    self.target = Style().getTarget(target, key: CaptureScreen.KEY)
    self.font = Font()
    self.color = Color()

    super.init(target: target, key: CaptureScreen.KEY)
  }
  
  override public func getCornerRadius() -> Int32 {
    return super.getCornerRadius(-1)
  }

  public func getFocusTextColor() -> UIColor {
    return self.color.getColor(self.target, key: "focusTextColor")
  }
  
  public func getFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "font")
  }
}

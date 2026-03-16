public class OrientationScreen: ViewStyle {
  private static let KEY: String = "orientationScreen"
  private let target: NSDictionary?
  private let font: Font
  private let image: Image

  init() {
    self.target = Style().getTarget(OrientationScreen.KEY)
    self.font = Font()
    self.image = Image(target: self.target)

    super.init(key: OrientationScreen.KEY)
  }

  override func getForegroundColor() -> UIColor {
    return super.getForegroundColor(defaultColor: "#026ff4")
  }

  public func getFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "font")
  }

  public func getIconImage() -> UIImage? {
    return self.image.getSource("iconImage")
  }
}

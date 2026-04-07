public class InputField: ViewStyle {
  private static let KEY: String = "inputField"
  private let style: Style
  private let target: NSDictionary?
  private let color: Color
  private let font: Font

  init(target: NSDictionary?) {
    self.style = Style()

    self.target = self.style.getTarget(target, key: InputField.KEY)
    self.color = Color()
    self.font = Font()

    super.init(key: InputField.KEY)
  }

//  TODO: Add it in the next versions because it currently is wrong on iOS
//  override public func getBackgroundColor() -> UIColor {
//    return super.getBackgroundColor(defaultColor: Color.TRANSPARENT)
//  }

  override public func getBorderColor() -> UIColor {
    return super.getBorderColor("#0264dc")
  }

  override public func getCornerRadius() -> Int32 {
    return super.getCornerRadius(4)
  }

  public func getTextColor() -> UIColor {
    return self.color.getColor(self.target, key: "textColor", defaultColor: "#272937")
  }

  public func getPlaceholderTextColor() -> UIColor {
    return self.color.getColor(self.target, key: "placeholderTextColor", defaultColor: Color.TRANSPARENT)
  }

  public func getShowInputFieldBottomBorderOnly() -> Bool {
    let key = "showsBorderBottomOnly"
    if !self.style.exists(self.target, key: key) {
      return false
    }

    return self.target?[key] as? Bool ?? false
  }

  public func getFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "font")
  }
}

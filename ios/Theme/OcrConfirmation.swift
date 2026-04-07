public class OcrConfirmation: ViewStyle {
  private static let KEY: String = "ocrConfirmation"
  private let style: Style
  private let target: NSDictionary?
  private let color: Color
  private let font: Font
  private let button: Button
  private let scrollIndicator: ScrollIndicator
  private let inputField: InputField

  init() {
    self.style = Style()

    self.target = self.style.getTarget(OcrConfirmation.KEY)
    self.color = Color()
    self.font = Font()
    self.button = Button(target: self.target)
    self.scrollIndicator = ScrollIndicator(target: self.target)
    self.inputField = InputField(target: self.target)

    super.init(key: OcrConfirmation.KEY)
  }

//  TODO: Add it in the next versions because it currently is wrong on iOS
//  override public func getBackgroundColor() -> UIColor {
//    return super.getBackgroundColor(defaultColor: Color.TRANSPARENT)
//  }

  public func getEnableFixedConfirmButton() -> Bool {
    let key = "isFixedConfirmButton"
    if !self.style.exists(self.target, key: key) {
      return false
    }

    return self.target?[key] as? Bool ?? false
  }

  public func getLineColor() -> UIColor {
    return self.color.getColor(self.target, key: "lineColor", defaultColor: "#026ff4")
  }

  public func getLineWidth() -> Int32 {
    let defaultLineWidth: Int32 = -1
    let key = "lineWidth"
    if !self.style.exists(self.target, key: key) {
      return defaultLineWidth
    }

    return self.target?[key] as? Int32 ?? defaultLineWidth
  }

  public func getHeaderTextColor() -> UIColor {
    return self.color.getColor(self.target, key: "headerTextColor", defaultColor: "#026ff4")
  }

  public func getHeaderFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "headerFont")
  }

  public func getSectionTextColor() -> UIColor {
    return self.color.getColor(self.target, key: "sectionTextColor", defaultColor: "#272937")
  }

  public func getSectionFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "sectionFont")
  }

  public func getLabelColor() -> UIColor {
    return self.color.getColor(self.target, key: "labelColor", defaultColor: "#272937")
  }

  public func getLabelFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "labelFont")
  }

  public func getButton() -> Button {
    return self.button
  }

  public func getScrollIndicator() -> ScrollIndicator {
    return self.scrollIndicator
  }

  public func getInputField() -> InputField {
    return self.inputField
  }
}

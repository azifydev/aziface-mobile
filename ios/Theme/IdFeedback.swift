public class IdFeedback: ViewStyle {
  private static let KEY: String = "idFeedback"
  private let style: Style
  private let target: NSDictionary?
  private let color: Color
  private let image: Image

  init(target: NSDictionary?) {
    self.style = Style()

    self.target = self.style.getTarget(target, key: IdFeedback.KEY)
    self.color = Color()
    self.image = Image(target: self.target)

    super.init(target: self.target, key: IdFeedback.KEY)
  }

  override func getForegroundColor() -> UIColor {
    return super.getForegroundColor(defaultColor: "#272937")
  }

  public func getDisableIDFeedbackScreen() -> Bool {
    let key = "isDisableIDFeedbackScreen"
    if !self.style.exists(self.target, key: key) {
      return false
    }

    return self.target?[key] as! Bool
  }

  public func getDisplayTime() -> Double {
    let defaultDisplayTime: Double = 2.0
    let key = "displayTime"

    if !self.style.exists(self.target, key: key) {
      return defaultDisplayTime
    }

    let displayTime = self.target?[key] as! Double
    return displayTime < 0 ? defaultDisplayTime : displayTime
  }

  public func getFlipIDBackImage() -> UIImage? {
    return self.image.getSource("flipIDBackImage")
  }

  public func getFlipIDFrontImage() -> UIImage? {
    return self.image.getSource("flipIDFrontImage")
  }
}

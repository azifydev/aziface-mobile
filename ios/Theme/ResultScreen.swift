public class ResultScreen: ViewStyle {
  private static let KEY: String = "resultScreen"
  private let style: Style
  private let target: NSDictionary?
  private let font: Font
  private let color: Color
  private let resultAnimation: ResultAnimation
  private let sessionAbortAnimation: SessionAbortAnimation

  init() {
    self.style = Style()
    self.font = Font()
    self.color = Color()

    self.target = self.style.getTarget(ResultScreen.KEY)

    self.resultAnimation = ResultAnimation(target: self.target)
    self.sessionAbortAnimation = SessionAbortAnimation(target: self.target)

    super.init(key: ResultScreen.KEY)
  }

  override public func getForegroundColor() -> UIColor {
    return self.getForegroundColor(defaultColor: "#272937")
  }

  public func getActivityIndicatorColor() -> UIColor {
    return self.color.getColor(self.target, key: "activityIndicatorColor", defaultColor: "#026ff4")
  }

  public func getUploadProgressFillColor() -> UIColor {
    return self.color.getColor(self.target, key: "uploadProgressFillColor", defaultColor: "#026ff4")
  }

  public func getUploadProgressTrackColor() -> UIColor {
    return self.color.getColor(
      self.target, key: "uploadProgressTrackColor", defaultColor: "#b3d4fc")
  }

  public func getShowUploadProgressBar() -> Bool {
    let key = "isShowUploadProgressBar"

    if !self.style.exists(self.target, key: key) {
      return true
    }

    return self.target?[key] as! Bool
  }

  public func getFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "font")
  }

  public func getResultAnimation() -> ResultAnimation {
    return self.resultAnimation
  }

  public func getSessionAbortAnimation() -> SessionAbortAnimation {
    return self.sessionAbortAnimation
  }
}

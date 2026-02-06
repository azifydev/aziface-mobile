public class CaptureScreen: ViewStyle {
  private static let KEY: String = "captureScreen"
  private let theme: NSDictionary?
  private let color: Color

  init(target: NSDictionary?) {
    self.theme = Style().getTarget(target, key: CaptureScreen.KEY)
    self.color = Color()

    super.init(target: target, key: CaptureScreen.KEY)
  }

  public func getFrameStrokeColor() -> UIColor {
    return self.color.getColor(self.theme, key: "frameStrokeColor")
  }
}

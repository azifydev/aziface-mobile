public class Guidance: ViewStyle {
  private static let KEY: String = "guidance"
  private let target: NSDictionary?
  private let font: Font
  private let button: Button
  private let image: Image
  private let readyScreen: ReadyScreen
  private let retryScreen: RetryScreen

  init() {
    self.target = Style().getTarget(Guidance.KEY)

    self.font = Font()
    self.button = Button(target: self.target)
    self.image = Image(target: self.target, key: "images")
    self.readyScreen = ReadyScreen(target: self.target)
    self.retryScreen = RetryScreen(target: self.target)

    super.init(key: Guidance.KEY)
  }

  override public func getForegroundColor() -> UIColor {
    return self.getForegroundColor(defaultColor: "#272937")
  }

  public func getButton() -> Button {
    return self.button
  }

  public func getImage() -> Image {
    return self.image
  }

  public func getReadyScreen() -> ReadyScreen {
    return self.readyScreen
  }

  public func getRetryScreen() -> RetryScreen {
    return self.retryScreen
  }
}

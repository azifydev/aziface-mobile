public class Guidance: ViewStyle {
  private static let KEY: String = "guidance"
  private let button: Button
  private let image: Image
  private let readyScreen: ReadyScreen
  private let retryScreen: RetryScreen

  init() {
    let target = Style().getTarget(Guidance.KEY)

    self.button = Button(target: target)
    self.image = Image(target: target, key: "images")
    self.readyScreen = ReadyScreen(target: target)
    self.retryScreen = RetryScreen(target: target)

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

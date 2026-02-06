public class RetryScreen {
  private static let KEY: String = "retryScreen"
  private let style: Style
  private let target: NSDictionary?
  private let color: Color

  init(target: NSDictionary?) {
    self.style = Style()
    self.target = self.style.getTarget(target, key: RetryScreen.KEY)
    self.color = Color()
  }

  private func getInt32(key: String) -> Int32 {
    let defaultValue: Int32 = -1
    if !self.style.exists(self.target, key: key) {
      return defaultValue
    }

    return (self.target?[key] ?? defaultValue) as! Int32
  }

  public func getImageBorderWidth() -> Int32 {
    return self.getInt32(key: "imageBorderWidth")
  }

  public func getImageBorderColor() -> UIColor {
    return self.color.getColor(self.target, key: "imageBorderColor")
  }

  public func getImageCornerRadius() -> Int32 {
    return self.getInt32(key: "imageCornerRadius")
  }

  public func getSubtextColor() -> UIColor {
    return self.color.getColor(self.target, key: "subtextColor", defaultColor: "#000000")
  }

  public func getOvalStrokeColor() -> UIColor {
    return self.color.getColor(self.target, key: "ovalStrokeColor")
  }
}

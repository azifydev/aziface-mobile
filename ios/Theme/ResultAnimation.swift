public class ResultAnimation: CommonViewStyle {
  init(theme: NSDictionary?) {
    super.init(theme: theme, key: "resultAnimation")
  }

  override public func getBackgroundColor() -> UIColor {
    return self.getBackgroundColor(defaultColor: "#026ff4")
  }
}

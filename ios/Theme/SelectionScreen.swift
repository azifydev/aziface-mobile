public class SelectionScreen: CommonViewStyle {

  init(theme: NSDictionary?) {
    super.init(theme: theme, key: "selectionScreen")
  }

  override public func getForegroundColor() -> UIColor {
    return self.getForegroundColor(defaultColor: "#272937")
  }
}

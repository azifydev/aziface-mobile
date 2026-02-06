public class SelectionScreen: ViewStyle {

  init(target: NSDictionary?) {
    super.init(target: target, key: "selectionScreen")
  }

  override public func getForegroundColor() -> UIColor {
    return self.getForegroundColor(defaultColor: "#272937")
  }
}

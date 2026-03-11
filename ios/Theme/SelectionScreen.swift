public class SelectionScreen: ViewStyle {
  private let style: Style
  private let target: NSDictionary?
  private let image: Image

  init(target: NSDictionary?) {
    self.style = Style()
    
    self.target = target
    self.image = Image(target: target)
    
    super.init(target: target, key: "selectionScreen")
  }

  override public func getForegroundColor() -> UIColor {
    return super.getForegroundColor(defaultColor: "#272937")
  }
  
  public func getDocumentImage() -> UIImage? {
    return self.image.getSource("documentImage")
  }
  
  public func getShowDocumentImage() -> Bool {
    let key = "isShowDocumentImage"
    if !self.style.exists(self.target, key: key) {
      return true
    }

    return self.target?[key] as! Bool
  }
}

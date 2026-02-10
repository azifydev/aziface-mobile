public class SessionAbortAnimation: ViewStyle {
  private static var KEY: String = "sessionAbortAnimation"
  private var image: Image

  init(target: NSDictionary?) {
    let currentTarget = Style().getTarget(target, key: SessionAbortAnimation.KEY)
    
    self.image = Image(target: currentTarget)
    
    super.init(target: target, key: SessionAbortAnimation.KEY)
  }

  override public func getBackgroundColor() -> UIColor {
    return self.getBackgroundColor(defaultColor: "#CC0044")
  }
  
  public func getBackgroundImage() -> UIImage? {
    return self.image.getSource("image")
  }
}

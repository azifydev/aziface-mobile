public class ResultAnimation: ViewStyle {
  private static let KEY = "resultAnimation"
  private let style: Style
  private let target: NSDictionary?
  private let image: Image
  private let color: Color
  
  init(target: NSDictionary?) {
    self.style = Style()
    self.color = Color()
    
    self.target = self.style.getTarget(target, key: ResultAnimation.KEY)
    
    self.image = Image(target: self.target)
    
    super.init(target: target, key: ResultAnimation.KEY)
  }

  override public func getBackgroundColor() -> UIColor {
    return self.getBackgroundColor(defaultColor: "#026ff4")
  }
  
  public func getDisplayTime() -> Double {
    let defaultDisplayTime: Double = 2.5
    let key = "displayTime"
    
    if !self.style.exists(self.target, key: key) {
      return defaultDisplayTime
    }
    
    let displayTime = self.target?[key] as! Double
    return displayTime < 0 ? defaultDisplayTime : displayTime
  }
  
  public func getIDScanSuccessForegroundColor() -> UIColor {
    return self.color.getColor(self.target, key: "idScanSuccessForegroundColor", defaultColor: "026ff4")
  }
  
  public func getUnsuccessBackgroundColor() -> UIColor {
    return self.color.getColor(self.target, key: "unsuccessBackgroundColor", defaultColor: "CC0044")
  }
  
  public func getUnsuccessForegroundColor() -> UIColor {
    return self.color.getColor(self.target, key: "unsuccessForegroundColor")
  }
  
  public func getSuccessBackgroundImage() -> UIImage? {
    return self.image.getSource("successImage")
  }
  
  public func getUnsuccessBackgroundImage() -> UIImage? {
    return self.image.getSource("unsuccessImage")
  }
}

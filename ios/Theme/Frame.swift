import FaceTecSDK

public class Frame: ViewStyle {
  private static let KEY: String = "frame"
  private let style: Style
  private let target: NSDictionary?
  private let shadow: Shadow
  private let color: Color

  init() {
    self.style = Style()
    self.color = Color()
    
    self.target = self.style.getTarget(Frame.KEY)
    self.shadow = Shadow(target: self.target)

    super.init(key: Frame.KEY)
  }
  
  public func getShadow() -> FaceTecShadow? {
    return self.shadow.getShadow()
  }
}

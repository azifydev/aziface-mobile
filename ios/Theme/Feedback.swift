import FaceTecSDK

public class Feedback: ViewStyle {
  private static let KEY: String = "feedback"
  private let style: Style
  private let target: NSDictionary?
  private let shadow: Shadow
  private let font: Font
  private let color: Color

  init() {
    self.style = Style()
    self.font = Font()
    self.color = Color()
    
    self.target = self.style.getTarget(Feedback.KEY)
    self.shadow = Shadow(target: self.target)

    super.init(key: Feedback.KEY)
  }
  
  override public func getCornerRadius() -> Int32 {
    return super.getCornerRadius(-1)
  }

  public func getTextColor() -> UIColor {
    return self.color.getColor(self.target, key: "textColor")
  }
  
  public func getShadow() -> FaceTecShadow? {
    return self.shadow.getShadow()
  }
  
  public func getEnablePulsatingText() -> Bool {
    let key = "isEnablePulsatingText"
    
    if !self.style.exists(self.target, key: key) {
      return true
    }

    return self.target?[key] as! Bool
  }
  
  public func getFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "font")
  }
}

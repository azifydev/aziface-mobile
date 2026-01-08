import FaceTecSDK

public class Feedback: CommonViewStyle {
  private static let KEY: String = "feedback"
  private let style: Style
  private let target: NSDictionary?
  private let general: General
  private let shadow: Shadow
  private let color: Color

  init() {
    self.style = Style()
    self.general = General()
    self.color = Color()
    
    self.target = self.style.getTarget(Feedback.KEY)
    self.shadow = Shadow(theme: self.target)

    super.init(key: Feedback.KEY)
  }

  public func getTextColor() -> UIColor {
    return self.color.getColor(self.target, key: "textColor")
  }
  
  public func getBorderRadius() -> Int32 {
    return self.general.getBorderRadius(self.target, key: "cornerRadius", defaultBorderRadius: -1)
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
}

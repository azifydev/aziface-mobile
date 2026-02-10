public class ViewStyle {
  private let style: Style
  private let target: NSDictionary?
  private let color: Color
  private let linearGradient: LinearGradient

  init(key: String) {
    self.style = Style()
    self.target = self.style.getTarget(key)
    
    self.color = Color()
    self.linearGradient = LinearGradient()
  }

  init(target: NSDictionary?, key: String) {
    self.style = Style()
    self.target = self.style.getTarget(target, key: key)
    
    self.color = Color()
    self.linearGradient = LinearGradient()
  }
  
  private func getInt32(_ key: String, defaultValue: Int32) -> Int32 {
    if (!self.style.exists(self.target, key: key)) {
      return defaultValue
    }
    
    return (target?[key] as? Int32) ?? defaultValue
  }

  func getBackgroundColor() -> UIColor {
    return self.color.getColor(self.target, key: "backgroundColor")
  }

  func getBackgroundColor(defaultColor: String) -> UIColor {
    return self.color.getColor(self.target, key: "backgroundColor", defaultColor: defaultColor)
  }

  func getBackgroundColors() -> [UIColor] {
    return self.linearGradient.getLinearGradient(self.target, key: "backgroundColor")
  }

  func getGradientBackgroundColors() -> CAGradientLayer {
    return self.linearGradient.getGradientLayer(self.target, key: "backgroundColors")
  }

  func getForegroundColor() -> UIColor {
    return self.color.getColor(self.target, key: "foregroundColor")
  }

  func getForegroundColor(defaultColor: String) -> UIColor {
    return self.color.getColor(self.target, key: "foregroundColor", defaultColor: defaultColor)
  }

  func getTextBackgroundColor() -> UIColor {
    return self.color.getColor(self.target, key: "textBackgroundColor", defaultColor: "#026ff4")
  }
  
  func getBorderColor() -> UIColor {
    return self.color.getColor(self.target, key: "borderColor");
  }
  
  func getBorderColor(_ defaultBorderColor: String) -> UIColor {
    return self.color.getColor(self.target, key: "borderColor", defaultColor: defaultBorderColor);
  }
  
  func getBorderWidth() -> Int32 {
    let defaultBorderWidth: Int32 = -1
    let borderWidth: Int32 = self.getInt32("borderWidth", defaultValue: defaultBorderWidth)
    return borderWidth < 0 ? defaultBorderWidth : borderWidth
  }
  
  public func getCornerRadius() -> Int32 {
    return self.getCornerRadius(20)
  }
  
  public func getCornerRadius(_ defaultCornerRadius: Int32) -> Int32 {
    let cornerRadius = self.getInt32("cornerRadius", defaultValue: defaultCornerRadius)
    return cornerRadius < 0 ? defaultCornerRadius : cornerRadius
  }
}

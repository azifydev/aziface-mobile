//
//  CommonSwift.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

public class CommonStyle {
  private let theme: NSDictionary?
  private let color: Color
  private let linearGradient: LinearGradient
  
  init(key: String) {
    self.theme = Style().getTarget(key)
    self.color = Color()
    self.linearGradient = LinearGradient()
  }
  
  init(theme: NSDictionary?, key: String) {
    self.theme = Style().getTarget(theme, key: key)
    self.color = Color()
    self.linearGradient = LinearGradient()
  }
  
  func getBackgroundColor() -> UIColor {
    return self.color.getColor(self.theme, key: "backgroundColor")
  }

  func getBackgroundColor(defaultColor: String) -> UIColor {
    return self.color.getColor(self.theme, key: "backgroundColor", defaultColor: defaultColor)
  }
  
  func getBackgroundColors() -> [UIColor] {
    return self.linearGradient.getLinearGradient(self.theme, key: "backgroundColors")
  }
  
  func getGradientBackgroundColors() -> CAGradientLayer {
    return self.linearGradient.getGradientLayer(self.theme, key: "backgroundColors")
  }

  func getForegroundColor() -> UIColor {
      return self.color.getColor(self.theme, key: "foregroundColor")
  }

  func getForegroundColor(defaultColor: String) -> UIColor {
      return self.color.getColor(self.theme, key: "foregroundColor", defaultColor: defaultColor)
  }

  func getTextBackgroundColor() -> UIColor {
    return self.color.getColor(self.theme, key: "textBackgroundColor", defaultColor: "#026ff4")
  }
}

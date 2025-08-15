//
//  Oval.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

public class Oval {
  private let theme: NSDictionary?
  private let color: Color
  
  init() {
    self.theme = Style().getTarget("oval")
    self.color = Color()
  }
  
  public func getStrokeColor() -> UIColor {
    return self.color.getColor(self.theme, key: "strokeColor", defaultColor: "#026ff4")
  }
  
  public func getFirstProgressColor() -> UIColor {
    return self.color.getColor(self.theme, key: "firstProgressColor", defaultColor: "#0264dc")
  }
  
  public func getSecondProgressColor() -> UIColor {
    return self.color.getColor(self.theme, key: "secondProgressColor", defaultColor: "#0264dc")
  }
}

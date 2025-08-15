//
//  Frame.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

public class Frame: CommonViewStyle {
  private static let KEY: String = "frame"
  private let theme: NSDictionary?
  private let color: Color
  private let general: General
  
  init() {
    self.theme = Style().getTarget(Frame.KEY)
    self.color = Color()
    self.general = General()
    
    super.init(key: Frame.KEY)
  }
  
  public func getCornerRadius() -> Int32 {
    return self.general.getBorderRadius(self.theme, key: "cornerRadius");
  }
  
  public func getBorderColor() -> UIColor {
    return self.color.getColor(self.theme, key: "borderColor");
  }
}

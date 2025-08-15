//
//  ResultAnimation.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

public class ResultAnimation: CommonViewStyle {
  override init(theme: NSDictionary?, key: String) {
    super.init(theme: theme, key: key)
  }
  
  override public func getBackgroundColor() -> UIColor {
    return self.getBackgroundColor(defaultColor: "026ff4")
  }
}

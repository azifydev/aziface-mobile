//
//  Button.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

import Foundation

public class Button {
  private let theme: NSDictionary?
  private let color: Color

  init(theme: NSDictionary?) {
    self.theme = Style().getTarget(theme, key: "button")
    self.color = Color()
  }

  public func getBackgroundNormalColor() -> UIColor {
    return self.color.getColor(self.theme, key: "backgroundNormalColor", defaultColor: "#026ff4")
  }

  public func getBackgroundDisabledColor() -> UIColor {
    return self.color.getColor(self.theme, key: "backgroundDisabledColor", defaultColor: "#b3d4fc")
  }

  public func getBackgroundHighlightColor() -> UIColor {
    return self.color.getColor(self.theme, key: "backgroundHighlightColor", defaultColor: "#0264dc")
  }

  public func getTextNormalColor() -> UIColor {
    return self.color.getColor(self.theme, key: "textNormalColor")
  }

  public func getTextDisabledColor() -> UIColor {
    return self.color.getColor(self.theme, key: "textDisabledColor")
  }

  public func getTextHighlightColor() -> UIColor {
    return self.color.getColor(self.theme, key: "textHighlightColor")
  }
}

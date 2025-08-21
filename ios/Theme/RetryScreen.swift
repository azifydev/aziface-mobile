//
//  RetryScreen.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

public class RetryScreen {
  private static let KEY: String = "retryScreen"
  private let theme: NSDictionary?
  private let color: Color

  init(theme: NSDictionary?) {
    self.theme = Style().getTarget(theme, key: RetryScreen.KEY)
    self.color = Color()
  }

  public func getImageBorderColor() -> UIColor {
    return self.color.getColor(self.theme, key: "imageBorderColor")
  }

  public func getOvalStrokeColor() -> UIColor {
    return self.color.getColor(self.theme, key: "ovalStrokeColor")
  }
}

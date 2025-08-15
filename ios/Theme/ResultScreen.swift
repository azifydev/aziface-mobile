//
//  ResultScreen.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

public class ResultScreen: CommonViewStyle {
  private static let KEY: String = "resultScreen"
  private let theme: NSDictionary?
  private let color: Color
  private let resultAnimation: ResultAnimation

  init() {
    self.theme = Style().getTarget(ResultScreen.KEY)
    self.color = Color()
    self.resultAnimation = ResultAnimation(theme: self.theme)

    super.init(key: ResultScreen.KEY)
  }

  override public func getForegroundColor() -> UIColor {
    return self.getForegroundColor(defaultColor: "#272937")
  }

  public func getActivityIndicatorColor() -> UIColor {
    return self.color.getColor(self.theme, key: "activityIndicatorColor", defaultColor: "#026ff4")
  }

  public func getUploadProgressFillColor() -> UIColor {
    return self.color.getColor(self.theme, key: "uploadProgressFillColor", defaultColor: "#026ff4")
  }

  public func getResultAnimation() -> ResultAnimation {
    return self.resultAnimation
  }
}

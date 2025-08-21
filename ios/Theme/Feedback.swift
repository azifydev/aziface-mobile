//
//  Feedback.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

public class Feedback: CommonViewStyle {
  private static let KEY: String = "feedback"
  private let theme: NSDictionary?
  private let color: Color

  init() {
    self.theme = Style().getTarget(Feedback.KEY)
    self.color = Color()

    super.init(key: Feedback.KEY)
  }

  public func getTextColor() -> UIColor {
    return self.color.getColor(self.theme, key: "textColor")
  }
}

//
//  Guidance.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

public class Guidance: CommonStyle {
  private static let KEY: String = "guidance"
  private let button: Button
  private let retryScreen: RetryScreen
  
  init() {
    let theme = Style().getTarget(Guidance.KEY)
    
    self.button = Button(theme: theme)
    self.retryScreen = RetryScreen(theme: theme)
    
    super.init(key: Guidance.KEY)
  }
  
  public func getButton() -> Button {
    return self.button
  }
  
  public func getRetryScreen() -> RetryScreen {
    return self.retryScreen
  }
  
  override public func getForegroundColor() -> UIColor {
    return self.getForegroundColor(defaultColor: "#272937")
  }
}

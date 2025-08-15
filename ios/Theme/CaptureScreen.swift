//
//  CaptureScreen.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

public class CaptureScreen: CommonStyle {
  private static let KEY: String = "captureScreen"
  private let theme: NSDictionary?
  private let color: Color
  
  init(theme: NSDictionary?) {
    self.theme = Style().getTarget(theme, key: CaptureScreen.KEY)
    self.color = Color()
    
    super.init(theme: theme, key: CaptureScreen.KEY)
  }
  
  public func getFrameStrokeColor() -> UIColor {
    return self.color.getColor(self.theme, key: "frameStrokeColor")
  }
}

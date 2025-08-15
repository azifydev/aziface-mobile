//
//  ResultScreen.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

public class ResultScreen: CommonViewStyle {
  private static let KEY: String = "resultScreen";
  private let theme: NSDictionary?;
  private let color: Color;
  private let resultAnimation: ResultAnimation;
  
  init() {
    self.theme = Style().getTarget(ResultScreen.KEY);
    self.color = Color();
    self.resultAnimation = ResultAnimation(theme: self.theme);
    
    super.init(key: ResultScreen.KEY);
  }
}

public class InitialLoadingAnimation {
  private let target: NSDictionary?
  private let color: Color
  private let font: Font
  
  init() {
    self.target = Style().getTarget("initialLoadingAnimation")
    self.color = Color()
    self.font = Font()
  }
  
  func getTrackColor() -> UIColor {
    return self.color.getColor(self.target, key: "trackColor", defaultColor: "b3d4fc")
  }
  
  func getFillColor() -> UIColor {
    return self.color.getColor(self.target, key: "fillColor", defaultColor: "#026ff4")
  }
  
  public func getFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "font")
  }
}

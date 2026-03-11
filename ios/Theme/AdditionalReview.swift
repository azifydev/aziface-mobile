public class AdditionalReview: ViewStyle {
  private static let KEY: String = "additionalReview"
  private let style: Style
  private let target: NSDictionary?
  private let color: Color
  private let image: Image

  init(target: NSDictionary?) {
    self.style = Style()

    self.target = self.style.getTarget(target, key: AdditionalReview.KEY)
    self.color = Color()
    self.image = Image(target: self.target)

    super.init(target: self.target, key: AdditionalReview.KEY)
  }
  
  private func getBoolean(_ key: String, defaultValue: Bool) -> Bool {
    if !self.style.exists(self.target, key: key) {
      return defaultValue
    }

    return self.target?[key] as! Bool
  }
  
  override func getForegroundColor() -> UIColor {
    return super.getForegroundColor(defaultColor: "#272937")
  }
  
  public func getDisableAdditionalReviewScreen() -> Bool {
    return self.getBoolean("isDisableAdditionalReviewScreen", defaultValue: false)
  }
  
  public func getEnableAdditionalReviewTag() -> Bool {
    return self.getBoolean("isEnableAdditionalReviewTag", defaultValue: true)
  }
  
  public func getDisplayTime() -> Double {
    let defaultDisplayTime: Double = 2.0
    let key = "displayTime"
      
    if !self.style.exists(self.target, key: key) {
      return defaultDisplayTime
    }
      
    let displayTime = self.target?[key] as! Double
    return displayTime < 0 ? defaultDisplayTime : displayTime
  }
  
  public func getTagImageColor() -> UIColor {
    return self.color.getColor(self.target, key: "tagImageColor", defaultColor: "#CC0044")
  }
  
  public func getTagTextColor() -> UIColor {
    return self.color.getColor(self.target, key: "tagTextColor", defaultColor: "#272937")
  }
  
  public func getReviewImage() -> UIImage? {
    return self.image.getSource("reviewImage")
  }
  
  public func getTagImage() -> UIImage? {
    return self.image.getSource("tagImage")
  }
}

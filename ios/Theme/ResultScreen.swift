public class ResultScreen: ViewStyle {
  private static let KEY: String = "resultScreen"
  private let style: Style
  private let target: NSDictionary?
  private let font: Font
  private let color: Color
  private let image: Image
  private let resultAnimation: ResultAnimation
  private let sessionAbortAnimation: SessionAbortAnimation

  init() {
    self.style = Style()
    self.font = Font()
    self.color = Color()
    
    self.target = self.style.getTarget(ResultScreen.KEY)
    
    self.image = Image(target: self.target)
    self.resultAnimation = ResultAnimation(target: self.target)
    self.sessionAbortAnimation = SessionAbortAnimation(target: self.target)
    
    super.init(key: ResultScreen.KEY)
  }
  
  private func getDouble(key: String, defaultValue: Double) -> Double {
    if !self.style.exists(self.target, key: key) {
      return defaultValue
    }
    
    let value = (self.target?[key] ?? defaultValue) as! Double
    return value < 0 ? defaultValue : value
  }

  override public func getForegroundColor() -> UIColor {
    return self.getForegroundColor(defaultColor: "#272937")
  }

  public func getActivityIndicatorColor() -> UIColor {
    return self.color.getColor(self.target, key: "activityIndicatorColor", defaultColor: "#026ff4")
  }
  
  public func getActivityIndicatorImage() -> UIImage? {
    return self.image.getSource("indicatorImage")
  }
  
  public func getFaceScanStillUploadingMessageDelayTime() -> Double {
    return self.getDouble(key: "faceScanStillUploadingMessageDelayTime", defaultValue: 6.0)
  }
  
  public func getIdScanStillUploadingMessageDelayTime() -> Double {
    return self.getDouble(key: "idScanStillUploadingMessageDelayTime", defaultValue: 8.0)
  }
  
  public func getIndicatorRotationInterval() -> Int32 {
    let key = "indicatorRotationInterval"
    let defaultRotationInterval: Int32 = 1000
    
    if !self.style.exists(self.target, key: key) {
      return defaultRotationInterval
    }
    
    let value = (self.target?[key] ?? defaultRotationInterval) as! Int32
    return value < 0 ? defaultRotationInterval : value
  }
  
  public func getUploadProgressFillColor() -> UIColor {
    return self.color.getColor(self.target, key: "uploadProgressFillColor", defaultColor: "#026ff4")
  }
  
  public func getUploadProgressTrackColor() -> UIColor {
    return self.color.getColor(self.target, key: "uploadProgressTrackColor", defaultColor: "#b3d4fc")
  }
  
  public func getAnimationRelativeScale() -> Float {
    let key = "animationRelativeScale"
    let defaultAnimationRelativeScale: Float = 1.0
    
    if !self.style.exists(self.target, key: key) {
      return defaultAnimationRelativeScale
    }
    
    let value = (self.target?[key] ?? defaultAnimationRelativeScale) as! Float
    return value < 0 ? defaultAnimationRelativeScale : value
  }
  
  public func getShowUploadProgressBar() -> Bool {
    let key = "isShowUploadProgressBar"
    
    if !self.style.exists(self.target, key: key) {
      return true
    }
    
    return self.target?[key] as! Bool
  }
  
  public func getFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "font")
  }

  public func getResultAnimation() -> ResultAnimation {
    return self.resultAnimation
  }
  
  public func getSessionAbortAnimation() -> SessionAbortAnimation {
    return self.sessionAbortAnimation
  }
}

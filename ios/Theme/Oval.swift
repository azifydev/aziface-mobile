public class Oval {
  private let style: Style
  private let target: NSDictionary?
  private let color: Color

  init() {
    self.style = Style()
    self.color = Color()
    
    self.target = self.style.getTarget("oval")
  }

  public func getStrokeColor() -> UIColor {
    return self.color.getColor(self.target, key: "strokeColor", defaultColor: "#026ff4")
  }
  
  public func getStrokeWidth() -> Int32 {
    let key = "strokeWidth"
    let defaultStrokeWidth: Int32 = -1
    
    if !self.style.exists(self.target, key: key) {
      return defaultStrokeWidth
    }

    let strokeWidth = (self.target?[key] as? Int32) ?? defaultStrokeWidth
    return strokeWidth < 0 ? defaultStrokeWidth : strokeWidth
  }

  public func getFirstProgressColor() -> UIColor {
    return self.color.getColor(self.target, key: "firstProgressColor", defaultColor: "#0264dc")
  }

  public func getSecondProgressColor() -> UIColor {
    return self.color.getColor(self.target, key: "secondProgressColor", defaultColor: "#0264dc")
  }
  
  public func getProgressRadialOffset() -> Int32 {
    let key = "progressRadialOffset"
    let defaultProgressRadialOffset: Int32 = -1
    
    if !self.style.exists(self.target, key: key) {
      return defaultProgressRadialOffset
    }

    let progressRadialOffset = (self.target?[key] as? Int32) ?? defaultProgressRadialOffset
    return progressRadialOffset < 0 ? defaultProgressRadialOffset : progressRadialOffset
  }
  
  public func getProgressStrokeWidth() -> Int32 {
    let key = "progressStrokeWidth"
    let defaultProgressStrokeWidth: Int32 = -1
    
    if !self.style.exists(self.target, key: key) {
      return defaultProgressStrokeWidth
    }

    let progressStrokeWidth = (self.target?[key] as? Int32) ?? defaultProgressStrokeWidth
    return progressStrokeWidth < 0 ? defaultProgressStrokeWidth : progressStrokeWidth
  }
}

public class Oval {
  private let style: Style
  private let target: NSDictionary?
  private let color: Color

  init() {
    self.style = Style()
    self.color = Color()
    
    self.target = self.style.getTarget("oval")
  }
  
  private func getInt(key: String) -> Int32 {
    let defaultParamValue: Int32 = -1
    
    if !self.style.exists(self.target, key: key) {
      return defaultParamValue
    }

    let paramValue = (self.target?[key] as? Int32) ?? defaultParamValue
    return paramValue < 0 ? defaultParamValue : paramValue
  }

  public func getStrokeColor() -> UIColor {
    return self.color.getColor(self.target, key: "strokeColor", defaultColor: "#026ff4")
  }
  
  public func getStrokeWidth() -> Int32 {
    return self.getInt(key: "strokeWidth")
  }

  public func getFirstProgressColor() -> UIColor {
    return self.color.getColor(self.target, key: "firstProgressColor", defaultColor: "#0264dc")
  }

  public func getSecondProgressColor() -> UIColor {
    return self.color.getColor(self.target, key: "secondProgressColor", defaultColor: "#0264dc")
  }
  
  public func getProgressRadialOffset() -> Int32 {
    return self.getInt(key: "progressRadialOffset")
  }
  
  public func getProgressStrokeWidth() -> Int32 {
    return self.getInt(key: "progressStrokeWidth")
  }
}

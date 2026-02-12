public class Style {
  public func exists(_ key: String) -> Bool {
    return Theme.Style != nil && Theme.Style?[key] != nil
  }

  public func exists(_ theme: NSDictionary?, key: String) -> Bool {
    return theme != nil && theme?[key] != nil
  }
  
  public func getGlobalFontFamily() -> String? {
    let key = "fontFamily"
    
    if self.exists(key) {
      let fontFamily = Theme.Style?[key] as? String
      if fontFamily != nil {
        return fontFamily?.replacingOccurrences(of: ".otf", with: "").replacingOccurrences(of: ".ttf", with: "")
      }
    }
    
    return nil
  }

  public func getTarget(_ key: String) -> NSDictionary? {
    if !self.exists(key) {
      return nil
    }

    return Theme.Style?[key] as? NSDictionary ?? nil
  }

  public func getTarget(_ theme: NSDictionary?, key: String) -> NSDictionary? {
    if !self.exists(theme, key: key) {
      return nil
    }

    return theme?[key] as? NSDictionary ?? nil
  }
}

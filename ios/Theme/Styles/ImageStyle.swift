public class ImageStyle {
  private let style: Style
  private let target: NSDictionary?
  
  public init(key: String) {
    self.style = Style()
    self.target = self.style.getTarget(key)
  }
  
  public init(target: NSDictionary?) {
    self.style = Style()
    self.target = target
  }
  
  public init(target: NSDictionary?, key: String) {
    self.style = Style()
    self.target = self.style.getTarget(target, key: key)
  }
  
  public func getSource(_ key: String) -> UIImage? {
    if !self.style.exists(self.target, key: key) {
      return nil
    }

    let imageName = self.target?[key] as? String ?? ""
    if imageName.isEmpty {
      return nil
    }

    return UIImage(named: imageName)
  }
  
  public func getSource(_ key: String, defaultImage: String) -> UIImage? {
    let source = self.getSource(key)
    return source ?? UIImage(named: defaultImage)
  }
}

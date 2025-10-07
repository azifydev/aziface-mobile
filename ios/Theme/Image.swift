import Foundation

public class Image {
  private let style: Style

  init() {
    self.style = Style()
  }

  public func getImage(_ key: String, defaultImage: String) -> UIImage? {
    let theme = self.style.getTarget("image")

    if !self.style.exists(theme, key: key) {
      return UIImage(named: defaultImage)
    }

    let imageName = theme?[key] as? String ?? ""
    if imageName.isEmpty {
      return UIImage(named: defaultImage)
    }

    return UIImage(named: imageName)
  }
}

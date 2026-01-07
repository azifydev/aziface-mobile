import Foundation

public class General {
  private let style: Style

  init() {
    self.style = Style()
  }

  public func getBorderRadius(_ theme: NSDictionary?, key: String) -> Int32 {
    let defaultBorderRadius: Int32 = 20
    if !self.style.exists(theme, key: key) {
      return defaultBorderRadius
    }

    let borderRadius = (theme?[key] as? Int32) ?? defaultBorderRadius
    return borderRadius < 0 ? defaultBorderRadius : borderRadius
  }
  
  public func getBorderRadius(_ theme: NSDictionary?, key: String, defaultBorderRadius: Int32) -> Int32 {
    if !self.style.exists(theme, key: key) {
      return defaultBorderRadius
    }

    let borderRadius = (theme?[key] as? Int32) ?? defaultBorderRadius
    return borderRadius < 0 ? defaultBorderRadius : borderRadius
  }

  public func getStatusBarStyle(_ key: String) -> UIStatusBarStyle {
    var defaultStatusBarStyle = UIStatusBarStyle.default
    if #available(iOS 13, *) {
      defaultStatusBarStyle = UIStatusBarStyle.darkContent
    }

    if !self.style.exists(key) {
      return defaultStatusBarStyle
    }

    let statusBarColor = (Theme.Style?[key] as? String) ?? ""
    if statusBarColor.isEmpty {
      return defaultStatusBarStyle
    }

    if #available(iOS 13, *) {
      switch statusBarColor {
      case "DARK_CONTENT":
        do {
          return defaultStatusBarStyle
        }
      case "LIGHT_CONTENT":
        do {
          return UIStatusBarStyle.lightContent
        }
      case "DEFAULT":
        do {
          return UIStatusBarStyle.default
        }
      default:
        do {
          return defaultStatusBarStyle
        }
      }
    }

    return defaultStatusBarStyle
  }
}

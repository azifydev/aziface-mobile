import FaceTecSDK
import Foundation

public class Font {
  private let style: Style

  public init() {
    self.style = Style()
  }

  public func getTypography(theme: NSDictionary?, key: String) -> UIFont {
    let systemFontSize = UIFont.systemFontSize
    let defaultFont = UIFont.systemFont(ofSize: systemFontSize)
    
    if !self.style.exists(theme, key: key) {
      return defaultFont
    }

    let filenameWithExt = theme?[key] as? String ?? ""
    let filenameWithoutExt = filenameWithExt.replacingOccurrences(of: ".otf", with: "")
      .replacingOccurrences(of: ".ttf", with: "")

    return UIFont(name: filenameWithoutExt, size: systemFontSize) ?? defaultFont
  }
}

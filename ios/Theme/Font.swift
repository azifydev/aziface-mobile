import FaceTecSDK
import Foundation

public class Font {
  private let style: Style

  public init() {
    self.style = Style()
  }
  
  private func getFont(filename: String?) -> UIFont {
    let systemFontSize = UIFont.systemFontSize
    let defaultFont = UIFont.systemFont(ofSize: systemFontSize)
    
    if filename == nil {
      return defaultFont
    }
    
    return UIFont(name: filename!, size: systemFontSize) ?? defaultFont
  }

  public func getTypography(theme: NSDictionary?, key: String) -> UIFont {
    let systemFontSize = UIFont.systemFontSize
    let defaultFont = UIFont.systemFont(ofSize: systemFontSize)
    let globalFontFamily = self.style.getGlobalFontFamily()

    if !self.style.exists(theme, key: key) {
      return self.getFont(filename: globalFontFamily)
    }

    let filenameWithExt = theme?[key] as? String ?? ""
    let filenameWithoutExt = filenameWithExt.replacingOccurrences(of: ".otf", with: "")
      .replacingOccurrences(of: ".ttf", with: "")

    return self.getFont(filename: filenameWithoutExt)
  }
}

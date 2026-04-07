import Foundation

public class Localization {
  private var locale: String = "default"
  public static let DEFAULT = "en"

  public init() {
    self.locale = Localization.DEFAULT
  }

  public func getLocale() -> String {
    return self.locale
  }

  public func setLocale(locale: String) {
    let temp = locale.lowercased()

    switch (temp) {
    case "af", "ar", "de", "el", "es", "fr", "ja", "kk", "nb", "ru", "vi", "zh":
      self.locale = temp
      break
    case "pt-br":
      self.locale = "pt-BR"
      break
    default:
      self.locale = "en"
      break
    }
  }
}

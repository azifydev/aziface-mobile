import Foundation

public enum Localization: String {
  case `default` = "default"
  case af = "af"
  case ar = "ar"
  case de = "de"
  case el = "el"
  case es = "es"
  case fr = "fr"
  case ja = "ja"
  case kk = "kk"
  case nb = "nb"
  case ptBR = "pt-BR"
  case ru = "ru"
  case vi = "vi"
  case zh = "zh"
  case en = "en"

  public init(locale: String) {
    self = Localization(rawValue: locale) ?? .default
  }

  public static func from(_ locale: String) -> Localization {
    return Localization(locale: locale)
  }
}

//
//  Color.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

import Foundation

public class Color {
  private static let DEFAULT_COLOR: String = "#ffffff"
  private let style: Style

  init() {
    self.style = Style()
  }

  private func parseColor(_ key: String, defaultColor: UIColor) -> UIColor {
    let color = (Theme.Style?[key] as? String) ?? ""
    if color.isEmpty {
      return defaultColor
    }

    return UIColor(hexString: color)
  }

  private func parseColor(_ theme: NSDictionary, key: String, defaultColor: UIColor) -> UIColor {
    let color = theme[key] as? String ?? ""
    if color.isEmpty {
      return defaultColor
    }

    return UIColor(hexString: color)
  }

  public func parseColors(_ hexColors: [String?]) -> [UIColor] {
    var colors = [UIColor]()
    if hexColors.isEmpty {
      return colors
    }

    for hexColor in hexColors {
      if hexColor != nil && hexColor != "" {
        colors.append(UIColor(hexString: hexColor!))
      }
    }

    if colors.count == 1 {
      colors.append(colors[0])
    }

    return colors
  }

  public func parseGradientColors(_ hexColors: [String?]) -> [CGColor] {
    var colors = [CGColor]()
    let defaultColor = UIColor(hexString: "#026ff4").cgColor
    let defaultColors = [defaultColor, defaultColor]
    if hexColors.isEmpty {
      return defaultColors
    }

    for hexColor in hexColors {
      if hexColor != nil && hexColor != "" {
        let cgColor = UIColor(hexString: hexColor!).cgColor
        colors.append(cgColor)
      }
    }

    if colors.count == 1 {
      colors.append(colors[0])
    }

    return colors
  }

  public func getColor(_ key: String) -> UIColor {
    let defaultColor = UIColor(hexString: Color.DEFAULT_COLOR)
    if !self.style.exists(key) {
      return defaultColor
    }

    return self.parseColor(key, defaultColor: defaultColor)
  }

  public func getColor(_ theme: NSDictionary?, key: String) -> UIColor {
    let defaultColor = UIColor(hexString: Color.DEFAULT_COLOR)
    if !self.style.exists(theme, key: key) {
      return defaultColor
    }

    return self.parseColor(theme!, key: key, defaultColor: defaultColor)
  }

  public func getColor(_ key: String, defaultColor: String) -> UIColor {
    if !self.style.exists(key) {
      return UIColor(hexString: defaultColor)
    }

    return self.parseColor(key, defaultColor: UIColor(hexString: defaultColor))
  }

  public func getColor(_ theme: NSDictionary?, key: String, defaultColor: String) -> UIColor {
    if !self.style.exists(theme, key: key) {
      return UIColor(hexString: defaultColor)
    }

    return self.parseColor(theme!, key: key, defaultColor: UIColor(hexString: defaultColor))
  }
}

extension UIColor {
  convenience init(hexString: String) {
    let hex = hexString.trimmingCharacters(in: CharacterSet.alphanumerics.inverted)
    var int = UInt64()
    Scanner(string: hex).scanHexInt64(&int)
    let a: UInt64
    let r: UInt64
    let g: UInt64
    let b: UInt64
    switch hex.count {
    case 3:  // RGB (12-bit)
      (a, r, g, b) = (255, (int >> 8) * 17, (int >> 4 & 0xF) * 17, (int & 0xF) * 17)
    case 4:  // RGBA (16-bit)
      (r, g, b, a) = (
        (int >> 12) * 17, (int >> 8 & 0xF) * 17, (int >> 4 & 0xF) * 17, (int & 0xF) * 17
      )
    case 6:  // RGB (24-bit)
      (a, r, g, b) = (255, int >> 16, int >> 8 & 0xFF, int & 0xFF)
    case 8:  // ARGB (32-bit)
      (r, g, b, a) = (int >> 24, int >> 16 & 0xFF, int >> 8 & 0xFF, int & 0xFF)
    default:
      (a, r, g, b) = (255, 0, 0, 0)
    }
    self.init(
      red: CGFloat(r) / 255, green: CGFloat(g) / 255, blue: CGFloat(b) / 255,
      alpha: CGFloat(a) / 255)
  }
}

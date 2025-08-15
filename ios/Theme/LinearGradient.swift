//
//  LinearGradient.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

import Foundation

public class LinearGradient {
  private let color: Color
  private let style: Style
  
  init() {
    self.color = Color()
    self.style = Style()
  }
  
  private func getPoints(_ points: NSDictionary?) -> CGPoint {
    let defaultPoints = CGPoint.init(x: 0, y: 0)
    
    if points == nil {
      return defaultPoints
    }
    
    let x = points?["x"] as? Double ?? defaultPoints.x
    let y = points?["y"] as? Double ?? defaultPoints.y
    return CGPoint.init(x: x, y: y)
  }
  
  private func getPoints(_ points: NSDictionary?, defaultPoints: CGPoint) -> CGPoint {
    if points == nil {
      return defaultPoints
    }
    
    let x = points?["x"] as? Double ?? defaultPoints.x
    let y = points?["y"] as? Double ?? defaultPoints.y
    return CGPoint.init(x: x, y: y)
  }
  
  private func getLocations(_ locations: [Int?]) -> [NSNumber] {
    let defaultLocations: [NSNumber] = [0, 1]
    if locations.count != 2 {
      return defaultLocations
    }
    
    for location in locations {
      if location == nil || (location! < 0 || location! > 1) {
        return defaultLocations
      }
    }
    return locations as! [NSNumber]
  }
  
  private func getGradient(_ gradient: NSDictionary) -> CAGradientLayer {
    let acGradient = CAGradientLayer.init()
    
    let defaultEndPoint = CGPoint.init(x: 1, y: 0)
    acGradient.colors = self.color.parseGradientColors(gradient["colors"] as? [String?] ?? [])
    acGradient.locations = self.getLocations(gradient["locations"] as? [Int?] ?? [])
    acGradient.startPoint = self.getPoints(gradient["startPoint"] as? NSDictionary ?? nil)
    acGradient.endPoint = self.getPoints(gradient["endPoint"] as? NSDictionary ?? nil, defaultPoints: defaultEndPoint)
    
    return acGradient
  }
  
  public func getLinearGradient(_ theme: NSDictionary?, key: String) -> [UIColor] {
    let color = UIColor(hexString: "#ffffff")
    let defaultLinearGradient = [color, color]
    if !self.style.exists(theme, key: key) {
      return defaultLinearGradient
    }
    
    let hexColors = Theme.Style?[key] as? [String?] ?? []
    let colors = self.color.parseColors(hexColors)
    if colors.isEmpty {
      return defaultLinearGradient
    }
    
    return colors
  }
  
  public func getGradientLayer(_ theme: NSDictionary?, key: String) -> CAGradientLayer {
    let defaultColor = UIColor(hexString: "#026ff4").cgColor
    let defaultGradient = CAGradientLayer.init()
    defaultGradient.colors = [defaultColor, defaultColor]
    defaultGradient.locations = [0, 1]
    defaultGradient.startPoint = CGPoint.init(x: 0, y: 0)
    defaultGradient.endPoint = CGPoint.init(x: 1, y: 0)
    
    if !self.style.exists(theme, key: key) {
      return defaultGradient
    }
    
    let gradiant = theme?[key] as? NSDictionary ?? nil
    if gradiant != nil {
      return self.getGradient(gradiant!)
    }
    
    return defaultGradient
  }
}

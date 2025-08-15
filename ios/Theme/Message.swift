//
//  Message.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

public class Message {
  private let style: Style

  init() {
    self.style = Style()
  }

  private func getMessage(_ theme: NSDictionary?, key: String, defaultMessage: String) -> String {
    if !self.style.exists(theme, key: key) {
      return defaultMessage
    }

    return theme?[key] as? String ?? defaultMessage
  }

  public func getMessage(_ target: String, key: String, defaultMessage: String) -> String {
    let theme = self.style.getTarget(target)
    return self.getMessage(theme, key: key, defaultMessage: defaultMessage)
  }

  public func getMessage(_ target: String, parent: String, key: String, defaultMessage: String)
    -> String
  {
    let targetTheme = self.style.getTarget(target)
    let parentTheme = self.style.getTarget(targetTheme, key: parent)
    return self.getMessage(parentTheme, key: key, defaultMessage: defaultMessage)
  }
}

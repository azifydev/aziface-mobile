import Foundation

class CommonParams {
  private let params: NSDictionary?

  init(params: NSDictionary?) {
    self.params = params
  }

  private func getParam(_ key: String) -> Any? {
    guard let value = self.params?.value(forKey: key) else {
      return nil
    }

    return value
  }

  private func getParam(parent: String, key: String) -> String? {
    guard let parentValue = self.params?.value(forKey: parent) as? [String: String] else {
      return nil
    }

    return parentValue[key]
  }

  private func isDevelopment() -> Bool {
    guard let isDev: Bool = self.getParam("isDevelopment") as? Bool else {
      return false
    }

    return isDev
  }

  func isNull() -> Bool {
    if (self.params == nil) {
      return true
    }

    return self.params?.count == 0
  }

  func setHeaders(_ headers: NSDictionary) {
    Config.setHeaders(headers)
  }

  func build() {
    if self.isNull() {
      return
    }

    Config.setDeviceKeyIdentifier(self.getParam("deviceKeyIdentifier") as? String ?? "")
    Config.setBaseUrl(self.getParam("baseUrl") as? String ?? "")
    Config.setIsDevelopment(self.isDevelopment())
  }
}

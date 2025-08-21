//
//  CommonParams.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 11/08/25.
//

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

  func isDeveloper() -> Bool {
    guard let developerMode: Bool = self.getParam("isDeveloperMode") as? Bool else {
      return false
    }

    return developerMode
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

  func buildProcessorPathURL() {
    if self.isNull() {
      return
    }

    let basePathUrl = self.getParam(parent: "pathUrl", key: "base") ?? ""
    let matchPathUrl = self.getParam(parent: "pathUrl", key: "match") ?? ""

    Config.setProcessorPathURL("base", pathUrl: basePathUrl)
    Config.setProcessorPathURL("match", pathUrl: matchPathUrl)
  }

  func build() {
    if self.isNull() {
      return
    }

    Config.setDevice(self.getParam("device") as? String ?? "")
    Config.setUrl(self.getParam("url") as? String ?? "")
    Config.setKey(self.getParam("key") as? String ?? "")
    Config.setProductionKeyText(self.getParam("productionKey") as? String ?? "")
    Config.setProcessId(self.getParam("processId") as? String ?? "")
  }
}

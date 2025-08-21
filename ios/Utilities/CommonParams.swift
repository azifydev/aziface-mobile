//
//  CommonParams.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 11/08/25.
//

import Foundation

class CommonParams {
  private let params: NSDictionary
  
  init(params: NSDictionary) {
    self.params = params
  }
  
  private func getParam<T>(_ key: String) -> T? {
    guard let value = params.value(forKey: key) else {
      return nil
    }
    return value as? T
  }
  
  private func getParam(parent: String, key: String) -> String? {
    guard let parentValue = params.value(forKey: parent) as? [String: String] else {
      return nil
    }
    
    return parentValue[key]
  }
  
  func isDeveloper() -> Bool {
    guard let developerMode: Bool = getParam("isDeveloperMode") else {
      return false
    }
    
    return developerMode
  }
  
  func setHeaders(_ headers: NSDictionary) {
    Config.setHeaders(headers)
  }
  
  func buildProcessorPathURL() {
    if params.count == 0 { return }
    
    let basePathUrl = getParam(parent: "pathUrl", key: "base") ?? ""
    let matchPathUrl = getParam(parent: "pathUrl", key: "match") ?? ""
    
    Config.setProcessorPathURL("base", pathUrl: basePathUrl)
    Config.setProcessorPathURL("match", pathUrl: matchPathUrl)
  }
  
  func build() {
    if params.count == 0 { return }
    
    Config.setDevice(params.value(forKey: "device") as! String)
    Config.setUrl(params.value(forKey: "url") as! String)
    Config.setKey(params.value(forKey: "key") as! String)
    Config.setProductionKeyText(params.value(forKey: "productionKey") as! String)
    Config.setProcessId(params.value(forKey: "processId") as! String)
  }
}

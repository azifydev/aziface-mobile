//
//  DynamicRoute.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 11/08/25.
//

import Foundation

class DynamicRoute: NSObject {

  private func getPathUrl(target: String, defaultPathUrl: String) -> String {
    guard let processorPathUrl = (Config.ProcessorPathURL[target]) else {
      return defaultPathUrl
    }

    if processorPathUrl.trimmingCharacters(in: .whitespaces).isEmpty {
      return defaultPathUrl
    }

    var resultUrl = processorPathUrl

    if processorPathUrl.contains("/:") {
      let pattern = "/:([a-zA-Z0-9_]+)"
      let regex = try! NSRegularExpression(pattern: pattern, options: [])
      let range = NSRange(location: 0, length: processorPathUrl.count)

      resultUrl = regex.stringByReplacingMatches(
        in: processorPathUrl,
        options: [],
        range: range,
        withTemplate: "/\(Config.ProcessId ?? "")"
      )
    }

    return resultUrl
  }

  func getPathUrlEnrollment3d(target: String) -> String {
    return getPathUrl(
      target: target,
      defaultPathUrl: "/Process/\(Config.ProcessId ?? "")/Enrollment3d")
  }

  func getPathUrlMatch3d2dIdScan(target: String) -> String {
    return getPathUrl(
      target: target,
      defaultPathUrl: "/Process/\(Config.ProcessId ?? "")/Match3d2dIdScan")
  }

  func getPathUrlMatch3d3d(target: String) -> String {
    return getPathUrl(
      target: target, defaultPathUrl: "/Process/\(Config.ProcessId ?? "")/Match3d3d")
  }
}

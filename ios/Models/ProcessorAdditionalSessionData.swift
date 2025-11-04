import Foundation

public class ProcessorAdditionalSessionData: JSONParams {
  private var object: [AnyHashable: Any?]

  override init(target: [AnyHashable: Any]?) {
    self.object = [:]
    
    super.init(target: target)
    
    self.parseJSON()
  }

  private func parseJSON() {
    let platform = self.getParam("platform", type: String.self)
    let appID = self.getParam("appID", type: String.self)
    let installationID = self.getParam("installationID", type: String.self)
    let deviceModel = self.getParam("deviceModel", type: String.self)
    let deviceSDKVersion = self.getParam("deviceSDKVersion", type: String.self)
    let userAgent = self.getParam("userAgent", type: String.self)
    let sessionID = self.getParam("sessionID", type: String.self)

    self.object["platform"] = platform
    self.object["appID"] = appID
    self.object["installationID"] = installationID
    self.object["deviceModel"] = deviceModel
    self.object["deviceSDKVersion"] = deviceSDKVersion
    self.object["userAgent"] = userAgent
    self.object["sessionID"] = sessionID
  }

  func getDict() -> NSDictionary {
    return NSDictionary(dictionary: self.object as [AnyHashable : Any])
  }
}

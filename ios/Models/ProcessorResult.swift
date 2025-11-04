import Foundation

public class ProcessorResult: JSONParams {
  private var object: [AnyHashable: Any?]

  override init(target: [AnyHashable: Any]?) {
    self.object = [:]

    super.init(target: target)

    self.parseJSON()
  }

  private func parseJSON() {
    let ageV2GroupEnumInt = self.getParam("ageV2GroupEnumInt", type: Int.self)
    let livenessProven = self.getParam("livenessProven", type: Bool.self)
    let auditTrailImage = self.getParam("auditTrailImage", type: String.self)
    let matchLevel = self.getParam("matchLevel", type: Int.self)

    self.object["ageV2GroupEnumInt"] = self.defaultIfNull(ageV2GroupEnumInt, defaultValue: -1)
    self.object["livenessProven"] = livenessProven == true
    self.object["auditTrailImage"] = auditTrailImage
    self.object["matchLevel"] = self.defaultIfNull(matchLevel, defaultValue: -1)
  }

  func getDict() -> NSDictionary {
    return NSDictionary(dictionary: self.object as [AnyHashable: Any])
  }
}

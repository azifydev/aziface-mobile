import Foundation

public class ProcessorHttpCallInfo: JSONParams {
  private var object: [AnyHashable: Any?]

  override init(target: [AnyHashable: Any]?) {
    self.object = [:]
    
    super.init(target: target)
    
    self.parseJSON()
  }

  private func parseJSON() {
    let epochSecond = self.getParam("epochSecond", type: Int.self)
    let tid = self.getParam("tid", type: String.self)
    let path = self.getParam("path", type: String.self)
    let requestMethod = self.getParam("requestMethod", type: String.self)
    let date = self.getParam("date", type: String.self)

    self.object["epochSecond"] = self.defaultIfNull(epochSecond, defaultValue: -1)
    self.object["tid"] = tid
    self.object["path"] = path
    self.object["requestMethod"] = requestMethod
    self.object["date"] = date
  }

  func getDict() -> NSDictionary {
    return NSDictionary(dictionary: self.object as [AnyHashable: Any])
  }
}

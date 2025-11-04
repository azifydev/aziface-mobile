import Foundation

public class ProcessorError {
  private var object: [AnyHashable: String]

  init(message: String, code: String) {
    self.object = [:]

    self.object["message"] = message
    self.object["code"] = code
  }

  func getDict() -> NSDictionary {
    return NSDictionary(dictionary: self.object as [AnyHashable: String])
  }
}

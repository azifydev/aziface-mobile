import Foundation

public class ProcessorResponse {
  private var object: [AnyHashable: Any?]
  
  init() {
    self.object = [:]
    
    self.reset()
  }
  
  func parseJSON(target: [AnyHashable: Any]) {
    let processorData = ProcessorData(target: target)
    
    let data = processorData.getDict()
    let responseBlob = data.value(forKey: "responseBlob")
    
    self.object["isSuccess"] = responseBlob != nil
    self.object["data"] = data
    self.object["error"] = nil
  }
  
  func getSuccess() -> Bool {
    let isSuccess = self.object["isSuccess"]
    if (isSuccess == nil) {
      return false
    }
    
    return isSuccess as! Bool
  }
  
  func getData() -> NSDictionary? {
    let data = self.object["data"]
    if (data == nil) {
      return nil
    }
        
    return NSDictionary(dictionary: data as! [AnyHashable: Any])
  }
  
  func setData(target: [AnyHashable: Any]) {
    self.parseJSON(target: target)
  }
  
  func getDict() -> NSDictionary {
    return NSDictionary(dictionary: self.object as [AnyHashable: Any])
  }
  
  func setError(message: String, code: String) {
    self.reset()
    
    let processorError = ProcessorError(message: message, code: code)
    
    self.object["error"] = processorError.getDict()
  }
  
  func reset() {
    self.object["isSuccess"] = false
    self.object["data"] = nil
    self.object["error"] = nil
  }
}

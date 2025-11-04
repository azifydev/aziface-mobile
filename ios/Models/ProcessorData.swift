public class ProcessorData: JSONParams {
  private var object: [AnyHashable: Any?]

  override init(target: [AnyHashable: Any]?) {
    self.object = [:]
    
    super.init(target: target)
  }
  
  private func parseJSONToHttpCallInfo(target: [AnyHashable: Any]?) -> NSDictionary? {
    if (target == nil) {
      return nil
    }
    
    let processor = ProcessorHttpCallInfo(target: target)
    return processor.getDict()
  }
  
  private func parseJSONToAdditionalSessionData(target: [AnyHashable: Any]?) -> NSDictionary? {
    if (target == nil) {
      return nil
    }
    
    let processor = ProcessorAdditionalSessionData(target: target)
    return processor.getDict()
  }
  
  private func parseJSONToResult(target: [AnyHashable: Any]?) -> NSDictionary? {
    if (target == nil) {
      return nil
    }
    
    let processor = ProcessorHttpCallInfo(target: target)
    return processor.getDict()
  }
  
  private func parseJSONToIDScanResultsSoFar(target: [AnyHashable: Any]?) -> NSDictionary? {
    if (target == nil) {
      return nil
    }
    
    let processor = ProcessorIDScanResultsSoFar(target: target)
    return processor.getDict()
  }
  
  func parseJSON() {
    let didError = self.getParam("didError", type: Bool.self)
    let responseBlob = self.getParam("responseBlob", type: String.self)
    let httpCallInfo = self.getParam("httpCallInfo", type: [AnyHashable : Any].self)
    let externalDatabaseRefID = self.getParam("externalDatabaseRefID", type: String.self)
    let additionalSessionData = self.getParam("additionalSessionData", type: [AnyHashable : Any].self)
    let result = self.getParam("result", type: [AnyHashable : Any].self)
    let idScanSessionId = self.getParam("idScanSessionId", type: String.self)
    let idScanResultsSoFar = self.getParam("idScanResultsSoFar", type: [AnyHashable : Any].self)
    
    self.object["didError"] = didError
    self.object["responseBlob"] = responseBlob
    self.object["httpCallInfo"] = self.parseJSONToHttpCallInfo(target: httpCallInfo)
    self.object["externalDatabaseRefID"] = externalDatabaseRefID
    self.object["additionalSessionData"] = self.parseJSONToIDScanResultsSoFar(target: additionalSessionData)
    self.object["result"] = self.parseJSONToResult(target: result)
    self.object["idScanSessionId"] = idScanSessionId
    self.object["idScanResultsSoFar"] = self.parseJSONToIDScanResultsSoFar(target: idScanResultsSoFar)
  }
  
  func getDict() -> NSDictionary {
    return NSDictionary(dictionary: self.object as [AnyHashable: Any])
  }
}

import FaceTecSDK
import Foundation

public class NetworkingRequest: NSObject, URLSessionTaskDelegate {
  private var responseProcessor: NSMutableDictionary
  let referencingProcessor: SessionRequestProcessor
  let sessionRequestCallback: FaceTecSessionRequestProcessorCallback

  init(
    referencingProcessor: SessionRequestProcessor,
    sessionRequestCallback: FaceTecSessionRequestProcessorCallback
  ) {
    self.responseProcessor = NSMutableDictionary()
    self.referencingProcessor = referencingProcessor
    self.sessionRequestCallback = sessionRequestCallback

    super.init()

    self.reset()
  }

  private func reset() {
    self.responseProcessor.setValue(false, forKey: "isSuccess")
    self.responseProcessor.setValue(nil, forKey: "data")
  }

  func send(sessionRequestBlob: String, data: NSDictionary?) {
    var sessionRequestCallPayload: [String: Any] = [:]
    sessionRequestCallPayload["requestBlob"] = sessionRequestBlob

    if data != nil {
      sessionRequestCallPayload["data"] = data
    }

    if !Aziface.DemonstrationExternalDatabaseRefID.isEmpty {
      sessionRequestCallPayload["externalDatabaseRefID"] =
        Aziface.DemonstrationExternalDatabaseRefID
    }

    var request = Config.getRequest()

    request.httpMethod = "POST"
    request.setValue("application/json", forHTTPHeaderField: "Content-Type")
    request.httpBody = try! JSONSerialization.data(withJSONObject: sessionRequestCallPayload)

    let session = URLSession(
      configuration: URLSessionConfiguration.default, delegate: self,
      delegateQueue: OperationQueue.main)

    let networkRequest = session.dataTask(
      with: request as URLRequest,
      completionHandler: { data, response, error in
        let responseBlob: String = self.getResponseBlobOrHandleError(data: data)

        if !responseBlob.isEmpty {
          self.responseProcessor.setValue(true, forKey: "isSuccess")

          self.referencingProcessor.onShared(data: self.responseProcessor)
          self.referencingProcessor.onResponseBlobReceived(
            responseBlob: responseBlob, sessionRequestCallback: self.sessionRequestCallback)
        }
      })

    networkRequest.resume()
  }

  func getResponseBlobOrHandleError(data: Data?) -> String {
    guard let data = data else {
      callAbortAndClose()
      return ""
    }

    guard
      let responseJSON = try? JSONSerialization.jsonObject(
        with: data, options: JSONSerialization.ReadingOptions.allowFragments)
        as? [String: AnyObject]
    else {
      callAbortAndClose()
      return ""
    }

    guard let responseBlob = responseJSON["responseBlob"] as? String else {
      callAbortAndClose()
      return ""
    }

    self.responseProcessor.setValue(responseJSON, forKey: "data")

    return responseBlob
  }

  func callAbortAndClose() {
    self.reset()

    self.referencingProcessor.onShared(data: self.responseProcessor)
    self.referencingProcessor.onCatastrophicNetworkError(
      sessionRequestCallback: self.sessionRequestCallback)
  }

  public func urlSession(
    _ session: URLSession, task: URLSessionTask, didSendBodyData bytesSent: Int64,
    totalBytesSent: Int64, totalBytesExpectedToSend: Int64
  ) {
    let uploadProgress: Float = Float(totalBytesSent) / Float(totalBytesExpectedToSend)
    self.referencingProcessor.onUploadProgress(
      progress: uploadProgress, sessionRequestCallback: self.sessionRequestCallback)
  }
}

//
//  LivenessCheckProcessor.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 18/04/24.
//  Copyright © 2024 Facebook. All rights reserved.
//

import FaceTecSDK
import Foundation
import UIKit

class LivenessCheckProcessor: NSObject, Processor, FaceTecFaceScanProcessorDelegate,
  URLSessionTaskDelegate
{
  public var success = false
  public var data: NSDictionary!
  public var latestNetworkRequest: URLSessionTask!
  public var viewController: AziFaceViewController!
  public var faceScanResultCallback: FaceTecFaceScanResultCallback!
  public let theme: Theme!

  init(sessionToken: String, viewController: AziFaceViewController, data: NSDictionary) {
    self.viewController = viewController
    self.data = data
    self.theme = Theme()

    super.init()

    AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: true)

    let controller = FaceTec.sdk.createSessionVC(
      faceScanProcessorDelegate: self, sessionToken: sessionToken)

    FaceTecUtilities.getTopMostViewController()?.present(
      controller, animated: true, completion: nil)
  }

  func processSessionWhileFaceTecSDKWaits(
    sessionResult: FaceTecSessionResult, faceScanResultCallback: FaceTecFaceScanResultCallback
  ) {
    self.viewController.setLatestSessionResult(sessionResult: sessionResult)
    self.faceScanResultCallback = faceScanResultCallback

    if sessionResult.status != FaceTecSessionStatus.sessionCompletedSuccessfully {
      if latestNetworkRequest != nil {
        latestNetworkRequest.cancel()
      }

      AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
      faceScanResultCallback.onFaceScanResultCancel()
      print("(Aziface SDK) Status is not session completed successfully!")
      return
    }

    var parameters: [String: Any] = [:]
    if self.data != nil {
      parameters["data"] = self.data
    }
    parameters["faceScan"] = sessionResult.faceScanBase64
    parameters["auditTrailImage"] = sessionResult.auditTrailCompressedBase64![0]
    parameters["lowQualityAuditTrailImage"] = sessionResult.lowQualityAuditTrailCompressedBase64![0]

    let dynamicRoute = DynamicRoute()
    let route = dynamicRoute.getPathUrlLiveness3d(target: "base")
    var request = Config.makeRequest(url: route, httpMethod: "POST")

    request.httpBody = try! JSONSerialization.data(
      withJSONObject: parameters, options: JSONSerialization.WritingOptions(rawValue: 0))

    let session = URLSession(
      configuration: URLSessionConfiguration.default, delegate: self,
      delegateQueue: OperationQueue.main)
    latestNetworkRequest = session.dataTask(
      with: request as URLRequest,
      completionHandler: { data, response, error in
        if let httpResponse = response as? HTTPURLResponse {
          if httpResponse.statusCode < 200 || httpResponse.statusCode >= 299 {
            AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
            faceScanResultCallback.onFaceScanResultCancel()
            print(
              "(Aziface SDK) Exception raised while attempting HTTPS call. Status code: \(httpResponse.statusCode)"
            )
            return
          }
        }

        if error != nil {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
          self.faceScanResultCallback.onFaceScanResultCancel()
          print("(Aziface SDK) An error occurred while scanning")
          return
        }

        guard let data = data else {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
          self.faceScanResultCallback.onFaceScanResultCancel()
          print("(Aziface SDK) Data object not found!")
          return
        }

        guard
          let responseJSON = try? JSONSerialization.jsonObject(
            with: data, options: JSONSerialization.ReadingOptions.allowFragments)
            as? [String: AnyObject]
        else {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
          self.faceScanResultCallback.onFaceScanResultCancel()
          print("(Aziface SDK) Invalid JSON response.")
          return
        }

        guard let scanResultBlob = responseJSON["scanResultBlob"] as? String,
          let wasProcessed = responseJSON["wasProcessed"] as? Bool
        else {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
          self.faceScanResultCallback.onFaceScanResultCancel()
          print("(Aziface SDK) Missing required keys 'scanResultBlob' or 'wasProcessed' in 'data'.")
          return
        }

        if wasProcessed == true {
          let message = self.theme.getLivenessMessage(
            "successMessage", defaultMessage: "Liveness Confirmed")
          FaceTecCustomization.setOverrideResultScreenSuccessMessage(message)

          self.success = faceScanResultCallback.onFaceScanGoToNextStep(
            scanResultBlob: scanResultBlob)
        } else {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
          self.faceScanResultCallback.onFaceScanResultCancel()
          print("(Aziface SDK) AziFace SDK wasn't able to process the values.")
          return
        }
      })

    latestNetworkRequest.resume()

    DispatchQueue.main.asyncAfter(deadline: .now() + 6) {
      if self.latestNetworkRequest.state == .completed { return }

      let message = self.theme.getLivenessMessage(
        "uploadMessage", defaultMessage: "Still Uploading...")
      let uploadMessage: NSMutableAttributedString = NSMutableAttributedString.init(string: message)
      faceScanResultCallback.onFaceScanUploadMessageOverride(uploadMessageOverride: uploadMessage)
    }
  }

  func urlSession(
    _ session: URLSession, task: URLSessionTask, didSendBodyData bytesSent: Int64,
    totalBytesSent: Int64, totalBytesExpectedToSend: Int64
  ) {
    let uploadProgress: Float = Float(totalBytesSent) / Float(totalBytesExpectedToSend)
    faceScanResultCallback.onFaceScanUploadProgress(uploadedPercent: uploadProgress)
  }

  func onFaceTecSDKCompletelyDone() {
    self.viewController.onComplete()
  }

  func isSuccess() -> Bool {
    return success
  }
}

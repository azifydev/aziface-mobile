//
//  EnrollmentProcessor.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 18/04/24.
//  Copyright © 2024 Facebook. All rights reserved.
//

import FaceTecSDK
import Foundation
import UIKit

class EnrollmentProcessor: NSObject, Processor, FaceTecFaceScanProcessorDelegate,
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
    if sessionResult.status != .sessionCompletedSuccessfully {
      if latestNetworkRequest != nil {
        latestNetworkRequest.cancel()
      }

      AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
      faceScanResultCallback.onFaceScanResultCancel()
      print("(Aziface SDK) Status is not session completed successfully!")
      return
    }
    var parameters: [String: Any] = ["faceScan": sessionResult.faceScanBase64]
    if let auditTrailImage = sessionResult.auditTrailCompressedBase64?.first {
      parameters["auditTrailImage"] = auditTrailImage
    }
    if let lowQualityAuditTrailImage = sessionResult.lowQualityAuditTrailCompressedBase64?.first {
      parameters["lowQualityAuditTrailImage"] = lowQualityAuditTrailImage
    }
    parameters["externalDatabaseRefID"] = self.viewController.getLatestExternalDatabaseRefID()
    if let data = self.data {
      parameters["data"] = data
    }

    let dynamicRoute = DynamicRoute()
    let route = dynamicRoute.getPathUrlEnrollment3d(target: "base")

    do {
      var request = Config.makeRequest(url: route, httpMethod: "POST")
      request.httpBody = try JSONSerialization.data(withJSONObject: parameters, options: [])

      let session = URLSession(configuration: .default, delegate: self, delegateQueue: .main)
      latestNetworkRequest = session.dataTask(with: request) { [weak self] data, response, error in
        guard let self = self else { return }

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

        do {
          guard
            let responseJSON = try JSONSerialization.jsonObject(with: data, options: [])
              as? [String: AnyObject]
          else {
            AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
            self.faceScanResultCallback.onFaceScanResultCancel()
            print("(Aziface SDK) Invalid JSON response.")
            return
          }
          guard let responseData = responseJSON["data"] as? [String: AnyObject] else {
            AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
            self.faceScanResultCallback.onFaceScanResultCancel()
            print("(Aziface SDK) Missing 'data' in response.")
            return
          }

          if let error = responseData["error"] as? Int, error != 0 {
            AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
            self.faceScanResultCallback.onFaceScanResultCancel()
            print("(Aziface SDK) Error in response")
            return
          }

          guard let scanResultBlob = responseData["scanResultBlob"] as? String,
                let wasProcessed = responseData["wasProcessed"] as? Int
          else {
            AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
            self.faceScanResultCallback.onFaceScanResultCancel()
            print(
              "(Aziface SDK) Missing required keys 'scanResultBlob' or 'wasProcessed' in 'data'.")
            return
          }

          if wasProcessed == 1 {
            let message = self.theme.getEnrollmentMessage(
              "successMessage", defaultMessage: "Face Scanned\n3D Liveness Proven")
            FaceTecCustomization.setOverrideResultScreenSuccessMessage(message)
            self.success = self.faceScanResultCallback.onFaceScanGoToNextStep(
              scanResultBlob: scanResultBlob)
          } else {
            AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
            self.faceScanResultCallback.onFaceScanResultCancel()
            print("(Aziface SDK) AziFace SDK wasn't able to process the values.")
            return
          }
        } catch {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
          self.faceScanResultCallback.onFaceScanResultCancel()
          print("(Aziface SDK) Exception raised while attempting to parse JSON result.")
          return
        }
      }
      latestNetworkRequest?.resume()
    } catch {
      AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
      faceScanResultCallback.onFaceScanResultCancel()
      print("(Aziface SDK) Exception raised while attempting HTTPS call.")
      return
    }

    DispatchQueue.main.asyncAfter(deadline: .now() + 6) {
      guard self.latestNetworkRequest.state != .completed else { return }

      let message = self.theme.getEnrollmentMessage(
        "uploadMessage", defaultMessage: "Still Uploading...")
      let uploadMessage = NSMutableAttributedString(string: message)
      self.faceScanResultCallback.onFaceScanUploadMessageOverride(
        uploadMessageOverride: uploadMessage)
    }
  }

  func urlSession(
    _ session: URLSession, task: URLSessionTask, didSendBodyData bytesSent: Int64,
    totalBytesSent: Int64, totalBytesExpectedToSend: Int64
  ) {
    let uploadProgress = Float(totalBytesSent) / Float(totalBytesExpectedToSend)
    faceScanResultCallback.onFaceScanUploadProgress(uploadedPercent: uploadProgress)
  }

  func onFaceTecSDKCompletelyDone() {
    self.viewController.onComplete()
  }

  func isSuccess() -> Bool {
    return success
  }
}

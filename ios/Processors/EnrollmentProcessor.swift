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
    
    let controller = FaceTec.sdk.createSessionVC(faceScanProcessorDelegate: self, sessionToken: sessionToken)
    
    FaceTecUtilities.getTopMostViewController()?.present(controller, animated: true, completion: nil)
  }
  
  func processSessionWhileFaceTecSDKWaits(
    sessionResult: FaceTecSessionResult, faceScanResultCallback: FaceTecFaceScanResultCallback
  ) {
    self.viewController.setLatestSessionResult(sessionResult: sessionResult)
    self.faceScanResultCallback = faceScanResultCallback
    
    if sessionResult.status != .sessionCompletedSuccessfully {
      latestNetworkRequest?.cancel()
      AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
      faceScanResultCallback.onFaceScanResultCancel()
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
        
        if let error = error {
          print("Network error")
          self.faceScanResultCallback.onFaceScanResultCancel()
          return
        }
        
        guard let data = data else {
          print("No data received from server.")
          self.faceScanResultCallback.onFaceScanResultCancel()
          return
        }
        
        // decode response
        do {
          
          guard
            let responseJSON = try JSONSerialization.jsonObject(with: data, options: [])
              as? [String: AnyObject]
          else {
            print("Invalid JSON response.")
            self.faceScanResultCallback.onFaceScanResultCancel()
            return
          }
          
          guard let responseData = responseJSON["data"] as? [String: AnyObject] else {
            print("Missing 'data' in response.")
            self.faceScanResultCallback.onFaceScanResultCancel()
            return
          }
          
          if let error = responseData["error"] as? Int, error != 0 {
            let errorMessage = responseData["errorMessage"] as? String
            print("Error in response")
            self.faceScanResultCallback.onFaceScanResultCancel()
            return
          }
          
          guard let scanResultBlob = responseData["scanResultBlob"] as? String,
                let wasProcessed = responseData["wasProcessed"] as? Int
          else {
            print("Missing required keys 'scanResultBlob' or 'wasProcessed' in 'data'.")
            self.faceScanResultCallback.onFaceScanResultCancel()
            return
          }
          
          if wasProcessed == 1 {
            let message = self.theme.getEnrollmentMessage(
              "successMessage", defaultMessage: "Face Scanned\n3D Liveness Proven")
            FaceTecCustomization.setOverrideResultScreenSuccessMessage(message)
            self.success = self.faceScanResultCallback.onFaceScanGoToNextStep(
              scanResultBlob: scanResultBlob)
          } else {
            print("Face scan was not processed successfully.")
            self.faceScanResultCallback.onFaceScanResultCancel()
          }
        } catch {
          print("Error parsing JSON response")
          self.faceScanResultCallback.onFaceScanResultCancel()
        }
      }
      
      latestNetworkRequest?.resume()
    } catch {
      print("Error creating request")
      faceScanResultCallback.onFaceScanResultCancel()
    }
    
    // show loading message
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

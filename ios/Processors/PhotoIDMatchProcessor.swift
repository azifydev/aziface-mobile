//
//  PhotoIDMatchProcessor.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 08/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import FaceTecSDK
import Foundation
import UIKit

class PhotoIDMatchProcessor: NSObject, Processor, FaceTecFaceScanProcessorDelegate,
                             FaceTecIDScanProcessorDelegate, URLSessionTaskDelegate
{
  public var success = false
  public var faceScanWasSuccessful = false
  public var latestExternalDatabaseRefID: String!
  public var data: NSDictionary!
  public var latestNetworkRequest: URLSessionTask!
  public var viewController: AziFaceViewController!
  public var faceScanResultCallback: FaceTecFaceScanResultCallback!
  public var idScanResultCallback: FaceTecIDScanResultCallback!

  init(sessionToken: String, viewController: AziFaceViewController, data: NSDictionary) {
    self.viewController = viewController
    self.latestExternalDatabaseRefID = self.viewController.getLatestExternalDatabaseRefID()
    self.data = data
    super.init()

    let theme: Theme = AziFaceViewController.Style

    FaceTecCustomization.setIDScanUploadMessageOverrides(
      frontSideUploadStarted: theme.getPhotoIDMatchMessage(
        "frontSide", key: "uploadStarted", defaultMessage: "Uploading\nEncrypted\nID Scan"),  // Upload of ID front-side has started.
      frontSideStillUploading: theme.getPhotoIDMatchMessage(
        "frontSide", key: "stillUploading", defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of ID front-side is still uploading to Server after an extended period of time.
      frontSideUploadCompleteAwaitingResponse: theme.getPhotoIDMatchMessage(
        "frontSide", key: "uploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),  // Upload of ID front-side to the Server is complete.
      frontSideUploadCompleteAwaitingProcessing: theme.getPhotoIDMatchMessage(
        "frontSide", key: "uploadCompleteAwaitingProcessing", defaultMessage: "Processing\nID Scan"),  // Upload of ID front-side is complete and we are waiting for the Server to finish processing and respond.
      backSideUploadStarted: theme.getPhotoIDMatchMessage(
        "backSide", key: "uploadStarted", defaultMessage: "Uploading\nEncrypted\nBack of ID"),  // Upload of ID back-side has started.
      backSideStillUploading: theme.getPhotoIDMatchMessage(
        "backSide", key: "stillUploading", defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of ID back-side is still uploading to Server after an extended period of time.
      backSideUploadCompleteAwaitingResponse: theme.getPhotoIDMatchMessage(
        "backSide", key: "uploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),  // Upload of ID back-side to Server is complete.
      backSideUploadCompleteAwaitingProcessing: theme.getPhotoIDMatchMessage(
        "backSide", key: "uploadCompleteAwaitingProcessing",
        defaultMessage: "Processing\nBack of ID"),  // Upload of ID back-side is complete and we are waiting for the Server to finish processing and respond.
      userConfirmedInfoUploadStarted: theme.getPhotoIDMatchMessage(
        "userConfirmedInfo", key: "uploadStarted", defaultMessage: "Uploading\nYour Confirmed Info"),  // Upload of User Confirmed Info has started.
      userConfirmedInfoStillUploading: theme.getPhotoIDMatchMessage(
        "userConfirmedInfo", key: "stillUploading",
        defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of User Confirmed Info is still uploading to Server after an extended period of time.
      userConfirmedInfoUploadCompleteAwaitingResponse: theme.getPhotoIDMatchMessage(
        "userConfirmedInfo", key: "uploadCompleteAwaitingResponse",
        defaultMessage: "Upload Complete"),  // Upload of User Confirmed Info to the Server is complete.
      userConfirmedInfoUploadCompleteAwaitingProcessing: theme.getPhotoIDMatchMessage(
        "userConfirmedInfo", key: "uploadCompleteAwaitingProcessing",
        defaultMessage: "Processing"),  // Upload of User Confirmed Info is complete and we are waiting for the Server to finish processing and respond.
      nfcUploadStarted: theme.getPhotoIDMatchMessage(
        "nfc", key: "uploadStarted",
        defaultMessage: "Uploading Encrypted\nNFC Details"),  // Upload of NFC Details has started.
      nfcStillUploading: theme.getPhotoIDMatchMessage(
        "nfc", key: "stillUploading",
        defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of NFC Details is still uploading to Server after an extended period of time.
      nfcUploadCompleteAwaitingResponse: theme.getPhotoIDMatchMessage(
        "nfc", key: "uploadCompleteAwaitingResponse",
        defaultMessage: "Upload Complete"),  // Upload of NFC Details to the Server is complete.
      nfcUploadCompleteAwaitingProcessing: theme.getPhotoIDMatchMessage(
        "nfc", key: "uploadCompleteAwaitingProcessing",
        defaultMessage: "Processing\nNFC Details"),  // Upload of NFC Details is complete and we are waiting for the Server to finish processing and respond.
      skippedNFCUploadStarted: theme.getPhotoIDMatchMessage(
        "skippedNFC", key: "uploadStarted",
        defaultMessage: "Uploading Encrypted\nID Details"),  // Upload of ID Details has started.
      skippedNFCStillUploading: theme.getPhotoIDMatchMessage(
        "skippedNFC", key: "stillUploading",
        defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of ID Details is still uploading to Server after an extended period of time.
      skippedNFCUploadCompleteAwaitingResponse: theme.getPhotoIDMatchMessage(
        "skippedNFC", key: "uploadCompleteAwaitingResponse",
        defaultMessage: "Upload Complete"),  // Upload of ID Details to the Server is complete.
      skippedNFCUploadCompleteAwaitingProcessing: theme.getPhotoIDMatchMessage(
        "skippedNFC", key: "uploadCompleteAwaitingProcessing",
        defaultMessage: "Processing\nID Details")  // Upload of ID Details is complete and we are waiting for the Server to finish processing and respond.
    )

    AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: true)

    let idScanViewController = FaceTec.sdk.createSessionVC(
      faceScanProcessorDelegate: self, idScanProcessorDelegate: self, sessionToken: sessionToken)
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
      return
    }

    // prepare parameters
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
            let message = AziFaceViewController.Style.getPhotoIDMatchMessage(
              "successMessage", defaultMessage: "Liveness Face Scanned\n3D Liveness Proven")
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
      if self.latestNetworkRequest.state == .completed { return }

      let message = AziFaceViewController.Style.getPhotoIDMatchMessage(
        "uploadMessage", defaultMessage: "Still Uploading...")
      let uploadMessage: NSMutableAttributedString = NSMutableAttributedString.init(string: message)
      faceScanResultCallback.onFaceScanUploadMessageOverride(uploadMessageOverride: uploadMessage)
    }
  }

  func processIDScanWhileFaceTecSDKWaits(
    idScanResult: FaceTecIDScanResult, idScanResultCallback: FaceTecIDScanResultCallback
  ) {
    self.viewController.setLatestIDScanResult(idScanResult: idScanResult)

    self.idScanResultCallback = idScanResultCallback

    if idScanResult.status != FaceTecIDScanStatus.success {
      latestNetworkRequest?.cancel()
      AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
      idScanResultCallback.onIDScanResultCancel()
      return
    }

    // prepare parameters
    let minMatchLevel = 3
    var parameters: [String: Any] = [:]
    parameters["idScan"] = idScanResult.idScanBase64
    if let frontImage = idScanResult.frontImagesCompressedBase64?.first {
      parameters["idScanFrontImage"] = frontImage
    }
    if let backImage = idScanResult.backImagesCompressedBase64?.first {
      parameters["idScanBackImage"] = backImage
    }
    parameters["minMatchLevel"] = minMatchLevel
    parameters["externalDatabaseRefID"] = self.latestExternalDatabaseRefID

    let dynamicRoute = DynamicRoute()
    let route = dynamicRoute.getPathUrlMatch3d2dIdScan(target: "match")
    var request = Config.makeRequest(url: route, httpMethod: "POST")

    do {
      request.httpBody = try JSONSerialization.data(withJSONObject: parameters, options: [])
    } catch {
      print("Error creating request")
      idScanResultCallback.onIDScanResultCancel()
      return
    }

    let session = URLSession(configuration: .default, delegate: self, delegateQueue: .main)
    latestNetworkRequest = session.dataTask(with: request) { data, response, error in
      if let error = error {
        idScanResultCallback.onIDScanResultCancel()
        return
      }

      guard let data = data else {
        idScanResultCallback.onIDScanResultCancel()
        return
      }

      guard
        let responseJSON = try? JSONSerialization.jsonObject(with: data, options: [])
          as? [String: AnyObject]
      else {
        print("Invalid JSON response.")
        idScanResultCallback.onIDScanResultCancel()
        return
      }

      guard let responseData = responseJSON["data"] as? [String: AnyObject] else {
        print("Missing 'data' in response.")
        idScanResultCallback.onIDScanResultCancel()
        return
      }

      if let error = responseJSON["error"] as? Bool, error {
        let errorMessage = responseJSON["errorMessage"] as? String ?? "Erro desconhecido"
        print("Missing 'data' in response.")
        idScanResultCallback.onIDScanResultCancel()
        return
      }

      guard let scanResultBlob = responseData["scanResultBlob"] as? String,
            let wasProcessed = responseData["wasProcessed"] as? Bool
      else {
        print("Missing required keys 'scanResultBlob' or 'wasProcessed' in 'data'.")
        idScanResultCallback.onIDScanResultCancel()
        return
      }

      if wasProcessed {
        let theme: Theme = AziFaceViewController.Style

        FaceTecCustomization.setIDScanResultScreenMessageOverrides(
          successFrontSide: theme.getPhotoIDMatchMessage("success", key: "frontSide", defaultMessage: "ID Scan Complete"),
          successFrontSideBackNext: theme.getPhotoIDMatchMessage("success", key: "frontSideBackNext", defaultMessage: "Front of ID\nScanned"),
          successFrontSideNFCNext: theme.getPhotoIDMatchMessage("success", key: "frontSideNFCNext", defaultMessage: "Front of ID\nScanned"),
          successBackSide: theme.getPhotoIDMatchMessage("success", key: "backSide", defaultMessage: "ID Scan Complete"),
          successBackSideNFCNext: theme.getPhotoIDMatchMessage("success", key: "backSideNFCNext", defaultMessage: "Back of ID\nScanned"),
          successPassport: theme.getPhotoIDMatchMessage("success", key: "passport", defaultMessage: "Passport Scan Complete"),
          successPassportNFCNext: theme.getPhotoIDMatchMessage("success", key: "passportNFCNext", defaultMessage: "Passport Scanned"),
          successUserConfirmation: theme.getPhotoIDMatchMessage("success", key: "userConfirmation", defaultMessage: "Photo ID Scan\nComplete"),
          successNFC: theme.getPhotoIDMatchMessage("success", key: "NFC", defaultMessage: "ID Scan Complete"),
          retryFaceDidNotMatch: theme.getPhotoIDMatchMessage("retry", key: "faceDidNotMatch", defaultMessage: "Face Didn’t Match\nHighly Enough"),
          retryIDNotFullyVisible: theme.getPhotoIDMatchMessage("retry", key: "IDNotFullyVisible", defaultMessage: "ID Document\nNot Fully Visible"),
          retryOCRResultsNotGoodEnough: theme.getPhotoIDMatchMessage("retry", key: "OCRResultsNotGoodEnough", defaultMessage: "ID Text Not Legible"),
          retryIDTypeNotSupported: theme.getPhotoIDMatchMessage("retry", key: "IDTypeNotSupported", defaultMessage: "ID Type Mismatch\nPlease Try Again"),
          skipOrErrorNFC: theme.getPhotoIDMatchMessage("skipOrErrorNFC", defaultMessage: "ID Details\nUploaded")
        )

        self.success = idScanResultCallback.onIDScanResultProceedToNextStep(
          scanResultBlob: scanResultBlob)
      } else {
        print("Face scan was not processed successfully.")
        AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
        idScanResultCallback.onIDScanResultCancel()
      }
    }

    latestNetworkRequest?.resume()
  }

  func urlSession(
    _ session: URLSession, task: URLSessionTask, didSendBodyData bytesSent: Int64,
    totalBytesSent: Int64, totalBytesExpectedToSend: Int64
  ) {
    let uploadProgress: Float = Float(totalBytesSent) / Float(totalBytesExpectedToSend)
    if idScanResultCallback != nil {
      idScanResultCallback.onIDScanUploadProgress(uploadedPercent: uploadProgress)
    } else {
      faceScanResultCallback.onFaceScanUploadProgress(uploadedPercent: uploadProgress)
    }
  }

  func onFaceTecSDKCompletelyDone() {
    self.viewController.onComplete()
  }

  func isSuccess() -> Bool {
    return success
  }
}

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
  public let theme: Theme!

  init(sessionToken: String, viewController: AziFaceViewController, data: NSDictionary) {
    self.viewController = viewController
    self.latestExternalDatabaseRefID = self.viewController.getLatestExternalDatabaseRefID()
    self.data = data
    self.theme = Theme()

    super.init()

    FaceTecCustomization.setIDScanUploadMessageOverrides(
      frontSideUploadStarted: self.theme.getPhotoIDMatchMessage(
        "frontSide", key: "uploadStarted", defaultMessage: "Uploading\nEncrypted\nID Scan"),  // Upload of ID front-side has started.
      frontSideStillUploading: self.theme.getPhotoIDMatchMessage(
        "frontSide", key: "stillUploading", defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of ID front-side is still uploading to Server after an extended period of time.
      frontSideUploadCompleteAwaitingResponse: self.theme.getPhotoIDMatchMessage(
        "frontSide", key: "uploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),  // Upload of ID front-side to the Server is complete.
      frontSideUploadCompleteAwaitingProcessing: self.theme.getPhotoIDMatchMessage(
        "frontSide", key: "uploadCompleteAwaitingProcessing", defaultMessage: "Processing\nID Scan"),  // Upload of ID front-side is complete and we are waiting for the Server to finish processing and respond.
      backSideUploadStarted: self.theme.getPhotoIDMatchMessage(
        "backSide", key: "uploadStarted", defaultMessage: "Uploading\nEncrypted\nBack of ID"),  // Upload of ID back-side has started.
      backSideStillUploading: self.theme.getPhotoIDMatchMessage(
        "backSide", key: "stillUploading", defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of ID back-side is still uploading to Server after an extended period of time.
      backSideUploadCompleteAwaitingResponse: self.theme.getPhotoIDMatchMessage(
        "backSide", key: "uploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),  // Upload of ID back-side to Server is complete.
      backSideUploadCompleteAwaitingProcessing: self.theme.getPhotoIDMatchMessage(
        "backSide", key: "uploadCompleteAwaitingProcessing",
        defaultMessage: "Processing\nBack of ID"),  // Upload of ID back-side is complete and we are waiting for the Server to finish processing and respond.
      userConfirmedInfoUploadStarted: self.theme.getPhotoIDMatchMessage(
        "userConfirmedInfo", key: "uploadStarted", defaultMessage: "Uploading\nYour Confirmed Info"),  // Upload of User Confirmed Info has started.
      userConfirmedInfoStillUploading: self.theme.getPhotoIDMatchMessage(
        "userConfirmedInfo", key: "stillUploading",
        defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of User Confirmed Info is still uploading to Server after an extended period of time.
      userConfirmedInfoUploadCompleteAwaitingResponse: self.theme.getPhotoIDMatchMessage(
        "userConfirmedInfo", key: "uploadCompleteAwaitingResponse",
        defaultMessage: "Upload Complete"),  // Upload of User Confirmed Info to the Server is complete.
      userConfirmedInfoUploadCompleteAwaitingProcessing: self.theme.getPhotoIDMatchMessage(
        "userConfirmedInfo", key: "uploadCompleteAwaitingProcessing",
        defaultMessage: "Processing"),  // Upload of User Confirmed Info is complete and we are waiting for the Server to finish processing and respond.
      nfcUploadStarted: self.theme.getPhotoIDMatchMessage(
        "nfc", key: "uploadStarted",
        defaultMessage: "Uploading Encrypted\nNFC Details"),  // Upload of NFC Details has started.
      nfcStillUploading: self.theme.getPhotoIDMatchMessage(
        "nfc", key: "stillUploading",
        defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of NFC Details is still uploading to Server after an extended period of time.
      nfcUploadCompleteAwaitingResponse: self.theme.getPhotoIDMatchMessage(
        "nfc", key: "uploadCompleteAwaitingResponse",
        defaultMessage: "Upload Complete"),  // Upload of NFC Details to the Server is complete.
      nfcUploadCompleteAwaitingProcessing: self.theme.getPhotoIDMatchMessage(
        "nfc", key: "uploadCompleteAwaitingProcessing",
        defaultMessage: "Processing\nNFC Details"),  // Upload of NFC Details is complete and we are waiting for the Server to finish processing and respond.
      skippedNFCUploadStarted: self.theme.getPhotoIDMatchMessage(
        "skippedNFC", key: "uploadStarted",
        defaultMessage: "Uploading Encrypted\nID Details"),  // Upload of ID Details has started.
      skippedNFCStillUploading: self.theme.getPhotoIDMatchMessage(
        "skippedNFC", key: "stillUploading",
        defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of ID Details is still uploading to Server after an extended period of time.
      skippedNFCUploadCompleteAwaitingResponse: self.theme.getPhotoIDMatchMessage(
        "skippedNFC", key: "uploadCompleteAwaitingResponse",
        defaultMessage: "Upload Complete"),  // Upload of ID Details to the Server is complete.
      skippedNFCUploadCompleteAwaitingProcessing: self.theme.getPhotoIDMatchMessage(
        "skippedNFC", key: "uploadCompleteAwaitingProcessing",
        defaultMessage: "Processing\nID Details")  // Upload of ID Details is complete and we are waiting for the Server to finish processing and respond.
    )

    AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: true)

    let controller = FaceTec.sdk.createSessionVC(
      faceScanProcessorDelegate: self, idScanProcessorDelegate: self, sessionToken: sessionToken)

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
            print("(Aziface SDK) Missing required keys 'scanResultBlob' or 'wasProcessed' in 'data'.")
            return
          }

          if wasProcessed == 1 {
            let message = self.theme.getPhotoIDMatchMessage(
              "successMessage", defaultMessage: "Liveness Face Scanned\n3D Liveness Proven")
            FaceTecCustomization.setOverrideResultScreenSuccessMessage(message)
            self.success = self.faceScanResultCallback.onFaceScanGoToNextStep(
              scanResultBlob: scanResultBlob)
          } else {
            AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
            self.faceScanResultCallback.onFaceScanResultCancel()
            print("(Aziface SDK) AziFace SDK wasn't have to values processed!")
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
      if self.latestNetworkRequest.state == .completed { return }

      let message = self.theme.getPhotoIDMatchMessage(
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
      if latestNetworkRequest != nil {
        latestNetworkRequest.cancel()
      }

      AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
      idScanResultCallback.onIDScanResultCancel()
      print("(Aziface SDK) Scan status is not success!")
      return
    }

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
      AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
      self.idScanResultCallback.onIDScanResultCancel()
      print("(Aziface SDK) Exception raised while attempting HTTPS call.")
      return
    }

    let session = URLSession(configuration: .default, delegate: self, delegateQueue: .main)
    latestNetworkRequest = session.dataTask(with: request) { data, response, error in
      if error != nil {
        AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
        self.idScanResultCallback.onIDScanResultCancel()
        print("(Aziface SDK) An error occurred while scanning")
        return
      }

      guard let data = data else {
        AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
        self.idScanResultCallback.onIDScanResultCancel()
        print("(Aziface SDK) Data object not found!")
        return
      }

      guard
        let responseJSON = try? JSONSerialization.jsonObject(with: data, options: [])
          as? [String: AnyObject]
      else {
        AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
        self.idScanResultCallback.onIDScanResultCancel()
        print("(Aziface SDK) Invalid JSON response.")
        return
      }

      guard let responseData = responseJSON["data"] as? [String: AnyObject] else {
        AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
        self.idScanResultCallback.onIDScanResultCancel()
        print("(Aziface SDK) Missing 'data' in response.")
        return
      }

      if let error = responseJSON["error"] as? Bool, error {
        AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
        self.idScanResultCallback.onIDScanResultCancel()
        print("(Aziface SDK) Error in response")
        return
      }

      guard let scanResultBlob = responseData["scanResultBlob"] as? String,
        let wasProcessed = responseData["wasProcessed"] as? Bool
      else {
        AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
        self.idScanResultCallback.onIDScanResultCancel()
        print("(Aziface SDK) Missing required keys 'scanResultBlob' or 'wasProcessed' in 'data'.")
        return
      }

      if wasProcessed == true {
        FaceTecCustomization.setIDScanResultScreenMessageOverrides(
          successFrontSide: self.theme.getPhotoIDMatchMessage(
            "success", key: "frontSide", defaultMessage: "ID Scan Complete"),
          successFrontSideBackNext: self.theme.getPhotoIDMatchMessage(
            "success", key: "frontSideBackNext", defaultMessage: "Front of ID\nScanned"),
          successFrontSideNFCNext: self.theme.getPhotoIDMatchMessage(
            "success", key: "frontSideNFCNext", defaultMessage: "Front of ID\nScanned"),
          successBackSide: self.theme.getPhotoIDMatchMessage(
            "success", key: "backSide", defaultMessage: "ID Scan Complete"),
          successBackSideNFCNext: self.theme.getPhotoIDMatchMessage(
            "success", key: "backSideNFCNext", defaultMessage: "Back of ID\nScanned"),
          successPassport: self.theme.getPhotoIDMatchMessage(
            "success", key: "passport", defaultMessage: "Passport Scan Complete"),
          successPassportNFCNext: self.theme.getPhotoIDMatchMessage(
            "success", key: "passportNFCNext", defaultMessage: "Passport Scanned"),
          successUserConfirmation: self.theme.getPhotoIDMatchMessage(
            "success", key: "userConfirmation", defaultMessage: "Photo ID Scan\nComplete"),
          successNFC: self.theme.getPhotoIDMatchMessage(
            "success", key: "NFC", defaultMessage: "ID Scan Complete"),
          retryFaceDidNotMatch: self.theme.getPhotoIDMatchMessage(
            "retry", key: "faceDidNotMatch", defaultMessage: "Face Didn’t Match\nHighly Enough"),
          retryIDNotFullyVisible: self.theme.getPhotoIDMatchMessage(
            "retry", key: "IDNotFullyVisible", defaultMessage: "ID Document\nNot Fully Visible"),
          retryOCRResultsNotGoodEnough: self.theme.getPhotoIDMatchMessage(
            "retry", key: "OCRResultsNotGoodEnough", defaultMessage: "ID Text Not Legible"),
          retryIDTypeNotSupported: self.theme.getPhotoIDMatchMessage(
            "retry", key: "IDTypeNotSupported", defaultMessage: "ID Type Mismatch\nPlease Try Again"
          ),
          skipOrErrorNFC: self.theme.getPhotoIDMatchMessage(
            "skipOrErrorNFC", defaultMessage: "ID Details\nUploaded")
        )

        self.success = idScanResultCallback.onIDScanResultProceedToNextStep(
          scanResultBlob: scanResultBlob)
      } else {
        AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
        self.idScanResultCallback.onIDScanResultCancel()
        print("(Aziface SDK) AziFace SDK wasn't have to scan values processed!")
        return
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

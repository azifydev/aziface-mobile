//
//  PhotoIDScanProcessor.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 08/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import FaceTecSDK
import Foundation
import UIKit

class PhotoIDScanProcessor: NSObject, Processor, FaceTecIDScanProcessorDelegate,
                            URLSessionTaskDelegate
{
  public var success = false
  public var data: NSDictionary!
  public var latestNetworkRequest: URLSessionTask!
  public var viewController: AziFaceViewController!
  public var idScanResultCallback: FaceTecIDScanResultCallback!
  public let theme: Theme!

  init(sessionToken: String, viewController: AziFaceViewController, data: NSDictionary) {
    self.viewController = viewController
    self.data = data
    self.theme = Theme()
    super.init()

    FaceTecCustomization.setIDScanUploadMessageOverrides(
      frontSideUploadStarted: self.theme.getPhotoIDScanMessage(
        "frontSide", key: "uploadStarted", defaultMessage: "Uploading\nEncrypted\nID Scan"),  // Upload of ID front-side has started.
      frontSideStillUploading: self.theme.getPhotoIDScanMessage(
        "frontSide", key: "stillUploading", defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of ID front-side is still uploading to Server after an extended period of time.
      frontSideUploadCompleteAwaitingResponse: self.theme.getPhotoIDScanMessage(
        "frontSide", key: "uploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),  // Upload of ID front-side to the Server is complete.
      frontSideUploadCompleteAwaitingProcessing: self.theme.getPhotoIDScanMessage(
        "frontSide", key: "uploadCompleteAwaitingProcessing", defaultMessage: "Processing\nID Scan"),  // Upload of ID front-side is complete and we are waiting for the Server to finish processing and respond.
      backSideUploadStarted: self.theme.getPhotoIDScanMessage(
        "backSide", key: "uploadStarted", defaultMessage: "Uploading\nEncrypted\nBack of ID"),  // Upload of ID back-side has started.
      backSideStillUploading: self.theme.getPhotoIDScanMessage(
        "backSide", key: "stillUploading", defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of ID back-side is still uploading to Server after an extended period of time.
      backSideUploadCompleteAwaitingResponse: self.theme.getPhotoIDScanMessage(
        "backSide", key: "uploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),  // Upload of ID back-side to Server is complete.
      backSideUploadCompleteAwaitingProcessing: self.theme.getPhotoIDScanMessage(
        "backSide", key: "uploadCompleteAwaitingProcessing",
        defaultMessage: "Processing\nBack of ID"),  // Upload of ID back-side is complete and we are waiting for the Server to finish processing and respond.
      userConfirmedInfoUploadStarted: self.theme.getPhotoIDScanMessage(
        "userConfirmedInfo", key: "uploadStarted", defaultMessage: "Uploading\nYour Confirmed Info"),  // Upload of User Confirmed Info has started.
      userConfirmedInfoStillUploading: self.theme.getPhotoIDScanMessage(
        "userConfirmedInfo", key: "stillUploading",
        defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of User Confirmed Info is still uploading to Server after an extended period of time.
      userConfirmedInfoUploadCompleteAwaitingResponse: self.theme.getPhotoIDScanMessage(
        "userConfirmedInfo", key: "uploadCompleteAwaitingResponse",
        defaultMessage: "Upload Complete"),  // Upload of User Confirmed Info to the Server is complete.
      userConfirmedInfoUploadCompleteAwaitingProcessing: self.theme.getPhotoIDScanMessage(
        "userConfirmedInfo", key: "uploadCompleteAwaitingProcessing", defaultMessage: "Processing"),  // Upload of User Confirmed Info is complete and we are waiting for the Server to finish processing and respond.
      nfcUploadStarted: self.theme.getPhotoIDScanMessage(
        "nfc", key: "uploadStarted", defaultMessage: "Uploading Encrypted\nNFC Details"),  // Upload of NFC Details has started.
      nfcStillUploading: self.theme.getPhotoIDScanMessage(
        "nfc", key: "stillUploading", defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of NFC Details is still uploading to Server after an extended period of time.
      nfcUploadCompleteAwaitingResponse: self.theme.getPhotoIDScanMessage(
        "nfc", key: "uploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),  // Upload of NFC Details to the Server is complete.
      nfcUploadCompleteAwaitingProcessing: self.theme.getPhotoIDScanMessage(
        "nfc", key: "uploadCompleteAwaitingProcessing", defaultMessage: "Processing\nNFC Details"),  // Upload of NFC Details is complete and we are waiting for the Server to finish processing and respond.
      skippedNFCUploadStarted: self.theme.getPhotoIDScanMessage(
        "skippedNFC", key: "uploadStarted", defaultMessage: "Uploading Encrypted\nID Details"),  // Upload of ID Details has started.
      skippedNFCStillUploading: self.theme.getPhotoIDScanMessage(
        "skippedNFC", key: "stillUploading", defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of ID Details is still uploading to Server after an extended period of time.
      skippedNFCUploadCompleteAwaitingResponse: self.theme.getPhotoIDScanMessage(
        "skippedNFC", key: "uploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),  // Upload of ID Details to the Server is complete.
      skippedNFCUploadCompleteAwaitingProcessing: self.theme.getPhotoIDScanMessage(
        "skippedNFC", key: "uploadCompleteAwaitingProcessing",
        defaultMessage: "Processing\nID Details")  // Upload of ID Details is complete and we are waiting for the Server to finish processing and respond.
    )

    AzifaceMobileSdk.emitter.sendEvent(withName: "onOpen", body: true)
    AzifaceMobileSdk.emitter.sendEvent(withName: "onClose", body: false)
    AzifaceMobileSdk.emitter.sendEvent(withName: "onCancel", body: false)
    AzifaceMobileSdk.emitter.sendEvent(withName: "onError", body: false)

    let controller = FaceTec.sdk.createSessionVC(
      idScanProcessorDelegate: self, sessionToken: sessionToken)

    FaceTecUtilities.getTopMostViewController()?.present(
      controller, animated: true, completion: nil)
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

      AzifaceMobileSdk.emitter.sendEvent(withName: "onOpen", body: false)
      AzifaceMobileSdk.emitter.sendEvent(withName: "onClose", body: true)
      AzifaceMobileSdk.emitter.sendEvent(withName: "onCancel", body: true)
      AzifaceMobileSdk.emitter.sendEvent(withName: "onError", body: false)
      
      idScanResultCallback.onIDScanResultCancel()
      print("(Aziface SDK) Scan status is not success!")
      return
    }

    var parameters: [String: Any] = [:]
    if self.data != nil {
      parameters["data"] = self.data
    }
    parameters["idScan"] = idScanResult.idScanBase64
    if idScanResult.frontImagesCompressedBase64?.isEmpty == false {
      parameters["idScanFrontImage"] = idScanResult.frontImagesCompressedBase64![0]
    }
    if idScanResult.backImagesCompressedBase64?.isEmpty == false {
      parameters["idScanBackImage"] = idScanResult.backImagesCompressedBase64![0]
    }

    let dynamicRoute = DynamicRoute()
    let route = dynamicRoute.getPathUrlIdScanOnly(target: "base")
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
            AzifaceMobileSdk.emitter.sendEvent(withName: "onOpen", body: false)
            AzifaceMobileSdk.emitter.sendEvent(withName: "onClose", body: true)
            AzifaceMobileSdk.emitter.sendEvent(withName: "onCancel", body: true)
            AzifaceMobileSdk.emitter.sendEvent(withName: "onError", body: true)
            
            print("Exception raised while attempting HTTPS call. Status code: \(httpResponse.statusCode)")
            idScanResultCallback.onIDScanResultCancel()
            print("(Aziface SDK) Exception raised while attempting HTTPS call.")
            return
          }
        }

        if error != nil {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onOpen", body: false)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onClose", body: true)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCancel", body: true)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onError", body: true)
          
          idScanResultCallback.onIDScanResultCancel()
          print("(Aziface SDK) An error occurred while scanning")
          return
        }

        guard let data = data else {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onOpen", body: false)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onClose", body: true)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCancel", body: true)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onError", body: true)
          
          idScanResultCallback.onIDScanResultCancel()
          print("(Aziface SDK) Data object not found!")
          return
        }

        guard
          let responseJSON = try? JSONSerialization.jsonObject(
            with: data, options: JSONSerialization.ReadingOptions.allowFragments)
            as? [String: AnyObject]
        else {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onOpen", body: false)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onClose", body: true)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCancel", body: true)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onError", body: true)
          
          idScanResultCallback.onIDScanResultCancel()
          print("(Aziface SDK) Invalid JSON response.")
          return
        }

        guard let scanResultBlob = responseJSON["scanResultBlob"] as? String,
              let wasProcessed = responseJSON["wasProcessed"] as? Bool
        else {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onOpen", body: false)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onClose", body: true)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCancel", body: true)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onError", body: true)
          
          idScanResultCallback.onIDScanResultCancel()
          print("(Aziface SDK) Missing required keys 'scanResultBlob' or 'wasProcessed' in 'data'.")
          return
        }

        if wasProcessed == true {
          FaceTecCustomization.setIDScanResultScreenMessageOverrides(
            successFrontSide: self.theme.getPhotoIDScanMessage(
              "success", key: "frontSide", defaultMessage: "ID Scan Complete"),  // Successful scan of ID front-side (ID Types with no back-side).
            successFrontSideBackNext: self.theme.getPhotoIDScanMessage(
              "success", key: "frontSideBackNext", defaultMessage: "Front of ID\nScanned"),  // Successful scan of ID front-side (ID Types that do have a back-side).
            successFrontSideNFCNext: self.theme.getPhotoIDScanMessage(
              "success", key: "frontSideNFCNext", defaultMessage: "Front of ID\nScanned"),  // Successful scan of ID front-side (ID Types that do have NFC but do not have a back-side).
            successBackSide: self.theme.getPhotoIDScanMessage(
              "success", key: "backSide", defaultMessage: "ID Scan Complete"),  // Successful scan of the ID back-side (ID Types that do not have NFC).
            successBackSideNFCNext: self.theme.getPhotoIDScanMessage(
              "success", key: "backSideNFCNext", defaultMessage: "Back of ID\nScanned"),  // Successful scan of the ID back-side (ID Types that do have NFC).
            successPassport: self.theme.getPhotoIDScanMessage(
              "success", key: "passport", defaultMessage: "Passport Scan Complete"),  // Successful scan of a Passport that does not have NFC.
            successPassportNFCNext: self.theme.getPhotoIDScanMessage(
              "success", key: "passportNFCNext", defaultMessage: "Passport Scanned"),  // Successful scan of a Passport that does have NFC.
            successUserConfirmation: self.theme.getPhotoIDScanMessage(
              "success", key: "userConfirmation", defaultMessage: "Photo ID Scan\nComplete"),  // Successful upload of final IDScan containing User-Confirmed ID Text.
            successNFC: self.theme.getPhotoIDScanMessage(
              "success", key: "NFC", defaultMessage: "ID Scan Complete"),  // Successful upload of the scanned NFC chip information.
            retryFaceDidNotMatch: self.theme.getPhotoIDScanMessage(
              "retry", key: "faceDidNotMatch", defaultMessage: "Face Didn’t Match\nHighly Enough"),  // Case where a Retry is needed because the Face on the Photo ID did not Match the User's Face highly enough.
            retryIDNotFullyVisible: self.theme.getPhotoIDScanMessage(
              "retry", key: "IDNotFullyVisible", defaultMessage: "ID Document\nNot Fully Visible"),  // Case where a Retry is needed because a Full ID was not detected with high enough confidence.
            retryOCRResultsNotGoodEnough: self.theme.getPhotoIDScanMessage(
              "retry", key: "OCRResultsNotGoodEnough", defaultMessage: "ID Text Not Legible"),  // Case where a Retry is needed because the OCR did not produce good enough results and the User should Retry with a better capture.
            retryIDTypeNotSupported: self.theme.getPhotoIDScanMessage(
              "retry", key: "IDTypeNotSupported",
              defaultMessage: "ID Type Mismatch\nPlease Try Again"),  // Case where there is likely no OCR Template installed for the document the User is attempting to scan.
            skipOrErrorNFC: self.theme.getPhotoIDScanMessage(
              "skipOrErrorNFC", defaultMessage: "ID Details\nUploaded")  // Case where NFC Scan was skipped due to the user's interaction or an unexpected error.
          )

          self.success = idScanResultCallback.onIDScanResultProceedToNextStep(
            scanResultBlob: scanResultBlob)
        } else {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onOpen", body: false)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onClose", body: true)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCancel", body: true)
          AzifaceMobileSdk.emitter.sendEvent(withName: "onError", body: true)
          
          self.idScanResultCallback.onIDScanResultCancel()
          print("(Aziface SDK) AziFace SDK wasn't have to scan values processed!")
          return
        }
      })

    latestNetworkRequest.resume()
  }

  func urlSession(
    _ session: URLSession, task: URLSessionTask, didSendBodyData bytesSent: Int64,
    totalBytesSent: Int64, totalBytesExpectedToSend: Int64
  ) {
    let uploadProgress: Float = Float(totalBytesSent) / Float(totalBytesExpectedToSend)
    if idScanResultCallback != nil {
      idScanResultCallback.onIDScanUploadProgress(uploadedPercent: uploadProgress)
    }
  }

  func onFaceTecSDKCompletelyDone() {
    self.viewController.onComplete()
  }

  func isSuccess() -> Bool {
    return success
  }
}

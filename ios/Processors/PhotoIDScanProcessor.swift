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
  var success = false
  var data: NSDictionary!
  var latestNetworkRequest: URLSessionTask!
  var fromViewController: AziFaceViewController!
  var idScanResultCallback: FaceTecIDScanResultCallback!
  private let principalKey = "photoIdScanMessage"
  private let AziThemeUtils: ThemeUtils! = ThemeUtils()

  init(sessionToken: String, fromViewController: AziFaceViewController, data: NSDictionary) {
    self.fromViewController = fromViewController
    self.data = data
    super.init()

    FaceTecCustomization.setIDScanUploadMessageOverrides(
      frontSideUploadStarted: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "frontSideUploadStarted",
        defaultMessage: "Uploading\nEncrypted\nID Scan"),  // Upload of ID front-side has started.
      frontSideStillUploading: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "frontSideStillUploading",
        defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of ID front-side is still uploading to Server after an extended period of time.
      frontSideUploadCompleteAwaitingResponse: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "frontSideUploadCompleteAwaitingResponse",
        defaultMessage: "Upload Complete"),  // Upload of ID front-side to the Server is complete.
      frontSideUploadCompleteAwaitingProcessing: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "frontSideUploadCompleteAwaitingProcessing",
        defaultMessage: "Processing\nID Scan"),  // Upload of ID front-side is complete and we are waiting for the Server to finish processing and respond.
      backSideUploadStarted: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "backSideUploadStarted",
        defaultMessage: "Uploading\nEncrypted\nBack of ID"),  // Upload of ID back-side has started.
      backSideStillUploading: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "backSideStillUploading",
        defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of ID back-side is still uploading to Server after an extended period of time.
      backSideUploadCompleteAwaitingResponse: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "backSideUploadCompleteAwaitingResponse",
        defaultMessage: "Upload Complete"),  // Upload of ID back-side to Server is complete.
      backSideUploadCompleteAwaitingProcessing: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "backSideUploadCompleteAwaitingProcessing",
        defaultMessage: "Processing\nBack of ID"),  // Upload of ID back-side is complete and we are waiting for the Server to finish processing and respond.
      userConfirmedInfoUploadStarted: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "userConfirmedInfoUploadStarted",
        defaultMessage: "Uploading\nYour Confirmed Info"),  // Upload of User Confirmed Info has started.
      userConfirmedInfoStillUploading: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "userConfirmedInfoStillUploading",
        defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of User Confirmed Info is still uploading to Server after an extended period of time.
      userConfirmedInfoUploadCompleteAwaitingResponse: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "userConfirmedInfoUploadCompleteAwaitingResponse",
        defaultMessage: "Upload Complete"),  // Upload of User Confirmed Info to the Server is complete.
      userConfirmedInfoUploadCompleteAwaitingProcessing: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "userConfirmedInfoUploadCompleteAwaitingProcessing",
        defaultMessage: "Processing"),  // Upload of User Confirmed Info is complete and we are waiting for the Server to finish processing and respond.
      nfcUploadStarted: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "nfcUploadStarted",
        defaultMessage: "Uploading Encrypted\nNFC Details"),  // Upload of NFC Details has started.
      nfcStillUploading: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "nfcStillUploading",
        defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of NFC Details is still uploading to Server after an extended period of time.
      nfcUploadCompleteAwaitingResponse: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "nfcUploadCompleteAwaitingResponse",
        defaultMessage: "Upload Complete"),  // Upload of NFC Details to the Server is complete.
      nfcUploadCompleteAwaitingProcessing: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "nfcUploadCompleteAwaitingProcessing",
        defaultMessage: "Processing\nNFC Details"),  // Upload of NFC Details is complete and we are waiting for the Server to finish processing and respond.
      skippedNFCUploadStarted: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "skippedNFCUploadStarted",
        defaultMessage: "Uploading Encrypted\nID Details"),  // Upload of ID Details has started.
      skippedNFCStillUploading: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "skippedNFCStillUploading",
        defaultMessage: "Still Uploading...\nSlow Connection"),  // Upload of ID Details is still uploading to Server after an extended period of time.
      skippedNFCUploadCompleteAwaitingResponse: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "skippedNFCUploadCompleteAwaitingResponse",
        defaultMessage: "Upload Complete"),  // Upload of ID Details to the Server is complete.
      skippedNFCUploadCompleteAwaitingProcessing: self.AziThemeUtils.handleMessage(
        self.principalKey, child: "skippedNFCUploadCompleteAwaitingProcessing",
        defaultMessage: "Processing\nID Details")  // Upload of ID Details is complete and we are waiting for the Server to finish processing and respond.
    )

    AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: true)

    let idScanViewController = FaceTec.sdk.createSessionVC(
      idScanProcessorDelegate: self, sessionToken: sessionToken)

    FaceTecUtilities.getTopMostViewController()?.present(
      idScanViewController, animated: true, completion: nil)
  }

  func processIDScanWhileFaceTecSDKWaits(
    idScanResult: FaceTecIDScanResult, idScanResultCallback: FaceTecIDScanResultCallback
  ) {
    fromViewController.setLatestIDScanResult(idScanResult: idScanResult)

    self.idScanResultCallback = idScanResultCallback

    if idScanResult.status != FaceTecIDScanStatus.success {
      if latestNetworkRequest != nil {
        latestNetworkRequest.cancel()
      }

      AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
      idScanResultCallback.onIDScanResultCancel()
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

    var request = Config.makeRequest(url: "/idscan-only", httpMethod: "POST")
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
            print(
              "Exception raised while attempting HTTPS call. Status code: \(httpResponse.statusCode)"
            )
            AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
            idScanResultCallback.onIDScanResultCancel()
            return
          }
        }

        if let error = error {
          print("Exception raised while attempting HTTPS call.")
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
          idScanResultCallback.onIDScanResultCancel()
          return
        }

        guard let data = data else {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
          idScanResultCallback.onIDScanResultCancel()
          return
        }

        guard
          let responseJSON = try? JSONSerialization.jsonObject(
            with: data, options: JSONSerialization.ReadingOptions.allowFragments)
            as? [String: AnyObject]
        else {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
          idScanResultCallback.onIDScanResultCancel()
          return
        }

        guard let scanResultBlob = responseJSON["scanResultBlob"] as? String,
          let wasProcessed = responseJSON["wasProcessed"] as? Bool
        else {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
          idScanResultCallback.onIDScanResultCancel()
          return
        }

        if wasProcessed == true {
          FaceTecCustomization.setIDScanResultScreenMessageOverrides(
            successFrontSide: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "successFrontSide", defaultMessage: "ID Scan Complete"),  // Successful scan of ID front-side (ID Types with no back-side).
            successFrontSideBackNext: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "successFrontSideBackNext",
              defaultMessage: "Front of ID\nScanned"),  // Successful scan of ID front-side (ID Types that do have a back-side).
            successFrontSideNFCNext: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "successFrontSideNFCNext",
              defaultMessage: "Front of ID\nScanned"),  // Successful scan of ID front-side (ID Types that do have NFC but do not have a back-side).
            successBackSide: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "successBackSide", defaultMessage: "ID Scan Complete"),  // Successful scan of the ID back-side (ID Types that do not have NFC).
            successBackSideNFCNext: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "successBackSideNFCNext",
              defaultMessage: "Back of ID\nScanned"),  // Successful scan of the ID back-side (ID Types that do have NFC).
            successPassport: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "successPassport", defaultMessage: "Passport Scan Complete"),  // Successful scan of a Passport that does not have NFC.
            successPassportNFCNext: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "successPassportNFCNext", defaultMessage: "Passport Scanned"
            ),  // Successful scan of a Passport that does have NFC.
            successUserConfirmation: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "successUserConfirmation",
              defaultMessage: "Photo ID Scan\nComplete"),  // Successful upload of final IDScan containing User-Confirmed ID Text.
            successNFC: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "successNFC", defaultMessage: "ID Scan Complete"),  // Successful upload of the scanned NFC chip information.
            retryFaceDidNotMatch: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "retryFaceDidNotMatch",
              defaultMessage: "Face Didn’t Match\nHighly Enough"),  // Case where a Retry is needed because the Face on the Photo ID did not Match the User's Face highly enough.
            retryIDNotFullyVisible: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "retryIDNotFullyVisible",
              defaultMessage: "ID Document\nNot Fully Visible"),  // Case where a Retry is needed because a Full ID was not detected with high enough confidence.
            retryOCRResultsNotGoodEnough: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "retryOCRResultsNotGoodEnough",
              defaultMessage: "ID Text Not Legible"),  // Case where a Retry is needed because the OCR did not produce good enough results and the User should Retry with a better capture.
            retryIDTypeNotSupported: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "retryIDTypeNotSupported",
              defaultMessage: "ID Type Mismatch\nPlease Try Again"),  // Case where there is likely no OCR Template installed for the document the User is attempting to scan.
            skipOrErrorNFC: self.AziThemeUtils.handleMessage(
              self.principalKey, child: "skipOrErrorNFC", defaultMessage: "ID Details\nUploaded")  // Case where NFC Scan was skipped due to the user's interaction or an unexpected error.
          )

          self.success = idScanResultCallback.onIDScanResultProceedToNextStep(
            scanResultBlob: scanResultBlob)
        } else {
          AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
          idScanResultCallback.onIDScanResultCancel()
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
    self.fromViewController.onComplete()
  }

  func isSuccess() -> Bool {
    return success
  }
}

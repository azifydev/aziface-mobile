//
//  PhotoIDMatchProcessor.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 08/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import UIKit
import Foundation
import FaceTecSDK

class PhotoIDMatchProcessor: NSObject, Processor, FaceTecFaceScanProcessorDelegate, FaceTecIDScanProcessorDelegate, URLSessionTaskDelegate {
    var success = false
    var faceScanWasSuccessful = false
    var latestExternalDatabaseRefID: String!
    var data: NSDictionary!
    var latestNetworkRequest: URLSessionTask!
    var fromViewController: AziFaceViewController!
    var faceScanResultCallback: FaceTecFaceScanResultCallback!
    var idScanResultCallback: FaceTecIDScanResultCallback!
    private let principalKey = "photoIdMatchMessage";
    private let AziThemeUtils: ThemeUtils! = ThemeUtils();


    init(sessionToken: String, fromViewController: AziFaceViewController, data: NSDictionary) {
        self.fromViewController = fromViewController
        self.latestExternalDatabaseRefID = self.fromViewController.getLatestExternalDatabaseRefID()
        self.data = data
        super.init()

        FaceTecCustomization.setIDScanUploadMessageOverrides(
            frontSideUploadStarted: self.AziThemeUtils.handleMessage(self.principalKey, child: "frontSideUploadStarted", defaultMessage: "Uploading\nEncrypted\nID Scan"),                // Upload of ID front-side has started.
            frontSideStillUploading: self.AziThemeUtils.handleMessage(self.principalKey, child: "frontSideStillUploading", defaultMessage: "Still Uploading...\nSlow Connection"),         // Upload of ID front-side is still uploading to Server after an extended period of time.
            frontSideUploadCompleteAwaitingResponse: self.AziThemeUtils.handleMessage(self.principalKey, child: "frontSideUploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),             // Upload of ID front-side to the Server is complete.
            frontSideUploadCompleteAwaitingProcessing: self.AziThemeUtils.handleMessage(self.principalKey, child: "frontSideUploadCompleteAwaitingProcessing", defaultMessage: "Processing\nID Scan"),       // Upload of ID front-side is complete and we are waiting for the Server to finish processing and respond.
            backSideUploadStarted: self.AziThemeUtils.handleMessage(self.principalKey, child: "backSideUploadStarted", defaultMessage: "Uploading\nEncrypted\nBack of ID"),              // Upload of ID back-side has started.
            backSideStillUploading: self.AziThemeUtils.handleMessage(self.principalKey, child: "backSideStillUploading", defaultMessage: "Still Uploading...\nSlow Connection"),          // Upload of ID back-side is still uploading to Server after an extended period of time.
            backSideUploadCompleteAwaitingResponse: self.AziThemeUtils.handleMessage(self.principalKey, child: "backSideUploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),              // Upload of ID back-side to Server is complete.
            backSideUploadCompleteAwaitingProcessing: self.AziThemeUtils.handleMessage(self.principalKey, child: "backSideUploadCompleteAwaitingProcessing", defaultMessage: "Processing\nBack of ID"),     // Upload of ID back-side is complete and we are waiting for the Server to finish processing and respond.
            userConfirmedInfoUploadStarted: self.AziThemeUtils.handleMessage(self.principalKey, child: "userConfirmedInfoUploadStarted", defaultMessage: "Uploading\nYour Confirmed Info"),       // Upload of User Confirmed Info has started.
            userConfirmedInfoStillUploading: self.AziThemeUtils.handleMessage(self.principalKey, child: "userConfirmedInfoStillUploading", defaultMessage: "Still Uploading...\nSlow Connection"), // Upload of User Confirmed Info is still uploading to Server after an extended period of time.
            userConfirmedInfoUploadCompleteAwaitingResponse: self.AziThemeUtils.handleMessage(self.principalKey, child: "userConfirmedInfoUploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),     // Upload of User Confirmed Info to the Server is complete.
            userConfirmedInfoUploadCompleteAwaitingProcessing: self.AziThemeUtils.handleMessage(self.principalKey, child: "userConfirmedInfoUploadCompleteAwaitingProcessing", defaultMessage: "Processing"),        // Upload of User Confirmed Info is complete and we are waiting for the Server to finish processing and respond.
            nfcUploadStarted: self.AziThemeUtils.handleMessage(self.principalKey, child: "nfcUploadStarted", defaultMessage: "Uploading Encrypted\nNFC Details"),                   // Upload of NFC Details has started.
            nfcStillUploading: self.AziThemeUtils.handleMessage(self.principalKey, child: "nfcStillUploading", defaultMessage: "Still Uploading...\nSlow Connection"),               // Upload of NFC Details is still uploading to Server after an extended period of time.
            nfcUploadCompleteAwaitingResponse: self.AziThemeUtils.handleMessage(self.principalKey, child: "nfcUploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),                   // Upload of NFC Details to the Server is complete.
            nfcUploadCompleteAwaitingProcessing: self.AziThemeUtils.handleMessage(self.principalKey, child: "nfcUploadCompleteAwaitingProcessing", defaultMessage: "Processing\nNFC Details"),         // Upload of NFC Details is complete and we are waiting for the Server to finish processing and respond.
            skippedNFCUploadStarted: self.AziThemeUtils.handleMessage(self.principalKey, child: "skippedNFCUploadStarted", defaultMessage: "Uploading Encrypted\nID Details"),             // Upload of ID Details has started.
            skippedNFCStillUploading: self.AziThemeUtils.handleMessage(self.principalKey, child: "skippedNFCStillUploading", defaultMessage: "Still Uploading...\nSlow Connection"),        // Upload of ID Details is still uploading to Server after an extended period of time.
            skippedNFCUploadCompleteAwaitingResponse: self.AziThemeUtils.handleMessage(self.principalKey, child: "skippedNFCUploadCompleteAwaitingResponse", defaultMessage: "Upload Complete"),            // Upload of ID Details to the Server is complete.
            skippedNFCUploadCompleteAwaitingProcessing: self.AziThemeUtils.handleMessage(self.principalKey, child: "skippedNFCUploadCompleteAwaitingProcessing", defaultMessage: "Processing\nID Details")    // Upload of ID Details is complete and we are waiting for the Server to finish processing and respond.
        );

        AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: true);

        let idScanViewController = FaceTec.sdk.createSessionVC(faceScanProcessorDelegate: self, idScanProcessorDelegate: self, sessionToken: sessionToken)

        FaceTecUtilities.getTopMostViewController()?.present(idScanViewController, animated: true, completion: nil)
    }

    func processSessionWhileFaceTecSDKWaits(sessionResult: FaceTecSessionResult, faceScanResultCallback: FaceTecFaceScanResultCallback) {
        fromViewController.setLatestSessionResult(sessionResult: sessionResult)

        self.faceScanResultCallback = faceScanResultCallback

        if sessionResult.status != FaceTecSessionStatus.sessionCompletedSuccessfully {
            if latestNetworkRequest != nil {
                latestNetworkRequest.cancel()
            }

            AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false);
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
        parameters["externalDatabaseRefID"] = fromViewController.getLatestExternalDatabaseRefID()
        if let data = self.data {
            parameters["data"] = data
        }

        // route and request configuration
        let route = "/Process/" + Config.ProcessId + "/Enrollment3d"
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

                guard let responseJSON = try JSONSerialization.jsonObject(with: data, options: []) as? [String: AnyObject] else {
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
                        let wasProcessed = responseData["wasProcessed"] as? Int else {
                        print("Missing required keys 'scanResultBlob' or 'wasProcessed' in 'data'.")
                        self.faceScanResultCallback.onFaceScanResultCancel()
                        return
                    }

                    if wasProcessed == 1 {
                        FaceTecCustomization.setOverrideResultScreenSuccessMessage("Face Scanned\n3D Liveness Proven")
                        self.success = self.faceScanResultCallback.onFaceScanGoToNextStep(scanResultBlob: scanResultBlob)
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

            let message = self.AziThemeUtils.handleMessage(self.principalKey, child: "uploadMessageIos", defaultMessage: "Still Uploading...");
            let uploadMessage:NSMutableAttributedString = NSMutableAttributedString.init(string: message);
            faceScanResultCallback.onFaceScanUploadMessageOverride(uploadMessageOverride: uploadMessage);
        }
    }

func processIDScanWhileFaceTecSDKWaits(idScanResult: FaceTecIDScanResult, idScanResultCallback: FaceTecIDScanResultCallback) {
    fromViewController.setLatestIDScanResult(idScanResult: idScanResult)

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

    let route = "/Process/" + Config.ProcessId + "/Match3d2dIdScan"
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

        guard let responseJSON = try? JSONSerialization.jsonObject(with: data, options: []) as? [String: AnyObject] else {
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
            let wasProcessed = responseData["wasProcessed"] as? Bool else {
            print("Missing required keys 'scanResultBlob' or 'wasProcessed' in 'data'.")
            idScanResultCallback.onIDScanResultCancel()
            return
        }

        if wasProcessed {
            FaceTecCustomization.setIDScanResultScreenMessageOverrides(
                successFrontSide: self.AziThemeUtils.handleMessage(self.principalKey, child: "successFrontSide", defaultMessage: "ID Scan Complete"),
                successFrontSideBackNext: self.AziThemeUtils.handleMessage(self.principalKey, child: "successFrontSideBackNext", defaultMessage: "Front of ID\nScanned"),
                successFrontSideNFCNext: self.AziThemeUtils.handleMessage(self.principalKey, child: "successFrontSideNFCNext", defaultMessage: "Front of ID\nScanned"),
                successBackSide: self.AziThemeUtils.handleMessage(self.principalKey, child: "successBackSide", defaultMessage: "ID Scan Complete"),
                successBackSideNFCNext: self.AziThemeUtils.handleMessage(self.principalKey, child: "successBackSideNFCNext", defaultMessage: "Back of ID\nScanned"),
                successPassport: self.AziThemeUtils.handleMessage(self.principalKey, child: "successPassport", defaultMessage: "Passport Scan Complete"),
                successPassportNFCNext: self.AziThemeUtils.handleMessage(self.principalKey, child: "successPassportNFCNext", defaultMessage: "Passport Scanned"),
                successUserConfirmation: self.AziThemeUtils.handleMessage(self.principalKey, child: "successUserConfirmation", defaultMessage: "Photo ID Scan\nComplete"),
                successNFC: self.AziThemeUtils.handleMessage(self.principalKey, child: "successNFC", defaultMessage: "ID Scan Complete"),
                retryFaceDidNotMatch: self.AziThemeUtils.handleMessage(self.principalKey, child: "retryFaceDidNotMatch", defaultMessage: "Face Didn’t Match\nHighly Enough"),
                retryIDNotFullyVisible: self.AziThemeUtils.handleMessage(self.principalKey, child: "retryIDNotFullyVisible", defaultMessage: "ID Document\nNot Fully Visible"),
                retryOCRResultsNotGoodEnough: self.AziThemeUtils.handleMessage(self.principalKey, child: "retryOCRResultsNotGoodEnough", defaultMessage: "ID Text Not Legible"),
                retryIDTypeNotSupported: self.AziThemeUtils.handleMessage(self.principalKey, child: "retryIDTypeNotSupported", defaultMessage: "ID Type Mismatch\nPlease Try Again"),
                skipOrErrorNFC: self.AziThemeUtils.handleMessage(self.principalKey, child: "skipOrErrorNFC", defaultMessage: "ID Details\nUploaded")
            )

            self.success = idScanResultCallback.onIDScanResultProceedToNextStep(scanResultBlob: scanResultBlob)
        } else {
            print("Face scan was not processed successfully.")
            AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false)
            idScanResultCallback.onIDScanResultCancel()
        }
    }

    latestNetworkRequest?.resume()
}


    func urlSession(_ session: URLSession, task: URLSessionTask, didSendBodyData bytesSent: Int64, totalBytesSent: Int64, totalBytesExpectedToSend: Int64) {
        let uploadProgress: Float = Float(totalBytesSent) / Float(totalBytesExpectedToSend)
        if idScanResultCallback != nil {
            idScanResultCallback.onIDScanUploadProgress(uploadedPercent: uploadProgress)
        } else {
            faceScanResultCallback.onFaceScanUploadProgress(uploadedPercent: uploadProgress)
        }
    }

    func onFaceTecSDKCompletelyDone() {
        self.fromViewController.onComplete()
    }

    func isSuccess() -> Bool {
        return success
    }
}


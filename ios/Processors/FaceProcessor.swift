//
//  LivenessCheckProcessor.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias 08/03/2024.
//  Copyright © 2024 Azify. All rights reserved.
//

import UIKit
import Foundation
import FaceTecSDK

class FaceProcessor: NSObject, Processor, FaceTecFaceScanProcessorDelegate, URLSessionTaskDelegate {
    private let key: String!
    private let faceConfig: FaceConfig!
    private let CapThemeUtils: ThemeUtils! = ThemeUtils();
    var success = false
    var latestNetworkRequest: URLSessionTask!
    var fromViewController: AziFaceViewController!
    var faceScanResultCallback: FaceTecFaceScanResultCallback!

    init(sessionToken: String, fromViewController: AziFaceViewController, faceConfig: FaceConfig) {
        self.fromViewController = fromViewController
        self.faceConfig = faceConfig
        self.key = faceConfig.getKey()
        super.init()

        AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: true);

        let faceViewController = FaceTec.sdk.createSessionVC(faceScanProcessorDelegate: self, sessionToken: sessionToken)

        FaceTecUtilities.getTopMostViewController()?.present(faceViewController, animated: true, completion: nil)
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

        var parameters: [String : Any] = [:]
        let extraParameters: [String: Any]? = self.faceConfig.getParameters()
        if extraParameters != nil {
            parameters["data"] = extraParameters
        }
        parameters["faceScan"] = sessionResult.faceScanBase64
        parameters["auditTrailImage"] = sessionResult.auditTrailCompressedBase64![0]
        parameters["lowQualityAuditTrailImage"] = sessionResult.lowQualityAuditTrailCompressedBase64![0]
        
        let hasExternalDatabaseRefID: Bool = self.faceConfig.getHasExternalDatabaseRefID()
        if hasExternalDatabaseRefID {
            parameters["externalDatabaseRefID"] = fromViewController.getLatestExternalDatabaseRefID()
        }

        let endpoint: String? = self.faceConfig.getEndpoint()
        var request = Config.makeRequest(url: endpoint ?? "", httpMethod: "POST")
        request.httpBody = try! JSONSerialization.data(withJSONObject: parameters, options: JSONSerialization.WritingOptions(rawValue: 0))

        let session = URLSession(configuration: URLSessionConfiguration.default, delegate: self, delegateQueue: OperationQueue.main)
        latestNetworkRequest = session.dataTask(with: request as URLRequest, completionHandler: { data, response, error in
            if let httpResponse = response as? HTTPURLResponse {
                if httpResponse.statusCode < 200 || httpResponse.statusCode >= 299 {
                    AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false);
                    faceScanResultCallback.onFaceScanResultCancel()
                    return
                }
            }

            if let error = error {
                AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false);
                faceScanResultCallback.onFaceScanResultCancel()
                return
            }

            guard let data = data else {
                AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false);
                faceScanResultCallback.onFaceScanResultCancel()
                return
            }

            guard let responseJSON = try? JSONSerialization.jsonObject(with: data, options: JSONSerialization.ReadingOptions.allowFragments) as? [String: AnyObject] else {
                AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false);
                faceScanResultCallback.onFaceScanResultCancel()
                return
            }

            guard let scanResultBlob = responseJSON["scanResultBlob"] as? String,
                  let wasProcessed = responseJSON["wasProcessed"] as? Bool else {
                AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false);
                faceScanResultCallback.onFaceScanResultCancel()
                return;
            }

            if wasProcessed == true {
                let successMessage: String? = self.faceConfig.getSuccessMessage()
                let message = self.CapThemeUtils.handleMessage(self.key, child: "successMessage", defaultMessage: successMessage ?? "");
                FaceTecCustomization.setOverrideResultScreenSuccessMessage(message);

                self.success = faceScanResultCallback.onFaceScanGoToNextStep(scanResultBlob: scanResultBlob);
            } else {
                AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false);
                faceScanResultCallback.onFaceScanResultCancel()
                return;
            }
        })

        latestNetworkRequest.resume()

        DispatchQueue.main.asyncAfter(deadline: .now() + 6) {
            if self.latestNetworkRequest.state == .completed { return }

            let uploadMessage: String = self.faceConfig.getUploadMessage()
            let message = self.CapThemeUtils.handleMessage(self.key, child: "uploadMessageIos", defaultMessage: uploadMessage);
            let uploadMessageOverride: NSMutableAttributedString = NSMutableAttributedString.init(string: message);
            faceScanResultCallback.onFaceScanUploadMessageOverride(uploadMessageOverride: uploadMessageOverride);
        }
    }

    func urlSession(_ session: URLSession, task: URLSessionTask, didSendBodyData bytesSent: Int64, totalBytesSent: Int64, totalBytesExpectedToSend: Int64) {
        let uploadProgress: Float = Float(totalBytesSent) / Float(totalBytesExpectedToSend)
        faceScanResultCallback.onFaceScanUploadProgress(uploadedPercent: uploadProgress)
    }

    func onFaceTecSDKCompletelyDone() {
        self.fromViewController.onComplete();
    }

    func isSuccess() -> Bool {
        return success
    }
}
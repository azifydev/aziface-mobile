//
//  FaceTecViewController.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 08/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import Foundation
import UIKit
import FaceTecSDK
import LocalAuthentication

class AziFaceViewController: UIViewController, URLSessionDelegate {
    var isSuccess: Bool! = false
    var latestExternalDatabaseRefID: String = ""
    var latestSessionResult: FaceTecSessionResult!
    var latestIDScanResult: FaceTecIDScanResult!
    var processorRevolver: RCTPromiseResolveBlock!;
    var processorRejecter: RCTPromiseRejectBlock!;
    var latestProcessor: Processor!
    var utils: FaceTecUtilities!
    @IBOutlet weak var themeTransitionText: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    func onLivenessCheck(_ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        setProcessorPromise(resolve, rejecter: reject);
        getSessionToken() { sessionToken in
            self.resetLatestResults()
            self.latestProcessor = LivenessCheckProcessor(sessionToken: sessionToken, fromViewController: self, data: data)
        }
    }

    func onEnrollUser(_ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        setProcessorPromise(resolve, rejecter: reject);
        getSessionToken() { sessionToken in
            self.resetLatestResults()
            self.latestExternalDatabaseRefID = "ios_azify_app_" + UUID().uuidString
            self.latestProcessor = EnrollmentProcessor(sessionToken: sessionToken, fromViewController: self, data: data)
        }
    }

    func onAuthenticateUser(_ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        setProcessorPromise(resolve, rejecter: reject);
        getSessionToken() { sessionToken in
            self.resetLatestResults()
            self.latestProcessor = AuthenticateProcessor(sessionToken: sessionToken, fromViewController: self, data: data)
        }
    }

    func onPhotoIDMatch(_ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        setProcessorPromise(resolve, rejecter: reject);
        getSessionToken() { sessionToken in
            self.resetLatestResults()
            self.latestExternalDatabaseRefID = "ios_azify_app_" + UUID().uuidString
            self.latestProcessor = PhotoIDMatchProcessor(sessionToken: sessionToken, fromViewController: self, data: data)
        }
    }

    func onPhotoIDScan(_ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        setProcessorPromise(resolve, rejecter: reject);
        getSessionToken() { sessionToken in
            self.resetLatestResults()
            self.latestProcessor = PhotoIDScanProcessor(sessionToken: sessionToken, fromViewController: self, data: data)
        }
    }

    func onComplete() {
        UIApplication.shared.statusBarStyle = FaceTecUtilities.DefaultStatusBarStyle;

        if self.latestProcessor != nil {
            self.isSuccess = self.latestProcessor.isSuccess();
        }

        AzifaceMobileSdk.emitter.sendEvent(withName: "onCloseModal", body: false);

        if !self.isSuccess {
            self.latestExternalDatabaseRefID = "";
            self.processorRejecter("AziFace SDK values were not processed!", "AziFaceValuesWereNotProcessed", nil);
        } else {
            self.processorRevolver(self.isSuccess);
        }
    }

    func setLatestSessionResult(sessionResult: FaceTecSessionResult) {
        latestSessionResult = sessionResult
    }

    func setLatestIDScanResult(idScanResult: FaceTecIDScanResult) {
        latestIDScanResult = idScanResult
    }

    func resetLatestResults() {
        latestSessionResult = nil
        latestIDScanResult = nil
    }

    func getLatestExternalDatabaseRefID() -> String {
        return latestExternalDatabaseRefID;
    }

    func setProcessorPromise(_ resolver: @escaping RCTPromiseResolveBlock, rejecter: @escaping RCTPromiseRejectBlock) {
        self.processorRevolver = resolver;
        self.processorRejecter = rejecter;
    }

    func getSessionToken(sessionTokenCallback: @escaping (String) -> ()) {
        let request = Config.makeRequest(url:"/Process/Session/Token", httpMethod: "GET");
        let session = URLSession(configuration: URLSessionConfiguration.default, delegate: self, delegateQueue: OperationQueue.main)
        let task = session.dataTask(with: request as URLRequest, completionHandler: { data, response, error in
            guard let data = data else {
                print("Exception raised while attempting HTTPS call.")
               
                if self.processorRejecter != nil {
                    self.processorRejecter("Exception raised while attempting HTTPS call.", "HTTPSError", nil);
                }
                return
            }
        
            if let responseJSONObj = try? JSONSerialization.jsonObject(with: data, options: JSONSerialization.ReadingOptions.allowFragments) as! [String: AnyObject] {
                
                if let dataObj = responseJSONObj["data"] as? [String: AnyObject] {
                        if let sessionToken = dataObj["sessionToken"] as? String {
                            sessionTokenCallback(sessionToken)
                            return
                        } else {
                            print("Session token not found.")
                        }
                    } else {
                        print("Data object not found.")
                    }
                    
                    print("Exception raised while attempting HTTPS call.")
                    if let processorRejecter = self.processorRejecter {
                        processorRejecter("Exception raised while attempting HTTPS call.", "HTTPSError", nil)
                    }
            }
        })
           task.resume()
    }
}

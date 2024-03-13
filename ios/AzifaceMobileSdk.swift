//
//  AzifaceMobileSdk.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 13/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import Foundation
import FaceTecSDK
import LocalAuthentication

@objc(AzifaceMobileSdk)
class AzifaceMobileSdk: RCTEventEmitter, URLSessionDelegate {
    private let initializationMessage = "Aziface SDK has not been initialized!"
    public static var emitter: RCTEventEmitter!
    var aziFaceViewController: AziFaceViewController!
    var isInitialized: Bool = false;
    
    override init() {
        super.init();
        AzifaceMobileSdk.emitter = self;
    }
    
    private func isDeveloperMode(_ params: NSDictionary) -> Bool {
        if let isDevMode = params["isDeveloperMode"] as? Bool {
            return isDevMode
        } else {
            return false
        }
    }
    
    private func handleAziFaceConfiguration(_ params: NSDictionary, headers: NSDictionary) {
        Config.setDevice(params.value(forKey: "device") as! String);
        Config.setUrl(params.value(forKey: "url") as! String);
        Config.setKey(params.value(forKey: "key") as! String);
        Config.setProductionKeyText(params.value(forKey: "productionKey") as! String);
        Config.setHeaders(headers);
    }
    
    @objc func initializeSdk(_ params: NSDictionary, headers: NSDictionary, callback: @escaping RCTResponseSenderBlock) -> Void {
        DispatchQueue.main.async {
            self.aziFaceViewController = AziFaceViewController();
            
            if params.count == 0 {
                self.isInitialized = false;
                callback([false]);
                return;
            }
            
            self.handleAziFaceConfiguration(params, headers: headers);
            
            if (Config.hasConfig()) {
                Config.initialize(self.isDeveloperMode(params), completion: { initializationSuccessful in
                    self.isInitialized = initializationSuccessful;
                    callback([initializationSuccessful]);
                })
            } else {
                self.isInitialized = false;
                callback([false]);
            }

            self.handleTheme(Config.Theme);
        }
    }
    
    @objc override static func requiresMainQueueSetup() -> Bool {
        return true;
    }
    
    @objc override func constantsToExport() -> [AnyHashable : Any]! {
        return nil;
    }
    
    @objc override func startObserving() {}
    
    @objc override func stopObserving() {}
    
    @objc override func supportedEvents() -> [String]! {
        return ["onCloseModal"];
    }
    
    @objc func handleFaceUser(_ config: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        if self.isInitialized {
            self.aziFaceViewController.onFaceUser(config, resolve: resolve, reject: reject);
        } else {
            return reject(self.initializationMessage, "AziFaceHasNotBeenInitialized", nil);
        }
    }
    
    @objc func handlePhotoIDMatch(_ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        if self.isInitialized {
            self.aziFaceViewController.onPhotoIDMatch(data, resolve: resolve, reject: reject);
        } else {
            return reject(self.initializationMessage, "AziFaceHasNotBeenInitialized", nil);
        }
    }
    
    @objc func handlePhotoIDScan(_ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        if self.isInitialized {
            self.aziFaceViewController.onPhotoIDScan(data, resolve: resolve, reject: reject);
        } else {
            return reject(self.initializationMessage, "AziFaceHasNotBeenInitialized", nil);
        }
    }
    
    @objc func handleTheme(_ options: NSDictionary?) {
        ThemeHelpers.setAppTheme(options);
    }
}

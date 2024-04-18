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
    public static var emitter: RCTEventEmitter!
    var AziFaceViewController: AziFaceViewController!
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
            self.AziFaceViewController = aziface_mobile_sdk.AziFaceViewController();
            self.handleTheme(Config.Theme);
            
            if params.count == 0 {
                self.isInitialized = false;
                callback([false]);
                print("No parameters provided!");
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
                print("AziFace SDK Configuration doesn't exists!");
            }
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
    
    @objc func handleLivenessCheck(_ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        if self.isInitialized {
            self.AziFaceViewController.onLivenessCheck(data, resolve: resolve, reject: reject);
        } else {
            print("AziFace SDK has not been initialized!");
            return reject("AziFace SDK has not been initialized!", "AziFaceHasNotBeenInitialized", nil);
        }
    }
    
    @objc func handleEnrollUser(_ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        if self.isInitialized {
            self.AziFaceViewController.onEnrollUser(data, resolve: resolve, reject: reject);
        } else {
            print("AziFace SDK has not been initialized!");
            return reject("AziFace SDK has not been initialized!", "AziFaceHasNotBeenInitialized", nil);
        }
    }
    
    @objc func handleAuthenticateUser(_ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        if self.isInitialized {
            self.AziFaceViewController.onAuthenticateUser(data, resolve: resolve, reject: reject);
        } else {
            print("AziFace SDK has not been initialized!");
            return reject("AziFace SDK has not been initialized!", "AziFaceHasNotBeenInitialized", nil);
        }
    }
    
    @objc func handlePhotoIDMatch(_ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        if self.isInitialized {
            self.AziFaceViewController.onPhotoIDMatch(data, resolve: resolve, reject: reject);
        } else {
            print("AziFace SDK has not been initialized!");
            return reject("AziFace SDK has not been initialized!", "AziFaceHasNotBeenInitialized", nil);
        }
    }
    
    @objc func handlePhotoIDScan(_ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
        if self.isInitialized {
            self.AziFaceViewController.onPhotoIDScan(data, resolve: resolve, reject: reject);
        } else {
            print("AziFace SDK has not been initialized!");
            return reject("AziFace SDK has not been initialized!", "AziFaceHasNotBeenInitialized", nil);
        }
    }
    
    @objc func handleTheme(_ options: NSDictionary?) {
        ThemeHelpers.setAppTheme(options);
    }
}

//
//  AzifaceMobileSdk.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 13/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import FaceTecSDK
import Foundation
import LocalAuthentication

@objc(AzifaceMobileSdk)
class AzifaceMobileSdk: RCTEventEmitter, URLSessionDelegate {
  public static var emitter: RCTEventEmitter!
  public var controller: AziFaceViewController
  public var isInitialized: Bool = false
  
  override init() {
    self.controller = AziFaceViewController()
    
    super.init()
    
    AzifaceMobileSdk.emitter = self
  }
  
  @objc func initializeSdk(
    _ params: NSDictionary, headers: NSDictionary, callback: @escaping RCTResponseSenderBlock
  ) {
    DispatchQueue.main.async {
      self.setTheme(Theme.Style)
      
      if params.count == 0 {
        self.isInitialized = false
        callback([false])
        print("No parameters provided!")
        return
      }
      
      let commonParams = CommonParams(params: params)
      commonParams.setHeaders(headers)
      commonParams.build()
      
      if Config.hasConfig() {
        Config.initialize(
          commonParams.isDeveloper(),
          completion: { initializationSuccessful in
            self.isInitialized = initializationSuccessful
            callback([initializationSuccessful])
          })
      } else {
        self.isInitialized = false
        callback([false])
        print("AziFace SDK Configuration doesn't exists!")
      }
    }
  }
  
  @objc override static func requiresMainQueueSetup() -> Bool {
    return true
  }
  
  @objc override func constantsToExport() -> [AnyHashable: Any]! {
    return nil
  }
  
  @objc override func startObserving() {}
  
  @objc override func stopObserving() {}
  
  @objc override func supportedEvents() -> [String]! {
    return ["onCloseModal"]
  }
  
  @objc func setTheme(_ options: NSDictionary?) {
    Theme.setAppTheme(options)
  }
  
  @objc func handleLivenessCheck(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    let commonParams = CommonParams(params: data)
    commonParams.buildProcessorPathURL()
    
    if self.isInitialized {
      self.controller.onLivenessCheck(data, resolve: resolve, reject: reject)
    } else {
      return reject("AziFace SDK has not been initialized!", "AziFaceHasNotBeenInitialized", nil)
    }
  }
  
  @objc func handleEnrollUser(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    let commonParams = CommonParams(params: data)
    commonParams.buildProcessorPathURL()
    
    if self.isInitialized {
      self.controller.onEnrollUser(data, resolve: resolve, reject: reject)
    } else {
      return reject("AziFace SDK has not been initialized!", "AziFaceHasNotBeenInitialized", nil)
    }
  }
  
  @objc func handleAuthenticateUser(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    let commonParams = CommonParams(params: data)
    commonParams.buildProcessorPathURL()
    
    if self.isInitialized {
      self.controller.onAuthenticateUser(data, resolve: resolve, reject: reject)
    } else {
      return reject("AziFace SDK has not been initialized!", "AziFaceHasNotBeenInitialized", nil)
    }
  }
  
  @objc func handlePhotoIDMatch(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    let commonParams = CommonParams(params: data)
    commonParams.buildProcessorPathURL()
    
    if self.isInitialized {
      self.controller.onPhotoIDMatch(data, resolve: resolve, reject: reject)
    } else {
      return reject("AziFace SDK has not been initialized!", "AziFaceHasNotBeenInitialized", nil)
    }
  }
  
  @objc func handlePhotoIDScan(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    let commonParams = CommonParams(params: data)
    commonParams.buildProcessorPathURL()
    
    if self.isInitialized {
      self.controller.onPhotoIDScan(data, resolve: resolve, reject: reject)
    } else {
      return reject("AziFace SDK has not been initialized!", "AziFaceHasNotBeenInitialized", nil)
    }
  }
}

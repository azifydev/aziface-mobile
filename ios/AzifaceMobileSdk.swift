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

  @objc func initialize(
    _ params: NSDictionary,
    headers: NSDictionary,
    resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      self.setTheme(Theme.Style)

      let paremeters = CommonParams(params: params)

      if paremeters.isNull() {
        self.isInitialized = false
        AzifaceMobileSdk.emitter.sendEvent(withName: "onInitialize", body: self.isInitialized)
        return reject("Parameters aren't provided", "ParamsNotProvided", nil)
      }

      paremeters.setHeaders(headers)
      paremeters.build()

      if Config.hasConfig() {
        Config.initialize(
          paremeters.isDeveloper(),
          completion: { initializationSuccessful in
            self.isInitialized = initializationSuccessful
            AzifaceMobileSdk.emitter.sendEvent(withName: "onInitialize", body: initializationSuccessful)
            if self.isInitialized {
              return resolve(true)
            }
            return reject("Initialization failed", "InitializationFailed", nil)
          })
      } else {
        self.isInitialized = false
        AzifaceMobileSdk.emitter.sendEvent(withName: "onInitialize", body: self.isInitialized)
        return reject("Configuration aren't provided", "ConfigNotProvided", nil)
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
    return ["onOpen", "onClose", "onCancel", "onError", "onInitialize"]
  }

  @objc func setTheme(_ options: NSDictionary?) {
    Theme.setAppTheme(options)
  }

  @objc func handleLivenessCheck(
    _ data: NSDictionary,
    resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    if self.isInitialized {
      let parameters = CommonParams(params: data)
      parameters.buildProcessorPathURL()

      self.controller.onLivenessCheck(data, resolve: resolve, reject: reject)
    } else {
      return reject("AziFace SDK doesn't initialized!", "NotInitialized", nil)
    }
  }

  @objc func handleEnrollUser(
    _ data: NSDictionary,
    resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    if self.isInitialized {
      let parameters = CommonParams(params: data)
      parameters.buildProcessorPathURL()

      self.controller.onEnrollUser(data, resolve: resolve, reject: reject)
    } else {
      return reject("AziFace SDK doesn't initialized!", "NotInitialized", nil)
    }
  }

  @objc func handleAuthenticateUser(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    if self.isInitialized {
      let parameters = CommonParams(params: data)
      parameters.buildProcessorPathURL()

      self.controller.onAuthenticateUser(data, resolve: resolve, reject: reject)
    } else {
      return reject("AziFace SDK doesn't initialized!", "NotInitialized", nil)
    }
  }

  @objc func handlePhotoIDMatch(
    _ data: NSDictionary,
    resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    if self.isInitialized {
      let parameters = CommonParams(params: data)
      parameters.buildProcessorPathURL()

      self.controller.onPhotoIDMatch(data, resolve: resolve, reject: reject)
    } else {
      return reject("AziFace SDK doesn't initialized!", "NotInitialized", nil)
    }
  }

  @objc func handlePhotoIDScan(
    _ data: NSDictionary,
    resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    if self.isInitialized {
      let parameters = CommonParams(params: data)
      parameters.buildProcessorPathURL()

      self.controller.onPhotoIDScan(data, resolve: resolve, reject: reject)
    } else {
      return reject("AziFace SDK doesn't initialized!", "NotInitialized", nil)
    }
  }
}

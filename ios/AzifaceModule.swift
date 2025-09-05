//
//  AzifaceModule.swift
//  AzifaceModule
//
//  Created by Nayara Dias on 13/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import FaceTecSDK
import Foundation
import LocalAuthentication

@objc(AzifaceModule)
class AzifaceModule: RCTEventEmitter, URLSessionDelegate, FaceTecInitializeCallback {
  private static let NAME: String = "ios_azify_app_"
  public static var DemonstrationExternalDatabaseRefID: String = ""
  //  public static var VocalGuidance: Vocal!
  public static var SDKInstance: FaceTecSDKInstance!
  public static var emitter: RCTEventEmitter!
  private var resolver: RCTPromiseResolveBlock?
  private var rejector: RCTPromiseRejectBlock?
  public var isInitialized: Bool = false

  override init() {
    super.init()

    AzifaceModule.emitter = self
  }

  @objc func initialize(
    _ params: NSDictionary,
    headers: NSDictionary,
    resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      self.setInitilizePromise(resolve: resolve, reject: reject)
      self.setTheme(Theme.Style)

      let paremeters = CommonParams(params: params)

      if paremeters.isNull() {
        self.isInitialized = false
        AzifaceModule.emitter.sendEvent(withName: "onInitialize", body: false)
        return reject("Parameters aren't provided", "ParamsNotProvided", nil)
      }

      paremeters.setHeaders(headers)
      paremeters.build()

      if !Config.isEmpty() {
        FaceTec.sdk.initializeWithSessionRequest(
          deviceKeyIdentifier: Config.DeviceKeyIdentifier,
          sessionRequestProcessor: SessionRequestProcessor(),
          completion: self
        )
      } else {
        self.isInitialized = false
        AzifaceModule.emitter.sendEvent(withName: "onInitialize", body: false)
        return reject("Configuration aren't provided", "ConfigNotProvided", nil)
      }
    }
  }

  @objc func liveness(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      if self.isInitialized {
        guard let viewController = self.getCurrentViewController() else {
          AzifaceModule.emitter.sendEvent(withName: "onError", body: true)
          return reject("AziFace SDK not found target View!", "NotFoundTargetView", nil)
        }

        AzifaceModule.DemonstrationExternalDatabaseRefID = ""

        let controller = AzifaceModule.SDKInstance.start3DLiveness(
          with: SessionRequestProcessor(data: data))
        
        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        AzifaceModule.emitter.sendEvent(withName: "onError", body: true)
        return reject("AziFace SDK doesn't initialized!", "NotInitialized", nil)
      }
    }
  }

  @objc func enroll(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      if self.isInitialized {
        guard let viewController = self.getCurrentViewController() else {
          AzifaceModule.emitter.sendEvent(withName: "onError", body: true)
          return reject("AziFace SDK not found target View!", "NotFoundTargetView", nil)
        }

        AzifaceModule.DemonstrationExternalDatabaseRefID = AzifaceModule.NAME + UUID().uuidString

        let controller = AzifaceModule.SDKInstance.start3DLiveness(
          with: SessionRequestProcessor(data: data))
        
        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        AzifaceModule.emitter.sendEvent(withName: "onError", body: true)
        return reject("AziFace SDK doesn't initialized!", "NotInitialized", nil)
      }
    }
  }

  @objc func authenticate(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      if self.isInitialized {
        guard let viewController = self.getCurrentViewController() else {
          AzifaceModule.emitter.sendEvent(withName: "onError", body: true)
          return reject("AziFace SDK not found target View!", "NotFoundTargetView", nil)
        }

        if AzifaceModule.DemonstrationExternalDatabaseRefID.isEmpty {
          AzifaceModule.emitter.sendEvent(withName: "onError", body: true)
          return reject("User isn't authenticated! You must enroll first!", "NotAuthenticated", nil)
        }
        
        let controller = AzifaceModule.SDKInstance.start3DLivenessThen3DFaceMatch(
          with: SessionRequestProcessor(data: data))
        
        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        AzifaceModule.emitter.sendEvent(withName: "onError", body: true)
        return reject("AziFace SDK doesn't initialized!", "NotInitialized", nil)
      }
    }
  }

  @objc func photoIDMatch(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      if self.isInitialized {
        guard let viewController = self.getCurrentViewController() else {
          AzifaceModule.emitter.sendEvent(withName: "onError", body: true)
          return reject("AziFace SDK not found target View!", "NotFoundTargetView", nil)
        }

        AzifaceModule.DemonstrationExternalDatabaseRefID = AzifaceModule.NAME + UUID().uuidString

        let controller = AzifaceModule.SDKInstance.start3DLivenessThen3D2DPhotoIDMatch(
          with: SessionRequestProcessor(data: data))
        
        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        AzifaceModule.emitter.sendEvent(withName: "onError", body: true)
        return reject("AziFace SDK doesn't initialized!", "NotInitialized", nil)
      }
    }
  }

  @objc func photoIDScanOnly(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      if self.isInitialized {
        guard let viewController = self.getCurrentViewController() else {
          AzifaceModule.emitter.sendEvent(withName: "onError", body: true)
          return reject("AziFace SDK not found target View!", "NotFoundTargetView", nil)
        }

        let controller = AzifaceModule.SDKInstance.startIDScanOnly(
          with: SessionRequestProcessor(data: data))
        
        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        AzifaceModule.emitter.sendEvent(withName: "onError", body: true)
        return reject("AziFace SDK doesn't initialized!", "NotInitialized", nil)
      }
    }
  }

  //  @objc func activeVocal() {
  //    AzifaceModule.VocalGuidance.setVocalGuidanceMode()
  //  }

  @objc func setTheme(_ options: NSDictionary?) {
    Theme.setAppTheme(options)
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

  private func getCurrentViewController() -> UIViewController? {
    var window: UIWindow?

    if #available(iOS 13.0, *) {
      guard let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene else {
        return nil
      }
      window = windowScene.windows.first
    } else {
      window = UIApplication.shared.keyWindow
    }

    guard let mainWindow = window else {
      return nil
    }

    var currentViewController = mainWindow.rootViewController

    while let presentedViewController = currentViewController?.presentedViewController {
      currentViewController = presentedViewController
    }

    if let navController = currentViewController as? UINavigationController {
      currentViewController = navController.visibleViewController
    }

    if let tabController = currentViewController as? UITabBarController {
      currentViewController = tabController.selectedViewController
    }

    return currentViewController
  }

  static func demonstrateHandlingFaceTecExit(_ status: FaceTecSessionStatus) {
    print("Session Status: " + Vocal.getSessionStatusString(status))

    let successful = status == .sessionCompleted
    if !successful {
      AzifaceModule.DemonstrationExternalDatabaseRefID = ""
    }
  }

  func onFaceTecSDKInitializeSuccess(sdkInstance: FaceTecSDKInstance) {
    self.isInitialized = true

    AzifaceModule.emitter.sendEvent(withName: "onInitialize", body: true)
    AzifaceModule.SDKInstance = sdkInstance

    Vocal.setVocalGuidanceSoundFiles()
    //    AzifaceModule.VocalGuidance.setUpVocalGuidancePlayers()

    Vocal.setOCRLocalization()

    self.resolver?(true)
  }

  func onFaceTecSDKInitializeError(error: FaceTecInitializationError) {
    self.isInitialized = false

    AzifaceModule.emitter.sendEvent(withName: "onInitialize", body: false)

    self.resolver?(false)
  }

  func setInitilizePromise(
    resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock
  ) {
    self.resolver = resolve
    self.rejector = reject
  }
  
  func sendOpenEvent() {
    AzifaceModule.emitter.sendEvent(withName: "onOpen", body: true)
    AzifaceModule.emitter.sendEvent(withName: "onClose", body: false)
    AzifaceModule.emitter.sendEvent(withName: "onCancel", body: false)
    AzifaceModule.emitter.sendEvent(withName: "onError", body: false)
  }
}

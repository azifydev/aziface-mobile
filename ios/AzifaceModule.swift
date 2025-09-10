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
  private static var error: AzifaceError!
  private static var resolver: RCTPromiseResolveBlock?
  private static var rejector: RCTPromiseRejectBlock?
  private static var isEnabled: Bool = false
  public static var DemonstrationExternalDatabaseRefID: String = ""
  private var vocalGuidance: Vocal!
  public var isInitialized: Bool = false
  public var emitter: RCTEventEmitter!
  public var sdkInstance: FaceTecSDKInstance!

  override init() {
    super.init()

    AzifaceModule.error = AzifaceError(module: self)
    self.emitter = self

    self.setupVocalGuidance()
  }

  static func demonstrateHandlingFaceTecExit(_ status: FaceTecSessionStatus) {
    let isCompleted = status == .sessionCompleted
    if !isCompleted {
      AzifaceModule.DemonstrationExternalDatabaseRefID = ""
    }

    let isError = AzifaceModule.error.isError(status: status)
    if isError {
      let message = AzifaceModule.error.getMessage(status: status)
      let code = AzifaceModule.error.getCode(status: status)
      AzifaceModule.rejector?(message, code, nil)
    } else {
      AzifaceModule.resolver?(true)
    }
    
    if AzifaceModule.isEnabled {
      Vocal.cleanUp()
      AzifaceModule.isEnabled = false;
    }
  }

  @objc func initialize(
    _ params: NSDictionary,
    headers: NSDictionary,
    resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      self.setPromiseResult(resolve: resolve, reject: reject)
      self.setTheme(Theme.Style)

      let paremeters = CommonParams(params: params)

      if paremeters.isNull() {
        self.isInitialized = false
        self.emitter.sendEvent(withName: "onInitialize", body: false)
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
        self.emitter.sendEvent(withName: "onInitialize", body: false)
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
          self.emitter.sendEvent(withName: "onError", body: true)
          return reject("AziFace SDK not found target View!", "NotFoundTargetView", nil)
        }

        self.setPromiseResult(resolve: resolve, reject: reject)
        AzifaceModule.DemonstrationExternalDatabaseRefID = ""

        let controller = self.sdkInstance.start3DLiveness(with: SessionRequestProcessor(data: data))

        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        self.emitter.sendEvent(withName: "onError", body: true)
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
          self.emitter.sendEvent(withName: "onError", body: true)
          return reject("AziFace SDK not found target View!", "NotFoundTargetView", nil)
        }

        self.setPromiseResult(resolve: resolve, reject: reject)
        AzifaceModule.DemonstrationExternalDatabaseRefID = AzifaceModule.NAME + UUID().uuidString

        let controller = self.sdkInstance.start3DLiveness(with: SessionRequestProcessor(data: data))

        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        self.emitter.sendEvent(withName: "onError", body: true)
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
          self.emitter.sendEvent(withName: "onError", body: true)
          return reject("AziFace SDK not found target View!", "NotFoundTargetView", nil)
        }

        if AzifaceModule.DemonstrationExternalDatabaseRefID.isEmpty {
          self.emitter.sendEvent(withName: "onError", body: true)
          return reject("User isn't authenticated! You must enroll first!", "NotAuthenticated", nil)
        }

        self.setPromiseResult(resolve: resolve, reject: reject)
        let controller = self.sdkInstance.start3DLivenessThen3DFaceMatch(
          with: SessionRequestProcessor(data: data))

        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        self.emitter.sendEvent(withName: "onError", body: true)
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
          self.emitter.sendEvent(withName: "onError", body: true)
          return reject("AziFace SDK not found target View!", "NotFoundTargetView", nil)
        }

        self.setPromiseResult(resolve: resolve, reject: reject)
        AzifaceModule.DemonstrationExternalDatabaseRefID = AzifaceModule.NAME + UUID().uuidString

        let controller = self.sdkInstance.start3DLivenessThen3D2DPhotoIDMatch(
          with: SessionRequestProcessor(data: data))

        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        self.emitter.sendEvent(withName: "onError", body: true)
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
          self.emitter.sendEvent(withName: "onError", body: true)
          return reject("AziFace SDK not found target View!", "NotFoundTargetView", nil)
        }

        self.setPromiseResult(resolve: resolve, reject: reject)
        let controller = self.sdkInstance.startIDScanOnly(with: SessionRequestProcessor(data: data))

        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        self.emitter.sendEvent(withName: "onError", body: true)
        return reject("AziFace SDK doesn't initialized!", "NotInitialized", nil)
      }
    }
  }

  @objc func vocal() {
    AzifaceModule.isEnabled = !AzifaceModule.isEnabled

    Vocal.setVocalGuidanceMode()
  }

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

  private func setupVocalGuidance() {
    let viewController = self.getCurrentViewController()
    self.vocalGuidance = Vocal(controller: viewController!)
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

  func onFaceTecSDKInitializeSuccess(sdkInstance: FaceTecSDKInstance) {
    self.isInitialized = true

    self.emitter.sendEvent(withName: "onInitialize", body: true)
    self.sdkInstance = sdkInstance

    Vocal.setVocalGuidanceSoundFiles()
    Vocal.setUpVocalGuidancePlayers()
    Vocal.setOCRLocalization()

    AzifaceModule.resolver?(true)
  }

  func onFaceTecSDKInitializeError(error: FaceTecInitializationError) {
    self.isInitialized = false

    self.emitter.sendEvent(withName: "onInitialize", body: false)

    AzifaceModule.resolver?(false)
  }

  func setPromiseResult(
    resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock
  ) {
    AzifaceModule.resolver = resolve
    AzifaceModule.rejector = reject
  }

  func sendOpenEvent() {
    self.emitter.sendEvent(withName: "onOpen", body: true)
    self.emitter.sendEvent(withName: "onClose", body: false)
    self.emitter.sendEvent(withName: "onCancel", body: false)
    self.emitter.sendEvent(withName: "onError", body: false)
  }
}

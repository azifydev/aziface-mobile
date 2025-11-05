import FaceTecSDK
import Foundation
import LocalAuthentication
import React

@objc(Aziface)
public class Aziface: NSObject, URLSessionDelegate, FaceTecInitializeCallback {
  private static let NAME: String = "ios_azify_app_"
  private static var IsRunning: Bool = false
  public static var DemonstrationExternalDatabaseRefID: String = ""
  private var error: AzifaceError!
  private var resolver: RCTPromiseResolveBlock?
  private var rejector: RCTPromiseRejectBlock?
  private var isEnabled: Bool = false
  private var vocalGuidance: Vocal!
  public var emitter: Emitter!
  public var isInitialized: Bool = false
  public var sdkInstance: FaceTecSDKInstance!

  @objc public init(emit: Emitter) {
    super.init()

    self.emitter = emit
    self.error = AzifaceError(module: self)

    self.setupVocalGuidance()
  }

  @objc public func initialize(
    _ params: NSDictionary,
    headers: NSDictionary,
    resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      if Aziface.IsRunning {
        return
      }

      Aziface.IsRunning = true
      self.setPromiseResult(resolve: resolve, reject: reject)
      self.setTheme(Theme.Style)

      let paremeters = CommonParams(params: params)

      if paremeters.isNull() {
        self.isInitialized = false
        self.emitter.emitOnInitialize(false)
        Aziface.IsRunning = false
        return reject("Parameters aren't provided", "ParamsNotProvided", nil)
      }

      paremeters.setHeaders(headers)
      paremeters.build()

      if !Config.isEmpty() {
        FaceTec.sdk.initializeWithSessionRequest(
          deviceKeyIdentifier: Config.DeviceKeyIdentifier,
          sessionRequestProcessor: SessionRequestProcessor(module: self),
          completion: self
        )
      } else {
        self.isInitialized = false
        self.emitter.emitOnInitialize(false)
        
        Aziface.IsRunning = false
        
        return reject("Configuration aren't provided", "ConfigNotProvided", nil)
      }
    }
  }

  @objc public func liveness(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      if Aziface.IsRunning {
        return
      }

      Aziface.IsRunning = true

      if self.isInitialized {
        guard let viewController = self.getCurrentViewController() else {
          return resolve(self.onCallProcessorError(message: "AziFace SDK not found target View!", code: "NotFoundTargetView"))
        }

        self.setPromiseResult(resolve: resolve, reject: reject)
        Aziface.DemonstrationExternalDatabaseRefID = ""

        let controller = self.sdkInstance.start3DLiveness(
          with: SessionRequestProcessor(module: self, data: data))

        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        return resolve(self.onCallProcessorError(message: "AziFace SDK doesn't initialized!", code: "NotInitialized"))
      }
    }
  }

  @objc public func enroll(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      if Aziface.IsRunning {
        return
      }

      Aziface.IsRunning = true

      if self.isInitialized {
        guard let viewController = self.getCurrentViewController() else {
          return resolve(self.onCallProcessorError(message: "AziFace SDK not found target View!", code: "NotFoundTargetView"))
        }

        self.setPromiseResult(resolve: resolve, reject: reject)
        Aziface.DemonstrationExternalDatabaseRefID = Aziface.NAME + UUID().uuidString

        let controller = self.sdkInstance.start3DLiveness(
          with: SessionRequestProcessor(module: self, data: data))

        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        return resolve(self.onCallProcessorError(message: "AziFace SDK doesn't initialized!", code: "NotInitialized"))
      }
    }
  }

  @objc public func authenticate(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      if Aziface.IsRunning {
        return
      }

      Aziface.IsRunning = true

      if self.isInitialized {
        guard let viewController = self.getCurrentViewController() else {
          return resolve(self.onCallProcessorError(message: "AziFace SDK not found target View!", code: "NotFoundTargetView"))
        }

        if Aziface.DemonstrationExternalDatabaseRefID.isEmpty {
          return resolve(self.onCallProcessorError(message: "User isn't authenticated! You must enroll first!", code: "NotAuthenticated"))
        }

        self.setPromiseResult(resolve: resolve, reject: reject)
        let controller = self.sdkInstance.start3DLivenessThen3DFaceMatch(
          with: SessionRequestProcessor(module: self, data: data))

        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        return resolve(self.onCallProcessorError(message: "AziFace SDK doesn't initialized!", code: "NotInitialized"))
      }
    }
  }

  @objc public func photoIDMatch(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      if Aziface.IsRunning {
        return
      }

      Aziface.IsRunning = true

      if self.isInitialized {
        guard let viewController = self.getCurrentViewController() else {
          return resolve(self.onCallProcessorError(message: "AziFace SDK not found target View!", code: "NotFoundTargetView"))
        }

        self.setPromiseResult(resolve: resolve, reject: reject)
        Aziface.DemonstrationExternalDatabaseRefID = Aziface.NAME + UUID().uuidString

        let controller = self.sdkInstance.start3DLivenessThen3D2DPhotoIDMatch(
          with: SessionRequestProcessor(module: self, data: data))

        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        return resolve(self.onCallProcessorError(message: "AziFace SDK doesn't initialized!", code: "NotInitialized"))      }
    }
  }

  @objc public func photoIDScanOnly(
    _ data: NSDictionary, resolve: @escaping RCTPromiseResolveBlock,
    reject: @escaping RCTPromiseRejectBlock
  ) {
    DispatchQueue.main.async {
      if Aziface.IsRunning {
        return
      }

      Aziface.IsRunning = true

      if self.isInitialized {
        guard let viewController = self.getCurrentViewController() else {
          return resolve(self.onCallProcessorError(message: "AziFace SDK not found target View!", code: "NotFoundTargetView"))
        }

        self.setPromiseResult(resolve: resolve, reject: reject)
        let controller = self.sdkInstance.startIDScanOnly(
          with: SessionRequestProcessor(module: self, data: data))

        self.sendOpenEvent()
        viewController.present(controller, animated: true, completion: nil)
      } else {
        return resolve(self.onCallProcessorError(message: "AziFace SDK doesn't initialized!", code: "NotInitialized"))
      }
    }
  }

  @objc public func vocal() {
    /**
    * TODO: Fix crash when device is muted.
    *
    * Current workaround is to check if device is muted and skip vocal guidance
    * toggle in that case.
    */
    let isMuted: Bool = Vocal.isDeviceMuted()

    if Aziface.IsRunning || isMuted {
      self.emitter.emitOnVocal(self.isEnabled)
      return
    }

    Aziface.IsRunning = true

    self.isEnabled = !self.isEnabled
    if self.isEnabled {
      Vocal.setUpVocalGuidancePlayers()
    }

    Vocal.setVocalGuidanceMode()

    self.emitter.emitOnVocal(self.isEnabled)
    Aziface.IsRunning = false
  }

  @objc public func setTheme(_ options: NSDictionary?) {
    Theme.setAppTheme(options)
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

  public func demonstrateHandlingFaceTecExit(_ status: FaceTecSessionStatus) {
    let isCompleted = status == .sessionCompleted
    if !isCompleted {
      Aziface.DemonstrationExternalDatabaseRefID = ""
    }

    Aziface.IsRunning = false
    let shared = SessionRequestProcessor.Shared

    if self.error.isError(status: status) {
      self.resolver?(self.onProcessorError(data: shared, status: status))
    } else {
      if self.isEnabled {
        Vocal.setUpVocalGuidancePlayers()
        Vocal.cleanUp()
        self.isEnabled = false

        self.emitter.emitOnVocal(false)
      }

      self.resolver?(self.onProcessorSuccess(data: shared))
    }
  }

  public func onFaceTecSDKInitializeSuccess(sdkInstance: FaceTecSDKInstance) {
    self.isInitialized = true

    self.emitter.emitOnInitialize(true)
    self.emitter.emitOnVocal(false)
    self.sdkInstance = sdkInstance

    Vocal.setVocalGuidanceSoundFiles()
    Vocal.setUpVocalGuidancePlayers()
    Vocal.setOCRLocalization()

    self.resolver?(true)

    Aziface.IsRunning = false
  }

  public func onFaceTecSDKInitializeError(error: FaceTecInitializationError) {
    self.isInitialized = false

    self.emitter.emitOnInitialize(false)
    self.emitter.emitOnVocal(false)

    self.resolver?(false)

    Aziface.IsRunning = false
  }

  public func setPromiseResult(
    resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock
  ) {
    self.resolver = resolve
    self.rejector = reject
  }

  public func sendOpenEvent() {
    self.emitter.emitOnOpen(true)
    self.emitter.emitOnClose(false)
    self.emitter.emitOnCancel(false)
    self.emitter.emitOnError(false)
  }

  private func onProcessorSuccess(data: NSMutableDictionary) -> NSDictionary {
    data.setValue(true, forKey: "isSuccess")
    data.setValue(nil, forKey: "error")

    return data
  }

  private func onProcessorError(data: NSMutableDictionary, status: FaceTecSessionStatus)
    -> NSDictionary
  {
    let message = self.error.getMessage(status: status)
    let code = self.error.getCode(status: status)

    let error: NSMutableDictionary = NSMutableDictionary()
    error.setValue(message, forKey: "message")
    error.setValue(code, forKey: "code")

    data.setValue(false, forKey: "isSuccess")
    data.setValue(nil, forKey: "data")
    data.setValue(error, forKey: "error")
    
    self.emitter.emitOnError(true)

    return data
  }

  private func onCallProcessorError(message: String, code: String) -> NSDictionary {
    let error: NSMutableDictionary = NSMutableDictionary()
    error.setValue(message, forKey: "message")
    error.setValue(code, forKey: "code")

    let processor: NSMutableDictionary = NSMutableDictionary()
    processor.setValue(false, forKey: "isSuccess")
    processor.setValue(nil, forKey: "data")
    processor.setValue(error, forKey: "error")

    self.emitter.emitOnError(true)

    Aziface.IsRunning = false

    return processor
  }
}

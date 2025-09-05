import Foundation

public class AzifaceError {
  private let module: AzifaceModule
  
  init(module: AzifaceModule) {
    self.module = module
  }
  
  public func getMessage(status: FaceTecSessionStatus) -> String {
    switch status {
    case .sessionCompleted: return "Aziface SDK has session completed!"
    case .cameraError: return "Aziface SDK has a camera error!"
    case .cameraPermissionsDenied: return "Aziface SDK hasn't camera permissions denied!"
    case .userCancelledIDScan: return "Aziface SDK was cancelled on ID scan!"
    case .userCancelledFaceScan: return "Aziface SDK was cancelled on face scan!"
    case .requestAborted: return "Aziface SDK has request aborted. Some error in JSON or network!"
    case .lockedOut: return "Aziface SDK is locked out!"
    default: return "Aziface SDK has unknown internal error!"
    }
  }
  
  public func getCode(status: FaceTecSessionStatus) -> String {
    switch status {
    case .sessionCompleted: return "SessionCompleted"
    case .cameraError: return "CameraError"
    case .cameraPermissionsDenied: return "CameraPermissionsDenied"
    case .userCancelledIDScan: return "UserCancelledIdScan"
    case .userCancelledFaceScan: return "UserCancelledFaceScan"
    case .requestAborted: return "RequestAborted"
    case .lockedOut: return "LockedOut"
    default: return "UnknownInternalError"
    }
  }
  
  public func isError(status: FaceTecSessionStatus) -> Bool {
    switch status {
    case .sessionCompleted:
      module.emitter.sendEvent(withName: "onOpen", body: false)
      module.emitter.sendEvent(withName: "onClose", body: true)
      module.emitter.sendEvent(withName: "onCancel", body: false)
      module.emitter.sendEvent(withName: "onError", body: false)
      return false
    case .userCancelledIDScan, .userCancelledFaceScan:
      module.emitter.sendEvent(withName: "onOpen", body: false)
      module.emitter.sendEvent(withName: "onClose", body: true)
      module.emitter.sendEvent(withName: "onCancel", body: true)
      module.emitter.sendEvent(withName: "onError", body: false)
      return true
    default:
      module.emitter.sendEvent(withName: "onOpen", body: false)
      module.emitter.sendEvent(withName: "onClose", body: true)
      module.emitter.sendEvent(withName: "onCancel", body: false)
      module.emitter.sendEvent(withName: "onError", body: true)
      return true
    }
  }
}

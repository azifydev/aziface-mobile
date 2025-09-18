import Foundation

public class AzifaceError {
  private let module: AzifaceModule

  init(module: AzifaceModule) {
    self.module = module
  }

  public func getMessage(status: FaceTecSessionStatus) -> String {
    switch status {
    case .sessionCompleted: return "Session was completed."
    case .cameraError: return "Session cancelled due to a camera error."
    case .cameraPermissionsDenied:
      return
        "FaceTec SDK was unable to access the Camera due to the User’s Settings or an Administrator Policy."
    case .userCancelledIDScan:
      return "The User cancelled before completing all of the steps in the ID Scan Process."
    case .userCancelledFaceScan:
      return "The user cancelled before performing enough Scans to Succeed."
    case .requestAborted:
      return "Session was cancelled because abortOnCatastrophicError() was called."
    case .lockedOut: return "FaceTec SDK is in a lockout state."
    default: return "Session failed because an unknown or unexpected error occurred."
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

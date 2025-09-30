import Foundation
import FaceTecSDK

public class AzifaceError {
  private let module: Aziface

  init(module: Aziface) {
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
    module.emitter.emitOnOpen(false)
    module.emitter.emitOnClose(true)
    
    switch status {
    case .sessionCompleted:
      module.emitter.emitOnCancel(false)
      module.emitter.emitOnError(false)
      return false
    case .userCancelledIDScan, .userCancelledFaceScan:
      module.emitter.emitOnCancel(true)
      module.emitter.emitOnError(false)
      return true
    default:
      module.emitter.emitOnCancel(false)
      module.emitter.emitOnError(true)
      return true
    }
  }
}

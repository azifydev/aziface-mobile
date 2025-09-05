package com.azify.errors;

import com.azify.AzifaceModule;
import com.facetec.sdk.FaceTecSessionStatus;

public class AzifaceError {
  public final AzifaceModule module;

  public AzifaceError(AzifaceModule module) {
    this.module = module;
  }

  public String getErrorMessage(FaceTecSessionStatus status) {
    switch (status) {
      case SESSION_COMPLETED: {
        return "Session was completed.";
      }
      case CAMERA_ERROR: {
        return "Session cancelled due to a camera error.";
      }
      case CAMERA_PERMISSIONS_DENIED: {
        return "FaceTec SDK was unable to access the Camera due to the User’s Settings or an Administrator Policy.";
      }
      case USER_CANCELLED_ID_SCAN: {
        return "The User cancelled before completing all of the steps in the ID Scan Process.";
      }
      case USER_CANCELLED_FACE_SCAN: {
        return "The user cancelled before performing enough Scans to Succeed.";
      }
      case REQUEST_ABORTED: {
        return "Session was cancelled because abortOnCatastrophicError() was called.";
      }
      case LOCKED_OUT: {
        return "FaceTec SDK is in a lockout state.";
      }
      default: {
        return "Session failed because an unknown or unexpected error occurred.";
      }
    }
  }

  public String getErrorCode(FaceTecSessionStatus status) {
    switch (status) {
      case SESSION_COMPLETED: {
        return "SessionCompleted";
      }
      case CAMERA_ERROR: {
        return "CameraError";
      }
      case CAMERA_PERMISSIONS_DENIED: {
        return "CameraPermissionsDenied";
      }
      case USER_CANCELLED_ID_SCAN: {
        return "UserCancelledIdScan";
      }
      case USER_CANCELLED_FACE_SCAN: {
        return "UserCancelledFaceScan";
      }
      case REQUEST_ABORTED: {
        return "RequestAborted";
      }
      case LOCKED_OUT: {
        return "LockedOut";
      }
      default: {
        return "UnknownInternalError";
      }
    }
  }

  public Boolean isError(FaceTecSessionStatus status) {
    switch (status) {
      case SESSION_COMPLETED: {
        module.sendEvent("onOpen", false);
        module.sendEvent("onClose", true);
        module.sendEvent("onCancel", false);
        module.sendEvent("onError", false);
        return false;
      }
      case USER_CANCELLED_ID_SCAN:
      case USER_CANCELLED_FACE_SCAN:
        module.sendEvent("onOpen", false);
        module.sendEvent("onClose", true);
        module.sendEvent("onCancel", true);
        module.sendEvent("onError", false);
        return true;
      default:
        module.sendEvent("onOpen", false);
        module.sendEvent("onClose", true);
        module.sendEvent("onCancel", false);
        module.sendEvent("onError", true);
        return true;
    }
  }
}

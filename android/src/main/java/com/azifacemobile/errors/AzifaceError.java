package com.azifacemobile.errors;

import com.azifacemobile.AzifaceMobileModule;
import com.facetec.sdk.FaceTecSessionStatus;

public class AzifaceError {
  public final AzifaceMobileModule module;

  public AzifaceError(AzifaceMobileModule module) {
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
    module.onOpen(false);
    module.onClose(true);

    switch (status) {
      case SESSION_COMPLETED: {
        module.onCancel(false);
        module.onError(false);
        return false;
      }
      case USER_CANCELLED_ID_SCAN:
      case USER_CANCELLED_FACE_SCAN:
        module.onCancel(true);
        module.onError(false);
        return true;
      default:
        module.onCancel(false);
        module.onError(true);
        return true;
    }
  }
}

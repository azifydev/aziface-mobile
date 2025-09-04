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
        return "Session Completed";
      }
      case CAMERA_ERROR: {
        return "Camera Error";
      }
      case USER_CANCELLED_ID_SCAN: {
        return "User Cancelled on ID Scan";
      }
      case USER_CANCELLED_FACE_SCAN: {
        return "User Cancelled on Face Scan";
      }
      case REQUEST_ABORTED: {
        return "Request Aborted";
      }
      case CAMERA_PERMISSIONS_DENIED: {
        return "Camera Permissions Denied";
      }
      case LOCKED_OUT: {
        return "Locked Out";
      }
      default: {
        return "Unknown Internal Error";
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
      case USER_CANCELLED_ID_SCAN: {
        return "UserCancelledIdScan";
      }
      case USER_CANCELLED_FACE_SCAN: {
        return "UserCancelledFaceScan";
      }
      case REQUEST_ABORTED: {
        return "RequestAborted";
      }
      case CAMERA_PERMISSIONS_DENIED: {
        return "CameraPermissionsDenied";
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

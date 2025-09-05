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
        return "Aziface SDK has session completed!";
      }
      case CAMERA_ERROR: {
        return "Aziface SDK has a camera error!";
      }
      case USER_CANCELLED_ID_SCAN: {
        return "Aziface SDK was cancelled on ID scan!";
      }
      case USER_CANCELLED_FACE_SCAN: {
        return "Aziface SDK was cancelled on face scan!";
      }
      case REQUEST_ABORTED: {
        return "Aziface SDK has request aborted. Some error in JSON or network!";
      }
      case CAMERA_PERMISSIONS_DENIED: {
        return "Aziface SDK hasn't camera permissions denied!";
      }
      case LOCKED_OUT: {
        return "Aziface SDK is locked out!";
      }
      default: {
        return "Aziface SDK has unknown internal error!";
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

/**
 * @enum
 *
 * @description This is the enum Errors that can be throws by Aziface SDK.
 */
export enum Errors {
  /**
   * @description When trying to initialize a process, but SDK wasn't
   * initialized.
   */
  NotInitialized = 'NotInitialized',

  /**
   * @description When `deviceKeyIdentifier` and `baseUrl` aren't provided.
   */
  ConfigNotProvided = 'ConfigNotProvided',

  /**
   * @description When parameters aren't provided, this case, it is `null`.
   */
  ParamsNotProvided = 'ParamsNotProvided',

  /**
   * @description When `authenticate` process is called, but `enroll` wasn't
   * done first.
   */
  NotAuthenticated = 'NotAuthenticated',

  /**
   * @description When `Activity` (Android) or `ViewController` (iOS) aren't
   * found on call processor.
   */
  NotFoundTargetView = 'NotFoundTargetView',

  /**
   * @description When an error on use the camera occurs.
   */
  CameraError = 'CameraError',

  /**
   * @description When the user doesn't permit the use camera.
   */
  CameraPermissionsDenied = 'CameraPermissionsDenied',

  /**
   * @description When process was cancelled on ID scan.
   */
  UserCancelledIdScan = 'UserCancelledIdScan',

  /**
   * @description When process was cancelled on face scan.
   */
  UserCancelledFaceScan = 'UserCancelledFaceScan',

  /**
   * @description When process has request aborted. Some error in JSON or
   * network.
   */
  RequestAborted = 'RequestAborted',

  /**
   * @description When process is locked out.
   */
  LockedOut = 'LockedOut',

  /**
   * @description When process has unknown internal error.
   */
  UnknownInternalError = 'UnknownInternalError',
}

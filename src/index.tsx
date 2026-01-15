import { useEffect } from 'react';
import {
  View,
  type EventSubscription,
  type TurboModule,
  type ViewProps,
} from 'react-native';
import AzifaceMobile from './NativeAzifaceMobile';
import {
  onCancel as handleCancel,
  onClose as handleClose,
  onError as handleError,
  onInitialize as handleInitialize,
  onOpen as handleOpen,
  onVocal as handleVocal,
} from './listeners';

// Errors

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

// Styles

/**
 * @type
 *
 * @description The type of cancel location.
 */
export type CancelLocation = 'DISABLED' | 'TOP_LEFT' | 'TOP_RIGHT';

/**
 * @interface CancelPosition
 *
 * @description This type must be used to set the custom position of the cancel
 * button.
 */
export interface CancelPosition {
  /**
   * @description The left position of the cancel button.
   */
  left: number;

  /**
   * @description The top position of the cancel button.
   */
  top: number;

  /**
   * @description The right position of the cancel button.
   */
  right: number;

  /**
   * @description The bottom position of the cancel button.
   */
  bottom: number;
}

/**
 * @interface Point
 *
 * @description Defines the drawn in the layer's coordinate space with axis X
 * and Y.
 *
 * @platform iOS
 */
export interface Point {
  /**
   * @description The X coordinate of the point.
   *
   * @default 0
   */
  x?: number;

  /**
   * @description The Y coordinate of the point.
   *
   * @default 0
   */
  y?: number;
}

/**
 * @interface FeedbackBackgroundColor
 *
 * @description This type must be used to set the theme of the feedback box.
 *
 * @platform iOS
 */
export interface FeedbackBackgroundColor {
  /**
   * @description An array of colors defining the color of each gradient stop.
   *
   * @default ['#026ff4', '#026ff4']
   */
  colors?: string[];

  /**
   * @description It's accepts only two values between 0 and 1 that defining
   * the location of each gradient stop.
   *
   * @default [0, 1]
   */
  locations?: [number, number];

  /**
   * @description The start point of the gradient when drawn in the layer’s
   * coordinate space.
   *
   * @default { x: 0, y: 0 }
   */
  startPoint?: Point;

  /**
   * @description The end point of the gradient when drawn in the layer’s
   * coordinate space.
   *
   * @default { x: 1, y: 0 }
   */
  endPoint?: Point;
}

/**
 * @interface ThemeShadowInsets
 *
 * @description An object containing the shadow inset styles used in the
 * Aziface SDK.
 *
 * @platform iOS
 */
export interface ThemeShadowInsets {
  /**
   * @description The top inset style of the shadow.
   *
   * @default 0
   */
  top?: number;

  /**
   * @description The left inset style of the shadow.
   *
   * @default 0
   */
  left?: number;

  /**
   * @description The bottom inset style of the shadow.
   *
   * @default 0
   */
  bottom?: number;

  /**
   * @description The right inset style of the shadow.
   *
   * @default 0
   */
  right?: number;
}

/**
 * @interface ThemeShadowOffset
 *
 * @description An object containing the shadow offset styles used in the
 * Aziface SDK.
 *
 * @platform iOS
 */
export interface ThemeShadowOffset {
  /**
   * @description The width offset style of the shadow.
   *
   * @default 0
   */
  width?: number;

  /**
   * @description The height offset style of the shadow.
   *
   * @default 0
   */
  height?: number;
}

/**
 * @interface ThemeShadow
 *
 * @description An object containing the shadow styles used in the Aziface SDK.
 *
 * @platform iOS
 */
export interface ThemeShadow {
  /**
   * @description The shadow color style of the view.
   *
   * @default '#000000'
   */
  color?: string;

  /**
   * @description The shadow opacity style of the view.
   *
   * @default 1
   */
  opacity?: number;

  /**
   * @description The shadow radius style of the view.
   *
   * @default 10
   */
  radius?: number;

  /**
   * @description The shadow offset style of the view.
   *
   * @default undefined
   */
  offset?: ThemeShadowOffset;

  /**
   * @description The shadow insets style of the view.
   *
   * @default undefined
   */
  insets?: ThemeShadowInsets;
}

/**
 * @interface ThemeImage
 *
 * @description An object containing the image assets used in the Aziface SDK.
 */
export interface ThemeImage {
  /**
   * @description The branding image to will be used in Aziface SDK screen.
   * **Note**: The image name must be to inserted with no extension format.
   *
   * @default undefined
   */
  branding?: string;

  /**
   * @description The icon cancel button to will be used in Aziface SDK screen.
   * The image name must be to inserted with no extension format.
   *
   * @default undefined
   */
  cancel?: string;

  /**
   * @description A boolean value to show or hide the branding image in
   * Aziface SDK screen.
   *
   * @default true
   */
  isShowBranding?: boolean;

  /**
   * @description A boolean value to hide the cancel button when the app.
   *
   * @default false - Android
   * @default true - iOS
   */
  isHideForCameraPermissions?: boolean;

  /**
   * @description The cancel location in Aziface SDK screen.
   *
   * @default "TOP_RIGHT"
   */
  cancelLocation?: CancelLocation;

  /**
   * @description The custom position to the cancel button in Aziface SDK
   * screen.
   *
   * @default undefined
   */
  cancelPosition?: CancelPosition;
}

/**
 * @interface ThemeFrame
 *
 * @description An object containing the frame styles used in the Aziface SDK.
 */
export interface ThemeFrame {
  /**
   * @description Represents the border radius style of the frame view.
   *
   * @default 20
   */
  cornerRadius?: number;

  /**
   * @description Represents the border color style of the frame view.
   *
   * @default '#ffffff'
   */
  borderColor?: string;

  /**
   * @description Represents the background color style of the frame view
   * during to check face or scan ID of the user.
   *
   * @default '#ffffff'
   */
  backgroundColor?: string;

  /**
   * @description Represents the border width style of the frame view.
   *
   * @default undefined
   */
  borderWidth?: number;

  /**
   * @description Represents the elevation style of the frame view.
   *
   * @default 0
   *
   * @platform Android
   */
  elevation?: number;

  /**
   * @description The shadow styles of the view.
   *
   * @default undefined
   *
   * @platform iOS
   */
  shadow?: ThemeShadow;
}

/**
 * @interface ThemeOval
 *
 * @description An object containing the oval styles used in the Aziface SDK.
 */
export interface ThemeOval {
  /**
   * @description Represents the border color style of the oval view border.
   *
   * @default '#026ff4'
   */
  strokeColor?: string;

  /**
   * @description Represents the border width style of the oval view border.
   *
   * @default undefined
   */
  strokeWidth?: number;

  /**
   * @description Represents the first progress row color during the check
   * face or scan ID of the user. It's localize inside over view when the
   * user is making check face or scan ID.
   *
   * @default '#0264dc'
   */
  firstProgressColor?: string;

  /**
   * @description Represents the second progress row color during the check
   * face or scan ID of the user. It's localize inside over view when the
   * user is making check face or scan ID.
   *
   * @default '#0264dc'
   */
  secondProgressColor?: string;

  /**
   * @description Represents the radial offset style of the oval view
   *
   * @default undefined
   */
  progressRadialOffset?: number;

  /**
   * @description Represents the stroke width style of the oval view
   *
   * @default undefined
   */
  progressStrokeWidth?: number;
}

/**
 * @interface ThemeFeedback
 *
 * @description An object containing the feedback styles used in the Aziface
 * SDK.
 */
export interface ThemeFeedback {
  /**
   * @description Represents the background color style of the feedback view.
   *
   * @default '#026ff4'
   *
   * @platform Android
   */
  backgroundColor?: string;

  /**
   * @description Represents the background color style of the feedback view.
   *
   * @default undefined
   *
   * @platform iOS
   */
  backgroundColors?: FeedbackBackgroundColor;

  /**
   * @description Represents the color style of the feedback box text.
   *
   * @default '#ffffff'
   */
  textColor?: string;

  /**
   * @description Represents the border radius style of the feedback view.
   *
   * @default undefined
   */
  cornerRadius?: number;

  /**
   * @description Represents the elevation style of the feedback view.
   *
   * @default 10
   *
   * @platform Android
   */
  elevation?: number;

  /**
   * @description The shadow styles of the view.
   *
   * @default undefined
   *
   * @platform iOS
   */
  shadow?: ThemeShadow;

  /**
   * @description A boolean value to enable or disable the pulsating text.
   *
   * @default true
   */
  isEnablePulsatingText?: boolean;
}

/**
 * @interface ThemeButton
 *
 * @description An object containing the button styles used in the Aziface SDK.
 */
export interface ThemeButton {
  /**
   * @description Represents the background color style of the button.
   *
   * @default '#026ff4'
   */
  backgroundNormalColor?: string;

  /**
   * @description Represents the background color style of the button when it's
   * disabled.
   *
   * @default '#b3d4fc'
   */
  backgroundDisabledColor?: string;

  /**
   * @description Represents the background color style of the button when it's
   * on press effect.
   *
   * @default '#0264dc'
   */
  backgroundHighlightColor?: string;

  /**
   * @description Represents the color style of the button text.
   *
   * @default '#ffffff'
   */
  textNormalColor?: string;

  /**
   * @description Represents the color style of the button text when it's
   * disabled.
   *
   * @default '#ffffff'
   */
  textDisabledColor?: string;

  /**
   * @description Represents the color style of the button text when it's on
   * press effect.
   *
   * @default '#ffffff'
   */
  textHighlightColor?: string;

  /**
   * @description Represents the border radius style of the button.
   *
   * @default undefined
   */
  cornerRadius?: number;

  /**
   * @description Represents the border width style of the button.
   *
   * @default undefined
   */
  borderWidth?: number;

  /**
   * @description Represents the border color style of the button.
   *
   * @default undefined
   */
  borderColor?: string;
}

/**
 * @interface ThemeResultAnimation
 *
 * @description An object containing the animation styles used in the Aziface
 * SDK result animation.
 */
export interface ThemeResultAnimation {
  /**
   * @description Represents the indicator background color style of the
   * result animation on finishing of the loading.
   *
   * @default '#026ff4'
   */
  backgroundColor?: string;

  /**
   * @description Represents the icon color style of the check icon on the
   * result animation.
   *
   * @default '#ffffff'
   */
  foregroundColor?: string;

  /**
   * @description Represents the display time style of the result animation in
   * seconds.
   *
   * @default 2.5
   */
  displayTime?: number;

  /**
   * @description Represents the background color style of the success result
   * animation.
   *
   * @default '#026ff4'
   */
  IDScanSuccessForegroundColor?: string;

  /**
   * @description Represents the success image of the success result animation.
   *
   * @default undefined
   */
  successImage?: string;

  /**
   * @description Represents the unsuccess image of the success result
   * animation.
   *
   * @default undefined
   */
  unsuccessImage?: string;

  /**
   * @description Represents the background color style of the unsuccess result
   * animation.
   *
   * @default '#cc0044'
   */
  unsuccessBackgroundColor?: string;

  /**
   * @description Represents the foreground color style of the unsuccess result
   * animation.
   *
   * @default '#ffffff'
   */
  unsuccessForegroundColor?: string;
}

/**
 * @interface ThemeSessionAbortAnimation
 *
 * @description An object containing the animation styles used in the Aziface
 * SDK session abort animation.
 */
export interface ThemeSessionAbortAnimation {
  /**
   * @description Represents the foreground color style of the session abort
   * animation.
   *
   * @default '#ffffff'
   */
  foregroundColor?: string;

  /**
   * @description Represents the background color style of the session abort
   * animation.
   *
   * @default '#cc0044'
   */
  backgroundColor?: string;

  /**
   * @description Represents the background image asset name of the session
   * abort animation. The image name must be to inserted with no extension
   * format.
   *
   * @default undefined
   */
  image?: string;
}

/**
 * @interface ThemeGuidanceRetryScreen
 *
 * @description An object containing the styles used in the guidance retry
 * screen.
 */
export interface ThemeGuidanceRetryScreen {
  /**
   * @description Represents the border color style of the guidance retry
   * screen.
   *
   * @default '#ffffff'
   */
  imageBorderColor?: string;

  /**
   * @description Represents the border width style of the guidance retry
   * screen.
   *
   * @default undefined
   */
  imageBorderWidth?: number;

  /**
   * @description Represents the corner radius style of the guidance retry
   * screen.
   *
   * @default undefined
   */
  imageCornerRadius?: number;

  /**
   * @description Represents the border color style of the oval view row
   * of the guidance retry.
   *
   * @default '#ffffff'
   */
  ovalStrokeColor?: string;

  /**
   * @description Represents the subtext color style of the guidance retry
   * screen.
   *
   * @default undefined
   */
  subtextColor?: string;
}

/**
 * @interface ThemeGuidanceImages
 *
 * @description An object containing the image assets used in the Aziface
 * SDK.
 */
export interface ThemeGuidanceImages {
  /**
   * @description The image to will be used in camera permissions screen.
   *
   * @default undefined
   */
  cameraPermission?: string;

  /**
   * @description The image to will be used in retry screen as ideal photo.
   *
   * @default undefined
   */
  ideal?: string;
}

/**
 * @interface ThemeGuidanceReadyScreen
 *
 * @description An object containing the styles used in the guidance ready
 * screen.
 */
export interface ThemeGuidanceReadyScreen {
  /**
   * @description Represents the header text of the guidance ready screen.
   *
   * @default undefined
   */
  headerText?: string;

  /**
   * @description Represents the header text color style of the guidance ready
   * screen.
   *
   * @default undefined
   */
  headerTextColor?: string;

  /**
   * @description Represents the oval fill color style of the guidance ready
   * screen.
   *
   * @default 'transparent'
   */
  ovalFillColor?: string;

  /**
   * @description Represents the subtext of the guidance ready screen.
   *
   * @default undefined
   */
  subtext?: string;

  /**
   * @description Represents the subtext color style of the guidance ready
   * screen.
   *
   * @default undefined
   */
  subtextColor?: string;
}

/**
 * @interface ThemeGuidance
 *
 * @description An object containing the styles used in the guidance view.
 */
export interface ThemeGuidance {
  /**
   * @description Represents the background color style of the guidance view.
   * The guidance view is above the frame view and it's showed to before
   * check face or scan ID of the user. In Android you must provide a string
   * color, but in iOS you must provide an array of colors.
   *
   * @description Default value in **Android** is:
   *
   * @default '#ffffff'
   *
   * @description Default value in **iOS** is:
   *
   * @default ['#ffffff', '#ffffff']
   */
  backgroundColor?: string | string[];

  /**
   * @description Represents the foreground color style of the guidance text.
   *
   * @default '#272937'
   */
  foregroundColor?: string;

  /**
   * @description Represents the button styles used in the Aziface SDK.
   *
   * @default undefined
   */
  button?: ThemeButton;

  /**
   * @description An object containing the styles used in the guidance retry
   * screen.
   *
   * @default undefined
   */
  retryScreen?: ThemeGuidanceRetryScreen;

  /**
   * @description An object containing the styles used in the guidance ready
   * screen.
   *
   * @default undefined
   */
  readyScreen?: ThemeGuidanceReadyScreen;

  /**
   * @description An object containing the image assets used in the Aziface
   * SDK.
   *
   * @default undefined
   */
  images?: ThemeGuidanceImages;
}

/**
 * @interface ThemeResultScreen
 *
 * @description An object containing the styles used in the result screen.
 */
export interface ThemeResultScreen {
  /**
   * @description Represents the background color style of the feedback view
   * that's shown on the finish to check face or scan ID. In Android you must
   * provide a string color, but in iOS you must provide an array of colors.
   *
   * @description Default value in **Android** is:
   *
   * @default '#ffffff'
   *
   * @description Default value in **iOS** is:
   *
   * @default ['#ffffff', '#ffffff']
   */
  backgroundColor?: string | string[];

  /**
   * @description Represents the foreground color style of the result screen
   * text.
   *
   * @default '#272937'
   */
  foregroundColor?: string;

  /**
   * @description Represents the indicator background color style of the
   * result screen during loading.
   *
   * @default '#026ff4'
   */
  activityIndicatorColor?: string;

  /**
   * @description Represents the indicator image asset name of the result
   * screen during loading. The image name must be to inserted with no
   * extension format.
   *
   * @default undefined
   */
  indicatorImage?: string;

  /**
   * @description Represents the progress bar fill color style of the result
   * screen during loading.
   *
   * @default '#026ff4'
   */
  uploadProgressFillColor?: string;

  /**
   * @description Represents the progress bar track color style of the result
   * screen during loading.
   *
   * @default '#b3d4fc'
   */
  uploadProgressTrackColor?: string;

  /**
   * @description A boolean value to show or hide the upload progress bar in
   * the result screen during loading.
   *
   * @default true
   */
  isShowUploadProgressBar?: boolean;

  /**
   * @description Represents the animation relative scale style of the result
   * screen.
   *
   * @default 1
   */
  animationRelativeScale?: number;

  /**
   * @description Represents the delay time style of the face scan still
   * uploading message in seconds.
   *
   * @default 6
   */
  faceScanStillUploadingMessageDelayTime?: number;

  /**
   * @description Represents the delay time style of the ID scan still
   * uploading message in seconds.
   *
   * @default 8
   */
  idScanStillUploadingMessageDelayTime?: number;

  /**
   * @description Represents the indicator rotation interval style of the
   * result screen during loading in milliseconds.
   *
   * @default 1000
   */
  indicatorRotationInterval?: number;

  /**
   * @description Represents the result animation styles of the result screen.
   *
   * @default undefined
   */
  resultAnimation?: ThemeResultAnimation;

  /**
   * @description Represents the session abort animation styles of the result
   * screen.
   *
   * @default undefined
   */
  sessionAbortAnimation?: ThemeSessionAbortAnimation;
}

/**
 * @interface ThemeIdScanCaptureScreen
 *
 * @description An object containing the styles used in the ID scan capture
 * screen.
 */
export interface ThemeIdScanCaptureScreen {
  /**
   * @description Represents the foreground color style of the ID scan
   * capture text.
   *
   * @default '#ffffff'
   */
  foregroundColor?: string;

  /**
   * @description Represents the background color style of the ID scan
   * capture view.
   *
   * @default '#ffffff'
   */
  textBackgroundColor?: string;

  /**
   * @description Represents the background color style of the ID scan
   * capture view.
   *
   * @default '#026ff4'
   */
  backgroundColor?: string;

  /**
   * @description Represents the border color style of the ID scan capture
   * camera.
   *
   * @default '#ffffff'
   */
  frameStrokeColor?: string;
}

/**
 * @interface ThemeIdScanReviewScreen
 *
 * @description An object containing the styles used in the ID scan review
 * screen.
 */
export interface ThemeIdScanReviewScreen {
  /**
   * @description Represents the color style of the ID scan review text.
   *
   * @default '#ffffff'
   */
  foregroundColor?: string;

  /**
   * @description Represents the background color style of the ID scan
   * review label.
   *
   * @default '#026ff4'
   */
  textBackgroundColor?: string;
}

/**
 * @interface ThemeIdScanSelectionScreen
 *
 * @description An object containing the styles used in the ID scan selection
 * screen.
 */
export interface ThemeIdScanSelectionScreen {
  /**
   * @description Represents the background color style of the ID scan
   * selection view. In Android you must provide a string color, but in iOS you
   * must provide an array of colors.
   *
   * @description Default value in **Android** is:
   *
   * @default '#ffffff'
   *
   * @description Default value in **iOS** is:
   *
   * @default ['#ffffff', '#ffffff']
   */
  backgroundColor?: string | string[];

  /**
   * @description Represents the color style of the ID scan selection text.
   *
   * @default '#272937'
   */
  foregroundColor?: string;
}

/**
 * @interface ThemeIdScan
 *
 * @description An object containing the styles used in the ID scan screens.
 */
export interface ThemeIdScan {
  /**
   * @description An object containing the styles used in the ID scan
   * selection screen.
   *
   * @default undefined
   */
  selectionScreen?: ThemeIdScanSelectionScreen;

  /**
   * @description An object containing the styles used in the ID scan review
   * screen.
   *
   * @default undefined
   */
  reviewScreen?: ThemeIdScanReviewScreen;

  /**
   * @description An object containing the styles used in the ID scan capture
   * screen.
   *
   * @default undefined
   */
  captureScreen?: ThemeIdScanCaptureScreen;

  /**
   * @description Represents the button styles used in the Aziface SDK.
   *
   * @default undefined
   */
  button?: ThemeButton;
}

/**
 * @interface Theme
 *
 * @description An object with all the properties to will be used to set the
 * theme. All properties are optional.
 */
export interface Theme {
  /**
   * @description Represents the background color style of the main view.
   *
   * @default '#ffffff'
   */
  overlayBackgroundColor?: string;

  /**
   * @description An object containing the image assets used in the Aziface
   * SDK.
   *
   * @default undefined
   */
  image?: ThemeImage;

  /**
   * @description An object containing the frame styles used in the Aziface
   * SDK.
   *
   * @default undefined
   */
  frame?: ThemeFrame;

  /**
   * @description An object containing the styles used in the guidance view.
   *
   * @default undefined
   */
  guidance?: ThemeGuidance;

  /**
   * @description An object containing the oval styles used in the Aziface SDK.
   *
   * @default undefined
   */
  oval?: ThemeOval;

  /**
   * @description An object containing the feedback styles used in the Aziface
   * SDK.
   *
   * @default undefined
   */
  feedback?: ThemeFeedback;

  /**
   * @description An object containing the styles used in the result screen.
   *
   * @default undefined
   */
  resultScreen?: ThemeResultScreen;

  /**
   * @description An object containing the styles used in the ID scan screens.
   *
   * @default undefined
   */
  idScan?: ThemeIdScan;
}

// Modules

/**
 * @type
 *
 * @description The processor request method.
 */
export type ProcessorRequestMethod = 'GET' | 'POST';

/**
 * @interface Headers
 *
 * @description Headers your requests, to each request it's sent.
 */
export interface Headers {
  [key: string]: string | null | undefined;
}

/**
 * @interface Initialize
 *
 * @description This is the parameters to initialize the Aziface SDK.
 */
export interface Initialize {
  /**
   * @description This is the parameters to initialize the Aziface SDK. This
   * property is required.
   */
  params: Params;

  /**
   * @description Headers your requests, to each request it's sent.
   *
   * @default undefined
   */
  headers?: Headers;
}

/**
 * @interface Params
 *
 * @description This is the parameters to initialize the Aziface SDK.
 */
export interface Params {
  /**
   * @description Your device to will be used to initialize Aziface SDK.
   * Available in your Aziface account.
   */
  deviceKeyIdentifier: string;

  /**
   * @description Your base URL to will be used to sent data.
   */
  baseUrl: string;

  /**
   * @description Option to select production or development mode for
   * initialize Aziface SDK.
   */
  isDevelopment?: boolean;
}

/**
 * @interface ProcessorAdditionalSessionData
 *
 * @description The Processor additional session data response.
 */
export interface ProcessorAdditionalSessionData {
  /**
   * @description The platform of the device.
   */
  platform: string;

  /**
   * @description The application ID.
   */
  appID: string;

  /**
   * @description The installation ID.
   */
  installationID: string;

  /**
   * @description The device model.
   */
  deviceModel: string;

  /**
   * @description The device SDK version.
   */
  deviceSDKVersion: string;

  /**
   * @description The user agent.
   */
  userAgent: string;

  /**
   * @description The session ID.
   */
  sessionID: string;
}

/**
 * @interface ProcessorResult
 *
 * @description The Processor result response.
 */
export interface ProcessorResult {
  /**
   * @description Indicates if it's liveness proven.
   */
  livenessProven: boolean;

  /**
   * @description The audit trail image in base64 format.
   */
  auditTrailImage: string;

  /**
   * @description The age group enumeration integer.
   */
  ageV2GroupEnumInt: number;

  /**
   * @description The match level.
   */
  matchLevel?: number;
}

/**
 * @interface ProcessorHttpCallInfo
 *
 * @description The Processor HTTP call information response.
 */
export interface ProcessorHttpCallInfo {
  /**
   * @description The transaction ID.
   */
  tid: string;

  /**
   * @description The request path.
   */
  path: string;

  /**
   * @description The date of the request.
   */
  date: string;

  /**
   * @description The epoch second of the request.
   */
  epochSecond: number;

  /**
   * @description The request method.
   */
  requestMethod: ProcessorRequestMethod;
}

/**
 * @interface ProcessorIDScanResultsSoFar
 *
 * @description The Processor ID scan results so far response.
 */
export interface ProcessorIDScanResultsSoFar {
  /**
   * @description The photo ID next step enumeration integer.
   */
  photoIDNextStepEnumInt: number;

  /**
   * @description The full ID status enumeration integer.
   */
  fullIDStatusEnumInt: number;

  /**
   * @description The face on document status enumeration integer.
   */
  faceOnDocumentStatusEnumInt: number;

  /**
   * @description The text on document status enumeration integer.
   */
  textOnDocumentStatusEnumInt: number;

  /**
   * @description The expected media status enumeration integer.
   */
  expectedMediaStatusEnumInt: number;

  /**
   * @description Indicates if unexpected media was encountered at least once.
   */
  unexpectedMediaEncounteredAtLeastOnce: boolean;

  /**
   * @description The document data in stringified JSON format.
   */
  documentData: string;

  /**
   * @description The NFC status enumeration integer.
   */
  nfcStatusEnumInt: number;

  /**
   * @description The NFC authentication status enumeration integer.
   */
  nfcAuthenticationStatusEnumInt: number;

  /**
   * @description The barcode status enumeration integer.
   */
  barcodeStatusEnumInt: number;

  /**
   * @description The MRZ status enumeration integer.
   */
  mrzStatusEnumInt: number;

  /**
   * @description Indicates if the ID was found without quality issues.
   */
  idFoundWithoutQualityIssueDetected: boolean;

  /**
   * @description Indicates if the face photo was found without quality issues.
   */
  idFacePhotoFoundWithoutQualityIssueDetected: boolean;

  /**
   * @description The ID scan age group enumeration integer.
   */
  idScanAgeV2GroupEnumInt: number;

  /**
   * @description Indicates if the ID scan matched the OCR template.
   */
  didMatchIDScanToOCRTemplate: boolean;

  /**
   * @description Indicates if the universal ID mode is enabled.
   */
  isUniversalIDMode: boolean;

  /**
   * @description The match level.
   */
  matchLevel: number;

  /**
   * @description The match level NFC to face map.
   */
  matchLevelNFCToFaceMap: number;

  /**
   * @description The face map age group enumeration integer.
   */
  faceMapAgeV2GroupEnumInt: number;

  /**
   * @description The watermark and hologram status enumeration integer.
   */
  watermarkAndHologramStatusEnumInt: number;
}

/**
 * @interface ProcessorData
 *
 * @description The Processor data response.
 */
export interface ProcessorData {
  /**
   * @description The unique identifier for the ID scan session.
   *
   * @processors `photoScan` and `photoMatch` methods.
   */
  idScanSessionId?: string | null;

  /**
   * @description The unique identifier for the face session.
   *
   * @processors `enroll`, `authenticate`, `photoMatch` methods.
   */
  externalDatabaseRefID?: string | null;

  /**
   * @description The additional session data.
   *
   * @processors All methods.
   */
  additionalSessionData: ProcessorAdditionalSessionData;

  /**
   * @description The result of the processing.
   *
   * @processors `enroll`, `authenticate`, and `liveness` methods.
   */
  result?: ProcessorResult | null;

  /**
   * @description The raw response blob from the server.
   *
   * @processors All methods.
   */
  responseBlob: string;

  /**
   * @description The HTTP call information.
   *
   * @processors All methods.
   */
  httpCallInfo: ProcessorHttpCallInfo;

  /**
   * @description Indicates if an error occurred during processing.
   *
   * @processors All methods.
   */
  didError: boolean;

  /**
   * @description The server information.
   *
   * @processors All methods.
   */
  serverInfo?: unknown | null;

  /**
   * @description The ID scan results so far.
   *
   * @processors `photoScan` and `photoMatch` methods.
   */
  idScanResultsSoFar?: ProcessorIDScanResultsSoFar | null;
}

/**
 * @interface ProcessorError
 *
 * @description The Processor error response.
 */
export interface ProcessorError {
  /**
   * @description The error code.
   */
  code: Errors;

  /**
   * @description The error message.
   */
  message: string;
}

/**
 * @interface Processor
 *
 * @description The Processor response.
 */
export interface Processor {
  /**
   * @description Indicates if the processing was successful.
   */
  isSuccess: boolean;

  /**
   * @description The processor data response.
   */
  data?: ProcessorData | null;

  /**
   * @description The processor error response.
   */
  error?: ProcessorError | null;
}

/**
 * @interface Methods
 *
 * @description This is the available methods in Aziface SDK.
 */
export interface Methods extends TurboModule {
  /**
   * @description This is the **principal** method to be called, he must be
   * **called first** to initialize the Aziface SDK. If he doens't be called
   * the other methods **don't works!**
   *
   * @param {Params} params - Initialization SDK parameters.
   * @param {Headers} headers - Headers your requests, to each
   * request it's sent. The headers is optional.
   *
   * @return {Promise<boolean>} Represents if initialization was a successful.
   */
  initialize(params: Params, headers?: Headers): Promise<boolean>;

  /**
   * @description This method make to read from face and documents for user,
   * after compare face and face documents from user to check veracity.
   *
   * @param {any|undefined} data - The object with data to be will send on photo
   * ID match. The data is optional.
   *
   * @return {Promise<Processor>} Represents if photo match was a successful.
   *
   * @throws If photo ID match was a unsuccessful or occurred some
   * interference.
   */
  photoIDMatch(data?: any): Promise<Processor>;

  /**
   * @description This method makes to read from documents for user, checking
   * in your server the veracity it.
   *
   * @param {any|undefined} data - The object with data to be will send on photo
   * ID scan only. The data is optional.
   *
   * @return {Promise<Processor>} Represents if photo scan only was a
   * successful.
   *
   * @throws If photo ID scan only was a unsuccessful or occurred some
   * interference.
   */
  photoIDScanOnly(data?: any): Promise<Processor>;

  /**
   * @description This method makes a 3D reading of the user's face. But, you
   * must use to **subscribe** user in Aziface SDK or in your server.
   *
   * @param {any|undefined} data - The object with data to be will send on
   * enrollment. The data is optional.
   *
   * @return {Promise<Processor>} Represents if enrollment was a successful.
   *
   * @throws If enrollment was a unsuccessful or occurred some interference.
   */
  enroll(data?: any): Promise<Processor>;

  /**
   * @description This method makes a 3D reading of the user's face, it's an
   * equal `enroll` method, but it must be used to **authenticate** your user.
   * An important detail about it is, you must **subscribe** to your user
   * **first**, after authenticating it with this method.
   *
   * @param {any|undefined} data - The object with data to be will send on
   * enrollment. The data is optional.
   *
   * @return {Promise<Processor>} Represents if authenticate was a successful.
   *
   * @throws If authenticate was a unsuccessful or occurred some interference.
   */
  authenticate(data?: any): Promise<Processor>;

  /**
   * @description This method makes a 3D reading of the user's face, ensuring
   * the liveness check of the user.
   *
   * @param {any|undefined} data - The object with data to be will send on
   * enrollment. The data is optional.
   *
   * @return {Promise<Processor>} Represents if liveness was a successful.
   *
   * @throws If liveness was a unsuccessful or occurred some interference.
   */
  liveness(data?: any): Promise<Processor>;

  /**
   * @description This method must be used to **set** the **theme** of the
   * Aziface SDK screen.
   *
   * @param {Theme|undefined} options - The object theme options.
   * All options are optional.
   *
   * @return {void}
   */
  setTheme(options?: Theme): void;

  /**
   * @description This method must be used to **activate** the vocal guidance
   * of the Aziface SDK.
   *
   * @return {void}
   */
  vocal(): void;
}

/**
 * @description This method must be used to **activate** the vocal guidance
 * of the Aziface SDK.
 *
 * @return {void}
 */
export function vocal(): void {
  AzifaceMobile.vocal();
}

/**
 * @description This is the **principal** method to be called, he must be
 * **called first** to initialize the Aziface SDK. If he doesn't be called the
 * other methods **don't works!**
 *
 * @param {Initialize} initialize - Initialize the Aziface SDK with
 * specific parameters and an optional headers.
 *
 * @return {Promise<boolean>} Represents if Aziface SDK initialized with
 * successful.
 */
export async function initialize({
  params,
  headers,
}: Initialize): Promise<boolean> {
  return await AzifaceMobile.initialize(params, headers)
    .then((successful) => successful)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method make to read from face and documents for user,
 * after compare face and face documents from user to check veracity.
 *
 * @param {object|undefined} data - The object with data to be will send on
 * photo ID match. The data is optional.
 *
 * @return {Promise<Processor>} Represents if photo match was a successful.
 *
 * @throws If photo ID match was a unsuccessful or occurred some interference.
 */
export async function photoMatch(data?: object): Promise<Processor> {
  return await AzifaceMobile.photoIDMatch(data)
    .then((response) => JSON.parse(response) as Processor)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method makes to read from documents for user, checking
 * in your server the veracity it.
 *
 * @param {object|undefined} data - The object with data to be will send on
 * photo ID scan only. The data is optional.
 *
 * @return {Promise<Processor>} Represents if photo scan only was a successful.
 *
 * @throws If photo ID scan only was a unsuccessful or occurred some
 * interference.
 */
export async function photoScan(data?: object): Promise<Processor> {
  return await AzifaceMobile.photoIDScanOnly(data)
    .then((response) => JSON.parse(response) as Processor)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method makes a 3D reading of the user's face. But, you must
 * use to **subscribe** user in Aziface SDK or in your server.
 *
 * @param {object|undefined} data - The object with data to be will send on
 * enrollment. The data is optional.
 *
 * @return {Promise<Processor>} Represents if enrollment was a successful.
 *
 * @throws If enrollment was a unsuccessful or occurred some interference.
 */
export async function enroll(data?: object): Promise<Processor> {
  return await AzifaceMobile.enroll(data)
    .then((response) => JSON.parse(response) as Processor)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method makes a 3D reading of the user's face, it's an
 * equal `enroll` method, but it must be used to **authenticate** your user.
 * An important detail about it is, you must **subscribe** to your user
 * **first**, after authenticating it with this method.
 *
 * @param {object|undefined} data - The object with data to be will send on
 * enrollment. The data is optional.
 *
 * @return {Promise<Processor>} Represents if authenticate was a successful.
 *
 * @throws If authenticate was a unsuccessful or occurred some interference.
 */
export async function authenticate(data?: object): Promise<Processor> {
  return await AzifaceMobile.authenticate(data)
    .then((response) => JSON.parse(response) as Processor)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method makes a 3D reading of the user's face, ensuring
 * the liveness check of the user.
 *
 * @param {object|undefined} data - The object with data to be will send on
 * enrollment. The data is optional.
 *
 * @return {Promise<Processor>} Represents if liveness was a successful.
 *
 * @throws If liveness was a unsuccessful or occurred some interference.
 */
export async function liveness(data?: object): Promise<Processor> {
  return await AzifaceMobile.liveness(data)
    .then((response) => JSON.parse(response) as Processor)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method must be used to **set** the **theme** of the Aziface
 * SDK screen.
 *
 * @param {Theme|undefined} options - The object theme options. All options are
 * optional.
 *
 * @return {void}
 */
export function setTheme(options?: Theme): void {
  AzifaceMobile.setTheme(options);
}

// Components

/**
 * @interface FaceViewProps
 *
 * @description Props for the FaceView component. The FaceView component is
 * responsible for displaying the user's face during processes. It informs
 * the user about the current status of the face modal.
 */
export interface FaceViewProps extends ViewProps {
  /**
   * @description Callback function called when the Aziface SDK is opened.
   *
   * @param {boolean} opened - Indicates if the Aziface SDK is opened.
   *
   * @return {void}
   */
  onOpen?: (opened: boolean) => void;

  /**
   * @description Callback function called when the Aziface SDK is closed.
   *
   * @param {boolean} closed - Indicates if the Aziface SDK is closed.
   *
   * @return {void}
   */
  onClose?: (closed: boolean) => void;

  /**
   * @description Callback function called when the Aziface SDK is cancelled.
   *
   * @param {boolean} cancelled - Indicates if the Aziface SDK is cancelled.
   *
   * @return {void}
   */
  onCancel?: (cancelled: boolean) => void;

  /**
   * @description Callback function called when an error occurs in the Aziface
   * SDK.
   *
   * @param {boolean} error - Indicates if an error occurred in the Aziface SDK.
   *
   * @return {void}
   */
  onError?: (error: boolean) => void;

  /**
   * @description Callback function called when the Aziface SDK is initialized.
   *
   * @param {boolean} initialized - Indicates if the Aziface SDK is initialized.
   *
   * @return {void}
   */
  onInitialize?: (initialized: boolean) => void;

  /**
   * @description Callback function called when the vocal guidance is activated.
   *
   * @param {boolean} activated - Indicates if the vocal guidance is activated.
   *
   * @returns {void}
   */
  onVocal?: (activated: boolean) => void;
}

export function FaceView(props: FaceViewProps) {
  const {
    children,
    onCancel,
    onClose,
    onError,
    onOpen,
    onVocal,
    onInitialize,
    ...rest
  } = props;

  useEffect(() => {
    const subscriptions = [
      onInitialize && handleInitialize(onInitialize),
      onOpen && handleOpen(onOpen),
      onClose && handleClose(onClose),
      onCancel && handleCancel(onCancel),
      onError && handleError(onError),
      onVocal && handleVocal(onVocal),
    ].filter(Boolean) as EventSubscription[];

    return () => {
      subscriptions.forEach((subscription) => subscription.remove());
    };
  }, [onCancel, onClose, onError, onVocal, onOpen, onInitialize]);

  return <View {...rest}>{children}</View>;
}

import { type ViewProps } from 'react-native';

/**
 * @type
 *
 * @description The type of button location.
 *
 * @default "TOP_RIGHT"
 */
export type ButtonLocation = 'DISABLED' | 'TOP_LEFT' | 'TOP_RIGHT';

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
 * @interface ThemeImage
 *
 * @description An object containing the image assets used in the Aziface SDK.
 */
export interface ThemeImage {
  /**
   * @description The logo image to will be used in Aziface SDK screen.
   * **Note**: The image name must be to inserted with no extension format.
   *
   * @default "facetec_your_app_logo.png"
   */
  logo?: string;

  /**
   * @description The icon cancel button to will be used in Aziface SDK screen.
   * The image name must be to inserted with no extension format.
   *
   * @default "facetec_cancel.png"
   */
  cancel?: string;
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
   * @description Represents the border color style of the oval view row
   * of the guidance retry.
   *
   * @default '#ffffff'
   */
  ovalStrokeColor?: string;
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
   * @description Represents the progress bar fill color style of the result
   * screen during loading.
   *
   * @default '#026ff4'
   */
  uploadProgressFillColor?: string;

  /**
   * @description Represents the result animation styles of the result screen.
   *
   * @default undefined
   */
  resultAnimation?: ThemeResultAnimation;
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
   * @description The button location in Aziface SDK screen.
   *
   * @default "TOP_RIGHT"
   */
  cancelButtonLocation?: ButtonLocation;

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

/**
 * @interface Methods
 *
 * @description This is the available methods in Aziface SDK.
 */
export interface Methods {
  /**
   * @description This is the **principal** method to be called, he must be
   * **called first** to initialize the Aziface SDK. If he doens't be called
   * the other methods **don't works!**
   *
   * @param {Params} params - Initialization SDK parameters.
   * @param {Headers} headers - Headers your requests, to each
   * request it's sent. The headers is optional.
   * @param {Function} callback - Callback function to be called after with
   * the response of the successfully. The callback is optional.
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
   * @return {Promise<boolean>} Represents if photo match was a successful.
   *
   * @throws If photo ID match was a unsuccessful or occurred some
   * interference.
   */
  photoIDMatch(data?: any): Promise<boolean>;

  /**
   * @description This method makes to read from documents for user, checking
   * in your server the veracity it.
   *
   * @param {any|undefined} data - The object with data to be will send on photo
   * ID scan only. The data is optional.
   *
   * @return {Promise<boolean>} Represents if photo scan only was a successful.
   *
   * @throws If photo ID scan only was a unsuccessful or occurred some
   * interference.
   */
  photoIDScanOnly(data?: any): Promise<boolean>;

  /**
   * @description This method makes a 3D reading of the user's face. But, you
   * must use to **subscribe** user in Aziface SDK or in your server.
   *
   * @param {any|undefined} data - The object with data to be will send on
   * enrollment. The data is optional.
   *
   * @return {Promise<boolean>} Represents if enrollment was a successful.
   *
   * @throws If enrollment was a unsuccessful or occurred some interference.
   */
  enroll(data?: any): Promise<boolean>;

  /**
   * @description This method makes a 3D reading of the user's face, it's an
   * equal `enroll` method, but it must be used to **authenticate** your user.
   * An important detail about it is, you must **subscribe** to your user
   * **first**, after authenticating it with this method.
   *
   * @param {any|undefined} data - The object with data to be will send on
   * enrollment. The data is optional.
   *
   * @return {Promise<boolean>} Represents if authenticate was a successful.
   *
   * @throws If authenticate was a unsuccessful or occurred some interference.
   */
  authenticate(data?: any): Promise<boolean>;

  /**
   * @description This method makes a 3D reading of the user's face, ensuring
   * the liveness check of the user.
   *
   * @param {any|undefined} data - The object with data to be will send on
   * enrollment. The data is optional.
   *
   * @return {Promise<boolean>} Represents if liveness was a successful.
   *
   * @throws If liveness was a unsuccessful or occurred some interference.
   */
  liveness(data?: any): Promise<boolean>;

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
   *
   * @platform Android
   */
  vocal(): void;
}

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
}

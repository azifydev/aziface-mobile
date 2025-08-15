import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package '@azify/aziface-mobile' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

/**
 * @type
 *
 * @description The type of button location.
 *
 * @default "TOP_RIGHT"
 */
export type ButtonLocation = 'DISABLED' | 'TOP_LEFT' | 'TOP_RIGHT';

/**
 * @type
 *
 * @description The type of status bar color. Note: The status bar color is
 * `DEFAULT` if device is less than iOS 13.
 *
 * @default "DARK_CONTENT"
 */
export type StatusBarColor = 'DARK_CONTENT' | 'DEFAULT' | 'LIGHT_CONTENT';

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
 * @interface DefaultMessage
 *
 * @description Represents the success message and loading data message
 * during to Aziface SDK flow. It interface is used **more** by processors's
 * `authenticate`, `enroll` and `liveness` processors.
 */
export interface DefaultMessage {
  /**
   * @description Success message when the process is completed successfully.
   *
   * @description For Liveness flow.
   *
   * @default "Liveness Confirmed"
   *
   * @description For Enrollment flow.
   *
   * @default "Face Scanned\n3D Liveness Proven"
   *
   * @description For Authenticate flow.
   *
   * @default "Authenticated"
   */
  successMessage?: string;

  /**
   * @description Success message when the process is completed successfully.
   *
   * @default "Still Uploading..."
   *
   * @platform iOS
   */
  uploadMessage?: string;
}

/**
 * @interface DefaultScanMessageFrontSide
 *
 * @description Represents the front-side scan messages during to Aziface SDK
 * flow.
 */
export interface DefaultScanMessageFrontSide {
  /**
   * @description Upload of ID front-side has started.
   *
   * @default "Uploading Encrypted ID Scan"
   */
  uploadStarted?: string;

  /**
   * @description Upload of ID front-side is still uploading to Server after
   * an extended period of time.
   *
   * @default "Still Uploading... Slow Connection"
   */
  stillUploading?: string;

  /**
   * @description Upload of ID front-side to the Server is complete.
   *
   * @default "Upload Complete"
   */
  uploadCompleteAwaitingResponse?: string;

  /**
   * @description Upload of ID front-side is complete and we are waiting for
   * the Server to finish processing and respond.
   *
   * @default "Processing ID Scan"
   */
  uploadCompleteAwaitingProcessing?: string;
}

/**
 * @interface DefaultScanMessageBackSide
 *
 * @description Represents the back-side scan messages during to Aziface SDK
 * flow.
 */
export interface DefaultScanMessageBackSide {
  /**
   * @description Upload of ID back-side has started.
   *
   * @default "Uploading Encrypted ID Scan"
   */
  uploadStarted?: string;

  /**
   * @description Upload of ID back-side is still uploading to Server after
   * an extended period of time.
   *
   * @default "Still Uploading... Slow Connection"
   */
  stillUploading?: string;

  /**
   * @description Upload of ID back-side to the Server is complete.
   *
   * @default "Upload Complete"
   */
  uploadCompleteAwaitingResponse?: string;

  /**
   * @description Upload of ID back-side is complete and we are waiting for
   * the Server to finish processing and respond.
   *
   * @default "Processing Back of ID"
   */
  uploadCompleteAwaitingProcessing?: string;
}

/**
 * @interface DefaultScanMessageUserConfirmInfo
 *
 * @description Represents the user confirmed information messages during to
 * Aziface SDK flow.
 */
export interface DefaultScanMessageUserConfirmInfo {
  /**
   * @description Upload of User Confirmed Info has started.
   *
   * @default "Uploading Your Confirmed Info"
   */
  uploadStarted?: string;

  /**
   * @description Upload of User Confirmed Info is still uploading to Server
   * after an extended period of time.
   *
   * @default "Still Uploading... Slow Connection"
   */
  stillUploading?: string;

  /**
   * @description Upload of User Confirmed Info to the Server is complete.
   *
   * @default "Upload Complete"
   */
  uploadCompleteAwaitingResponse?: string;

  /**
   * @description Upload of User Confirmed Info is complete and we are waiting
   * for the Server to finish processing and respond.
   *
   * @default "Processing"
   */
  uploadCompleteAwaitingProcessing?: string;
}

/**
 * @interface DefaultScanMessageNFC
 *
 * @description Represents the NFC scan messages during to Aziface SDK flow.
 */
export interface DefaultScanMessageNFC {
  /**
   * @description Upload of NFC Details has started.
   *
   * @default "Uploading Encrypted NFC Details"
   */
  uploadStarted?: string;

  /**
   * @description Upload of NFC Details is still uploading to Server after an
   * extended period of time.
   *
   * @default "Still Uploading... Slow Connection"
   */
  stillUploading?: string;

  /**
   * @description Upload of NFC Details to the Server is complete.
   *
   * @default "Upload Complete"
   */
  uploadCompleteAwaitingResponse?: string;

  /**
   * @description Upload of NFC Details is complete and we are waiting for the
   * Server to finish processing and respond.
   *
   * @default "Processing NFC Details"
   */
  uploadCompleteAwaitingProcessing?: string;
}

/**
 * @interface DefaultScanMessageSkippedNFC
 *
 * @description Represents the skipped NFC scan messages during to Aziface SDK
 * flow.
 */
export interface DefaultScanMessageSkippedNFC {
  /**
   * @description Upload of ID Details has started.
   *
   * @default "Uploading Encrypted ID Details"
   */
  uploadStarted?: string;

  /**
   * @description Upload of ID Details is still uploading to Server after an
   * extended period of time.
   *
   * @default "Still Uploading... Slow Connection"
   */
  stillUploading?: string;

  /**
   * @description Upload of ID Details to the Server is complete.
   *
   * @default "Upload Complete"
   */
  uploadCompleteAwaitingResponse?: string;

  /**
   * @description Upload of ID Details is complete and we are waiting for the
   * Server to finish processing and respond.
   *
   * @default "Processing ID Details"
   */
  uploadCompleteAwaitingProcessing?: string;
}

/**
 * @interface DefaultScanMessageSuccess
 *
 * @description Represents the success messages during the Aziface SDK flow.
 */
export interface DefaultScanMessageSuccess {
  /**
   * @description Successful scan of ID front-side (ID Types with no
   * back-side).
   *
   * @default "ID Scan Complete"
   */
  frontSide?: string;

  /**
   * @description Successful scan of ID front-side (ID Types that do have a
   * back-side).
   *
   * @default "Front of ID Scanned"
   */
  frontSideBackNext?: string;

  /**
   * @description Successful scan of ID front-side (ID Types that do have
   * NFC but do not have a back-side).
   *
   * @default "Front of ID Scanned"
   */
  frontSideNFCNext?: string;

  /**
   * @description Successful scan of the ID back-side (ID Types that do not
   * have NFC).
   *
   * @default "ID Scan Complete"
   */
  backSide?: string;

  /**
   * @description Successful scan of the ID back-side (ID Types that do have
   * NFC).
   *
   * @default "Back of ID Scanned"
   */
  backSideNFCNext?: string;

  /**
   * @description Successful scan of a Passport that does not have NFC.
   *
   * @default "Passport Scan Complete"
   */
  passport?: string;

  /**
   * @description Successful scan of a Passport that does have NFC.
   *
   * @default "Passport Scanned"
   */
  passportNFCNext?: string;

  /**
   * @description Successful upload of final IDScan containing
   * User-Confirmed ID Text.
   *
   * @default "Photo ID Scan Complete"
   */
  userConfirmation?: string;

  /**
   * @description Successful upload of the scanned NFC chip information.
   *
   * @default "ID Scan Complete"
   */
  NFC?: string;
}

/**
 * @interface DefaultScanMessageRetry
 *
 * @description Represents the retry messages during the Aziface SDK flow.
 */
export interface DefaultScanMessageRetry {
  /**
   * @description Case where a Retry is needed because the Face on the Photo
   * ID did not Match the User's Face highly enough.
   *
   * @default "Face Didn’t Match Highly Enough"
   */
  faceDidNotMatch?: string;

  /**
   * @description Case where a Retry is needed because a Full ID was not
   * detected with high enough confidence.
   *
   * @default "ID Document Not Fully Visible"
   */
  IDNotFullyVisible?: string;

  /**
   * @description Case where a Retry is needed because the OCR did not
   * produce good enough results and the User should Retry with a better
   * capture.
   *
   * @default "ID Text Not Legible"
   */
  OCRResultsNotGoodEnough?: string;

  /**
   * @description Case where there is likely no OCR Template installed for
   * the document the User is attempting to scan.
   *
   * @default "ID Type Mismatch Please Try Again"
   */
  IDTypeNotSupported?: string;
}

/**
 * @interface DefaultScanMessage
 *
 * @description Represents the all scan messages during to Aziface SDK flow.
 * It interface is used by processors's `photoScan` and `photoMatch`
 * processors.
 */
export interface DefaultScanMessage {
  /**
   * @description Case where NFC Scan was skipped due to the user's
   * interaction or an unexpected error.
   *
   * @default "ID Details Uploaded"
   */
  skipOrErrorNFC?: string;

  /**
   * @description Configuration object for customizing UI messages during the
   * front-side ID document upload and processing workflow. Contains localized
   * text strings displayed to users at different stages of the upload process,
   * from initial upload through server processing completion.
   *
   * @default undefined
   */
  frontSide?: DefaultScanMessageFrontSide;

  /**
   * @description Configuration object for customizing UI messages during the
   * back-side ID document upload and processing workflow. Contains localized
   * text strings displayed to users at different stages of the upload process,
   * from initial upload through server processing completion.
   *
   * @default undefined
   */
  backSide?: DefaultScanMessageBackSide;

  /**
   * @description Configuration object for customizing UI messages during the
   * user confirmed information upload and processing workflow. Contains
   * localized text strings displayed to users at different stages of the
   * upload process, from initial upload through server processing completion.
   *
   * @default undefined
   */
  userConfirmedInfo?: DefaultScanMessageUserConfirmInfo;

  /**
   * @description Configuration object for customizing UI messages during the
   * NFC (Near Field Communication) data upload and processing workflow.
   * Contains localized text strings displayed to users at different stages of
   * the upload process, from initial upload through server processing
   * completion.
   *
   * @default undefined
   */
  nfc?: DefaultScanMessageNFC;

  /**
   * @description Configuration object for customizing UI messages during the
   * ID details upload and processing workflow when NFC scanning was skipped.
   * Contains localized text strings displayed to users at different stages of
   * the upload process, from initial upload through server processing
   * completion, specifically for scenarios where NFC data reading was bypassed.
   *
   * @default undefined
   */
  skippedNFC?: DefaultScanMessageSkippedNFC;

  /**
   * @description Configuration object for customizing success messages
   * displayed after successful completion of various ID scanning and
   * processing stages. Contains localized text strings for different document
   * types and scanning scenarios, including front/back sides, NFC chip
   * reading, passports, and user confirmation steps. Messages are
   * contextually displayed based on document type and available features.
   *
   * @default undefined
   */
  success?: DefaultScanMessageSuccess;

  /**
   * @description Configuration object for customizing error messages that
   * prompt users to retry the ID scanning process. Contains localized text
   * strings for various failure scenarios that require user action to rescan
   * or recapture their ID document, including face matching issues,
   * visibility problems, text legibility, and unsupported document types.
   *
   * @default undefined
   */
  retry?: DefaultScanMessageRetry;
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
   * @description The status bar color style of the device during the Aziface
   * SDK flow.
   *
   * @default "DARK_CONTENT"
   *
   * @platform iOS
   */
  defaultStatusBarColor?: StatusBarColor;

  /**
   * @description An object with all messages to will be used the during the
   * authentication flow.
   *
   * @default undefined
   */
  authenticateMessage?: DefaultMessage;

  /**
   * @description An object with all messages to will be used the during the
   * enrollment flow.
   *
   * @default undefined
   */
  enrollMessage?: DefaultMessage;

  /**
   * @description An object with all messages to will be used the during the
   * liveness flow.
   *
   * @default undefined
   */
  livenessMessage?: DefaultMessage;

  /**
   * @description An object with all messages to will be used the during the
   * photo ID scan flow.
   *
   * @default undefined
   */
  scanMessage?: DefaultScanMessage;

  /**
   * @description An object with all messages to will be used the during the
   * photo ID match flow.
   *
   * @default undefined
   */
  matchMessage?: DefaultScanMessage & DefaultMessage;

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
  device: string;

  /**
   * @description Your base URL to will be used to sent data.
   */
  url: string;

  /**
   * @description Your public key to will be used to initialize Aziface SDK.
   * Available in your Aziface account.
   */
  key: string;

  /**
   * @description Your production key to will be used to initialize Aziface SDK.
   * Available in your Aziface account.
   */
  productionKey: string;

  /**
   * @description Option to select production or development mode for
   * initialize Aziface SDK.
   */
  isDeveloperMode: boolean;

  /**
   * @description The id of the user process.
   */
  processId: string;
}

/**
 * @interface SessionBasePathUrl
 *
 * @description This is target properties of the path session.
 */
interface SessionBasePathUrl {
  /**
   * @description The base URL used for the session.
   *
   * @default undefined
   */
  base?: string;
}

/**
 * @interface SessionMatchPathUrl
 *
 * @description This is target properties of the session with multiple path
 * urls.
 */
interface SessionMatchPathUrl extends SessionParams {
  /**
   * @description The match URL used for the session with multiple
   * verifications.
   *
   * @default undefined
   */
  match?: string;
}

/**
 * @interface SessionParams
 *
 * @description This is the parameters for the session.
 */
export interface SessionParams<T = 'base'> {
  /**
   * @description The object path URL for the session.
   *
   * @default undefined
   */
  pathUrl?: T extends 'base' ? SessionBasePathUrl : SessionMatchPathUrl;

  [key: string]: any;
}

/**
 * @enum
 *
 * @description This is the enum Errors that can be throws by Aziface SDK.
 */
export enum Errors {
  /**
   * @description When some processors method is running, but Aziface SDK
   * **has not been initialized!**.
   */
  AziFaceHasNotBeenInitialized = 'AziFaceHasNotBeenInitialized',

  /**
   * @description When the image sent to the processors cannot be processed
   * due to inconsistency.
   */
  AziFaceValuesWereNotProcessed = 'AziFaceValuesWereNotProcessed',

  /**
   * @description When exists some network error.
   */
  HTTPSError = 'HTTPSError',

  /**
   * @description When exists some problem in getting data in request of
   * **base URL** information.
   *
   * @platform Android
   */
  JSONError = 'JSONError',

  /**
   * @description When session status is invalid.
   *
   * @platform Android
   */
  AziFaceInvalidSession = 'AziFaceInvalidSession',

  /**
   * @description When session status is different of success.
   *
   * @platform Android
   */
  AziFaceTecDifferentStatus = 'AziFaceTecDifferentStatus',

  /**
   * @description When the image ID sent to the processors cannot be processed
   * due to inconsistency.
   *
   * @platform Android
   */
  AziFaceScanValuesWereNotProcessed = 'AziFaceScanValuesWereNotProcessed',
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
  initializeSdk(
    params: Params,
    headers?: Headers,
    callback?: Function
  ): Promise<boolean>;

  /**
   * @description This method make to read from face and documents for user,
   * after comparate face and face documents from user to check veracity.
   *
   * @param {Object|undefined} data - The object with data to be will send on
   * photo ID match. The data is optional.
   *
   * @return {Promise<boolean>} Represents if photo match was a successful.
   * @throws If photo ID match was a unsuccessful or occurred some
   * interference.
   */
  handlePhotoIDMatch(data?: Object): Promise<boolean>;

  /**
   * @description This method makes a 3D reading of the user's face. But, you
   * must use to **subscribe** user in Aziface SDK or in your server.
   *
   * @param {Object|undefined} data - The object with data to be will send on
   * enrollment. The data is optional.
   *
   * @return {Promise<boolean>} Represents if enrollment was a successful.
   * @throws If enrollment was a unsuccessful or occurred some interference.
   */
  handleEnrollUser(data?: Object): Promise<boolean>;

  /**
   * @description This method makes a 3D reading of the user's face. But, you
   * must use to **authenticate** user in Aziface SDK or in your server.
   *
   * @param {Object|undefined} data - The object with data to be will send on
   * authentication. The data is optional.
   *
   * @return {Promise<boolean>} Represents if authentication was a successful.
   * @throws If authenticate was a unsuccessful or occurred some interference.
   */
  handleAuthenticateUser(data?: Object): Promise<boolean>;

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
}

/**
 * @description Native module Aziface SDK, it's recommended use it with event
 * types.
 *
 * @example
 * import { NativeEventEmitter } from 'react-native';
 * import AzifaceMobileSdk from '@azify/aziface-mobile';
 *
 * const emitter = new NativeEventEmitter(AzifaceMobileSdk);
 * emitter.addListener('onCloseModal', (event: boolean) => console.log('onCloseModal', event));
 */
export const AzifaceMobileSdk: Methods = NativeModules?.AzifaceMobileSdk
  ? NativeModules.AzifaceMobileSdk
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

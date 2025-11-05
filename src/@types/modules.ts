import type { TurboModule } from 'react-native';
import type { Theme } from './styles';
import type { Errors } from './errors';

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
  serverInfo: unknown | null;

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

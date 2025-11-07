import type { ViewProps } from 'react-native';

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

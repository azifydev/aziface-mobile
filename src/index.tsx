import { NativeModules, Platform } from 'react-native';

import { NATIVE_CONSTANTS } from './constants';
import { AzifaceSdkProps } from './types';

import type { NativeModule } from 'react-native';
import { convertThemePropsToHexColor } from './helpers';

const LINKING_ERROR =
  `The package '@azify/aziface-mobile' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

/**
 * @description Native module AzifaceSDK, it's recommended use it with event
 * types.
 *
 * @example
 * import { NativeEventEmitter } from 'react-native';
 * import AzifaceMobileSdk from '@azify/aziface-mobile';
 *
 * const emitter = new NativeEventEmitter(AzifaceMobileSdk);
 * emitter.addListener('onCloseModal', (event: boolean) => console.log('onCloseModal', event));
 */
const AzifaceSdk = NativeModules.AzifaceMobileSdk
  ? NativeModules.AzifaceMobileSdk
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export default AzifaceSdk as NativeModule;

/**
 * @description This is the **principal** method to be called, he must be
 * **called first** to initialize the Aziface SDK. If he doens't be called the
 * other methods **don't works!**
 *
 * @param {AzifaceSdkProps.Initialize} initialize - Initialize the Aziface SDK with
 * especific parameters and an optional headers.
 *
 * @return {Promise<boolean>} Represents if Aziface SDK initialized with
 * successful.
 */
export function initialize({
  params,
  headers,
}: AzifaceSdkProps.Initialize): Promise<boolean> {
  return new Promise((resolve, reject) => {
    AzifaceSdk.initializeSdk(params, headers, (successful: boolean) => {
      if (successful) resolve(true);
      else reject(false);
    });
  });
}

/**
 * @description This method is called to make enrollment, authenticate and
 * liveness available. The **enrollment method** makes a 3D reading of the
 * user's face. But, you must use to **subscribe** user in Aziface SDK or in
 * your server. The **authenticate method** makes a 3D reading of the user's
 * face. But, you must use to **authenticate** user in Aziface SDK or in
 * your server. Finally, the **liveness** method makes a 3D reading of the
 * user's face.
 *
 * @param {AzifaceSdkProps.MatchType} type - The type of flow to be called.
 * @param {AzifaceSdkProps.MatchData|undefined} data - The object with properties
 * that will be sent to native modules to make the requests, change text labels
 * and sent parameters via headers.
 *
 * @return {Promise<boolean>} Represents if flow was a successful.
 * @throws If was a unsuccessful or occurred some interference.
 */
export async function faceMatch(
  type: AzifaceSdkProps.MatchType,
  data?: AzifaceSdkProps.MatchData
): Promise<boolean> {
  return await AzifaceSdk.handleFaceUser(NATIVE_CONSTANTS(data)[type])
    .then((successful: boolean) => successful)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method make to read from face and documents for user,
 * after comparate face and face documents from user to check veracity.
 *
 * @param {Object|undefined} data - The object with data to be will send on
 * photo ID match. The data is optional.
 *
 * @return {Promise<boolean>} Represents if photo match was a successful.
 * @throws If photo ID match was a unsuccessful or occurred some interference.
 */
export async function photoMatch(data?: Object): Promise<boolean> {
  return await AzifaceSdk.handlePhotoIDMatch(data)
    .then((successful: boolean) => successful)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method must be used to **set** the **theme** of the Aziface
 * SDK screen.
 *
 * @param {AzifaceSdkProps.Theme|undefined} options - The object theme options.
 * All options are optional.
 *
 * @return {void}
 */
export function setTheme(options?: AzifaceSdkProps.Theme): void {
  AzifaceSdk.handleTheme(convertThemePropsToHexColor(options));
}

export * from './types';

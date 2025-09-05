import { NativeModules, Platform } from 'react-native';
import type { Initialize, Methods, Theme } from '../types';

const LINKING_ERROR =
  `The package '@azify/aziface-mobile' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

export const AzifaceModule: Methods = NativeModules?.AzifaceModule
  ? NativeModules.AzifaceModule
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

/**
 * @description This method must be used to **activate** the vocal guidance
 * of the Aziface SDK.
 *
 * @return {void}
 */
export function vocal(): void {
  AzifaceModule.vocal();
}

/**
 * @description This method checks if the vocal guidance is activated in
 * the Aziface SDK.
 *
 * @return {boolean} Returns true if the vocal guidance is activated,
 * otherwise false.
 */
export function isVocalEnabled(): boolean {
  return AzifaceModule.isVocalEnabled();
}

/**
 * @description This is the **principal** method to be called, he must be
 * **called first** to initialize the Aziface SDK. If he doesn't be called the
 * other methods **don't works!**
 *
 * @param {Initialize} initialize - Initialize the Aziface SDK with
 * especific parameters and an optional headers.
 *
 * @return {Promise<boolean>} Represents if Aziface SDK initialized with
 * successful.
 */
export async function initialize({
  params,
  headers,
}: Initialize): Promise<boolean> {
  return await AzifaceModule.initialize(params, headers)
    .then((successful: boolean) => successful)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method make to read from face and documents for user,
 * after comparate face and face documents from user to check veracity.
 *
 * @param {any|undefined} data - The object with data to be will send on photo
 * ID match. The data is optional.
 *
 * @return {Promise<boolean>} Represents if photo match was a successful.
 * @throws If photo ID match was a unsuccessful or occurred some interference.
 */
export async function photoMatch(data?: any): Promise<boolean> {
  return await AzifaceModule.photoIDMatch(data)
    .then((successful: boolean) => successful)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method makes a 3D reading of the user's face. But, you must
 * use to **subscribe** user in Aziface SDK or in your server.
 *
 * @param {any|undefined} data - The object with data to be will send on
 * enrollment. The data is optional.
 *
 * @return {Promise<boolean>} Represents if enrollment was a successful.
 * @throws If enrollment was a unsuccessful or occurred some interference.
 */
export async function enroll(data?: any): Promise<boolean> {
  return await AzifaceModule.enroll(data)
    .then((successful: boolean) => successful)
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
  AzifaceModule.setTheme(options);
}

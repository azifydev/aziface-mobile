import { AzifaceMobileSdk, type Initialize, type Theme } from './types';

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
export function initialize({ params, headers }: Initialize): Promise<boolean> {
  return new Promise((resolve, reject) => {
    AzifaceMobileSdk.initializeSdk(params, headers, (successful: boolean) => {
      successful ? resolve(true) : reject(false);
    });
  });
}

/**
 * @description This method make to read from face and documents for user,
 * after comparate face and face documents from user to check veracity.
 *
 * @param {AzifaceSdk.CommonSessionParams<'match'>|undefined} data - The object
 * with data to be will send on photo ID match. The data is optional.
 *
 * @return {Promise<boolean>} Represents if photo match was a successful.
 * @throws If photo ID match was a unsuccessful or occurred some interference.
 */
export async function photoMatch(
  data?: AzifaceSdk.CommonSessionParams<'match'>
): Promise<boolean> {
  return await AzifaceMobileSdk.handlePhotoIDMatch(data)
    .then((successful: boolean) => successful)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method makes a 3D reading of the user's face. But, you must
 * use to **subscribe** user in Aziface SDK or in your server.
 *
 * @param {AzifaceSdk.CommonSessionParams<'base'>|undefined} data - The object
 * with data to be will send on enrollment. The data is optional.
 *
 * @return {Promise<boolean>} Represents if enrollment was a successful.
 * @throws If enrollment was a unsuccessful or occurred some interference.
 */
export async function enroll(
  data?: AzifaceSdk.CommonSessionParams<'base'>
): Promise<boolean> {
  return await AzifaceMobileSdk.handleEnrollUser(data)
    .then((successful: boolean) => successful)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method makes a 3D reading of the user's face. But, you must
 * use to **authenticate** user in Aziface SDK or in your server.
 *
 * @param {AzifaceSdk.CommonSessionParams<'base'>|undefined} data - The object
 * with data to be will send on authentication. The data is optional.
 *
 * @return {Promise<boolean>} Represents if authentication was a successful.
 * @throws If authenticate was a unsuccessful or occurred some interference.
 */
export async function authenticate(
  data?: AzifaceSdk.CommonSessionParams<'base'>
): Promise<boolean> {
  return await AzifaceMobileSdk.handleAuthenticateUser(data)
    .then((successful: boolean) => successful)
    .catch((error: Error) => {
      throw new Error(error.message);
    });
}

/**
 * @description This method must be used to **set** the **theme** of the Aziface
 * SDK screen.
 *
 * @param {Theme|undefined} options - The object theme options. All
 * options are optional.
 *
 * @return {void}
 */
export function setTheme(options?: Theme): void {
  AzifaceMobileSdk.setTheme(options);
}

export * from './types';

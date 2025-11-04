import type { Theme, Initialize, Processor } from '../@types';
import { AzifaceMobile } from '../turbo';

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
 * especific parameters and an optional headers.
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
    .then((successful) => successful as Processor)
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
    .then((successful) => successful as Processor)
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
    .then((successful) => successful as Processor)
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
    .then((successful) => successful as Processor)
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
    .then((successful) => successful as Processor)
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

import type { EventSubscription } from 'react-native';
import { AzifaceMobile } from '../turbo';

type Listener<T = any> = (value: T) => void;

/**
 * @description This method is used to **listen** when the Aziface SDK is
 * initialized.
 *
 * @param listener - The listener function that will be called when the event
 * is emitted.
 *
 * @returns {EventSubscription}
 */
export function onInitialize(listener: Listener<boolean>): EventSubscription {
  return AzifaceMobile.onInitialize(listener);
}

/**
 * @description This method is used to **listen** when the Aziface SDK is
 * opened.
 *
 * @param listener - The listener function that will be called when the event
 * is emitted.
 *
 * @returns {EventSubscription}
 */
export function onOpen(listener: Listener<boolean>): EventSubscription {
  return AzifaceMobile.onOpen(listener);
}

/**
 * @description This method is used to **listen** when the Aziface SDK is
 * closed.
 *
 * @param listener - The listener function that will be called when the event
 * is emitted.
 *
 * @returns {EventSubscription}
 */
export function onClose(listener: Listener<boolean>): EventSubscription {
  return AzifaceMobile.onClose(listener);
}

/**
 * @description This method is used to **listen** when the Aziface SDK is
 * cancelled.
 *
 * @param listener - The listener function that will be called when the event
 * is emitted.
 *
 * @returns {EventSubscription}
 */
export function onCancel(listener: Listener<boolean>): EventSubscription {
  return AzifaceMobile.onCancel(listener);
}

/**
 * @description This method is used to **listen** when the Aziface SDK has
 * error.
 *
 * @param listener - The listener function that will be called when the event
 * is emitted.
 *
 * @returns {EventSubscription}
 */
export function onError(listener: Listener<boolean>): EventSubscription {
  return AzifaceMobile.onError(listener);
}

/**
 * @description This method is used to **listen** the vocal guidance.
 *
 * @param listener - The listener function that will be called when the event
 * is emitted.
 *
 * @returns {EventSubscription}
 */
export function onVocal(listener: Listener<boolean>): EventSubscription {
  return AzifaceMobile.onVocal(listener);
}

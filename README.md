# @azify/aziface-mobile 📱

> [!NOTE]
> This package supports **only** [new architecture](https://reactnative.dev/blog/2024/10/23/the-new-architecture-is-here).

<div align="center">
  <p>
    <img alt="Version" src="https://img.shields.io/github/package-json/v/azifydev/aziface-mobile?style=flat&color=brightgreen">
    <img alt="NPM Downloads" src="https://img.shields.io/npm/dm/%40azify%2Faziface-mobile?style=flat">
    <img alt="Unpacked Size" src="https://img.shields.io/npm/unpacked-size/%40azify%2Faziface-mobile?style=flat&color=brightgreen">
  </p>
</div>

Aziface SDK adapter to react native.

## Summary

- [Installation](#installation)
- [Usage](#usage)
- [API](#api)
  - [`initialize`](#initialize)
    - [Properties](#properties)
  - [`enroll`](#enroll)
    - [Properties](#properties-1)
  - [`authenticate`](#authenticate)
    - [Properties](#properties-2)
  - [`liveness`](#liveness)
    - [Properties](#properties-3)
  - [`photoMatch`](#photomatch)
    - [Properties](#properties-4)
  - [`photoScan`](#photoscan)
    - [Properties](#properties-5)
  - [`setLocale`](#setlocale)
    - [Properties](#properties-6)
  - [`vocal`](#vocal)
- [Types](#types)
  - [`Params`](#azifacesdkparams)
  - [`Headers`](#azifacesdkheaders)
  - [`Processor`](#processor)
    - [`ProcessorData`](#processordata)
      - [`ProcessorAdditionalSessionData`](#processoradditionalsessiondata)
      - [`ProcessorResult`](#processorresult)
      - [`ProcessorHttpCallInfo`](#processorhttpcallinfo)
        - [`ProcessorRequestMethod`](#processorrequestmethod)
      - [`ProcessorIDScanResultsSoFar`](#processoridscanresultssofar)
    - [`ProcessorError`](#processorerror)
  - [`Locale`](#locale)
  - [`Errors`](#errors)
- [Components](#components)
  - [`FaceView`](#faceview)
    - [Properties](#properties-7)
- [Dynamic Strings](#dynamic-strings)
- [Theme](#theme)
- [Vocal Guidance](#vocal-guidance)
- [Enabling Camera (iOS only)](#enabling-camera-ios-only)
- [Integration guide](#integration-guide)
  - [`flutter`](./docs/FLUTTER.md)
- [Expo](#expo)
- [Contributing](#contributing)
- [License](#license)

<hr/>

## Installation

```sh
npm install @azify/aziface-mobile
# or
yarn add @azify/aziface-mobile
```

Only iOS:

```sh
cd ios && pod install && cd ..
```

<hr/>

## Usage

```tsx
import { useState } from 'react';
import { Text, TouchableOpacity, Platform, ScrollView } from 'react-native';
import {
  initialize,
  enroll,
  authenticate,
  liveness,
  photoMatch,
  photoScan,
  setLocale,
  FaceView,
  type Params,
  type Headers,
  type Processor,
  type Locale,
} from '@azify/aziface-mobile';

export default function App() {
  const [isInitialized, setIsInitialized] = useState(false);
  const [localization, setLocalization] = useState<Locale>('default');

  const opacity = isInitialized ? 1 : 0.5;

  const onInitialize = async () => {
    /*
     * The SDK must be initialized first
     * so that the rest of the library
     * functions can work!
     * */
    const headers: Headers = {
      'x-token-bearer': 'YOUR_X_TOKEN_BEARER',
      'x-api-key': 'YOUR_X_API_KEY',
      'clientInfo': 'YUOR_CLIENT_INFO',
      'contentType': 'YOUR_CONTENT_TYPE',
      'device': 'YOUR_DEVICE',
      'deviceid': 'YOUR_DEVICE_ID',
      'deviceip': 'YOUR_DEVICE_IP',
      'locale': 'YOUR_LOCALE',
      'xForwardedFor': 'YOUR_X_FORWARDED_FOR',
      'user-agent': 'YOUR_USER_AGENT',
      'x-only-raw-analysis': '1',
    };

    const params: Params = {
      isDevelopment: true,
      deviceKeyIdentifier: 'YOUR_DEVICE_KEY_IDENTIFIER',
      baseUrl: 'YOUR_BASE_URL',
    };

    try {
      const initialized = await initialize({
        params,
        headers,
      });

      setIsInitialized(initialized);
      console.log(initialized);
    } catch (error: any) {
      setIsInitialized(false);
      console.error(error);
    }
  };

  const onFaceScan = async (type: string, data?: any) => {
    try {
      let processor: Processor | null = null;

      switch (type) {
        case 'enroll':
          processor = await enroll(data);
          break;
        case 'liveness':
          processor = await liveness(data);
          break;
        case 'authenticate':
          processor = await authenticate(data);
          break;
        case 'photoMatch':
          processor = await photoMatch(data);
          break;
        case 'photoScan':
          processor = await photoScan(data);
          break;
        default:
          processor = false;
          break;
      }

      console.log(type, processor);
    } catch (error: any) {
      console.error(type, error.message);
    }
  };

  const onLocale = () => {
    const LOCALES: Locale[] = ['default', 'en', 'es', 'pt-BR'];
    const localeIndex = Math.floor(Math.random() * (LOCALES.length - 2));
    const value = LOCALES.filter((l) => l !== localization)[localeIndex]!;

    setLocalization(value);
    setLocale(value);
  };

  return (
    <ScrollView
      style={styles.scroll}
      contentContainerStyle={styles.scrollContent}
      showsVerticalScrollIndicator={false}
    >
      <FaceView
        style={styles.content}
        onInitialize={(event) => console.log('onInitialize', event)}
        onOpen={(event) => console.log('onOpen', event)}
        onClose={(event) => console.log('onClose', event)}
        onCancel={(event) => console.log('onCancel', event)}
        onError={(event) => console.log('onError', event)}
        onVocal={(event) => console.log('onVocal', event)}
      >
        <TouchableOpacity
          style={styles.button}
          activeOpacity={0.8}
          onPress={onInitialize}
        >
          <Text style={styles.buttonText}>Initialize SDK</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { opacity }]}
          activeOpacity={0.8}
          onPress={() => onFaceScan('enroll')}
          disabled={!isInitialized}
        >
          <Text style={styles.buttonText}>Enrollment</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { opacity }]}
          activeOpacity={0.8}
          onPress={() => onFaceScan('liveness')}
          disabled={!isInitialized}
        >
          <Text style={styles.buttonText}>Liveness</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { opacity }]}
          activeOpacity={0.8}
          onPress={() => onFaceScan('authenticate')}
          disabled={!isInitialized}
        >
          <Text style={styles.buttonText}>Authenticate</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { opacity }]}
          activeOpacity={0.8}
          onPress={() => onFaceScan('photoMatch')}
          disabled={!isInitialized}
        >
          <Text style={styles.buttonText}>Photo Match</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { opacity }]}
          activeOpacity={0.8}
          onPress={() => onFaceScan('photoScan')}
          disabled={!isInitialized}
        >
          <Text style={styles.buttonText}>Photo Scan</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.button, { opacity }]}
          activeOpacity={0.8}
          onPress={() => onLocale()}
          disabled={!isInitialized}
        >
          <Text style={styles.buttonText}>Localization: {localization}</Text>
        </TouchableOpacity>
      </FaceView>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  scroll: {
    flex: 1,
    paddingTop: Platform.OS === 'ios' ? 40 : 0,
    backgroundColor: 'white',
  },
  scrollContent: {
    flexGrow: 1,
  },
  content: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    gap: 40,
    backgroundColor: 'white',
  },
  button: {
    width: '100%',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
    borderRadius: 16,
    backgroundColor: '#4a68b3',
  },
  buttonText: {
    fontSize,
    fontWeight: 'bold',
    color: 'white',
  },
});
```

<hr/>

## API

| Methods        | Return Type          | Platform |
| -------------- | -------------------- | -------- |
| `initialize`   | `Promise<boolean>`   | All      |
| `enroll`       | `Promise<Processor>` | All      |
| `authenticate` | `Promise<Processor>` | All      |
| `liveness`     | `Promise<Processor>` | All      |
| `photoMatch`   | `Promise<Processor>` | All      |
| `photoScan`    | `Promise<Processor>` | All      |
| `setLocale`    | `void`               | All      |
| `vocal`        | `void`               | All      |

### `initialize`

The `initialize` of the Aziface SDK is the process of configuring and preparing the SDK for use before any face capture, liveness, authentication, or identity verification sessions can begin.

During initialization, the application provides the SDK with the required configuration data, such as the device key identifier, base URL, and `x-token-bearer`. The SDK validates these parameters, performs internal setup, and prepares the necessary resources for secure camera access, biometric processing, and user interface rendering.

A successful initialization confirms that the SDK is correctly licensed, properly configured for the target environment, and ready to start user sessions. If initialization fails due to invalid keys, network issues, or unsupported device conditions, the SDK returns boolean information (true or false) so the application can handle the failure gracefully and prevent session startup.

Initialization is a **mandatory step** and must be completed once during the application lifecycle (or as required by the platform) before invoking any Aziface SDK workflows.

```tsx
const initialized = await initialize({
  params: {
    deviceKeyIdentifier: 'YOUR_DEVICE_KEY_IDENTIFIER',
    baseUrl: 'YOUR_BASE_URL',
  },
  headers: {
    'x-token-bearer': 'YOUR_TOKEN_BEARER',
    // your headers...
  },
});

console.log(initialized);
```

#### Properties

| `Initialize` | type                  | Required |
| ------------ | --------------------- | -------- |
| `params`     | [`Params`](#params)   | ✅       |
| `headers`    | [`Headers`](#headers) | ✅       |

### `enroll`

The `enroll` method in the Aziface SDK is responsible for registering a user’s face for the first time and creating a secure biometric identity. During enrollment, the SDK guides the user through a liveness detection process to ensure that a real person is present and not a photo, video, or spoofing attempt.

While the user follows on-screen instructions (such as positioning their face within the oval and performing natural movements), the SDK captures a set of facial data and generates a secure face scan. This face scan is then encrypted and sent to the backend for processing and storage.

The result of a successful enrollment is a trusted biometric template associated with the user’s identity, which can later be used for authentication, verification, or ongoing identity checks. If the enrollment fails due to poor lighting, incorrect positioning, or liveness issues, the SDK returns detailed status and error information so the application can handle retries or user feedback appropriately.

```tsx
const result = await enroll();

console.log(result);
```

#### Properties

| Property | type  | Required | Default     |
| -------- | ----- | -------- | ----------- |
| `data`   | `any` | ❌       | `undefined` |

### `authenticate`

The authentication method in the Aziface SDK is used to verify a user’s identity by comparing a newly captured face scan against a previously enrolled biometric template. This process confirms that the person attempting to access the system is the same individual who completed the enrollment.

During authentication, the SDK performs an active liveness check while guiding the user through simple on-screen instructions. A fresh face scan is captured, encrypted, and securely transmitted to the backend, where it is matched against the stored enrollment data.

If the comparison is successful and the liveness checks pass, the authentication is approved and the user is granted access. If the process fails due to a mismatch, spoofing attempt, or poor capture conditions, the SDK returns detailed result and error codes so the application can handle denial, retries, or alternative verification flows.

```tsx
const result = await authenticate();

console.log(result);
```

#### Properties

| Property | type  | Required | Default     |
| -------- | ----- | -------- | ----------- |
| `data`   | `any` | ❌       | `undefined` |

### `liveness`

The `liveness` method in the Aziface SDK is designed to determine whether the face presented to the camera belongs to a real, live person at the time of capture, without necessarily verifying their identity against a stored template.

In this flow, the SDK guides the user through a short interaction to capture facial movements and depth cues that are difficult to replicate with photos, videos, or masks. The resulting face scan is encrypted and sent to the backend, where advanced liveness detection algorithms analyze it for signs of spoofing or fraud.

A successful liveness result confirms real human presence and can be used as a standalone security check or as part of broader workflows such as authentication, onboarding, or high-risk transactions. If the liveness check fails, the SDK provides detailed feedback to allow the application to respond appropriately.

```tsx
const result = await liveness();

console.log(result);
```

#### Properties

| Property | type  | Required | Default     |
| -------- | ----- | -------- | ----------- |
| `data`   | `any` | ❌       | `undefined` |

### `photoMatch`

The `photoMatch` method in the Aziface SDK is used to verify a user’s identity by analyzing a government-issued identity document and comparing it with the user’s live facial biometric data.

In this flow, the SDK first guides the user to capture high-quality images of their identity document. Then, a face scan is collected through a liveness-enabled facial capture. Both the document images and the face scan are encrypted and securely transmitted to the backend.

A successful result provides strong identity assurance, combining document authenticity and biometric verification. This flow is commonly used in regulated onboarding, KYC, and high-security access scenarios. If any step fails, the SDK returns detailed results and error information to support retries or alternative verification paths.

```tsx
const result = await photoMatch();

console.log(result);
```

#### Properties

| Property | type  | Required | Default     |
| -------- | ----- | -------- | ----------- |
| `data`   | `any` | ❌       | `undefined` |

### `photoScan`

The `photoScan` method in the Aziface SDK is used to verify the authenticity and validity of a government-issued identity document without performing facial biometric verification.

In this flow, the SDK guides the user to capture images of the identity document, ensuring proper framing, focus, and lighting. The captured document images are encrypted and securely sent to the backend for analysis.

A successful document-only verification is suitable for lower-risk scenarios or cases where biometric capture is not required. If the verification fails due to image quality issues, unsupported documents, or suspected tampering, the SDK provides detailed feedback for proper error handling and user guidance.

```tsx
const result = await photoScan();

console.log(result);
```

#### Properties

| Property | type  | Required | Default     |
| -------- | ----- | -------- | ----------- |
| `data`   | `any` | ❌       | `undefined` |

### `setLocale`

The `setLocale` method in the Aziface SDK is used to define the language and locale used by the SDK’s user interface and vocal guidance during verification sessions.

By calling this method, the application specifies which language the SDK should use for on-screen text, voice prompts, and user instructions. This allows the SDK to present a localized experience that matches the user’s preferred or device language.

The selected language applies to all Aziface SDK workflows, including enrollment, authentication, liveness checks, photo scan, and photo match verification. The language must be set **before starting** a session to ensure consistent localization throughout the user interaction.

If an unsupported or invalid language code is provided, the SDK falls back to a default language (en-US) and returns appropriate status or error information, depending on the platform implementation.

```tsx
setLocale('en');
```

#### Properties

| Property | type                | Required |
| -------- | ------------------- | -------- |
| `locale` | [`Locale`](#locale) | ✅       |

### `vocal`

The Vocal Guidance feature in the Aziface SDK provides spoken, real-time instructions to guide users through face capture, liveness, authentication, and identity verification flows.

During a session, the SDK uses voice prompts to instruct the user on what to do next, such as positioning their face within the camera frame, moving closer or farther, or maintaining proper alignment. This auditory guidance complements on-screen visual cues, helping users complete the process more easily and with fewer errors.

```tsx
vocal();
```

<hr/>

## Enums

| Enums               | Platform |
| ------------------- | -------- |
| [`Errors`](#errors) | All      |

<hr/>

## Types

| Types                                                               | Platform |
| ------------------------------------------------------------------- | -------- |
| [`Params`](#params)                                                 | All      |
| [`Headers`](#headers)                                               | All      |
| [`Processor`](#processor)                                           | All      |
| [`ProcessorData`](#processordata)                                   | All      |
| [`ProcessorError`](#processorerror)                                 | All      |
| [`ProcessorAdditionalSessionData`](#processoradditionalsessiondata) | All      |
| [`ProcessorResult`](#processorresult)                               | All      |
| [`ProcessorHttpCallInfo`](#processorhttpcallinfo)                   | All      |
| [`ProcessorRequestMethod`](#processorrequestmethod)                 | All      |
| [`ProcessorIDScanResultsSoFar`](#processoridscanresultssofar)       | All      |
| [`Locale`](#locale)                                                 | All      |

### `Params`

Here must be passed to initialize the Aziface SDK! Case the parameters isn't provided the Aziface SDK goes to be not initialized.

| `Params`              | type      | Required | Default |
| --------------------- | --------- | -------- | ------- |
| `deviceKeyIdentifier` | `string`  | ✅       | -       |
| `baseUrl`             | `string`  | ✅       | -       |
| `isDevelopment`       | `boolean` | ❌       | `false` |

### `Headers`

Here you can add your headers to send request when some method is called. Only values from type **string**, **null** or **undefined** are accepts!

| `Headers`        | type                              | Required | Default     |
| ---------------- | --------------------------------- | -------- | ----------- |
| `x-token-bearer` | `string`                          | ✅       | -           |
| `[key: string]`  | `string` or `null` or `undefined` | ❌       | `undefined` |

### `Processor`

A processor always return this properties.

| `Processor` | type                                                         | Description                                 |
| ----------- | ------------------------------------------------------------ | ------------------------------------------- |
| `isSuccess` | `boolean`                                                    | Indicates if the processing was successful. |
| `data`      | [`ProcessorData`](#processordata) or `null` or `undefined`   | The processor data response.                |
| `error`     | [`ProcessorError`](#processorerror) or `null` or `undefined` | The processor error response.               |

#### `ProcessorData`

This is data response processor. Each processor can give different responses.

| `ProcessorData`         | type                                                                                      | Description                                       |
| ----------------------- | ----------------------------------------------------------------------------------------- | ------------------------------------------------- |
| `idScanSessionId`       | `string` or `null` or `undefined`                                                         | The unique identifier for the ID scan session.    |
| `externalDatabaseRefID` | `string` or `null` or `undefined`                                                         | The unique identifier for the face session.       |
| `additionalSessionData` | [`ProcessorAdditionalSessionData`](#processoradditionalsessiondata)                       | The additional session data.                      |
| `result`                | [`ProcessorResult`](#processorresult) or `null` or `undefined`                            | The result of the processing.                     |
| `responseBlob`          | `string`                                                                                  | The raw response blob from the server.            |
| `httpCallInfo`          | [`ProcessorHttpCallInfo`](#processorhttpcallinfo) or `null` or `undefined`                | The HTTP call information.                        |
| `didError`              | `boolean`                                                                                 | Indicates if an error occurred during processing. |
| `serverInfo`            | `unknown` or `null` or `undefined`                                                        | The server information.                           |
| `idScanResultsSoFar`    | [`ProcessorIDScanResultsSoFar`](#processoridscanresultssofar) or or `null` or `undefined` | The ID scan results so far.                       |

#### `ProcessorError`

This is error response processor.

| `ProcessorError` | type                | Description        |
| ---------------- | ------------------- | ------------------ |
| `code`           | [`Errors`](#errors) | The error code.    |
| `message`        | `string`            | The error message. |

##### `ProcessorAdditionalSessionData`

The additional session data are extra information about device and processor.

| `ProcessorAdditionalSessionData` | type     | Description                 |
| -------------------------------- | -------- | --------------------------- |
| `platform`                       | `string` | The platform of the device. |
| `appID`                          | `string` | The application ID.         |
| `installationID`                 | `string` | The installation ID.        |
| `deviceModel`                    | `string` | The device model.           |
| `deviceSDKVersion`               | `string` | The device SDK version.     |
| `userAgent`                      | `string` | The user agent.             |
| `sessionID`                      | `string` | The session ID.             |

##### `ProcessorResult`

The result object of the face or scan analyze.

| `ProcessorResult`   | type                    | Description                             |
| ------------------- | ----------------------- | --------------------------------------- |
| `livenessProven`    | `boolean`               | Indicates if it's liveness proven.      |
| `auditTrailImage`   | `string`                | The audit trail image in base64 format. |
| `ageV2GroupEnumInt` | `number`                | The age group enumeration integer.      |
| `matchLevel`        | `number` or `undefined` | The match level.                        |

##### `ProcessorHttpCallInfo`

The HTTP information of the processor request.

| `ProcessorHttpCallInfo` | type                                                | Description                      |
| ----------------------- | --------------------------------------------------- | -------------------------------- |
| `tid`                   | `string`                                            | The transaction ID.              |
| `path`                  | `string`                                            | The request path.                |
| `date`                  | `string`                                            | The date of the request.         |
| `epochSecond`           | `number`                                            | The epoch second of the request. |
| `requestMethod`         | [`ProcessorRequestMethod`](#processorrequestmethod) | The request method.              |

###### `ProcessorRequestMethod`

The request method of the processor.

| `ProcessorRequestMethod` | Description   |
| ------------------------ | ------------- |
| `GET`                    | GET request.  |
| `POST`                   | POST request. |

##### `ProcessorIDScanResultsSoFar`

The processor scan result. It's shown when you use `photoScan` or `photoMatch`.

| `ProcessorIDScanResultsSoFar`                 | Type      | Description                                                   |
| --------------------------------------------- | --------- | ------------------------------------------------------------- |
| `photoIDNextStepEnumInt`                      | `number`  | The photo ID next step enumeration integer.                   |
| `fullIDStatusEnumInt`                         | `number`  | The full ID status enumeration integer.                       |
| `faceOnDocumentStatusEnumInt`                 | `number`  | The face on document status enumeration integer.              |
| `textOnDocumentStatusEnumInt`                 | `number`  | The text on document status enumeration integer.              |
| `expectedMediaStatusEnumInt`                  | `number`  | The expected media status enumeration integer.                |
| `unexpectedMediaEncounteredAtLeastOnce`       | `boolean` | Indicates if unexpected media was encountered at least once.  |
| `documentData`                                | `string`  | The document data in stringified JSON format.                 |
| `nfcStatusEnumInt`                            | `number`  | The NFC status enumeration integer.                           |
| `nfcAuthenticationStatusEnumInt`              | `number`  | The NFC authentication status enumeration integer.            |
| `barcodeStatusEnumInt`                        | `number`  | The barcode status enumeration integer.                       |
| `mrzStatusEnumInt`                            | `number`  | The MRZ status enumeration integer.                           |
| `idFoundWithoutQualityIssueDetected`          | `boolean` | Indicates if the ID was found without quality issues.         |
| `idFacePhotoFoundWithoutQualityIssueDetected` | `boolean` | Indicates if the face photo was found without quality issues. |
| `idScanAgeV2GroupEnumInt`                     | `number`  | The ID scan age group enumeration integer.                    |
| `didMatchIDScanToOCRTemplate`                 | `boolean` | Indicates if the ID scan matched the OCR template.            |
| `isUniversalIDMode`                           | `boolean` | Indicates if the universal ID mode is enabled.                |
| `matchLevel`                                  | `number`  | The match level.                                              |
| `matchLevelNFCToFaceMap`                      | `number`  | The match level NFC to face map.                              |
| `faceMapAgeV2GroupEnumInt`                    | `number`  | The face map age group enumeration integer.                   |
| `watermarkAndHologramStatusEnumInt`           | `number`  | The watermark and hologram status enumeration integer.        |

### `Locale`

The `Locale` type use the [ISO 639](https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes) language codes pattern.

| type      | Description                     | Platform |
| --------- | ------------------------------- | -------- |
| `af`      | Afrikaans language.             | All      |
| `ar`      | Arabic language.                | All      |
| `de`      | German language.                | All      |
| `default` | English language.               | All      |
| `en`      | English language.               | All      |
| `el`      | Greek language.                 | All      |
| `es`      | Spanish and Castilian language. | All      |
| `fr`      | French language.                | All      |
| `ja`      | Japanese language.              | All      |
| `kk`      | Kazakh language.                | All      |
| `nb`      | Norwegian Bokmål language.      | All      |
| `pt-BR`   | Portuguese Brazilian language.  | All      |
| `ru`      | Russian language.               | All      |
| `vi`      | Vietnamese language.            | All      |
| `zh`      | Chinese language.               | All      |

### `Errors`

| `Errors`                  | Description                                                                         | Platform |
| ------------------------- | ----------------------------------------------------------------------------------- | -------- |
| `NotInitialized`          | When trying to initialize a process, but SDK wasn't initialized.                    | All      |
| `ConfigNotProvided`       | When `deviceKeyIdentifier` and `baseUrl` aren't provided.                           | All      |
| `ParamsNotProvided`       | When parameters aren't provided, this case, it is `null`.                           | All      |
| `NotAuthenticated`        | When `authenticate` process is called, but `enroll` wasn't done first.              | All      |
| `NotFoundTargetView`      | When `Activity` (Android) or `ViewController` (iOS) aren't found on call processor. | All      |
| `CameraError`             | When an error on use the camera occurs.                                             | All      |
| `CameraPermissionsDenied` | When the user doesn't permit the use camera.                                        | All      |
| `UserCancelledIdScan`     | When process was cancelled on ID scan.                                              | All      |
| `UserCancelledFaceScan`   | When process was cancelled on face scan.                                            | All      |
| `RequestAborted`          | When process has request aborted. Some error in JSON or network.                    | All      |
| `LockedOut`               | When process is locked out.                                                         | All      |
| `UnknownInternalError`    | When process has unknown internal error.                                            | All      |

<hr/>

## Components

### `FaceView`

The `FaceView` extends all properties of the `View`, but it has five new callbacks to listener Aziface SDK events.

#### Properties

| Property       | Description                                                       | Returns   | Platform |
| -------------- | ----------------------------------------------------------------- | --------- | -------- |
| `onOpen`       | Callback function called when the Aziface SDK is opened.          | `boolean` | All      |
| `onClose`      | Callback function called when the Aziface SDK is closed.          | `boolean` | All      |
| `onCancel`     | Callback function called when the Aziface SDK is cancelled.       | `boolean` | All      |
| `onError`      | Callback function called when an error occurs in the Aziface SDK. | `boolean` | All      |
| `onVocal`      | Callback function called when the vocal guidance is activated.    | `boolean` | All      |
| `onInitialize` | Callback function called when the Aziface SDK is initialized.     | `boolean` | All      |

<hr/>

## Dynamic Strings

The `setDynamicStrings` method in the Aziface SDK allows applications to dynamically customize and override the text strings displayed in the SDK’s user interface during verification sessions.

This method enables the application to replace default UI messages such as instructions, error messages, button labels, and guidance text with custom strings at runtime. It is commonly used to adapt wording, terminology, or tone to better align with product language, branding, regulatory requirements, or user context.

The dynamic strings defined through this method apply across Aziface SDK workflows, including enrollment, authentication, liveness checks, photo scan, and photo match verification. To ensure consistency, setDynamicStrings should be called before starting a session so that all UI elements display the customized text.

If a provided string key is invalid or missing, the SDK falls back to its default text for that element. This ensures that the user experience remains functional even if some custom strings are not defined.

We separated another documentation about the dynamic strings, see more [here](./docs/DYNAMIC_STRINGS.md)!

<hr/>

## Theme

The Aziface SDK provides the ability to change the theme of each flow. We separated another documentation about the theme, see more [here](./docs/THEME.md)!

<hr/>

## Vocal Guidance

The Aziface SDK provides the `vocal` method for you on vocal guidance. We recommend using it the SDK is initialized. The `vocal` method will always return `false` when the device is muted.

**Note**: We recommend to use the `FaceView` component for control vocal guidance state with efficiently.

```tsx
import { useState } from 'react';
import { Button } from 'react-native';
// ...
import { initialize, vocal, type Params } from '@azify/aziface-mobile';

export default function App() {
  const [isInitialized, setIsInitialized] = useState(false);
  // State to manager when vocal is enabled
  const [isVocalEnabled, setIsVocalEnabled] = useState(false);

  function onInitialize() {
    const params: Params = {
      isDevelopment: true,
      deviceKeyIdentifier: 'YOUR_DEVICE_KEY_IDENTIFIER',
      baseUrl: 'YOUR_BASE_URL',
    };

    try {
      const initialized = await initialize({ params });
      setIsInitialized(initialized);
    } catch {
      setIsInitialized(false);
    } finally {
      setIsVocalEnabled(false);
    }
  }

  // Call onVocal function when SDK is initialized!
  function onVocal(enabled: boolean) {
    setIsVocalEnabled(enabled);
  }

  return (
    <FaceView onVocal={onVocal}>
      {/* ... */}

      <Button
        title={isVocalEnabled ? 'Vocal ON' : 'Vocal OFF'}
        onPress={vocal}
      />
    </FaceView>
  );
}
```

<hr/>

## Enabling Camera (iOS only)

If you want to enable the camera, you need to add the following instructions in your `Info.plist` file:

```plist
<key>NSCameraUsageDescription</key>
<string>$(PRODUCT_NAME) need access to your camera to take picture.</string>
```

> That's will be necessary to what iOS **works** correctly!

<hr/>

## Integration guide

The Azify offers an example App for Flutter developers. Currently, this example App has full implementation in Android apps. Now, in iOS apps it's still in progress. Check that's the documentation [here](./docs/FLUTTER.md).

<hr/>

## Expo

In Expo, you need to convert to a [custom development build](https://docs.expo.dev/develop/development-builds/introduction/) or use [prebuild](https://docs.expo.dev/workflow/continuous-native-generation/). You can use also React Native without Expo. Check Expo example App [here](./examples/expoapp/README.md).

<hr/>

## Contributing

See the [contributing guide](./CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

<hr/>

## License

[MIT License](./LICENSE). 🙂

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob). 😊

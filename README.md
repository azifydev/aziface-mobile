# @azify/aziface-mobile

![Version](https://img.shields.io/github/package-json/v/azifydev/aziface-mobile?style=flat&color=brightgreen)

Azify SDK adapter to react native. 📱

- [Installation](#installation)
- [Usage](#usage)
- [API](#api)
  - [`initialize`](#initialize)
  - [`enroll`](#enroll)
  - [`photoMatch`](#photomatch)
  - [`setTheme`](#settheme)
- [Types](#types)
  - [`Params`](#azifacesdkparams)
  - [`Headers`](#azifacesdkheaders)
  - [`SessionParams`](#sessionparamst)
    - [`SessionBasePathUrl`](#sessionbasepathurl)
    - [`SessionMatchPathUrl`](#sessionmatchpathurl)
  - [`Theme`](#theme)
  - [`ButtonLocation`](#buttonlocation)
  - [`StatusBarColor`](#statusbarcolor-ios-only)
  - [`FeedbackBackgroundColor`](#feedbackbackgroundcolor-ios-only)
  - [`Point`](#point-ios-only)
  - [`DefaultMessage`](#defaultmessage)
  - [`DefaultScanMessage`](#defaultscanmessage)
    - [`DefaultScanMessageFrontSide`](#defaultscanmessagefrontside)
    - [`DefaultScanMessageBackSide`](#defaultscanmessagebackside)
    - [`DefaultScanMessageUserConfirmInfo`](#defaultscanmessageuserconfirminfo)
    - [`DefaultScanMessageNFC`](#defaultscanmessagenfc)
    - [`DefaultScanMessageSkippedNFC`](#defaultscanmessageskippednfc)
    - [`DefaultScanMessageSuccess`](#defaultscanmessagesuccess)
    - [`DefaultScanMessageRetry`](#defaultscanmessageretry)
  - [`Errors`](#errors)
- [Native Events](#native-events)
  - [`Event Types`](#event-types)
- [How to add images in Aziface SDK module?](#how-to-add-images-in-aziface-sdk-module)
  - [How to add images in Android?](#how-to-add-images-in-android)
  - [How to add images in iOS?](#how-to-add-images-in-ios)
  - [Example with images added](#example-with-images-added)
- [Limitations and Features](#limitations-and-features)
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
cd ios && pod install
```

<hr/>

## Usage

```tsx
import * as React from 'react';

import {
  StyleSheet,
  View,
  Text,
  TouchableOpacity,
  ScrollView,
  NativeEventEmitter,
} from 'react-native';
import ReactNativeAzifaceSdk, {
  enroll,
  initialize,
  photoMatch,
} from '@azify/aziface-mobile';

export default function App() {
  const init = async () => {
    /*
     * The SDK must be initialized first
     * so that the rest of the library
     * functions can work!
     *
     * */
    const headers = {
      'Authorization': 'YOUR_AUTHORIZATION',
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
    const params = {
      isDeveloperMode: false,
      device: 'YOUR_DEVICE',
      url: 'YOUR_BASE_URL',
      key: 'YOUR_KEY',
      productionKey: 'YOUR_PRODUCTION_KEY',
    };

    try {
      const isInitialized = await initialize({
        params,
        headers,
      });

      console.log(isInitialized);
    } catch (error: any) {
      console.error(error.message);
    }
  };

  const emitter = new NativeEventEmitter(ReactNativeAzifaceSdk);
  emitter.addListener('onCloseModal', (event: boolean) =>
    console.log('onCloseModal', event)
  );

  const onPressPhotoMatch = async () => {
    try {
      const isSuccess = await photoMatch();
      console.log(isSuccess);
    } catch (error: any) {
      console.error(error.message);
    }
  };

  const onPressEnroll = async () => {
    try {
      const isSuccess = await enroll();
      console.log(isSuccess);
    } catch (error: any) {
      console.error(error.message);
    }
  };

  return (
    <ScrollView style={styles.container}>
      <View style={styles.content}>
        <TouchableOpacity
          style={styles.button}
          onPress={async () => await init()}
        >
          <Text style={styles.text}>Init Aziface Module</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={onPressPhotoMatch}>
          <Text style={styles.text}>Open Photo Match</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={onPressEnroll}>
          <Text style={styles.text}>Open Enroll</Text>
        </TouchableOpacity>
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
  },
  content: {
    justifyContent: 'center',
    alignItems: 'center',
    marginVertical: 30,
  },
  button: {
    width: '100%',
    backgroundColor: '#4a68b3',
    padding: 20,
    borderRadius: 15,
    alignItems: 'center',
    justifyContent: 'center',
    marginVertical: 20,
  },
  text: {
    color: 'white',
    fontWeight: '700',
    fontSize: 22,
  },
});
```

<hr/>

## API

| Methods        | Return Type        | iOS | Android |
| -------------- | ------------------ | --- | ------- |
| `initialize`   | `Promise<boolean>` | ✅  | ✅      |
| `enroll`       | `Promise<boolean>` | ✅  | ✅      |
| `authenticate` | `Promise<boolean>` | ❌  | ❌      |
| `liveness`     | `Promise<boolean>` | ❌  | ❌      |
| `photoMatch`   | `Promise<boolean>` | ✅  | ✅      |
| `photoScan`    | `Promise<boolean>` | ❌  | ❌      |
| `setTheme`     | `void`             | ✅  | ✅      |

### `initialize`

This is the **principal** method to be called, he must be **called first** to initialize the Aziface SDK. If he doens't be called the other methods **don't works!**

| `Initialize` | type                            | Required | Default     |
| ------------ | ------------------------------- | -------- | ----------- |
| `params`     | [`Params`](#azifacesdkparams)   | ✅       | -           |
| `headers`    | [`Headers`](#azifacesdkheaders) | ❌       | `undefined` |

### `enroll`

This method makes a 3D reading of the user's face. But, you must use to **subscribe** user in Aziface SDK or in your server.

| `SessionParams<"base">` | type                                                    | Required | Default     |
| ----------------------- | ------------------------------------------------------- | -------- | ----------- |
| `data`                  | [`SessionBasePathUrl`](#azifacesdkcommonsessionpathurl) | ❌       | `undefined` |

### `photoMatch`

This method make to read from face and documents for user, after compare face and face documents from user to check veracity.

| `SessionParams<"match">` | type                                                       | Required | Default     |
| ------------------------ | ---------------------------------------------------------- | -------- | ----------- |
| `data`                   | [`SessionMatchPathUrl`](#azifacesdkmultiplesessionpathurl) | ❌       | `undefined` |

### `setTheme`

This method must be used to **set** the **theme** of the Aziface SDK screen.

| Property  | type                        | Required | Default     |
| --------- | --------------------------- | -------- | ----------- |
| `options` | [`Theme`](#azifacesdktheme) | ❌       | `undefined` |

<hr/>

## Types

| Types                                                                    | iOS | Android |
| ------------------------------------------------------------------------ | --- | ------- |
| [`Params`](#azifacesdkparams)                                            | ✅  | ✅      |
| [`Headers`](#azifacesdkheaders)                                          | ✅  | ✅      |
| [`SessionParams`](#azifacesdkcommonsessionparams)                        | ✅  | ✅      |
| [`SessionBasePathUrl`](#azifacesdkcommonsessionpathurl)                  | ✅  | ✅      |
| [`SessionMatchPathUrl`](#azifacesdkmultiplesessionpathurl)               | ✅  | ✅      |
| [`Theme`](#azifacesdktheme)                                              | ✅  | ✅      |
| [`ButtonLocation`](#azifacesdkbuttonlocation)                            | ✅  | ✅      |
| [`StatusBarColor`](#azifacesdkstatusbarcolor-ios-only)                   | ✅  | ❌      |
| [`FeedbackBackgroundColor`](#azifacesdkfeedbackbackgroundcolor-ios-only) | ✅  | ❌      |
| [`Point`](#azifacesdkpoint-ios-only)                                     | ✅  | ❌      |
| [`DefaultMessage`](#azifacesdkdefaultmessage)                            | ✅  | ✅      |
| [`DefaultScanMessage`](#azifacesdkdefaultscanmessage)                    | ✅  | ✅      |

### `Params`

Here must be passed to initialize the Aziface SDK! Case the parameters isn't provided the Aziface SDK goes to be not initialized.

| `Params`          | type      | Required |
| ----------------- | --------- | -------- |
| `device`          | `string`  | ✅       |
| `url`             | `string`  | ✅       |
| `key`             | `string`  | ✅       |
| `productionKey`   | `string`  | ✅       |
| `isDeveloperMode` | `boolean` | ✅       |

### `Headers`

Here you can add your headers to send request when some method is called. Only values from type **string**, **null** or **undefined** are accepts!

| `Headers`       | type                            | Required | Default     |
| --------------- | ------------------------------- | -------- | ----------- |
| `[key: string]` | `string`, `null` or `undefined` | ❌       | `undefined` |

### `SessionParams<T>`

This type is related to the data that will be sent by the SDK methods. It accepts any key, but has a property called `pathUrl`. The `pathUrl` is an object of URLs that will be passed in requests instead of the SDK's default URLs.

| `SessionParams<T>` | type                                                                                         | Required | Default     |
| ------------------ | -------------------------------------------------------------------------------------------- | -------- | ----------- |
| `pathUrl`          | [`SessionBasePathUrl`](#sessionbasepathurl) or [`SessionMatchPathUrl`](#sessionmatchpathurl) | ❌       | `undefined` |
| `[key: string]`    | `any`                                                                                        | ❌       | `undefined` |

#### `SessionBasePathUrl`

This type is only for SDK methods that perform a single request. It contains the `base` property, where you can specify the URL you want the request to be made natively. If not specified, the default URL will be used for the request.

If the **URL is different** from the default and requires passing the `processId` parameter, you can enter the URL in this style `/my-url/:my-id/my-final-url`. Internally, our native module will identify the slug `/:my-id` and replace it with the `processId` in the parameters.

| `SessionBasePathUrl` | type     | Required | Default     |
| -------------------- | -------- | -------- | ----------- |
| `base`               | `string` | ❌       | `undefined` |

#### `SessionMatchPathUrl`

This type is only for SDK methods that perform a single request. It contains the `base` and `match` properties, where you can specify the URL you want the request to be made natively. If not specified, the default URL will be used for the request.

If the **URL is different** from the default and requires passing the `processId` parameter, you can enter the URL in this style `/my-url/:my-id/my-final-url`. Internally, our native module will identify the slug `/:my-id` and replace it with the `processId` in the parameters.

This type is specify of the `photoMatch` method.

| `SessionMatchPathUrl` | type     | Required | Default     |
| --------------------- | -------- | -------- | ----------- |
| `base`                | `string` | ❌       | `undefined` |
| `match`               | `string` | ❌       | `undefined` |

### `Theme`

This is a list of theme properties that can be used to styling. Note, we recommend that you use **only** hexadecimal values to colors, between six and eight characters, because still we don't supported others color type.

| `Theme`                                        | type                                                                                                    | iOS | Android | Required | Default                                                                                                 |
| ---------------------------------------------- | ------------------------------------------------------------------------------------------------------- | --- | ------- | -------- | ------------------------------------------------------------------------------------------------------- |
| `logoImage`                                    | `string`                                                                                                | ✅  | ✅      | ❌       | `facetec_your_app_logo.png`                                                                             |
| `cancelImage`                                  | `string`                                                                                                | ✅  | ✅      | ❌       | `facetec_cancel.png`                                                                                    |
| `cancelButtonLocation`                         | [`ButtonLocation`](#azifacesdkbuttonlocation)                                                           | ✅  | ✅      | ❌       | `TOP_RIGHT`                                                                                             |
| `defaultStatusBarColorIos`                     | [`StatusBarColor`](#azifacesdkstatusbarcolor-ios-only)                                                  | ✅  | ❌      | ❌       | `DARK_CONTENT`                                                                                          |
| `frameCornerRadius`                            | `number`                                                                                                | ✅  | ✅      | ❌       | `10` (iOS) and `20` (Android)                                                                           |
| `frameBackgroundColor`                         | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `frameBorderColor`                             | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `overlayBackgroundColor`                       | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `guidanceBackgroundColorsAndroid`              | `string`                                                                                                | ❌  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `guidanceBackgroundColorsIos`                  | `string[]`                                                                                              | ✅  | ❌      | ❌       | `["#FFFFFF", "#FFFFFF"]`                                                                                |
| `guidanceForegroundColor`                      | `string`                                                                                                | ✅  | ✅      | ❌       | `#272937`                                                                                               |
| `guidanceButtonBackgroundNormalColor`          | `string`                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `guidanceButtonBackgroundDisabledColor`        | `string`                                                                                                | ✅  | ✅      | ❌       | `#B3D4FC`                                                                                               |
| `guidanceButtonBackgroundHighlightColor`       | `string`                                                                                                | ✅  | ✅      | ❌       | `#0264DC`                                                                                               |
| `guidanceButtonTextNormalColor`                | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `guidanceButtonTextDisabledColor`              | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `guidanceButtonTextHighlightColor`             | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `guidanceRetryScreenImageBorderColor`          | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `guidanceRetryScreenOvalStrokeColor`           | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `ovalStrokeColor`                              | `string`                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `ovalFirstProgressColor`                       | `string`                                                                                                | ✅  | ✅      | ❌       | `#0264DC`                                                                                               |
| `ovalSecondProgressColor`                      | `string`                                                                                                | ✅  | ✅      | ❌       | `#0264DC`                                                                                               |
| `feedbackBackgroundColorsAndroid`              | `string`                                                                                                | ❌  | ✅      | ❌       | `#026FF4`                                                                                               |
| `feedbackBackgroundColorsIos`                  | [`FeedbackBackgroundColor` ](#azifacesdkfeedbackbackgroundcolor-ios-only)                               | ✅  | ❌      | ❌       | [`FeedbackBackgroundColor` ](#azifacesdkfeedbackbackgroundcolor-ios-only)                               |
| `feedbackTextColor`                            | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `resultScreenBackgroundColorsAndroid`          | `string`                                                                                                | ❌  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `resultScreenBackgroundColorsIos`              | `string[]`                                                                                              | ✅  | ❌      | ❌       | `["#FFFFFF", "#FFFFFF"]`                                                                                |
| `resultScreenForegroundColor`                  | `string`                                                                                                | ✅  | ✅      | ❌       | `#272937`                                                                                               |
| `resultScreenActivityIndicatorColor`           | `string`                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `resultScreenResultAnimationBackgroundColor`   | `string`                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `resultScreenResultAnimationForegroundColor`   | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `resultScreenUploadProgressFillColor`          | `string`                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `idScanSelectionScreenBackgroundColorsAndroid` | `string`                                                                                                | ❌  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanSelectionScreenBackgroundColorsIos`     | `string[]`                                                                                              | ✅  | ❌      | ❌       | `["#FFFFFF", "#FFFFFF"]`                                                                                |
| `idScanSelectionScreenForegroundColor`         | `string`                                                                                                | ✅  | ✅      | ❌       | `#272937`                                                                                               |
| `idScanReviewScreenForegroundColor`            | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanReviewScreenTextBackgroundColor`        | `string`                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `idScanCaptureScreenForegroundColor`           | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanCaptureScreenTextBackgroundColor`       | `string`                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `idScanButtonBackgroundNormalColor`            | `string`                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `idScanButtonBackgroundDisabledColor`          | `string`                                                                                                | ✅  | ✅      | ❌       | `#B3D4FC`                                                                                               |
| `idScanButtonBackgroundHighlightColor`         | `string`                                                                                                | ✅  | ✅      | ❌       | `#0264DC`                                                                                               |
| `idScanButtonTextNormalColor`                  | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanButtonTextDisabledColor`                | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanButtonTextHighlightColor`               | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanCaptureScreenBackgroundColor`           | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanCaptureFrameStrokeColor`                | `string`                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `autheticanteMessage`                          | [`DefaultMessage`](#azifacesdkdefaultmessage)                                                           | ✅  | ✅      | ❌       | [`DefaultMessage`](#azifacesdkdefaultmessage)                                                           |
| `enrollMessage`                                | [`DefaultMessage`](#azifacesdkdefaultmessage)                                                           | ✅  | ✅      | ❌       | [`DefaultMessage`](#azifacesdkdefaultmessage)                                                           |
| `photoIdScanMessage`                           | [`DefaultScanMessage`](#azifacesdkdefaultscanmessage)                                                   | ✅  | ✅      | ❌       | [`DefaultScanMessage`](#azifacesdkdefaultscanmessage)                                                   |
| `photoIdMatchMessage`                          | [`DefaultScanMessage`](#azifacesdkdefaultscanmessage) and [`DefaultMessage`](#azifacesdkdefaultmessage) | ✅  | ✅      | ❌       | [`DefaultScanMessage`](#azifacesdkdefaultscanmessage) and [`DefaultMessage`](#azifacesdkdefaultmessage) |

### `ButtonLocation`

This type must be used to position of the cancel button on screen.

| `ButtonLocation` | Description                                                     |
| ---------------- | --------------------------------------------------------------- |
| `DISABLED`       | Disable cancel button and doesn't show it.                      |
| `TOP_LEFT`       | Position cancel button in top right.                            |
| `TOP_RIGHT`      | Position cancel button in top right. It's **default** position. |

### `StatusBarColor` (`iOS` only)

This type must be used to status bar color.

| `StatusBarColor` | Description                                  |
| ---------------- | -------------------------------------------- |
| `DARK_CONTENT`   | **Default** color to status bar.             |
| `DEFAULT`        | Status bar color that's set from the device. |
| `LIGHT_CONTENT`  | Light color to status bar.                   |

### `FeedbackBackgroundColor` (`iOS` only)

This type must be used to **set** the **theme** of the feedback box.

| `FeedbackBackgroundColor` | Description                                                                                        | type                       | Required | Default                  |
| ------------------------- | -------------------------------------------------------------------------------------------------- | -------------------------- | -------- | ------------------------ |
| `colors`                  | An array of colors defining the color of each gradient stop.                                       | `string[]`                 | ❌       | `["#026FF4", "#026FF4"]` |
| `locations`               | It's accepts only two values between `0` and `1` that defining the location of each gradient stop. | `[number, number]`         | ❌       | `[0, 1]`                 |
| `startPoint`              | The start point of the gradient when drawn in the layer’s coordinate space.                        | [`Point`](#point-ios-only) | ❌       | `{ x: 0, y: 0 }`         |
| `endPoint`                | The end point of the gradient when drawn in the layer’s coordinate space.                          | [`Point`](#point-ios-only) | ❌       | `{ x: 1, y: 0 }`         |

### `Point` (`iOS` only)

This interface defines the drawn in the layer's coordinate space.

| `Point` | type     | Required | Default |
| ------- | -------- | -------- | ------- |
| `x`     | `number` | ❌       | `0`     |
| `y`     | `number` | ❌       | `0`     |

### `DefaultMessage`

This interface represents the success message and loading data message during to Aziface SDK flow. It interface is used by processors's [enroll](#enroll) processor.

| `DefaultMessage` | type     | iOS | Android | Required | Default                            |
| ---------------- | -------- | --- | ------- | -------- | ---------------------------------- |
| `successMessage` | `string` | ✅  | ✅      | ❌       | `Face Scanned\n3D Liveness Proven` |
| `uploadMessage`  | `string` | ✅  | ❌      | ❌       | `Still Uploading...`               |

### `DefaultScanMessage`

This interface represents the all scan messages during to Aziface SDK flow. It interface is used by [photoMatch](#photomatch) processors.

| `DefaultScanMessage` | type                                                                      | iOS | Android | Required | Default               |
| -------------------- | ------------------------------------------------------------------------- | --- | ------- | -------- | --------------------- |
| `skipOrErrorNFC`     | `string`                                                                  | ✅  | ✅      | ❌       | `ID Details Uploaded` |
| `frontSide`          | [`DefaultScanMessageFrontSide`](#defaultscanmessagefrontside)             | ✅  | ✅      | ❌       | `undefined`           |
| `backSide`           | [`DefaultScanMessageBackSide`](#defaultscanmessagebackside)               | ✅  | ✅      | ❌       | `undefined`           |
| `userConfirmedInfo`  | [`DefaultScanMessageUserConfirmInfo`](#defaultscanmessageuserconfirminfo) | ✅  | ✅      | ❌       | `undefined`           |
| `nfc`                | [`DefaultScanMessageNFC`](#defaultscanmessagenfc)                         | ✅  | ✅      | ❌       | `undefined`           |
| `skippedNFC`         | [`DefaultScanMessageSkippedNFC`](#defaultscanmessageskippednfc)           | ✅  | ✅      | ❌       | `undefined`           |
| `success`            | [`DefaultScanMessageSuccess`](#defaultscanmessagesuccess)                 | ✅  | ✅      | ❌       | `undefined`           |
| `retry`              | [`DefaultScanMessageRetry`](#defaultscanmessageretry)                     | ✅  | ✅      | ❌       | `undefined`           |

#### `DefaultScanMessageFrontSide`

Represents the front-side scan messages during to Aziface SDK flow.

| `DefaultScanMessageFrontSide`      | type     | iOS | Android | Required | Default                              |
| ---------------------------------- | -------- | --- | ------- | -------- | ------------------------------------ |
| `uploadStarted`                    | `string` | ✅  | ✅      | ❌       | `Uploading Encrypted ID Scan`        |
| `stillUploading`                   | `string` | ✅  | ✅      | ❌       | `Still Uploading... Slow Connection` |
| `uploadCompleteAwaitingResponse`   | `string` | ✅  | ✅      | ❌       | `Upload Complete`                    |
| `uploadCompleteAwaitingProcessing` | `string` | ✅  | ✅      | ❌       | `Processing ID Scan`                 |

#### `DefaultScanMessageBackSide`

Represents the back-side scan messages during to Aziface SDK flow.

| `DefaultScanMessageBackSide`       | type     | iOS | Android | Required | Default                              |
| ---------------------------------- | -------- | --- | ------- | -------- | ------------------------------------ |
| `uploadStarted`                    | `string` | ✅  | ✅      | ❌       | `Uploading Encrypted Back of ID`     |
| `stillUploading`                   | `string` | ✅  | ✅      | ❌       | `Still Uploading... Slow Connection` |
| `uploadCompleteAwaitingResponse`   | `string` | ✅  | ✅      | ❌       | `Upload Complete`                    |
| `uploadCompleteAwaitingProcessing` | `string` | ✅  | ✅      | ❌       | `Processing Back of ID`              |

#### `DefaultScanMessageUserConfirmInfo`

Represents the user confirmed information messages during to Aziface SDK flow.

| `DefaultScanMessageUserConfirmInfo` | type     | iOS | Android | Required | Default                              |
| ----------------------------------- | -------- | --- | ------- | -------- | ------------------------------------ |
| `uploadStarted`                     | `string` | ✅  | ✅      | ❌       | `Uploading Your Confirmed Info`      |
| `stillUploading`                    | `string` | ✅  | ✅      | ❌       | `Still Uploading... Slow Connection` |
| `uploadCompleteAwaitingResponse`    | `string` | ✅  | ✅      | ❌       | `Upload Complete`                    |
| `uploadCompleteAwaitingProcessing`  | `string` | ✅  | ✅      | ❌       | `Processing`                         |

#### `DefaultScanMessageNFC`

Represents the NFC scan messages during to Aziface SDK flow.

| `DefaultScanMessageNFC`            | type     | iOS | Android | Required | Default                              |
| ---------------------------------- | -------- | --- | ------- | -------- | ------------------------------------ |
| `uploadStarted`                    | `string` | ✅  | ✅      | ❌       | `Uploading Encrypted NFC Details`    |
| `stillUploading`                   | `string` | ✅  | ✅      | ❌       | `Still Uploading... Slow Connection` |
| `uploadCompleteAwaitingResponse`   | `string` | ✅  | ✅      | ❌       | `Upload Complete`                    |
| `uploadCompleteAwaitingProcessing` | `string` | ✅  | ✅      | ❌       | `Processing NFC Details`             |

#### `DefaultScanMessageSkippedNFC`

Represents the skipped NFC scan messages during to Aziface SDK flow.

| `DefaultScanMessageSkippedNFC`     | type     | iOS | Android | Required | Default                              |
| ---------------------------------- | -------- | --- | ------- | -------- | ------------------------------------ |
| `uploadStarted`                    | `string` | ✅  | ✅      | ❌       | `Uploading Encrypted ID Details`     |
| `stillUploading`                   | `string` | ✅  | ✅      | ❌       | `Still Uploading... Slow Connection` |
| `uploadCompleteAwaitingResponse`   | `string` | ✅  | ✅      | ❌       | `Upload Complete`                    |
| `uploadCompleteAwaitingProcessing` | `string` | ✅  | ✅      | ❌       | `Processing ID Details`              |

#### `DefaultScanMessageSuccess`

Represents the success messages during to Aziface SDK flow.

| `DefaultScanMessageSuccess` | type     | iOS | Android | Required | Default                  |
| --------------------------- | -------- | --- | ------- | -------- | ------------------------ |
| `frontSide`                 | `string` | ✅  | ✅      | ❌       | `ID Scan Complete`       |
| `frontSideBackNext`         | `string` | ✅  | ✅      | ❌       | `Front of ID Scanned`    |
| `frontSideNFCNext`          | `string` | ✅  | ✅      | ❌       | `Front of ID Scanned`    |
| `backSide`                  | `string` | ✅  | ✅      | ❌       | `ID Scan Complete`       |
| `backSideNFCNext`           | `string` | ✅  | ✅      | ❌       | `Back of ID Scanned`     |
| `passport`                  | `string` | ✅  | ✅      | ❌       | `Passport Scan Complete` |
| `passportNFCNext`           | `string` | ✅  | ✅      | ❌       | `Passport Scanned`       |
| `userConfirmation`          | `string` | ✅  | ✅      | ❌       | `Photo ID Scan Complete` |
| `NFC`                       | `string` | ✅  | ✅      | ❌       | `ID Scan Complete`       |

#### `DefaultScanMessageRetry`

Represents the retry messages during to Aziface SDK flow.

| `DefaultScanMessageSkippedNFC` | type     | iOS | Android | Required | Default                             |
| ------------------------------ | -------- | --- | ------- | -------- | ----------------------------------- |
| `faceDidNotMatch`              | `string` | ✅  | ✅      | ❌       | `Face Didn’t Match Highly Enough`   |
| `IDNotFullyVisible`            | `string` | ✅  | ✅      | ❌       | `ID Document Not Fully Visible`     |
| `OCRResultsNotGoodEnough`      | `string` | ✅  | ✅      | ❌       | `ID Text Not Legible`               |
| `IDTypeNotSupported`           | `string` | ✅  | ✅      | ❌       | `ID Type Mismatch Please Try Again` |

### `Errors`

| `Errors`                            | Description                                                                           | iOS | Android |
| ----------------------------------- | ------------------------------------------------------------------------------------- | --- | ------- |
| `AziFaceHasNotBeenInitialized`      | When some processors method is running, but Aziface SDK **has not been initialized**. | ✅  | ✅      |
| `AziFaceValuesWereNotProcessed`     | When the image sent to the processors cannot be processed due to inconsistency.       | ✅  | ✅      |
| `HTTPSError`                        | When exists some network error.                                                       | ✅  | ✅      |
| `JSONError`                         | When exists some problem in getting data in request of **base URL** information.      | ❌  | ✅      |
| `AziFaceInvalidSession`             | When session status is invalid.                                                       | ❌  | ✅      |
| `AziFaceTecDifferentStatus`         | When session status is different of success.                                          | ❌  | ✅      |
| `AziFaceScanValuesWereNotProcessed` | When the image ID sent to the processors cannot be processed due to inconsistency.    | ❌  | ✅      |

<hr/>

## Native Events

| Methods                                                              | Return Type                                                                                               | iOS | Android |
| -------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --- | ------- |
| `addListener(eventType: string, callback: (event: boolean) => void)` | [`EmitterSubscription`](https://reactnative.dev/docs/native-modules-android#sending-events-to-javascript) | ✅  | ✅      |

### `Event Types`

This is a list of event types that can be used on `addListener`.

| `eventType`    | Return    | Description                                                                                                       |
| -------------- | --------- | ----------------------------------------------------------------------------------------------------------------- |
| `onCloseModal` | `boolean` | This event listener verify if Aziface modal biometric is open. Return `true` if modal is open, `false` otherwise. |

<hr/>

## How to add images in Aziface SDK module?

The `logoImage` and `cancelImage` properties represents your logo and icon of the button cancel. Does not possible to remove them from the module. Default are [Azify](https://www.azify.com/) images and `.png` format. By default in `Android` the logo image is shown, but on `iOS` it isn't shown, It's necessary to add manually.

### How to add images in Android?

To add your images in `Android`, you must go to your project's `android/src/main/res/drawable` directory. If in your project `drawable` folder doesn't exist, it create one. Inside the `drawable` folder, you must put your images and done!

**Important**: The filename of the image can't have uppercase letters, Android doesn't accept these characters in the image name.

### How to add images in iOS?

In `iOS`, open your XCode and go to your project's `ios/<YOUR_PROJECT_NAME>/Images.xcassets` directory. Open the `Images.xcassets` folder and only put your images inside there.

### Example with images added

Now, go back to where you want to apply the styles, import `setTheme` method and add only the image name, no extension format, in image property (`logo` or `cancel`). **Note**: If the image is not founded the default image will be showed. Check the code example below:

```tsx
import React, { useEffect } from 'react';
import { View, TouchableOpacity, Text } from 'react-native';
import { initialize, enroll, setTheme } from '@azify/aziface-mobile';

export default function App() {
  useEffect(() => {
    const params = {
      isDeveloperMode: true,
      device: 'YOUR_DEVICE',
      url: 'YOUR_URL',
      key: 'YOUR_PUBLIC_KEY',
      productionKey: 'YOUR_PRODUCTION_KEY',
    };

    async function initialize() {
      setTheme({
        image: {
          logo: 'brand_logo' // brand_logo.png
          cancel: 'close' // close.png
        }
      });
      await initialize({ params });
    }

    initialize();
  }, []);

  return (
    <View
      style={{
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        paddingHorizontal: 20,
      }}
    >
      <TouchableOpacity
        style={{
          width: '100%',
          height: 64,
          justifyContent: 'center',
          alignItems: 'center',
          backgroundColor: 'black',
        }}
        onPress={async () => {
          try {
            const isSuccess = await enroll();
            console.log(isSuccess);
          } catch (error: any) {
            console.error(error);
          }
        }}
      >
        <Text style={{ textAlign: 'center', fontSize: 24, color: 'white' }}>
          Open!
        </Text>
      </TouchableOpacity>
    </View>
  );
}
```

<hr/>

## Limitations, Features or Camera Problems

See the [native implementation](./NATIVE_IMPLEMENTATION.md) to learn more about the limitations and features that will need improving in the `@azify/aziface-mobile`.

<hr/>

## Contributing

See the [contributing guide](./CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

<hr/>

## License

[MIT License](./LICENSE). 🙂

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob). 😊

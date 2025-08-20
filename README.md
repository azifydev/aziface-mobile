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
    - [`ThemeImage`](#themeimage)
    - [`ThemeFrame`](#themeframe)
    - [`ThemeButton`](#themebutton)
    - [`ThemeGuidance`](#themeguidance)
      - [`ThemeGuidanceRetryScreen`](#themeguidanceretryscreen)
    - [`ThemeOval`](#themeoval)
    - [`ThemeFeedback`](#themefeedback)
      - [`FeedbackBackgroundColor`](#feedbackbackgroundcolor-ios-only)
        - [`Point`](#point-ios-only)
    - [`ThemeResultScreen`](#themeresultscreen)
      - [`ThemeResultAnimation`](#themeresultanimation)
    - [`ThemeIdScan`](#themeidscan)
      - [`ThemeIdScanSelectionScreen`](#themeidscanselectionscreen)
      - [`ThemeIdScanReviewScreen`](#themeidscanreviewscreen)
      - [`ThemeIdScanCaptureScreen`](#themeidscancapturescreen)
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
- [Native Events (Deprecated)](#native-events-deprecated)
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

| Methods        | Return Type        | Platform    |
| -------------- | ------------------ | ----------- |
| `initialize`   | `Promise<boolean>` | All         |
| `enroll`       | `Promise<boolean>` | All         |
| `authenticate` | `Promise<boolean>` | Unavailable |
| `liveness`     | `Promise<boolean>` | Unavailable |
| `photoMatch`   | `Promise<boolean>` | All         |
| `photoScan`    | `Promise<boolean>` | Unavailable |
| `setTheme`     | `void`             | All         |

### `initialize`

This is the **principal** method to be called, he must be **called first** to initialize the Aziface SDK. If he doens't be called the other methods **don't works!**

| `Initialize` | type                  | Required | Default     |
| ------------ | --------------------- | -------- | ----------- |
| `params`     | [`Params`](#params)   | ✅       | -           |
| `headers`    | [`Headers`](#headers) | ❌       | `undefined` |

### `enroll`

This method makes a 3D reading of the user's face. But, you must use to **subscribe** user in Aziface SDK or in your server.

| `SessionParams<"base">` | type                                        | Required | Default     |
| ----------------------- | ------------------------------------------- | -------- | ----------- |
| `data`                  | [`SessionBasePathUrl`](#sessionbasepathurl) | ❌       | `undefined` |

### `photoMatch`

This method make to read from face and documents for user, after compare face and face documents from user to check veracity.

| `SessionParams<"match">` | type                                          | Required | Default     |
| ------------------------ | --------------------------------------------- | -------- | ----------- |
| `data`                   | [`SessionMatchPathUrl`](#sessionmatchpathurl) | ❌       | `undefined` |

### `setTheme`

This method must be used to **set** the **theme** of the Aziface SDK screen.

| Property  | type              | Required | Default     |
| --------- | ----------------- | -------- | ----------- |
| `options` | [`Theme`](#theme) | ❌       | `undefined` |

<hr/>

## Enums

| Enums               | iOS | Android |
| ------------------- | --- | ------- |
| [`Errors`](#errors) | ✅  | ✅      |

<hr/>

## Types

| Types                                                                     | Platform |
| ------------------------------------------------------------------------- | -------- |
| [`Params`](#params)                                                       | All      |
| [`Headers`](#headers)                                                     | All      |
| [`SessionParams`](#sessionparamst)                                        | All      |
| [`SessionBasePathUrl`](#sessionbasepathurl)                               | All      |
| [`SessionMatchPathUrl`](#sessionmatchpathurl)                             | All      |
| [`Theme`](#theme)                                                         | All      |
| [`ButtonLocation`](#buttonlocation)                                       | All      |
| [`StatusBarColor`](#statusbarcolor-ios-only)                              | iOS      |
| [`ThemeImage`](#themeimage)                                               | All      |
| [`ThemeFrame`](#themeframe)                                               | All      |
| [`ThemeButton`](#themebutton)                                             | All      |
| [`ThemeGuidance`](#themeguidance)                                         | All      |
| [`ThemeGuidanceRetryScreen`](#themeguidanceretryscreen)                   | All      |
| [`ThemeOval`](#themeoval)                                                 | All      |
| [`ThemeFeedback`](#themefeedback)                                         | All      |
| [`FeedbackBackgroundColor`](#feedbackbackgroundcolor-ios-only)            | iOS      |
| [`Point`](#point-ios-only)                                                | iOS      |
| [`ThemeResultScreen`](#themeresultscreen)                                 | All      |
| [`ThemeResultAnimation`](#themeresultanimation)                           | All      |
| [`ThemeIdScan`](#themeidscan)                                             | All      |
| [`ThemeIdScanSelectionScreen`](#themeidscanselectionscreen)               | All      |
| [`ThemeIdScanReviewScreen`](#themeidscanreviewscreen)                     | All      |
| [`ThemeIdScanCaptureScreen`](#themeidscancapturescreen)                   | All      |
| [`DefaultMessage`](#defaultmessage)                                       | All      |
| [`DefaultScanMessage`](#defaultmessage)                                   | All      |
| [`DefaultScanMessageFrontSide`](#defaultscanmessagefrontside)             | All      |
| [`DefaultScanMessageBackSide`](#defaultscanmessagebackside)               | All      |
| [`DefaultScanMessageUserConfirmInfo`](#defaultscanmessageuserconfirminfo) | All      |
| [`DefaultScanMessageNFC`](#defaultscanmessagenfc)                         | All      |
| [`DefaultScanMessageSkippedNFC`](#defaultscanmessageskippednfc)           | All      |
| [`DefaultScanMessageSuccess`](#defaultscanmessagesuccess)                 | All      |
| [`DefaultScanMessageRetry`](#defaultscanmessageretry)                     | All      |

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

| `Theme`                  | type                                         | Platform | Required | Default        |
| ------------------------ | -------------------------------------------- | -------- | -------- | -------------- |
| `overlayBackgroundColor` | `string`                                     | All      | ❌       | `#ffffff`      |
| `cancelButtonLocation`   | [`ButtonLocation`](#buttonlocation)          | All      | ❌       | `TOP_RIGHT`    |
| `statusBarColor`         | [`StatusBarColor`](#statusbarcolor-ios-only) | iOS      | ❌       | `DARK_CONTENT` |
| `authenticateMessage`    | [`DefaultMessage`](#defaultmessage)          | All      | ❌       | `undefined`    |
| `enrollMessage`          | [`DefaultMessage`](#defaultmessage)          | All      | ❌       | `undefined`    |
| `livenessMessage`        | [`DefaultMessage`](#defaultmessage)          | All      | ❌       | `undefined`    |
| `scanMessage`            | [`DefaultScanMessage`](#defaultscanmessage)  | All      | ❌       | `undefined`    |
| `matchMessage`           | `DefaultScanMessage & DefaultMessage`        | All      | ❌       | `undefined`    |
| `image`                  | [`ThemeImage`](#themeimage)                  | All      | ❌       | `undefined`    |
| `frame`                  | [`ThemeFrame`](#themeframe)                  | All      | ❌       | `undefined`    |
| `guidance`               | [`ThemeGuidance`](#themeguidance)            | All      | ❌       | `undefined`    |
| `oval`                   | [`ThemeOval`](#themeoval)                    | All      | ❌       | `undefined`    |
| `feedback`               | [`ThemeFeedback`](#themefeedback)            | All      | ❌       | `undefined`    |
| `resultScreen`           | [`ThemeResultScreen`](#themeresultscreen)    | All      | ❌       | `undefined`    |
| `idScan`                 | [`ThemeIdScan`](#themeidscan)                | All      | ❌       | `undefined`    |

#### `ButtonLocation`

This type must be used to position of the cancel button on screen.

| `ButtonLocation` | Description                                                     |
| ---------------- | --------------------------------------------------------------- |
| `DISABLED`       | Disable cancel button and doesn't show it.                      |
| `TOP_LEFT`       | Position cancel button in top right.                            |
| `TOP_RIGHT`      | Position cancel button in top right. It's **default** position. |

#### `StatusBarColor` (`iOS` only)

This type must be used to status bar color.

| `StatusBarColor` | Description                                  |
| ---------------- | -------------------------------------------- |
| `DARK_CONTENT`   | **Default** color to status bar.             |
| `DEFAULT`        | Status bar color that's set from the device. |
| `LIGHT_CONTENT`  | Light color to status bar.                   |

#### `ThemeImage`

An object containing the image assets used in the Aziface SDK.

| `ThemeImage` | type     | Platform | Required | Default                     |
| ------------ | -------- | -------- | -------- | --------------------------- |
| `logo`       | `string` | All      | ❌       | `facetec_your_app_logo.png` |
| `cancel`     | `string` | All      | ❌       | `facetec_cancel.png`        |

#### `ThemeFrame`

An object containing the frame styles used in the Aziface SDK.

| `ThemeFrame`      | type     | Platform | Required | Default   |
| ----------------- | -------- | -------- | -------- | --------- |
| `cornerRadius`    | `number` | All      | ❌       | `20`      |
| `borderColor`     | `string` | All      | ❌       | `#ffffff` |
| `backgroundColor` | `string` | All      | ❌       | `#ffffff` |

#### `ThemeButton`

An object containing the button styles used in the Aziface SDK.

| `ThemeButton`              | type     | Platform | Required | Default   |
| -------------------------- | -------- | -------- | -------- | --------- |
| `backgroundNormalColor`    | `string` | All      | ❌       | `#026ff4` |
| `backgroundDisabledColor`  | `string` | All      | ❌       | `#b3d4fc` |
| `backgroundHighlightColor` | `string` | All      | ❌       | `#0264dc` |
| `textNormalColor`          | `string` | All      | ❌       | `#ffffff` |
| `textDisabledColor`        | `string` | All      | ❌       | `#ffffff` |
| `textHighlightColor`       | `string` | All      | ❌       | `#ffffff` |

#### `ThemeGuidance`

An object containing the styles used in the guidance view.

| `ThemeGuidance`   | type                                                    | Platform | Required | Default                                                |
| ----------------- | ------------------------------------------------------- | -------- | -------- | ------------------------------------------------------ |
| `backgroundColor` | `string` or `string[]`                                  | All      | ❌       | `#ffffff` (Android) and `['#ffffff', '#ffffff']` (iOS) |
| `foregroundColor` | `string`                                                | All      | ❌       | `#272937`                                              |
| `button`          | [`ThemeButton`](#themebutton)                           | All      | ❌       | `undefined`                                            |
| `retryScreen`     | [`ThemeGuidanceRetryScreen`](#themeguidanceretryscreen) | All      | ❌       | `undefined`                                            |

##### `ThemeGuidanceRetryScreen`

An object containing the styles used in the guidance retry screen.

| `ThemeGuidanceRetryScreen` | type     | Platform | Required | Default   |
| -------------------------- | -------- | -------- | -------- | --------- |
| `imageBorderColor`         | `string` | All      | ❌       | `#ffffff` |
| `ovalStrokeColor`          | `string` | All      | ❌       | `#ffffff` |

#### `ThemeOval`

An object containing the oval styles used in the Aziface SDK.

| `ThemeOval`           | type     | Platform | Required | Default   |
| --------------------- | -------- | -------- | -------- | --------- |
| `strokeColor`         | `string` | All      | ❌       | `#026ff4` |
| `firstProgressColor`  | `string` | All      | ❌       | `#0264dc` |
| `secondProgressColor` | `string` | All      | ❌       | `#0264dc` |

#### `ThemeFeedback`

An object containing the oval styles used in the Aziface SDK.

| `ThemeFeedback`                                         | type     | Platform | Required | Default     |
| ------------------------------------------------------- | -------- | -------- | -------- | ----------- |
| `backgroundColor`                                       | `string` | Android  | ❌       | `#026ff4`   |
| [`backgroundColors`](#feedbackbackgroundcolor-ios-only) | `string` | iOS      | ❌       | `undefined` |
| `textColor`                                             | `string` | All      | ❌       | `#ffffff`   |

##### `FeedbackBackgroundColor` (`iOS` only)

This type must be used to **set** the **theme** of the feedback box.

| `FeedbackBackgroundColor` | Description                                                                                        | type                       | Required | Default                  |
| ------------------------- | -------------------------------------------------------------------------------------------------- | -------------------------- | -------- | ------------------------ |
| `colors`                  | An array of colors defining the color of each gradient stop.                                       | `string[]`                 | ❌       | `["#026FF4", "#026FF4"]` |
| `locations`               | It's accepts only two values between `0` and `1` that defining the location of each gradient stop. | `[number, number]`         | ❌       | `[0, 1]`                 |
| `startPoint`              | The start point of the gradient when drawn in the layer’s coordinate space.                        | [`Point`](#point-ios-only) | ❌       | `{ x: 0, y: 0 }`         |
| `endPoint`                | The end point of the gradient when drawn in the layer’s coordinate space.                          | [`Point`](#point-ios-only) | ❌       | `{ x: 1, y: 0 }`         |

###### `Point` (`iOS` only)

This interface defines the drawn in the layer's coordinate space.

| `Point` | type     | Required | Default |
| ------- | -------- | -------- | ------- |
| `x`     | `number` | ❌       | `0`     |
| `y`     | `number` | ❌       | `0`     |

#### `ThemeResultScreen`

An object containing the styles used in the result screen.

| `ThemeResultScreen`       | type                                            | Platform | Required | Default                                                |
| ------------------------- | ----------------------------------------------- | -------- | -------- | ------------------------------------------------------ |
| `backgroundColor`         | `string` or `string[]`                          | All      | ❌       | `#ffffff` (Android) and `['#ffffff', '#ffffff']` (iOS) |
| `foregroundColor`         | `string`                                        | All      | ❌       | `#272937`                                              |
| `activityIndicatorColor`  | `string`                                        | All      | ❌       | `#026ff4`                                              |
| `uploadProgressFillColor` | `string`                                        | All      | ❌       | `#026ff4`                                              |
| `resultAnimation`         | [`ThemeResultAnimation`](#themeresultanimation) | All      | ❌       | `undefined`                                            |

##### `ThemeResultAnimation`

An object containing the animation styles used in the Aziface SDK result animation.

| `ThemeResultAnimation` | type     | Platform | Required | Default   |
| ---------------------- | -------- | -------- | -------- | --------- |
| `backgroundColor`      | `string` | All      | ❌       | `#026ff4` |
| `foregroundColor`      | `string` | All      | ❌       | `#ffffff` |

#### `ThemeIdScan`

An object containing the styles used in the ID scan screens.

| `ThemeIdScan`     | type                                                        | Platform | Required | Default     |
| ----------------- | ----------------------------------------------------------- | -------- | -------- | ----------- |
| `selectionScreen` | [`ThemeIdScanSelectionScreen`](#themeidscanselectionscreen) | All      | ❌       | `undefined` |
| `reviewScreen`    | [`ThemeIdScanReviewScreen`](#themeidscanreviewscreen)       | All      | ❌       | `undefined` |
| `captureScreen`   | [`ThemeIdScanCaptureScreen`](#themeidscancapturescreen)     | All      | ❌       | `undefined` |
| `button`          | [`ThemeButton`](#themebutton)                               | All      | ❌       | `undefined` |

##### `ThemeIdScanSelectionScreen`

An object containing the styles used in the ID scan selection screen.

| `ThemeIdScanSelectionScreen` | type                   | Platform | Required | Default                                                |
| ---------------------------- | ---------------------- | -------- | -------- | ------------------------------------------------------ |
| `backgroundColor`            | `string` or `string[]` | All      | ❌       | `#ffffff` (Android) and `['#ffffff', '#ffffff']` (iOS) |
| `foregroundColor`            | `string`               | All      | ❌       | `#272937`                                              |

##### `ThemeIdScanReviewScreen`

An object containing the styles used in the ID scan review screen.

| `ThemeIdScanReviewScreen` | type     | Platform | Required | Default   |
| ------------------------- | -------- | -------- | -------- | --------- |
| `foregroundColor`         | `string` | All      | ❌       | `#ffffff` |
| `textBackgroundColor`     | `string` | All      | ❌       | `#026ff4` |

##### `ThemeIdScanCaptureScreen`

An object containing the styles used in the ID scan capture screen.

| `ThemeIdScanCaptureScreen` | type     | Platform | Required | Default   |
| -------------------------- | -------- | -------- | -------- | --------- |
| `foregroundColor`          | `string` | All      | ❌       | `#ffffff` |
| `textBackgroundColor`      | `string` | All      | ❌       | `#ffffff` |
| `backgroundColor`          | `string` | All      | ❌       | `#026ff4` |
| `frameStrokeColor`         | `string` | All      | ❌       | `#ffffff` |

#### `DefaultMessage`

This interface represents the success message and loading data message during to Aziface SDK flow. It interface is used by processors's [enroll](#enroll) processor.

| `DefaultMessage` | type     | Platform | Required | Default                            |
| ---------------- | -------- | -------- | -------- | ---------------------------------- |
| `successMessage` | `string` | All      | ❌       | `Face Scanned\n3D Liveness Proven` |
| `uploadMessage`  | `string` | iOS      | ❌       | `Still Uploading...`               |

#### `DefaultScanMessage`

This interface represents the all scan messages during to Aziface SDK flow. It interface is used by [photoMatch](#photomatch) processors.

| `DefaultScanMessage` | type                                                                      | Platform | Required | Default               |
| -------------------- | ------------------------------------------------------------------------- | -------- | -------- | --------------------- |
| `skipOrErrorNFC`     | `string`                                                                  | All      | ❌       | `ID Details Uploaded` |
| `frontSide`          | [`DefaultScanMessageFrontSide`](#defaultscanmessagefrontside)             | All      | ❌       | `undefined`           |
| `backSide`           | [`DefaultScanMessageBackSide`](#defaultscanmessagebackside)               | All      | ❌       | `undefined`           |
| `userConfirmedInfo`  | [`DefaultScanMessageUserConfirmInfo`](#defaultscanmessageuserconfirminfo) | All      | ❌       | `undefined`           |
| `nfc`                | [`DefaultScanMessageNFC`](#defaultscanmessagenfc)                         | All      | ❌       | `undefined`           |
| `skippedNFC`         | [`DefaultScanMessageSkippedNFC`](#defaultscanmessageskippednfc)           | All      | ❌       | `undefined`           |
| `success`            | [`DefaultScanMessageSuccess`](#defaultscanmessagesuccess)                 | All      | ❌       | `undefined`           |
| `retry`              | [`DefaultScanMessageRetry`](#defaultscanmessageretry)                     | All      | ❌       | `undefined`           |

##### `DefaultScanMessageFrontSide`

Represents the front-side scan messages during to Aziface SDK flow.

| `DefaultScanMessageFrontSide`      | type     | Platform | Required | Default                              |
| ---------------------------------- | -------- | -------- | -------- | ------------------------------------ |
| `uploadStarted`                    | `string` | All      | ❌       | `Uploading Encrypted ID Scan`        |
| `stillUploading`                   | `string` | All      | ❌       | `Still Uploading... Slow Connection` |
| `uploadCompleteAwaitingResponse`   | `string` | All      | ❌       | `Upload Complete`                    |
| `uploadCompleteAwaitingProcessing` | `string` | All      | ❌       | `Processing ID Scan`                 |

##### `DefaultScanMessageBackSide`

Represents the back-side scan messages during to Aziface SDK flow.

| `DefaultScanMessageBackSide`       | type     | Platform | Required | Default                              |
| ---------------------------------- | -------- | -------- | -------- | ------------------------------------ |
| `uploadStarted`                    | `string` | All      | ❌       | `Uploading Encrypted Back of ID`     |
| `stillUploading`                   | `string` | All      | ❌       | `Still Uploading... Slow Connection` |
| `uploadCompleteAwaitingResponse`   | `string` | All      | ❌       | `Upload Complete`                    |
| `uploadCompleteAwaitingProcessing` | `string` | All      | ❌       | `Processing Back of ID`              |

##### `DefaultScanMessageUserConfirmInfo`

Represents the user confirmed information messages during to Aziface SDK flow.

| `DefaultScanMessageUserConfirmInfo` | type     | Platform | Required | Default                              |
| ----------------------------------- | -------- | -------- | -------- | ------------------------------------ |
| `uploadStarted`                     | `string` | All      | ❌       | `Uploading Your Confirmed Info`      |
| `stillUploading`                    | `string` | All      | ❌       | `Still Uploading... Slow Connection` |
| `uploadCompleteAwaitingResponse`    | `string` | All      | ❌       | `Upload Complete`                    |
| `uploadCompleteAwaitingProcessing`  | `string` | All      | ❌       | `Processing`                         |

##### `DefaultScanMessageNFC`

Represents the NFC scan messages during to Aziface SDK flow.

| `DefaultScanMessageNFC`            | type     | Platform | Required | Default                              |
| ---------------------------------- | -------- | -------- | -------- | ------------------------------------ |
| `uploadStarted`                    | `string` | All      | ❌       | `Uploading Encrypted NFC Details`    |
| `stillUploading`                   | `string` | All      | ❌       | `Still Uploading... Slow Connection` |
| `uploadCompleteAwaitingResponse`   | `string` | All      | ❌       | `Upload Complete`                    |
| `uploadCompleteAwaitingProcessing` | `string` | All      | ❌       | `Processing NFC Details`             |

##### `DefaultScanMessageSkippedNFC`

Represents the skipped NFC scan messages during to Aziface SDK flow.

| `DefaultScanMessageSkippedNFC`     | type     | Platform | Required | Default                              |
| ---------------------------------- | -------- | -------- | -------- | ------------------------------------ |
| `uploadStarted`                    | `string` | All      | ❌       | `Uploading Encrypted ID Details`     |
| `stillUploading`                   | `string` | All      | ❌       | `Still Uploading... Slow Connection` |
| `uploadCompleteAwaitingResponse`   | `string` | All      | ❌       | `Upload Complete`                    |
| `uploadCompleteAwaitingProcessing` | `string` | All      | ❌       | `Processing ID Details`              |

##### `DefaultScanMessageSuccess`

Represents the success messages during to Aziface SDK flow.

| `DefaultScanMessageSuccess` | type     | Platform | Required | Default                  |
| --------------------------- | -------- | -------- | -------- | ------------------------ |
| `frontSide`                 | `string` | All      | ❌       | `ID Scan Complete`       |
| `frontSideBackNext`         | `string` | All      | ❌       | `Front of ID Scanned`    |
| `frontSideNFCNext`          | `string` | All      | ❌       | `Front of ID Scanned`    |
| `backSide`                  | `string` | All      | ❌       | `ID Scan Complete`       |
| `backSideNFCNext`           | `string` | All      | ❌       | `Back of ID Scanned`     |
| `passport`                  | `string` | All      | ❌       | `Passport Scan Complete` |
| `passportNFCNext`           | `string` | All      | ❌       | `Passport Scanned`       |
| `userConfirmation`          | `string` | All      | ❌       | `Photo ID Scan Complete` |
| `NFC`                       | `string` | All      | ❌       | `ID Scan Complete`       |

##### `DefaultScanMessageRetry`

Represents the retry messages during to Aziface SDK flow.

| `DefaultScanMessageSkippedNFC` | type     | Platform | Required | Default                             |
| ------------------------------ | -------- | -------- | -------- | ----------------------------------- |
| `faceDidNotMatch`              | `string` | All      | ❌       | `Face Didn’t Match Highly Enough`   |
| `IDNotFullyVisible`            | `string` | All      | ❌       | `ID Document Not Fully Visible`     |
| `OCRResultsNotGoodEnough`      | `string` | All      | ❌       | `ID Text Not Legible`               |
| `IDTypeNotSupported`           | `string` | All      | ❌       | `ID Type Mismatch Please Try Again` |

<hr/>

### `Errors`

| `Errors`                   | Description                                                                        | Platform |
| -------------------------- | ---------------------------------------------------------------------------------- | -------- |
| `ConfigNotProvided`        | When `device`, `url`, `key` and `productionKey` aren't provided.                   | All      |
| `HTTPSError`               | When exists some network error.                                                    | All      |
| `InitializationFailed`     | When parameters provided by initialize are inconsistent or invalid.                | All      |
| `JSONError`                | When exists some problem in getting `data` in request of **base URL** information. | All      |
| `ParamsNotProvided`        | When parameters aren't provided, this case, it is `null`.                          | All      |
| `NotInitialized`           | When session status is different of success.                                       | All      |
| `ProcessorError`           | When an unknown session error occurs.                                              | All      |
| `SessionNotProcessedError` | When the image ID sent to the processors cannot be processed due to inconsistency. | All      |
| `SessionScanStatusError`   | When scan session status isn't success. It's recommend try again.                  | All      |
| `SessionStatusError`       | When session status isn't of success. It's recommend try again.                    | All      |

<hr/>

## Native Events (Deprecated)

In the near future we will remove native events for native components, with this you will be able to capture opening and closing events of the Aziface module on the screen.

| Methods                                                              | Return Type                                                                                               | Platform |
| -------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | -------- |
| `addListener(eventType: string, callback: (event: boolean) => void)` | [`EmitterSubscription`](https://reactnative.dev/docs/native-modules-android#sending-events-to-javascript) | All      |

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

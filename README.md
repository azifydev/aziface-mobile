# @azify/aziface-mobile

Aziface sdk adapter to react native. 📱

- [Installation](#installation)
- [Usage](#usage)
- [API](#api)
  - [`initialize(init: AzifaceSdkProps.Initialize)`](#initializeinit-azifacesdkinitialize)
  - [`faceMatch(type: AzifaceSdkProps.MatchType, data?: AzifaceSdkProps.MatchData)`](#facematchtype-azifacesdkmatchtype-data-azifacesdkmatchdata)
  - [`photoMatch(data?: Object)`](#photomatchdata-azifacesdkdata)
  - [`setTheme(options?: AzifaceSdkProps.Theme)`](#setthemeoptions-azifacesdktheme)
- [Types](#types)
  - [`AzifaceSdkProps.Params`](#azifacesdkparams)
  - [`AzifaceSdkProps.Headers`](#azifacesdkheaders)
  - [`AzifaceSdkProps.Theme`](#azifacesdktheme)
  - [`AzifaceSdkProps.ButtonLocation`](#azifacesdkbuttonlocation)
  - [`AzifaceSdkProps.StatusBarColor`](#azifacesdkstatusbarcolor-ios-only)
  - [`AzifaceSdkProps.FeedbackBackgroundColor`](#azifacesdkfeedbackbackgroundcolor-ios-only)
  - [`AzifaceSdkProps.Point`](#azifacesdkpoint-ios-only)
  - [`AzifaceSdkProps.DefaultMessage`](#azifacesdkdefaultmessage)
  - [`AzifaceSdkProps.DefaultScanMessage`](#azifacesdkdefaultscanmessage)
  - [`AzifaceSdkProps.Errors`](#azifacesdkerrors)
  - [`AzifaceSdkProps.MatchType`](#azifacesdkmatchtype)
  - [`AzifaceSdkProps.MatchData`](#azifacesdkmatchdata)
- [Native Events](#native-events)
  - [`Event Types`](#event-types)
- [How to add images in AzifaceSDK module?](#how-to-add-images-in-azifacesdk-module)
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
import AzifaceSdk, {
  AzifaceSdkProps,
  initialize,
  faceMatch,
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
      'clientInfo': 'YUOR_CLIENT_INFO',
      'contentType': 'YOUR_CONTENT_TYPE',
      'device': 'YOUR_DEVICE',
      'deviceid': 'YOUR_DEVICE_ID',
      'deviceip': 'YOUR_DEVICE_IP',
      'locale': 'YOUR_LOCALE',
      'xForwardedFor': 'YOUR_X_FORWARDED_FOR',
      'user-agent': 'YOUR_USER_AGENT',
    };
    const params = {
      device: 'YOUR_DEVICE',
      url: 'YOUR_BASE_URL',
      key: 'YOUR_KEY',
      productionKey: 'YOUR_PRODUCTION_KEY',
    };

    const isInitialized = await initialize({
      params,
      headers,
    });

    console.log(isInitialized);
  };

  const emitter = new NativeEventEmitter(AzifaceSdk);
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

  const onPressFaceMatch = async (
    type: AzifaceSdkProps.MatchType,
    data?: AzifaceSdkProps.MatchData
  ) => {
    try {
      const isSuccess = await faceMatch(type, data);
      console.log(isSuccess);
    } catch (error: any) {
      console.error(error.message);
    }
  };

  return (
    <ScrollView style={styles.container}>
      <View style={styles.content}>
        <TouchableOpacity style={styles.button} onPress={init}>
          <Text style={styles.text}>Init Aziface Module</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={onPressPhotoMatch}>
          <Text style={styles.text}>Open Photo Match</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.button}
          onPress={async () => await onPressFaceMatch('enroll')}
        >
          <Text style={styles.text}>Open Enroll</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.button}
          onPress={async () =>
            await onPressFaceMatch('authenticate', { id: '123456' })
          }
        >
          <Text style={styles.text}>Open Authenticate</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.button}
          onPress={async () => await onPressFaceMatch('liveness')}
        >
          <Text style={styles.text}>Open Liveness</Text>
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

| Methods                                                                                                                                      | Return Type        | iOS | Android |
| -------------------------------------------------------------------------------------------------------------------------------------------- | ------------------ | --- | ------- |
| [`initialize(init: AzifaceSdkProps.Initialize)`](#initializeinit-azifacesdkinitialize)                                                       | `Promise<boolean>` | ✅  | ✅      |
| [`faceMatch(type: AzifaceSdkProps.MatchType, data?: AzifaceSdkProps.MatchData`](#facematchtype-azifacesdkmatchtype-data-azifacesdkmatchdata) | `Promise<boolean>` | ✅  | ✅      |
| [`photoMatch(data?: Object)`](#photomatchdata-azifacesdkdata)                                                                                | `Promise<boolean>` | ✅  | ✅      |
| [`setTheme(options?: AzifaceSdkProps.Theme)`](#setthemeoptions-azifacesdktheme)                                                              | `void`             | ✅  | ✅      |

### `initialize(init: AzifaceSdkProps.Initialize)`

This is the **principal** method to be called, he must be **called first** to initialize the Aziface SDK. If he doens't be called the other methods **don't works!**

| `AzifaceSdkProps.Initialize` | type                                            | Required | Default     |
| ---------------------------- | ----------------------------------------------- | -------- | ----------- |
| `params`                     | [`AzifaceSdkProps.Params`](#azifacesdkparams)   | ✅       | -           |
| `headers`                    | [`AzifaceSdkProps.Headers`](#azifacesdkheaders) | ❌       | `undefined` |

### `faceMatch(type: AzifaceSdkProps.MatchType, data?: AzifaceSdkProps.MatchData)`

This method is called to make enrollment, authenticate and liveness available. The type is required and it must provided to select which flow you are interested.

- **Enrollment**: This method makes a 3D reading of the user's face. But, you must use to **subscribe** user in Aziface SDK or in your server.
- **Authenticate**: This method makes a 3D reading of the user's face. But, you must use to **authenticate** user in Aziface SDK or in your server.
- **Liveness**: This method makes a 3D reading of the user's face.

| `Object` | type                                                | Required | Default     |
| -------- | --------------------------------------------------- | -------- | ----------- |
| `type`   | [`AzifaceSdkProps.MatchType`](#azifacesdkmatchtype) | ✅       | -           |
| `data`   | [`AzifaceSdkProps.MatchData`](#azifacesdkmatchdata) | ❌       | `undefined` |

### `photoMatch(data?: Object)`

This method make to read from face and documents for user, after comparate face and face documents from user to check veracity.

| `Object` | type     | Required | Default     |
| -------- | -------- | -------- | ----------- |
| `data`   | `Object` | ❌       | `undefined` |

### `setTheme(options?: AzifaceSdkProps.Theme)`

This method must be used to **set** the **theme** of the Aziface SDK screen.

| `AzifaceSdkProps.Theme` | type                                        | Required | Default     |
| ----------------------- | ------------------------------------------- | -------- | ----------- |
| `options`               | [`AzifaceSdkProps.Theme`](#azifacesdktheme) | ❌       | `undefined` |

<hr/>

## Types

| `AzifaceSdkProps` - Types                                                                | iOS | Android |
| ---------------------------------------------------------------------------------------- | --- | ------- |
| [`AzifaceSdkProps.Params`](#azifacesdkparams)                                            | ✅  | ✅      |
| [`AzifaceSdkProps.Headers`](#azifacesdkheaders)                                          | ✅  | ✅      |
| [`AzifaceSdkProps.Theme`](#azifacesdktheme)                                              | ✅  | ✅      |
| [`AzifaceSdkProps.ButtonLocation`](#azifacesdkbuttonlocation)                            | ✅  | ✅      |
| [`AzifaceSdkProps.StatusBarColor`](#azifacesdkstatusbarcolor-ios-only)                   | ✅  | ❌      |
| [`AzifaceSdkProps.FeedbackBackgroundColor`](#azifacesdkfeedbackbackgroundcolor-ios-only) | ✅  | ❌      |
| [`AzifaceSdkProps.Point`](#azifacesdkpoint-ios-only)                                     | ✅  | ❌      |
| [`AzifaceSdkProps.DefaultMessage`](#azifacesdkdefaultmessage)                            | ✅  | ✅      |
| [`AzifaceSdkProps.Errors`](#azifacesdkerrors)                                            | ✅  | ✅      |
| [`AzifaceSdkProps.MatchType`](#azifacesdkdefaultscanmessage)                             | ✅  | ✅      |
| [`AzifaceSdkProps.MatchData`](#azifacesdkdefaultscanmessage)                             | ✅  | ✅      |

### `AzifaceSdkProps.Params`

Here must be passed to initialize the Aziface SDK! Case the parameters isn't provided the Aziface SDK goes to be not initialized.

| `AzifaceSdkProps.Params` | type      | Required |
| ------------------------ | --------- | -------- |
| `device`                 | `string`  | ✅       |
| `url`                    | `string`  | ✅       |
| `key`                    | `string`  | ✅       |
| `productionKey`          | `string`  | ✅       |
| `isDeveloperMode`        | `boolean` | ❌       |

### `AzifaceSdkProps.Headers`

Here you can add your headers to send request when some method is called. Only values from type **string**, **null** or **undefined** are accepts!

| `AzifaceSdkProps.Headers` | type                            | Required | Default     |
| ------------------------- | ------------------------------- | -------- | ----------- |
| `[key: string]`           | `string`, `null` or `undefined` | ❌       | `undefined` |

### `AzifaceSdkProps.Theme`

This is a list of theme properties that can be used to styling. We recommend that you use hexadecimal values for colors. RGB, RGBA, HSL and HSLA colors are also supported.

| `AzifaceSdkProps.Theme`                        | type                                                                                                                                    | iOS | Android | Required | Default                                                                                                 |
| ---------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------- | --- | ------- | -------- | ------------------------------------------------------------------------------------------------------- |
| `logoImage`                                    | `string`                                                                                                                                | ✅  | ✅      | ❌       | `facetec_your_app_logo.png`                                                                             |
| `cancelImage`                                  | `string`                                                                                                                                | ✅  | ✅      | ❌       | `facetec_cancel.png`                                                                                    |
| `cancelButtonLocation`                         | [`AzifaceSdkProps.ButtonLocation`](#azifacesdkbuttonlocation)                                                                           | ✅  | ✅      | ❌       | `TOP_RIGHT`                                                                                             |
| `defaultStatusBarColorIos`                     | [`AzifaceSdkProps.StatusBarColor`](#azifacesdkstatusbarcolor-ios-only)                                                                  | ✅  | ❌      | ❌       | `DARK_CONTENT`                                                                                          |
| `frameCornerRadius`                            | `number`                                                                                                                                | ✅  | ✅      | ❌       | `10` (iOS) and `20` (Android)                                                                           |
| `frameBackgroundColor`                         | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `frameBorderColor`                             | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `overlayBackgroundColor`                       | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `guidanceBackgroundColorsAndroid`              | `string`                                                                                                                                | ❌  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `guidanceBackgroundColorsIos`                  | `string[]`                                                                                                                              | ✅  | ❌      | ❌       | `["#FFFFFF", "#FFFFFF"]`                                                                                |
| `guidanceForegroundColor`                      | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#272937`                                                                                               |
| `guidanceButtonBackgroundNormalColor`          | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `guidanceButtonBackgroundDisabledColor`        | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#B3D4FC`                                                                                               |
| `guidanceButtonBackgroundHighlightColor`       | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#0264DC`                                                                                               |
| `guidanceButtonTextNormalColor`                | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `guidanceButtonTextDisabledColor`              | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `guidanceButtonTextHighlightColor`             | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `guidanceRetryScreenImageBorderColor`          | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `guidanceRetryScreenOvalStrokeColor`           | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `ovalStrokeColor`                              | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `ovalFirstProgressColor`                       | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#0264DC`                                                                                               |
| `ovalSecondProgressColor`                      | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#0264DC`                                                                                               |
| `feedbackBackgroundColorsAndroid`              | `string`                                                                                                                                | ❌  | ✅      | ❌       | `#026FF4`                                                                                               |
| `feedbackBackgroundColorsIos`                  | [`AzifaceSdkProps.FeedbackBackgroundColor` ](#azifacesdkfeedbackbackgroundcolor-ios-only)                                               | ✅  | ❌      | ❌       | [`FeedbackBackgroundColor` ](#azifacesdkfeedbackbackgroundcolor-ios-only)                               |
| `feedbackTextColor`                            | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `resultScreenBackgroundColorsAndroid`          | `string`                                                                                                                                | ❌  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `resultScreenBackgroundColorsIos`              | `string[]`                                                                                                                              | ✅  | ❌      | ❌       | `["#FFFFFF", "#FFFFFF"]`                                                                                |
| `resultScreenForegroundColor`                  | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#272937`                                                                                               |
| `resultScreenActivityIndicatorColor`           | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `resultScreenResultAnimationBackgroundColor`   | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `resultScreenResultAnimationForegroundColor`   | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `resultScreenUploadProgressFillColor`          | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `idScanSelectionScreenBackgroundColorsAndroid` | `string`                                                                                                                                | ❌  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanSelectionScreenBackgroundColorsIos`     | `string[]`                                                                                                                              | ✅  | ❌      | ❌       | `["#FFFFFF", "#FFFFFF"]`                                                                                |
| `idScanSelectionScreenForegroundColor`         | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#272937`                                                                                               |
| `idScanReviewScreenForegroundColor`            | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanReviewScreenTextBackgroundColor`        | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `idScanCaptureScreenForegroundColor`           | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanCaptureScreenTextBackgroundColor`       | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `idScanButtonBackgroundNormalColor`            | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#026FF4`                                                                                               |
| `idScanButtonBackgroundDisabledColor`          | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#B3D4FC`                                                                                               |
| `idScanButtonBackgroundHighlightColor`         | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#0264DC`                                                                                               |
| `idScanButtonTextNormalColor`                  | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanButtonTextDisabledColor`                | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanButtonTextHighlightColor`               | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanCaptureScreenBackgroundColor`           | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `idScanCaptureFrameStrokeColor`                | `string`                                                                                                                                | ✅  | ✅      | ❌       | `#FFFFFF`                                                                                               |
| `autheticanteMessage`                          | [`AzifaceSdkProps.DefaultMessage`](#azifacesdkdefaultmessage)                                                                           | ✅  | ✅      | ❌       | [`DefaultMessage`](#azifacesdkdefaultmessage)                                                           |
| `enrollMessage`                                | [`AzifaceSdkProps.DefaultMessage`](#azifacesdkdefaultmessage)                                                                           | ✅  | ✅      | ❌       | [`DefaultMessage`](#azifacesdkdefaultmessage)                                                           |
| `livenessMessage`                              | [`AzifaceSdkProps.DefaultMessage`](#azifacesdkdefaultmessage)                                                                           | ✅  | ✅      | ❌       | [`DefaultMessage`](#azifacesdkdefaultmessage)                                                           |
| `photoIdMatchMessage`                          | [`AzifaceSdkProps.DefaultScanMessage`](#azifacesdkdefaultscanmessage) and [`AzifaceSdkProps.DefaultMessage`](#azifacesdkdefaultmessage) | ✅  | ✅      | ❌       | [`DefaultScanMessage`](#azifacesdkdefaultscanmessage) and [`DefaultMessage`](#azifacesdkdefaultmessage) |

### `AzifaceSdkProps.ButtonLocation`

This type must be used to position of the cancel button on screen.

| `AzifaceSdkProps.ButtonLocation` | Description                                                     |
| -------------------------------- | --------------------------------------------------------------- |
| `DISABLED`                       | Disable cancel button and doesn't show it.                      |
| `TOP_LEFT`                       | Position cancel button in top right.                            |
| `TOP_RIGHT`                      | Position cancel button in top right. It's **default** position. |

### `AzifaceSdkProps.StatusBarColor` (`iOS` only)

This type must be used to status bar color.

| `AzifaceSdkProps.StatusBarColor` | Description                                  |
| -------------------------------- | -------------------------------------------- |
| `DARK_CONTENT`                   | **Default** color to status bar.             |
| `DEFAULT`                        | Status bar color that's set from the device. |
| `LIGHT_CONTENT`                  | Light color to status bar.                   |

### `AzifaceSdkProps.FeedbackBackgroundColor` (`iOS` only)

This type must be used to **set** the **theme** of the feedback box.

| `AzifaceSdkProps.FeedbackBackgroundColor` | Description                                                                                    | type                                 | Required | Default                  |
| ----------------------------------------- | ---------------------------------------------------------------------------------------------- | ------------------------------------ | -------- | ------------------------ |
| `colors`                                  | An array of colors defining the color of each gradient stop.                                   | `string[]`                           | ❌       | `["#026FF4", "#026FF4"]` |
| `locations`                               | It's accepts only two values between 0 and 1 that defining the location of each gradient stop. | `[number, number]`                   | ❌       | `[0, 1]`                 |
| `startPoint`                              | The start point of the gradient when drawn in the layer’s coordinate space.                    | [`Point`](#azifacesdkpoint-ios-only) | ❌       | `x: 0` and `y: 0`        |
| `endPoint`                                | The end point of the gradient when drawn in the layer’s coordinate space.                      | [`Point`](#azifacesdkpoint-ios-only) | ❌       | `x: 1` and `y: 0`        |

### `AzifaceSdkProps.Point` (`iOS` only)

This interface defines the drawn in the layer's coordinate space.

| `AzifaceSdkProps.Point` | type     | Required | Default     |
| ----------------------- | -------- | -------- | ----------- |
| `x`                     | `number` | ❌       | `undefined` |
| `y`                     | `number` | ❌       | `undefined` |

### `AzifaceSdkProps.DefaultMessage`

This interface represents the success message and loading data message during to AzifaceSDK flow. It interface is used **more** by processors's [authenticate](#authenticatedata-azifacesdkdata) and [enroll](#enrolldata-azifacesdkdata) processors.

| `AzifaceSdkProps.DefaultMessage` | type     | iOS | Android | Required | Default                                                                 |
| -------------------------------- | -------- | --- | ------- | -------- | ----------------------------------------------------------------------- |
| `successMessage`                 | `string` | ✅  | ✅      | ❌       | `Liveness Confirmed` (Exception to authenticate method: `Autheticated`) |
| `uploadMessageIos`               | `string` | ✅  | ❌      | ❌       | `Still Uploading...`                                                    |

### `AzifaceSdkProps.DefaultScanMessage`

This interface represents the all scan messages during to AzifaceSDK flow. It interface is used by [photoMatch](#photomatchdata-azifacesdkdata) processors.

| `AzifaceSdkProps.DefaultScanMessage`                | type     | iOS | Android | Required | Default                              |
| --------------------------------------------------- | -------- | --- | ------- | -------- | ------------------------------------ |
| `frontSideUploadStarted`                            | `string` | ✅  | ✅      | ❌       | `Uploading Encrypted ID Scan`        |
| `frontSideStillUploading`                           | `string` | ✅  | ✅      | ❌       | `Still Uploading... Slow Connection` |
| `frontSideUploadCompleteAwaitingResponse`           | `string` | ✅  | ✅      | ❌       | `Upload Complete`                    |
| `frontSideUploadCompleteAwaitingResponse`           | `string` | ✅  | ✅      | ❌       | `Processing ID Scan`                 |
| `backSideUploadStarted`                             | `string` | ✅  | ✅      | ❌       | `Uploading Encrypted Back of ID`     |
| `backSideStillUploading`                            | `string` | ✅  | ✅      | ❌       | `Still Uploading... Slow Connection` |
| `backSideUploadCompleteAwaitingResponse`            | `string` | ✅  | ✅      | ❌       | `Upload Complete`                    |
| `backSideUploadCompleteAwaitingProcessing`          | `string` | ✅  | ✅      | ❌       | `Processing Back of ID`              |
| `userConfirmedInfoUploadStarted`                    | `string` | ✅  | ✅      | ❌       | `Uploading Your Confirmed Info`      |
| `userConfirmedInfoStillUploading`                   | `string` | ✅  | ✅      | ❌       | `Still Uploading... Slow Connection` |
| `userConfirmedInfoUploadCompleteAwaitingResponse`   | `string` | ✅  | ✅      | ❌       | `Upload Complete`                    |
| `userConfirmedInfoUploadCompleteAwaitingProcessing` | `string` | ✅  | ✅      | ❌       | `Processing`                         |
| `nfcUploadStarted`                                  | `string` | ✅  | ✅      | ❌       | `Uploading Encrypted NFC Details`    |
| `nfcStillUploading`                                 | `string` | ✅  | ✅      | ❌       | `Still Uploading... Slow Connection` |
| `nfcUploadCompleteAwaitingResponse`                 | `string` | ✅  | ✅      | ❌       | `Upload Complete`                    |
| `nfcUploadCompleteAwaitingProcessing`               | `string` | ✅  | ✅      | ❌       | `Processing NFC Details`             |
| `skippedNFCUploadStarted`                           | `string` | ✅  | ✅      | ❌       | `Uploading Encrypted ID Details`     |
| `skippedNFCStillUploading`                          | `string` | ✅  | ✅      | ❌       | `Still Uploading... Slow Connection` |
| `skippedNFCUploadCompleteAwaitingResponse`          | `string` | ✅  | ✅      | ❌       | `Upload Complete`                    |
| `skippedNFCUploadCompleteAwaitingProcessing`        | `string` | ✅  | ✅      | ❌       | `Processing ID Details`              |
| `successFrontSide`                                  | `string` | ✅  | ✅      | ❌       | `ID Scan Complete`                   |
| `successFrontSideBackNext`                          | `string` | ✅  | ✅      | ❌       | `Front of ID Scanned`                |
| `successFrontSideNFCNext`                           | `string` | ✅  | ✅      | ❌       | `Front of ID Scanned`                |
| `successBackSide`                                   | `string` | ✅  | ✅      | ❌       | `ID Scan Complete`                   |
| `successBackSideNFCNext`                            | `string` | ✅  | ✅      | ❌       | `Back of ID Scanned`                 |
| `successPassport`                                   | `string` | ✅  | ✅      | ❌       | `Passport Scan Complete`             |
| `successPassportNFCNext`                            | `string` | ✅  | ✅      | ❌       | `Passport Scanned`                   |
| `successUserConfirmation`                           | `string` | ✅  | ✅      | ❌       | `Photo ID Scan Complete`             |
| `successNFC`                                        | `string` | ✅  | ✅      | ❌       | `ID Scan Complete`                   |
| `retryFaceDidNotMatch`                              | `string` | ✅  | ✅      | ❌       | `Face Didn’t Match Highly Enough`    |
| `retryIDNotFullyVisible`                            | `string` | ✅  | ✅      | ❌       | `ID Document Not Fully Visible`      |
| `retryOCRResultsNotGoodEnough`                      | `string` | ✅  | ✅      | ❌       | `ID Text Not Legible`                |
| `retryIDTypeNotSupported`                           | `string` | ✅  | ✅      | ❌       | `ID Type Mismatch Please Try Again`  |
| `skipOrErrorNFC`                                    | `string` | ✅  | ✅      | ❌       | `ID Details Uploaded`                |

### `AzifaceSdkProps.Errors`

This enum represents all errors that are encountered on the AziFace SDK.

| `AzifaceSdkProps.Errors`        | Description                                                                                                          | iOS | Android |
| ------------------------------- | -------------------------------------------------------------------------------------------------------------------- | --- | ------- |
| `AziFaceHasNotBeenInitialized`  | When some processors method is runned, but AzifaceSDK **has not been initialized**.                                  | ✅  | ✅      |
| `AziFaceValuesWereNotProcessed` | When the image sent to the processors cannot be processed due to inconsistency.                                      | ✅  | ✅      |
| `HTTPSError`                    | When exists some network error.                                                                                      | ✅  | ✅      |
| `JSONError`                     | When exists some problem in getting data in request of **base URL** information.                                     | ✅  | ✅      |
| `NoConfigurationsProvided`      | When the configurations [`faceMatch`](#facematchtype-azifacesdkmatchtype-data-azifacesdkmatchdata) doesn't provided. | ❌  | ✅      |
| `AziFaceInvalidSession`         | When session status is invalid.                                                                                      | ❌  | ✅      |
| `AziFaceLivenessWasntProcessed` | When the image user sent to the processors cannot be processed due to inconsistency.                                 | ❌  | ✅      |
| `AziFaceScanWasntProcessed`     | When the image ID sent to the processors cannot be processed due to inconsistency.                                   | ❌  | ✅      |

### `AzifaceSdkProps.MatchType`

This enum represents all the possible types of flow that can be used on the [`faceMatch`](#facematchtype-azifacesdkmatchtype-data-azifacesdkmatchdata) method.

| `AzifaceSdkProps.MatchType` | Description                              | iOS | Android |
| --------------------------- | ---------------------------------------- | --- | ------- |
| `authenticate`              | When you want to make authenticate flow. | ✅  | ✅      |
| `enroll`                    | When you want to make enrollment flow.   | ✅  | ✅      |
| `liveness`                  | When you want to make liveness flow.     | ✅  | ✅      |

> The **authenticate flow** depends on to enrollment flow to **work** because the authenticate flow is done using an **UUID** that's was created by enrollment flow.

### `AzifaceSdkProps.MatchData`

The object with properties that will be sent to native modules to make the requests, change text labels and sent parameters via headers.

| `AzifaceSdkProps.MatchData` | type               | iOS | Android | Required | Default                                                                                     |
| --------------------------- | ------------------ | --- | ------- | -------- | ------------------------------------------------------------------------------------------- |
| `endpoint`                  | `string` or `null` | ✅  | ✅      | ❌       | `Authenticated` (authenticate) or `Liveness\nConfirmed` (enrollment and liveness)           |
| `parameters`                | `string` or `null` | ✅  | ✅      | ❌       | `null`                                                                                      |
| `successMessage`            | `string` or `null` | ✅  | ✅      | ❌       | `/match-3d-3d` (authenticate) or `/enrollment-3d` (enrollment) or `/liveness-3d` (liveness) |
| `uploadMessageIos`          | `string` or `null` | ✅  | ✅      | ❌       | `Still Uploading...`                                                                        |

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

## How to add images in AzifaceSDK module?

The `logoImage` and `cancelImage` properties represents your logo and icon of the button cancel. Does not possible to remove them from the module. Default are [Azify](https://azify.com/) images and `.png` format. By default in `Android` the logo image is shown, but on `iOS` it isn't shown, It's necessary to add manually.

### How to add images in Android?

To add your images in `Android`, you must go to your project's `android/src/main/res/drawable` directory. If in your project `drawable` folder doesn't exist, it create one. Inside the `drawable` folder, you must put your images and done!

### How to add images in iOS?

In `iOS`, open your XCode and go to your project's `ios/<YOUR_PROJECT_NAME>/Images.xcassets` directory. Open the `Images.xcassets` folder and only put your images inside there.

### Example with images added

Now, go back to where you want to apply the styles, import `setTheme` method and add only the image name, no extension format, in image property (`logoImage` or `cancelImage`). **Note**: If the image is not founded the default image will be showed. Check the code example below:

```tsx
import React, { useEffect } from 'react';
import { View, TouchableOpacity, Text } from 'react-native';
import {
  initialize,
  faceMatch,
  setTheme,
} from '@azify/aziface-mobile';

export default function App() {
  useEffect(() => {
    const params = {
      device: 'YOUR_DEVICE',
      url: 'YOUR_URL',
      key: 'YOUR_PUBLIC_KEY',
      productionKey: 'YOUR_PRODUCTION_KEY',
    };

    async function initialize() {
      await initialize({ params });
      setTheme({
        logoImage: 'yourLogoImage', // yourLogoImage.png
        cancelImage: 'yourCancelImage', // yourCancelImage.png
      });
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
            const isSuccess = await faceMatch('enroll');
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

See the [native implementation](./NATIVE_IMPLEMENTATION.md) to learn more about the limitations and features that will need improving in the `aziface-mobile-sdk`.

<hr/>

## Contributing

See the [contributing guide](./CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

<hr/>

## License

[MIT License](./LICENSE). 🙂

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob). 😊

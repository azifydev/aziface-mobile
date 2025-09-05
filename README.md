# @azify/aziface-mobile

<div align="center">
  <p>
    <img alt="Version" src="https://img.shields.io/github/package-json/v/azifydev/aziface-mobile?style=flat&color=brightgreen">
    <img alt="NPM Downloads" src="https://img.shields.io/npm/dm/%40azify%2Faziface-mobile?style=flat">
  </p>
</div>

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
  - [`Theme`](#theme)
    - [`ButtonLocation`](#buttonlocation)
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
  - [`Errors`](#errors)
- [Components](#components)
  - [`FaceView`](#faceview)
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
cd ios && pod install && cd ..
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
} from 'react-native';
import { enroll, initialize, photoMatch } from '@azify/aziface-mobile';

export default function App() {
  const init = async () => {
    /*
     * The SDK must be initialized first
     * so that the rest of the library
     * functions can work!
     *
     * */
    const headers = {
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
      isDevelopment: true,
      deviceKeyIdentifier: 'YOUR_DEVICE_KEY_IDENTIFIER',
      baseUrl: 'YOUR_BASE_URL',
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

| Methods           | Return Type        | Platform    |
| ----------------- | ------------------ | ----------- |
| `initialize`      | `Promise<boolean>` | All         |
| `enroll`          | `Promise<boolean>` | All         |
| `authenticate`    | `Promise<boolean>` | In Progress |
| `liveness`        | `Promise<boolean>` | In Progress |
| `photoMatch`      | `Promise<boolean>` | All         |
| `photoIDScanOnly` | `Promise<boolean>` | In Progress |
| `activeVocal`     | `void`             | In Progress |
| `setTheme`        | `void`             | All         |

### `initialize`

This is the **principal** method to be called, he must be **called first** to initialize the Aziface SDK. If he doens't be called the other methods **don't works!**

| `Initialize` | type                  | Required | Default     |
| ------------ | --------------------- | -------- | ----------- |
| `params`     | [`Params`](#params)   | ✅       | -           |
| `headers`    | [`Headers`](#headers) | ❌       | `undefined` |

### `enroll`

This method makes a 3D reading of the user's face. But, you must use to **subscribe** user in Aziface SDK or in your server.

| Property | type                                                                                                | Required | Default     |
| -------- | --------------------------------------------------------------------------------------------------- | -------- | ----------- |
| `data`   | [`Object`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object) | ❌       | `undefined` |

### `photoMatch`

This method make to read from face and documents for user, after compare face and face documents from user to check veracity.

| Property | type                                                                                                | Required | Default     |
| -------- | --------------------------------------------------------------------------------------------------- | -------- | ----------- |
| `data`   | [`Object`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object) | ❌       | `undefined` |

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

| Types                                                          | Platform |
| -------------------------------------------------------------- | -------- |
| [`Params`](#params)                                            | All      |
| [`Headers`](#headers)                                          | All      |
| [`Theme`](#theme)                                              | All      |
| [`ButtonLocation`](#buttonlocation)                            | All      |
| [`ThemeImage`](#themeimage)                                    | All      |
| [`ThemeFrame`](#themeframe)                                    | All      |
| [`ThemeButton`](#themebutton)                                  | All      |
| [`ThemeGuidance`](#themeguidance)                              | All      |
| [`ThemeGuidanceRetryScreen`](#themeguidanceretryscreen)        | All      |
| [`ThemeOval`](#themeoval)                                      | All      |
| [`ThemeFeedback`](#themefeedback)                              | All      |
| [`FeedbackBackgroundColor`](#feedbackbackgroundcolor-ios-only) | iOS      |
| [`Point`](#point-ios-only)                                     | iOS      |
| [`ThemeResultScreen`](#themeresultscreen)                      | All      |
| [`ThemeResultAnimation`](#themeresultanimation)                | All      |
| [`ThemeIdScan`](#themeidscan)                                  | All      |
| [`ThemeIdScanSelectionScreen`](#themeidscanselectionscreen)    | All      |
| [`ThemeIdScanReviewScreen`](#themeidscanreviewscreen)          | All      |
| [`ThemeIdScanCaptureScreen`](#themeidscancapturescreen)        | All      |

### `Params`

Here must be passed to initialize the Aziface SDK! Case the parameters isn't provided the Aziface SDK goes to be not initialized.

| `Params`              | type      | Required |
| --------------------- | --------- | -------- |
| `deviceKeyIdentifier` | `string`  | ✅       |
| `baseUrl`             | `string`  | ✅       |
| `isDevelopment`       | `boolean` | ❌       |

### `Headers`

Here you can add your headers to send request when some method is called. Only values from type **string**, **null** or **undefined** are accepts!

| `Headers`       | type                            | Required | Default     |
| --------------- | ------------------------------- | -------- | ----------- |
| `[key: string]` | `string`, `null` or `undefined` | ❌       | `undefined` |

### `Theme`

This is a list of theme properties that can be used to styling. Note, we recommend that you use **only** hexadecimal values to colors, between six and eight characters, because still we don't supported others color type.

| `Theme`                  | type                                      | Platform | Required | Default     |
| ------------------------ | ----------------------------------------- | -------- | -------- | ----------- |
| `overlayBackgroundColor` | `string`                                  | All      | ❌       | `#ffffff`   |
| `cancelButtonLocation`   | [`ButtonLocation`](#buttonlocation)       | All      | ❌       | `TOP_RIGHT` |
| `image`                  | [`ThemeImage`](#themeimage)               | All      | ❌       | `undefined` |
| `frame`                  | [`ThemeFrame`](#themeframe)               | All      | ❌       | `undefined` |
| `guidance`               | [`ThemeGuidance`](#themeguidance)         | All      | ❌       | `undefined` |
| `oval`                   | [`ThemeOval`](#themeoval)                 | All      | ❌       | `undefined` |
| `feedback`               | [`ThemeFeedback`](#themefeedback)         | All      | ❌       | `undefined` |
| `resultScreen`           | [`ThemeResultScreen`](#themeresultscreen) | All      | ❌       | `undefined` |
| `idScan`                 | [`ThemeIdScan`](#themeidscan)             | All      | ❌       | `undefined` |

#### `ButtonLocation`

This type must be used to position of the cancel button on screen.

| `ButtonLocation` | Description                                                     |
| ---------------- | --------------------------------------------------------------- |
| `DISABLED`       | Disable cancel button and doesn't show it.                      |
| `TOP_LEFT`       | Position cancel button in top right.                            |
| `TOP_RIGHT`      | Position cancel button in top right. It's **default** position. |

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

<hr/>

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
| `onInitialize` | Callback function called when the Aziface SDK is initialized.     | `boolean` | All      |

<hr/>

## How to add images in Aziface SDK module?

The `logo` and `cancel` properties represents your logo and icon of the button cancel. Does not possible to remove them from the module. Default are [Azify](https://www.azify.com/) images and `.png` format. By default in `Android` the logo image is shown, but on `iOS` it isn't shown, It's necessary to add manually.

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
      isDevelopment: true,
      deviceKeyIdentifier: 'YOUR_DEVICE_KEY_IDENTIFIER',
      baseUrl: 'YOUR_BASE_URL',
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

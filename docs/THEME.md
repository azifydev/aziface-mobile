# Theme 💅

The Aziface SDK provides the ability to change the theme of each flow. You can modify background colors, borders, text, border radius, among other things. We recommend changing the theme before calling the `initialized` function so that the style changes are applied.

<hr/>

## Summary

- [Usage](#usage)
- [API](#api)
  - [`setTheme`](#settheme)
    - [Properties](#properties)
- [Types](#types)
  - [`Theme`](#theme)
    - [`CancelLocation`](#cancellocation)
    - [`ThemeImage`](#themeimage)
      - [`CancelPosition`](#cancelposition)
        - [`CancelPositionAndroid` (Android only)](#cancelpositionandroid-android-only)
        - [`CancelPositionIOS` (iOS only)](#cancelpositionios-ios-only)
    - [`ThemeImage`](#themeimage)
    - [`ThemeFrame`](#themeframe)
    - [`ThemeButton`](#themebutton)
    - [`ThemeGuidance`](#themeguidance)
      - [`ThemeGuidanceRetryScreen`](#themeguidanceretryscreen)
      - [`ThemeGuidanceReadyScreen`](#themeguidancereadyscreen)
      - [`ThemeGuidanceImages`](#themeguidanceimages)
    - [`ThemeOval`](#themeoval)
    - [`ThemeFeedback`](#themefeedback)
      - [`FeedbackBackgroundColor` (iOS only)](#feedbackbackgroundcolor-ios-only)
        - [`Point`](#point)
    - [`ThemeResultScreen`](#themeresultscreen)
      - [`ThemeSessionAbortAnimation`](#themesessionabortanimation)
      - [`ThemeResultAnimation`](#themeresultanimation)
    - [`ThemeIdScan`](#themeidscan)
      - [`ThemeIdScanSelectionScreen`](#themeidscanselectionscreen)
      - [`ThemeIdScanReviewScreen`](#themeidscanreviewscreen)
      - [`ThemeIdScanCaptureScreen`](#themeidscancapturescreen)
    - [`ThemeShadow` (iOS only)](#themeshadow-ios-only)
      - [`ThemeShadowInsets`](#themeshadowinsets)
      - [`ThemeShadowOffset`](#themeshadowoffset)
- [How to add images in Aziface SDK module?](#how-to-add-images-in-aziface-sdk-module)
  - [Android](#android)
  - [iOS](#ios)
  - [Example](#example)
- [Custom Fonts](#custom-fonts)
  - [Android](#android-1)
  - [iOS](#ios-1)
  - [Example](#example-1)
- [Colors Support](#colors-support)

<hr/>

## Usage

```tsx
// It's recommended to use it before calling the initialize function
setTheme({
  overlayBackgroundColor: '#f1f1f1',
  // ...
});

await initialize({
  // ...
});
```

<hr/>

## API

| Methods    | Return Type | Platform |
| ---------- | ----------- | -------- |
| `setTheme` | `void`      | All      |

### `setTheme`

This method customize your SDK theme during session. **Note**: Currently, it's recommended testing the theme with a physical device. The SDK does not behave correctly with customizable themes in emulators.

#### Properties

| Property  | type              | Required | Default     |
| --------- | ----------------- | -------- | ----------- |
| `options` | [`Theme`](#theme) | ❌       | `undefined` |

<hr/>

## Types

| Types                                                          | Platform |
| -------------------------------------------------------------- | -------- |
| [`Theme`](#theme)                                              | All      |
| [`CancelLocation`](#cancellocation)                            | All      |
| [`ThemeImage`](#themeimage)                                    | All      |
| [`CancelPosition`](#cancelposition)                            | All      |
| [`CancelPositionAndroid`](#cancelpositionandroid-android-only) | Android  |
| [`CancelPositionIOS`](#cancelpositionios-ios-only)             | iOS      |
| [`ThemeFrame`](#themeframe)                                    | All      |
| [`ThemeButton`](#themebutton)                                  | All      |
| [`ThemeGuidance`](#themeguidance)                              | All      |
| [`ThemeGuidanceRetryScreen`](#themeguidanceretryscreen)        | All      |
| [`ThemeGuidanceReadyScreen`](#themeguidancereadyscreen)        | All      |
| [`ThemeGuidanceImages`](#themeguidanceimages)                  | All      |
| [`ThemeOval`](#themeoval)                                      | All      |
| [`ThemeFeedback`](#themefeedback)                              | All      |
| [`FeedbackBackgroundColor`](#feedbackbackgroundcolor-ios-only) | iOS      |
| [`Point`](#point)                                              | iOS      |
| [`ThemeResultScreen`](#themeresultscreen)                      | All      |
| [`ThemeSessionAbortAnimation`](#themesessionabortanimation)    | All      |
| [`ThemeResultAnimation`](#themeresultanimation)                | All      |
| [`ThemeIdScan`](#themeidscan)                                  | All      |
| [`ThemeIdScanSelectionScreen`](#themeidscanselectionscreen)    | All      |
| [`ThemeIdScanReviewScreen`](#themeidscanreviewscreen)          | All      |
| [`ThemeIdScanCaptureScreen`](#themeidscancapturescreen)        | All      |
| [`ThemeShadow`](#themeshadow-ios-only)                         | iOS      |
| [`ThemeShadowInsets`](#themeshadowinsets)                      | iOS      |
| [`ThemeShadowOffset`](#themeshadowoffset)                      | iOS      |

<hr/>

### `Theme`

This is a list of theme properties that can be used to styling. Note, we recommend that you use **only** hexadecimal values to colors on format `#RGB`, `#RGBA`, `#RRGGBB`, or `#RRGGBBAA` because still we don't supported others color type.

| `Theme`                  | type                                      | Platform | Required | Default     |
| ------------------------ | ----------------------------------------- | -------- | -------- | ----------- |
| `overlayBackgroundColor` | `string`                                  | All      | ❌       | `#ffffff`   |
| `image`                  | [`ThemeImage`](#themeimage)               | All      | ❌       | `undefined` |
| `frame`                  | [`ThemeFrame`](#themeframe)               | All      | ❌       | `undefined` |
| `guidance`               | [`ThemeGuidance`](#themeguidance)         | All      | ❌       | `undefined` |
| `oval`                   | [`ThemeOval`](#themeoval)                 | All      | ❌       | `undefined` |
| `feedback`               | [`ThemeFeedback`](#themefeedback)         | All      | ❌       | `undefined` |
| `resultScreen`           | [`ThemeResultScreen`](#themeresultscreen) | All      | ❌       | `undefined` |
| `idScan`                 | [`ThemeIdScan`](#themeidscan)             | All      | ❌       | `undefined` |

#### `CancelLocation`

This type must be used to position of the cancel button on screen.

| `CancelLocation` | Description                                                     |
| ---------------- | --------------------------------------------------------------- |
| `DISABLED`       | Disable cancel button and doesn't show it.                      |
| `TOP_LEFT`       | Position cancel button in top right.                            |
| `TOP_RIGHT`      | Position cancel button in top right. It's **default** position. |
| `CUSTOM`         | Indicate that cancel button will have custom position.          |

#### `ThemeImage`

An object containing the image assets used in the Aziface SDK.

| `ThemeImage`                 | type                                | Platform | Required | Default                            |
| ---------------------------- | ----------------------------------- | -------- | -------- | ---------------------------------- |
| `branding`                   | `string`                            | All      | ❌       | `undefined`                        |
| `isShowBranding`             | `boolean`                           | All      | ❌       | `undefined`                        |
| `isHideForCameraPermissions` | `boolean`                           | All      | ❌       | `true` (iOS) and `false` (Android) |
| `cancel`                     | `string`                            | All      | ❌       | `undefined`                        |
| `cancelLocation`             | [`CancelLocation`](#cancellocation) | All      | ❌       | `TOP_RIGHT`                        |
| `cancelPosition`             | [`CancelPosition`](#cancelposition) | All      | ❌       | `undefined`                        |

##### `CancelPosition`

This type must be used to set the custom position of the cancel button.

| `CancelPosition` | type                                                           | Platform | Required | Default     |
| ---------------- | -------------------------------------------------------------- | -------- | -------- | ----------- |
| `android`        | [`CancelPositionAndroid`](#cancelpositionandroid-android-only) | Android  | ❌       | `undefined` |
| `ios`            | [`CancelPositionIOS`](#cancelpositionios-ios-only)             | iOS      | ❌       | `undefined` |

###### `CancelPositionAndroid` (Android only)

The cancel button position for Android.

```tsx
setTheme({
  // Set cancel location as CUSTOM to enable custom position.
  cancelLocation: 'CUSTOM',
  cancelPosition: {
    android: {
      left: 32,
      right: 32,
      top: 32,
      bottom: 32,
    },
  },
});

await initialize({
  // ...
});
```

| `CancelPositionAndroid` | type     | Platform | Required | Default     |
| ----------------------- | -------- | -------- | -------- | ----------- |
| `left`                  | `number` | Android  | ✅       | `undefined` |
| `right`                 | `number` | Android  | ✅       | `undefined` |
| `top`                   | `number` | Android  | ✅       | `undefined` |
| `bottom`                | `number` | Android  | ✅       | `undefined` |

###### `CancelPositionIOS` (iOS only)

The cancel button position for iOS.

```tsx
setTheme({
  // Set cancel location as CUSTOM to enable custom position.
  cancelLocation: 'CUSTOM',
  cancelPosition: {
    android: {
      x: 20,
      y: 64,
      width: 32,
      height: 32,
    },
  },
});

await initialize({
  // ...
});
```

| `CancelPositionIOS` | type     | Platform | Required | Default     |
| ------------------- | -------- | -------- | -------- | ----------- |
| `x`                 | `number` | iOS      | ✅       | `undefined` |
| `y`                 | `number` | iOS      | ✅       | `undefined` |
| `width`             | `number` | iOS      | ✅       | `undefined` |
| `height`            | `number` | iOS      | ✅       | `undefined` |

#### `ThemeFrame`

An object containing the frame styles used in the Aziface SDK.

| `ThemeFrame`      | type                                   | Platform | Required | Default     |
| ----------------- | -------------------------------------- | -------- | -------- | ----------- |
| `cornerRadius`    | `number`                               | All      | ❌       | `20`        |
| `borderColor`     | `string`                               | All      | ❌       | `#ffffff`   |
| `borderWidth`     | `number`                               | All      | ❌       | `undefined` |
| `backgroundColor` | `string`                               | All      | ❌       | `#ffffff`   |
| `elevation`       | `number`                               | Android  | ❌       | `0`         |
| `shadow`          | [`ThemeShadow`](#themeshadow-ios-only) | iOS      | ❌       | `undefined` |

#### `ThemeButton`

An object containing the button styles used in the Aziface SDK.

| `ThemeButton`              | type     | Platform | Required | Default     |
| -------------------------- | -------- | -------- | -------- | ----------- |
| `backgroundNormalColor`    | `string` | All      | ❌       | `#026ff4`   |
| `backgroundDisabledColor`  | `string` | All      | ❌       | `#b3d4fc`   |
| `backgroundHighlightColor` | `string` | All      | ❌       | `#0264dc`   |
| `textNormalColor`          | `string` | All      | ❌       | `#ffffff`   |
| `textDisabledColor`        | `string` | All      | ❌       | `#ffffff`   |
| `textHighlightColor`       | `string` | All      | ❌       | `#ffffff`   |
| `cornerRadius`             | `number` | All      | ❌       | `undefined` |
| `borderWidth`              | `number` | All      | ❌       | `undefined` |
| `borderColor`              | `number` | All      | ❌       | `undefined` |
| `font`                     | `string` | All      | ❌       | `undefined` |

#### `ThemeGuidance`

An object containing the styles used in the guidance view.

| `ThemeGuidance`   | type                                                    | Platform | Required | Default                                                |
| ----------------- | ------------------------------------------------------- | -------- | -------- | ------------------------------------------------------ |
| `backgroundColor` | `string` or `string[]`                                  | All      | ❌       | `#ffffff` (Android) and `['#ffffff', '#ffffff']` (iOS) |
| `foregroundColor` | `string`                                                | All      | ❌       | `#272937`                                              |
| `headerFont`      | `string`                                                | All      | ❌       | `undefined`                                            |
| `subtextFont`     | `string`                                                | All      | ❌       | `undefined`                                            |
| `button`          | [`ThemeButton`](#themebutton)                           | All      | ❌       | `undefined`                                            |
| `retryScreen`     | [`ThemeGuidanceRetryScreen`](#themeguidanceretryscreen) | All      | ❌       | `undefined`                                            |
| `readyScreen`     | [`ThemeGuidanceReadyScreen`](#themeguidancereadyscreen) | All      | ❌       | `undefined`                                            |
| `images`          | [`ThemeGuidanceImages`](#themeguidanceimages)           | All      | ❌       | `undefined`                                            |

##### `ThemeGuidanceRetryScreen`

An object containing the styles used in the guidance retry screen.

| `ThemeGuidanceRetryScreen`    | type     | Platform | Required | Default     |
| ----------------------------- | -------- | -------- | -------- | ----------- |
| `imageBorderColor`            | `string` | All      | ❌       | `#ffffff`   |
| `imageBorderWidth`            | `number` | All      | ❌       | `undefined` |
| `imageCornerRadius`           | `number` | All      | ❌       | `undefined` |
| `ovalStrokeColor`             | `string` | All      | ❌       | `#ffffff`   |
| `headerTextColor`             | `string` | All      | ❌       | `#000000`   |
| `headerFont`                  | `string` | All      | ❌       | `undefined` |
| `subtextColor`                | `string` | All      | ❌       | `#000000`   |
| `subtextFont`                 | `string` | All      | ❌       | `undefined` |
| `textBackgroundColor`         | `string` | All      | ❌       | `undefined` |
| `textBackgroundCornersRadius` | `number` | All      | ❌       | `undefined` |

##### `ThemeGuidanceReadyScreen`

An object containing the styles used in the guidance ready screen.

| `ThemeGuidanceReadyScreen` | type     | Platform | Required | Default       |
| -------------------------- | -------- | -------- | -------- | ------------- |
| `headerTextColor`          | `string` | All      | ❌       | `#000000`     |
| `headerFont`               | `string` | All      | ❌       | `undefined`   |
| `ovalFillColor`            | `string` | All      | ❌       | `transparent` |
| `subtextColor`             | `string` | All      | ❌       | `#000000`     |
| `subtextFont`              | `string` | All      | ❌       | `undefined`   |

##### `ThemeGuidanceImages`

An object containing the images assets used in the guidance.

| `ThemeGuidanceImages` | type     | Platform | Required | Default     |
| --------------------- | -------- | -------- | -------- | ----------- |
| `cameraPermission`    | `string` | All      | ❌       | `undefined` |
| `ideal`               | `string` | All      | ❌       | `undefined` |

#### `ThemeOval`

An object containing the oval styles used in the Aziface SDK.

| `ThemeOval`            | type     | Platform | Required | Default     |
| ---------------------- | -------- | -------- | -------- | ----------- |
| `strokeColor`          | `string` | All      | ❌       | `#026ff4`   |
| `strokeWidth`          | `number` | All      | ❌       | `undefined` |
| `firstProgressColor`   | `string` | All      | ❌       | `#0264dc`   |
| `secondProgressColor`  | `string` | All      | ❌       | `#0264dc`   |
| `progressRadialOffset` | `number` | All      | ❌       | `undefined` |
| `progressStrokeWidth`  | `number` | All      | ❌       | `undefined` |

#### `ThemeFeedback`

An object containing the oval styles used in the Aziface SDK.

| `ThemeFeedback`                                         | type                                   | Platform | Required | Default     |
| ------------------------------------------------------- | -------------------------------------- | -------- | -------- | ----------- |
| `backgroundColor`                                       | `string`                               | Android  | ❌       | `#026ff4`   |
| [`backgroundColors`](#feedbackbackgroundcolor-ios-only) | `string`                               | iOS      | ❌       | `undefined` |
| `textColor`                                             | `string`                               | All      | ❌       | `#ffffff`   |
| `font`                                                  | `string`                               | All      | ❌       | `undefined` |
| `cornerRadius`                                          | `number`                               | All      | ❌       | `undefined` |
| `elevation`                                             | `number`                               | Android  | ❌       | `0`         |
| `shadow`                                                | [`ThemeShadow`](#themeshadow-ios-only) | iOS      | ❌       | `undefined` |
| `isEnablePulsatingText`                                 | `boolean`                              | All      | ❌       | `true`      |

##### `FeedbackBackgroundColor` (iOS only)

This type must be used to **set** the **theme** of the feedback box.

| `FeedbackBackgroundColor` | Description                                                                                        | type                       | Required | Default                  |
| ------------------------- | -------------------------------------------------------------------------------------------------- | -------------------------- | -------- | ------------------------ |
| `colors`                  | An array of colors defining the color of each gradient stop.                                       | `string[]`                 | ❌       | `["#026FF4", "#026FF4"]` |
| `locations`               | It's accepts only two values between `0` and `1` that defining the location of each gradient stop. | `[number, number]`         | ❌       | `[0, 1]`                 |
| `startPoint`              | The start point of the gradient when drawn in the layer’s coordinate space.                        | [`Point`](#point-ios-only) | ❌       | `{ x: 0, y: 0 }`         |
| `endPoint`                | The end point of the gradient when drawn in the layer’s coordinate space.                          | [`Point`](#point-ios-only) | ❌       | `{ x: 1, y: 0 }`         |

###### `Point`

This interface defines the drawn in the layer's coordinate space.

| `Point` | type     | Required | Default |
| ------- | -------- | -------- | ------- |
| `x`     | `number` | ❌       | `0`     |
| `y`     | `number` | ❌       | `0`     |

#### `ThemeResultScreen`

An object containing the styles used in the result screen.

| `ThemeResultScreen`                      | type                                                        | Platform | Required | Default                                                |
| ---------------------------------------- | ----------------------------------------------------------- | -------- | -------- | ------------------------------------------------------ |
| `backgroundColor`                        | `string` or `string[]`                                      | All      | ❌       | `#ffffff` (Android) and `['#ffffff', '#ffffff']` (iOS) |
| `foregroundColor`                        | `string`                                                    | All      | ❌       | `#272937`                                              |
| `font`                                   | `string`                                                    | All      | ❌       | `undefined`                                            |
| `activityIndicatorColor`                 | `string`                                                    | All      | ❌       | `#026ff4`                                              |
| `indicatorImage`                         | `string`                                                    | All      | ❌       | `undefined`                                            |
| `indicatorRotationInterval`              | `number`                                                    | All      | ❌       | `1000`                                                 |
| `uploadProgressFillColor`                | `string`                                                    | All      | ❌       | `#026ff4`                                              |
| `uploadProgressTrackColor`               | `string`                                                    | All      | ❌       | `#b3d4fc`                                              |
| `isShowUploadProgressBar`                | `boolean`                                                   | All      | ❌       | `true`                                                 |
| `animationRelativeScale`                 | `number`                                                    | All      | ❌       | `1`                                                    |
| `faceScanStillUploadingMessageDelayTime` | `number`                                                    | All      | ❌       | `6.0`                                                  |
| `idScanStillUploadingMessageDelayTime`   | `number`                                                    | All      | ❌       | `8.0`                                                  |
| `resultAnimation`                        | [`ThemeResultAnimation`](#themeresultanimation)             | All      | ❌       | `undefined`                                            |
| `sessionAbortAnimation`                  | [`ThemeSessionAbortAnimation`](#themesessionabortanimation) | All      | ❌       | `undefined`                                            |

##### `ThemeResultAnimation`

An object containing the animation styles used in the Aziface SDK result animation.

| `ThemeResultAnimation`         | type     | Platform | Required | Default     |
| ------------------------------ | -------- | -------- | -------- | ----------- |
| `backgroundColor`              | `string` | All      | ❌       | `#026ff4`   |
| `foregroundColor`              | `string` | All      | ❌       | `#ffffff`   |
| `displayTime`                  | `number` | All      | ❌       | `2.5`       |
| `IDScanSuccessForegroundColor` | `string` | All      | ❌       | `#026ff4`   |
| `successImage`                 | `string` | All      | ❌       | `undefined` |
| `unsuccessImage`               | `string` | All      | ❌       | `undefined` |
| `unsuccessBackgroundColor`     | `string` | All      | ❌       | `#cc0044`   |
| `unsuccessForegroundColor`     | `string` | All      | ❌       | `#ffffff`   |

##### `ThemeSessionAbortAnimation`

An object containing the animation styles used in the Aziface SDK session abort animation.

| `ThemeSessionAbortAnimation` | type     | Platform | Required | Default     |
| ---------------------------- | -------- | -------- | -------- | ----------- |
| `foregroundColor`            | `string` | All      | ❌       | `#ffffff`   |
| `backgroundColor`            | `string` | All      | ❌       | `#cc0044`   |
| `image`                      | `string` | All      | ❌       | `undefined` |

#### `ThemeIdScan`

An object containing the styles used in the ID scan screens.

| `ThemeIdScan`     | type                                                        | Platform | Required | Default     |
| ----------------- | ----------------------------------------------------------- | -------- | -------- | ----------- |
| `headerFont`      | `string`                                                    | All      | ❌       | `undefined` |
| `subtextFont`     | `string`                                                    | All      | ❌       | `undefined` |
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

| `ThemeIdScanReviewScreen` | type                   | Platform | Required | Default                                                |
| ------------------------- | ---------------------- | -------- | -------- | ------------------------------------------------------ |
| `backgroundColor`         | `string` or `string[]` | All      | ❌       | `#ffffff` (Android) and `['#ffffff', '#ffffff']` (iOS) |
| `foregroundColor`         | `string`               | All      | ❌       | `#ffffff`                                              |
| `textBackgroundColor`     | `string`               | All      | ❌       | `#026ff4`                                              |

##### `ThemeIdScanCaptureScreen`

An object containing the styles used in the ID scan capture screen.

| `ThemeIdScanCaptureScreen` | type     | Platform | Required | Default     |
| -------------------------- | -------- | -------- | -------- | ----------- |
| `foregroundColor`          | `string` | All      | ❌       | `#ffffff`   |
| `textBackgroundColor`      | `string` | All      | ❌       | `#ffffff`   |
| `backgroundColor`          | `string` | All      | ❌       | `#026ff4`   |
| `frameStrokeColor`         | `string` | All      | ❌       | `#ffffff`   |
| `font`                     | `string` | All      | ❌       | `undefined` |

#### `ThemeShadow` (iOS only)

An object containing the shadow styles used during capture screen. If you want to use shadow in Android App, you the `elevation` property in the `frame` or `feedback` objects.

| `ThemeShadow` | type                                      | Platform | Required | Default     |
| ------------- | ----------------------------------------- | -------- | -------- | ----------- |
| `color`       | `string`                                  | iOS      | ❌       | `#000000`   |
| `opacity`     | `number`                                  | iOS      | ❌       | `1`         |
| `radius`      | `number`                                  | iOS      | ❌       | `10`        |
| `offset`      | [`ThemeShadowInsets`](#themeshadowoffset) | iOS      | ❌       | `undefined` |
| `insets`      | [`ThemeShadowOffset`](#themeshadowinsets) | iOS      | ❌       | `undefined` |

##### `ThemeShadowInsets`

An object containing the shadow inset styles used in screen.

| `ThemeShadowInsets` | type     | Platform | Required | Default |
| ------------------- | -------- | -------- | -------- | ------- |
| `top`               | `number` | iOS      | ❌       | `0`     |
| `left`              | `number` | iOS      | ❌       | `0`     |
| `bottom`            | `number` | iOS      | ❌       | `0`     |
| `right`             | `number` | iOS      | ❌       | `0`     |

##### `ThemeShadowOffset`

An object containing the shadow offset styles used in screen.

| `ThemeShadowOffset` | type     | Platform | Required | Default |
| ------------------- | -------- | -------- | -------- | ------- |
| `width`             | `number` | iOS      | ❌       | `0`     |
| `height`            | `number` | iOS      | ❌       | `0`     |

<hr/>

## How to add images in Aziface SDK module?

The `branding` and `cancel` properties represents your branding and icon of the button cancel. Does not possible to remove them from the module. Default are [Azify](https://www.azify.com/) images and `.png` format. By default in `Android` the branding image is shown, but on `iOS` it isn't shown, It's necessary to add manually.

### Android

To add your images in `Android`, you must go to your project's `android/src/main/res/drawable` directory. If in your project `drawable` folder doesn't exist, it create one. Inside the `drawable` folder, you must put your images and done!

**Important**: The filename of the image can't have uppercase letters, Android doesn't accept these characters in the image name.

### iOS

In `iOS`, open your XCode and go to your project's `ios/<YOUR_PROJECT_NAME>/Images.xcassets` directory. Open the `Images.xcassets` folder and only put your images inside there.

### Example

Now, go back to where you want to apply the styles, import `setTheme` function and add only the image name, no extension format, in image property (`branding` or `cancel`). **Note**: If the image is not founded the default image will be showed. Check the code example below:

```tsx
import { useEffect } from 'react';
// ...
import {
  initialize,
  setTheme,
  type Params /* ... */,
} from '@azify/aziface-mobile';

export default function App() {
  useEffect(() => {
    const params: Params = {
      isDevelopment: true,
      deviceKeyIdentifier: 'YOUR_DEVICE_KEY_IDENTIFIER',
      baseUrl: 'YOUR_BASE_URL',
    };

    async function initialize() {
      // You call setTheme after initialize.
      setTheme({
        image: {
          branding: 'branding', // branding.png
          cancel: 'close', // close.png
        },
      });

      await initialize({ params });
    }

    initialize();
  }, []);

  // ...
}
```

<hr/>

## Custom Fonts

The Aziface SDK allows changing the font family style to each the session.

### Android

In Android, you should add all fonts in the `android/app/main/assets/fonts` directory path (if your project's `assets/fonts` isn't exists, you should create it), but if the font isn't found, the Aziface SDK uses the default system font. By default, the Aziface SDK searches for your custom fonts there.

All extension fonts are supported.

### iOS

To add a font file to your XCode project, select File > Add Files to "Your Project Name" from the menu bar, or drag the file from Finder and drop it into your XCode project. You can add True Type Font (`.ttf`) and Open Type Font (`.otf`) files. Also, make sure the font file is a target member of your App, otherwise, the font file will not be distributed as part of your App.

After adding the font file to your project, you need to let iOS know about the font. To do this, add the key "Fonts provided by application" to `Info.plist` (the raw key name is `UIAppFonts`). XCode creates an array value for the key, add the name of the font file as an item of the array. Be sure to include the file extension as part of the name.

That's **optional**, but you can add the key "Application fonts resource path" to `Info.plist` (the raw key to be used like a font file's resource path). XCode creates a string value for the key, add the directory path of the font file, if your font file is in the main directory you should add a dot (`.`) like value for the key, otherwise, you should provides the path directory correctly the font files. If custom fonts isn't found, the Aziface SDK uses the default system font.

Extension fonts supported are: `.ttf` and `.otf`.

### Example

You should call the `setTheme` function and provides **filename string** as value of the font. If the font isn't found, the Aziface SDK uses the default font.

```tsx
import { useEffect } from 'react';
// ...
import {
  initialize,
  setTheme,
  type Params /* ... */,
} from '@azify/aziface-mobile';

export default function App() {
  useEffect(() => {
    const params: Params = {
      isDevelopment: true,
      deviceKeyIdentifier: 'YOUR_DEVICE_KEY_IDENTIFIER',
      baseUrl: 'YOUR_BASE_URL',
    };

    async function initialize() {
      // You call setTheme after initialize.
      setTheme({
        guidance: {
          headerFont: 'Roboto-Bold.otf',
          subtextFont: 'OpenSans-Medium.ttf',
          button: {
            font: 'NotoSans-Regular.otf',
          },
          readyScreen: {
            headerFont: 'Roboto-Bold.otf',
            subtextFont: 'OpenSans-Medium.ttf',
          },
          retryScreen: {
            headerFont: 'Roboto-Bold.otf',
            subtextFont: 'OpenSans-Medium.ttf',
          },
        },
      });

      await initialize({ params });
    }

    initialize();
  }, []);

  // ...
}
```

<hr/>

## Colors Support

Currently, the Aziface SDK theme accepts only hexadecimal colors in this format:

- `#RGB`
- `#RGBA`
- `#RRGGBB`
- `#RRGGBBAA`

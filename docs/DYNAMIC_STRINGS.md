# Dynamic Strings ✏️

The Aziface SDK allows applications to dynamically customize and override the text strings displayed in the SDK’s user interface during verification sessions.

This enables the application to replace default UI messages such as instructions, error messages, button labels, and guidance text with custom strings at runtime. It is commonly used to adapt wording, terminology, or tone to better align with product language, branding, regulatory requirements, or user context.

Dynamic strings are set with [`setLocale`](#setlocale). The same method changes the SDK language: pass one of the [built-in locales](#built-in-locales) to translate every string, or pass your own [`Locale`](#locale) object to customize them. You can also spread a built-in locale and override only the strings you want to change.

The dynamic strings apply across Aziface SDK workflows, including enrollment, authentication, liveness checks, photo scan, and photo match verification. To ensure consistency, call `setLocale` before starting a session so that all UI elements display the customized text.

If a provided string key is invalid or missing, the SDK falls back to its default text (English) for that element. To keep a single language on screen, spread a built-in locale before your custom strings, as shown in [Usage](#usage).

<hr/>

## Summary

- [Usage](#usage)
- [API](#api)
  - [`setLocale`](#setlocale)
    - [Properties](#properties)
- [Built-in locales](#built-in-locales)
- [Types](#types)
  - [`Locale`](#locale)
    - [`LocaleAccessibility`](#localeaccessibility)
    - [`LocaleAccessibilityFeedback`](#localeaccessibilityfeedback)
      - [`LocaleAccessibilityFeedbackFace`](#localeaccessibilityfeedbackface)
    - [`LocaleAction`](#localeaction)
    - [`LocaleCamera`](#localecamera)
      - [`LocaleCameraPermission`](#localecamerapermission)
    - [`LocaleFeedback`](#localefeedback)
      - [`LocaleFeedbackFace`](#localefeedbackface)
      - [`LocaleFeedbackMove`](#localefeedbackmove)
    - [`LocaleIdScan`](#localeidscan)
      - [`LocaleIdScanCapture`](#localeidscancapture)
      - [`LocaleIdScanFeedback`](#localeidscanfeedback)
      - [`LocaleIdScanNfc`](#localeidscannfc)
      - [`LocaleIdScanOcr`](#localeidscanocr)
      - [`LocaleIdScanReview`](#localeidscanreview)
    - [`LocaleInstructions`](#localeinstructions)
    - [`LocalePresession`](#localepresession)
    - [`LocaleResult`](#localeresult)
      - [`LocaleResultFaceScan`](#localeresultfacescan)
        - [`LocaleResultFaceScanSuccess3d`](#localeresultfacescansuccess3d)
      - [`LocaleResultIdScan`](#localeresultidscan)
        - [`LocaleResultIdScanSuccess`](#localeresultidscansuccess)
        - [`LocaleResultIdScanRetry`](#localeresultidscanretry)
        - [`LocaleResultIdScanUpload`](#localeresultidscanupload)
    - [`LocaleRetry`](#localeretry)
      - [`LocaleRetryOfficialIdPhoto`](#localeretryofficialidphoto)
    - [`LocaleLabel`](#localelabel)

<hr/>

## Usage

```tsx
import { Button } from 'react-native';
import { setLocale, enroll, en, ptBR } from '@azify/aziface-mobile';

export default function App() {
  // ...

  function onChangeLanguage() {
    // Translates every string to Brazilian Portuguese.
    setLocale(ptBR);
  }

  function onCustomizeStrings() {
    setLocale({
      ...en, // rest of English language object
      action: {
        ...en.action,
        ok: "Let's go!",
        imReady: "I'm ready!",
      },
      feedback: {
        ...en.feedback,
        centerFace: 'Put your face in the center',
      },
    });
  }

  return (
    <>
      <Button title="Português" onPress={onChangeLanguage} />
      <Button title="Custom Strings" onPress={onCustomizeStrings} />

      {/* The next session uses the texts set above. */}
      <Button title="Enroll" onPress={() => enroll()} />
    </>
  );
}
```

<hr/>

## API

| Methods     | Return Type | Platform |
| ----------- | ----------- | -------- |
| `setLocale` | `void`      | All      |

### `setLocale`

The `setLocale` sets the text strings displayed in the SDK’s user interface during verification sessions. Use it to change the SDK language with a [built-in locale](#built-in-locales), or to customize and override the strings with your own [`Locale`](#locale) object.

Calling `setLocale` without arguments resets the strings to the [`en`](#built-in-locales) locale. `setLocale` doesn't return anything.

#### Properties

| Property | type                | Required | Default |
| -------- | ------------------- | -------- | ------- |
| `locale` | [`Locale`](#locale) | ❌       | `en`    |

<hr/>

## Built-in locales

The library exports a complete [`Locale`](#locale) object for each supported language. Each object is typed as `Required<Locale>`, so you can spread it as the base of your custom strings.

| Locale | Description                     | Platform |
| ------ | ------------------------------- | -------- |
| `af`   | Afrikaans language.             | All      |
| `ar`   | Arabic language.                | All      |
| `de`   | German language.                | All      |
| `el`   | Greek language.                 | All      |
| `en`   | English language (default).     | All      |
| `es`   | Spanish and Castilian language. | All      |
| `fr`   | French language.                | All      |
| `ja`   | Japanese language.              | All      |
| `kk`   | Kazakh language.                | All      |
| `nb`   | Norwegian Bokmål language.      | All      |
| `ptBR` | Portuguese Brazilian language.  | All      |
| `ru`   | Russian language.               | All      |
| `vi`   | Vietnamese language.            | All      |
| `zh`   | Chinese language.               | All      |

<hr/>

## Types

| Types                                                                 | Platform |
| --------------------------------------------------------------------- | -------- |
| [`Locale`](#locale)                                                   | All      |
| [`LocaleAccessibility`](#localeaccessibility)                         | All      |
| [`LocaleAccessibilityFeedback`](#localeaccessibilityfeedback)         | All      |
| [`LocaleAccessibilityFeedbackFace`](#localeaccessibilityfeedbackface) | All      |
| [`LocaleAction`](#localeaction)                                       | All      |
| [`LocaleCamera`](#localecamera)                                       | All      |
| [`LocaleCameraPermission`](#localecamerapermission)                   | All      |
| [`LocaleFeedback`](#localefeedback)                                   | All      |
| [`LocaleFeedbackFace`](#localefeedbackface)                           | All      |
| [`LocaleFeedbackMove`](#localefeedbackmove)                           | All      |
| [`LocaleIdScan`](#localeidscan)                                       | All      |
| [`LocaleIdScanCapture`](#localeidscancapture)                         | All      |
| [`LocaleIdScanFeedback`](#localeidscanfeedback)                       | All      |
| [`LocaleIdScanNfc`](#localeidscannfc)                                 | All      |
| [`LocaleIdScanOcr`](#localeidscanocr)                                 | All      |
| [`LocaleIdScanReview`](#localeidscanreview)                           | All      |
| [`LocaleInstructions`](#localeinstructions)                           | All      |
| [`LocalePresession`](#localepresession)                               | All      |
| [`LocaleResult`](#localeresult)                                       | All      |
| [`LocaleResultFaceScan`](#localeresultfacescan)                       | All      |
| [`LocaleResultFaceScanSuccess3d`](#localeresultfacescansuccess3d)     | All      |
| [`LocaleResultIdScan`](#localeresultidscan)                           | All      |
| [`LocaleResultIdScanSuccess`](#localeresultidscansuccess)             | All      |
| [`LocaleResultIdScanRetry`](#localeresultidscanretry)                 | All      |
| [`LocaleResultIdScanUpload`](#localeresultidscanupload)               | All      |
| [`LocaleRetry`](#localeretry)                                         | All      |
| [`LocaleRetryOfficialIdPhoto`](#localeretryofficialidphoto)           | All      |
| [`LocaleLabel`](#localelabel)                                         | All      |

### `Locale`

An object with all the strings used in the Aziface SDK screen. All properties are optional. See the [built-in locales](#built-in-locales) for complete objects.

| `Locale`                | type                                                          | Platform | Required | Default     |
| ----------------------- | ------------------------------------------------------------- | -------- | -------- | ----------- |
| `accessibility`         | [`LocaleAccessibility`](#localeaccessibility)                 | All      | ❌       | `undefined` |
| `accessibilityFeedback` | [`LocaleAccessibilityFeedback`](#localeaccessibilityfeedback) | All      | ❌       | `undefined` |
| `action`                | [`LocaleAction`](#localeaction)                               | All      | ❌       | `undefined` |
| `camera`                | [`LocaleCamera`](#localecamera)                               | All      | ❌       | `undefined` |
| `feedback`              | [`LocaleFeedback`](#localefeedback)                           | All      | ❌       | `undefined` |
| `idScan`                | [`LocaleIdScan`](#localeidscan)                               | All      | ❌       | `undefined` |
| `instructions`          | [`LocaleInstructions`](#localeinstructions)                   | All      | ❌       | `undefined` |
| `presession`            | [`LocalePresession`](#localepresession)                       | All      | ❌       | `undefined` |
| `result`                | [`LocaleResult`](#localeresult)                               | All      | ❌       | `undefined` |
| `retry`                 | [`LocaleRetry`](#localeretry)                                 | All      | ❌       | `undefined` |

#### `LocaleAccessibility`

An object containing the accessibility dynamic strings used in the Aziface SDK.

| `LocaleAccessibility` | type     | Platform | Required | Default     |
| --------------------- | -------- | -------- | -------- | ----------- |
| `cancelButton`        | `string` | All      | ❌       | `undefined` |
| `torchButton`         | `string` | All      | ❌       | `undefined` |
| `tapGuidance`         | `string` | All      | ❌       | `undefined` |

#### `LocaleAccessibilityFeedback`

An object containing the accessibility feedback dynamic strings used in the Aziface SDK.

| `LocaleAccessibilityFeedback` | type                                                                  | Platform | Required | Default     |
| ----------------------------- | --------------------------------------------------------------------- | -------- | -------- | ----------- |
| `movePhoneAway`               | `string`                                                              | All      | ❌       | `undefined` |
| `movePhoneCloser`             | `string`                                                              | All      | ❌       | `undefined` |
| `holdDeviceToEyeLevel`        | `string`                                                              | All      | ❌       | `undefined` |
| `face`                        | [`LocaleAccessibilityFeedbackFace`](#localeaccessibilityfeedbackface) | All      | ❌       | `undefined` |

##### `LocaleAccessibilityFeedbackFace`

An object containing the face feedback accessibility dynamic strings used in the Aziface SDK.

| `LocaleAccessibilityFeedbackFace` | type     | Platform | Required | Default     |
| --------------------------------- | -------- | -------- | -------- | ----------- |
| `tooFarLeft`                      | `string` | All      | ❌       | `undefined` |
| `tooFarRight`                     | `string` | All      | ❌       | `undefined` |
| `tooLow`                          | `string` | All      | ❌       | `undefined` |
| `tooHigh`                         | `string` | All      | ❌       | `undefined` |
| `rotatedTooFarLeft`               | `string` | All      | ❌       | `undefined` |
| `rotatedTooFarRight`              | `string` | All      | ❌       | `undefined` |
| `pointingTooFarLeft`              | `string` | All      | ❌       | `undefined` |
| `pointingTooFarRight`             | `string` | All      | ❌       | `undefined` |
| `notOnCamera`                     | `string` | All      | ❌       | `undefined` |

#### `LocaleAction`

An object containing the action dynamic strings used in the Aziface SDK.

| `LocaleAction` | type     | Platform | Required | Default     |
| -------------- | -------- | -------- | -------- | ----------- |
| `ok`           | `string` | All      | ❌       | `undefined` |
| `imReady`      | `string` | All      | ❌       | `undefined` |
| `tryAgain`     | `string` | All      | ❌       | `undefined` |
| `continue`     | `string` | All      | ❌       | `undefined` |
| `takePhoto`    | `string` | All      | ❌       | `undefined` |
| `retakePhoto`  | `string` | All      | ❌       | `undefined` |
| `acceptPhoto`  | `string` | All      | ❌       | `undefined` |
| `confirm`      | `string` | All      | ❌       | `undefined` |
| `scanNfc`      | `string` | All      | ❌       | `undefined` |
| `scanNfcCard`  | `string` | All      | ❌       | `undefined` |
| `skipNfc`      | `string` | All      | ❌       | `undefined` |

#### `LocaleCamera`

An object containing the camera dynamic strings used in the Aziface SDK.

| `LocaleCamera`       | type                                                | Platform | Required | Default     |
| -------------------- | --------------------------------------------------- | -------- | -------- | ----------- |
| `initializingCamera` | `string`                                            | All      | ❌       | `undefined` |
| `permission`         | [`LocaleCameraPermission`](#localecamerapermission) | All      | ❌       | `undefined` |

##### `LocaleCameraPermission`

An object containing the camera permission dynamic strings used in the Aziface SDK.

| `LocaleCameraPermission` | type     | Platform | Required | Default     |
| ------------------------ | -------- | -------- | -------- | ----------- |
| `header`                 | `string` | All      | ❌       | `undefined` |
| `enroll`                 | `string` | All      | ❌       | `undefined` |
| `auth`                   | `string` | All      | ❌       | `undefined` |
| `enableCamera`           | `string` | All      | ❌       | `undefined` |
| `launchSettings`         | `string` | All      | ❌       | `undefined` |

#### `LocaleFeedback`

An object containing the feedback dynamic strings used in the Aziface SDK.

| `LocaleFeedback`  | type                                        | Platform | Required | Default     |
| ----------------- | ------------------------------------------- | -------- | -------- | ----------- |
| `centerFace`      | `string`                                    | All      | ❌       | `undefined` |
| `holdSteady`      | `string`                                    | All      | ❌       | `undefined` |
| `useEvenLighting` | `string`                                    | All      | ❌       | `undefined` |
| `face`            | [`LocaleFeedbackFace`](#localefeedbackface) | All      | ❌       | `undefined` |
| `move`            | [`LocaleFeedbackMove`](#localefeedbackmove) | All      | ❌       | `undefined` |

##### `LocaleFeedbackFace`

An object containing the face feedback dynamic strings used in the Aziface SDK.

| `LocaleFeedbackFace`      | type     | Platform | Required | Default     |
| ------------------------- | -------- | -------- | -------- | ----------- |
| `notFound`                | `string` | All      | ❌       | `undefined` |
| `notLookingStraightAhead` | `string` | All      | ❌       | `undefined` |
| `notUpright`              | `string` | All      | ❌       | `undefined` |

##### `LocaleFeedbackMove`

An object containing the move feedback dynamic strings used in the Aziface SDK.

| `LocaleFeedbackMove` | type     | Platform | Required | Default     |
| -------------------- | -------- | -------- | -------- | ----------- |
| `phoneAway`          | `string` | All      | ❌       | `undefined` |
| `phoneCloser`        | `string` | All      | ❌       | `undefined` |
| `phoneToEyeLevel`    | `string` | All      | ❌       | `undefined` |

#### `LocaleIdScan`

An object containing the ID scan dynamic strings used in the Aziface SDK.

| `LocaleIdScan`        | type                                            | Platform | Required | Default     |
| --------------------- | ----------------------------------------------- | -------- | -------- | ----------- |
| `typeSelectionHeader` | `string`                                        | All      | ❌       | `undefined` |
| `additionalReview`    | `string`                                        | All      | ❌       | `undefined` |
| `capture`             | [`LocaleIdScanCapture`](#localeidscancapture)   | All      | ❌       | `undefined` |
| `feedback`            | [`LocaleIdScanFeedback`](#localeidscanfeedback) | All      | ❌       | `undefined` |
| `nfc`                 | [`LocaleIdScanNfc`](#localeidscannfc)           | All      | ❌       | `undefined` |
| `ocr`                 | [`LocaleIdScanOcr`](#localeidscanocr)           | All      | ❌       | `undefined` |
| `review`              | [`LocaleIdScanReview`](#localeidscanreview)     | All      | ❌       | `undefined` |

##### `LocaleIdScanCapture`

An object containing the ID scan capture dynamic strings used in the Aziface SDK.

| `LocaleIdScanCapture` | type     | Platform | Required | Default     |
| --------------------- | -------- | -------- | -------- | ----------- |
| `tapToFocus`          | `string` | All      | ❌       | `undefined` |
| `holdSteady`          | `string` | All      | ❌       | `undefined` |
| `idFrontInstruction`  | `string` | All      | ❌       | `undefined` |
| `idBackInstruction`   | `string` | All      | ❌       | `undefined` |

##### `LocaleIdScanFeedback`

An object containing the ID scan feedback dynamic strings used in the Aziface SDK.

| `LocaleIdScanFeedback` | type     | Platform | Required | Default     |
| ---------------------- | -------- | -------- | -------- | ----------- |
| `flipIdToBack`         | `string` | All      | ❌       | `undefined` |
| `flipIdToFront`        | `string` | All      | ❌       | `undefined` |

##### `LocaleIdScanNfc`

An object containing the ID scan NFC dynamic strings used in the Aziface SDK.

| `LocaleIdScanNfc`             | type     | Platform | Required | Default     |
| ----------------------------- | -------- | -------- | -------- | ----------- |
| `statusDisabled`              | `string` | All      | ❌       | `undefined` |
| `statusReady`                 | `string` | All      | ❌       | `undefined` |
| `cardStatusReady`             | `string` | All      | ❌       | `undefined` |
| `statusStarting`              | `string` | All      | ❌       | `undefined` |
| `cardStatusStarting`          | `string` | All      | ❌       | `undefined` |
| `statusScanning`              | `string` | All      | ❌       | `undefined` |
| `statusWeakConnection`        | `string` | All      | ❌       | `undefined` |
| `statusFinishedWithSuccess`   | `string` | All      | ❌       | `undefined` |
| `statusFinishedWithError`     | `string` | All      | ❌       | `undefined` |
| `cardStatusFinishedWithError` | `string` | All      | ❌       | `undefined` |
| `statusSkipped`               | `string` | All      | ❌       | `undefined` |

##### `LocaleIdScanOcr`

An object containing the ID scan OCR dynamic strings used in the Aziface SDK.

| `LocaleIdScanOcr`        | type     | Platform | Required | Default     |
| ------------------------ | -------- | -------- | -------- | ----------- |
| `confirmationMainHeader` | `string` | All      | ❌       | `undefined` |
| `confirmationScroll`     | `string` | All      | ❌       | `undefined` |

##### `LocaleIdScanReview`

An object containing the ID scan review dynamic strings used in the Aziface SDK.

| `LocaleIdScanReview` | type     | Platform | Required | Default     |
| -------------------- | -------- | -------- | -------- | ----------- |
| `idFrontInstruction` | `string` | All      | ❌       | `undefined` |
| `idBackInstruction`  | `string` | All      | ❌       | `undefined` |

#### `LocaleInstructions`

An object containing the instructions dynamic strings used in the Aziface SDK.

| `LocaleInstructions` | type                                            | Platform | Required | Default     |
| -------------------- | ----------------------------------------------- | -------- | -------- | ----------- |
| `header`             | [`Omit<LocaleLabel, 'tertiary'>`](#localelabel) | All      | ❌       | `undefined` |
| `message`            | [`Omit<LocaleLabel, 'tertiary'>`](#localelabel) | All      | ❌       | `undefined` |

#### `LocalePresession`

An object containing the presession dynamic strings used in the Aziface SDK.

| `LocalePresession`           | type     | Platform | Required | Default     |
| ---------------------------- | -------- | -------- | -------- | ----------- |
| `orientation`                | `string` | All      | ❌       | `undefined` |
| `frameYourFace`              | `string` | All      | ❌       | `undefined` |
| `positionFaceStraightInOval` | `string` | All      | ❌       | `undefined` |
| `removeDarkGlasses`          | `string` | All      | ❌       | `undefined` |
| `neutralExpression`          | `string` | All      | ❌       | `undefined` |
| `conditionsTooBright`        | `string` | All      | ❌       | `undefined` |
| `brightenYourEnvironment`    | `string` | All      | ❌       | `undefined` |

#### `LocaleResult`

| `LocaleResult` | type                                            | Platform | Required | Default     |
| -------------- | ----------------------------------------------- | -------- | -------- | ----------- |
| `nfcUpload`    | `string`                                        | All      | ❌       | `undefined` |
| `sessionAbort` | `string`                                        | All      | ❌       | `undefined` |
| `faceScan`     | [`LocaleResultFaceScan`](#localeresultfacescan) | All      | ❌       | `undefined` |
| `idScan`       | [`LocaleResultIdScan`](#localeresultidscan)     | All      | ❌       | `undefined` |

##### `LocaleResultFaceScan`

An object containing the face scan dynamic strings used in the Aziface SDK.

| `LocaleResultFaceScan` | type                                                              | Platform | Required | Default     |
| ---------------------- | ----------------------------------------------------------------- | -------- | -------- | ----------- |
| `upload`               | `string`                                                          | All      | ❌       | `undefined` |
| `uploadStillUploading` | `string`                                                          | All      | ❌       | `undefined` |
| `success3d`            | [`LocaleResultFaceScanSuccess3d`](#localeresultfacescansuccess3d) | All      | ❌       | `undefined` |

###### `LocaleResultFaceScanSuccess3d`

An object containing the face scan success 3D dynamic strings used in the Aziface SDK.

| `LocaleResultFaceScanSuccess3d` | type     | Platform | Required | Default     |
| ------------------------------- | -------- | -------- | -------- | ----------- |
| `enrollment`                    | `string` | All      | ❌       | `undefined` |
| `reverification`                | `string` | All      | ❌       | `undefined` |
| `livenessPriorToIdScan`         | `string` | All      | ❌       | `undefined` |
| `livenessAndOfficialIdPhoto`    | `string` | All      | ❌       | `undefined` |

##### `LocaleResultIdScan`

An object containing the ID scan dynamic strings used in the Aziface SDK.

| `LocaleResultIdScan`      | type                                                      | Platform | Required | Default     |
| ------------------------- | --------------------------------------------------------- | -------- | -------- | ----------- |
| `unsuccess`               | `string`                                                  | All      | ❌       | `undefined` |
| `skipOrErrorNfc`          | `string`                                                  | All      | ❌       | `undefined` |
| `additionalReviewTag`     | `string`                                                  | All      | ❌       | `undefined` |
| `uploadFrontSide`         | [`LocaleResultIdScanUpload`](#localeresultidscanupload)   | All      | ❌       | `undefined` |
| `uploadBackSide`          | [`LocaleResultIdScanUpload`](#localeresultidscanupload)   | All      | ❌       | `undefined` |
| `uploadUserConfirmedInfo` | [`LocaleResultIdScanUpload`](#localeresultidscanupload)   | All      | ❌       | `undefined` |
| `uploadNfc`               | [`LocaleResultIdScanUpload`](#localeresultidscanupload)   | All      | ❌       | `undefined` |
| `uploadSkippedNfc`        | [`LocaleResultIdScanUpload`](#localeresultidscanupload)   | All      | ❌       | `undefined` |
| `success`                 | [`LocaleResultIdScanSuccess`](#localeresultidscansuccess) | All      | ❌       | `undefined` |
| `retry`                   | [`LocaleResultIdScanRetry`](#localeresultidscanretry)     | All      | ❌       | `undefined` |

###### `LocaleResultIdScanUpload`

An object containing the upload dynamic strings used in the Aziface SDK.

| `LocaleResultIdScanUpload`         | type     | Platform | Required | Default     |
| ---------------------------------- | -------- | -------- | -------- | ----------- |
| `uploadStarted`                    | `string` | All      | ❌       | `undefined` |
| `stillUploading`                   | `string` | All      | ❌       | `undefined` |
| `uploadCompleteAwaitingResponse`   | `string` | All      | ❌       | `undefined` |
| `uploadCompleteAwaitingProcessing` | `string` | All      | ❌       | `undefined` |

###### `LocaleResultIdScanSuccess`

An object containing the ID scan success dynamic strings used in the Aziface SDK.

| `LocaleResultIdScanSuccess` | type     | Platform | Required | Default     |
| --------------------------- | -------- | -------- | -------- | ----------- |
| `frontSide`                 | `string` | All      | ❌       | `undefined` |
| `frontSideBackNext`         | `string` | All      | ❌       | `undefined` |
| `frontSideNfcNext`          | `string` | All      | ❌       | `undefined` |
| `backSide`                  | `string` | All      | ❌       | `undefined` |
| `backSideNfcNext`           | `string` | All      | ❌       | `undefined` |
| `passport`                  | `string` | All      | ❌       | `undefined` |
| `passportNfcNext`           | `string` | All      | ❌       | `undefined` |
| `userConfirmation`          | `string` | All      | ❌       | `undefined` |
| `nfc`                       | `string` | All      | ❌       | `undefined` |
| `additionalReview`          | `string` | All      | ❌       | `undefined` |

###### `LocaleResultIdScanRetry`

An object containing the ID scan retry dynamic strings used in the Aziface SDK.

| `LocaleResultIdScanRetry` | type     | Platform | Required | Default     |
| ------------------------- | -------- | -------- | -------- | ----------- |
| `faceDidNotMatch`         | `string` | All      | ❌       | `undefined` |
| `idNotFullyVisible`       | `string` | All      | ❌       | `undefined` |
| `ocrResultsNotGoodEnough` | `string` | All      | ❌       | `undefined` |
| `idTypeNotSupported`      | `string` | All      | ❌       | `undefined` |
| `barcodeNotRead`          | `string` | All      | ❌       | `undefined` |

#### `LocaleRetry`

An object containing the retry dynamic strings used in the Aziface SDK.

| `LocaleRetry`     | type                                                        | Platform | Required | Default     |
| ----------------- | ----------------------------------------------------------- | -------- | -------- | ----------- |
| `header`          | `string`                                                    | All      | ❌       | `undefined` |
| `subHeader`       | `string`                                                    | All      | ❌       | `undefined` |
| `yourImageLabel`  | `string`                                                    | All      | ❌       | `undefined` |
| `idealImageLabel` | `string`                                                    | All      | ❌       | `undefined` |
| `instruction`     | [`LocaleLabel`](#localelabel)                               | All      | ❌       | `undefined` |
| `officialIdPhoto` | [`LocaleRetryOfficialIdPhoto`](#localeretryofficialidphoto) | All      | ❌       | `undefined` |

##### `LocaleRetryOfficialIdPhoto`

An object containing the retry official ID photo dynamic strings used in the Aziface SDK.

| `LocaleRetryOfficialIdPhoto` | type     | Platform | Required | Default     |
| ---------------------------- | -------- | -------- | -------- | ----------- |
| `header`                     | `string` | All      | ❌       | `undefined` |
| `subHeader`                  | `string` | All      | ❌       | `undefined` |
| `instruction`                | `string` | All      | ❌       | `undefined` |
| `yourImageLabel`             | `string` | All      | ❌       | `undefined` |
| `idealImageLabel`            | `string` | All      | ❌       | `undefined` |

#### `LocaleLabel`

An object containing label dynamic strings used in the Aziface SDK.

| `LocaleLabel` | type     | Platform | Required | Default     |
| ------------- | -------- | -------- | -------- | ----------- |
| `primary`     | `string` | All      | ❌       | `undefined` |
| `secondary`   | `string` | All      | ❌       | `undefined` |
| `tertiary`    | `string` | All      | ❌       | `undefined` |

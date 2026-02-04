# Dynamic Strings ✏️

The Aziface SDK allows applications to dynamically customize and override the text strings displayed in the SDK’s user interface during verification sessions.

This it enables the application to replace default UI messages such as instructions, error messages, button labels, and guidance text with custom strings at runtime. It is commonly used to adapt wording, terminology, or tone to better align with product language, branding, regulatory requirements, or user context.

The dynamic strings defined through this method apply across Aziface SDK workflows, including enrollment, authentication, liveness checks, photo scan, and photo match verification. To ensure consistency, you should be called before starting a session so that all UI elements display the customized text.

If a provided string key is invalid or missing, the SDK falls back to its default text for that element. This ensures that the user experience remains functional even if some custom strings are not defined.

<hr/>

## Summary

- [Usage](#usage)
- [API](#api)
  - [`setDynamicStrings`](#setdynamicstrings)
    - [Properties](#properties)
  - [`resetDynamicStrings`](#resetdynamicstrings)
- [Types](#types)
  - [`DynamicStrings`](#dynamicstrings)
    - [`DynamicStringsAccessibility`](#dynamicstringsaccessibility)
    - [`DynamicStringsAccessibilityFeedback`](#dynamicstringsaccessibilityfeedback)
      - [`DynamicStringsAccessibilityFeedbackFace`](#dynamicstringsaccessibilityfeedbackface)
    - [`DynamicStringsAction`](#dynamicstringsaction)
    - [`DynamicStringsCamera`](#dynamicstringscamera)
      - [`DynamicStringsCameraPermission`](#dynamicstringscamerapermission)
    - [`DynamicStringsFeedback`](#dynamicstringsfeedback)
      - [`DynamicStringsFeedbackFace`](#dynamicstringsfeedbackface)
      - [`DynamicStringsFeedbackMove`](#dynamicstringsfeedbackmove)
    - [`DynamicStringsIdScan`](#dynamicstringsidscan)
      - [`DynamicStringsIdScanCapture`](#dynamicstringsidscancapture)
      - [`DynamicStringsIdScanFeedback`](#dynamicstringsidscanfeedback)
      - [`DynamicStringsIdScanNfc`](#dynamicstringsidscannfc)
      - [`DynamicStringsIdScanOcr`](#dynamicstringsidscanocr)
      - [`DynamicStringsIdScanReview`](#dynamicstringsidscanreview)
    - [`DynamicStringsInstructions`](#dynamicstringsinstructions)
    - [`DynamicStringsPresession`](#dynamicstringspresession)
    - [`DynamicStringsResult`](#dynamicstringsresult)
      - [`DynamicStringsResultFaceScan`](#dynamicstringsresultfacescan)
        - [`DynamicStringsResultFaceScanSuccess3d`](#dynamicstringsresultfacescansuccess3d)
      - [`DynamicStringsResultIdScan`](#dynamicstringsresultidscan)
        - [`DynamicStringsResultIdScanSuccess`](#dynamicstringsresultidscansuccess)
        - [`DynamicStringsResultIdScanRetry`](#dynamicstringsresultidscanretry)
        - [`DynamicStringsResultIdScanUpload`](#dynamicstringsresultidscanupload)
      - [`DynamicStringsRetryOfficialIdPhoto`](#dynamicstringsretryofficialidphoto)
    - [`DynamicStringsLabel`](#dynamicstringslabel)

<hr/>

## Usage

```tsx
import { useState } from 'react';
import { Button } from 'react-native';
// ...
import {
  setDynamicStrings,
  resetDynamicStrings,
  FaceView,
} from '@azify/aziface-mobile';

// ...
export default function App() {
  const [isEnabled, setIsEnabled] = useState(false);

  function onDynamicStrings() {
    // ...

    setIsEnabled(true);
    setDynamicStrings({
      action: {
        ok: "Let's go!",
        imReady: "I'm perfect!",
        // ...
      },
      // ...
    });
  }

  function onResetDynamicStrings() {
    // ...

    setIsEnabled(false);
    resetDynamicStrings();
  }

  // ...

  return (
    <FaceView>
      {/* ... */}

      <Button
        title={isEnabled ? 'Dynamic Strings Enabled' : 'Reset Dynamic Strings'}
        onPress={isEnabled ? onResetDynamicStrings : onDynamicStrings}
      />
    </FaceView>
  );
}
```

<hr/>

## API

| Methods               | Return Type | Platform |
| --------------------- | ----------- | -------- |
| `setDynamicStrings`   | `void`      | All      |
| `resetDynamicStrings` | `void`      | All      |

### `setDynamicStrings`

The `setDynamicStrings` allows applications to dynamically customize and override the text strings displayed in the SDK’s user interface during verification sessions.

#### Properties

| Property  | type                                | Required | Default     |
| --------- | ----------------------------------- | -------- | ----------- |
| `strings` | [`DynamicStrings`](#dynamicstrings) | ❌       | `undefined` |

### `resetDynamicStrings`

The `resetDynamicStrings` is a fallback method to return default strings.

<hr/>

## Types

| Types                                                                                 | Platform |
| ------------------------------------------------------------------------------------- | -------- |
| [`DynamicStrings`](#dynamicstrings)                                                   | All      |
| [`DynamicStringsAccessibility`](#dynamicstringsaccessibility)                         | All      |
| [`DynamicStringsAccessibilityFeedback`](#dynamicstringsaccessibilityfeedback)         | All      |
| [`DynamicStringsAccessibilityFeedbackFace`](#dynamicstringsaccessibilityfeedbackface) | All      |
| [`DynamicStringsAction`](#dynamicstringsaction)                                       | All      |
| [`DynamicStringsCamera`](#dynamicstringscamera)                                       | All      |
| [`DynamicStringsCameraPermission`](#dynamicstringscamerapermission)                   | All      |
| [`DynamicStringsFeedback`](#dynamicstringsfeedback)                                   | All      |
| [`DynamicStringsFeedbackFace`](#dynamicstringsfeedbackface)                           | All      |
| [`DynamicStringsFeedbackMove`](#dynamicstringsfeedbackmove)                           | All      |
| [`DynamicStringsIdScan`](#dynamicstringsidscan)                                       | All      |
| [`DynamicStringsIdScanCapture`](#dynamicstringsidscancapture)                         | All      |
| [`DynamicStringsIdScanFeedback`](#dynamicstringsidscanfeedback)                       | All      |
| [`DynamicStringsIdScanNfc`](#dynamicstringsidscannfc)                                 | All      |
| [`DynamicStringsIdScanOcr`](#dynamicstringsidscanocr)                                 | All      |
| [`DynamicStringsIdScanReview`](#dynamicstringsidscanreview)                           | All      |
| [`DynamicStringsInstructions`](#dynamicstringsinstructions)                           | All      |
| [`DynamicStringsPresession`](#dynamicstringspresession)                               | All      |
| [`DynamicStringsResult`](#dynamicstringsresult)                                       | All      |
| [`DynamicStringsResultFaceScan`](#dynamicstringsresultfacescan)                       | All      |
| [`DynamicStringsResultFaceScanSuccess3d`](#dynamicstringsresultfacescansuccess3d)     | All      |
| [`DynamicStringsResultIdScan`](#dynamicstringsresultidscan)                           | All      |
| [`DynamicStringsResultIdScanSuccess`](#dynamicstringsresultidscansuccess)             | All      |
| [`DynamicStringsResultIdScanRetry`](#dynamicstringsresultidscanretry)                 | All      |
| [`DynamicStringsResultUpload`](#dynamicstringsresultidscanupload)                     | All      |
| [`DynamicStringsRetry`](#dynamicstringsretry)                                         | All      |
| [`DynamicStringsRetryOfficialIdPhoto`](#dynamicstringsretryofficialidphoto)           | All      |
| [`DynamicStringsLabel`](#dynamicstringslabel)                                         | All      |

### `DynamicStrings`

An object with all the dynamic strings to will be used in the Aziface SDK screen. All properties are optional.

| `DynamicStrings`        | type                                                                          | Platform | Required | Default     |
| ----------------------- | ----------------------------------------------------------------------------- | -------- | -------- | ----------- |
| `accessibility`         | [`DynamicStringsAccessibility`](#dynamicstringsaccessibility)                 | All      | ❌       | `undefined` |
| `accessibilityFeedback` | [`DynamicStringsAccessibilityFeedback`](#dynamicstringsaccessibilityfeedback) | All      | ❌       | `undefined` |
| `action`                | [`DynamicStringsAction`](#dynamicstringsaction)                               | All      | ❌       | `undefined` |
| `camera`                | [`DynamicStringsCamera`](#dynamicstringscamera)                               | All      | ❌       | `undefined` |
| `feedback`              | [`DynamicStringsFeedback`](#dynamicstringsfeedback)                           | All      | ❌       | `undefined` |
| `idScan`                | [`DynamicStringsIdScan`](#dynamicstringsidscan)                               | All      | ❌       | `undefined` |
| `instructions`          | [`DynamicStringsInstructions`](#dynamicstringsinstructions)                   | All      | ❌       | `undefined` |
| `presession`            | [`DynamicStringsPresession`](#dynamicstringspresession)                       | All      | ❌       | `undefined` |
| `result`                | [`DynamicStringsResult`](#dynamicstringsresult)                               | All      | ❌       | `undefined` |
| `retry`                 | [`DynamicStringsRetry`](#dynamicstringsretry)                                 | All      | ❌       | `undefined` |

#### `DynamicStringsAccessibility`

An object containing the accessibility dynamic strings used in the Aziface SDK.

| `DynamicStringsAccessibility` | type     | Platform | Required | Default     |
| ----------------------------- | -------- | -------- | -------- | ----------- |
| `cancelButton`                | `string` | All      | ❌       | `undefined` |
| `torchButton`                 | `string` | All      | ❌       | `undefined` |
| `tapGuidance`                 | `string` | All      | ❌       | `undefined` |

#### `DynamicStringsAccessibilityFeedback`

An object containing the accessibility feedback dynamic strings used in the Aziface SDK.

| `DynamicStringsAccessibilityFeedback` | type                                                                                  | Platform | Required | Default     |
| ------------------------------------- | ------------------------------------------------------------------------------------- | -------- | -------- | ----------- |
| `movePhoneAway`                       | `string`                                                                              | All      | ❌       | `undefined` |
| `movePhoneCloser`                     | `string`                                                                              | All      | ❌       | `undefined` |
| `holdDeviceToEyeLevel`                | `string`                                                                              | All      | ❌       | `undefined` |
| `face`                                | [`DynamicStringsAccessibilityFeedbackFace`](#dynamicstringsaccessibilityfeedbackface) | All      | ❌       | `undefined` |

##### `DynamicStringsAccessibilityFeedbackFace`

An object containing the face feedback accessibility dynamic strings used in the Aziface SDK.

| `DynamicStringsAccessibilityFeedbackFace` | type     | Platform | Required | Default     |
| ----------------------------------------- | -------- | -------- | -------- | ----------- |
| `tooFarLeft`                              | `string` | All      | ❌       | `undefined` |
| `tooFarRight`                             | `string` | All      | ❌       | `undefined` |
| `tooLow`                                  | `string` | All      | ❌       | `undefined` |
| `tooHigh`                                 | `string` | All      | ❌       | `undefined` |
| `rotatedTooFarLeft`                       | `string` | All      | ❌       | `undefined` |
| `rotatedTooFarRight`                      | `string` | All      | ❌       | `undefined` |
| `pointingTooFarLeft`                      | `string` | All      | ❌       | `undefined` |
| `pointingTooFarRight`                     | `string` | All      | ❌       | `undefined` |
| `notOnCamera`                             | `string` | All      | ❌       | `undefined` |

#### `DynamicStringsAction`

An object containing the action dynamic strings used in the Aziface SDK.

| `DynamicStringsAction` | type     | Platform | Required | Default     |
| ---------------------- | -------- | -------- | -------- | ----------- |
| `ok`                   | `string` | All      | ❌       | `undefined` |
| `imReady`              | `string` | All      | ❌       | `undefined` |
| `tryAgain`             | `string` | All      | ❌       | `undefined` |
| `continue`             | `string` | All      | ❌       | `undefined` |
| `takePhoto`            | `string` | All      | ❌       | `undefined` |
| `retakePhoto`          | `string` | All      | ❌       | `undefined` |
| `acceptPhoto`          | `string` | All      | ❌       | `undefined` |
| `confirm`              | `string` | All      | ❌       | `undefined` |
| `scanNfc`              | `string` | All      | ❌       | `undefined` |
| `scanNfcCard`          | `string` | All      | ❌       | `undefined` |
| `skipNfc`              | `string` | All      | ❌       | `undefined` |

#### `DynamicStringsCamera`

An object containing the camera dynamic strings used in the Aziface SDK.

| `DynamicStringsCamera` | type                                                                | Platform | Required | Default     |
| ---------------------- | ------------------------------------------------------------------- | -------- | -------- | ----------- |
| `initializingCamera`   | `string`                                                            | All      | ❌       | `undefined` |
| `permission`           | [`DynamicStringsCameraPermission`](#dynamicstringscamerapermission) | All      | ❌       | `undefined` |

##### `DynamicStringsCameraPermission`

An object containing the camera permission dynamic strings used in the Aziface SDK.

| `DynamicStringsCameraPermission` | type     | Platform | Required | Default     |
| -------------------------------- | -------- | -------- | -------- | ----------- |
| `header`                         | `string` | All      | ❌       | `undefined` |
| `enroll`                         | `string` | All      | ❌       | `undefined` |
| `auth`                           | `string` | All      | ❌       | `undefined` |
| `enableCamera`                   | `string` | All      | ❌       | `undefined` |
| `launchSettings`                 | `string` | All      | ❌       | `undefined` |

#### `DynamicStringsFeedback`

An object containing the feedback dynamic strings used in the Aziface SDK.

| `DynamicStringsFeedback` | type                                                        | Platform | Required | Default     |
| ------------------------ | ----------------------------------------------------------- | -------- | -------- | ----------- |
| `centerFace`             | `string`                                                    | All      | ❌       | `undefined` |
| `holdSteady`             | `string`                                                    | All      | ❌       | `undefined` |
| `useEvenLighting`        | `string`                                                    | All      | ❌       | `undefined` |
| `face`                   | [`DynamicStringsFeedbackFace`](#dynamicstringsfeedbackface) | All      | ❌       | `undefined` |
| `move`                   | [`DynamicStringsFeedbackMove`](#dynamicstringsfeedbackmove) | All      | ❌       | `undefined` |

##### `DynamicStringsFeedbackFace`

An object containing the face feedback dynamic strings used in the Aziface SDK.

| `DynamicStringsFeedbackFace` | type     | Platform | Required | Default     |
| ---------------------------- | -------- | -------- | -------- | ----------- |
| `notFound`                   | `string` | All      | ❌       | `undefined` |
| `notLookingStraightAhead`    | `string` | All      | ❌       | `undefined` |
| `notUpright`                 | `string` | All      | ❌       | `undefined` |

##### `DynamicStringsFeedbackMove`

An object containing the move feedback dynamic strings used in the Aziface SDK.

| `DynamicStringsFeedbackMove` | type     | Platform | Required | Default     |
| ---------------------------- | -------- | -------- | -------- | ----------- |
| `phoneAway`                  | `string` | All      | ❌       | `undefined` |
| `phoneCloser`                | `string` | All      | ❌       | `undefined` |
| `phoneToEyeLevel`            | `string` | All      | ❌       | `undefined` |

#### `DynamicStringsIdScan`

An object containing the ID scan dynamic strings used in the Aziface SDK.

| `DynamicStringsIdScan` | type                                                            | Platform | Required | Default     |
| ---------------------- | --------------------------------------------------------------- | -------- | -------- | ----------- |
| `typeSelectionHeader`  | `string`                                                        | All      | ❌       | `undefined` |
| `additionalReview`     | `string`                                                        | All      | ❌       | `undefined` |
| `capture`              | [`DynamicStringsIdScanCapture`](#dynamicstringsidscancapture)   | All      | ❌       | `undefined` |
| `feedback`             | [`DynamicStringsIdScanFeedback`](#dynamicstringsidscanfeedback) | All      | ❌       | `undefined` |
| `nfc`                  | [`DynamicStringsIdScanNfc`](#dynamicstringsidscannfc)           | All      | ❌       | `undefined` |
| `ocr`                  | [`DynamicStringsIdScanOcr`](#dynamicstringsidscanocr)           | All      | ❌       | `undefined` |
| `review`               | [`DynamicStringsIdScanReview`](#dynamicstringsidscanreview)     | All      | ❌       | `undefined` |

##### `DynamicStringsIdScanCapture`

An object containing the ID scan capture dynamic strings used in the Aziface SDK.

| `DynamicStringsIdScanCapture` | type     | Platform | Required | Default     |
| ----------------------------- | -------- | -------- | -------- | ----------- |
| `tapToFocus`                  | `string` | All      | ❌       | `undefined` |
| `holdSteady`                  | `string` | All      | ❌       | `undefined` |
| `idFrontInstruction`          | `string` | All      | ❌       | `undefined` |
| `idBackInstruction`           | `string` | All      | ❌       | `undefined` |

##### `DynamicStringsIdScanFeedback`

An object containing the ID scan feedback dynamic strings used in the Aziface SDK.

| `DynamicStringsIdScanFeedback` | type     | Platform | Required | Default     |
| ------------------------------ | -------- | -------- | -------- | ----------- |
| `flipIdToBack`                 | `string` | All      | ❌       | `undefined` |
| `flipIdToFront`                | `string` | All      | ❌       | `undefined` |

##### `DynamicStringsIdScanNfc`

An object containing the ID scan NFC dynamic strings used in the Aziface SDK.

| `DynamicStringsIdScanNfc`     | type     | Platform | Required | Default     |
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

##### `DynamicStringsIdScanOcr`

An object containing the ID scan OCR dynamic strings used in the Aziface SDK.

| `DynamicStringsIdScanOcr` | type     | Platform | Required | Default     |
| ------------------------- | -------- | -------- | -------- | ----------- |
| `confirmationMainHeader`  | `string` | All      | ❌       | `undefined` |
| `confirmationScroll`      | `string` | All      | ❌       | `undefined` |

##### `DynamicStringsIdScanReview`

An object containing the ID scan review dynamic strings used in the Aziface SDK.

| `DynamicStringsIdScanReview` | type     | Platform | Required | Default     |
| ---------------------------- | -------- | -------- | -------- | ----------- |
| `idFrontInstruction`         | `string` | All      | ❌       | `undefined` |
| `idBackInstruction`          | `string` | All      | ❌       | `undefined` |

#### `DynamicStringsInstructions`

An object containing the instructions dynamic strings used in the Aziface SDK.

| `DynamicStringsInstructions` | type                                                            | Platform | Required | Default     |
| ---------------------------- | --------------------------------------------------------------- | -------- | -------- | ----------- |
| `header`                     | [`Omit<DynamicStringsLabel, 'tertiary'>`](#dynamicstringslabel) | All      | ❌       | `undefined` |
| `message`                    | [`Omit<DynamicStringsLabel, 'tertiary'>`](#dynamicstringslabel) | All      | ❌       | `undefined` |

#### `DynamicStringsPresession`

An object containing the presession dynamic strings used in the Aziface SDK.

| `DynamicStringsPresession`   | type     | Platform | Required | Default     |
| ---------------------------- | -------- | -------- | -------- | ----------- |
| `orientation`                | `string` | All      | ❌       | `undefined` |
| `frameYourFace`              | `string` | All      | ❌       | `undefined` |
| `positionFaceStraightInOval` | `string` | All      | ❌       | `undefined` |
| `removeDarkGlasses`          | `string` | All      | ❌       | `undefined` |
| `neutralExpression`          | `string` | All      | ❌       | `undefined` |
| `conditionsTooBright`        | `string` | All      | ❌       | `undefined` |
| `brightenYourEnvironment`    | `string` | All      | ❌       | `undefined` |

#### `DynamicStringsResult`

| `DynamicStringsResult` | type                                                            | Platform | Required | Default     |
| ---------------------- | --------------------------------------------------------------- | -------- | -------- | ----------- |
| `nfcUpload`            | `string`                                                        | All      | ❌       | `undefined` |
| `sessionAbort`         | `string`                                                        | All      | ❌       | `undefined` |
| `faceScan`             | [`DynamicStringsResultFaceScan`](#dynamicstringsresultfacescan) | All      | ❌       | `undefined` |
| `idScan`               | [`DynamicStringsResultIdScan`](#dynamicstringsresultidscan)     | All      | ❌       | `undefined` |

##### `DynamicStringsResultFaceScan`

An object containing the face scan dynamic strings used in the Aziface SDK.

| `DynamicStringsResultFaceScan` | type                                                                              | Platform | Required | Default     |
| ------------------------------ | --------------------------------------------------------------------------------- | -------- | -------- | ----------- |
| `upload`                       | `string`                                                                          | All      | ❌       | `undefined` |
| `uploadStillUploading`         | `string`                                                                          | All      | ❌       | `undefined` |
| `success3d`                    | [`DynamicStringsResultFaceScanSuccess3d`](#dynamicstringsresultfacescansuccess3d) | All      | ❌       | `undefined` |

###### `DynamicStringsResultFaceScanSuccess3d`

An object containing the face scan success 3D dynamic strings used in the Aziface SDK.

| `DynamicStringsResultFaceScanSuccess3d` | type     | Platform | Required | Default     |
| --------------------------------------- | -------- | -------- | -------- | ----------- |
| `enrollment`                            | `string` | All      | ❌       | `undefined` |
| `reverification`                        | `string` | All      | ❌       | `undefined` |
| `livenessPriorToIdScan`                 | `string` | All      | ❌       | `undefined` |
| `livenessAndOfficialIdPhoto`            | `string` | All      | ❌       | `undefined` |

##### `DynamicStringsResultIdScan`

An object containing the ID scan dynamic strings used in the Aziface SDK.

| `DynamicStringsResultIdScan` | type                                                                      | Platform | Required | Default     |
| ---------------------------- | ------------------------------------------------------------------------- | -------- | -------- | ----------- |
| `unsuccess`                  | `string`                                                                  | All      | ❌       | `undefined` |
| `skipOrErrorNfc`             | `string`                                                                  | All      | ❌       | `undefined` |
| `additionalReviewTag`        | `string`                                                                  | All      | ❌       | `undefined` |
| `uploadFrontSide`            | [`DynamicStringsResultIdScanUpload`](#dynamicstringsresultidscanupload)   | All      | ❌       | `undefined` |
| `uploadBackSide`             | [`DynamicStringsResultIdScanUpload`](#dynamicstringsresultidscanupload)   | All      | ❌       | `undefined` |
| `uploadUserConfirmedInfo`    | [`DynamicStringsResultIdScanUpload`](#dynamicstringsresultidscanupload)   | All      | ❌       | `undefined` |
| `uploadNfc`                  | [`DynamicStringsResultIdScanUpload`](#dynamicstringsresultidscanupload)   | All      | ❌       | `undefined` |
| `uploadSkippedNfc`           | [`DynamicStringsResultIdScanUpload`](#dynamicstringsresultidscanupload)   | All      | ❌       | `undefined` |
| `success`                    | [`DynamicStringsResultIdScanSuccess`](#dynamicstringsresultidscansuccess) | All      | ❌       | `undefined` |
| `retry`                      | [`DynamicStringsResultIdScanRetry`](#dynamicstringsresultidscanretry)     | All      | ❌       | `undefined` |

###### `DynamicStringsResultIdScanUpload`

An object containing the upload dynamic strings used in the Aziface SDK.

| `DynamicStringsResultIdScanUpload` | type     | Platform | Required | Default     |
| ---------------------------------- | -------- | -------- | -------- | ----------- |
| `uploadStarted`                    | `string` | All      | ❌       | `undefined` |
| `stillUploading`                   | `string` | All      | ❌       | `undefined` |
| `uploadCompleteAwaitingResponse`   | `string` | All      | ❌       | `undefined` |
| `uploadCompleteAwaitingProcessing` | `string` | All      | ❌       | `undefined` |

###### `DynamicStringsResultIdScanSuccess`

An object containing the ID scan success dynamic strings used in the Aziface SDK.

| `DynamicStringsResultIdScanSuccess` | type     | Platform | Required | Default     |
| ----------------------------------- | -------- | -------- | -------- | ----------- |
| `frontSide`                         | `string` | All      | ❌       | `undefined` |
| `frontSideBackNext`                 | `string` | All      | ❌       | `undefined` |
| `frontSideNfcNext`                  | `string` | All      | ❌       | `undefined` |
| `backSide`                          | `string` | All      | ❌       | `undefined` |
| `backSideNfcNext`                   | `string` | All      | ❌       | `undefined` |
| `passport`                          | `string` | All      | ❌       | `undefined` |
| `passportNfcNext`                   | `string` | All      | ❌       | `undefined` |
| `userConfirmation`                  | `string` | All      | ❌       | `undefined` |
| `nfc`                               | `string` | All      | ❌       | `undefined` |
| `additionalReviewTag`               | `string` | All      | ❌       | `undefined` |

###### `DynamicStringsResultIdScanRetry`

An object containing the ID scan retry dynamic strings used in the Aziface SDK.

| `DynamicStringsResultIdScanRetry` | type     | Platform | Required | Default     |
| --------------------------------- | -------- | -------- | -------- | ----------- |
| `faceDidNotMatch`                 | `string` | All      | ❌       | `undefined` |
| `idNotFullyVisible`               | `string` | All      | ❌       | `undefined` |
| `ocrResultsNotGoodEnough`         | `string` | All      | ❌       | `undefined` |
| `idTypeNotSupported`              | `string` | All      | ❌       | `undefined` |
| `barcodeNotReadable`              | `string` | All      | ❌       | `undefined` |

#### `DynamicStringsRetry`

An object containing the retry dynamic strings used in the Aziface SDK.

| `DynamicStringsRetry` | type                                                                        | Platform | Required | Default     |
| --------------------- | --------------------------------------------------------------------------- | -------- | -------- | ----------- |
| `header`              | `string`                                                                    | All      | ❌       | `undefined` |
| `subHeader`           | `string`                                                                    | All      | ❌       | `undefined` |
| `yourImageLabel`      | `string`                                                                    | All      | ❌       | `undefined` |
| `idealImageLabel`     | `string`                                                                    | All      | ❌       | `undefined` |
| `instruction`         | [`DynamicStringsLabel`](#dynamicstringslabel)                               | All      | ❌       | `undefined` |
| `officialIdPhoto`     | [`DynamicStringsRetryOfficialIdPhoto`](#dynamicstringsretryofficialidphoto) | All      | ❌       | `undefined` |

##### `DynamicStringsRetryOfficialIdPhoto`

An object containing the retry official ID photo dynamic strings used in the Aziface SDK.

| `DynamicStringsRetryOfficialIdPhoto` | type     | Platform | Required | Default     |
| ------------------------------------ | -------- | -------- | -------- | ----------- |
| `header`                             | `string` | All      | ❌       | `undefined` |
| `subHeader`                          | `string` | All      | ❌       | `undefined` |
| `instruction`                        | `string` | All      | ❌       | `undefined` |
| `yourImageLabel`                     | `string` | All      | ❌       | `undefined` |
| `idealImageLabel`                    | `string` | All      | ❌       | `undefined` |

#### `DynamicStringsLabel`

An object containing label dynamic strings used in the Aziface SDK.

| `DynamicStringsLabel` | type     | Platform | Required | Default     |
| --------------------- | -------- | -------- | -------- | ----------- |
| `primary`             | `string` | All      | ❌       | `undefined` |
| `secondary`           | `string` | All      | ❌       | `undefined` |
| `tertiary`            | `string` | All      | ❌       | `undefined` |

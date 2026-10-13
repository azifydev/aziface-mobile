import type { Locale } from '..';

/**
 * @description The English locale for the Aziface SDK. This locale is used as
 * the default language.
 */
export const en: Required<Locale> = {
  accessibility: {
    cancelButton: 'Cancel',
    torchButton: 'Toggle Light',
    tapGuidance:
      'Double tap anywhere on the screen for face alignment guidance.',
  },
  accessibilityFeedback: {
    movePhoneAway: 'Face Too Close',
    movePhoneCloser: 'Face Too Far Away',
    holdDeviceToEyeLevel: 'Hold Device At Eye Level',
    face: {
      tooFarLeft: 'Face Too Far Left',
      tooFarRight: 'Face Too Far Right',
      tooLow: 'Face Too Low',
      tooHigh: 'Face Too High',
      rotatedTooFarLeft: 'Face Rotated Too Far Left',
      rotatedTooFarRight: 'Face Rotated Too Far Right',
      pointingTooFarLeft: 'Face Pointing Too Far Left',
      pointingTooFarRight: 'Face Pointing Too Far Right',
      notOnCamera: 'Face Not On Camera or Too Far Away',
    },
  },
  action: {
    ok: 'OK',
    imReady: "I'M READY",
    tryAgain: 'TRY AGAIN',
    continue: 'CONTINUE',
    takePhoto: 'TAKE PHOTO',
    retakePhoto: 'RETRY',
    acceptPhoto: 'ACCEPT',
    confirm: 'CONFIRM INFO',
    scanNfc: 'SCAN ePASSPORT',
    scanNfcCard: 'SCAN ID CARD',
    skipNfc: 'SKIP THIS STEP',
  },
  camera: {
    initializingCamera: 'Securing Camera Feed',
    permission: {
      header: 'Enable Camera',
      enroll: 'Please tap the button below to enable your selfie camera.',
      auth: 'Your camera is disabled. Tap below to edit your settings.',
      enableCamera: 'ENABLE CAMERA',
      launchSettings: 'LAUNCH SETTINGS',
    },
  },
  feedback: {
    centerFace: 'Center Your Face',
    holdSteady: 'Hold Steady',
    useEvenLighting: 'Light Face More Evenly',
    face: {
      notFound: 'Frame Your Face',
      notLookingStraightAhead: 'Look Straight Ahead',
      notUpright: 'Hold Your Head Straight',
    },
    move: {
      phoneAway: 'Move Away',
      phoneCloser: 'Move Closer',
      phoneToEyeLevel: 'Move Camera To Eye Level',
    },
  },
  idScan: {
    typeSelectionHeader: 'Prepare to Scan\nYour ID Document',
    additionalReview: 'Additional Review\nRequired',
    capture: {
      tapToFocus: 'Tap Screen To Focus',
      holdSteady: 'Please Hold Steady',
      idFrontInstruction: 'Scan Front of ID',
      idBackInstruction: 'Scan Back of ID',
    },
    review: {
      idFrontInstruction: 'Confirm Photo is Sharp & Legible',
      idBackInstruction: 'Confirm Photo is Legible',
    },
    ocr: {
      confirmationMainHeader: 'Review & Confirm',
      confirmationScroll: 'Scroll Down',
    },
    nfc: {
      statusDisabled: "Turn ON\nYour Device's NFC\nTo Continue",
      statusReady: 'Get Ready to Scan\nYour ePassport Chip',
      cardStatusReady: 'Get Ready to Scan\nYour ID Card Chip',
      statusStarting: 'Hold Phone to\nBack of ePassport\nto Scan NFC Chip',
      cardStatusStarting: 'Hold Phone to ID Card\nto Scan NFC Chip',
      statusScanning: 'Hold Steady,\nScanning NFC Chip',
      statusWeakConnection: "Let's Try\nAnother Scan",
      statusFinishedWithSuccess: 'NFC Chip\nScanned',
      statusFinishedWithError: 'ePassport Chip\nUnreadable',
      cardStatusFinishedWithError: 'ID Card Chip\nUnreadable',
      statusSkipped: 'NFC Scan\nNot Performed',
    },
    feedback: {
      flipIdToBack: 'Show Back of ID',
      flipIdToFront: 'Show Front of ID',
    },
  },
  instructions: {
    header: {
      primary: 'Get Ready For',
      secondary: 'Your Video Selfie',
    },
    message: {
      primary: 'Frame Your Face in the Oval,',
      secondary: "Press I'm Ready & Move Closer",
    },
  },
  presession: {
    orientation: 'Please Rotate\nYour Device',
    frameYourFace: 'Frame Your Face In The Oval',
    positionFaceStraightInOval: 'Look Straight Ahead',
    removeDarkGlasses: 'Remove Dark Glasses',
    neutralExpression: 'Neutral Expression, No Smiling',
    conditionsTooBright: 'Conditions Too Bright',
    brightenYourEnvironment: 'Brighten Your Environment',
  },
  result: {
    nfcUpload: 'Uploading\nEncrypted\nNFC Details',
    sessionAbort: 'Critical Error\nSession Ended',
    faceScan: {
      upload: 'Uploading\nEncrypted\n3D FaceScan',
      uploadStillUploading: 'Still Uploading...\nSlow Connection',
      success3d: {
        enrollment: 'Face Scanned\n3D Liveness Proven',
        reverification: '3D Liveness Proven\nFace Verified',
        livenessPriorToIdScan: 'Face Scanned\n3D Liveness Proven',
        livenessAndOfficialIdPhoto: '3D Liveness Proven',
      },
    },
    idScan: {
      unsuccess: "ID Photo\nDid Not Match\nUser's Face",
      skipOrErrorNfc: 'ID Data\nUploaded',
      additionalReviewTag: 'Manual Review Required\nPlease See IDV Dashboard',
      uploadFrontSide: {
        uploadStarted: 'Uploading\nEncrypted\nID Scan',
        stillUploading: 'Still Uploading...\nSlow Connection',
        uploadCompleteAwaitingResponse: 'ID Image\nUploaded',
        uploadCompleteAwaitingProcessing: 'Processing\nID Scan',
      },
      uploadBackSide: {
        uploadStarted: 'Uploading\nEncrypted\nBack of ID',
        stillUploading: 'Still Uploading...\nSlow Connection',
        uploadCompleteAwaitingResponse: 'ID Image\nUploaded',
        uploadCompleteAwaitingProcessing: 'Processing\nBack of ID',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: 'Saving\nYour Confirmed Info',
        stillUploading: 'Still Uploading...\nSlow Connection',
        uploadCompleteAwaitingResponse: 'Info Saved',
        uploadCompleteAwaitingProcessing: 'Processing',
      },
      uploadNfc: {
        uploadStarted: 'Uploading\nEncrypted\nNFC Details',
        stillUploading: 'Still Uploading...\nSlow Connection',
        uploadCompleteAwaitingResponse: 'NFC Chip Info\nUploaded',
        uploadCompleteAwaitingProcessing: 'Processing\nNFC Details',
      },
      uploadSkippedNfc: {
        uploadStarted: 'Uploading\nEncrypted\nID Data',
        stillUploading: 'Still Uploading...\nSlow Connection',
        uploadCompleteAwaitingResponse: 'Upload Complete',
        uploadCompleteAwaitingProcessing: 'Processing\nID Data',
      },
      success: {
        frontSide: 'Photo ID\nScanned',
        frontSideBackNext: 'Front of ID\nScanned',
        frontSideNfcNext: 'Front of ID\nScanned',
        backSide: 'Photo ID\nScanned',
        backSideNfcNext: 'Back of ID\nScanned',
        passport: 'Passport Scanned',
        passportNfcNext: 'Passport Scanned',
        userConfirmation: 'ID Scan\nCompleted',
        nfc: 'NFC Chip Info\nUploaded',
        additionalReview: 'ID Scan\nCompleted',
      },
      retry: {
        faceDidNotMatch: "Face Didn't Match\nHighly Enough",
        idNotFullyVisible: 'ID Document\nNot Fully Visible',
        ocrResultsNotGoodEnough: 'ID Text Not Legible',
        idTypeNotSupported: 'ID Not Supported\nPlease Use a Different ID',
        barcodeNotRead: 'Barcode Failed To Scan\nPlease Try Again',
      },
    },
  },
  retry: {
    header: "Let's Try That Again",
    subHeader: 'We Need a Clearer Video Selfie',
    yourImageLabel: 'Your Selfie',
    idealImageLabel: 'Ideal Pose',
    instruction: {
      primary: 'Neutral Expression, No Smiling',
      secondary: 'Please Use Ideal Pose & Lighting',
      tertiary: 'Too Blurry, Clean Camera',
    },
    officialIdPhoto: {
      header: "Let's Try That Again",
      subHeader: 'We Need a Higher Quality Image',
      instruction:
        'Please try Indoor Lighting,\nNo Glare in Glasses, No Smiling & No Headwear',
      yourImageLabel: 'Your Image',
      idealImageLabel: 'Compliant Face',
    },
  },
};

import type { Locale } from '..';

/**
 * @description The Afrikaans locale for the Aziface SDK.
 */
export const af: Required<Locale> = {
  accessibility: {
    cancelButton: 'Kanselleer',
    torchButton: 'Skakel lig',
    tapGuidance: 'Dubbeltik enige plek op die skerm vir gesigbelyningsleiding.',
  },
  accessibilityFeedback: {
    movePhoneAway: 'Gesig te naby',
    movePhoneCloser: 'Gesig te ver weg',
    holdDeviceToEyeLevel: 'Hou toestel op ooghoogte',
    face: {
      tooFarLeft: 'Gesig te ver links',
      tooFarRight: 'Gesig te ver regs',
      tooLow: 'Gesig te laag',
      tooHigh: 'Gesig te hoog',
      rotatedTooFarLeft: 'Gesig te ver links gedraai',
      rotatedTooFarRight: 'Gesig te ver regs gedraai',
      pointingTooFarLeft: 'Gesig wat te ver links wys',
      pointingTooFarRight: 'Gesig wys te ver regs',
      notOnCamera: 'Gesig nie op kamera of te ver weg nie',
    },
  },
  action: {
    ok: 'OK',
    imReady: 'EK IS GEREED',
    tryAgain: 'PROBEER WEER',
    continue: 'AANHOU',
    takePhoto: "NEEM 'N FOTO",
    retakePhoto: 'WEER',
    acceptPhoto: 'AANVAAR',
    confirm: 'BEVESTIG INLIGTING',
    scanNfc: 'SKANDEER ePASPORT',
    scanNfcCard: 'SKANDEER ID-KAART',
    skipNfc: 'SLAAN HIERDIE STAP OOR',
  },
  camera: {
    initializingCamera: 'Beveilig kameratoevoer',
    permission: {
      header: 'Aktiveer kamera',
      enroll:
        'Tik asseblief die knoppie hieronder om jou selfie kamera te aktiveer.',
      auth: 'Jou kamera is gedeaktiveer. Tik hieronder om jou instellings te wysig.',
      enableCamera: 'AKTIVEER KAMERA',
      launchSettings: 'LAANSEER INSTELLINGS',
    },
  },
  feedback: {
    centerFace: 'Sentreer jou gesig',
    holdSteady: 'Hou vas',
    useEvenLighting: 'Lig gesig meer eweredig',
    face: {
      notFound: 'Raam jou gesig',
      notLookingStraightAhead: 'Kyk Reguit Vooruit',
      notUpright: 'Hou jou kop reguit',
    },
    move: {
      phoneAway: 'Beweeg Weg',
      phoneCloser: 'Beweeg nader',
      phoneToEyeLevel: 'Beweeg kamera na ooghoogte',
    },
  },
  idScan: {
    typeSelectionHeader: 'Berei voor om\njou identifikasie te skandeer',
    additionalReview: 'Bykomende verifikasie\nword vereis',
    capture: {
      tapToFocus: 'Raak die skerm aan om te fokus',
      holdSteady: 'Hou asseblief in die posisie',
      idFrontInstruction: 'Skandeer Voorkant van ID',
      idBackInstruction: 'Skandeer Agterkant van ID',
    },
    review: {
      idFrontInstruction: 'Bevestig Foto Skerp & Leesbaar',
      idBackInstruction: 'Bevestig Foto is Leesbaar',
    },
    ocr: {
      confirmationMainHeader: 'Hersien en bevestig',
      confirmationScroll: 'Beweeg na af',
    },
    nfc: {
      statusDisabled: 'Skakel \nJou Toestel se NFC\nAAN om Voort te Gaan',
      statusReady: 'Maak gereed om\njou ePassport-skyfie te skandeer',
      cardStatusReady: 'Berei voor om\njou ID-skyfie te skandeer',
      statusStarting:
        'Hou Selfoon aan \nAgterkant van e-Paspoort\nom NFC-skyfie te Skandeer',
      cardStatusStarting:
        'Bring die foon naby jou ID\nom die NFC-skyfie te skandeer',
      statusScanning: 'Hou dit stil,\nskandeer die NFC-skyfie',
      statusWeakConnection: "Kom ons probeer\nnog 'n skandering",
      statusFinishedWithSuccess: 'NFC Skyfie\nGeskandeer',
      statusFinishedWithError: 'Kan nie\nePassport-skyfie lees nie',
      cardStatusFinishedWithError: 'Kan nie\ndie ID-skyfie lees nie',
      statusSkipped: 'NFC-skandering\nNie Uitgevoer Nie',
    },
    feedback: {
      flipIdToBack: 'Agterkant VAN JOU ID',
      flipIdToFront: 'Die voorkant VAN JOU ID',
    },
  },
  instructions: {
    header: {
      primary: 'Maak Gereed Vir',
      secondary: 'Jou Video Selfie',
    },
    message: {
      primary: 'Raam Jou Gesig In Die Ovaal',
      secondary: 'Druk ek Is Gereed En Beweeg Nader',
    },
  },
  presession: {
    orientation: 'Draai Asseblief\nJou Toestel',
    frameYourFace: 'Raam jou gesig in die ovaal',
    positionFaceStraightInOval: 'Kyk Reguit Vooruit',
    removeDarkGlasses: 'Verwyder donkerbrille',
    neutralExpression: 'Neutrale uitdrukking, geen glimlag nie',
    conditionsTooBright: 'Toestande te helder',
    brightenYourEnvironment: 'Verhelder jou omgewing',
  },
  result: {
    nfcUpload: 'Laai tans\nNFC\ngeënkripteerde inligting op',
    sessionAbort: 'Kritiese Fout\nSessie Beëindig',
    faceScan: {
      upload: 'Oplaai\nDIE 3D gesig scan\ngeënkripteer',
      uploadStillUploading: 'Laai Steeds...\nStadige Verbinding',
      success3d: {
        enrollment: 'Gesig Geskandeer\n 3D Lewende Bewys',
        reverification: '3D Lewende Bewys\nGesig Geverifieer',
        livenessPriorToIdScan: 'Gesig Geskandeer\n 3D Lewende Bewys',
        livenessAndOfficialIdPhoto: '3D Lewende Bewys',
      },
    },
    idScan: {
      unsuccess: 'Die ID-foto\nof pas by\ndie gebruiker se gesig',
      skipOrErrorNfc: 'ID-data\nOpgelaai',
      additionalReviewTag:
        'Handleiding Hersiening Vereis\nSien Asseblief IDV Paneelbord',
      uploadFrontSide: {
        uploadStarted: 'Oplaai\nDIE ID scan\ngeënkripteer',
        stillUploading: 'Laai Steeds...\nStadige Verbinding',
        uploadCompleteAwaitingResponse: 'ID Beeld\nOpgelaai',
        uploadCompleteAwaitingProcessing: 'Verwerk\nID-skandering',
      },
      uploadBackSide: {
        uploadStarted: 'Laai Op\nVersleutelde\nAgterkant van ID',
        stillUploading: 'Laai Steeds...\nStadige Verbinding',
        uploadCompleteAwaitingResponse: 'ID Beeld\nOpgelaai',
        uploadCompleteAwaitingProcessing: 'Verwerk\nAgterkant van ID',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: 'Stoor\nJou Bevestigde Inligting',
        stillUploading: 'Laai Steeds...\nStadige Verbinding',
        uploadCompleteAwaitingResponse: 'Inligting Gestoor',
        uploadCompleteAwaitingProcessing: 'Verwerk',
      },
      uploadNfc: {
        uploadStarted: 'Laai Op Versleutelde\nNFC Besonderhede',
        stillUploading: 'Laai Steeds...\nStadige Verbinding',
        uploadCompleteAwaitingResponse: 'NFC Skyfie-inligting\nOpgelaai',
        uploadCompleteAwaitingProcessing: 'Verwerk\nNFC Besonderhede',
      },
      uploadSkippedNfc: {
        uploadStarted: 'Oplaai\nGeïnkripteer\nID Data',
        stillUploading: 'Laai Steeds...\nStadige Verbinding',
        uploadCompleteAwaitingResponse: 'Oplaai Voltooi',
        uploadCompleteAwaitingProcessing: 'Verwerk\nID-data',
      },
      success: {
        frontSide: 'Foto ID\nGeskandeer',
        frontSideBackNext: 'Voorkant van ID\ngeskandeer',
        frontSideNfcNext: 'Voorkant van ID\ngeskandeer',
        backSide: 'Foto ID\nGeskandeer',
        backSideNfcNext: 'Agterkant van ID\ngeskandeer',
        passport: 'Geskandeerde paspoort',
        passportNfcNext: 'Geskandeerde paspoort',
        userConfirmation: 'ID Skandering\nVoltooi',
        nfc: 'NFC Skyfie-inligting\nOpgelaai',
        additionalReview: 'ID Skandering\nVoltooi',
      },
      retry: {
        faceDidNotMatch: 'Die gesig pas nie genoeg by\nnie',
        idNotFullyVisible: 'Die id\nis nie ten volle sigbaar nie',
        ocrResultsNotGoodEnough:
          'Die teks van die identifikasie is nie leesbaar nie',
        idTypeNotSupported:
          'ID Nie Ondersteun Nie\nGebruik Asseblief ’n Ander ID',
        barcodeNotRead:
          'Kon nie strepieskode skandeer nie\nProbeer asseblief weer',
      },
    },
  },
  retry: {
    header: 'Kom Ons Probeer Dit Weer',
    subHeader: "Ons Benodig'n Duideliker Video Selfie",
    yourImageLabel: 'Jou Selfie',
    idealImageLabel: 'Ideale Posisie',
    instruction: {
      primary: 'Neutrale Uitdrukking, Geen Glimlag Nie',
      secondary: 'Gebruik ideale houding en beligting',
      tertiary: 'Te Vaag, Skoon Kamera',
    },
    officialIdPhoto: {
      header: 'Kom Ons Probeer Dit Weer',
      subHeader: 'n Hoër Kwaliteit Beeld asb',
      instruction:
        'Probeer Binnebeligting.\nGeen Glans op Bril, Glimlag of Kopbedekking',
      yourImageLabel: 'Jou Beeld',
      idealImageLabel: 'Genoegsame Gesig',
    },
  },
};

import type { Locale } from '..';

/**
 * @description The Norwegian Bokmål locale for the Aziface SDK.
 */
export const nb: Required<Locale> = {
  accessibility: {
    cancelButton: 'Kanseller',
    torchButton: 'Vend lysinnstilling',
    tapGuidance:
      'Dobbeltrykk hvor som helst på skjermen for veiledning om justering av ansiktsplasseringen.',
  },
  accessibilityFeedback: {
    movePhoneAway: 'Ansikt for nærme',
    movePhoneCloser: 'Ansikt for langt vekke',
    holdDeviceToEyeLevel: 'Hold enheten i øyehøyde',
    face: {
      tooFarLeft: 'Ansikt for langt til venstre',
      tooFarRight: 'Ansikt for langt til høyre',
      tooLow: 'Ansikt for lavt',
      tooHigh: 'Ansikt for høyt',
      rotatedTooFarLeft: 'Ansikt rotert for langt til venstre',
      rotatedTooFarRight: 'Ansikt rotert for langt til høyre',
      pointingTooFarLeft: 'Ansikt peker for langt til venstre',
      pointingTooFarRight: 'Ansikt peker for langt til høyre',
      notOnCamera: 'Ansiktet vises ikke for kameraet eller er for langt unna',
    },
  },
  action: {
    ok: 'OK',
    imReady: 'JEG ER KLAR',
    tryAgain: 'PRØV IGJEN',
    continue: 'FORTSETT',
    takePhoto: 'TA BILDE',
    retakePhoto: 'GJENTA',
    acceptPhoto: 'AKSEPTER',
    confirm: 'BEKREFT INFO',
    scanNfc: 'SKANN ePASSPORT',
    scanNfcCard: 'SKANN ID-KORT',
    skipNfc: 'HOPP OVER DETTE STEGET',
  },
  camera: {
    initializingCamera: 'Sikrer kamera-feeden',
    permission: {
      header: 'Aktiver kameraet',
      enroll: 'Trykk på knappen nedenfor for å aktivere selfie-kameraet ditt.',
      auth: 'Kameraet ditt er deaktivert. Trykk nedenfor for å redigere innstillingene dine.',
      enableCamera: 'AKTIVER KAMERA',
      launchSettings: 'ÅPNE INNSTILLINGER',
    },
  },
  feedback: {
    centerFace: 'Sentrer ansiktet ditt',
    holdSteady: 'Hold i ro',
    useEvenLighting: 'Anvend jevnere lys på ansiktet',
    face: {
      notFound: 'Ram inn ansiktet ditt',
      notLookingStraightAhead: 'Se rett fremover',
      notUpright: 'Hold hodet rett',
    },
    move: {
      phoneAway: 'Flytt deg lengre fra',
      phoneCloser: 'Flytt deg nærmere',
      phoneToEyeLevel: 'Flytt kameraet til øyenivå',
    },
  },
  idScan: {
    typeSelectionHeader: 'Gjør klar til å skanne\nID-dokumentet ditt',
    additionalReview: 'Ekstra gjennomgang\npåkrevd',
    capture: {
      tapToFocus: 'Trykk på skjermen for å fokusere',
      holdSteady: 'Hold rolig',
      idFrontInstruction: 'Skann ID-forside',
      idBackInstruction: 'Skann ID-bakside',
    },
    review: {
      idFrontInstruction: 'Bekreft foto skarpt og leselig',
      idBackInstruction: 'Bekreft at fotoet er lesbart',
    },
    ocr: {
      confirmationMainHeader: 'Gjennomgå og bekreft',
      confirmationScroll: 'Skroll ned',
    },
    nfc: {
      statusDisabled:
        'Slå PÅ\nenhetens nærfeltkommunikasjon (NFC)\nfor å fortsette',
      statusReady: 'Gjør deg klar til å skanne\n ePassport-chipen din',
      cardStatusReady: 'Gjør deg klar til å skanne\nID-kort-chipen din',
      statusStarting:
        'Hold telefonen over\nbaksiden av e-passet\nfor å skanne NFC-chipen',
      cardStatusStarting:
        'Hold telefonen mot ID-kortet\nfor å skanne NFC-chipen',
      statusScanning: 'Hold i ro,\nSkanner NFC-chipen',
      statusWeakConnection: 'La oss prøve\nen ny skanning',
      statusFinishedWithSuccess: 'NFC-chip\nscannet',
      statusFinishedWithError: 'ePassport-chip\nuleselig',
      cardStatusFinishedWithError: 'ID-kort-chip\nuleselig',
      statusSkipped: 'NFC-skanning\nikke utført',
    },
    feedback: {
      flipIdToBack: 'Vis ID-ens bakside',
      flipIdToFront: 'Vis ID-ens forside',
    },
  },
  instructions: {
    header: {
      primary: 'Gjør deg klar til',
      secondary: 'Video-selfien din',
    },
    message: {
      primary: 'Ram ansiktet ditt inn i ovalen',
      secondary: 'Trykk på «Jeg er klar» og kom nærmere',
    },
  },
  presession: {
    orientation: 'Roter\nenheten din',
    frameYourFace: 'Ram ansiktet ditt inn i ovalen',
    positionFaceStraightInOval: 'Se rett fremover',
    removeDarkGlasses: 'Ta av mørke briller',
    neutralExpression: 'Naturlig uttrykk, ingen smiling',
    conditionsTooBright: 'For lyse forhold',
    brightenYourEnvironment: 'Gjør miljøet lysere',
  },
  result: {
    nfcUpload: 'Laster opp\nkrypterte\nNFC-opplysninger',
    sessionAbort: 'Kritisk feil\nØkt avsluttet',
    faceScan: {
      upload: 'Laster opp\nkryptert\n3D-ansiktsskanning',
      uploadStillUploading: 'Laster fortsatt opp...\nTreg tilkobling',
      success3d: {
        enrollment: 'Ansiktsskannet\n3D liveness-bekreftet',
        reverification: '3D liveness-bekreftet\nAnsiktsverifisert',
        livenessPriorToIdScan: 'Ansiktsskannet\n3D liveness-bekreftet',
        livenessAndOfficialIdPhoto: '3D liveness-bekreftet',
      },
    },
    idScan: {
      unsuccess: 'ID-foto\nmatchet ikke\nbrukerens ansikt',
      skipOrErrorNfc: 'ID-data\nOpplastet',
      additionalReviewTag: 'Manuell gjennomgang påkrevd\nSe IDV-dashbord',
      uploadFrontSide: {
        uploadStarted: 'Laster opp\nkryptert\nID-skanning',
        stillUploading: 'Laster fortsatt opp...\nTreg tilkobling',
        uploadCompleteAwaitingResponse: 'ID-bilde\nlastet opp',
        uploadCompleteAwaitingProcessing: 'Behandler\nID-skanning',
      },
      uploadBackSide: {
        uploadStarted: 'Laster opp\nKryptert\nBaksiden av ID',
        stillUploading: 'Laster fortsatt opp...\nTreg tilkobling',
        uploadCompleteAwaitingResponse: 'ID-bilde\nlastet opp',
        uploadCompleteAwaitingProcessing: 'Behandler\nBaksiden av ID',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: 'Lagrer\nDin bekreftede info',
        stillUploading: 'Laster fortsatt opp...\nTreg tilkobling',
        uploadCompleteAwaitingResponse: 'Informasjon lagret',
        uploadCompleteAwaitingProcessing: 'Behandler',
      },
      uploadNfc: {
        uploadStarted: 'Laster opp kryptert\nNFC-detaljer',
        stillUploading: 'Laster fortsatt opp...\nTreg tilkobling',
        uploadCompleteAwaitingResponse: 'NFC-Chip-info\nlastet opp',
        uploadCompleteAwaitingProcessing: 'Behandler\nNFC-detaljer',
      },
      uploadSkippedNfc: {
        uploadStarted: 'Laster opp\nkrypterte\nID-data',
        stillUploading: 'Laster fortsatt opp...\nTreg tilkobling',
        uploadCompleteAwaitingResponse: 'Opplasting fullført',
        uploadCompleteAwaitingProcessing: 'Behandler\nID-data',
      },
      success: {
        frontSide: 'Foto-ID\nskannet',
        frontSideBackNext: 'ID-ens forside\nskannet',
        frontSideNfcNext: 'ID-ens forside\nskannet',
        backSide: 'Foto-ID\nskannet',
        backSideNfcNext: 'ID-ens bakside\nskannet',
        passport: 'Pass skannet',
        passportNfcNext: 'Pass skannet',
        userConfirmation: 'ID-skanning\nfullført',
        nfc: 'NFC-Chip-info\nlastet opp',
        additionalReview: 'ID-skanning\nfullført',
      },
      retry: {
        faceDidNotMatch: 'Ansiktet matchet ikke\ngodt nok',
        idNotFullyVisible: 'ID-dokument\nikke helt synlig',
        ocrResultsNotGoodEnough: 'ID-tekst ikke lesbar',
        idTypeNotSupported: 'ID ikke støttet\nVennligst bruk en annen ID',
        barcodeNotRead: 'Kunne ikke skanne strekkode\nprøv igjen',
      },
    },
  },
  retry: {
    header: 'La oss prøve det igjen',
    subHeader: 'Vi trenger en klarere video-selfie',
    yourImageLabel: 'Selfien din',
    idealImageLabel: 'Ideell posering',
    instruction: {
      primary: 'Naturlig uttrykk, ingen smiling',
      secondary: 'Bruk ideell posering og lyssetting',
      tertiary: 'For uklart, rens kameraet',
    },
    officialIdPhoto: {
      header: 'La oss prøve det igjen',
      subHeader: 'Trenger høyere bildekvalitet',
      instruction:
        'Prøv innendørsbelysning,\nIkke gjenskinn i briller, ikke smil/hodeplagg',
      yourImageLabel: 'Ditt bilde',
      idealImageLabel: 'Samsvarende ansikt',
    },
  },
};

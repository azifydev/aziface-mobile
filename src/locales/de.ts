import type { Locale } from '..';

/**
 * @description The German locale for the Aziface SDK.
 */
export const de: Required<Locale> = {
  accessibility: {
    cancelButton: 'Abbrechen',
    torchButton: 'Licht ein- oder ausschalten',
    tapGuidance:
      'Tippen Sie für Hilfe zur Positionierung Ihres Gesichts doppelt auf eine beliebige Stelle des Bildschirms.',
  },
  accessibilityFeedback: {
    movePhoneAway: 'Ihr Gesicht ist zu nah',
    movePhoneCloser: 'Ihr Gesicht ist zu weit entfernt',
    holdDeviceToEyeLevel: 'Halten Sie Ihr Gerät auf Augenhöhe',
    face: {
      tooFarLeft: 'Ihr Gesicht ist zu weit links',
      tooFarRight: 'Ihr Gesicht ist zu weit rechts',
      tooLow: 'Ihr Gesicht ist zu niedrig',
      tooHigh: 'Ihr Gesicht ist zu hoch',
      rotatedTooFarLeft: 'Ihr Gesicht ist zu weit nach links gedreht',
      rotatedTooFarRight: 'Ihr Gesicht ist zu weit nach rechts gedreht',
      pointingTooFarLeft: 'Ihr Gesicht zeigt zu weit nach links',
      pointingTooFarRight: 'Ihr Gesicht zeigt zu weit nach rechts',
      notOnCamera: 'Gesicht nicht in der Kamera oder zu weit weg',
    },
  },
  action: {
    ok: 'OK',
    imReady: 'ICH BIN BEREIT',
    tryAgain: 'ERNEUT VERSUCHEN',
    continue: 'FORTFAHREN',
    takePhoto: 'FOTO AUFNEHMEN',
    retakePhoto: 'WIEDER',
    acceptPhoto: 'ANNEHMEN',
    confirm: 'INFORMATION BESTÄTIGEN',
    scanNfc: 'ePASSPORT SCANNEN',
    scanNfcCard: 'AUSWEIS SCANNEN',
    skipNfc: 'SCHRITT ÜBERSPRINGEN',
  },
  camera: {
    initializingCamera: 'Sichern der Kamerainformationen',
    permission: {
      header: 'Aktivieren Sie Ihre Kamera',
      enroll:
        'Bitte tippen Sie auf die Schaltfläche unten, um Ihre Selfie-Kamera zu aktivieren.',
      auth: 'Ihre Kamera ist deaktiviert. Tippen Sie unten, um Ihre Einstellungen zu ändern.',
      enableCamera: 'KAMERA AKTIVIEREN',
      launchSettings: 'EINSTELLUNGEN ÖFFEN',
    },
  },
  feedback: {
    centerFace: 'Positionieren Sie Ihr Gesicht mittig',
    holdSteady: 'Bewegen Sie sich nicht',
    useEvenLighting: 'Leuchten Sie Ihr Gesicht gleichmäßiger aus',
    face: {
      notFound: 'Halten Sie Ihr Gesicht innerhalb des Rahmens',
      notLookingStraightAhead: 'Schauen Sie geradeaus',
      notUpright: 'Halten Sie Ihren Kopf gerade',
    },
    move: {
      phoneAway: 'Rücken Sie weiter weg',
      phoneCloser: 'Rücken Sie näher heran',
      phoneToEyeLevel: 'Halten Sie die Kamera auf Augenhöhe',
    },
  },
  idScan: {
    typeSelectionHeader:
      'Bereiten Sie sich auf den Scan\nIhres Ausweisdokuments vor',
    additionalReview: 'Zusätzliche Prüfung\nerforderlich',
    capture: {
      tapToFocus: 'Display zum Fokussieren antippen',
      holdSteady: 'Bitte ruhig halten',
      idFrontInstruction: 'Ausweis-Vorderseite scannen',
      idBackInstruction: 'Ausweis-Rückseite scannen',
    },
    review: {
      idFrontInstruction: 'Foto als klar/lesbar bestätigen',
      idBackInstruction: 'Foto als lesbar bestätigen',
    },
    ocr: {
      confirmationMainHeader: 'Prüfen und bestätigen',
      confirmationScroll: 'Scrollen Sie nach unten',
    },
    nfc: {
      statusDisabled:
        'Schalten Sie das\nNFC Ihres Geräts ein,\num fortzufahren',
      statusReady:
        'Bereiten Sie sich auf das Einlesen\nIhres ePassport-Chips vor',
      cardStatusReady:
        'Bereiten Sie sich auf das Einlesen\nIhres Ausweis-Chips vor',
      statusStarting:
        'Halten Sie das Telefon an\ndie Rückseite des ePassports\num den NFC-Chip zu scannen',
      cardStatusStarting:
        'Halten Sie das Smartphone an den Ausweis,\num den NFC-Chip einzulesen',
      statusScanning:
        'Halten Sie das Smartphone ruhig,\nder NFC-Chip wird eingelesen',
      statusWeakConnection: 'Lassen Sie uns einen\nweiteren Scan probieren',
      statusFinishedWithSuccess: 'NFC-Chip\ngescannt',
      statusFinishedWithError: 'Der ePassport-Chip\nist nicht lesbar',
      cardStatusFinishedWithError: 'Der Ausweis-Chip\nist nicht lesbar',
      statusSkipped: 'NFC-Scan\nnicht durchgeführt',
    },
    feedback: {
      flipIdToBack: 'Ausweis-Rückseite vorzeigen',
      flipIdToFront: 'Ausweis-Vorderseite vorzeigen',
    },
  },
  instructions: {
    header: {
      primary: 'Bereiten Sie sich auf',
      secondary: 'Ihr Video-Selfie vor',
    },
    message: {
      primary: 'Positionieren Sie Ihr Gesicht im Oval',
      secondary: 'Drücke „Ich bin bereit“ und rücke näher ran',
    },
  },
  presession: {
    orientation: 'Bitte das\nGerät drehen',
    frameYourFace: 'Positionieren Sie Ihr Gesicht im Oval',
    positionFaceStraightInOval: 'Schauen Sie geradeaus',
    removeDarkGlasses: 'Legen Sie Ihre Sonnenbrille ab',
    neutralExpression: 'Gesichtsausdruck neutral, nicht lächeln',
    conditionsTooBright: 'Die Umgebung ist zu hell',
    brightenYourEnvironment: 'Beleuchten Sie Ihre Umgebung stärker',
  },
  result: {
    nfcUpload: 'Lade\nverschlüsselte\nNFC-Informationen hoch',
    sessionAbort: 'Kritischer Fehler\nSitzung beendet',
    faceScan: {
      upload: 'Lade\nverschlüsselten\n3D-FaceScan hoch',
      uploadStillUploading:
        'Der Hochladevorgang läuft immer noch.\nLangsame Verbindung',
      success3d: {
        enrollment: 'Gesicht gescannt\n3D-Liveness bestätigt',
        reverification: '3D-Liveness bestätigtn\nGesicht verifiziert',
        livenessPriorToIdScan: 'Gesicht gescannt\n3D-Liveness bestätigt',
        livenessAndOfficialIdPhoto: '3D-Liveness bestätigtn',
      },
    },
    idScan: {
      unsuccess:
        'Das Foto auf dem Ausweis\nstimmt nicht mit dem\nGesicht des Benutzers überein',
      skipOrErrorNfc: 'ID-Daten\nhochgeladen',
      additionalReviewTag:
        'Manuelle Überprüfung erforderlich\nBitte IDV-Dashboard prüfen',
      uploadFrontSide: {
        uploadStarted: 'Lade\nverschlüsselten\nAusweis-Scan hoch',
        stillUploading:
          'Der Hochladevorgang läuft immer noch.\nLangsame Verbindung',
        uploadCompleteAwaitingResponse: 'ID-Bild\nhochgeladen',
        uploadCompleteAwaitingProcessing: 'ID-Scan\nwird verarbeitet',
      },
      uploadBackSide: {
        uploadStarted: 'Die verschlüsselte\nID-Rückseite\nwird hochgeladen',
        stillUploading:
          'Der Hochladevorgang läuft immer noch.\nLangsame Verbindung',
        uploadCompleteAwaitingResponse: 'ID-Bild\nhochgeladen',
        uploadCompleteAwaitingProcessing:
          'Bearbeitung\nder Rückseite des ID-Dokuments',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: 'Ihre bestätigten Informationen\nspeichern',
        stillUploading:
          'Der Hochladevorgang läuft immer noch.\nLangsame Verbindung',
        uploadCompleteAwaitingResponse: 'Informationen gespeichert',
        uploadCompleteAwaitingProcessing: 'Wird bearbeitet',
      },
      uploadNfc: {
        uploadStarted: 'Verschlüsselte NFC-Daten\nwerden hochgeladen',
        stillUploading:
          'Der Hochladevorgang läuft immer noch.\nLangsame Verbindung',
        uploadCompleteAwaitingResponse: 'NFC-Chip-Informationen\nhochgeladen',
        uploadCompleteAwaitingProcessing: 'NFC-Daten\nwerden verarbeitet',
      },
      uploadSkippedNfc: {
        uploadStarted: 'Verschlüsselte\nID-Informationen\nwerden hochgeladen',
        stillUploading:
          'Der Hochladevorgang läuft immer noch.\nLangsame Verbindung',
        uploadCompleteAwaitingResponse: 'Der Hochladevorgang ist abgeschlossen',
        uploadCompleteAwaitingProcessing: 'ID-Daten\nwerden bearbeitet',
      },
      success: {
        frontSide: 'Foto-ID\ngescannt',
        frontSideBackNext: 'Vorderseite des Ausweises\ngescannt',
        frontSideNfcNext: 'Vorderseite des Ausweises\ngescannt',
        backSide: 'Foto-ID\ngescannt',
        backSideNfcNext: 'Rückseite des Ausweises\ngescannt',
        passport: 'Reisepass gescannt',
        passportNfcNext: 'Reisepass gescannt',
        userConfirmation: 'ID-Scan\nabgeschlossen',
        nfc: 'NFC-Chip-Informationen\nhochgeladen',
        additionalReview: 'ID-Scan\nabgeschlossen',
      },
      retry: {
        faceDidNotMatch: 'Das Gesicht weicht\nzu stark ab',
        idNotFullyVisible: 'Der Ausweis ist nicht\nvollständig sichtbar',
        ocrResultsNotGoodEnough: 'Der Text auf dem Ausweis ist nicht lesbar',
        idTypeNotSupported:
          'Ausweis wird nicht unterstützt\nbitte verwenden Sie einen anderen Ausweis',
        barcodeNotRead:
          'Der Barcode konnte nicht gescannt werden,\nbitte versuchen Sie es noch einmal',
      },
    },
  },
  retry: {
    header: 'Versuchen wir es noch einmal',
    subHeader: 'Wir brauchen ein schärferes Video-Selfie',
    yourImageLabel: 'Ihr Selfie',
    idealImageLabel: 'Optimale Haltung',
    instruction: {
      primary: 'Gesichtsausdruck neutral, nicht lächeln',
      secondary: 'Pose und Licht müssen perfekt sein',
      tertiary: 'Zu unscharf, bitte Kamera reinigen',
    },
    officialIdPhoto: {
      header: 'Versuchen wir es noch einmal',
      subHeader: 'Bild in höherer Qualität nötig',
      instruction:
        'Versuchen mit Raumlicht,\nohne Brillenblendung, Lächeln oder Kopfbedeckung',
      yourImageLabel: 'Ihr Bild',
      idealImageLabel: 'Konforme Gesichtsaufnahme',
    },
  },
};

import type { Locale } from '..';

/**
 * @description The French locale for the Aziface SDK.
 */
export const fr: Required<Locale> = {
  accessibility: {
    cancelButton: 'Annuler',
    torchButton: 'Basculer la lumière',
    tapGuidance:
      "Tapez deux fois n'importe où sur l'écran pour obtenir des indications sur l'alignement du visage.",
  },
  accessibilityFeedback: {
    movePhoneAway: 'Visage trop proche',
    movePhoneCloser: 'Visage trop éloigné',
    holdDeviceToEyeLevel: "Tenez l'appareil au niveau des yeux",
    face: {
      tooFarLeft: 'Visage trop à gauche',
      tooFarRight: 'Visage trop à droite',
      tooLow: 'Visage trop bas',
      tooHigh: 'Visage trop haut',
      rotatedTooFarLeft: 'Visage tourné trop à gauche',
      rotatedTooFarRight: 'Visage tourné trop à droite',
      pointingTooFarLeft: 'Visage braqué trop à gauche',
      pointingTooFarRight: 'Visage braqué trop à droite',
      notOnCamera: 'Visage pas sur la caméra ou trop éloigné',
    },
  },
  action: {
    ok: 'OK',
    imReady: 'JE SUIS PRÊT(E)',
    tryAgain: 'RÉESSAYER',
    continue: 'CONTINUER',
    takePhoto: 'PRENDRE UNE PHOTO',
    retakePhoto: 'RÉESSAYEZ',
    acceptPhoto: 'ACCEPTER',
    confirm: 'CONFIRMEZ LES INFOS',
    scanNfc: 'SCANNEZ LE ePASSEPORT',
    scanNfcCard: 'SCANNEZ LA CARTE ID',
    skipNfc: 'IGNORER CETTE ÉTAPE',
  },
  camera: {
    initializingCamera: 'Sécurisation flux de caméra',
    permission: {
      header: 'Activez la caméra',
      enroll:
        'Veuillez appuyer sur le bouton ci-dessous pour activer votre caméra pour le selfie.',
      auth: 'Votre caméra est désactivée. Tapez ci-dessous pour modifier vos paramètres.',
      enableCamera: 'ACTIVER LA CAMÉRA',
      launchSettings: 'PARAMÈTRES DE LANCEMENT',
    },
  },
  feedback: {
    centerFace: 'Centrez votre visage',
    holdSteady: 'Restez stable',
    useEvenLighting: 'Éclairez le visage de manière plus uniforme',
    face: {
      notFound: 'Cadrez votre visage',
      notLookingStraightAhead: 'Regardez droit devant',
      notUpright: 'Tenez votre tête droite',
    },
    move: {
      phoneAway: 'Éloignez-vous',
      phoneCloser: 'Approchez-vous',
      phoneToEyeLevel: 'Déplacez la caméra au niveau des yeux',
    },
  },
  idScan: {
    typeSelectionHeader: "Préparez-vous à numériser\nVotre pièce d'identité",
    additionalReview: 'Examen supplémentaire\nRequis',
    capture: {
      tapToFocus: "Tapez l'écran pour mettre au point",
      holdSteady: 'Veuillez rester stable',
      idFrontInstruction: 'Numérisez le recto de la carte',
      idBackInstruction: 'Numérisez le verso de la carte',
    },
    review: {
      idFrontInstruction: 'Confirmez que photo est nette',
      idBackInstruction: 'Confirmez que photo est lisible',
    },
    ocr: {
      confirmationMainHeader: 'Examiner et confirmer',
      confirmationScroll: 'Défiler vers le bas',
    },
    nfc: {
      statusDisabled: 'Allumez le\n NFC de votre appareil\npour continuer',
      statusReady:
        'Préparez-vous à scanner\nLa puce de votre passeport électronique',
      cardStatusReady:
        "Préparez-vous à scanner\nLa puce de votre carte d'identité",
      statusStarting:
        'Maintenez le téléphone sur\nverso du passeport électronique\n pour scanner la puce NFC',
      cardStatusStarting:
        "Tenez le téléphone devant la carte d'identité\nPour scanner la puce NFC",
      statusScanning: 'Restez stable,\nScan de la puce NFC',
      statusWeakConnection: 'Essayons\nUn autre scan',
      statusFinishedWithSuccess: 'Puce NFC\nScannée',
      statusFinishedWithError: 'Puce du passeport électronique\nIllisible',
      cardStatusFinishedWithError: "Puce de carte d'identité\nIllisible",
      statusSkipped: 'Scannage NFC\nnon exécuté',
    },
    feedback: {
      flipIdToBack: "Montrez le verso de l'ID",
      flipIdToFront: "Montrez le recto de l'ID",
    },
  },
  instructions: {
    header: {
      primary: 'Préparez-vous pour',
      secondary: 'Votre selfie vidéo',
    },
    message: {
      primary: "Cadrez votre visage dans l'ovale",
      secondary: 'Cliquez Je suis prêt(e) et rapprochez-vous',
    },
  },
  presession: {
    orientation: 'Faire pivoter\nvotre appareil',
    frameYourFace: "Cadrez votre visage dans l'ovale",
    positionFaceStraightInOval: 'Regardez droit devant',
    removeDarkGlasses: 'Retirez les lunettes noires',
    neutralExpression: 'Expression neutre, pas de sourire',
    conditionsTooBright: 'Conditions trop lumineuses',
    brightenYourEnvironment: 'Illuminez votre environnement',
  },
  result: {
    nfcUpload: 'Téléchargement\nDétails NFC\nCrypté',
    sessionAbort: 'Erreur critique\nSession terminée',
    faceScan: {
      upload: 'Téléchargement\nFaceScan 3D\nCrypté',
      uploadStillUploading:
        'Toujours en cours de téléchargement...\nConnexion lente',
      success3d: {
        enrollment: 'Visage scanné\nVivacité 3D prouvée',
        reverification: 'Vivacité 3D prouvée\nVisage vérifié',
        livenessPriorToIdScan: 'Visage scanné\nVivacité 3D prouvée',
        livenessAndOfficialIdPhoto: 'Vivacité 3D prouvée',
      },
    },
    idScan: {
      unsuccess:
        "La photo d'identité\nNe correspond pas\nAu visage de l'utilisateur",
      skipOrErrorNfc: "Données d'identification\ntéléchargées",
      additionalReviewTag:
        'Examen manuel requis\nVeuillez consulter le tableau de bord IDV',
      uploadFrontSide: {
        uploadStarted: "Téléchargement\nScan d'identité\nCrypté",
        stillUploading:
          'Toujours en cours de téléchargement...\nConnexion lente',
        uploadCompleteAwaitingResponse:
          "Image de la pièce d'identité\nImportée",
        uploadCompleteAwaitingProcessing: "Traitement du\nscan d'identité",
      },
      uploadBackSide: {
        uploadStarted:
          "Téléchargement\ndu verso de la carte d'identité\nchiffré",
        stillUploading:
          'Toujours en cours de téléchargement...\nConnexion lente',
        uploadCompleteAwaitingResponse:
          "Image de la pièce d'identité\nImportée",
        uploadCompleteAwaitingProcessing:
          "Traitement du\nverso de la carte d'identité",
      },
      uploadUserConfirmedInfo: {
        uploadStarted: 'Sauvegarde de\nvos informations confirmées',
        stillUploading:
          'Toujours en cours de téléchargement...\nConnexion lente',
        uploadCompleteAwaitingResponse: 'Informations enregistrées',
        uploadCompleteAwaitingProcessing: 'Traitement',
      },
      uploadNfc: {
        uploadStarted: 'Téléchargement des\ndétails NFC chiffrés',
        stillUploading:
          'Toujours en cours de téléchargement...\nConnexion lente',
        uploadCompleteAwaitingResponse:
          'Informations sur la puce\nNFC Importées',
        uploadCompleteAwaitingProcessing: 'Traitement des\ndétails NFC',
      },
      uploadSkippedNfc: {
        uploadStarted: "Importation\nCryptées\nDonnées d'identification",
        stillUploading:
          'Toujours en cours de téléchargement...\nConnexion lente',
        uploadCompleteAwaitingResponse: 'Téléchargement terminé',
        uploadCompleteAwaitingProcessing:
          "Traitement\nde données d'identification",
      },
      success: {
        frontSide: "Pièce d'identité avec photo\nScannée",
        frontSideBackNext: "Recto de la pièce d'identité\nScanné",
        frontSideNfcNext: "Recto de la pièce d'identité\nScanné",
        backSide: "Pièce d'identité avec photo\nScannée",
        backSideNfcNext: "Dos de la pièce d'identité\nScanné",
        passport: 'Passeport scanné',
        passportNfcNext: 'Passeport scanné',
        userConfirmation: "Scan de la pièce d'identité\nEffectué",
        nfc: 'Informations sur la puce\nNFC Importées',
        additionalReview: "Scan de la pièce d'identité\nEffectué",
      },
      retry: {
        faceDidNotMatch: 'Le visage ne correspond\nPas assez',
        idNotFullyVisible: "Document d'identité\nPas entièrement visible",
        ocrResultsNotGoodEnough: "Texte d'identification illisible",
        idTypeNotSupported:
          'Identifiant non pris en charge\nveuillez utiliser un identifiant différent',
        barcodeNotRead: 'Échec du scan du code-barres\nVeuillez réessayer',
      },
    },
  },
  retry: {
    header: 'Essayons à nouveau',
    subHeader: 'Il nous faut un selfie vidéo plus clair',
    yourImageLabel: 'Votre selfie',
    idealImageLabel: 'Pose idéale',
    instruction: {
      primary: 'Expression neutre, pas de sourire',
      secondary: 'Utilisez la pose et l’éclairage idéaux',
      tertiary: 'Trop flou, nettoyez la caméra',
    },
    officialIdPhoto: {
      header: 'Essayons à nouveau',
      subHeader: "Besoin d'une meilleure image",
      instruction:
        "Essayez l'éclairage intérieur,\nsans reflet ni sourire, ni couvre-chef",
      yourImageLabel: 'Votre image',
      idealImageLabel: 'Visage conforme',
    },
  },
};

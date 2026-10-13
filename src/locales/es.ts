import type { Locale } from '..';

/**
 * @description The Spanish locale for the Aziface SDK.
 */
export const es: Required<Locale> = {
  accessibility: {
    cancelButton: 'Cancelar',
    torchButton: 'Interruptor de iluminación',
    tapGuidance:
      'Toque dos veces cualquier parte de la pantalla para ver indicaciones sobre cómo alinear el rostro.',
  },
  accessibilityFeedback: {
    movePhoneAway: 'Su rostro está demasiado cerca',
    movePhoneCloser: 'Su rostro está demasiado lejos',
    holdDeviceToEyeLevel: 'Mantenga el dispositivo a la altura de los ojos',
    face: {
      tooFarLeft: 'Su rostro está demasiado a la izquierda',
      tooFarRight: 'Su rostro está demasiado a la derecha',
      tooLow: 'Su rostro está demasiado abajo',
      tooHigh: 'Su rostro está demasiado arriba',
      rotatedTooFarLeft: 'Su rostro gira demasiado a la izquierda',
      rotatedTooFarRight: 'Su rostro gira demasiado a la derecha',
      pointingTooFarLeft:
        'Su rostro está demasiado inclinado hacia la izquierda',
      pointingTooFarRight:
        'Su rostro está demasiado inclinado hacia la derecha',
      notOnCamera: 'El rostro no se ve en la cámara o está demasiado lejos',
    },
  },
  action: {
    ok: 'ACEPTAR',
    imReady: 'ESTOY LISTO',
    tryAgain: 'VOLVER A INTENTARLO',
    continue: 'CONTINUAR',
    takePhoto: 'TOMAR FOTO',
    retakePhoto: 'REINTENTO',
    acceptPhoto: 'ACEPTAR',
    confirm: 'CONFIRMAR INFORMACIÓN',
    scanNfc: 'VOLVER A ESCANEAR',
    scanNfcCard: 'VOLVER A ESCANEAR',
    skipNfc: 'OMITIR ESTE PASO',
  },
  camera: {
    initializingCamera: 'Protegiendo la conexión',
    permission: {
      header: 'Habilite la cámara',
      enroll:
        'Por favor, toque el botón de abajo para activar su cámara de selfis.',
      auth: 'La cámara está deshabilitada. Toque a continuación para editar su configuración.',
      enableCamera: 'HABILITAR LA CÁMARA',
      launchSettings: 'ABRIR LA CONFIGURACIÓN',
    },
  },
  feedback: {
    centerFace: 'Centre su rostro',
    holdSteady: 'Quédese quieto',
    useEvenLighting: 'Ilumine el rostro de forma más uniforme',
    face: {
      notFound: 'Encuadre su rostro',
      notLookingStraightAhead: 'Mire hacia el frente',
      notUpright: 'Mantenga la cabeza recta',
    },
    move: {
      phoneAway: 'Apártese',
      phoneCloser: 'Acérquese',
      phoneToEyeLevel: 'Coloque la cámara a la altura de los ojos',
    },
  },
  idScan: {
    typeSelectionHeader: 'Prepárese para escanear\nsu identificación',
    additionalReview: 'Se requiere\nuna verificación adicional',
    capture: {
      tapToFocus: 'Toque la pantalla para enfocar',
      holdSteady: 'Quédese quieto',
      idFrontInstruction: 'Escanee el frente de la ID',
      idBackInstruction: 'Escanee el reverso de la ID',
    },
    review: {
      idFrontInstruction: 'Que la foto sea nítida y legible',
      idBackInstruction: 'Que la foto sea legible',
    },
    ocr: {
      confirmationMainHeader: 'Revisar y confirmar',
      confirmationScroll: 'Desplácese hacia abajo',
    },
    nfc: {
      statusDisabled: 'ENCIENDA\nel NFC de su equipo\npara continuar',
      statusReady:
        'Prepárese para escanear\nel chip de su pasaporte electrónico',
      cardStatusReady: 'Prepárese para escanear\nel chip de su identificación',
      statusStarting:
        'Sujete el teléfono en el\nreverso del e-Pasaporte\n para escanear el chip del NFC',
      cardStatusStarting:
        'Acerque el teléfono a su identificación\npara escanear el chip NFC',
      statusScanning: 'Manténgalo quieto,\nescaneando el chip NFC',
      statusWeakConnection: 'Intentemos\notro escaneo',
      statusFinishedWithSuccess: 'Chip NFC\nEscaneado',
      statusFinishedWithError:
        'No se puede leer\nel chip del pasaporte electrónico',
      cardStatusFinishedWithError:
        'No se puede leer\nel chip de la identificación',
      statusSkipped: 'Escaneo de NFC\nno realizado',
    },
    feedback: {
      flipIdToBack: 'Reverso de su identificación',
      flipIdToFront: 'Anverso de su identificación',
    },
  },
  instructions: {
    header: {
      primary: 'Prepárese para',
      secondary: 'su videoselfie',
    },
    message: {
      primary: 'Encuadre su rostro en el óvalo',
      secondary: 'Presione Estoy listo y acérquese',
    },
  },
  presession: {
    orientation: 'Gire\nsu dispositivo',
    frameYourFace: 'Encuadre su rostro en el óvalo',
    positionFaceStraightInOval: 'Mire hacia el frente',
    removeDarkGlasses: 'Quítese los lentes oscuros',
    neutralExpression: 'Expresión neutra, sin sonreír',
    conditionsTooBright: 'Demasiada luz',
    brightenYourEnvironment: 'Ilumine su entorno',
  },
  result: {
    nfcUpload: 'Subiendo\nla información NFC\nencriptada',
    sessionAbort: 'Error Crítico\nSesión Finalizada',
    faceScan: {
      upload: 'Subiendo\nel escaneo facial 3D\nencriptado',
      uploadStillUploading: 'Aún subiendo...\nConexión lenta',
      success3d: {
        enrollment: 'Rostro escaneado\nAnimación 3D verificada',
        reverification: 'Animación 3D verificada\nRostro verificado',
        livenessPriorToIdScan: 'Rostro escaneado\nAnimación 3D verificada',
        livenessAndOfficialIdPhoto: 'Animación 3D verificada',
      },
    },
    idScan: {
      unsuccess:
        'La foto de la identificación\no coincide con\\el rostro del usuario',
      skipOrErrorNfc: 'Datos de ID\ncargados',
      additionalReviewTag:
        'Revisión manual requerida\nConsulte el panel de IDV',
      uploadFrontSide: {
        uploadStarted: 'Subiendo\nel escaneo de identificación\nencriptado',
        stillUploading: 'Aún subiendo...\nConexión lenta',
        uploadCompleteAwaitingResponse: 'Imagen de ID\nCargada',
        uploadCompleteAwaitingProcessing: 'Procesando\nescaneo del ID',
      },
      uploadBackSide: {
        uploadStarted: 'Subiendo\nparte trasera del ID\ncifrada',
        stillUploading: 'Aún subiendo...\nConexión lenta',
        uploadCompleteAwaitingResponse: 'Imagen de ID\nCargada',
        uploadCompleteAwaitingProcessing: 'Procesando\nParte trasera del ID',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: 'Guardando\ntu información confirmada',
        stillUploading: 'Aún subiendo...\nConexión lenta',
        uploadCompleteAwaitingResponse: 'Información guardada',
        uploadCompleteAwaitingProcessing: 'Procesando',
      },
      uploadNfc: {
        uploadStarted: 'Subiendo detalles\nNFC cifrados',
        stillUploading: 'Aún subiendo...\nConexión lenta',
        uploadCompleteAwaitingResponse: 'Info de Chip NFC\nCargada',
        uploadCompleteAwaitingProcessing: 'Procesando\ndetalles NFC',
      },
      uploadSkippedNfc: {
        uploadStarted: 'Cargando\nDatos de ID\nCifrados',
        stillUploading: 'Aún subiendo...\nConexión lenta',
        uploadCompleteAwaitingResponse: 'Carga completa',
        uploadCompleteAwaitingProcessing: 'Procesando\ndatos de ID',
      },
      success: {
        frontSide: 'ID con foto\nEscaneada',
        frontSideBackNext: 'Anverso de la identificación\nescaneado',
        frontSideNfcNext: 'Anverso de la identificación\nescaneado',
        backSide: 'ID con foto\nEscaneada',
        backSideNfcNext: 'Reverso de la identificación\nescaneado',
        passport: 'Pasaporte escaneado',
        passportNfcNext: 'Pasaporte escaneado',
        userConfirmation: 'Escaneo de ID\nCompletado',
        nfc: 'Info de Chip NFC\nCargada',
        additionalReview: 'Escaneo de ID\nCompletado',
      },
      retry: {
        faceDidNotMatch: 'El rostro no coincide\nlo suficiente',
        idNotFullyVisible: 'La identificación\nno es totalmente visible',
        ocrResultsNotGoodEnough: 'El texto de la identificación no es legible',
        idTypeNotSupported: 'ID no compatible\nUse una ID diferente',
        barcodeNotRead:
          'No se pudo escanear el código de barras\nInténtelo de nuevo',
      },
    },
  },
  retry: {
    header: 'Intentémoslo otra vez',
    subHeader: 'Necesitamos una videoselfie más nítida',
    yourImageLabel: 'Su selfie',
    idealImageLabel: 'Pose ideal',
    instruction: {
      primary: 'Expresión neutra, sin sonreír',
      secondary: 'Use una postura e iluminación ideal',
      tertiary: 'Demasiado borrosa, limpie la cámara',
    },
    officialIdPhoto: {
      header: 'Intentémoslo otra vez',
      subHeader: 'La foto debe tener más calidad',
      instruction:
        'Use luz interior,\nsin sonreír, lentes con reflejos o sombreros',
      yourImageLabel: 'Su imagen',
      idealImageLabel: 'Cara conforme',
    },
  },
};

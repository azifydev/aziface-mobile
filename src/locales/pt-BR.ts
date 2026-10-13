import type { Locale } from '..';

/**
 * @description The Brazilian Portuguese locale for the Aziface SDK.
 */
export const ptBR: Required<Locale> = {
  accessibility: {
    cancelButton: 'Cancelar',
    torchButton: 'Alternar Luz',
    tapGuidance:
      'Toque duas vezes em qualquer lugar na tela para orientação de alinhamento facial.',
  },
  accessibilityFeedback: {
    movePhoneAway: 'Rosto Muito Próximo',
    movePhoneCloser: 'Rosto Muito Longe',
    holdDeviceToEyeLevel: 'Segure o Dispositivo ao Nível dos Olhos',
    face: {
      tooFarLeft: 'Rosto Muito à Esquerda',
      tooFarRight: 'Rosto Muito à Direita',
      tooLow: 'Rosto Muito Baixo',
      tooHigh: 'Rosto Muito Alto',
      rotatedTooFarLeft: 'Rosto Muito Virado Para a Esquerda',
      rotatedTooFarRight: 'Rosto Muito Virado Para a Direita',
      pointingTooFarLeft: 'Rosto Apontando Muito Para a Esquerda',
      pointingTooFarRight: 'Rosto Apontando Muito Para a Direita',
      notOnCamera: 'Rosto Fora da Câmera ou Muito Longe',
    },
  },
  action: {
    ok: 'OK',
    imReady: 'ESTOU PRONTO',
    tryAgain: 'TENTAR NOVAMENTE',
    continue: 'CONTINUAR',
    takePhoto: 'TIRAR FOTO',
    retakePhoto: 'REPETIR',
    acceptPhoto: 'ACEITAR',
    confirm: 'CONFIRMAR INFORMAÇÕES',
    scanNfc: 'ESCANEAR NOVAMENTE',
    scanNfcCard: 'ESCANEAR NOVAMENTE',
    skipNfc: 'PULAR ESTE PASSO',
  },
  camera: {
    initializingCamera: 'Protegendo o Feed da Câmera',
    permission: {
      header: 'Ative a Câmera',
      enroll: 'Toque no botão abaixo para habilitar sua câmera de selfie.',
      auth: 'Sua câmera está desativada. Toque abaixo para editar suas configurações.',
      enableCamera: 'ATIVAR CÂMERA',
      launchSettings: 'ABRIR CONFIGURAÇÕES',
    },
  },
  feedback: {
    centerFace: 'Centralize Seu Rosto',
    holdSteady: 'Mantenha a Posição',
    useEvenLighting: 'Ilumine o Rosto de Maneira Mais Uniforme',
    face: {
      notFound: 'Enquadre Seu Rosto',
      notLookingStraightAhead: 'Olhe Para Frente',
      notUpright: 'Mantenha Sua Cabeça Reta',
    },
    move: {
      phoneAway: 'Afaste-se',
      phoneCloser: 'Aproxime-se',
      phoneToEyeLevel: 'Mova a Câmera Para o Nível Dos Olhos',
    },
  },
  idScan: {
    typeSelectionHeader:
      'Prepare-se Para Escanear\nSeu Documento de Identificação',
    additionalReview: 'Revisão Adicional\nNecessária',
    capture: {
      tapToFocus: 'Toque na Tela Para Focar',
      holdSteady: 'Por Favor, Mantenha na Posição',
      idFrontInstruction: 'Escaneie Frente da Identificação',
      idBackInstruction: 'Escaneie Verso da Identificação',
    },
    review: {
      idFrontInstruction: 'Confirme Que a Foto é Nítida',
      idBackInstruction: 'Confirme Que a Foto é Legível',
    },
    ocr: {
      confirmationMainHeader: 'Revise e Confirme',
      confirmationScroll: 'Role Para Baixo',
    },
    nfc: {
      statusDisabled: 'Ligue\no NFC do Seu Dispositivo\nPara Continuar',
      statusReady:
        'Prepare-se Para Escanear\nO Chip do Seu Passaporte Eletrônico',
      cardStatusReady:
        'Prepare-se Para Escanear\nO Chip do Seu Cartão de Identificação',
      statusStarting:
        'Segure o Dispositivo no\nVerso do Passaporte Eletrônico\nPara Ler o Chip de NFC',
      cardStatusStarting:
        'Mova o Telefone Para o Cartão De Identificação\nPara Escanear o Chip NFC',
      statusScanning: 'Mantenha na Posição,\nEscaneando o Chip NFC',
      statusWeakConnection: 'Vamos Tentar\nde Novo',
      statusFinishedWithSuccess: 'Chip NFC\nEscaneado',
      statusFinishedWithError: 'Chip do Passaporte Eletrônico\nIlegível',
      cardStatusFinishedWithError: 'Chip do Cartão de Identificação\n Ilegível',
      statusSkipped: 'Leitura de NFC\nNão Realizada',
    },
    feedback: {
      flipIdToBack: 'Mostre o Verso da Identificação',
      flipIdToFront: 'Mostre a Frente da Identificação',
    },
  },
  instructions: {
    header: {
      primary: 'Prepare-se Para',
      secondary: 'Sua Selfie de Vídeo',
    },
    message: {
      primary: 'Enquadre Seu Rosto No Oval,',
      secondary: 'Pressione Estou Pronto, e Aproxime-se',
    },
  },
  presession: {
    orientation: 'Gire\nSeu Dispositivo',
    frameYourFace: 'Enquadre Seu Rosto no Oval',
    positionFaceStraightInOval: 'Olhe Para Frente',
    removeDarkGlasses: 'Remova os Óculos Escuros',
    neutralExpression: 'Expressão Neutra, Sem Sorriso',
    conditionsTooBright: 'Condições Muito Iluminadas',
    brightenYourEnvironment: 'Ilumine Seu Ambiente',
  },
  result: {
    nfcUpload: 'Carregando\nDetalhes de NFC\nCriptografados',
    sessionAbort: 'Erro Crítico\nSessão Finalizada',
    faceScan: {
      upload: 'Carregando\n3D FaceScan\nCriptografado',
      uploadStillUploading: 'Ainda Carregando...\nConexão Lenta',
      success3d: {
        enrollment: 'Rosto Escaneado\nProva de Vida 3D Comprovada',
        reverification: 'Prova de Vida 3D Comprovada\nRosto Verificado',
        livenessPriorToIdScan: 'Rosto Escaneado\nProva de Vida 3D Comprovada',
        livenessAndOfficialIdPhoto: 'Prova de Vida 3D Comprovada',
      },
    },
    idScan: {
      unsuccess:
        'A Foto da Identificação\nNão Correspondeu\nAo Rosto do Usuário',
      skipOrErrorNfc: 'Dados de Identificação\nCarregados',
      additionalReviewTag: 'Revisão Manual Necessária\nConsulte o painel IDV',
      uploadFrontSide: {
        uploadStarted:
          'Carregando\nEscaneamento de Identificação\nCriptografado',
        stillUploading: 'Ainda Carregando...\nConexão Lenta',
        uploadCompleteAwaitingResponse: 'Imagem de ID\nCarregada',
        uploadCompleteAwaitingProcessing: 'Processando\nEscaneamento de ID',
      },
      uploadBackSide: {
        uploadStarted: 'Carregando\nVerso de ID\nCriptografado',
        stillUploading: 'Ainda Carregando...\nConexão Lenta',
        uploadCompleteAwaitingResponse: 'Imagem de ID\nCarregada',
        uploadCompleteAwaitingProcessing: 'Processando\nVerso de ID',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: 'Salvando\nSuas Informações Confirmadas',
        stillUploading: 'Ainda Carregando...\nConexão Lenta',
        uploadCompleteAwaitingResponse: 'Informações Salvas',
        uploadCompleteAwaitingProcessing: 'Processando',
      },
      uploadNfc: {
        uploadStarted: 'Carregando Detalhes NFC\nCriptografado',
        stillUploading: 'Ainda Carregando...\nConexão Lenta',
        uploadCompleteAwaitingResponse: 'Informações do Chip NFC\nCarregadas',
        uploadCompleteAwaitingProcessing: 'Processando\nDetalhes NFC',
      },
      uploadSkippedNfc: {
        uploadStarted: 'Carregando\nDados de ID\nCriptografados',
        stillUploading: 'Ainda Carregando...\nConexão Lenta',
        uploadCompleteAwaitingResponse: 'Upload Completo',
        uploadCompleteAwaitingProcessing: 'Processando\nDados de Identificação',
      },
      success: {
        frontSide: 'ID com foto\nEscaneada',
        frontSideBackNext: 'Frente da Identificação\nEscaneada',
        frontSideNfcNext: 'Frente da Identificação\nEscaneada',
        backSide: 'ID com foto\nEscaneada',
        backSideNfcNext: 'Verso da Identidade\nEscaneado',
        passport: 'Passaporte Escaneado',
        passportNfcNext: 'Passaporte Escaneado',
        userConfirmation: 'Digitalização de ID\nConcluída',
        nfc: 'Informações do Chip NFC\nCarregadas',
        additionalReview: 'Digitalização de ID\nConcluída',
      },
      retry: {
        faceDidNotMatch: 'O Rosto Não Correspondeu\nMuito Bem',
        idNotFullyVisible:
          'O Documento de Identificação\nNão Está Totalmente Visível',
        ocrResultsNotGoodEnough: 'O Texto da Identificação Não Está Legível',
        idTypeNotSupported:
          'Identificação Não Suportada\nUse uma Identificação Diferente',
        barcodeNotRead:
          'Falha ao Escanear o Código de Barras\nPor Favor, Tente Novamente',
      },
    },
  },
  retry: {
    header: 'Vamos Tentar de Novo',
    subHeader: 'Faça Uma Selfie de Vídeo Melhor',
    yourImageLabel: 'Sua selfie',
    idealImageLabel: 'Pose Ideal',
    instruction: {
      primary: 'Expressão Neutra, Sem Sorriso',
      secondary: 'Por favor, Use Pose e Iluminação Ideal',
      tertiary: 'Muito Embaçado, Limpe a câmera',
    },
    officialIdPhoto: {
      header: 'Vamos Tentar de Novo',
      subHeader: 'É necessária uma imagem melhor',
      instruction:
        'Tente iluminação interna,\nóculos sem reflexos, sem sorrir nem chapéus',
      yourImageLabel: 'Sua Imagem',
      idealImageLabel: 'Rosto Conforme',
    },
  },
};

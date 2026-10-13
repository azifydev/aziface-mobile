import type { Locale } from '..';

/**
 * @description The Russian locale for the Aziface SDK.
 */
export const ru: Required<Locale> = {
  accessibility: {
    cancelButton: 'Отмена',
    torchButton: 'Переключить свет',
    tapGuidance:
      'Дважды коснитесь любого места на экране для получения рекомендаций по выравниванию лица.',
  },
  accessibilityFeedback: {
    movePhoneAway: 'Лицо слишком близко',
    movePhoneCloser: 'Лицо слишком далеко',
    holdDeviceToEyeLevel: 'Держите устройство на уровне глаз',
    face: {
      tooFarLeft: 'Лицо намного левее',
      tooFarRight: 'Лицо намного правее',
      tooLow: 'Лицо слишком низко',
      tooHigh: 'Лицо слишком высоко',
      rotatedTooFarLeft: 'Лицо повернуто намного левее',
      rotatedTooFarRight: 'Лицо повернуто намного правее',
      pointingTooFarLeft: 'Лицо смотрит намного левее',
      pointingTooFarRight: 'Лицо смотрит намного правее',
      notOnCamera: 'Лицо не в камере или слишком далеко',
    },
  },
  action: {
    ok: 'ОК',
    imReady: 'Я ГОТОВ',
    tryAgain: 'ПОВТОРИТЬ ПОПЫТКУ',
    continue: 'ПРОДОЛЖИТЬ',
    takePhoto: 'СФОТОГРАФИРОВАТЬ',
    retakePhoto: 'ПОВТОР',
    acceptPhoto: 'ПРИНЯТЬ',
    confirm: 'ПОДТВЕРДИТЬ ИНФОРМАЦИЮ',
    scanNfc: 'СКАНИРОВАТЬ ЭЛ.ПАСПОРТ',
    scanNfcCard: 'СКАНИР. ИДЕНТИФ.КАРТУ',
    skipNfc: 'ПРОПУСТИТЬ ЭТОТ ШАГ',
  },
  camera: {
    initializingCamera: 'Защита данных с камеры',
    permission: {
      header: 'Включить камеру',
      enroll: 'Нажмите кнопку ниже, чтобы включить селфи-камеру.',
      auth: 'Ваша камера отключена. Нажмите ниже, чтобы изменить свои настройки.',
      enableCamera: 'ВКЛЮЧИТЬ КАМЕРУ',
      launchSettings: 'ЗАПУСТИТЬ НАСТРОЙКИ',
    },
  },
  feedback: {
    centerFace: 'Поместите свое лицо в центр',
    holdSteady: 'Не двигайтесь',
    useEvenLighting: 'Осветите лицо более равномерно',
    face: {
      notFound: 'Поместите свое лицо в рамку',
      notLookingStraightAhead: 'Смотрите прямо перед собой',
      notUpright: 'Держите голову прямо',
    },
    move: {
      phoneAway: 'Отодвиньтесь дальше',
      phoneCloser: 'Придвиньтесь ближе',
      phoneToEyeLevel: 'Держите камеру на уровне глаз',
    },
  },
  idScan: {
    typeSelectionHeader:
      'Подготовьтесь к сканированию\nвашего идентификационного документа',
    additionalReview: 'Требуется дополнительная\nпроверка',
    capture: {
      tapToFocus: 'Коснитесь экрана, чтобы сфокусир.',
      holdSteady: 'Пожалуйста, не двигайтесь',
      idFrontInstruction: 'Сканир-ть удостоверение спереди',
      idBackInstruction: 'Сканировать удостоверение сзади',
    },
    review: {
      idFrontInstruction: 'Фото четкое, текст читается?',
      idBackInstruction: 'Читается ли текст на фото?',
    },
    ocr: {
      confirmationMainHeader: 'Проверьте и подтвердите',
      confirmationScroll: 'Прокрутите вниз',
    },
    nfc: {
      statusDisabled:
        'Поднесите телефон к\nзадней стороне паспорта\n, чтобы отсканировать чип NFC',
      statusReady:
        'Приготовьтесь к сканированию\nчипа вашего электронного паспорта',
      cardStatusReady:
        'Приготовьтесь к сканированию\nчипа вашей идентификационной карты',
      statusStarting:
        'Чтобы сканировать чип NFC, поднесите телефон к\nзадней стороне электронного паспорта\n',
      cardStatusStarting:
        'Поднесите телефон к идентификационной карте\nдля сканирования чипа NFC',
      statusScanning: 'Держите неподвижно, сканирование чипа NFC',
      statusWeakConnection: 'Давайте попробуем\nотсканировать еще раз',
      statusFinishedWithSuccess: 'Чип NFC\nотсканирован',
      statusFinishedWithError: 'Чип электронного паспорта\nнечитаемый',
      cardStatusFinishedWithError: 'Чип идентификационной карты\nнечитаемый',
      statusSkipped: 'Сканирование NFC\nне выполнено',
    },
    feedback: {
      flipIdToBack: 'Покаж.обратн.стор.идент.докум.',
      flipIdToFront: 'Покаж.передн.стор.идент.докум.',
    },
  },
  instructions: {
    header: {
      primary: 'Приготовьтесь к',
      secondary: 'вашему видеоселфи',
    },
    message: {
      primary: 'Поместите свое лицо в овал',
      secondary: 'Нажмите Я готов и придвиньтесь ближе',
    },
  },
  presession: {
    orientation: 'Поверните\nсвое устройство',
    frameYourFace: 'Поместите свое лицо в овал',
    positionFaceStraightInOval: 'Смотрите прямо перед собой',
    removeDarkGlasses: 'Снимите темные очки',
    neutralExpression: 'Нейтральное выражение лица, без улыбки',
    conditionsTooBright: 'Слишком яркая обстановка',
    brightenYourEnvironment: 'Сделайте свою обстановку ярче',
  },
  result: {
    nfcUpload: 'Загрузка\nзашифрованных\nданных NFC',
    sessionAbort: 'Критическая ошибка\nСеанс завершен',
    faceScan: {
      upload: 'Загрузка\nзашифрованного\n3D-скана лица',
      uploadStillUploading: 'Еще идет отправка...\nМедленное соединение',
      success3d: {
        enrollment: 'Лицо отсканировано\nПроверенная 3D-живость',
        reverification: 'Проверенная 3D-живость\nЛицо верифицировано',
        livenessPriorToIdScan: 'Лицо отсканировано\nПроверенная 3D-живость',
        livenessAndOfficialIdPhoto: 'Проверенная 3D-живость',
      },
    },
    idScan: {
      unsuccess:
        'Фото на идентификационном документе\nне соответствует\nлицу пользователя',
      skipOrErrorNfc: 'Данные удостоверения личности\nзагружены',
      additionalReviewTag:
        'Требуется проверка вручную\nСм. панель управления IDV',
      uploadFrontSide: {
        uploadStarted:
          'Загрузка\nзашифрованного\nскана идентификационного документа',
        stillUploading: 'Еще идет отправка...\nМедленное соединение',
        uploadCompleteAwaitingResponse:
          'Изображение удостоверения личности\nзагружено',
        uploadCompleteAwaitingProcessing:
          'Идет обработка\nСканирование документа',
      },
      uploadBackSide: {
        uploadStarted: 'Идет отправка\nЗашифровано\nОбратная сторона документа',
        stillUploading: 'Еще идет отправка...\nМедленное соединение',
        uploadCompleteAwaitingResponse:
          'Изображение удостоверения личности\nзагружено',
        uploadCompleteAwaitingProcessing:
          'Идет обработка\nОбратная сторона документа',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: 'Сохранение\nПодтвержденная вами информация',
        stillUploading: 'Еще идет отправка...\nМедленное соединение',
        uploadCompleteAwaitingResponse: 'Информация сохранена',
        uploadCompleteAwaitingProcessing: 'Идет обработка',
      },
      uploadNfc: {
        uploadStarted: 'Отправка зашифрована\nNFC-данные',
        stillUploading: 'Еще идет отправка...\nМедленное соединение',
        uploadCompleteAwaitingResponse: 'Информация о чипе NFC\nзагружена',
        uploadCompleteAwaitingProcessing: 'Идет обработка\nNFC-данные',
      },
      uploadSkippedNfc: {
        uploadStarted:
          'Загрузка\nзашифрованных\nданных об удостоверении личности',
        stillUploading: 'Еще идет отправка...\nМедленное соединение',
        uploadCompleteAwaitingResponse: 'Отправка завершена',
        uploadCompleteAwaitingProcessing:
          'Обработка\nданных удостоверения личности',
      },
      success: {
        frontSide: 'Удостоверение личности с фотографией\nотсканировано',
        frontSideBackNext:
          'Передняя сторона идентификационного документа\nотсканирована',
        frontSideNfcNext:
          'Передняя сторона идентификационного документа\nотсканирована',
        backSide: 'Удостоверение личности с фотографией\nотсканировано',
        backSideNfcNext:
          'Обратная сторона идентификационного документа\nотсканирована',
        passport: 'Паспорт отсканирован',
        passportNfcNext: 'Паспорт отсканирован',
        userConfirmation: 'Сканирование удостоверения личности\nзавершено',
        nfc: 'Информация о чипе NFC\nзагружена',
        additionalReview: 'Сканирование удостоверения личности\nзавершено',
      },
      retry: {
        faceDidNotMatch: 'Лицо не соответствует\nдостаточно сильно',
        idNotFullyVisible: 'Идентификационный документ\nвиден не полностью',
        ocrResultsNotGoodEnough:
          'Текст на идентификационном документе не разборчив',
        idTypeNotSupported:
          'Идентификатор не поддерживается\nИспользуйте другой идентификатор',
        barcodeNotRead:
          'Не удалось отсканировать штрих-код,\nпожалуйста, попробуйте еще раз',
      },
    },
  },
  retry: {
    header: 'Давайте попробуем еще раз',
    subHeader: 'Нам нужно более четкое видеоселфи',
    yourImageLabel: 'Ваше селфи',
    idealImageLabel: 'Идеальная поза',
    instruction: {
      primary: 'Нейтральное выражение лица, без улыбки',
      secondary: 'Используйте идеальную позу и освещение',
      tertiary: 'Слишком размыто, почистите камеру',
    },
    officialIdPhoto: {
      header: 'Давайте попробуем еще раз',
      subHeader: 'Нужно фото лучшего качества',
      instruction:
        'Попробуйте внутреннее освещение.\nБез бликов, улыбки и головного убора',
      yourImageLabel: 'Ваше изображение',
      idealImageLabel: 'Соответствующее лицо',
    },
  },
};

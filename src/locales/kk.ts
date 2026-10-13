import type { Locale } from '..';

/**
 * @description The Kazakh locale for the Aziface SDK.
 */
export const kk: Required<Locale> = {
  accessibility: {
    cancelButton: 'Болдырмау',
    torchButton: 'Жарық қосу/өшіру',
    tapGuidance:
      'Бетіңізді туралау туралы нұсқаулар алу үшін экранның кез келген жерін екі рет түртіңіз.',
  },
  accessibilityFeedback: {
    movePhoneAway: 'Бет тым жақын',
    movePhoneCloser: 'Тым алыс бет',
    holdDeviceToEyeLevel: 'Құрылғыны көз деңгейінде ұстаңыз',
    face: {
      tooFarLeft: 'Бет тым алыс солға',
      tooFarRight: 'Бет тым алыс оңға',
      tooLow: 'Бет тым төмен',
      tooHigh: 'Бет тым жоғары',
      rotatedTooFarLeft: 'Бет тым алыс солға бұрылды',
      rotatedTooFarRight: 'Бет тым алыс оңға бұрылды',
      pointingTooFarLeft: 'Тым алыс солға бағытталған бет',
      pointingTooFarRight: 'Тым алыс оңға бағытталған бет',
      notOnCamera: 'Бет жақтауда немесе тым алыс емес',
    },
  },
  action: {
    ok: 'ЖАРАЙДЫ МА',
    imReady: 'МЕН ДАЙЫНМЫН',
    tryAgain: 'ҚАЙТАДАН БАЙҚАП КӨРІҢІЗ',
    continue: 'ЖАЛҒАСТЫРУ',
    takePhoto: 'СУРЕТКЕ ТҮСІРУ',
    retakePhoto: 'ҚАЙТАЛАУ',
    acceptPhoto: 'ҚАБЫЛДАУ',
    confirm: 'АҚПАРАТТЫ РАСТАУ',
    scanNfc: 'ePASSPORT SCAN',
    scanNfcCard: 'ID CARD SCAN',
    skipNfc: 'БҰЛ ҚАДАМДЫ ӨТКІЗУ',
  },
  camera: {
    initializingCamera: 'Камераны қамтамасыз ету',
    permission: {
      header: 'Камераны қосыңыз',
      enroll: 'Селфи камерасын қосу үшін төмендегі түймені басыңыз.',
      auth: 'Камераңыз өшірілген. Параметрлерді өңдеу үшін төменде түртіңіз.',
      enableCamera: 'КАМЕРАНЫ ҚОСУ',
      launchSettings: 'ПАРАМЕТРЛЕРДІ ҚОСУ',
    },
  },
  feedback: {
    centerFace: 'Бетіңізді ортаға қойыңыз',
    holdSteady: 'Тұрақты ұстаңыз',
    useEvenLighting: 'Бет біркелкі жарық',
    face: {
      notFound: 'Бетіңізді жақтау',
      notLookingStraightAhead: 'Алға қара',
      notUpright: 'Басыңызды тік ұстаңыз',
    },
    move: {
      phoneAway: 'Алысқа көшіңіз',
      phoneCloser: 'Жақынырақ жылжытыңыз',
      phoneToEyeLevel: 'Камераны көз деңгейіне жылжытыңыз',
    },
  },
  idScan: {
    typeSelectionHeader: 'Сканерлеуге дайындалыңыз\nсіздің сәйкестендіруіңіз',
    additionalReview: 'Қосымша растау\nқажет',
    capture: {
      tapToFocus: 'Фокуста экранды түртіңіз',
      holdSteady: 'Өтінемін, позицияда болыңыз',
      idFrontInstruction: 'алдыңғы бөлігін сканерлеу',
      idBackInstruction: 'артқы бөлігін сканерлеу',
    },
    review: {
      idFrontInstruction: 'Таза Фотосуретті Растаңыз',
      idBackInstruction: 'Түсінікті Фотосуретті Растаңыз',
    },
    ocr: {
      confirmationMainHeader: 'Шолу және растау',
      confirmationScroll: 'Төмен қарай жылжытыңыз',
    },
    nfc: {
      statusDisabled: 'Қосу\nҚұрылғыңыздың NFC\nЖалғастыру Үшін',
      statusReady: 'ePassport чипін \n сканерлеуге дайын болыңыз',
      cardStatusReady: 'ID чипіңізді \n сканерлеуге дайындалыңыз',
      statusStarting:
        'NFC чипін сканерлеу үшін телефонды\nэлектрондық төлқұжаттың\nартқы жағында ұстаңыз',
      cardStatusStarting:
        'NFC чипін сканерлеу үшін телефонды идентификаторыңызға\nжақындатыңыз',
      statusScanning: 'Оны қозғалыссыз ұстаңыз, \n NFC чипін сканерлейді',
      statusWeakConnection: 'Тағы \n сканерлеп көрейік',
      statusFinishedWithSuccess: 'NFC Чипі\nСканерленген',
      statusFinishedWithError: 'Оқу мүмкін емес\nэлектрондық төлқұжат чипі',
      cardStatusFinishedWithError: 'Идентификатордағы\nчипті оқу мүмкін емес',
      statusSkipped: 'NFC Сканерлеу\nОрындалмады',
    },
    feedback: {
      flipIdToBack: 'Жеке куәліктің сыртқы жағы',
      flipIdToFront: 'Жеке куәліктің алдыңғы жағы',
    },
  },
  instructions: {
    header: {
      primary: 'Дайындалыңыз',
      secondary: 'Сіздің бейнеңіз',
    },
    message: {
      primary: 'Сіздің бетіңізді сопақша жақтаңыз',
      secondary: 'Менің дайынмын және жақындастырыңыз',
    },
  },
  presession: {
    orientation: 'Құрылғыны\nбұраңыз',
    frameYourFace: 'Бетіңізді сопақша жақтаңыз',
    positionFaceStraightInOval: 'Алға қара',
    removeDarkGlasses: 'Қара көзілдірікті алып тастаңыз',
    neutralExpression: 'Бейтарап өрнек, күлімсіреу',
    conditionsTooBright: 'Шарттар тым ашық',
    brightenYourEnvironment: 'Қоршаған ортаңызды нұрландырыңыз',
  },
  result: {
    nfcUpload: 'Жүктеу\nNFC туралы ақпарат\nшифрланбаған',
    sessionAbort: 'Сыни Қате\nСессия Аяқталды',
    faceScan: {
      upload: 'Жүктеу\n3D бетті сканерлеу\nшифрланған',
      uploadStillUploading: 'Әлі Жүктеп Жатыр...\nБаяу Байланыс',
      success3d: {
        enrollment: 'Бетті сканерлеу\nрасталған 3D-өміршеңдік',
        reverification: '3D өміршеңдігі расталды\nбет тексерілді',
        livenessPriorToIdScan: 'Бетті сканерлеу\nрасталған 3D-өміршеңдік',
        livenessAndOfficialIdPhoto: '3D өміршеңдігі расталды',
      },
    },
    idScan: {
      unsuccess:
        'Жеке куәлік суреті \n немесе \n пайдаланушының бетіне сәйкес келеді',
      skipOrErrorNfc: 'ЖЕКЕ КУӘЛІК деректері\nЖүктелді',
      additionalReviewTag: 'Қолмен Тексеру Қажет\nIDV Бақылау Тақтасын Қараңыз',
      uploadFrontSide: {
        uploadStarted: 'Жүктеу\nсәйкестендіруді сканерлеу\nшифрланбаған',
        stillUploading: 'Әлі Жүктеп Жатыр...\nБаяу Байланыс',
        uploadCompleteAwaitingResponse: 'ID Кескіні\nЖүктелді',
        uploadCompleteAwaitingProcessing: 'Өңдеу\nИДЕНТИФИКАТОРДЫ Сканерлеу',
      },
      uploadBackSide: {
        uploadStarted: 'ЖҮКТЕУ\nШИФРЛАНҒАН\nИДЕНТИФИКАТОРДЫҢ Артқы Жағы',
        stillUploading: 'Әлі Жүктеп Жатыр...\nБаяу Байланыс',
        uploadCompleteAwaitingResponse: 'ID Кескіні\nЖүктелді',
        uploadCompleteAwaitingProcessing: 'ӨҢДЕУ\nИДЕНТИФИКАТОРДЫҢ Артқы Жағы',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: 'Сіздің Расталған Ақпаратыңызды Сақтау',
        stillUploading: 'Әлі Жүктеп Жатыр...\nБаяу Байланыс',
        uploadCompleteAwaitingResponse: 'Ақпарат Сақталды',
        uploadCompleteAwaitingProcessing: 'Өңдеу',
      },
      uploadNfc: {
        uploadStarted: 'Шифрланған\nNFC Мәліметтерін жүктеу',
        stillUploading: 'Әлі Жүктеп Жатыр...\nБаяу Байланыс',
        uploadCompleteAwaitingResponse: 'Nfc Чипі Туралы Ақпарат\nЖүктелді',
        uploadCompleteAwaitingProcessing: 'Өңдеу\nNFC Мәліметтері',
      },
      uploadSkippedNfc: {
        uploadStarted: 'Деректерді жүктеу\nШифрланған\nИДЕНТИФИКАТОР',
        stillUploading: 'Әлі Жүктеп Жатыр...\nБаяу Байланыс',
        uploadCompleteAwaitingResponse: 'Жүктеу Аяқталды',
        uploadCompleteAwaitingProcessing: 'ӨҢДЕУ\nЖЕКЕ КУӘЛІК деректері',
      },
      success: {
        frontSide: 'Фотосурет ИДЕНТИФИКАТОРЫ\nСканерленген',
        frontSideBackNext: 'ID алдыңғы\nсканерленді',
        frontSideNfcNext: 'ID алдыңғы\nсканерленді',
        backSide: 'Фотосурет ИДЕНТИФИКАТОРЫ\nСканерленген',
        backSideNfcNext: 'ID артқы\nсканерленді',
        passport: 'Сканерленген төлқұжат',
        passportNfcNext: 'Сканерленген төлқұжат',
        userConfirmation: 'ЖЕКЕ КУӘЛІКТІ Сканерлеу\nАяқталды',
        nfc: 'Nfc Чипі Туралы Ақпарат\nЖүктелді',
        additionalReview: 'ЖЕКЕ КУӘЛІКТІ Сканерлеу\nАяқталды',
      },
      retry: {
        faceDidNotMatch: 'Бет\nсәйкес келмейді',
        idNotFullyVisible: 'Идентификатор\nтолық көрінбейді',
        ocrResultsNotGoodEnough: 'Сәйкестендіру мәтіні оқылмайды',
        idTypeNotSupported:
          'ИДЕНТИФИКАТОРҒА Қолдау көрсетілмейді\nБасқа ИДЕНТИФИКАТОРДЫ Пайдаланыңыз',
        barcodeNotRead:
          'Штрих-кодты сканерлеу мүмкін болмады\nҚайталап көріңіз',
      },
    },
  },
  retry: {
    header: 'Мұны тағы да байқап көрейік',
    subHeader: 'Бізге нақты бейне-селфи керек',
    yourImageLabel: 'Сенің сенсің',
    idealImageLabel: 'Идеалды поза',
    instruction: {
      primary: 'Бейтарап өрнек, күлімсіреу жоқ',
      secondary: 'Поза мен жарықтандыруды жақсарту',
      tertiary: 'Тым бұлыңғыр, таза камера',
    },
    officialIdPhoto: {
      header: 'Мұны тағы да байқап көрейік',
      subHeader: 'Сапасы жоғары сурет керек',
      instruction:
        'Ішкі жарықты қолданыңыз,\nКөзілдірікте жарқылсыз, күлмеңіз, бас киімсіз',
      yourImageLabel: 'Сіздің Суретіңіз',
      idealImageLabel: 'Үйлесімді Тұлға',
    },
  },
};

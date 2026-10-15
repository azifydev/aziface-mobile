import type { Locale } from '..';

/**
 * @description The Arabic locale for the Aziface SDK.
 */
export const ar: Required<Locale> = {
  accessibility: {
    cancelButton: 'إلغاء',
    torchButton: 'تبديل الضوء',
    tapGuidance: 'انقر مرتين في أي مكان على الشاشة لتوجيه محاذاة الوجه.',
  },
  accessibilityFeedback: {
    movePhoneAway: 'وجهك قريب أكثر من اللازم',
    movePhoneCloser: 'وجهك بعيد أكثر من اللازم',
    holdDeviceToEyeLevel: 'أمسك الجهاز على مستوى العين',
    face: {
      tooFarLeft: 'وجهك ناحية اليسار أكثر من اللازم',
      tooFarRight: 'وجهك ناحية اليمين أكثر من اللازم',
      tooLow: 'وجهك منخفض أكثر من اللازم',
      tooHigh: 'وجهك مرتفع أكثر من اللازم',
      rotatedTooFarLeft: 'تم تدوير وجهك ناحية اليسار أكثر من اللازم',
      rotatedTooFarRight: 'تم تدوير وجهك ناحية اليمين أكثر من اللازم',
      pointingTooFarLeft: 'وجهك يشير إلى اليسار أكثر من اللازم',
      pointingTooFarRight: 'وجهك يشير إلى اليمين أكثر من اللازم',
      notOnCamera: 'الوجه ليس على الكاميرا أو بعيدًا جدًا',
    },
  },
  action: {
    ok: 'أوافق',
    imReady: 'أنا مستعد',
    tryAgain: 'المحاولة مرة أخرى',
    continue: 'التقاط صورة',
    takePhoto: 'التقاط صورة',
    retakePhoto: 'حاول',
    acceptPhoto: 'أقبل',
    confirm: 'تأكيد المعلومات',
    scanNfc: 'امسح الجواز الإلكتروني',
    scanNfcCard: 'امسح بطاقة الهوية',
    skipNfc: 'تخطي هذه الخطوة',
  },
  camera: {
    initializingCamera: 'تأمين بث الكاميرا',
    permission: {
      header: 'فعّل الكاميرا',
      enroll:
        'يُرجى النقر على الزر أدناه لتمكين كاميرا الصور الذاتية الخاصة بك.',
      auth: 'تم تعطيل الكاميرا الخاصة بك. انقر أدناه لتعديل الإعدادات الخاصة بك.',
      enableCamera: 'تفعيل الكاميرا',
      launchSettings: 'تشغيل الإعدادات',
    },
  },
  feedback: {
    centerFace: 'قم بتوسيط وجهك',
    holdSteady: 'أمسك بثبات',
    useEvenLighting: 'اجعل الضوء على وجهك بشكل متساوٍ أكثر',
    face: {
      notFound: 'ضع وجهك في الإطار',
      notLookingStraightAhead: 'انظر إلى الأمام مباشرةً',
      notUpright: 'ارفع رأسك بشكل مستقيم',
    },
    move: {
      phoneAway: 'تحرّك بعيدًا',
      phoneCloser: 'اقترب أكثر',
      phoneToEyeLevel: 'حرّك الكاميرا إلى مستوى العين',
    },
  },
  idScan: {
    typeSelectionHeader: 'استعد لمسح\nمستند الهوية التعريفية الخاصة بك ضوئيًا',
    additionalReview: 'مطلوب\nمراجعة إضافية',
    capture: {
      tapToFocus: 'انقر فوق الشاشة للتركيز',
      holdSteady: 'من فضلك أمسك بثبات',
      idFrontInstruction: 'مسح الوجه الأمامي من الهوية',
      idBackInstruction: 'مسح الوجه الخلفي من الهوية',
    },
    review: {
      idFrontInstruction: 'تأكد من أن الصورة واضحة ومقروءة',
      idBackInstruction: 'تأكد من أن الصورة مقروءة',
    },
    ocr: {
      confirmationMainHeader: 'مراجعة وتأكيد',
      confirmationScroll: 'مرر لأسفل',
    },
    nfc: {
      statusDisabled: 'قم بتشغيل\n‏NFC في جهازك\n‏للمتابعة',
      statusReady: 'استعد لمسح\n شريحة جواز السفر الإلكتروني الخاص بك ضوئيًا',
      cardStatusReady:
        'استعد لمسح\n شريحة بطاقة الهوية التعريفية الخاصة بك ضوئيًا',
      statusStarting:
        'امسك الهاتف بجانب\n‏الجزء الخلفي من جواز السفر الإلكتروني\n‏لمسح شريحة NFC ضوئيًا',
      cardStatusStarting:
        'أمسك الهاتف ناحية بطاقة الهوية التعريفية\nلمسح شريحة NFC ضوئيًا',
      statusScanning: 'أمسكه بثبات،\n جارٍ مسح شريحة NFC ضوئيًا',
      statusWeakConnection: 'لنحاول\nمسح ضوئي آخر',
      statusFinishedWithSuccess: 'تم مسح\n‏شريحة NFC ضوئياً',
      statusFinishedWithError: 'شريحة جواز السفر الإلكتروني\nغير قابلة للقراءة',
      cardStatusFinishedWithError:
        'شريحة بطاقة الهوية التعريفية\n غير قابلة للقراءة',
      statusSkipped: 'لم يتم إجراء مسح ضوئي لـ NFC‏',
    },
    feedback: {
      flipIdToBack: 'أظهر خلفية الهوية التعريفية',
      flipIdToFront: 'أظهر مقدمة الهوية التعريفية',
    },
  },
  instructions: {
    header: {
      primary: 'استعد من أجل',
      secondary: 'تصوير فيديو ذاتي',
    },
    message: {
      primary: 'ضع وجهك في الشكل البيضاوي',
      secondary: 'اضغط على أنا جاهز واقترب',
    },
  },
  presession: {
    orientation: 'يُرجى تدوير\n جهازك',
    frameYourFace: 'ضع وجهك في الشكل البيضاوي',
    positionFaceStraightInOval: 'انظر إلى الأمام مباشرةً',
    removeDarkGlasses: 'قم بإزالة النظارات الداكنة',
    neutralExpression: 'تعبير محايد، لا تبتسم',
    conditionsTooBright: 'الظروف شديدة السطوع',
    brightenYourEnvironment: 'قم بزيادة الضوء في محيطك',
  },
  result: {
    nfcUpload: 'تحميل \nمشفر\n لبيانات NFC',
    sessionAbort: 'انتهت الجلسة\nخطأ فادح',
    faceScan: {
      upload: 'تحميل \nمشفر\n لمسح ضوئي ثلاثي الأبعاد للوجه',
      uploadStillUploading: 'لا تزال عملية التحميل جارية...\n الاتصال بطيء',
      success3d: {
        enrollment: 'تم مسح الوجه ضوئيًا\nتم إثبات حيوية ثلاثية الأبعاد',
        reverification: 'تم إثبات حيوية ثلاثية الأبعاد\nتم التحقق من الوجه',
        livenessPriorToIdScan:
          'تم مسح الوجه ضوئيًا\nتم إثبات حيوية ثلاثية الأبعاد',
        livenessAndOfficialIdPhoto: 'تم إثبات حيوية ثلاثية الأبعاد',
      },
    },
    idScan: {
      unsuccess: 'صورة الهوية التعريفية\nلم تتطابق\nمع وجه المستخدم',
      skipOrErrorNfc: 'تم تحميل بيانات بطاقة الهوية\n‏',
      additionalReviewTag:
        'المراجعة اليدوية مطلوبة\n‏يُرجى الاطلاع على لوحة معلومات IDV',
      uploadFrontSide: {
        uploadStarted: 'تحميل \nمشفر\n لمسح ضوئي للهوية التعريفية',
        stillUploading: 'لا تزال عملية التحميل جارية...\n الاتصال بطيء',
        uploadCompleteAwaitingResponse: 'تم رفع\n‏صورة الهوية',
        uploadCompleteAwaitingProcessing: 'معالجة\n مسح الهوية',
      },
      uploadBackSide: {
        uploadStarted: 'تحميل\n الجزء الخلفي من الهوية \n المشفرة',
        stillUploading: 'لا تزال عملية التحميل جارية...\n الاتصال بطيء',
        uploadCompleteAwaitingResponse: 'تم رفع\n‏صورة الهوية',
        uploadCompleteAwaitingProcessing: 'معالجة\n الجزء الخلفي من الهوية',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: 'حفظ\n معلوماتك المؤكدة',
        stillUploading: 'لا تزال عملية التحميل جارية...\n الاتصال بطيء',
        uploadCompleteAwaitingResponse: 'تم حفظ المعلومات',
        uploadCompleteAwaitingProcessing: 'المعالجة',
      },
      uploadNfc: {
        uploadStarted: 'تحميل تفاصيل\nNFC المشفرة',
        stillUploading: 'لا تزال عملية التحميل جارية...\n الاتصال بطيء',
        uploadCompleteAwaitingResponse: 'تم رفع معلومات\n‏شريحة NFC',
        uploadCompleteAwaitingProcessing: 'معالجة\n تفاصيل NFC',
      },
      uploadSkippedNfc: {
        uploadStarted: 'جارٍ رفع\n‏بيانات\n‏الهوية المُشفَّرة',
        stillUploading: 'لا تزال عملية التحميل جارية...\n الاتصال بطيء',
        uploadCompleteAwaitingResponse: 'لقد اكتمل التحميل',
        uploadCompleteAwaitingProcessing: 'جارٍ معالجة\n‏بيانات بطاقة الهوية',
      },
      success: {
        frontSide: 'تم مسح \n‏صورة الهوية ضوئياً',
        frontSideBackNext: 'تم مسح\nمقدمة الهوية التعريفية ضوئيًا',
        frontSideNfcNext: 'تم مسح\nمقدمة الهوية التعريفية ضوئيًا',
        backSide: 'تم مسح \n‏صورة الهوية ضوئياً',
        backSideNfcNext: 'تم مسح\nخلفية الهوية التعريفية ضوئيًا',
        passport: 'تم مسح جواز السفر ضوئيًا',
        passportNfcNext: 'تم مسح جواز السفر ضوئيًا',
        userConfirmation: 'اكتمل\n‏مسح الهوية',
        nfc: 'تم رفع معلومات\n‏شريحة NFC',
        additionalReview: 'اكتمل\n‏مسح الهوية',
      },
      retry: {
        faceDidNotMatch: 'الوجه غير متطابق\nبدرجة كافية',
        idNotFullyVisible: 'مستند الهوية التعريفية\nغير مرئي بالكامل',
        ocrResultsNotGoodEnough: 'نص الهوية التعريفية غير مقروء',
        idTypeNotSupported:
          'بطاقة الهوية غير مدعومة\n‏يرجى استخدام بطاقة هوية مختلفة',
        barcodeNotRead: 'فشل مسح الرمز الشريطي ضوئيًا\nيُرجى المحاولة مرة أخرى',
      },
    },
  },
  retry: {
    header: 'لنحاول ذلك مرة أخرى',
    subHeader: 'نحتاج إلى تصوير فيديو ذاتي أوضح',
    yourImageLabel: 'صورتك الذاتية',
    idealImageLabel: 'الوضعية المثالية',
    instruction: {
      primary: 'تعبير محايد، لا تبتسم',
      secondary: 'استخدم الوضعية والإضاءة المثاليين',
      tertiary: 'ضبابية للغاية، نظّف الكاميرا',
    },
    officialIdPhoto: {
      header: 'لنحاول ذلك مرة أخرى',
      subHeader: 'نحتاج إلى صورة عالية الجودة.',
      instruction:
        'يُرجى استخدام إضاءة داخلية،\nدون نظارة شمسية أو ابتسام أو غطاء رأس.',
      yourImageLabel: 'صورتك',
      idealImageLabel: 'الوجه المتوافق',
    },
  },
};

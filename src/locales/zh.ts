import type { Locale } from '..';

/**
 * @description The Chinese locale for the Aziface SDK.
 */
export const zh: Required<Locale> = {
  accessibility: {
    cancelButton: '取消',
    torchButton: '切换灯光',
    tapGuidance: '双击屏幕任意位置，获取面部对齐指南。',
  },
  accessibilityFeedback: {
    movePhoneAway: '面部太近',
    movePhoneCloser: '面部太远',
    holdDeviceToEyeLevel: '将设备置于与眼睛平齐的高度',
    face: {
      tooFarLeft: '面部太偏左',
      tooFarRight: '面部太偏右',
      tooLow: '面部太低',
      tooHigh: '面部太高',
      rotatedTooFarLeft: '面部向左旋转太多',
      rotatedTooFarRight: '面部向右旋转太多',
      pointingTooFarLeft: '面部朝向太偏左',
      pointingTooFarRight: '面部朝向太偏右',
      notOnCamera: '面部未对着摄像头或距离太远',
    },
  },
  action: {
    ok: '好的',
    imReady: '我准备好了',
    tryAgain: '重试',
    continue: '继续',
    takePhoto: '拍摄照片',
    retakePhoto: '重试',
    acceptPhoto: '接受',
    confirm: '确认信息',
    scanNfc: '扫描电子护照',
    scanNfcCard: '扫描身份证件',
    skipNfc: '跳过此步骤',
  },
  camera: {
    initializingCamera: '确保摄像头馈送信号',
    permission: {
      header: '启用摄像头',
      enroll: '请点击下方按钮，启用自拍摄像头。',
      auth: '您的摄像头已禁用。请点击下方编辑设置。',
      enableCamera: '启用摄像头',
      launchSettings: '启动设置',
    },
  },
  feedback: {
    centerFace: '将面部居中',
    holdSteady: '保持稳定',
    useEvenLighting: '让面部光线更均匀',
    face: {
      notFound: '将面部框住',
      notLookingStraightAhead: '直视前方',
      notUpright: '保持头部正直',
    },
    move: {
      phoneAway: '移开',
      phoneCloser: '靠近',
      phoneToEyeLevel: '将摄像头移到与眼睛平齐的高度',
    },
  },
  idScan: {
    typeSelectionHeader: '准备扫描\n您的身份证件',
    additionalReview: '需要\n额外审核',
    capture: {
      tapToFocus: '点击屏幕对焦',
      holdSteady: '请保持稳定',
      idFrontInstruction: '扫描身份证正面',
      idBackInstruction: '扫描身份证背面',
    },
    review: {
      idFrontInstruction: '确认照片清晰且易读',
      idBackInstruction: '确认照片易读',
    },
    ocr: {
      confirmationMainHeader: '查看并确认',
      confirmationScroll: '下滑',
    },
    nfc: {
      statusDisabled: '开启\n设备的NFC\n以继续',
      statusReady: '准备扫描\n您的电子护照芯片',
      cardStatusReady: '准备扫描\n您的身份证件芯片',
      statusStarting: '将手机贴近\n电子护照背面\n以扫描NFC芯片',
      cardStatusStarting: '将手机靠近身份证件\n以扫描NFC芯片',
      statusScanning: '请保持稳定，\n正在扫描NFC芯片',
      statusWeakConnection: '尝试\n再次扫描',
      statusFinishedWithSuccess: 'NFC 芯片\n已扫描',
      statusFinishedWithError: '无法读取\n电子护照芯片',
      cardStatusFinishedWithError: '无法读取\n身份证件芯片',
      statusSkipped: '未执行NFC扫描',
    },
    feedback: {
      flipIdToBack: '将身份证件翻转\n至背面',
      flipIdToFront: '出示身份证件正面',
    },
  },
  instructions: {
    header: {
      primary: '准备进行',
      secondary: '视频自拍',
    },
    message: {
      primary: '用椭圆框住面部',
      secondary: '按下“我准备好了”并靠近',
    },
  },
  presession: {
    orientation: '请旋转您的设备',
    frameYourFace: '用椭圆框住面部',
    positionFaceStraightInOval: '直视前方',
    removeDarkGlasses: '摘掉深色眼镜',
    neutralExpression: '表情自然，不要微笑',
    conditionsTooBright: '环境太亮',
    brightenYourEnvironment: '将环境调亮',
  },
  result: {
    nfcUpload: '正在上传\n加密\nNFC详细信息',
    sessionAbort: '严重错误\n会话已结束',
    faceScan: {
      upload: '正在上传\n加密\n3D面部扫描',
      uploadStillUploading: '仍在上传中……\n连接速度较慢',
      success3d: {
        enrollment: '面部已扫描\n已经过 3D 活体验证',
        reverification: '面部已扫描\n已经过 3D 活体验证',
        livenessPriorToIdScan: '面部已扫描\n已经过 3D 活体验证',
        livenessAndOfficialIdPhoto: '已经过 3D 活体验证',
      },
    },
    idScan: {
      unsuccess: '身份证件照片\n与用户面部\n不匹配',
      skipOrErrorNfc: '身份证数据\n已上传',
      additionalReviewTag: '需要人工审核\n请参见 IDV 控制面板',
      uploadFrontSide: {
        uploadStarted: '正在上传\n加密\n身份证件扫描',
        stillUploading: '仍在上传中……\n连接速度较慢',
        uploadCompleteAwaitingResponse: 'ID 图片\n已上传',
        uploadCompleteAwaitingProcessing: '正在处理\n身份证扫描件',
      },
      uploadBackSide: {
        uploadStarted: '正在上传\n加密的身份证背面',
        stillUploading: '仍在上传中……\n连接速度较慢',
        uploadCompleteAwaitingResponse: 'ID 图片\n已上传',
        uploadCompleteAwaitingProcessing: '正在处理\n身份证背面',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: '正在保存\n您确认的信息',
        stillUploading: '仍在上传中……\n连接速度较慢',
        uploadCompleteAwaitingResponse: '信息已保存',
        uploadCompleteAwaitingProcessing: '处理中',
      },
      uploadNfc: {
        uploadStarted: '正在上传加密的\nNFC 详细信息',
        stillUploading: '仍在上传中……\n连接速度较慢',
        uploadCompleteAwaitingResponse: 'NFC 芯片信息\n已上传',
        uploadCompleteAwaitingProcessing: '正在处理\nNFC 详细信息',
      },
      uploadSkippedNfc: {
        uploadStarted: '正在上传\n加密\n ID数据',
        stillUploading: '仍在上传中……\n连接速度较慢',
        uploadCompleteAwaitingResponse: '上传完成',
        uploadCompleteAwaitingProcessing: '正在处理\n身份证数据',
      },
      success: {
        frontSide: '带照片的 ID \n已扫描',
        frontSideBackNext: '身份证件正面\n已扫描',
        frontSideNfcNext: '身份证件正面\n已扫描',
        backSide: '带照片的 ID \n已扫描',
        backSideNfcNext: '身份证件背面\n已扫描',
        passport: '护照已扫描',
        passportNfcNext: '护照已扫描',
        userConfirmation: 'ID 扫描\n完成',
        nfc: 'NFC 芯片信息\n已上传',
        additionalReview: 'ID 扫描\n完成',
      },
      retry: {
        faceDidNotMatch: '面部匹配程度\n不够',
        idNotFullyVisible: '身份证件\n并非完全可见',
        ocrResultsNotGoodEnough: '身份证件文本不可读',
        idTypeNotSupported: '此身份证不支持\n，请使用其他身份证',
        barcodeNotRead: '扫描条形码失败\n请重试',
      },
    },
  },
  retry: {
    header: '再试一次',
    subHeader: '我们需要更清晰的视频自拍',
    yourImageLabel: '您的自拍',
    idealImageLabel: '理想的姿势',
    instruction: {
      primary: '表情自然，不要微笑',
      secondary: '请使用理想的姿势和光线',
      tertiary: '过于模糊，请清理摄像头',
    },
    officialIdPhoto: {
      header: '再试一次',
      subHeader: '我们需要更高质量的图像',
      instruction:
        '请在室内光线条件下拍摄，\n确保眼镜无反光、不面带微笑，且未佩戴任何头饰。',
      yourImageLabel: '您的照片',
      idealImageLabel: '符合要求的面部',
    },
  },
};

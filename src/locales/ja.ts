import type { Locale } from '..';

/**
 * @description The Japanese locale for the Aziface SDK.
 */
export const ja: Required<Locale> = {
  accessibility: {
    cancelButton: 'キャンセル',
    torchButton: 'ライトの切り替え',
    tapGuidance:
      '画面のどこかをダブルタップすると、顔の位置合わせガイダンスが表示されます。',
  },
  accessibilityFeedback: {
    movePhoneAway: '顔が近すぎます',
    movePhoneCloser: '顔が遠すぎます',
    holdDeviceToEyeLevel: 'デバイスを目の高さで保ってください',
    face: {
      tooFarLeft: '顔が左に寄りすぎです',
      tooFarRight: '顔が右に寄りすぎです',
      tooLow: '顔が低すぎます',
      tooHigh: '顔が高すぎます',
      rotatedTooFarLeft: '顔が左に回転しすぎです',
      rotatedTooFarRight: '顔が右に回転しすぎです',
      pointingTooFarLeft: '顔が左に向きすぎです',
      pointingTooFarRight: '顔が右に向きすぎです',
      notOnCamera: '顔がカメラに向いていないか、遠すぎます',
    },
  },
  action: {
    ok: 'OK',
    imReady: '準備完了',
    tryAgain: 'もう一度やり直す',
    continue: '続行',
    takePhoto: '写真を撮る',
    retakePhoto: '再試行',
    acceptPhoto: '承諾する',
    confirm: '情報を確認する',
    scanNfc: 'eパスポートをスキャン',
    scanNfcCard: 'IDカードをスキャン',
    skipNfc: 'このステップをスキップ',
  },
  camera: {
    initializingCamera: 'カメラフィードの保護',
    permission: {
      header: 'カメラを有効にしてください',
      enroll: '下のボタンをクリックして自撮りカメラを有効にしてください。',
      auth: 'カメラが無効になっています。下のボタンをタップして設定を変更してください。',
      enableCamera: 'カメラを有効にする',
      launchSettings: '設定を起動',
    },
  },
  feedback: {
    centerFace: '顔を中央に合わせてください',
    holdSteady: '動かないでください',
    useEvenLighting: '顔を均等に照らしてください',
    face: {
      notFound: '顔を枠内に収めてください',
      notLookingStraightAhead: 'まっすぐ前を向いてください',
      notUpright: '頭をまっすぐに保ってください',
    },
    move: {
      phoneAway: '離れてください',
      phoneCloser: '近づいてください',
      phoneToEyeLevel: 'カメラを目の高さに合わせてください',
    },
  },
  idScan: {
    typeSelectionHeader: '身分証明書の\nスキャンの準備をしてください',
    additionalReview: '追加の確認が\n必要です',
    capture: {
      tapToFocus: '画面をタップしてフォーカスを合わせてください',
      holdSteady: '動かないでください',
      idFrontInstruction: 'IDの表面をスキャンします',
      idBackInstruction: 'IDの裏面をスキャンします',
    },
    review: {
      idFrontInstruction: '写真が鮮明で判読可能であることを確認します',
      idBackInstruction: '写真が判読可能であることを確認します',
    },
    ocr: {
      confirmationMainHeader: '確認してください',
      confirmationScroll: 'スクロールダウンしてください',
    },
    nfc: {
      statusDisabled: '続行するには\nデバイスのNFC\nをオンにします',
      statusReady: 'eパスポートチップを\nスキャンする準備をしてください',
      cardStatusReady: 'IDカードチップを\nスキャンする準備をしてください',
      statusStarting:
        '携帯電話を\neパスポートの裏面\nにかざして、NFCチップをスキャンします',
      cardStatusStarting:
        '携帯電話をIDカードにかざして\nNFCチップをスキャンしてください',
      statusScanning: '動かないでください\nNFCチップをスキャン中です',
      statusWeakConnection: 'もう一度スキャンを\nやってみましょう',
      statusFinishedWithSuccess: 'NFCチップ\nスキャン済み',
      statusFinishedWithError: 'eパスポートチップが\n読み取れません',
      cardStatusFinishedWithError: 'IDカードチップが\n読み取れません',
      statusSkipped: 'NFCスキャン\nが実行されませんでした',
    },
    feedback: {
      flipIdToBack: '裏面のために\n身分証明書を裏返してください',
      flipIdToFront: '身分証明書の表面を撮影してください',
    },
  },
  instructions: {
    header: {
      primary: '自撮り動画の',
      secondary: '準備をしてください',
    },
    message: {
      primary: '顔を楕円形の枠内に収めてください',
      secondary: '「準備完了」を押して、もっと近づいてください',
    },
  },
  presession: {
    orientation: 'デバイスを\n回転してください',
    frameYourFace: '顔を楕円形の枠内に収めてください',
    positionFaceStraightInOval: 'まっすぐ前を向いてください',
    removeDarkGlasses: '濃い色のメガネは外してください',
    neutralExpression: '笑顔のない自然な表情をしてください',
    conditionsTooBright: '環境が明るすぎます',
    brightenYourEnvironment: '環境を明るくしてください',
  },
  result: {
    nfcUpload: '暗号化された\nNFCの詳細を\nアップロード中です',
    sessionAbort: '重大なエラー\nセッション終了',
    faceScan: {
      upload: '暗号化された\n3Dフェイススキャンを\nアップロード中です',
      uploadStillUploading: 'まだアップロード中...\n接続が遅い',
      success3d: {
        enrollment: '顔スキャン完了\n3D生体判定異常なし',
        reverification: '3D生体判定異常なし\n顔認証完了',
        livenessPriorToIdScan: '顔スキャン完了\n3D生体判定異常なし',
        livenessAndOfficialIdPhoto: '3D生体判定異常なし',
      },
    },
    idScan: {
      unsuccess: '証明写真が\nユーザーの顔と\n一致しませんでした',
      skipOrErrorNfc: 'IDデータが\nアップロードされました',
      additionalReviewTag:
        'マニュアルレビューが必要です\nIDVダッシュボードを確認してください',
      uploadFrontSide: {
        uploadStarted:
          '暗号化された\n身分証明書のスキャンを\nアップロード中です',
        stillUploading: 'まだアップロード中...\n接続が遅い',
        uploadCompleteAwaitingResponse: 'ID画像\nアップロード済み',
        uploadCompleteAwaitingProcessing: '処理中\nIDスキャン',
      },
      uploadBackSide: {
        uploadStarted: 'アップロード中\n暗号化されました\nIDの裏面',
        stillUploading: 'まだアップロード中...\n接続が遅い',
        uploadCompleteAwaitingResponse: 'ID画像\nアップロード済み',
        uploadCompleteAwaitingProcessing: '処理中\nIDの裏面',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: '保存中\n確認済みの情報',
        stillUploading: 'まだアップロード中...\n接続が遅い',
        uploadCompleteAwaitingResponse: '情報が保存されました',
        uploadCompleteAwaitingProcessing: '処理中',
      },
      uploadNfc: {
        uploadStarted: '暗号化されたものをアップロード中\nNFCの詳細',
        stillUploading: 'まだアップロード中...\n接続が遅い',
        uploadCompleteAwaitingResponse: 'NFCチップ情報\nアップロード済み',
        uploadCompleteAwaitingProcessing: '処理中\nNFCの詳細',
      },
      uploadSkippedNfc: {
        uploadStarted: '暗号化された\nIDデータをアップロード中',
        stillUploading: 'まだアップロード中...\n接続が遅い',
        uploadCompleteAwaitingResponse: 'アップロード完了',
        uploadCompleteAwaitingProcessing: 'IDデータを\n処理中',
      },
      success: {
        frontSide: '写真付きID\nスキャン済み',
        frontSideBackNext: '身分証明書の表面を\nスキャンしました',
        frontSideNfcNext: '身分証明書の表面を\nスキャンしました',
        backSide: '写真付きID\nスキャン済み',
        backSideNfcNext: '身分証明書の裏面を\nスキャンしました',
        passport: 'パスポートがスキャンされました',
        passportNfcNext: 'パスポートがスキャンされました',
        userConfirmation: 'IDスキャン\n完了',
        nfc: 'NFCチップ情報\nアップロード済み',
        additionalReview: 'IDスキャン\n完了',
      },
      retry: {
        faceDidNotMatch: '顔が十分に\n一致しませんでした',
        idNotFullyVisible: '身分証明書が完全に見えません',
        ocrResultsNotGoodEnough: '身分証明書の文字がが判読できません',
        idTypeNotSupported:
          'IDはサポートされていません\n別のIDを使用してください',
        barcodeNotRead:
          'バーコードのスキャンに失敗しました\nもう一度やり直してください',
      },
    },
  },
  retry: {
    header: 'もう一度やり直しましょう',
    subHeader: 'より鮮明な自撮り動画が必要です',
    yourImageLabel: 'あなたの自撮り',
    idealImageLabel: '理想のポーズ',
    instruction: {
      primary: '笑顔のない自然な表情をしてください',
      secondary: '適切なポーズと照明を使用してください',
      tertiary: 'ぼやけすぎる時は綺麗なカメラを使用してください',
    },
    officialIdPhoto: {
      header: 'もう一度やり直しましょう',
      subHeader: 'もっと高品質の画像が必要です',
      instruction:
        '室内照明の元でお試しください。\nメガネの反射、笑顔、帽子着用は禁止です',
      yourImageLabel: 'あなたの画像',
      idealImageLabel: 'フェイスコンフォーム',
    },
  },
};

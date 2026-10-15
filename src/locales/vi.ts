import type { Locale } from '..';

/**
 * @description The Vietnamese locale for the Aziface SDK.
 */
export const vi: Required<Locale> = {
  accessibility: {
    cancelButton: 'Hủy',
    torchButton: 'Điều chỉnh Ánh sáng',
    tapGuidance:
      'Nhấn hai lần ở bất cứ đâu trên màn hình để xem hướng dẫn chỉnh mặt.',
  },
  accessibilityFeedback: {
    movePhoneAway: 'Mặt Quá Gần',
    movePhoneCloser: 'Mặt Quá Xa',
    holdDeviceToEyeLevel: 'Dịch Máy Ảnh Tới Ngang Tầm Mắt',
    face: {
      tooFarLeft: 'Mặt Quá Xa Sang Trái',
      tooFarRight: 'Mặt Quá Xa Sang Phải',
      tooLow: 'Mặt Quá Thấp',
      tooHigh: 'Mặt Quá Cao',
      rotatedTooFarLeft: 'Mặt Quá Nghiêng Sang Trái',
      rotatedTooFarRight: 'Mặt Quá Nghiêng Sang Phải',
      pointingTooFarLeft: 'Mặt Quá Hếch Sang Trái',
      pointingTooFarRight: 'Mặt Quá Hếch Sang Phải',
      notOnCamera: 'Mặt Không Trên Máy Ảnh hoặc Quá Xa',
    },
  },
  action: {
    ok: 'OK',
    imReady: 'TÔI ĐÃ SẴN SÀNG',
    tryAgain: 'THỬ LẦN NỮA',
    continue: 'TIẾP TỤC',
    takePhoto: 'CHỤP ẢNH',
    retakePhoto: 'THỬ LẠI',
    acceptPhoto: 'CHẤP NHẬN',
    confirm: 'XÁC NHẬN THÔNG TIN',
    scanNfc: 'QUÉT HỘ CHIẾU ĐIỆN TỬ',
    scanNfcCard: 'QUÉT THẺ NHẬN DIỆN',
    skipNfc: 'BỎ QUA BƯỚC NÀY',
  },
  camera: {
    initializingCamera: 'Đang Bảo mật Hình ảnh Máy ảnh',
    permission: {
      header: 'Bật Máy ảnh',
      enroll: 'Hãy nhấn nút dưới đây để bật máy ảnh selfie của bạn.',
      auth: 'Máy ảnh của bạn đang tắt. Gõ dưới đây để thay đổi cài đặt của bạn.',
      enableCamera: 'BẬT MÁY ẢNH',
      launchSettings: 'KHỞI ĐỘNG CÀI ĐẶT',
    },
  },
  feedback: {
    centerFace: 'Đặt Mặt bạn vào Giữa',
    holdSteady: 'Giữ Vững',
    useEvenLighting: 'Chỉnh Ánh sáng Trên Mặt cho Đều hơn',
    face: {
      notFound: 'Chỉnh Khung Mặt Bạn',
      notLookingStraightAhead: 'Nhìn Thẳng Về Phía Trước',
      notUpright: 'Giữ Đầu bạn Cho Thẳng',
    },
    move: {
      phoneAway: 'Tránh Ra',
      phoneCloser: 'Dịch Lại Gần',
      phoneToEyeLevel: 'Dịch Máy Ảnh tới Ngang Tầm Mắt',
    },
  },
  idScan: {
    typeSelectionHeader: 'Chuẩn bị Quét\n Tài liệu Nhận diện của Bạn',
    additionalReview: 'Cần\nKiểm tra Thêm',
    capture: {
      tapToFocus: 'Gõ Màn hình để Tập trung',
      holdSteady: 'Hãy Giữ Vững',
      idFrontInstruction: 'Quét mặt trước ID',
      idBackInstruction: 'Quét mặt sau ID',
    },
    review: {
      idFrontInstruction: 'Xác nhận ảnh sắc nét và rõ ràng',
      idBackInstruction: 'Xác nhận ảnh rõ ràng',
    },
    ocr: {
      confirmationMainHeader: 'Kiểm tra & Xác nhận',
      confirmationScroll: 'Kéo Xuống',
    },
    nfc: {
      statusDisabled: 'Bật\nNFC của thiết bị\nđể tiếp tục',
      statusReady: 'Chuẩn bị Quét\nChip Hộ chiếu điện tử của Bạn',
      cardStatusReady: 'Chuẩn bị Quét\nChip Thẻ Nhận diện của Bạn',
      statusStarting:
        'Giữ điện thoại ở\nmặt sau của hộ chiếu điện tử\nđể quét chip NFC',
      cardStatusStarting: 'Giữ Điện thoại trên Thẻ Nhận diện\nđể Quét Chip NFC',
      statusScanning: 'Giữ Vững,\nĐang Quét Chip NFC',
      statusWeakConnection: 'Hãy Thử\nQuét Lại Một Lần Nữa',
      statusFinishedWithSuccess: 'Đã quét\nVi mạch NFC',
      statusFinishedWithError: 'Không đọc được\nChip Hộ chiếu điện tử',
      cardStatusFinishedWithError: 'Không đọc được\nChip Thẻ Nhận diện',
      statusSkipped: 'Quét NFC\nkhông được thực hiện',
    },
    feedback: {
      flipIdToBack: 'Lật Thẻ Nhận diện\nSang Mặt Sau',
      flipIdToFront: 'Hiển thị mặt trước căn cước',
    },
  },
  instructions: {
    header: {
      primary: 'Chuẩn bị Sẵn sàng Cho',
      secondary: 'Video Selfie của Bạn',
    },
    message: {
      primary: 'Chỉnh Mặt Bạn vào Khung Bầu dục',
      secondary: 'Nhấn Tôi đã Sẵn sàng & Dịch Lại Gần Hơn',
    },
  },
  presession: {
    orientation: 'Vui lòng\nxoay thiết bị',
    frameYourFace: 'Chỉnh mặt Bạn vào Khung Bầu dục',
    positionFaceStraightInOval: 'Nhìn Thẳng Về Phía Trước',
    removeDarkGlasses: 'Tháo Kính Tối Màu',
    neutralExpression: 'Nét Mặt Trung Lập, Không Cười',
    conditionsTooBright: 'Điều kiện Quá Sáng',
    brightenYourEnvironment: 'Làm Sáng Môi trường Quanh Bạn',
  },
  result: {
    nfcUpload: 'Đang Tải lên\nĐã Mã hóa\nChi tiết NFC',
    sessionAbort: 'Lỗi nghiêm trọng\nPhiên đã kết thúc',
    faceScan: {
      upload: 'Đang Tải lên\nĐã Mã hóa\n QuétMặt 3D',
      uploadStillUploading: 'Đang tải lên...\nKết nối chậm',
      success3d: {
        enrollment: 'Đã quét mặt\nĐã chứng minh sống động 3D',
        reverification: 'Đã chứng minh sống động 3D\nĐã xác minh mặt',
        livenessPriorToIdScan: 'Đã quét mặt\nĐã chứng minh sống động 3D',
        livenessAndOfficialIdPhoto: 'Đã chứng minh sống động 3D',
      },
    },
    idScan: {
      unsuccess: 'Ảnh Nhận diện\nKhông Khớp\nMặt Người Dùng',
      skipOrErrorNfc: 'Dữ liệu ID\nđã được tải lên',
      additionalReviewTag:
        'Cần xem xét thủ công\nVui lòng xem Bảng điều khiển IDV',
      uploadFrontSide: {
        uploadStarted: 'Đang Tải lên\nĐã Mã hóa\nQuét Thẻ Nhận diện',
        stillUploading: 'Đang tải lên...\nKết nối chậm',
        uploadCompleteAwaitingResponse: 'Đã tải lên\nẢnh ID',
        uploadCompleteAwaitingProcessing: 'Đang xử lý\nQuét ID',
      },
      uploadBackSide: {
        uploadStarted: 'Đang tải lên\nMặt sau của ID\nĐược mã hóa',
        stillUploading: 'Đang tải lên...\nKết nối chậm',
        uploadCompleteAwaitingResponse: 'Đã tải lên\nẢnh ID',
        uploadCompleteAwaitingProcessing: 'Đang xử lý\nMặt sau của ID',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: 'Đang lưu\nThông tin đã xác nhận của bạn',
        stillUploading: 'Đang tải lên...\nKết nối chậm',
        uploadCompleteAwaitingResponse: 'Thông tin đã lưu',
        uploadCompleteAwaitingProcessing: 'Đang xử lý',
      },
      uploadNfc: {
        uploadStarted: 'Đang tải lên\nChi tiết NFC\nĐược mã hóa',
        stillUploading: 'Đang tải lên...\nKết nối chậm',
        uploadCompleteAwaitingResponse: 'Đã tải lên\nthông tin vi mạch NFC',
        uploadCompleteAwaitingProcessing: 'Đang xử lý\nChi tiết NFC',
      },
      uploadSkippedNfc: {
        uploadStarted: 'Đang tải lên\ndữ liệu ID\nđược mã hóa',
        stillUploading: 'Đang tải lên...\nKết nối chậm',
        uploadCompleteAwaitingResponse: 'Hoàn tất tải lên',
        uploadCompleteAwaitingProcessing: 'Đang xử lý\ndữ liệu ID',
      },
      success: {
        frontSide: 'Đã quét\nẢnh ID',
        frontSideBackNext: 'Đã Quét\nMặt trước Thẻ Nhận diện',
        frontSideNfcNext: 'Đã Quét\nMặt trước Thẻ Nhận diện',
        backSide: 'Đã quét\nẢnh ID',
        backSideNfcNext: 'Đã Quét\nMặt sau Thẻ Nhận diện',
        passport: 'Đã Quét Hộ chiếu',
        passportNfcNext: 'Đã Quét Hộ chiếu',
        userConfirmation: 'Đã hoàn tất\nquá trình quét thẻ ID',
        nfc: 'Đã tải lên\nthông tin vi mạch NFC',
        additionalReview: 'Đã hoàn tất\nquá trình quét thẻ ID',
      },
      retry: {
        faceDidNotMatch: 'Mặt Không Khớp\n Đủ Nhiều',
        idNotFullyVisible: 'Không Thấy Đầy đủ\n Tài liệu Nhận diện',
        ocrResultsNotGoodEnough: 'Không Đọc được Văn bản Nhận diện',
        idTypeNotSupported: 'ID không được hỗ trợ\nVui lòng sử dụng ID khác',
        barcodeNotRead: 'Không Quét được Mã vạch\nHãy Thử Lại',
      },
    },
  },
  retry: {
    header: 'Hãy Thử Lại Một Lần nữa',
    subHeader: 'Chúng tôi Cần Một Video Selfie Rõ hơn',
    yourImageLabel: 'Selfie của Bạn',
    idealImageLabel: 'Tư thế Lý tưởng',
    instruction: {
      primary: 'Nét mặt Trung lập, Không Cười',
      secondary: 'Sử dụng Tư thế & Ánh sáng Lý tưởng',
      tertiary: 'Quá Nhòe, Lau Máy ảnh',
    },
    officialIdPhoto: {
      header: 'Hãy Thử Lại Một Lần nữa',
      subHeader: 'Cần ảnh chất lượng cao hơn',
      instruction:
        'Dùng ánh sáng trong nhà,\ntránh lóa kính, không cười, không đội mũ nón',
      yourImageLabel: 'Ảnh của bạn',
      idealImageLabel: 'Khuôn mặt đạt chuẩn',
    },
  },
};

import type { Locale } from '..';

/**
 * @description The Greek locale for the Aziface SDK.
 */
export const el: Required<Locale> = {
  accessibility: {
    cancelButton: 'Ακύρωση',
    torchButton: 'Εναλλαγή φωτός',
    tapGuidance:
      'Πατήστε δύο φορές οπουδήποτε στην οθόνη για καθοδήγηση ευθυγράμμισης προσώπου.',
  },
  accessibilityFeedback: {
    movePhoneAway: 'Πρόσωπο πολύ κοντά',
    movePhoneCloser: 'Πρόσωπο πολύ μακριά',
    holdDeviceToEyeLevel: 'Κρατήστε τη συσκευή στο ύψος των ματιών',
    face: {
      tooFarLeft: 'Πρόσωπο πολύ αριστερά',
      tooFarRight: 'Πρόσωπο πολύ δεξιά',
      tooLow: 'Πρόσωπο πολύ χαμηλά',
      tooHigh: 'Πρόσωπο πολύ ψηλά',
      rotatedTooFarLeft: 'Το πρόσωπο περιστράφηκε πολύ αριστερά',
      rotatedTooFarRight: 'Το πρόσωπο περιστράφηκε πολύ δεξιά',
      pointingTooFarLeft: 'Το πρόσωπο κοιτάζει πολύ αριστερά',
      pointingTooFarRight: 'Το πρόσωπο κοιτάζει πολύ δεξιά',
      notOnCamera: 'Πρόσωπο όχι στην κάμερα ή πολύ μακριά',
    },
  },
  action: {
    ok: 'ΟΚ',
    imReady: 'ΕΙΜΑΙ ΕΤΟΙΜΟΣ',
    tryAgain: 'ΠΡΟΣΠΑΘΗΣΤΕ ΞΑΝΑ',
    continue: 'ΣΥΝΕΧΕΙΑ',
    takePhoto: 'ΛΗΨΗ ΦΩΤΟΓΡΑΦΙΑΣ',
    retakePhoto: 'ΕΠΑΝΑΛΗΨΗ',
    acceptPhoto: 'ΑΠΟΔΟΧΗ',
    confirm: 'ΕΠΙΒΕΒΑΙΩΣΗ ΠΛΗΡΟΦΟΡΙΩΝ',
    scanNfc: 'ΣΑΡΩΣΗ ePASSPORT',
    scanNfcCard: 'ΣΑΡΩΣΗ ΤΑΥΤΟΤΗΤΑΣ',
    skipNfc: 'ΠΑΡΑΛΕΙΨΗ ΒΗΜΑΤΟΣ',
  },
  camera: {
    initializingCamera: 'Ασφάλιση τροφοδοσίας κάμερας',
    permission: {
      header: 'Ενεργοποίηση κάμερας',
      enroll:
        'Πατήστε το παρακάτω κουμπί για να ενεργοποιήσετε την κάμερα σέλφι.',
      auth: 'Η κάμερά σας είναι απενεργοποιημένη. Πατήστε παρακάτω για να επεξεργαστείτε τις ρυθμίσεις σας.',
      enableCamera: 'ΕΝΕΡΓΟΠΟΙΗΣΗ ΚΑΜΕΡΑΣ',
      launchSettings: 'ΡΥΘΜΙΣΕΙΣ ΕΚΚΙΝΗΣΗΣ',
    },
  },
  feedback: {
    centerFace: 'Κεντράρετε το πρόσωπό σας',
    holdSteady: 'Κρατήστε σταθερά',
    useEvenLighting: 'Φωτίστε το πρόσωπο πιο ομοιόμορφα',
    face: {
      notFound: 'Πλαισιώστε το πρόσωπό σας',
      notLookingStraightAhead: 'Κοίταξε ευθεία μπροστά',
      notUpright: 'Κρατήστε το κεφάλι σας ίσια',
    },
    move: {
      phoneAway: 'Απομακρυνθείτε',
      phoneCloser: 'Πλησιάστε',
      phoneToEyeLevel: 'Μετακινήστε την κάμερα στο επίπεδο των ματιών',
    },
  },
  idScan: {
    typeSelectionHeader: 'Προετοιμάστε για σάρωση\nτην Ταυτότητά σας',
    additionalReview: 'Πρόσθετος έλεγχος\nΑπαιτείται',
    capture: {
      tapToFocus: 'Αγγίξτε την οθόνη για εστίαση',
      holdSteady: 'Κρατήστε σταθερά',
      idFrontInstruction: 'Σάρωση ταυτότητας μπροστά',
      idBackInstruction: 'Σάρωση ταυτότητας πίσω',
    },
    review: {
      idFrontInstruction: 'Είναι η φωτό ευκρινής;',
      idBackInstruction: 'Είναι η φωτό ευανάγνωστη;',
    },
    ocr: {
      confirmationMainHeader: 'Έλεγχος & Επιβεβαίωση',
      confirmationScroll: 'Μετακίνηση κάτω',
    },
    nfc: {
      statusDisabled:
        'Ενεργοποιήστε\nτο NFC της συσκευής σας\nγια να συνεχίσετε',
      statusReady: 'Ετοιμαστείτε για σάρωση\nΤο τσιπ του ePassport σας',
      cardStatusReady: 'Ετοιμαστείτε για σάρωση\nΤο τσιπ της ταυτότητάς σας',
      statusStarting:
        'Κρατήστε το τηλέφωνο στο\nπίσω μέρος του ePassport\nγια να σαρώσετε το τσιπ του NFC',
      cardStatusStarting:
        'Κρατήστε το τηλέφωνο στην ταυτότητα\nγια σάρωση τσιπ NFC',
      statusScanning: 'Κρατήστε σταθερά,\nΣάρωση τσιπ NFC...',
      statusWeakConnection: 'Ας δοκιμάσουμε\nΆλλη μια σάρωση',
      statusFinishedWithSuccess: 'Το Τσιπ NFC\nΣαρώθηκε',
      statusFinishedWithError: 'Τσιπ ePassport\nΜη αναγνώσιμο',
      cardStatusFinishedWithError: 'Τσιπ ταυτότητας\nΜη αναγνώσιμο',
      statusSkipped: 'Η σάρωση του NFC\nδεν πραγματοποιήθηκε',
    },
    feedback: {
      flipIdToBack: 'Πίσω όψη ταυτότητας',
      flipIdToFront: 'Μπροστινή όψη ταυτότητας',
    },
  },
  instructions: {
    header: {
      primary: 'Ετοιμαστείτε για',
      secondary: 'το βίντεο-σέλφι σας',
    },
    message: {
      primary: 'Προσαρμόστε το πρόσωπό σας στο οβάλ',
      secondary: 'Πιέστε ΕΙΜΑΙ ΕΤΟΙΜΟΣ και πλησιάστε',
    },
  },
  presession: {
    orientation: 'Περιστρέψτε\nτη συσκευή σας',
    frameYourFace: 'Πλαισιώστε το πρόσωπό σας στο οβάλ',
    positionFaceStraightInOval: 'Κοιτάξτε ευθεία μπροστά',
    removeDarkGlasses: 'Αφαιρέστε τα σκούρα γυαλιά',
    neutralExpression: 'Ουδέτερη έκφραση, χωρίς χαμόγελο',
    conditionsTooBright: 'Πολύ φωτεινές συνθήκες',
    brightenYourEnvironment: 'Φωτίστε το περιβάλλον σας',
  },
  result: {
    nfcUpload: 'Μεταφόρτωση\nΚρυπτογραφημένες\nΛεπτομέρειες NFC',
    sessionAbort: 'Κρίσιμο σφάλμα\nΗ συνεδρία έληξε',
    faceScan: {
      upload: 'Μεταφόρτωση\nΚρυπτογραφημένο\n3D FaceScan',
      uploadStillUploading: 'Ακόμα Ανεβαίνει...\nΑργή Σύνδεση',
      success3d: {
        enrollment: 'Το πρόσωπο σαρώθηκε\nΑποδεδειγμένη ζωντάνια 3D',
        reverification: 'Αποδεδειγμένη ζωντάνια 3D\nΤο πρόσωπο επαληθεύτηκε',
        livenessPriorToIdScan: 'Το πρόσωπο σαρώθηκε\nΑποδεδειγμένη ζωντάνια 3D',
        livenessAndOfficialIdPhoto: 'Αποδεδειγμένη ζωντάνια 3D',
      },
    },
    idScan: {
      unsuccess:
        'Η Φωτογραφία ταυτότητας\nΔεν ταιριάζει\nΣτο πρόσωπο του χρήστη',
      skipOrErrorNfc: 'Τα δεδομένα ταυτότητας\nμεταφορτώθηκαν',
      additionalReviewTag:
        'Απαιτείται Μη Αυτόματος έλεγχος\n Παρακαλούμε Δείτε τον Πίνακα Ελέγχου IDV',
      uploadFrontSide: {
        uploadStarted: 'Μεταφόρτωση\nΚρυπτογραφημένη\nΣάρωση ID',
        stillUploading: 'Ακόμα Ανεβαίνει...\nΑργή Σύνδεση',
        uploadCompleteAwaitingResponse:
          'Η Εικόνα της Ταυτότητας\nΜεταφορτώθηκε',
        uploadCompleteAwaitingProcessing: 'Επεξεργασία\nΣάρωσης Ταυτότητας',
      },
      uploadBackSide: {
        uploadStarted: 'Ανεβαίνει\nΚρυπτογραφημένο\nΠίσω Μέρος Ταυτότητας',
        stillUploading: 'Ακόμα Ανεβαίνει...\nΑργή Σύνδεση',
        uploadCompleteAwaitingResponse:
          'Η Εικόνα της Ταυτότητας\nΜεταφορτώθηκε',
        uploadCompleteAwaitingProcessing: 'Επεξεργασία\nΠίσω Μέρους Ταυτότητας',
      },
      uploadUserConfirmedInfo: {
        uploadStarted: 'Σώζονται\nΟι Επιβεβαιωμένες σας Πληροφορίες',
        stillUploading: 'Ακόμα Ανεβαίνει...\nΑργή Σύνδεση',
        uploadCompleteAwaitingResponse: 'Οι Πληροφορίες Σώθηκαν',
        uploadCompleteAwaitingProcessing: 'Επεξεργασία',
      },
      uploadNfc: {
        uploadStarted: 'Ανεβαίνουν Κρυπτογραφημένες\nΛεπτομέρειες NFC',
        stillUploading: 'Ακόμα Ανεβαίνει...\nΑργή Σύνδεση',
        uploadCompleteAwaitingResponse:
          'Οι Πληροφορίες του Τσιπ NFC\n  Μεταφορτώθηκαν',
        uploadCompleteAwaitingProcessing: 'Επεξεργασία\nΛεπτομερειών NFC',
      },
      uploadSkippedNfc: {
        uploadStarted: 'Μεταφόρτωση\nΚρυπτογραφημένων\n Δεδομένων Ταυτότητας',
        stillUploading: 'Ακόμα Ανεβαίνει...\nΑργή Σύνδεση',
        uploadCompleteAwaitingResponse: 'Ολοκλήρωση Μεταφόρτωσης',
        uploadCompleteAwaitingProcessing:
          'Γίνεται επεξεργασία\nδεδομένων ταυτότητας',
      },
      success: {
        frontSide: 'Η Φωτογραφία της Ταυτότητας \nΣαρώθηκε',
        frontSideBackNext: 'Μπροστινή όψη ταυτότητας\nΣαρώθηκε',
        frontSideNfcNext: 'Μπροστινή όψη ταυτότητας\nΣαρώθηκε',
        backSide: 'Η Φωτογραφία της Ταυτότητας \nΣαρώθηκε',
        backSideNfcNext: 'Πίσω όψη ταυτότητας\nΣαρώθηκε',
        passport: 'Η σάρωση διαβατηρίου ολοκληρώθηκε',
        passportNfcNext: 'Η σάρωση διαβατηρίου ολοκληρώθηκε',
        userConfirmation: 'Η Σάρωση της Ταυτότητας \nΟλοκληρώθηκε',
        nfc: 'Οι Πληροφορίες του Τσιπ NFC\n  Μεταφορτώθηκαν',
        additionalReview: 'Η Σάρωση της Ταυτότητας \nΟλοκληρώθηκε',
      },
      retry: {
        faceDidNotMatch: 'Το πρόσωπο δεν ταίριαζε\nΑρκετά',
        idNotFullyVisible: 'Έγγραφο ταυτότητας\nΔεν είναι πλήρως ορατό',
        ocrResultsNotGoodEnough: 'Κείμενο Ταυτότητας Μη Ευανάγνωστο',
        idTypeNotSupported:
          'Η ταυτότητα δεν υποστηρίζεται\nΧρησιμοποιήστε διαφορετική ταυτότητα',
        barcodeNotRead: 'Η σάρωση του γραμμοκώδικα απέτυχε\nΠροσπαθήστε ξανά',
      },
    },
  },
  retry: {
    header: 'Ας το δοκιμάσουμε ξανά',
    subHeader: 'Χρειαζόμαστε ένα πιο καθαρό βίντεο-σέλφι',
    yourImageLabel: 'Η σέλφι σας',
    idealImageLabel: 'Ιδανική πόζα',
    instruction: {
      primary: 'Ουδέτερη έκφραση, χωρίς χαμόγελο',
      secondary: 'Χρήση Ιδανικής Πόζας και Φωτισμού',
      tertiary: 'Είναι πολύ θολό, καθαρίστε την κάμερα',
    },
    officialIdPhoto: {
      header: 'Ας το δοκιμάσουμε ξανά',
      subHeader: 'Χρειαζόμαστε καλύτερη ποιότητα',
      instruction:
        'Δοκίμασε σε εσωτερικό χώρο,\nΧωρίς έντονο φως, χαμόγελο και καπέλο',
      yourImageLabel: 'Η εικόνα σου',
      idealImageLabel: 'Συμμορφούμενο πρόσωπο',
    },
  },
};

package com.azifacemobile.strings;

import com.azifacemobile.R;
import com.facebook.react.bridge.ReadableMap;
import com.facetec.sdk.FaceTecSDK;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class DynamicStrings {
  Map<Integer, String> dynamicStrings;
  @Nullable ReadableMap strings;
  @Nullable ReadableMap target;

  public DynamicStrings() {
    this.dynamicStrings = new HashMap<>();
    this.strings = null;
    this.target = null;
  }

  @Nullable
  private String getString(String key) {
    if (this.target == null) return null;

    return this.target.getString(key);
  }

  @Nullable
  private ReadableMap getTarget(String key) {
    if (this.strings == null) return null;

    return this.strings.getMap(key);
  }

  @Nullable
  private ReadableMap getTarget(@Nullable ReadableMap target, String key) {
    if (target == null) return null;

    return target.getMap(key);
  }

  private void accessibility() {
    this.target = this.getTarget("accessibility");

    dynamicStrings.put(R.string.FaceTec_accessibility_cancel_button, this.getString("cancelButton"));
    dynamicStrings.put(R.string.FaceTec_accessibility_torch_button, this.getString("torchButton"));
    dynamicStrings.put(R.string.FaceTec_accessibility_tap_guidance, this.getString("tapGuidance"));
  }

  private void accessibilityFeedback() {
    this.target = this.getTarget("accessibilityFeedback");

    dynamicStrings.put(R.string.FaceTec_accessibility_feedback_move_phone_away, this.getString("movePhoneAway"));
    dynamicStrings.put(R.string.FaceTec_accessibility_feedback_move_phone_closer, this.getString("movePhoneCloser"));
    dynamicStrings.put(R.string.FaceTec_accessibility_feedback_hold_device_to_eye_level, this.getString("holdDeviceToEyeLevel"));

    this.target = this.getTarget(this.target, "face");
    dynamicStrings.put(R.string.FaceTec_accessibility_feedback_face_too_far_left, this.getString("tooFarLeft"));
    dynamicStrings.put(R.string.FaceTec_accessibility_feedback_face_too_far_right, this.getString("tooFarRight"));
    dynamicStrings.put(R.string.FaceTec_accessibility_feedback_face_too_low, this.getString("tooLow"));
    dynamicStrings.put(R.string.FaceTec_accessibility_feedback_face_too_high, this.getString("tooHigh"));
    dynamicStrings.put(R.string.FaceTec_accessibility_feedback_face_rotated_too_far_left, this.getString("rotatedTooFarLeft"));
    dynamicStrings.put(R.string.FaceTec_accessibility_feedback_face_rotated_too_far_right, this.getString("rotatedTooFarRight"));
    dynamicStrings.put(R.string.FaceTec_accessibility_feedback_face_pointing_too_far_left, this.getString("pointingTooFarLeft"));
    dynamicStrings.put(R.string.FaceTec_accessibility_feedback_face_pointing_too_far_right, this.getString("pointingTooFarRight"));
    dynamicStrings.put(R.string.FaceTec_accessibility_feedback_face_not_on_camera, this.getString("notOnCamera"));
  }

  private void action() {
    this.target = this.getTarget("action");

    dynamicStrings.put(R.string.FaceTec_action_ok, this.getString("ok"));
    dynamicStrings.put(R.string.FaceTec_action_im_ready, this.getString("imReady"));
    dynamicStrings.put(R.string.FaceTec_action_try_again, this.getString("tryAgain"));
    dynamicStrings.put(R.string.FaceTec_action_continue, this.getString("continue"));
    dynamicStrings.put(R.string.FaceTec_action_take_photo, this.getString("takePhoto"));
    dynamicStrings.put(R.string.FaceTec_action_retake_photo, this.getString("retakePhoto"));
    dynamicStrings.put(R.string.FaceTec_action_accept_photo, this.getString("acceptPhoto"));
    dynamicStrings.put(R.string.FaceTec_action_confirm, this.getString("confirm"));
    dynamicStrings.put(R.string.FaceTec_action_scan_nfc, this.getString("scanNfc"));
    dynamicStrings.put(R.string.FaceTec_action_scan_nfc_card, this.getString("scanNfcCard"));
    dynamicStrings.put(R.string.FaceTec_action_skip_nfc, this.getString("skipNfc"));
  }

  private void camera() {
    final ReadableMap target = this.getTarget("camera");

    this.target = target;
    dynamicStrings.put(R.string.FaceTec_initializing_camera, this.getString("initializingCamera"));

    this.target = this.getTarget(target, "permission");
    dynamicStrings.put(R.string.FaceTec_camera_permission_header, this.getString("header"));
    dynamicStrings.put(R.string.FaceTec_camera_permission_message_enroll, this.getString("enroll"));
    dynamicStrings.put(R.string.FaceTec_camera_permission_message_auth, this.getString("auth"));
    dynamicStrings.put(R.string.FaceTec_camera_permission_enable_camera, this.getString("enableCamera"));
    dynamicStrings.put(R.string.FaceTec_camera_permission_launch_settings, this.getString("launchSettings"));
  }

  private void feedback() {
    final ReadableMap target = this.getTarget("feedback");

    this.target = target;
    dynamicStrings.put(R.string.FaceTec_feedback_center_face, this.getString("centerFace"));
    dynamicStrings.put(R.string.FaceTec_feedback_hold_steady, this.getString("holdSteady"));
    dynamicStrings.put(R.string.FaceTec_feedback_use_even_lighting, this.getString("useEvenLighting"));

    this.target = this.getTarget(target, "face");
    dynamicStrings.put(R.string.FaceTec_feedback_face_not_found, this.getString("notFound"));
    dynamicStrings.put(R.string.FaceTec_feedback_face_not_looking_straight_ahead, this.getString("notLookingStraightAhead"));
    dynamicStrings.put(R.string.FaceTec_feedback_face_not_upright, this.getString("notUpright"));

    this.target = this.getTarget(target, "move");
    dynamicStrings.put(R.string.FaceTec_feedback_move_phone_away, this.getString("phoneAway"));
    dynamicStrings.put(R.string.FaceTec_feedback_move_phone_closer, this.getString("phoneCloser"));
    dynamicStrings.put(R.string.FaceTec_feedback_move_phone_to_eye_level, this.getString("phoneToEyeLevel"));
  }

  private void idScan() {
    final ReadableMap target = this.getTarget("idScan");

    this.target = target;
    dynamicStrings.put(R.string.FaceTec_idscan_type_selection_header, this.getString("typeSelectionHeader"));
    dynamicStrings.put(R.string.FaceTec_idscan_additional_review_message, this.getString("additionalReview"));

    this.target = this.getTarget(target, "capture");
    dynamicStrings.put(R.string.FaceTec_idscan_capture_tap_to_focus_message, this.getString("tapToFocus"));
    dynamicStrings.put(R.string.FaceTec_idscan_capture_hold_steady_message, this.getString("holdSteady"));
    dynamicStrings.put(R.string.FaceTec_idscan_capture_id_front_instruction_message, this.getString("idFrontInstruction"));
    dynamicStrings.put(R.string.FaceTec_idscan_capture_id_back_instruction_message, this.getString("idBackInstruction"));

    this.target = this.getTarget(target, "review");
    dynamicStrings.put(R.string.FaceTec_idscan_review_id_front_instruction_message, this.getString("idFrontInstruction"));
    dynamicStrings.put(R.string.FaceTec_idscan_review_id_back_instruction_message, this.getString("idBackInstruction"));

    this.target = this.getTarget(target, "ocr");
    dynamicStrings.put(R.string.FaceTec_idscan_ocr_confirmation_main_header, this.getString("confirmationMainHeader"));
    dynamicStrings.put(R.string.FaceTec_idscan_ocr_confirmation_scroll_message, this.getString("confirmationScroll"));

    this.target = this.getTarget(target, "nfc");
    dynamicStrings.put(R.string.FaceTec_idscan_nfc_status_disabled_message, this.getString("statusDisabled"));
    dynamicStrings.put(R.string.FaceTec_idscan_nfc_status_ready_message, this.getString("statusReady"));
    dynamicStrings.put(R.string.FaceTec_idscan_nfc_card_status_ready_message, this.getString("cardStatusReady"));
    dynamicStrings.put(R.string.FaceTec_idscan_nfc_status_starting_message, this.getString("statusStarting"));
    dynamicStrings.put(R.string.FaceTec_idscan_nfc_card_status_starting_message, this.getString("cardStatusStarting"));
    dynamicStrings.put(R.string.FaceTec_idscan_nfc_status_scanning_message, this.getString("statusScanning"));
    dynamicStrings.put(R.string.FaceTec_idscan_nfc_status_weak_connection_message, this.getString("statusWeakConnection"));
    dynamicStrings.put(R.string.FaceTec_idscan_nfc_status_finished_with_success_message, this.getString("statusFinishedWithSuccess"));
    dynamicStrings.put(R.string.FaceTec_idscan_nfc_status_finished_with_error_message, this.getString("statusFinishedWithError"));
    dynamicStrings.put(R.string.FaceTec_idscan_nfc_card_status_finished_with_error_message, this.getString("cardStatusFinishedWithError"));
    dynamicStrings.put(R.string.FaceTec_idscan_nfc_status_skipped_message, this.getString("statusSkipped"));

    this.target = this.getTarget(target, "feedback");
    dynamicStrings.put(R.string.FaceTec_idscan_feedback_flip_id_to_back_message, this.getString("flipIdToBack"));
    dynamicStrings.put(R.string.FaceTec_idscan_feedback_flip_id_to_front_message, this.getString("flipIdToFront"));
  }

  private void instructions() {
    final ReadableMap target = this.getTarget("instructions");

    this.target = this.getTarget(target, "header");
    dynamicStrings.put(R.string.FaceTec_instructions_header_ready_1, this.getString("primary"));
    dynamicStrings.put(R.string.FaceTec_instructions_header_ready_2, this.getString("secondary"));

    this.target = this.getTarget(target, "message");
    dynamicStrings.put(R.string.FaceTec_instructions_message_ready_1, this.getString("primary"));
    dynamicStrings.put(R.string.FaceTec_instructions_message_ready_2, this.getString("secondary"));
  }

  private void presession() {
    this.target = this.getTarget("presession");

    dynamicStrings.put(R.string.FaceTec_orientation_message, this.getString("orientation"));
    dynamicStrings.put(R.string.FaceTec_presession_frame_your_face, this.getString("frameYourFace"));
    dynamicStrings.put(R.string.FaceTec_presession_position_face_straight_in_oval, this.getString("positionFaceStraightInOval"));
    dynamicStrings.put(R.string.FaceTec_presession_remove_dark_glasses, this.getString("removeDarkGlasses"));
    dynamicStrings.put(R.string.FaceTec_presession_neutral_expression, this.getString("neutralExpression"));
    dynamicStrings.put(R.string.FaceTec_presession_conditions_too_bright, this.getString("conditionsTooBright"));
    dynamicStrings.put(R.string.FaceTec_presession_brighten_your_environment, this.getString("brightenYourEnvironment"));
  }

  private void result() {
    final ReadableMap target = this.getTarget("result");

    this.target = target;
    dynamicStrings.put(R.string.FaceTec_result_nfc_upload_message, this.getString("nfcUpload"));
    dynamicStrings.put(R.string.FaceTec_result_session_abort_message, this.getString("sessionAbort"));

    this.target = this.getTarget(target, "faceScan");
    dynamicStrings.put(R.string.FaceTec_result_facescan_upload_message, this.getString("upload"));
    dynamicStrings.put(R.string.FaceTec_result_facescan_upload_message_still_uploading, this.getString("uploadStillUploading"));

    this.target = this.getTarget(this.target, "success3d");
    dynamicStrings.put(R.string.FaceTec_result_facescan_success_3d_enrollment_message, this.getString("enrollment"));
    dynamicStrings.put(R.string.FaceTec_result_facescan_success_3d_3d_reverification_message, this.getString("reverification"));
    dynamicStrings.put(R.string.FaceTec_result_facescan_success_3d_liveness_prior_to_idscan_message, this.getString("livenessPriorToIdScan"));
    dynamicStrings.put(R.string.FaceTec_result_facescan_success_3d_liveness_and_official_id_photo_message, this.getString("livenessAndOfficialIdPhoto"));

    final ReadableMap idScan = this.getTarget(target, "idScan");

    this.target = idScan;
    dynamicStrings.put(R.string.FaceTec_result_idscan_unsuccess_message, this.getString("unsuccess"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_skip_or_error_nfc_message, this.getString("skipOrErrorNfc"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_additional_review_tag_message, this.getString("additionalReviewTag"));

    this.target = this.getTarget(idScan, "uploadFrontSide");
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_front_side_upload_started, this.getString("uploadStarted"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_front_side_still_uploading, this.getString("stillUploading"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_front_side_upload_complete_awaiting_response, this.getString("uploadCompleteAwaitingResponse"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_front_side_upload_complete_awaiting_processing, this.getString("uploadCompleteAwaitingProcessing"));

    this.target = this.getTarget(idScan, "uploadBackSide");
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_back_side_upload_started, this.getString("uploadStarted"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_back_side_still_uploading, this.getString("stillUploading"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_back_side_upload_complete_awaiting_response, this.getString("uploadCompleteAwaitingResponse"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_back_side_upload_complete_awaiting_processing, this.getString("uploadCompleteAwaitingProcessing"));

    this.target = this.getTarget(idScan, "uploadUserConfirmedInfo");
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_user_confirmed_info_upload_started, this.getString("uploadStarted"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_user_confirmed_info_still_uploading, this.getString("stillUploading"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_user_confirmed_info_upload_complete_awaiting_response, this.getString("uploadCompleteAwaitingResponse"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_user_confirmed_info_upload_complete_awaiting_processing, this.getString("uploadCompleteAwaitingProcessing"));

    this.target = this.getTarget(idScan, "uploadNfc");
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_nfc_upload_started, this.getString("uploadStarted"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_nfc_still_uploading, this.getString("stillUploading"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_nfc_upload_complete_awaiting_response, this.getString("uploadCompleteAwaitingResponse"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_nfc_upload_complete_awaiting_processing, this.getString("uploadCompleteAwaitingProcessing"));

    this.target = this.getTarget(idScan, "uploadSkippedNfc");
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_skipped_nfc_upload_started, this.getString("uploadStarted"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_skipped_nfc_still_uploading, this.getString("stillUploading"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_skipped_nfc_upload_complete_awaiting_response, this.getString("uploadCompleteAwaitingResponse"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_upload_message_skipped_nfc_upload_complete_awaiting_processing, this.getString("uploadCompleteAwaitingProcessing"));

    this.target = this.getTarget(idScan, "success");
    dynamicStrings.put(R.string.FaceTec_result_idscan_success_front_side_message, this.getString("frontSide"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_success_front_side_back_next_message, this.getString("frontSideBackNext"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_success_front_side_nfc_next_message, this.getString("frontSideNfcNext"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_success_back_side_message, this.getString("backSide"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_success_back_side_nfc_next_message, this.getString("backSideNfcNext"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_success_passport_message, this.getString("passport"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_success_passport_nfc_next_message, this.getString("passportNfcNext"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_success_user_confirmation_message, this.getString("userConfirmation"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_success_nfc_message, this.getString("nfc"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_success_additional_review_message, this.getString("additionalReview"));

    this.target = this.getTarget(idScan, "retry");
    dynamicStrings.put(R.string.FaceTec_result_idscan_retry_face_did_not_match_message, this.getString("faceDidNotMatch"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_retry_id_not_fully_visible_message, this.getString("idNotFullyVisible"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_retry_ocr_results_not_good_enough_message, this.getString("ocrResultsNotGoodEnough"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_retry_id_type_not_supported_message, this.getString("idTypeNotSupported"));
    dynamicStrings.put(R.string.FaceTec_result_idscan_retry_barcode_not_read_message, this.getString("barcodeNotRead"));
  }

  private void retry() {
    final ReadableMap target = this.getTarget("retry");

    this.target = target;
    dynamicStrings.put(R.string.FaceTec_retry_header, this.getString("header"));
    dynamicStrings.put(R.string.FaceTec_retry_subheader_message, this.getString("subHeader"));
    dynamicStrings.put(R.string.FaceTec_retry_your_image_label, this.getString("yourImageLabel"));
    dynamicStrings.put(R.string.FaceTec_retry_ideal_image_label, this.getString("idealImageLabel"));

    this.target = this.getTarget(target, "instruction");
    dynamicStrings.put(R.string.FaceTec_retry_instruction_message_1, this.getString("primary"));
    dynamicStrings.put(R.string.FaceTec_retry_instruction_message_2, this.getString("secondary"));
    dynamicStrings.put(R.string.FaceTec_retry_instruction_message_3, this.getString("tertiary"));

    this.target = this.getTarget(target, "officialIdPhoto");
    dynamicStrings.put(R.string.FaceTec_retry_official_id_photo_header, this.getString("header"));
    dynamicStrings.put(R.string.FaceTec_retry_official_id_photo_subheader_message, this.getString("subHeader"));
    dynamicStrings.put(R.string.FaceTec_retry_official_id_photo_instruction_message, this.getString("instruction"));
    dynamicStrings.put(R.string.FaceTec_retry_official_id_photo_your_image_label, this.getString("yourImageLabel"));
    dynamicStrings.put(R.string.FaceTec_retry_official_id_photo_ideal_image_label, this.getString("idealImageLabel"));
  }

  private void cleanup() {
    this.target = null;
  }

  public DynamicStrings setStrings(@Nullable ReadableMap strings) {
    this.strings = strings;

    return this;
  }

  public DynamicStrings load() {
    this.accessibility();
    this.accessibilityFeedback();
    this.action();
    this.camera();
    this.feedback();
    this.idScan();
    this.instructions();
    this.presession();
    this.result();
    this.retry();

    return this;
  }

  public void build() {
    FaceTecSDK.setDynamicStrings(this.dynamicStrings);

    this.cleanup();
  }
}

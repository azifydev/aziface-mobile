import Foundation
import FaceTecSDK

public class DynamicStrings {
  private var dynamicStrings: [String: String]
  private var strings: NSDictionary?
  private var target: NSDictionary?
  
  public init() {
    self.dynamicStrings = [:]
    self.strings = nil
    self.target = nil
  }
  
  private func getString(_ key: String) -> String? {
    return self.target?[key] as? String
  }
  
  private func getTarget(_ key: String) -> NSDictionary? {
    return self.strings?[key] as? NSDictionary
  }
  
  private func getTarget(_ target: NSDictionary?, key: String) -> NSDictionary? {
    return target?[key] as? NSDictionary
  }
  
  private func accessibility() {
    self.target = self.getTarget("accessibility")
    
    self.dynamicStrings["FaceTec_accessibility_cancel_button"] = self.getString("cancelButton")
    self.dynamicStrings["FaceTec_accessibility_torch_button"] = self.getString("torchButton")
    self.dynamicStrings["FaceTec_accessibility_tap_guidance"] = self.getString("tapGuidance")
  }
  
  private func accessibilityFeedback() {
    self.target = self.getTarget("accessibilityFeedback")
    
    self.dynamicStrings["FaceTec_accessibility_feedback_move_phone_away"] = self.getString("movePhoneAway")
    self.dynamicStrings["FaceTec_accessibility_feedback_move_phone_closer"] = self.getString("movePhoneCloser")
    self.dynamicStrings["FaceTec_accessibility_feedback_hold_device_to_eye_level"] = self.getString("holdDeviceToEyeLevel")
    
    self.target = self.getTarget(self.target, key: "face")
    self.dynamicStrings["FaceTec_accessibility_feedback_face_too_far_left"] = self.getString("tooFarLeft")
    self.dynamicStrings["FaceTec_accessibility_feedback_face_too_far_right"] = self.getString("tooFarRight")
    self.dynamicStrings["FaceTec_accessibility_feedback_face_too_low"] = self.getString("tooLow")
    self.dynamicStrings["FaceTec_accessibility_feedback_face_too_high"] = self.getString("tooHigh")
    self.dynamicStrings["FaceTec_accessibility_feedback_face_rotated_too_far_left"] = self.getString("rotatedTooFarLeft")
    self.dynamicStrings["FaceTec_accessibility_feedback_face_rotated_too_far_right"] = self.getString("rotatedTooFarRight")
    self.dynamicStrings["FaceTec_accessibility_feedback_face_pointing_too_far_left"] = self.getString("pointingTooFarLeft")
    self.dynamicStrings["FaceTec_accessibility_feedback_face_pointing_too_far_right"] = self.getString("pointingTooFarRight")
    self.dynamicStrings["FaceTec_accessibility_feedback_face_not_on_camera"] = self.getString("notOnCamera")
  }
  
  private func action() {
    self.target = self.getTarget("action")
    
    self.dynamicStrings["FaceTec_action_ok"] = self.getString("ok")
    self.dynamicStrings["FaceTec_action_im_ready"] = self.getString("imReady")
    self.dynamicStrings["FaceTec_action_try_again"] = self.getString("tryAgain")
    self.dynamicStrings["FaceTec_action_continue"] = self.getString("continue")
    self.dynamicStrings["FaceTec_action_take_photo"] = self.getString("takePhoto")
    self.dynamicStrings["FaceTec_action_retake_photo"] = self.getString("retakePhoto")
    self.dynamicStrings["FaceTec_action_accept_photo"] = self.getString("acceptPhoto")
    self.dynamicStrings["FaceTec_action_confirm"] = self.getString("confirm")
    self.dynamicStrings["FaceTec_action_scan_nfc"] = self.getString("scanNfc")
    self.dynamicStrings["FaceTec_action_scan_nfc_card"] = self.getString("scanNfcCard")
    self.dynamicStrings["FaceTec_action_skip_nfc"] = self.getString("skipNfc")
  }
  
  private func camera() {
    let target = self.getTarget("camera")
    
    self.target = target
    self.dynamicStrings["FaceTec_initializing_camera"] = self.getString("initializingCamera")
    
    self.target = self.getTarget(target, key: "permission")
    self.dynamicStrings["FaceTec_camera_permission_header"] = self.getString("header")
    self.dynamicStrings["FaceTec_camera_permission_message_enroll"] = self.getString("enroll")
    self.dynamicStrings["FaceTec_camera_permission_message_auth"] = self.getString("auth")
    self.dynamicStrings["FaceTec_camera_permission_enable_camera"] = self.getString("enableCamera")
    self.dynamicStrings["FaceTec_camera_permission_launch_settings"] = self.getString("launchSettings")
  }
  
  private func feedback() {
    let target = self.getTarget("feedback")
    
    self.target = target
    self.dynamicStrings["FaceTec_feedback_center_face"] = self.getString("centerFace")
    self.dynamicStrings["FaceTec_feedback_hold_steady"] = self.getString("holdSteady")
    self.dynamicStrings["FaceTec_feedback_use_even_lighting"] = self.getString("useEvenLighting")
    
    self.target = self.getTarget(target, key: "face")
    self.dynamicStrings["FaceTec_feedback_face_not_found"] = self.getString("notFound")
    self.dynamicStrings["FaceTec_feedback_face_not_looking_straight_ahead"] = self.getString("notLookingStraightAhead")
    self.dynamicStrings["FaceTec_feedback_face_not_upright"] = self.getString("notUpright")
    
    self.target = self.getTarget(target, key: "move")
    self.dynamicStrings["FaceTec_feedback_move_phone_away"] = self.getString("phoneAway")
    self.dynamicStrings["FaceTec_feedback_move_phone_closer"] = self.getString("phoneCloser")
    self.dynamicStrings["FaceTec_feedback_move_phone_to_eye_level"] = self.getString("phoneToEyeLevel")
  }
  
  private func idScan() {
    let target = self.getTarget("idScan")
    
    self.target = target
    self.dynamicStrings["FaceTec_idscan_type_selection_header"] = self.getString("typeSelectionHeader")
    self.dynamicStrings["FaceTec_idscan_additional_review_message"] = self.getString("additionalReview")
    
    self.target = self.getTarget(target, key: "capture")
    self.dynamicStrings["FaceTec_idscan_capture_tap_to_focus_message"] = self.getString("tapToFocus")
    self.dynamicStrings["FaceTec_idscan_capture_hold_steady_message"] = self.getString("holdSteady")
    self.dynamicStrings["FaceTec_idscan_capture_id_front_instruction_message"] = self.getString("idFrontInstruction")
    self.dynamicStrings["FaceTec_idscan_capture_id_back_instruction_message"] = self.getString("idBackInstruction")
    
    self.target = self.getTarget(target, key: "review")
    self.dynamicStrings["FaceTec_idscan_review_id_front_instruction_message"] = self.getString("idFrontInstruction")
    self.dynamicStrings["FaceTec_idscan_review_id_back_instruction_message"] = self.getString("idBackInstruction")
    
    self.target = self.getTarget(target, key: "ocr")
    self.dynamicStrings["FaceTec_idscan_ocr_confirmation_main_header"] = self.getString("confirmationMainHeader")
    self.dynamicStrings["FaceTec_idscan_ocr_confirmation_scroll_message"] = self.getString("confirmationScroll")
    
    self.target = self.getTarget(target, key: "nfc")
    self.dynamicStrings["FaceTec_idscan_nfc_status_disabled_message"] = self.getString("statusDisabled")
    self.dynamicStrings["FaceTec_idscan_nfc_status_ready_message"] = self.getString("statusReady")
    self.dynamicStrings["FaceTec_idscan_nfc_card_status_ready_message"] = self.getString("cardStatusReady")
    self.dynamicStrings["FaceTec_idscan_nfc_status_starting_message"] = self.getString("statusStarting")
    self.dynamicStrings["FaceTec_idscan_nfc_card_status_starting_message"] = self.getString("cardStatusStarting")
    self.dynamicStrings["FaceTec_idscan_nfc_status_scanning_message"] = self.getString("statusScanning")
    self.dynamicStrings["FaceTec_idscan_nfc_status_weak_connection_message"] = self.getString("statusWeakConnection")
    self.dynamicStrings["FaceTec_idscan_nfc_status_finished_with_success_message"] = self.getString("statusFinishedWithSuccess")
    self.dynamicStrings["FaceTec_idscan_nfc_status_finished_with_error_message"] = self.getString("statusFinishedWithError")
    self.dynamicStrings["FaceTec_idscan_nfc_card_status_finished_with_error_message"] = self.getString("cardStatusFinishedWithError")
    self.dynamicStrings["FaceTec_idscan_nfc_status_skipped_message"] = self.getString("statusSkipped")
    
    self.target = self.getTarget(target, key: "feedback")
    self.dynamicStrings["FaceTec_idscan_feedback_flip_id_to_back_message"] = self.getString("flipIdToBack")
    self.dynamicStrings["FaceTec_idscan_feedback_flip_id_to_front_message"] = self.getString("flipIdToFront")
  }
  
  private func instructions() {
    let target = self.getTarget("instructions")
    
    self.target = self.getTarget(target, key: "header")
    self.dynamicStrings["FaceTec_instructions_header_ready_1"] = self.getString("primary")
    self.dynamicStrings["FaceTec_instructions_header_ready_2"] = self.getString("secondary")
    
    self.target = self.getTarget(target, key: "message")
    self.dynamicStrings["FaceTec_instructions_message_ready_1"] = self.getString("primary")
    self.dynamicStrings["FaceTec_instructions_message_ready_2"] = self.getString("secondary")
  }
  
  private func presession() {
    self.target = self.getTarget("presession")
    
    self.dynamicStrings["FaceTec_orientation_message"] = self.getString("orientation")
    self.dynamicStrings["FaceTec_presession_frame_your_face"] = self.getString("frameYourFace")
    self.dynamicStrings["FaceTec_presession_position_face_straight_in_oval"] = self.getString("positionFaceStraightInOval")
    self.dynamicStrings["FaceTec_presession_remove_dark_glasses"] = self.getString("removeDarkGlasses")
    self.dynamicStrings["FaceTec_presession_neutral_expression"] = self.getString("neutralExpression")
    self.dynamicStrings["FaceTec_presession_conditions_too_bright"] = self.getString("conditionsTooBright")
    self.dynamicStrings["FaceTec_presession_brighten_your_environment"] = self.getString("brightenYourEnvironment")
  }
  
  private func result() {
    let target = self.getTarget("result")
    
    self.target = target
    self.dynamicStrings["FaceTec_result_nfc_upload_message"] = self.getString("nfcUpload")
    self.dynamicStrings["FaceTec_result_session_abort_message"] = self.getString("sessionAbort")
    
    self.target = self.getTarget(target, key: "faceScan")
    self.dynamicStrings["FaceTec_result_facescan_upload_message"] = self.getString("upload")
    self.dynamicStrings["FaceTec_result_facescan_upload_message_still_uploading"] = self.getString("uploadStillUploading")
    
    self.target = self.getTarget(self.target, key: "success3d")
    self.dynamicStrings["FaceTec_result_facescan_success_3d_enrollment_message"] = self.getString("enrollment")
    self.dynamicStrings["FaceTec_result_facescan_success_3d_3d_reverification_message"] = self.getString("reverification")
    self.dynamicStrings["FaceTec_result_facescan_success_3d_liveness_prior_to_idscan_message"] = self.getString("livenessPriorToIdScan")
    self.dynamicStrings["FaceTec_result_facescan_success_3d_liveness_and_official_id_photo_message"] = self.getString("livenessAndOfficialIdPhoto")
    
    let idScan = self.getTarget(target, key: "idScan")
    
    self.target = idScan
    self.dynamicStrings["FaceTec_result_idscan_unsuccess_message"] = self.getString("unsuccess")
    self.dynamicStrings["FaceTec_result_idscan_skip_or_error_nfc_message"] = self.getString("skipOrErrorNfc")
    self.dynamicStrings["FaceTec_result_idscan_additional_review_tag_message"] = self.getString("additionalReviewTag")
    
    self.target = self.getTarget(idScan, key: "uploadFrontSide")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_front_side_upload_started"] = self.getString("uploadStarted")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_front_side_still_uploading"] = self.getString("stillUploading")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_front_side_upload_complete_awaiting_response"] = self.getString("uploadCompleteAwaitingResponse")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_front_side_upload_complete_awaiting_processing"] = self.getString("uploadCompleteAwaitingProcessing")
    
    self.target = self.getTarget(idScan, key: "uploadBackSide")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_back_side_upload_started"] = self.getString("uploadStarted")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_back_side_still_uploading"] = self.getString("stillUploading")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_back_side_upload_complete_awaiting_response"] = self.getString("uploadCompleteAwaitingResponse")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_back_side_upload_complete_awaiting_processing"] = self.getString("uploadCompleteAwaitingProcessing")
    
    self.target = self.getTarget(idScan, key: "uploadUserConfirmedInfo")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_user_confirmed_info_upload_started"] = self.getString("uploadStarted")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_user_confirmed_info_still_uploading"] = self.getString("stillUploading")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_user_confirmed_info_upload_complete_awaiting_response"] = self.getString("uploadCompleteAwaitingResponse")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_user_confirmed_info_upload_complete_awaiting_processing"] = self.getString("uploadCompleteAwaitingProcessing")
    
    self.target = self.getTarget(idScan, key: "uploadNfc")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_nfc_upload_started"] = self.getString("uploadStarted")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_nfc_still_uploading"] = self.getString("stillUploading")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_nfc_upload_complete_awaiting_response"] = self.getString("uploadCompleteAwaitingResponse")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_nfc_upload_complete_awaiting_processing"] = self.getString("uploadCompleteAwaitingProcessing")
    
    self.target = self.getTarget(idScan, key: "uploadSkippedNfc")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_skipped_nfc_upload_started"] = self.getString("uploadStarted")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_skipped_nfc_still_uploading"] = self.getString("stillUploading")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_skipped_nfc_upload_complete_awaiting_response"] = self.getString("uploadCompleteAwaitingResponse")
    self.dynamicStrings["FaceTec_result_idscan_upload_message_skipped_nfc_upload_complete_awaiting_processing"] = self.getString("uploadCompleteAwaitingProcessing")
    
    self.target = self.getTarget(idScan, key: "success")
    self.dynamicStrings["FaceTec_result_idscan_success_front_side_message"] = self.getString("frontSide")
    self.dynamicStrings["FaceTec_result_idscan_success_front_side_back_next_message"] = self.getString("frontSideBackNext")
    self.dynamicStrings["FaceTec_result_idscan_success_front_side_nfc_next_message"] = self.getString("frontSideNfcNext")
    self.dynamicStrings["FaceTec_result_idscan_success_back_side_message"] = self.getString("backSide")
    self.dynamicStrings["FaceTec_result_idscan_success_back_side_nfc_next_message"] = self.getString("backSideNfcNext")
    self.dynamicStrings["FaceTec_result_idscan_success_passport_message"] = self.getString("passport")
    self.dynamicStrings["FaceTec_result_idscan_success_passport_nfc_next_message"] = self.getString("passportNfcNext")
    self.dynamicStrings["FaceTec_result_idscan_success_user_confirmation_message"] = self.getString("userConfirmation")
    self.dynamicStrings["FaceTec_result_idscan_success_nfc_message"] = self.getString("nfc")
    self.dynamicStrings["FaceTec_result_idscan_success_additional_review_message"] = self.getString("additionalReview")
    
    self.target = self.getTarget(idScan, key: "retry")
    self.dynamicStrings["FaceTec_result_idscan_retry_face_did_not_match_message"] = self.getString("faceDidNotMatch")
    self.dynamicStrings["FaceTec_result_idscan_retry_id_not_fully_visible_message"] = self.getString("idNotFullyVisible")
    self.dynamicStrings["FaceTec_result_idscan_retry_ocr_results_not_good_enough_message"] = self.getString("ocrResultsNotGoodEnough")
    self.dynamicStrings["FaceTec_result_idscan_retry_id_type_not_supported_message"] = self.getString("idTypeNotSupported")
    self.dynamicStrings["FaceTec_result_idscan_retry_barcode_not_read_message"] = self.getString("barcodeNotRead")
  }
  
  private func retry() {
    let target = self.getTarget("retry")
    
    self.target = target
    self.dynamicStrings["FaceTec_retry_header"] = self.getString("header")
    self.dynamicStrings["FaceTec_retry_subheader_message"] = self.getString("subHeader")
    self.dynamicStrings["FaceTec_retry_your_image_label"] = self.getString("yourImageLabel")
    self.dynamicStrings["FaceTec_retry_ideal_image_label"] = self.getString("idealImageLabel")
    
    self.target = self.getTarget(target, key: "instruction")
    self.dynamicStrings["FaceTec_retry_instruction_message_1"] = self.getString("primary")
    self.dynamicStrings["FaceTec_retry_instruction_message_2"] = self.getString("secondary")
    self.dynamicStrings["FaceTec_retry_instruction_message_3"] = self.getString("tertiary")
    
    self.target = self.getTarget(target, key: "officialIdPhoto")
    self.dynamicStrings["FaceTec_retry_official_id_photo_header"] = self.getString("header")
    self.dynamicStrings["FaceTec_retry_official_id_photo_subheader_message"] = self.getString("subHeader")
    self.dynamicStrings["FaceTec_retry_official_id_photo_instruction_message"] = self.getString("instruction")
    self.dynamicStrings["FaceTec_retry_official_id_photo_your_image_label"] = self.getString("yourImageLabel")
    self.dynamicStrings["FaceTec_retry_official_id_photo_ideal_image_label"] = self.getString("idealImageLabel")
  }

  private func cleanup() {
    self.target = nil
  }
  
  public func setStrings(_ strings: NSDictionary?) -> DynamicStrings {
    self.strings = strings
    
    return self
  }
  
  public func load() -> DynamicStrings {
    self.accessibility()
    self.accessibilityFeedback()
    self.action()
    self.camera()
    self.feedback()
    self.idScan()
    self.instructions()
    self.presession()
    self.result()
    self.retry()
    
    return self
  }
  
  public func build() {
    FaceTec.sdk.setDynamicStrings(self.dynamicStrings)
    
    self.cleanup()
  }
}

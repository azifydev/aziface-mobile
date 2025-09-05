package com.azify.theme;

import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;

import android.app.AlertDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.ContextThemeWrapper;

import com.azify.AzifaceModule;
import com.azify.Config;
import com.azify.R;
import com.facetec.sdk.FaceTecSDK;
import com.facetec.sdk.FaceTecVocalGuidanceCustomization;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Vocal {
  enum VocalGuidanceMode {
    OFF,
    MINIMAL,
    FULL
  }

  static MediaPlayer vocalGuidanceOnPlayer;
  static MediaPlayer vocalGuidanceOffPlayer;
  static Vocal.VocalGuidanceMode vocalGuidanceMode = VocalGuidanceMode.MINIMAL;

  public static void setUpVocalGuidancePlayers(AzifaceModule module) {
    vocalGuidanceOnPlayer = MediaPlayer.create(module.getContext(), R.raw.vocal_guidance_on);
    vocalGuidanceOffPlayer = MediaPlayer.create(module.getContext(), R.raw.vocal_guidance_off);
    vocalGuidanceMode = Vocal.VocalGuidanceMode.MINIMAL;
  }

  public static void setVocalGuidanceMode(AzifaceModule module) {
    if (isDeviceMuted(module)) {
      // TODO: Add custom message for AlertDialog
      AlertDialog alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(module.getContext(), android.R.style.Theme_Holo_Light)).create();
      alertDialog.setMessage("Vocal Guidance is disabled when the device is muted");
      alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
        (dialog, which) -> dialog.dismiss());
      alertDialog.show();
      return;
    }

    if (vocalGuidanceOnPlayer == null || vocalGuidanceOffPlayer == null || vocalGuidanceOnPlayer.isPlaying() || vocalGuidanceOffPlayer.isPlaying()) {
      return;
    }

    runOnUiThread(() -> {
      switch (vocalGuidanceMode) {
        case OFF:
          vocalGuidanceMode = VocalGuidanceMode.MINIMAL;
          vocalGuidanceOnPlayer.start();
          Config.currentCustomization.vocalGuidanceCustomization.mode = FaceTecVocalGuidanceCustomization.VocalGuidanceMode.MINIMAL_VOCAL_GUIDANCE;
          break;
        case MINIMAL:
          vocalGuidanceMode = VocalGuidanceMode.FULL;
          vocalGuidanceOnPlayer.start();
          Config.currentCustomization.vocalGuidanceCustomization.mode = FaceTecVocalGuidanceCustomization.VocalGuidanceMode.FULL_VOCAL_GUIDANCE;
          break;
        case FULL:
          vocalGuidanceMode = VocalGuidanceMode.OFF;
          vocalGuidanceOffPlayer.start();
          Config.currentCustomization.vocalGuidanceCustomization.mode = FaceTecVocalGuidanceCustomization.VocalGuidanceMode.NO_VOCAL_GUIDANCE;
          break;
      }

      Vocal.setVocalGuidanceSoundFiles();
      FaceTecSDK.setCustomization(Config.currentCustomization);
    });
  }

  public static void setVocalGuidanceSoundFiles() {
    Config.currentCustomization.vocalGuidanceCustomization.pleaseFrameYourFaceInTheOvalSoundFile = R.raw.please_frame_your_face_sound_file;
    Config.currentCustomization.vocalGuidanceCustomization.pleaseMoveCloserSoundFile = R.raw.please_move_closer_sound_file;
    Config.currentCustomization.vocalGuidanceCustomization.pleaseRetrySoundFile = R.raw.please_retry_sound_file;
    Config.currentCustomization.vocalGuidanceCustomization.uploadingSoundFile = R.raw.uploading_sound_file;
    Config.currentCustomization.vocalGuidanceCustomization.facescanSuccessfulSoundFile = R.raw.facescan_successful_sound_file;
    Config.currentCustomization.vocalGuidanceCustomization.pleasePressTheButtonToStartSoundFile = R.raw.please_press_button_sound_file;

    switch (vocalGuidanceMode) {
      case OFF:
        Config.currentCustomization.vocalGuidanceCustomization.mode = FaceTecVocalGuidanceCustomization.VocalGuidanceMode.NO_VOCAL_GUIDANCE;
        break;
      case MINIMAL:
        Config.currentCustomization.vocalGuidanceCustomization.mode = FaceTecVocalGuidanceCustomization.VocalGuidanceMode.MINIMAL_VOCAL_GUIDANCE;
        break;
      case FULL:
        Config.currentCustomization.vocalGuidanceCustomization.mode = FaceTecVocalGuidanceCustomization.VocalGuidanceMode.FULL_VOCAL_GUIDANCE;
        break;
    }
  }

  public static boolean isDeviceMuted(AzifaceModule module) {
    AudioManager audio = (AudioManager) (module.getActivity().getSystemService(Context.AUDIO_SERVICE));
    return audio.getStreamVolume(AudioManager.STREAM_MUSIC) == 0;
  }

  public static void setOCRLocalization(Context context) {
    // Set the strings to be used for group names, field names, and placeholder texts for the FaceTec ID Scan User OCR Confirmation Screen.
    // DEVELOPER NOTE: For this demo, we are using the template json file, 'FaceTec_OCR_Customization.json,' as the parameter in calling this API.
    // For the configureOCRLocalization API parameter, you may use any object that follows the same structure and key naming as the template json file, 'FaceTec_OCR_Customization.json'.
    try {
      InputStream is = context.getAssets().open("FaceTec_OCR_Customization.json");
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      String ocrLocalizationJSONString = new String(buffer, StandardCharsets.UTF_8);
      JSONObject ocrLocalizationJSON = new JSONObject(ocrLocalizationJSONString);

      FaceTecSDK.configureOCRLocalization(ocrLocalizationJSON);
    } catch (IOException | JSONException ex) {
      ex.printStackTrace();
    }
  }
}

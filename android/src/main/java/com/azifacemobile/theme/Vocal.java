package com.azifacemobile.theme;

import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;

import android.app.AlertDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.ContextThemeWrapper;

import com.azifacemobile.AzifaceMobileModule;
import com.azifacemobile.Config;
import com.azifacemobile.R;
import com.facetec.sdk.FaceTecSDK;
import com.facetec.sdk.FaceTecVocalGuidanceCustomization;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Vocal {
  public enum VocalGuidanceMode {
    OFF,
    MINIMAL,
    FULL
  }

  static MediaPlayer vocalGuidanceOnPlayer;
  static MediaPlayer vocalGuidanceOffPlayer;
  public static Vocal.VocalGuidanceMode vocalGuidanceMode = VocalGuidanceMode.OFF;

  public static void setUpVocalGuidancePlayers(AzifaceMobileModule module) {
    if (vocalGuidanceOnPlayer != null && vocalGuidanceOffPlayer != null) return;

    vocalGuidanceOnPlayer = MediaPlayer.create(module.getContext(), R.raw.vocal_guidance_on);
    vocalGuidanceOffPlayer = MediaPlayer.create(module.getContext(), R.raw.vocal_guidance_off);
  }

  public static void setVocalGuidanceMode(boolean isEnabled) {
    vocalGuidanceMode = isEnabled ? VocalGuidanceMode.FULL : VocalGuidanceMode.OFF;

    runOnUiThread(() -> {
      final MediaPlayer player = isEnabled ? vocalGuidanceOnPlayer : vocalGuidanceOffPlayer;
      if (player != null) player.start();

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

  public static boolean isDeviceMuted(AzifaceMobileModule module) {
    AudioManager audio = (AudioManager) (module.getActivity().getSystemService(Context.AUDIO_SERVICE));
    return audio.getStreamVolume(AudioManager.STREAM_MUSIC) == 0;
  }

  public static void setOCRLocalization(Context context) {
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

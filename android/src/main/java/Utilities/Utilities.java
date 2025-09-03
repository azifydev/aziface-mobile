package Utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.azify.Config;
import com.azify.R;
import com.azify.ModuleActivity;
import com.facetec.sdk.FaceTecSDK;
import com.facetec.sdk.FaceTecVocalGuidanceCustomization;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Utilities {
    enum VocalGuidanceMode {
        OFF,
        MINIMAL,
        FULL
    }

    static MediaPlayer vocalGuidanceOnPlayer;
    static MediaPlayer vocalGuidanceOffPlayer;
    static Utilities.VocalGuidanceMode vocalGuidanceMode = VocalGuidanceMode.MINIMAL;
    public static String currentTheme = "Config Wizard Theme";


    public static void setupAllButtons(ModuleActivity moduleActivity) {
        moduleActivity.runOnUiThread(() -> {
            moduleActivity.activityMainBinding.enrollButton.setupButton(moduleActivity);
            moduleActivity.activityMainBinding.verifyButton.setupButton(moduleActivity);
            moduleActivity.activityMainBinding.livenessCheckButton.setupButton(moduleActivity);
            moduleActivity.activityMainBinding.identityCheckButton.setupButton(moduleActivity);
            moduleActivity.activityMainBinding.identityScanOnlyButton.setupButton(moduleActivity);
            moduleActivity.activityMainBinding.settingsButton.setupButton(moduleActivity);
            moduleActivity.activityMainBinding.officialIDPhotoButton.setupButton(moduleActivity);
        });
    }

    public static void disableAllButtons(ModuleActivity moduleActivity) {
        moduleActivity.runOnUiThread(() -> {
            moduleActivity.activityMainBinding.enrollButton.setEnabled(false, true);
            moduleActivity.activityMainBinding.verifyButton.setEnabled(false, true);
            moduleActivity.activityMainBinding.livenessCheckButton.setEnabled(false, true);
            moduleActivity.activityMainBinding.identityCheckButton.setEnabled(false, true);
            moduleActivity.activityMainBinding.identityScanOnlyButton.setEnabled(false, true);
            moduleActivity.activityMainBinding.settingsButton.setEnabled(false, true);
            moduleActivity.activityMainBinding.officialIDPhotoButton.setEnabled(false, true);
        });
    }

    public static void enableAllButtons(ModuleActivity moduleActivity) {
        moduleActivity.runOnUiThread(() -> {
            moduleActivity.activityMainBinding.enrollButton.setEnabled(true, true);
            moduleActivity.activityMainBinding.verifyButton.setEnabled(true, true);
            moduleActivity.activityMainBinding.livenessCheckButton.setEnabled(true, true);
            moduleActivity.activityMainBinding.identityCheckButton.setEnabled(true, true);
            moduleActivity.activityMainBinding.identityScanOnlyButton.setEnabled(true, true);
            moduleActivity.activityMainBinding.settingsButton.setEnabled(true, true);
            moduleActivity.activityMainBinding.officialIDPhotoButton.setEnabled(true, true);
        });
    }

    // Disable buttons to prevent hammering, fade out main interface elements, and shuffle the guidance images.
    public static void fadeOutMainUIAndPrepareForFaceTecSDK(ModuleActivity moduleActivity, final Runnable callback) {
        disableAllButtons(moduleActivity);
        moduleActivity.runOnUiThread(() -> {
            moduleActivity.activityMainBinding.vocalGuidanceSettingButton.animate().alpha(0f).setDuration(600).start();
            moduleActivity.activityMainBinding.themeTransitionImageView.animate().alpha(1f).setDuration(600).start();
            moduleActivity.activityMainBinding.contentLayout.animate().alpha(0f).setDuration(600).withEndAction(callback).start();
        });
    }

    public static void fadeInMainUI(ModuleActivity moduleActivity) {
        enableAllButtons(moduleActivity);
        moduleActivity.runOnUiThread(() -> {
                moduleActivity.activityMainBinding.vocalGuidanceSettingButton.animate().alpha(1f).setDuration(600);
                moduleActivity.activityMainBinding.contentLayout.animate().alpha(1f).setDuration(600);
                moduleActivity.activityMainBinding.themeTransitionImageView.animate().alpha(0f).setDuration(600);
            }
        );
    }

    public static void displayStatus(ModuleActivity moduleActivity, final String statusString) {
        displayStatus(moduleActivity, statusString, true);
    }

    public static void displayStatus(ModuleActivity moduleActivity, final String statusString, boolean shouldLog) {
        if (shouldLog) {
            Log.d("FaceTecSDKSampleApp", statusString);
        }

        moduleActivity.runOnUiThread(() -> moduleActivity.activityMainBinding.statusLabel.setText(statusString));
    }

    public static void showThemeSelectionMenu(ModuleActivity moduleActivity) {
        final String[] themes = new String[] { "Config Wizard Theme", "FaceTec Theme", "Pseudo-Fullscreen", "Well-Rounded", "Bitcoin Exchange", "eKYC", "Sample Bank"};

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(moduleActivity, android.R.style.Theme_Holo_Light));
        builder.setTitle("Select a Theme:");
        builder.setItems(themes, (dialog, index) -> {
            currentTheme = themes[index];
            ThemeHelpers.setAppTheme(moduleActivity, currentTheme);
            updateThemeTransitionView(moduleActivity);
        });
        builder.show();
    }

    public static void updateThemeTransitionView(ModuleActivity moduleActivity) {
        int transitionViewImage = 0;
        int transitionViewTextColor = Config.currentCustomization.getGuidanceCustomization().foregroundColor;
        switch (currentTheme) {
            case "FaceTec Theme":
                break;
            case "Config Wizard Theme":
                break;
            case "Pseudo-Fullscreen":
                break;
            case "Well-Rounded":
                transitionViewImage = R.drawable.well_rounded_bg;
                transitionViewTextColor = Config.currentCustomization.getFrameCustomization().backgroundColor;
                break;
            case "Bitcoin Exchange":
                transitionViewImage = R.drawable.bitcoin_exchange_bg;
                transitionViewTextColor = Config.currentCustomization.getFrameCustomization().backgroundColor;
                break;
            case "eKYC":
                transitionViewImage = R.drawable.ekyc_bg;
                break;
            case "Sample Bank":
                transitionViewImage = R.drawable.sample_bank_bg;
                transitionViewTextColor = Config.currentCustomization.getFrameCustomization().backgroundColor;
                break;
            default:
                break;
        }

        moduleActivity.activityMainBinding.themeTransitionImageView.setImageResource(transitionViewImage);
        moduleActivity.activityMainBinding.themeTransitionText.setTextColor(transitionViewTextColor);
    }

    public static void setUpVocalGuidancePlayers(ModuleActivity moduleActivity) {
        vocalGuidanceOnPlayer = MediaPlayer.create(moduleActivity, R.raw.vocal_guidance_on);
        vocalGuidanceOffPlayer = MediaPlayer.create(moduleActivity, R.raw.vocal_guidance_off);
        vocalGuidanceMode = Utilities.VocalGuidanceMode.MINIMAL;
        moduleActivity.runOnUiThread(() -> moduleActivity.activityMainBinding.vocalGuidanceSettingButton.setEnabled(true));
    }

    public static void setVocalGuidanceMode(ModuleActivity moduleActivity) {
        if (isDeviceMuted(moduleActivity)) {
            AlertDialog alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(moduleActivity, android.R.style.Theme_Holo_Light)).create();
            alertDialog.setMessage("Vocal Guidance is disabled when the device is muted");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
            return;
        }

        if (vocalGuidanceOnPlayer == null || vocalGuidanceOffPlayer == null || vocalGuidanceOnPlayer.isPlaying() || vocalGuidanceOffPlayer.isPlaying()) {
            return;
        }

        moduleActivity.runOnUiThread(() -> {
            switch (vocalGuidanceMode) {
                case OFF:
                    vocalGuidanceMode = VocalGuidanceMode.MINIMAL;
                    moduleActivity.activityMainBinding.vocalGuidanceSettingButton.setImageResource(R.drawable.vocal_minimal);
                    vocalGuidanceOnPlayer.start();
                    Config.currentCustomization.vocalGuidanceCustomization.mode = FaceTecVocalGuidanceCustomization.VocalGuidanceMode.MINIMAL_VOCAL_GUIDANCE;
                    break;
                case MINIMAL:
                    vocalGuidanceMode = VocalGuidanceMode.FULL;
                    moduleActivity.activityMainBinding.vocalGuidanceSettingButton.setImageResource(R.drawable.vocal_full);
                    vocalGuidanceOnPlayer.start();
                    Config.currentCustomization.vocalGuidanceCustomization.mode = FaceTecVocalGuidanceCustomization.VocalGuidanceMode.FULL_VOCAL_GUIDANCE;
                    break;
                case FULL:
                    vocalGuidanceMode = VocalGuidanceMode.OFF;
                    moduleActivity.activityMainBinding.vocalGuidanceSettingButton.setImageResource(R.drawable.vocal_off);
                    vocalGuidanceOffPlayer.start();
                    Config.currentCustomization.vocalGuidanceCustomization.mode = FaceTecVocalGuidanceCustomization.VocalGuidanceMode.NO_VOCAL_GUIDANCE;
                    break;
            }

            Utilities.setVocalGuidanceSoundFiles();
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

    public static boolean isDeviceMuted(ModuleActivity moduleActivity) {
        AudioManager audio = (AudioManager) (moduleActivity.getSystemService(Context.AUDIO_SERVICE));
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

    public static void configureInitialSampleAppUI(ModuleActivity moduleActivity) {
        moduleActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN);

        moduleActivity.activityMainBinding = DataBindingUtil.setContentView(moduleActivity, R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 35) {
            // Since edge-to-edge layout is enforced in Android 15, update main activity content to layout between the system bar bounds
            ViewCompat.setOnApplyWindowInsetsListener(moduleActivity.activityMainBinding.contentLayout, (view, windowInsets) -> {
                Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                marginLayoutParams.topMargin = insets.top;
                marginLayoutParams.leftMargin = insets.left;
                marginLayoutParams.bottomMargin = insets.bottom;
                marginLayoutParams.rightMargin = insets.right;
                view.setLayoutParams(marginLayoutParams);

                return WindowInsetsCompat.CONSUMED;
            });
        }

        setupAllButtons(moduleActivity);

        // If the screen size is small, reduce FaceTec Logo
        if (moduleActivity.getResources().getConfiguration().screenHeightDp < 500) {
            moduleActivity.activityMainBinding.facetecLogo.setScaleX(0.6f);
            moduleActivity.activityMainBinding.facetecLogo.setScaleY(0.6f);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) moduleActivity.activityMainBinding.facetecLogo.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
        }

        moduleActivity.runOnUiThread(() -> moduleActivity.activityMainBinding.vocalGuidanceSettingButton.setEnabled(false));
    }
}

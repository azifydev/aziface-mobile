package com.azify.theme;

import com.azify.azifacemobilesdk.R;
import com.azify.processors.Config;
import com.facebook.react.bridge.ReadableMap;
import com.facetec.sdk.FaceTecCancelButtonCustomization.ButtonLocation;
import com.facetec.sdk.FaceTecCustomization;
import com.facetec.sdk.FaceTecSDK;

public class Theme {
  private final FaceTec faceTec;
  private final DefaultMessage defaultMessage;

  public Theme() {
    this.faceTec = new FaceTec();
    this.defaultMessage = new DefaultMessage();
  }

  public static FaceTecCustomization getCustomizationForTheme() {
    FaceTecCustomization currentCustomization = new FaceTecCustomization();
    currentCustomization = Config.retrieveConfigurationWizardCustomization();
    currentCustomization
        .getIdScanCustomization().customNFCStartingAnimation = R.drawable.facetec_nfc_starting_animation;
    currentCustomization
        .getIdScanCustomization().customNFCScanningAnimation = R.drawable.facetec_nfc_scanning_animation;
    currentCustomization
        .getIdScanCustomization().customNFCCardStartingAnimation = R.drawable.facetec_nfc_card_starting_animation;
    currentCustomization
        .getIdScanCustomization().customNFCCardScanningAnimation = R.drawable.facetec_nfc_card_scanning_animation;

    return currentCustomization;
  }

  public static FaceTecCustomization getLowLightCustomizationForTheme() {
    FaceTecCustomization currentLowLightCustomization = getCustomizationForTheme();
    currentLowLightCustomization = Config.retrieveLowLightConfigurationWizardCustomization();

    return currentLowLightCustomization;
  }

  public static FaceTecCustomization getDynamicDimmingCustomizationForTheme() {
    FaceTecCustomization currentDynamicDimmingCustomization = getCustomizationForTheme();
    currentDynamicDimmingCustomization = Config.retrieveDynamicDimmingConfigurationWizardCustomization();

    return currentDynamicDimmingCustomization;
  }

  public static void setTheme(ReadableMap theme) {
    Config.setTheme(theme);

    Config.currentCustomization = getCustomizationForTheme();
    Config.currentLowLightCustomization = getLowLightCustomizationForTheme();
    Config.currentDynamicDimmingCustomization = getDynamicDimmingCustomizationForTheme();

    FaceTecSDK.setCustomization(Config.currentCustomization);
    FaceTecSDK.setLowLightCustomization(Config.currentLowLightCustomization);
    FaceTecSDK.setDynamicDimmingCustomization(Config.currentDynamicDimmingCustomization);
  }

  public ButtonLocation getButtonLocation(String key) {
    return this.faceTec.getButtonLocation(key);
  }

  public String getAuthenticateMessage(String key, String defaultMessage) {
    return this.defaultMessage.getMessage("authenticateMessage", key, defaultMessage);
  }

  public String getEnrollmentMessage(String key, String defaultMessage) {
    return this.defaultMessage.getMessage("enrollMessage", key, defaultMessage);
  }

  public String getPhotoIDScanMessage(String key, String defaultMessage) {
    return this.defaultMessage.getMessage("scanMessage", key, defaultMessage);
  }

  public String getPhotoIDScanMessage(String parent, String key, String defaultMessage) {
    return this.defaultMessage.getMessage("scanMessage", parent, key, defaultMessage);
  }

  public String getPhotoIDMatchMessage(String key, String defaultMessage) {
    return this.defaultMessage.getMessage("matchMessage", key, defaultMessage);
  }

  public String getPhotoIDMatchMessage(String parent, String key, String defaultMessage) {
    return this.defaultMessage.getMessage("matchMessage", parent, key, defaultMessage);
  }
}

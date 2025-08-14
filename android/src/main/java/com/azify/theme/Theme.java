package com.azify.theme;

import com.azify.azifacemobilesdk.R;
import com.azify.processors.Config;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facetec.sdk.FaceTecCustomization;
import com.facetec.sdk.FaceTecSDK;

public class Theme {
  private final Color color;
  private final FaceTec faceTec;
  private final Message message;
  private final Image image;
  private final Frame frame;
  private final Guidance guidance;
  private final Oval oval;
  private final Feedback feedback;
  private final ResultScreen resultScreen;
  private final IdScan idScan;
  public static ReadableMap Style;

  public Theme(ReactApplicationContext context) {
    this.color = new Color();
    this.faceTec = new FaceTec();
    this.message = new Message();
    this.frame = new Frame();
    this.guidance = new Guidance();
    this.oval = new Oval();
    this.feedback = new Feedback();
    this.resultScreen = new ResultScreen();
    this.idScan = new IdScan();
    this.image = new Image(context);
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

  public static void setTheme(ReadableMap style) {
    Style = style;

    Config.currentCustomization = getCustomizationForTheme();
    Config.currentLowLightCustomization = getLowLightCustomizationForTheme();
    Config.currentDynamicDimmingCustomization = getDynamicDimmingCustomizationForTheme();

    FaceTecSDK.setCustomization(Config.currentCustomization);
    FaceTecSDK.setLowLightCustomization(Config.currentLowLightCustomization);
    FaceTecSDK.setDynamicDimmingCustomization(Config.currentDynamicDimmingCustomization);
  }

  public int getColor(String key) {
    return this.color.getColor(key);
  }

  public String getAuthenticateMessage(String key, String defaultMessage) {
    return this.message.getMessage("authenticateMessage", key, defaultMessage);
  }

  public String getEnrollmentMessage(String key, String defaultMessage) {
    return this.message.getMessage("enrollMessage", key, defaultMessage);
  }

  public String getPhotoIDScanMessage(String key, String defaultMessage) {
    return this.message.getMessage("scanMessage", key, defaultMessage);
  }

  public String getPhotoIDScanMessage(String parent, String key, String defaultMessage) {
    return this.message.getMessage("scanMessage", parent, key, defaultMessage);
  }

  public String getPhotoIDMatchMessage(String key, String defaultMessage) {
    return this.message.getMessage("matchMessage", key, defaultMessage);
  }

  public String getPhotoIDMatchMessage(String parent, String key, String defaultMessage) {
    return this.message.getMessage("matchMessage", parent, key, defaultMessage);
  }

  public int getImage(String key, int defaultImage) {
    return this.image.getImage(key, defaultImage);
  }

  public FaceTec getFaceTec() {
    return this.faceTec;
  }

  public Frame getFrame() {
    return this.frame;
  }

  public Guidance getGuidance() {
    return this.guidance;
  }

  public Oval getOval() {
    return this.oval;
  }

  public Feedback getFeedback() {
    return this.feedback;
  }

  public ResultScreen getResultScreen() {
    return this.resultScreen;
  }

  public IdScan getIdScan() {
    return this.idScan;
  }
}

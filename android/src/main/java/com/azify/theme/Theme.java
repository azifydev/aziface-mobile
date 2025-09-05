package com.azify.theme;

import com.azify.R;
import com.azify.Config;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facetec.sdk.FaceTecCustomization;
import com.facetec.sdk.FaceTecSDK;

public class Theme {
  private final Color color;
  private final General general;
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
    this.general = new General();
    this.frame = new Frame();
    this.guidance = new Guidance();
    this.oval = new Oval();
    this.feedback = new Feedback();
    this.resultScreen = new ResultScreen();
    this.idScan = new IdScan();
    this.image = new Image(context);
  }

  static FaceTecCustomization getLowLightCustomizationForTheme() {
    return getCustomizationForTheme();
  }

  static FaceTecCustomization getDynamicDimmingCustomizationForTheme() {
    return getCustomizationForTheme();
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

  public int getImage(String key, int defaultImage) {
    return this.image.getImage(key, defaultImage);
  }

  public General getGeneral() {
    return this.general;
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

package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONObject;

public class Guidance extends ViewStyle {
  private static final String KEY = "guidance";
  private final Button button;
  private final Image image;
  private final RetryScreen retryScreen;
  private final ReadyScreen readyScreen;

  public Guidance(ReactApplicationContext context) {
    super(KEY);

    final JSONObject target = new Theme().getTarget(KEY);

    this.button = new Button(target);
    this.image = new Image(context, target, "images");
    this.retryScreen = new RetryScreen(target);
    this.readyScreen = new ReadyScreen(target);
  }

  @Override
  public int getForegroundColor() {
    return super.getForegroundColor("#272937");
  }

  public Button getButton() {
    return this.button;
  }

  public Image getImage() {
    return this.image;
  }

  public RetryScreen getRetryScreen() {
    return this.retryScreen;
  }

  public ReadyScreen getReadyScreen() {
    return this.readyScreen;
  }
}

package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONObject;

public class Guidance extends ViewStyle {
  private static final String KEY = "guidance";
  private final JSONObject target;
  private final Button button;
  private final Image image;
  private final RetryScreen retryScreen;
  private final ReadyScreen readyScreen;

  public Guidance(ReactApplicationContext context) {
    super(KEY);

    this.target = new Theme().getTarget(KEY);

    this.button = new Button(context, this.target);
    this.image = new Image(context, this.target, "images");
    this.retryScreen = new RetryScreen(context, this.target);
    this.readyScreen = new ReadyScreen(context, this.target);
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

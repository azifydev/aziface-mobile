package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.CommonStyle;
import com.azifacemobile.utils.Theme;

import org.json.JSONObject;

public class Guidance extends CommonStyle {
  private static final String KEY = "guidance";
  private final Button button;
  private final RetryScreen retryScreen;

  public Guidance() {
    super(KEY);

    final JSONObject theme = new Theme().getTarget(KEY);

    this.button = new Button(theme);
    this.retryScreen = new RetryScreen(theme);
  }

  @Override
  public int getForegroundColor() {
    return this.getForegroundColor("#272937");
  }

  public Button getButton() {
    return this.button;
  }

  public RetryScreen getRetryScreen() {
    return this.retryScreen;
  }
}

package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;

import org.json.JSONObject;

public class RetryScreen {
  private final JSONObject theme;
  private final Color color;

  public RetryScreen(JSONObject theme) {
    this.theme = new Theme().getTarget(theme, "retryScreen");
    this.color = new Color();
  }

  public int getImageBorderColor() {
    return this.color.getColor(this.theme, "imageBorderColor");
  }

  public int getOvalStrokeColor() {
    return this.color.getColor(this.theme, "ovalStrokeColor");
  }
}

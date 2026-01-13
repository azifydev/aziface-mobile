package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

public class RetryScreen {
  private final Theme theme;
  private final JSONObject target;
  private final Color color;

  public RetryScreen(JSONObject theme) {
    this.theme = new Theme();
    this.color = new Color();

    this.target = this.theme.getTarget(theme, "retryScreen");
  }

  public int getImageBorderWidth() {
    final String key = "imageBorderWidth";
    final int defaultBorderWidth = -1;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultBorderWidth;
      }

      final int borderWidth = this.target.getInt(key);
      return borderWidth < 0 ? defaultBorderWidth : borderWidth;
    } catch (JSONException e) {
      return defaultBorderWidth;
    }
  }

  public int getImageBorderColor() {
    return this.color.getColor(this.target, "imageBorderColor");
  }

  public int getImageCornerRadius() {
    final String key = "imageCornerRadius";
    final int defaultCornerRadius = -1;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultCornerRadius;
      }

      final int cornerRadius = this.target.getInt(key);
      return cornerRadius < 0 ? defaultCornerRadius : cornerRadius;
    } catch (JSONException e) {
      return defaultCornerRadius;
    }
  }

  public int getSubtextColor() {
    return this.color.getColor(this.target, "subtextColor", "#000000");
  }

  public int getOvalStrokeColor() {
    return this.color.getColor(this.target, "ovalStrokeColor");
  }
}

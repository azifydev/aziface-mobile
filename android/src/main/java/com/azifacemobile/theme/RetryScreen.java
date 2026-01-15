package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

public class RetryScreen {
  private final Theme theme;
  private final JSONObject target;
  private final Color color;

  public RetryScreen(JSONObject target) {
    this.theme = new Theme();
    this.color = new Color();

    this.target = this.theme.getTarget(target, "retryScreen");
  }

  private int getInt(String key) {
    final int defaultCornerRadius = -1;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultCornerRadius;
      }

      return this.target.getInt(key);
    } catch (JSONException e) {
      return defaultCornerRadius;
    }
  }

  public int getImageBorderWidth() {
    return this.getInt("imageBorderWidth");
  }

  public int getImageBorderColor() {
    return this.color.getColor(this.target, "imageBorderColor");
  }

  public int getImageCornerRadius() {
    return this.getInt("imageCornerRadius");
  }

  public int getSubtextColor() {
    return this.color.getColor(this.target, "subtextColor", "#000000");
  }

  public int getOvalStrokeColor() {
    return this.color.getColor(this.target, "ovalStrokeColor");
  }
}

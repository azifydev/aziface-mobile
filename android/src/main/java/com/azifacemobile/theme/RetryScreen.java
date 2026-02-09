package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class RetryScreen {
  private final Theme theme;
  private final JSONObject target;
  private final Font font;
  private final Color color;

  public RetryScreen(ReactApplicationContext context, JSONObject target) {
    this.theme = new Theme();
    this.font = new Font(context);
    this.color = new Color();

    this.target = this.theme.getTarget(target, "retryScreen");
  }

  private int getInt(String key) {
    final int defaultValue = -1;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultValue;
      }

      return this.target.getInt(key);
    } catch (JSONException e) {
      return defaultValue;
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

  public int getHeaderTextColor() {
    return this.color.getColor(this.target, "headerTextColor", "#000000");
  }

  @Nullable
  public Typeface getHeaderFont() {
    return this.font.getTypography(this.target, "headerFont");
  }

  public int getSubtextColor() {
    return this.color.getColor(this.target, "subtextColor", "#000000");
  }

  @Nullable
  public Typeface getSubtextFont() {
    return this.font.getTypography(this.target, "subtextFont");
  }

  public int getOvalStrokeColor() {
    return this.color.getColor(this.target, "ovalStrokeColor");
  }
}

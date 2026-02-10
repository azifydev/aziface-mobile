package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class ReadyScreen {
  private final Theme theme;
  private final JSONObject target;
  private final Font font;
  private final Color color;

  public ReadyScreen(ReactApplicationContext context, JSONObject target) {
    this.theme = new Theme();
    this.font = new Font(context);
    this.color = new Color();

    this.target = this.theme.getTarget(target, "readyScreen");
  }

  public int getTextBackgroundColor() {
    return this.color.getColor(this.target, "textBackgroundColor", -1);
  }

  public int getTextBackgroundCornerRadius() {
    final int defaultCornerRadius = -1;
    try {
      if (!this.theme.exists(this.target, "textBackgroundCornerRadius")) {
        return defaultCornerRadius;
      }

      final int cornerRadius = this.target.getInt("textBackgroundCornerRadius");
      return cornerRadius < 0 ? defaultCornerRadius : cornerRadius;
    } catch (JSONException e) {
      return defaultCornerRadius;
    }
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

  public int getOvalFillColor() {
    return this.color.getColor(this.target, "ovalFillColor", android.graphics.Color.TRANSPARENT);
  }
}


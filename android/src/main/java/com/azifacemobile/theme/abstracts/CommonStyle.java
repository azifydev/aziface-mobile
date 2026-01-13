package com.azifacemobile.theme.abstracts;

import com.azifacemobile.theme.Color;
import com.azifacemobile.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class CommonStyle {
  private final Theme theme;
  private final JSONObject target;
  private final Color color;

  public CommonStyle(String key) {
    this.theme = new Theme();
    this.color = new Color();

    this.target = this.theme.getTarget(key);
  }

  public CommonStyle(JSONObject theme, String key) {
    this.theme = new Theme();
    this.color = new Color();

    this.target = this.theme.getTarget(theme, key);
  }

  public int getBackgroundColor() {
    return this.color.getColor(this.target, "backgroundColor");
  }

  public int getBackgroundColor(String defaultColor) {
    return this.color.getColor(this.target, "backgroundColor", defaultColor);
  }

  public int getForegroundColor() {
    return this.color.getColor(this.target, "foregroundColor");
  }

  public int getForegroundColor(String defaultColor) {
    return this.color.getColor(this.target, "foregroundColor", defaultColor);
  }

  public int getTextBackgroundColor() {
    return this.color.getColor(this.target, "textBackgroundColor", "#026ff4");
  }

  public int getBorderColor() {
    return this.color.getColor(this.target, "borderColor");
  }

  public int getBorderColor(String defaultBorderColor) {
    return this.color.getColor(this.target, "borderColor", defaultBorderColor);
  }

  public int getBorderWidth() {
    final String key = "borderWidth";
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

  public int getCornerRadius() {
    return this.getCornerRadius(20);
  }

  public int getCornerRadius(int defaultBorderRadius) {
    final String key = "cornerRadius";
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultBorderRadius;
      }

      final int borderRadius = this.target.getInt(key);
      return borderRadius < 0 ? defaultBorderRadius : borderRadius;
    } catch (JSONException e) {
      return defaultBorderRadius;
    }
  }

  public int getElevation() {
    return this.getElevation(0);
  }

  public int getElevation(int defaultElevation) {
    final String key = "elevation";
    final int maxElevation = 24;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultElevation;
      }

      final int elevation = this.target.getInt(key);
      return elevation < 0 ? defaultElevation : Math.clamp(elevation, defaultElevation, maxElevation);
    } catch (JSONException e) {
      return defaultElevation;
    }
  }
}

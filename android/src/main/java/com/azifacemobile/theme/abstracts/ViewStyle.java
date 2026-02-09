package com.azifacemobile.theme.abstracts;

import com.azifacemobile.theme.Color;
import com.azifacemobile.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ViewStyle {
  private final Theme theme;
  private final JSONObject target;
  private final Color color;

  public ViewStyle(String key) {
    this.theme = new Theme();
    this.color = new Color();

    this.target = this.theme.getTarget(key);
  }

  public ViewStyle(JSONObject target, String key) {
    this.theme = new Theme();
    this.color = new Color();

    this.target = this.theme.getTarget(target, key);
  }

  private int getInt(String key, int defaultValue) {
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultValue;
      }

      return this.target.getInt(key);
    } catch (JSONException e) {
      return defaultValue;
    }
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

  public int getBorderWidth() {
    final int defaultBorderWidth = -1;
    final int borderWidth = this.getInt("borderWidth", defaultBorderWidth);
    return borderWidth < 0 ? defaultBorderWidth : borderWidth;
  }

  public int getCornerRadius() {
    return this.getCornerRadius(20);
  }

  public int getCornerRadius(int defaultCornerRadius) {
    final int cornerRadius = this.getInt("cornerRadius", defaultCornerRadius);
    return cornerRadius < 0 ? defaultCornerRadius : cornerRadius;
  }

  public int getElevation() {
    return this.getElevation(0);
  }

  public int getElevation(int defaultElevation) {
    final int maxElevation = 24;
    final int elevation = this.getInt("elevation", defaultElevation);
    return elevation < 0 ? defaultElevation : Math.clamp(elevation, defaultElevation, maxElevation);
  }
}

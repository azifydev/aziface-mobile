package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

public class General {
  private final Theme theme;

  public General() {
    this.theme = new Theme();
  }

  public int getBorderRadius(JSONObject theme, String key) {
    final int defaultBorderRadius = 20;
    try {
      if (!this.theme.exists(theme, key)) {
        return defaultBorderRadius;
      }

      final int borderRadius = theme.getInt(key);
      return borderRadius < 0 ? defaultBorderRadius : borderRadius;
    } catch (JSONException e) {
      return defaultBorderRadius;
    }
  }

  public int getBorderRadius(JSONObject theme, String key, int defaultBorderRadius) {
    try {
      if (!this.theme.exists(theme, key)) {
        return defaultBorderRadius;
      }

      final int borderRadius = theme.getInt(key);
      return borderRadius < 0 ? defaultBorderRadius : borderRadius;
    } catch (JSONException e) {
      return defaultBorderRadius;
    }
  }

  public int getElevation(JSONObject theme, String key) {
    final int maxElevation = 24;
    final int defaultElevation = 0;
    try {
      if (!this.theme.exists(theme, key)) {
        return defaultElevation;
      }

      final int elevation = theme.getInt(key);
      return elevation < 0 ? defaultElevation : Math.clamp(elevation, defaultElevation, maxElevation);
    } catch (JSONException e) {
      return defaultElevation;
    }
  }

  public int getElevation(JSONObject theme, String key, int defaultElevation) {
    final int maxElevation = 24;
    try {
      if (!this.theme.exists(theme, key)) {
        return defaultElevation;
      }

      final int elevation = theme.getInt(key);
      return elevation < 0 ? defaultElevation : Math.clamp(elevation, defaultElevation, maxElevation);
    } catch (JSONException e) {
      return defaultElevation;
    }
  }
}

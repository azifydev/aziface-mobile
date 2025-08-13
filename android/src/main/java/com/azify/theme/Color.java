package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Color {
  private final String DEFAULT_COLOR = "#ffffff";

  private Boolean exists(String key) {
    return Config.Theme == null || !Config.Theme.hasKey(key) || Config.Theme.isNull(key);
  }

  private Boolean exists(JSONObject theme, String key) {
    return theme == null || !theme.has(key) || theme.isNull(key);
  }

  private int parseColor(String key, int defaultColor) {
    final int hasAlpha = 9;
    String color = Config.Theme.getString(key);

    if (color == null) {
      return defaultColor;
    }
    if (color.length() == hasAlpha) {
      color = "#" + color.substring(hasAlpha - 2) + color.substring(1, hasAlpha - 2);
    }
    return color.isEmpty() ? defaultColor : android.graphics.Color.parseColor(color);
  }

  private int parseColor(JSONObject theme, String key, int defaultColor) {
    try {
      final int hasAlpha = 9;
      String color = theme.getString(key);

      if (color.length() == hasAlpha) {
        color = "#" + color.substring(hasAlpha - 2) + color.substring(1, hasAlpha - 2);
      }
      return color.isEmpty() ? defaultColor : android.graphics.Color.parseColor(color);
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return defaultColor;
    }
  }

  public int getColor(String key) {
    final int defaultColor = android.graphics.Color.parseColor(this.DEFAULT_COLOR);
    if (this.exists(key)) {
      return defaultColor;
    }

    return this.parseColor(key, defaultColor);
  }

  public int getColor(JSONObject theme, String key) {
    final int defaultColor = android.graphics.Color.parseColor(this.DEFAULT_COLOR);
    if (this.exists(theme, key)) {
      return defaultColor;
    }

    return this.parseColor(theme, key, defaultColor);
  }

  public int getColor(String key, String defaultColor) {
    final int color = android.graphics.Color.parseColor(defaultColor);
    if (this.exists(key)) {
      return color;
    }

    return this.parseColor(key, color);
  }

  public int getColor(JSONObject theme, String key, String defaultColor) {
    final int color = android.graphics.Color.parseColor(defaultColor);
    if (this.exists(theme, key)) {
      return color;
    }

    return this.parseColor(theme, key, color);
  }
}

package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;
import com.azify.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Color {
  private static final String DEFAULT_COLOR = "#ffffff";
  private final Theme theme;

  public Color() {
    this.theme = new Theme();
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
    final int defaultColor = android.graphics.Color.parseColor(DEFAULT_COLOR);
    if (this.theme.exists(key)) {
      return defaultColor;
    }

    return this.parseColor(key, defaultColor);
  }

  public int getColor(JSONObject theme, String key) {
    final int defaultColor = android.graphics.Color.parseColor(DEFAULT_COLOR);
    if (this.theme.exists(theme, key)) {
      return defaultColor;
    }

    return this.parseColor(theme, key, defaultColor);
  }

  public int getColor(String key, String defaultColor) {
    final int color = android.graphics.Color.parseColor(defaultColor);
    if (this.theme.exists(key)) {
      return color;
    }

    return this.parseColor(key, color);
  }

  public int getColor(JSONObject theme, String key, String defaultColor) {
    final int color = android.graphics.Color.parseColor(defaultColor);
    if (this.theme.exists(theme, key)) {
      return color;
    }

    return this.parseColor(theme, key, color);
  }
}

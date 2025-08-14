package com.azify.theme;

import android.util.Log;

import com.azify.utils.Theme;

import org.json.JSONObject;

import java.util.Objects;

public class Message {
  private final Theme theme;

  public Message() {
    this.theme = new Theme();
  }

  private String getMessage(JSONObject theme, String key, String defaultMessage) {
    try {
      if (!this.theme.exists(theme, key)) {
        return defaultMessage;
      }

      return theme.getString(key);
    } catch (Exception error) {
      Log.d("Aziface", Objects.requireNonNull(error.getMessage()));
      return defaultMessage;
    }
  }

  public String getMessage(String target, String key, String defaultMessage) {
    final JSONObject theme = this.theme.getTarget(target);
    return this.getMessage(theme, key, defaultMessage);
  }

  public String getMessage(String target, String parent, String key, String defaultMessage) {
    final JSONObject targetTheme = this.theme.getTarget(target);
    final JSONObject parentTheme = this.theme.getTarget(targetTheme, parent);
    return this.getMessage(parentTheme, key, defaultMessage);
  }
}

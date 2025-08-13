package com.azify.theme;

import android.util.Log;

import org.json.JSONObject;

import java.util.Objects;

public class Message {
  private JSONObject theme;

  private Boolean exists(String key) {
    return this.theme == null || !this.theme.has(key) || this.theme.isNull(key);
  }

  public void setTheme(JSONObject theme) {
    this.theme = theme;
  }

  public String getMessage(String key, String defaultMessage) {
    try {
      if (this.exists(key)) {
        return defaultMessage;
      }

      return theme.getString(key);
    } catch (Exception error) {
      Log.d("Aziface", Objects.requireNonNull(error.getMessage()));
      return defaultMessage;
    }
  }
}

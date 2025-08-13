package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;

import org.json.JSONObject;

import java.util.Objects;

public class Message {
  private JSONObject target;

  private Boolean exists(String key) {
    return this.target == null || !this.target.has(key) || this.target.isNull(key);
  }

  private String getMessage(String key, String defaultMessage) {
    try {
      if (this.exists(key)) {
        return defaultMessage;
      }

      return this.target.getString(key);
    } catch (Exception error) {
      Log.d("Aziface", Objects.requireNonNull(error.getMessage()));
      return defaultMessage;
    }
  }

  public String getMessage(String target, String key, String defaultMessage) {
    try {
      final JSONObject theme = new JSONObject(Config.Theme.toHashMap());
      this.target = theme.getJSONObject(target);

      return this.getMessage(key, defaultMessage);
    } catch (Exception error) {
      Log.d("Aziface", Objects.requireNonNull(error.getMessage()));
      return defaultMessage;
    }
  }

  public String getMessage(String target, String parent, String key, String defaultMessage) {
    try {
      final JSONObject theme = new JSONObject(Config.Theme.toHashMap());
      final JSONObject targetTheme = theme.getJSONObject(target);
      this.target = targetTheme.getJSONObject(parent);

      return this.getMessage(key, defaultMessage);
    } catch (Exception error) {
      Log.d("Aziface", Objects.requireNonNull(error.getMessage()));
      return defaultMessage;
    }
  }
}

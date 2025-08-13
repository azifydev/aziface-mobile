package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;

import org.json.JSONObject;

import java.util.Objects;

public class DefaultMessage {
  private Message message;

  public DefaultMessage() {
    this.message = new Message();
  }

  public String getMessage(String target, String key, String defaultMessage) {
    try {
      final JSONObject theme = new JSONObject(Config.Theme.toHashMap());
      final JSONObject targetTheme = theme.getJSONObject(target);

      this.message.setTheme(targetTheme);
      return this.message.getMessage(key, defaultMessage);
    } catch (Exception error) {
      Log.d("Aziface", Objects.requireNonNull(error.getMessage()));
      return defaultMessage;
    }
  }

  public String getMessage(String target, String parent, String key, String defaultMessage) {
    try {
      final JSONObject theme = new JSONObject(Config.Theme.toHashMap());
      final JSONObject targetTheme = theme.getJSONObject(target);
      final JSONObject parentTheme = targetTheme.getJSONObject(parent);

      this.message.setTheme(parentTheme);
      return this.message.getMessage(key, defaultMessage);
    } catch (Exception error) {
      Log.d("Aziface", Objects.requireNonNull(error.getMessage()));
      return defaultMessage;
    }
  }
}

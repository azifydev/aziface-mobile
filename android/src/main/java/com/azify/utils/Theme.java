package com.azify.utils;

import com.azify.processors.Config;

import org.json.JSONException;
import org.json.JSONObject;

public class Theme {

  public Boolean exists(String key) {
    return Config.Theme != null && Config.Theme.hasKey(key) && !Config.Theme.isNull(key);
  }

  public Boolean exists(JSONObject theme, String key) {
    return theme != null && theme.has(key) && !theme.isNull(key);
  }

  public JSONObject getTarget(String key) {
    try {
      if (!this.exists(key)) {
        return null;
      }
      
      final JSONObject theme = new JSONObject(Config.Theme.toHashMap());
      return theme.getJSONObject(key);
    } catch (JSONException | ExceptionInInitializerError | NoClassDefFoundError e) {
      return null;
    }
  }

  public JSONObject getTarget(JSONObject theme, String key) {
    try {
      if (!this.exists(theme, key)) {
        return null;
      }

      return theme.getJSONObject(key);
    } catch (JSONException | ExceptionInInitializerError | NoClassDefFoundError e) {
      return null;
    }
  }
}

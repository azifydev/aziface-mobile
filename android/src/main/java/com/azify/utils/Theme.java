package com.azify.utils;

import android.util.Log;

import com.azify.processors.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Theme {

  public Boolean exists(String key) {
    return Config.Theme == null || !Config.Theme.hasKey(key) || Config.Theme.isNull(key);
  }

  public Boolean exists(JSONObject theme, String key) {
    return theme == null || !theme.has(key) || theme.isNull(key);
  }
  
  public JSONObject getTarget(String key) {
    try {
      final JSONObject theme = new JSONObject(Config.Theme.toHashMap());
      return theme.getJSONObject(key);
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return null;
    }
  }

  public JSONObject getTarget(JSONObject theme, String key) {
    try {
      return theme.getJSONObject(key);
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return null;
    }
  }
}

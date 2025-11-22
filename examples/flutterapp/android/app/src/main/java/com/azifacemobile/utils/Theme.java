package com.azifacemobile.utils;

import com.facebook.react.bridge.ReadableMap;

import org.json.JSONException;
import org.json.JSONObject;

public class Theme {

  public Boolean exists(String key) {
    final ReadableMap style = com.azifacemobile.theme.Theme.Style;
    return style != null && style.hasKey(key) && !style.isNull(key);
  }

  public Boolean exists(JSONObject theme, String key) {
    return theme != null && theme.has(key) && !theme.isNull(key);
  }

  public JSONObject getTarget(String key) {
    try {
      if (!this.exists(key)) {
        return null;
      }

      final JSONObject theme = new JSONObject(com.azifacemobile.theme.Theme.Style.toHashMap());
      return theme.getJSONObject(key);
    } catch (JSONException e) {
      return null;
    }
  }

  public JSONObject getTarget(JSONObject theme, String key) {
    try {
      if (!this.exists(theme, key)) {
        return null;
      }

      return theme.getJSONObject(key);
    } catch (JSONException e) {
      return null;
    }
  }
}

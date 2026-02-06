package com.azifacemobile.utils;

import com.facebook.react.bridge.ReadableMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import javax.annotation.Nullable;

public class Theme {

  public Boolean exists(String key) {
    final ReadableMap style = com.azifacemobile.theme.Theme.Style;
    return style != null && style.hasKey(key) && !style.isNull(key);
  }

  public Boolean exists(JSONObject theme, String key) {
    return theme != null && theme.has(key) && !theme.isNull(key);
  }

  @Nullable
  public JSONObject getTheme() {
    try {
      final HashMap<String, Object> map = com.azifacemobile.theme.Theme.Style.toHashMap();

      return new JSONObject(map);
    } catch (NullPointerException e) {
      return null;
    }
  }

  @Nullable
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

  @Nullable
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

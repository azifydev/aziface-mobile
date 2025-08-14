package com.azify.theme.abstracts;

import android.util.Log;

import com.azify.theme.Color;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public abstract class CommonStyle {
  private final JSONObject target;
  private final Color color;

  public CommonStyle(JSONObject target, String key) {
    this.target = this.getTarget(target, key);
    this.color = new Color();
  }

  private JSONObject getTarget(JSONObject target, String key) {
    try {
      return target.getJSONObject(key);
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return null;
    }
  }

  public int getBackgroundColor() {
    return this.color.getColor(this.target, "backgroundColor");
  }

  public int getBackgroundColor(String defaultColor) {
    return this.color.getColor(this.target, "backgroundColor", defaultColor);
  }

  public int getForegroundColor() {
    return this.color.getColor(this.target, "foregroundColor");
  }

  public int getForegroundColor(String defaultColor) {
    return this.color.getColor(this.target, "foregroundColor", defaultColor);
  }

  public int getTextBackgroundColor() {
    return this.color.getColor(this.target, "textBackgroundColor", "#026ff4");
  }
}

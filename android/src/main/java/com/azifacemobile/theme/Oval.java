package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

public class Oval {
  private final Theme theme;
  private final JSONObject target;
  private final Color color;

  public Oval() {
    this.theme = new Theme();
    this.color = new Color();

    this.target = this.theme.getTarget("oval");
  }

  private int getInt(String key) {
    final int defaultParamValue = -1;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultParamValue;
      }

      final int paramValue = this.target.getInt(key);
      return paramValue < 0 ? defaultParamValue : paramValue;
    } catch (JSONException e) {
      return defaultParamValue;
    }
  }

  public int getStrokeColor() {
    return this.color.getColor(this.target, "strokeColor", "#026ff4");
  }

  public int getStrokeWidth() {
    return this.getInt("strokeWidth");
  }

  public int getFirstProgressColor() {
    return this.color.getColor(this.target, "firstProgressColor", "#0264dc");
  }

  public int getSecondProgressColor() {
    return this.color.getColor(this.target, "secondProgressColor", "#0264dc");
  }

  public int getProgressRadialOffset() {
    return this.getInt("progressRadialOffset");
  }

  public int getProgressStrokeWidth() {
    return this.getInt("progressStrokeWidth");
  }
}

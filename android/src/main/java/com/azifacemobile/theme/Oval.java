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

  public int getStrokeColor() {
    return this.color.getColor(this.target, "strokeColor", "#026ff4");
  }

  public int getStrokeWidth() {
    final String key = "strokeWidth";
    final int defaultStrokeWidth = -1;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultStrokeWidth;
      }

      final int strokeWidth = this.target.getInt(key);
      return strokeWidth < 0 ? defaultStrokeWidth : strokeWidth;
    } catch (JSONException e) {
      return defaultStrokeWidth;
    }
  }

  public int getFirstProgressColor() {
    return this.color.getColor(this.target, "firstProgressColor", "#0264dc");
  }

  public int getSecondProgressColor() {
    return this.color.getColor(this.target, "secondProgressColor", "#0264dc");
  }

  public int getProgressRadialOffset() {
    final String key = "progressRadialOffset";
    final int defaultProgressRadialOffset = -1;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultProgressRadialOffset;
      }

      final int progressRadialOffset = this.target.getInt(key);
      return progressRadialOffset < 0 ? defaultProgressRadialOffset : progressRadialOffset;
    } catch (JSONException e) {
      return defaultProgressRadialOffset;
    }
  }

  public int getProgressStrokeWidth() {
    final String key = "progressStrokeWidth";
    final int defaultProgressStrokeWidth = -1;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultProgressStrokeWidth;
      }

      final int progressStrokeWidth = this.target.getInt(key);
      return progressStrokeWidth < 0 ? defaultProgressStrokeWidth : progressStrokeWidth;
    } catch (JSONException e) {
      return defaultProgressStrokeWidth;
    }
  }
}

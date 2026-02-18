package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

public class Oval extends ViewStyle {
  private static final String KEY = "oval";
  private final Theme theme;
  private final JSONObject target;
  private final Color color;

  public Oval() {
    super(KEY);

    this.theme = new Theme();
    this.color = new Color();

    this.target = this.theme.getTarget(KEY);
  }

  private int getInt(String key) {
    final int defaultValue = -1;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultValue;
      }

      return this.target.getInt(key);
    } catch (JSONException e) {
      return defaultValue;
    }
  }

  @Override
  public int getStrokeColor() {
    return super.getStrokeColor("#026ff4");
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

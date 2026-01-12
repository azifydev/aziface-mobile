package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.CommonStyle;
import com.azifacemobile.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

public class Frame extends CommonStyle {
  private static final String KEY = "frame";
  private final Theme theme;
  private final JSONObject target;
  private final Color color;
  private final General general;

  public Frame() {
    super(KEY);

    this.theme = new Theme();
    this.color = new Color();
    this.general = new General();

    this.target = this.theme.getTarget(KEY);
  }

  public int getCornerRadius() {
    return this.general.getBorderRadius(this.target, "cornerRadius");
  }

  public int getBorderColor() {
    return this.color.getColor(this.target, "borderColor");
  }

  public int getBorderWidth() {
    final String key = "borderWidth";
    final int defaultBorderWidth = -1;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultBorderWidth;
      }

      final int borderWidth = this.target.getInt(key);
      return borderWidth < 0 ? defaultBorderWidth : borderWidth;
    } catch (JSONException e) {
      return defaultBorderWidth;
    }
  }

  public int getElevation() {
    return this.general.getElevation(this.target, "elevation");
  }
}

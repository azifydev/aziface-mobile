package com.azifacemobile.theme.abstracts;

import com.azifacemobile.theme.Color;
import com.azifacemobile.utils.Theme;

import org.json.JSONObject;

public abstract class CommonStyle {
  private final JSONObject theme;
  private final Color color;

  public CommonStyle(String key) {
    this.theme = new Theme().getTarget(key);
    this.color = new Color();
  }

  public CommonStyle(JSONObject theme, String key) {
    this.theme = new Theme().getTarget(theme, key);
    this.color = new Color();
  }

  public int getBackgroundColor() {
    return this.color.getColor(this.theme, "backgroundColor");
  }

  public int getBackgroundColor(String defaultColor) {
    return this.color.getColor(this.theme, "backgroundColor", defaultColor);
  }

  public int getForegroundColor() {
    return this.color.getColor(this.theme, "foregroundColor");
  }

  public int getForegroundColor(String defaultColor) {
    return this.color.getColor(this.theme, "foregroundColor", defaultColor);
  }

  public int getTextBackgroundColor() {
    return this.color.getColor(this.theme, "textBackgroundColor", "#026ff4");
  }
}

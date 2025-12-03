package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;

import org.json.JSONObject;

public class Button {
  private final JSONObject theme;
  private final Color color;

  public Button(JSONObject theme) {
    this.theme = new Theme().getTarget(theme, "button");
    this.color = new Color();
  }

  public int getBackgroundNormalColor() {
    return this.color.getColor(this.theme, "backgroundNormalColor", "#026ff4");
  }

  public int getBackgroundDisabledColor() {
    return this.color.getColor(this.theme, "backgroundDisabledColor", "#b3d4fc");
  }

  public int getBackgroundHighlightColor() {
    return this.color.getColor(this.theme, "backgroundHighlightColor", "#0264dc");
  }

  public int getTextNormalColor() {
    return this.color.getColor(this.theme, "textNormalColor");
  }

  public int getTextDisabledColor() {
    return this.color.getColor(this.theme, "textDisabledColor");
  }

  public int getTextHighlightColor() {
    return this.color.getColor(this.theme, "textHighlightColor");
  }
}

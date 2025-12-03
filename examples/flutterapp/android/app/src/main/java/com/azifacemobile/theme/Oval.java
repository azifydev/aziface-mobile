package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;

import org.json.JSONObject;

public class Oval {
  private final JSONObject theme;
  private final Color color;

  public Oval() {
    this.theme = new Theme().getTarget("oval");
    this.color = new Color();
  }

  public int getStrokeColor() {
    return this.color.getColor(this.theme, "strokeColor", "#026ff4");
  }

  public int getFirstProgressColor() {
    return this.color.getColor(this.theme, "firstProgressColor", "#0264dc");
  }

  public int getSecondProgressColor() {
    return this.color.getColor(this.theme, "secondProgressColor", "#0264dc");
  }
}

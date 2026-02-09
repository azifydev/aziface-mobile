package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;

import org.json.JSONObject;

public class ReadyScreen {
  private final JSONObject target;
  private final Color color;

  public ReadyScreen(JSONObject target) {
    this.color = new Color();

    this.target = new Theme().getTarget(target, "readyScreen");
  }

  public int getHeaderTextColor() {
    return this.color.getColor(this.target, "headerTextColor", "#000000");
  }

  public int getSubtextColor() {
    return this.color.getColor(this.target, "subtextColor", "#000000");
  }

  public int getOvalFillColor() {
    return this.color.getColor(this.target, "ovalFillColor", android.graphics.Color.TRANSPARENT);
  }
}


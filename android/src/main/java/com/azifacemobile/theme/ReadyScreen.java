package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

public class ReadyScreen {
  private final Theme theme;
  private final JSONObject target;
  private final Color color;

  public ReadyScreen(JSONObject target) {
    this.theme = new Theme();
    this.color = new Color();

    this.target = this.theme.getTarget(target, "readyScreen");
  }

  private String getString(String key) {
    final String empty = "";
    try {
      if (!this.theme.exists(this.target, key)) {
        return empty;
      }

      return this.target.getString(key);
    } catch (JSONException e) {
      return empty;
    }
  }

  public String getHeaderText() {
    return this.getString("headerText");
  }

  public int getHeaderTextColor() {
    return this.color.getColor(this.target, "headerTextColor", "#000000");
  }

  public String getSubtext() {
    return this.getString("subtext");
  }

  public int getSubtextColor() {
    return this.color.getColor(this.target, "subtextColor", "#000000");
  }

  public int getOvalFillColor() {
    final String transparent = "#00000000";
    return this.color.getColor(this.target, "ovalFillColor", transparent);
  }
}


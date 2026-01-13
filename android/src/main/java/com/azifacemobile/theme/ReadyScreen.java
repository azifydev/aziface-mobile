package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

public class ReadyScreen {
  private static final String KEY = "readyScreen";
  private final Theme theme;
  private final JSONObject target;
  private final Color color;

  public ReadyScreen(JSONObject target) {
    this.theme = new Theme();
    this.color = new Color();

    this.target = this.theme.getTarget(target, "readyScreen");
  }

  public int getTextBackgroundColor() {
    return this.color.getColor(this.target, "textBackgroundColor", "#000000");
  }

  public String getHeaderText() {
    final String key = "headerText";
    try {
      if (!this.theme.exists(this.target, key)) {
        return "";
      }

      return this.target.getString(key);
    } catch (JSONException e) {
      return "";
    }
  }

  public int getHeaderTextColor() {
    return this.color.getColor(this.target, "headerTextColor", "#000000");
  }

  public String getSubtext() {
    final String key = "subtext";
    try {
      if (!this.theme.exists(this.target, key)) {
        return "";
      }

      return this.target.getString(key);
    } catch (JSONException e) {
      return "";
    }
  }

  public int getSubtextColor() {
    return this.color.getColor(this.target, "subtextColor", "#000000");
  }

  public int getOvalFillColor() {
    return this.color.getColor(this.target, "ovalFillColor", "#00000000");
  }
}


package com.azify.theme;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Button {
  private final JSONObject target;
  private final Color color;

  public Button(JSONObject target) {
    this.target = this.getTarget(target);
    this.color = new Color();
  }

  private JSONObject getTarget(JSONObject target) {
    try {
      return target.getJSONObject("button");
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return null;
    }
  }

  public int getBackgroundNormalColor() {
    return this.color.getColor(this.target, "backgroundNormalColor", "#026ff4");
  }

  public int getBackgroundDisabledColor() {
    return this.color.getColor(this.target, "backgroundDisabledColor", "#b3d4fc");
  }

  public int getBackgroundHighlightColor() {
    return this.color.getColor(this.target, "backgroundHighlightColor", "#0264dc");
  }

  public int getTextNormalColor() {
    return this.color.getColor(this.target, "textNormalColor");
  }

  public int getTextDisabledColor() {
    return this.color.getColor(this.target, "textDisabledColor");
  }

  public int getTextHighlightColor() {
    return this.color.getColor(this.target, "textHighlightColor");
  }
}

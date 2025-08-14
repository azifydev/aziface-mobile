package com.azify.theme;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class RetryScreen {
  private final JSONObject target;
  private final Color color;

  public RetryScreen(JSONObject target) {
    this.target = this.getTarget(target);
    this.color = new Color();
  }

  private JSONObject getTarget(JSONObject target) {
    try {
      return target.getJSONObject("retryScreen");
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return null;
    }
  }

  public int getImageBorderColor() {
    return this.color.getColor(this.target, "imageBorderColor");
  }

  public int getOvalStrokeColor() {
    return this.color.getColor(this.target, "ovalStrokeColor");
  }
}

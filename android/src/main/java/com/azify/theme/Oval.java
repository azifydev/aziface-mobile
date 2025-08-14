package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Oval {
  private final JSONObject target;
  private final Color color;

  public Oval() {
    this.target = this.getTarget();
    this.color = new Color();
  }

  private JSONObject getTarget() {
    try {
      final JSONObject theme = new JSONObject(Config.Theme.toHashMap());
      return theme.getJSONObject("oval");
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return null;
    }
  }

  public int getStrokeColor() {
    return this.color.getColor(this.target, "strokeColor", "#026ff4");
  }

  public int getFirstProgressColor() {
    return this.color.getColor(this.target, "firstProgressColor", "#0264dc");
  }

  public int getSecondProgressColor() {
    return this.color.getColor(this.target, "secondProgressColor", "#0264dc");
  }
}

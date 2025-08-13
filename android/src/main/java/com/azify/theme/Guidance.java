package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Guidance {
  private final JSONObject target;
  private final Color color;
  private final Button button;

  public Guidance() {
    this.target = this.getTarget();
    this.color = new Color();
    this.button = new Button(this.target);
  }

  private JSONObject getTarget() {
    try {
      final JSONObject theme = new JSONObject(Config.Theme.toHashMap());
      return theme.getJSONObject("guidance");
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return null;
    }
  }

  public int getBackgroundColor() {
    return this.color.getColor(this.target, "backgroundColor");
  }

  public int getForegroundColor() {
    return this.color.getColor(this.target, "foregroundColor", "#272937");
  }

  public Button getButton() {
    return this.button;
  }
}

package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Frame {
  private final JSONObject target;
  private final Color color;
  private final FaceTec faceTec;

  public Frame() {
    this.target = getTarget();
    this.color = new Color();
    this.faceTec = new FaceTec();
  }

  private JSONObject getTarget() {
    try {
      final JSONObject theme = new JSONObject(Config.Theme.toHashMap());
      return theme.getJSONObject("frame");
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return null;
    }
  }

  public int getCornerRadius() {
    return faceTec.getBorderRadius(this.target, "cornerRadius");
  }

  public int getBackgroundColor() {
    return this.color.getColor(this.target, "backgroundColor");
  }

  public int getBorderColor() {
    return this.color.getColor(this.target, "borderColor");
  }
}

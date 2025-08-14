package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;
import com.azify.theme.abstracts.CommonStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Feedback extends CommonStyle {
  private static final String KEY = "feedback";
  private final JSONObject target;
  private final Color color;

  public Feedback() {
    super(new JSONObject(Config.Theme.toHashMap()), KEY);

    this.target = this.getTarget();
    this.color = new Color();
  }

  private JSONObject getTarget() {
    try {
      final JSONObject theme = new JSONObject(Config.Theme.toHashMap());
      return theme.getJSONObject(KEY);
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return null;
    }
  }

  @Override
  public int getBackgroundColor() {
    return this.getBackgroundColor("#026ff4");
  }

  public int getTextColor() {
    return this.color.getColor(this.target, "textColor");
  }
}

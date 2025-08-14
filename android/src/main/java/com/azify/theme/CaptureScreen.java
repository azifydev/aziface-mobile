package com.azify.theme;

import android.util.Log;

import com.azify.theme.abstracts.CommonStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class CaptureScreen extends CommonStyle {
  private static final String KEY = "captureScreen";
  private final JSONObject target;
  private final Color color;

  public CaptureScreen(JSONObject target) {
    super(target, KEY);

    this.target = this.getTarget(target);
    this.color = new Color();
  }

  private JSONObject getTarget(JSONObject target) {
    try {
      return target.getJSONObject(KEY);
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return null;
    }
  }

  public int getFrameStrokeColor() {
    return this.color.getColor(this.target, "frameStrokeColor");
  }
}

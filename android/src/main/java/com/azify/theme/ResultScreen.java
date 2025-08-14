package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;
import com.azify.theme.abstracts.CommonStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class ResultScreen extends CommonStyle {
  private static final String KEY = "resultScreen";
  private final JSONObject target;
  private final Color color;
  private final ResultAnimation resultAnimation;

  public ResultScreen() {
    super(new JSONObject(Config.Theme.toHashMap()), KEY);
    this.target = this.getTarget();
    this.color = new Color();
    this.resultAnimation = new ResultAnimation(target);
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
  public int getForegroundColor() {
    return this.getForegroundColor("#272937");
  }

  public int getActivityIndicatorColor() {
    return this.color.getColor(this.target, "activityIndicatorColor", "#026ff4");
  }

  public int getUploadProgressFillColor() {
    return this.color.getColor(this.target, "uploadProgressFillColor", "#026ff4");
  }

  public ResultAnimation getResultAnimation() {
    return resultAnimation;
  }
}

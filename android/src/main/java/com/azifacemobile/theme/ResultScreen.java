package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class ResultScreen extends ViewStyle {
  private static final String KEY = "resultScreen";
  private final Theme theme;
  private final JSONObject target;
  private final Font font;
  private final Color color;
  private final ResultAnimation resultAnimation;
  private final SessionAbortAnimation sessionAbortAnimation;

  public ResultScreen(ReactApplicationContext context) {
    super(KEY);

    this.theme = new Theme();
    this.font = new Font(context);
    this.color = new Color();

    this.target = this.theme.getTarget(KEY);

    this.resultAnimation = new ResultAnimation(context, this.target);
    this.sessionAbortAnimation = new SessionAbortAnimation(context, this.target);
  }

  @Override
  public int getForegroundColor() {
    return super.getForegroundColor("#272937");
  }

  public int getActivityIndicatorColor() {
    return this.color.getColor(this.target, "activityIndicatorColor", "#026ff4");
  }

  public int getUploadProgressFillColor() {
    return this.color.getColor(this.target, "uploadProgressFillColor", "#026ff4");
  }

  public int getUploadProgressTrackColor() {
    return this.color.getColor(this.target, "uploadProgressTrackColor", "#b3d4fc");
  }

  public boolean getShowUploadProgressBar() {
    final String key = "isShowUploadProgressBar";
    try {
      if (!this.theme.exists(this.target, key)) {
        return true;
      }

      return this.target.getBoolean(key);
    } catch (JSONException e) {
      return true;
    }
  }

  @Nullable
  public Typeface getFont() {
    return this.font.getTypography(this.target, "font");
  }

  public ResultAnimation getResultAnimation() {
    return this.resultAnimation;
  }

  public SessionAbortAnimation getSessionAbortAnimation() {
    return this.sessionAbortAnimation;
  }
}

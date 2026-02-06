package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultScreen extends ViewStyle {
  private static final String KEY = "resultScreen";
  private final Theme theme;
  private final JSONObject target;
  private final Color color;
  private final Image image;
  private final ResultAnimation resultAnimation;
  private final SessionAbortAnimation sessionAbortAnimation;

  public ResultScreen(ReactApplicationContext context) {
    super(KEY);

    this.theme = new Theme();
    this.color = new Color();

    this.target = this.theme.getTarget(KEY);

    this.image = new Image(context, this.target);
    this.resultAnimation = new ResultAnimation(context, this.target);
    this.sessionAbortAnimation = new SessionAbortAnimation(context, this.target);
  }

  private double getDouble(String key, double defaultValue) {
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultValue;
      }

      final double value = this.target.getDouble(key);
      return value < 0 ? defaultValue : value;
    } catch (JSONException e) {
      return defaultValue;
    }
  }

  @Override
  public int getForegroundColor() {
    return super.getForegroundColor("#272937");
  }

  public int getActivityIndicatorColor() {
    return this.color.getColor(this.target, "activityIndicatorColor", "#026ff4");
  }

  public int getActivityIndicatorImage() {
    return this.image.getSource("indicatorImage");
  }

  public double getFaceScanStillUploadingMessageDelayTime() {
    return this.getDouble("faceScanStillUploadingMessageDelayTime", 6.0d);
  }

  public double getIdScanStillUploadingMessageDelayTime() {
    return this.getDouble("idScanStillUploadingMessageDelayTime", 8.0d);
  }

  public int getIndicatorRotationInterval() {
    final String key = "indicatorRotationInterval";
    final int defaultRotationInterval = 1000;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultRotationInterval;
      }

      final int indicatorRotationInterval = this.target.getInt(key);
      return indicatorRotationInterval < 0 ? defaultRotationInterval : indicatorRotationInterval;
    } catch (JSONException e) {
      return defaultRotationInterval;
    }
  }

  public int getUploadProgressFillColor() {
    return this.color.getColor(this.target, "uploadProgressFillColor", "#026ff4");
  }

  public int getUploadProgressTrackColor() {
    return this.color.getColor(this.target, "uploadProgressTrackColor", "#b3d4fc");
  }

  public float getAnimationRelativeScale() {
    final String key = "animationRelativeScale";
    final float defaultAnimationRelativeScale = 1.0f;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultAnimationRelativeScale;
      }

      final float animationRelativeScale = (float) this.target.getDouble(key);
      return animationRelativeScale < 0 ? defaultAnimationRelativeScale : animationRelativeScale;
    } catch (JSONException e) {
      return defaultAnimationRelativeScale;
    }
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

  public ResultAnimation getResultAnimation() {
    return this.resultAnimation;
  }

  public SessionAbortAnimation getSessionAbortAnimation() {
    return this.sessionAbortAnimation;
  }
}

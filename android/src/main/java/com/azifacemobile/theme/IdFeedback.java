package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

public class IdFeedback extends ViewStyle {
  private static final String KEY = "idFeedback";
  final Theme theme;
  final JSONObject target;
  final Color color;
  final Image image;

  public IdFeedback(ReactApplicationContext context, JSONObject target) {
    super(target, KEY);

    this.theme = new Theme();

    this.target = this.theme.getTarget(target, KEY);
    this.color = new Color();
    this.image = new Image(context, this.target);
  }

  @Override
  public int getForegroundColor() {
    return super.getForegroundColor("#272937");
  }

  public boolean getDisableIDFeedbackScreen() {
    final String key = "isDisableIDFeedbackScreen";
    final boolean defaultDisableIDFeedback = false;
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultDisableIDFeedback;
      }

      return this.target.getBoolean(key);
    } catch (JSONException e) {
      return defaultDisableIDFeedback;
    }
  }

  public double getDisplayTime() {
    final double defaultDisplayTime = 2.0d;
    final String key = "displayTime";
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultDisplayTime;
      }

      return this.target.getDouble(key);
    } catch (JSONException e) {
      return defaultDisplayTime;
    }
  }

  public int getFlipIDBackImage() {
    return this.image.getSource("flipIDBackImage");
  }

  public int getFlipIDFrontImage() {
    return this.image.getSource("flipIDFrontImage");
  }
}

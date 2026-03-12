package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

public class AdditionalReview extends ViewStyle {
  private static final String KEY = "additionalReview";
  final Theme theme;
  final JSONObject target;
  final Color color;
  final Image image;

  public AdditionalReview(ReactApplicationContext context, JSONObject target) {
    super(target, KEY);

    this.theme = new Theme();

    this.target = this.theme.getTarget(target, KEY);
    this.color = new Color();
    this.image = new Image(context, this.target);
  }

  private boolean getBoolean(String key, boolean defaultValue) {
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultValue;
      }

      return this.target.getBoolean(key);
    } catch (JSONException e) {
      return defaultValue;
    }
  }

  @Override
  public int getForegroundColor() {
    return super.getForegroundColor("#272937");
  }

  public boolean getDisableAdditionalReviewScreen() {
    return this.getBoolean("isDisableAdditionalReviewScreen", false);
  }

  public boolean getEnableAdditionalReviewTag() {
    return this.getBoolean("isEnableAdditionalReviewTag", true);
  }

  public int getTagImageColor() {
    return this.color.getColor(this.target, "tagImageColor", "#CC0044");
  }

  public int getTagTextColor() {
    return this.color.getColor(this.target, "tagTextColor", "#272937");
  }

  public int getReviewImage() {
    return this.image.getSource("reviewImage");
  }

  public int getTagImage() {
    return this.image.getSource("tagImage");
  }
}

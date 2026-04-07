package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class ScrollIndicator extends ViewStyle {
  private static final String KEY = "scrollIndicator";
  private final Theme theme;
  private final JSONObject target;
  private final Color color;
  private final Font font;

  public ScrollIndicator(ReactApplicationContext context, JSONObject target) {
    super(target, KEY);

    this.theme = new Theme();
    this.target = this.theme.getTarget(target, KEY);
    this.color = new Color();
    this.font = new Font(context);
  }

  private boolean getBoolean(String key) {
    try {
      if (!this.theme.exists(this.target, key)) {
        return true;
      }

      return this.target.getBoolean(key);
    } catch (JSONException e) {
      return true;
    }
  }

  @Override
  public int getCornerRadius() {
    return this.getCornerRadius(-1);
  }

  @Override
  public int getElevation() {
    return this.getElevation(10);
  }

  public int getBackgroundNormalColor() {
    return this.color.getColor(this.target, "backgroundNormalColor", "#026ff4");
  }

  public int getBackgroundHighlightColor() {
    return this.color.getColor(this.target, "backgroundHighlightColor", "#0264dc");
  }

  public int getForegroundNormalColor() {
    return this.color.getColor(this.target, "foregroundNormalColor", "#ffffff");
  }

  public int getForegroundHighlightColor() {
    return this.color.getColor(this.target, "foregroundHighlightColor", "#ffffff");
  }

  public boolean getEnableScrollIndicator() {
    return this.getBoolean("showsScrollIndicator");
  }

  public boolean getEnableScrollIndicatorTextAnimation() {
    return this.getBoolean("showsScrollTextAnimation");
  }

  public boolean getShowScrollIndicatorImage() {
    return this.getBoolean("showsScrollImage");
  }

  @Nullable
  public Typeface getFont() {
    return this.font.getTypography(this.target, "font");
  }
}

package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONObject;

import javax.annotation.Nullable;

public class Button extends ViewStyle {
  private static final String KEY = "button";
  private final JSONObject target;
  private final Font font;
  private final Color color;

  public Button(ReactApplicationContext context, JSONObject target) {
    super(target, KEY);

    this.target = new Theme().getTarget(target, KEY);
    this.font = new Font(context);
    this.color = new Color();
  }

  @Override
  public int getCornerRadius() {
    return super.getCornerRadius(-1);
  }

  public int getBackgroundNormalColor() {
    return this.color.getColor(this.target, "backgroundNormalColor", "#026ff4");
  }

  public int getBackgroundDisabledColor() {
    return this.color.getColor(this.target, "backgroundDisabledColor", "#b3d4fc");
  }

  public int getBackgroundHighlightColor() {
    return this.color.getColor(this.target, "backgroundHighlightColor", "#0264dc");
  }

  public int getTextNormalColor() {
    return this.color.getColor(this.target, "textNormalColor");
  }

  public int getTextDisabledColor() {
    return this.color.getColor(this.target, "textDisabledColor");
  }

  public int getTextHighlightColor() {
    return this.color.getColor(this.target, "textHighlightColor");
  }

  @Nullable
  public Typeface getFont() {
    return this.font.getTypography(this.target, "font");
  }
}

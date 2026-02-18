package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONObject;

import javax.annotation.Nullable;

public class ReadyScreen extends ViewStyle {
  private static final String KEY = "readyScreen";
  private final JSONObject target;
  private final Font font;
  private final Color color;

  public ReadyScreen(ReactApplicationContext context, JSONObject target) {
    super(target, KEY);

    this.font = new Font(context);
    this.color = new Color();

    this.target = new Theme().getTarget(target, KEY);
  }

  @Override
  public int getTextBackgroundColor() {
    return super.getTextBackgroundColor(android.graphics.Color.TRANSPARENT);
  }

  public int getHeaderTextColor() {
    return this.color.getColor(this.target, "headerTextColor", "#272937");
  }

  @Nullable
  public Typeface getHeaderFont() {
    return this.font.getTypography(this.target, "headerFont");
  }

  public int getSubtextColor() {
    return this.color.getColor(this.target, "subtextColor", "#272937");
  }

  @Nullable
  public Typeface getSubtextFont() {
    return this.font.getTypography(this.target, "subtextFont");
  }

  public int getOvalFillColor() {
    return this.color.getColor(this.target, "ovalFillColor", android.graphics.Color.TRANSPARENT);
  }
}


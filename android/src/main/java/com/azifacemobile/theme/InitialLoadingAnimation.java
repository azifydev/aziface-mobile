package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONObject;

public class InitialLoadingAnimation {
  private final JSONObject target;
  private final Color color;
  private final Font font;

  public InitialLoadingAnimation(ReactApplicationContext context) {
    this.target = new Theme().getTarget("initialLoadingAnimation");
    this.color = new Color();
    this.font = new Font(context);
  }

  public int getTrackColor() {
    return this.color.getColor(this.target, "trackColor", "#b3d4fc");
  }

  public int getFillColor() {
    return this.color.getColor(this.target, "fillColor", "#026ff4");
  }

  public Typeface getFont() {
    return this.font.getTypography(this.target, "font");
  }
}

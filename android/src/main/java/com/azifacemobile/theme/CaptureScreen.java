package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONObject;

import javax.annotation.Nullable;

public class CaptureScreen extends ViewStyle {
  private static final String KEY = "captureScreen";
  private final JSONObject target;
  private final Font font;
  private final Color color;

  public CaptureScreen(ReactApplicationContext context, JSONObject target) {
    super(target, KEY);

    this.target = new Theme().getTarget(target, KEY);
    this.font = new Font(context);
    this.color = new Color();
  }

  public int getFrameStrokeColor() {
    return this.color.getColor(this.target, "frameStrokeColor");
  }

  @Nullable
  public Typeface getFont() {
    return this.font.getTypography(this.target, "font");
  }
}

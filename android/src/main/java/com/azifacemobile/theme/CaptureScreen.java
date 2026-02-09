package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;

import org.json.JSONObject;

public class CaptureScreen extends ViewStyle {
  private static final String KEY = "captureScreen";
  private final JSONObject theme;
  private final Color color;

  public CaptureScreen(JSONObject target) {
    super(target, KEY);

    this.theme = new Theme().getTarget(target, KEY);
    this.color = new Color();
  }

  public int getFrameStrokeColor() {
    return this.color.getColor(this.theme, "frameStrokeColor");
  }
}

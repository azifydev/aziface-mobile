package com.azify.theme;

import com.azify.theme.abstracts.CommonStyle;
import com.azify.utils.Theme;

import org.json.JSONObject;

public class CaptureScreen extends CommonStyle {
  private static final String KEY = "captureScreen";
  private final JSONObject theme;
  private final Color color;

  public CaptureScreen(JSONObject theme) {
    super(theme, KEY);

    this.theme = new Theme().getTarget(theme, KEY);
    this.color = new Color();
  }

  public int getFrameStrokeColor() {
    return this.color.getColor(this.theme, "frameStrokeColor");
  }
}

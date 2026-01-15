package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONObject;

public class SessionAbortAnimation extends ViewStyle {
  private final static String KEY = "selectionScreen";
  private final Image image;

  public SessionAbortAnimation(ReactApplicationContext context, JSONObject target) {
    super(target, KEY);

    JSONObject targetTheme = new Theme().getTarget(KEY);

    this.image = new Image(context, targetTheme);
  }

  @Override
  public int getBackgroundColor() {
    return super.getBackgroundColor("#CC0044");
  }

  public int getBackgroundImage() {
    return this.image.getSource("image");
  }
}

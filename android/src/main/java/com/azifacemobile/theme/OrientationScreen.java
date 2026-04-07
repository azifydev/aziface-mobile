package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONObject;

import javax.annotation.Nullable;

public class OrientationScreen extends ViewStyle {
  private final static String KEY = "orientationScreen";
  private final JSONObject target;
  private final Font font;
  private final Image image;

  public OrientationScreen(ReactApplicationContext context) {
    super(KEY);

    this.target = new Theme().getTarget(KEY);
    this.font = new Font(context);
    this.image = new Image(context, this.target);
  }

  @Override
  public int getForegroundColor() {
    return super.getForegroundColor("#026ff4");
  }

  @Nullable
  public Typeface getFont() {
    return this.font.getTypography(this.target, "font");
  }

  public int getIconImage() {
    return this.image.getSource("iconImage");
  }
}

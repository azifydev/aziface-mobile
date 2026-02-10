package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

public class Feedback extends ViewStyle {
  private static final String KEY = "feedback";
  private final Theme theme;
  private final JSONObject target;
  private final Font font;
  private final Color color;

  public Feedback(ReactApplicationContext context) {
    super(KEY);

    this.theme = new Theme();
    this.font = new Font(context);
    this.color = new Color();

    this.target = this.theme.getTarget(KEY);
  }

  @Override
  public int getBackgroundColor() {
    return super.getBackgroundColor("#026ff4");
  }

  @Override
  public int getCornerRadius() {
    return super.getCornerRadius(-1);
  }

  @Override
  public int getElevation() {
    return this.getElevation(10);
  }

  public int getTextColor() {
    return this.color.getColor(this.target, "textColor");
  }

  public boolean getEnablePulsatingText() {
    final String key = "isEnablePulsatingText";
    try {
      if (!this.theme.exists(this.target, key)) {
        return true;
      }

      return this.target.getBoolean(key);
    } catch (JSONException e) {
      return true;
    }
  }

  public Typeface getFont() {
    return this.font.getTypography(this.target, "font");
  }
}

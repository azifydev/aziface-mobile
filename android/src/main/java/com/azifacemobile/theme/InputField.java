package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class InputField extends ViewStyle {
  private static final String KEY = "inputField";
  private final Theme theme;
  private final JSONObject target;
  private final Color color;
  private final Font font;

  public InputField(ReactApplicationContext context, JSONObject target) {
    super(target, KEY);

    this.theme = new Theme();
    this.target = this.theme.getTarget(target, KEY);
    this.color = new Color();
    this.font = new Font(context);
  }

  @Override
  public int getBackgroundColor() {
    return super.getBackgroundColor(Color.TRANSPARENT);
  }

  @Override
  public int getBorderColor() {
    return super.getBorderColor("#0264dc");
  }

  @Override
  public int getCornerRadius() {
    return super.getCornerRadius(4);
  }

  public int getTextColor() {
    return this.color.getColor(this.target, "textColor", "#272937");
  }

  public int getPlaceholderTextColor() {
    return this.color.getColor(this.target, "placeholderTextColor", Color.TRANSPARENT);
  }

  public boolean getShowInputFieldBottomBorderOnly() {
    final String key = "showsBorderBottomOnly";
    try {
      if (!this.theme.exists(this.target, key)) {
        return false;
      }

      return this.target.getBoolean(key);
    } catch (JSONException e) {
      return false;
    }
  }

  @Nullable
  public Typeface getFont() {
    return this.font.getTypography(this.target, "font");
  }
}

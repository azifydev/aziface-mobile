package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class OcrConfirmation extends ViewStyle {
  private static final String KEY = "ocrConfirmation";
  private final Theme theme;
  private final JSONObject target;
  private final Color color;
  private final Font font;
  private final Button button;
  private final ScrollIndicator scrollIndicator;
  private final InputField inputField;

  public OcrConfirmation(ReactApplicationContext context) {
    super(KEY);

    this.theme = new Theme();
    this.target = this.theme.getTarget(KEY);
    this.color = new Color();
    this.font = new Font(context);
    this.button = new Button(context, this.target);
    this.scrollIndicator = new ScrollIndicator(context, this.target);
    this.inputField = new InputField(context, this.target);
  }

  @Override
  public int getBackgroundColor() {
    return super.getBackgroundColor(Color.TRANSPARENT);
  }

  public boolean getEnableFixedConfirmButton() {
    final String key = "isFixedConfirmButton";
    try {
      if (!this.theme.exists(this.target, key)) {
        return false;
      }

      return this.target.getBoolean(key);
    } catch (JSONException e) {
      return false;
    }
  }

  public int getLineColor() {
    return this.color.getColor(this.target, "lineColor", "#026ff4");
  }

  public int getLineWidth() {
    final int defaultLineWidth = -1;
    final String key = "lineWidth";
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultLineWidth;
      }

      return this.target.getInt(key);
    } catch (JSONException e) {
      return defaultLineWidth;
    }
  }

  public int getHeaderTextColor() {
    return this.color.getColor(this.target, "headerTextColor", "#026ff4");
  }

  @Nullable
  public Typeface getHeaderFont() {
    return this.font.getTypography(this.target, "headerFont");
  }

  public int getSectionTextColor() {
    return this.color.getColor(this.target, "sectionTextColor", "#272937");
  }

  @Nullable
  public Typeface getSectionFont() {
    return this.font.getTypography(this.target, "sectionFont");
  }

  public Button getButton() {
    return this.button;
  }

  public ScrollIndicator getScrollIndicator() {
    return this.scrollIndicator;
  }
  public InputField getInputField() {
    return this.inputField;
  }
}

package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.CommonStyle;
import com.azifacemobile.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

public class Feedback extends CommonStyle {
  private static final String KEY = "feedback";
  private final Theme theme;
  private final JSONObject target;
  private final General general;
  private final Color color;

  public Feedback() {
    super(KEY);

    this.theme = new Theme();
    this.general = new General();
    this.color = new Color();

    this.target = this.theme.getTarget(KEY);
  }

  @Override
  public int getBackgroundColor() {
    return this.getBackgroundColor("#026ff4");
  }

  public int getTextColor() {
    return this.color.getColor(this.target, "textColor");
  }

  public int getBorderRadius() {
    return this.general.getBorderRadius(this.target, "cornerRadius", -1);
  }

  public int getElevation() {
    return this.general.getElevation(this.target, "elevation", 10);
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
}

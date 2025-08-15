package com.azify.theme;

import com.azify.theme.abstracts.CommonStyle;
import com.azify.utils.Theme;

import org.json.JSONObject;

public class Frame extends CommonStyle {
  private static final String KEY = "frame";
  private final JSONObject theme;
  private final Color color;
  private final FaceTec faceTec;

  public Frame() {
    super(KEY);

    this.theme = new Theme().getTarget(KEY);
    this.color = new Color();
    this.faceTec = new FaceTec();
  }

  public int getCornerRadius() {
    return this.faceTec.getBorderRadius(this.theme, "cornerRadius");
  }

  public int getBorderColor() {
    return this.color.getColor(this.theme, "borderColor");
  }
}

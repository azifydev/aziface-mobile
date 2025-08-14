package com.azify.theme;

import com.azify.processors.Config;
import com.azify.theme.abstracts.CommonStyle;
import com.azify.utils.Theme;

import org.json.JSONObject;

public class Feedback extends CommonStyle {
  private static final String KEY = "feedback";
  private final JSONObject theme;
  private final Color color;

  public Feedback() {
    super(new JSONObject(Config.Theme.toHashMap()), KEY);

    this.theme = new Theme().getTarget(KEY);
    this.color = new Color();
  }

  @Override
  public int getBackgroundColor() {
    return this.getBackgroundColor("#026ff4");
  }

  public int getTextColor() {
    return this.color.getColor(this.theme, "textColor");
  }
}

package com.azify.theme;

import com.azify.theme.abstracts.CommonStyle;

import org.json.JSONObject;

public class ResultAnimation extends CommonStyle {
  private static final String KEY = "resultAnimation";

  public ResultAnimation(JSONObject target) {
    super(target, KEY);
  }

  @Override
  public int getBackgroundColor() {
    return this.getBackgroundColor("#026ff4");
  }
}

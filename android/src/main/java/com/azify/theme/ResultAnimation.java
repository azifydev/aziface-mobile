package com.azify.theme;

import com.azify.theme.abstracts.CommonStyle;

import org.json.JSONObject;

public class ResultAnimation extends CommonStyle {

  public ResultAnimation(JSONObject target) {
    super(target, "resultAnimation");
  }

  @Override
  public int getBackgroundColor() {
    return this.getBackgroundColor("#026ff4");
  }
}

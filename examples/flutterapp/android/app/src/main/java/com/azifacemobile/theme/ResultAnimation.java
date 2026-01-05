package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.CommonStyle;

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

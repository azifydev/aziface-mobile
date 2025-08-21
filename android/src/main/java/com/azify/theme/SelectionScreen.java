package com.azify.theme;

import com.azify.theme.abstracts.CommonStyle;

import org.json.JSONObject;

public class SelectionScreen extends CommonStyle {

  public SelectionScreen(JSONObject target) {
    super(target, "selectionScreen");
  }

  @Override
  public int getForegroundColor() {
    return this.getForegroundColor("#272937");
  }
}

package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.CommonStyle;

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

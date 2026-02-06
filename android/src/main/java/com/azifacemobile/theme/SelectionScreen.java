package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.ViewStyle;

import org.json.JSONObject;

public class SelectionScreen extends ViewStyle {

  public SelectionScreen(JSONObject target) {
    super(target, "selectionScreen");
  }

  @Override
  public int getForegroundColor() {
    return super.getForegroundColor("#272937");
  }
}

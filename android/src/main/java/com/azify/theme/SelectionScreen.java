package com.azify.theme;

import com.azify.theme.abstracts.CommonStyle;

import org.json.JSONObject;

public class SelectionScreen extends CommonStyle {
  private static final String KEY = "selectionScreen";

  public SelectionScreen(JSONObject target) {
    super(target, KEY);
  }

  @Override
  public int getForegroundColor() {
    return this.getForegroundColor("#272937");
  }
}

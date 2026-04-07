package com.azifacemobile.theme;

import com.azifacemobile.theme.abstracts.ViewStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

public class SelectionScreen extends ViewStyle {
  private final Theme theme;
  private final JSONObject target;
  private final Image image;

  public SelectionScreen(ReactApplicationContext context, JSONObject target) {
    super(target, "selectionScreen");

    this.target = target;
    this.theme = new Theme();
    this.image = new Image(context, target);
  }

  @Override
  public int getForegroundColor() {
    return super.getForegroundColor("#272937");
  }

  public int getDocumentImage() {
    return this.image.getSource("documentImage");
  }

  public boolean getShowDocumentImage() {
    final String key = "isShowDocumentImage";
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

package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;
import com.azifacemobile.theme.abstracts.ViewStyle;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONObject;

public class ResultAnimation extends ViewStyle {
  private static final String KEY = "resultAnimation";
  private final JSONObject target;
  private final Image image;
  private final Color color;

  public ResultAnimation(ReactApplicationContext context, JSONObject target) {
    super(target, KEY);

    this.target = new Theme().getTarget(target, KEY);

    this.color = new Color();
    this.image = new Image(context, this.target);
  }

  @Override
  public int getBackgroundColor() {
    return super.getBackgroundColor("#026ff4");
  }

  public int getIDScanSuccessForegroundColor() {
    return this.color.getColor(this.target, "idScanSuccessForegroundColor", "#026ff4");
  }

  public int getUnsuccessBackgroundColor() {
    return this.color.getColor(this.target, "unsuccessBackgroundColor", "#CC0044");
  }

  public int getUnsuccessForegroundColor() {
    return this.color.getColor(this.target, "unsuccessForegroundColor");
  }

  public int getSuccessBackgroundImage() {
    return this.image.getSource("successImage");
  }

  public int getUnsuccessBackgroundImage() {
    return this.image.getSource("unsuccessImage");
  }
}

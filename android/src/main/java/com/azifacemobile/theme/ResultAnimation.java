package com.azifacemobile.theme;

import com.azifacemobile.R;
import com.azifacemobile.utils.Theme;
import com.azifacemobile.theme.abstracts.ViewStyle;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultAnimation extends ViewStyle {
  private static final String KEY = "resultAnimation";
  private final Theme theme;
  private final JSONObject target;
  private final Image image;
  private final Color color;

  public ResultAnimation(ReactApplicationContext context, JSONObject target) {
    super(target, KEY);

    this.theme = new Theme();
    this.color = new Color();

    this.target = this.theme.getTarget(target, KEY);

    this.image = new Image(context, this.target);
  }

  @Override
  public int getBackgroundColor() {
    return super.getBackgroundColor("#026ff4");
  }

  public double getDisplayTime() {
    final double defaultDisplayTime = 2.5d;
    final String key = "displayTime";
    try {
      if (!this.theme.exists(this.target, key)) {
        return defaultDisplayTime;
      }

      return this.target.getDouble(key);
    } catch (JSONException e) {
      return defaultDisplayTime;
    }
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

package com.azify.theme;

import com.azify.processors.Config;
import com.azify.theme.abstracts.CommonStyle;
import com.azify.utils.Theme;

import org.json.JSONObject;

public class ResultScreen extends CommonStyle {
  private static final String KEY = "resultScreen";
  private final JSONObject theme;
  private final Color color;
  private final ResultAnimation resultAnimation;

  public ResultScreen() {
    super(new JSONObject(Config.Theme.toHashMap()), KEY);
    this.theme = new Theme().getTarget(KEY);
    this.color = new Color();
    this.resultAnimation = new ResultAnimation(this.theme);
  }

  @Override
  public int getForegroundColor() {
    return this.getForegroundColor("#272937");
  }

  public int getActivityIndicatorColor() {
    return this.color.getColor(this.theme, "activityIndicatorColor", "#026ff4");
  }

  public int getUploadProgressFillColor() {
    return this.color.getColor(this.theme, "uploadProgressFillColor", "#026ff4");
  }

  public ResultAnimation getResultAnimation() {
    return resultAnimation;
  }
}

package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONObject;

import javax.annotation.Nullable;

public class IdScan {
  private final JSONObject target;
  private final Font font;
  private final Button button;
  private final SelectionScreen selectionScreen;
  private final CaptureScreen captureScreen;
  private final ReviewScreen reviewScreen;

  public IdScan(ReactApplicationContext context) {
    this.target = new Theme().getTarget("idScan");

    this.font = new Font(context);
    this.button = new Button(context, this.target);
    this.selectionScreen = new SelectionScreen(this.target);
    this.captureScreen = new CaptureScreen(context, this.target);
    this.reviewScreen = new ReviewScreen(this.target);
  }

  @Nullable
  public Typeface getHeaderFont() {
    return this.font.getTypography(this.target, "headerFont");
  }

  @Nullable
  public Typeface getSubtextFont() {
    return this.font.getTypography(this.target, "subtextFont");
  }

  public Button getButton() {
    return this.button;
  }

  public SelectionScreen getSelectionScreen() {
    return this.selectionScreen;
  }

  public CaptureScreen getCaptureScreen() {
    return this.captureScreen;
  }

  public ReviewScreen getReviewScreen() {
    return this.reviewScreen;
  }
}

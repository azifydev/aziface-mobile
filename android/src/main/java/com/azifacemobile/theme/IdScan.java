package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;

import org.json.JSONObject;

public class IdScan {
  private final Button button;
  private final SelectionScreen selectionScreen;
  private final CaptureScreen captureScreen;
  private final ReviewScreen reviewScreen;

  public IdScan() {
    final JSONObject theme = new Theme().getTarget("idScan");

    this.button = new Button(theme);
    this.selectionScreen = new SelectionScreen(theme);
    this.captureScreen = new CaptureScreen(theme);
    this.reviewScreen = new ReviewScreen(theme);
  }

  public Button getButton() {
    return button;
  }

  public SelectionScreen getSelectionScreen() {
    return this.selectionScreen;
  }

  public CaptureScreen getCaptureScreen() {
    return captureScreen;
  }

  public ReviewScreen getReviewScreen() {
    return reviewScreen;
  }
}

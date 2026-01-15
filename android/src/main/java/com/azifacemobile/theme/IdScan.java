package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;

import org.json.JSONObject;

public class IdScan {
  private final Button button;
  private final SelectionScreen selectionScreen;
  private final CaptureScreen captureScreen;
  private final ReviewScreen reviewScreen;

  public IdScan() {
    final JSONObject target = new Theme().getTarget("idScan");

    this.button = new Button(target);
    this.selectionScreen = new SelectionScreen(target);
    this.captureScreen = new CaptureScreen(target);
    this.reviewScreen = new ReviewScreen(target);
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

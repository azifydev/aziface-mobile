package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class IdScan {
  private final Button button;
  private final SelectionScreen selectionScreen;
  private final CaptureScreen captureScreen;
  private final ReviewScreen reviewScreen;

  public IdScan() {
    JSONObject target = this.getTarget();
    this.button = new Button(target);
    this.selectionScreen = new SelectionScreen(target);
    this.captureScreen = new CaptureScreen(target);
    this.reviewScreen = new ReviewScreen(target);
  }

  private JSONObject getTarget() {
    try {
      final JSONObject theme = new JSONObject(Config.Theme.toHashMap());
      return theme.getJSONObject("idScan");
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return null;
    }
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

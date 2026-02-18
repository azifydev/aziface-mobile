package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.R;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class IdScan {
  private final Theme theme;
  private final JSONObject target;
  private final Font font;
  private final Button button;
  private final Image image;
  private final SelectionScreen selectionScreen;
  private final CaptureScreen captureScreen;
  private final ReviewScreen reviewScreen;
  private final AdditionalReview additionalReview;
  private final IdFeedback idFeedback;

  public IdScan(ReactApplicationContext context) {
    this.theme = new Theme();

    this.target = this.theme.getTarget("idScan");
    this.font = new Font(context);
    this.button = new Button(context, this.target);
    this.image =  new Image(context, this.target);
    this.selectionScreen = new SelectionScreen(context, this.target);
    this.captureScreen = new CaptureScreen(context, this.target);
    this.reviewScreen = new ReviewScreen(this.target);
    this.additionalReview = new AdditionalReview(context, this.target);
    this.idFeedback = new IdFeedback(context, this.target);
  }

  public boolean getShowFaceMatchToIDBrandingImage() {
    final String key = "isShowFaceMatchToIDBrandingImage";
    try {
      if (!this.theme.exists(this.target, key)) {
        return false;
      }

      return this.target.getBoolean(key);
    } catch (JSONException e) {
      return false;
    }
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

  public int getInactiveTorchImage() {
    return this.image.getSource("inactiveTorchImage", R.drawable.facetec_inactive_torch);
  }

  public int getActiveTorchImage() {
    return this.image.getSource("activeTorchImage", R.drawable.facetec_active_torch);
  }

  public int getFaceMatchToIDBrandingImage() {
    return this.image.getSource("faceMatchToIDBrandingImage");
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

  public AdditionalReview getAdditionalReview() {
    return this.additionalReview;
  }

  public IdFeedback getIdFeedback() {
    return this.idFeedback;
  }
}

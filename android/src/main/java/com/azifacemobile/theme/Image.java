package com.azifacemobile.theme;

import android.graphics.Rect;

import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facetec.sdk.FaceTecCancelButtonCustomization;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class Image {
  private static ReactApplicationContext reactContext;
  private final JSONObject theme;
  private boolean isPosition;

  public Image(ReactApplicationContext context) {
    reactContext = context;

    this.theme = new Theme().getTarget("images");
    this.isPosition = false;
  }

  public int getSource(String key) {
    try {
      if (reactContext == null) {
        return 0;
      }

      final String imageName = this.theme.getString(key);

      if (imageName.isEmpty()) {
        return 0;
      }

      final String packageName = reactContext.getPackageName();
      return reactContext.getResources().getIdentifier(imageName, "drawable", packageName);
    } catch (NullPointerException | JSONException e) {
      return 0;
    }
  }

  public int getSource(String key, int defaultImage) {
    try {
      if (reactContext == null) {
        return defaultImage;
      }

      final String imageName = this.theme.getString(key);

      if (imageName.isEmpty()) {
        return defaultImage;
      }

      final String packageName = reactContext.getPackageName();
      final int resourceId = reactContext.getResources().getIdentifier(imageName, "drawable", packageName);
      return resourceId == 0 ? defaultImage : resourceId;
    } catch (NullPointerException | JSONException e) {
      return defaultImage;
    }
  }

  public boolean getShowBranding() {
    try {
      return this.theme.getBoolean("isShowBranding");
    } catch (NullPointerException | JSONException e) {
      return true;
    }
  }

  public boolean getHideForCameraPermissions() {
    try {
      return this.theme.getBoolean("isHideForCameraPermissions");
    } catch (NullPointerException | JSONException e) {
      return false;
    }
  }

  @Nullable
  public Rect getButtonPosition() {
    try {
      final JSONObject cancelPosition = this.theme.getJSONObject("cancelPosition");

      final int left = cancelPosition.getInt("left");
      final int top = cancelPosition.getInt("top");
      final int right = cancelPosition.getInt("right");
      final int bottom = cancelPosition.getInt("bottom");

      this.isPosition = true;

      return new Rect(left, top, right, bottom);
    } catch (NullPointerException | JSONException e) {
      this.isPosition = false;

      return null;
    }
  }

  public FaceTecCancelButtonCustomization.ButtonLocation getButtonLocation() {
    final FaceTecCancelButtonCustomization.ButtonLocation defaultLocation = FaceTecCancelButtonCustomization.ButtonLocation.TOP_RIGHT;
    try {
      final String cancelLocation = this.isPosition ? "CUSTOM" : this.theme.getString("cancelLocation");

      switch (cancelLocation) {
        case "TOP_RIGHT":
          return FaceTecCancelButtonCustomization.ButtonLocation.TOP_RIGHT;
        case "TOP_LEFT":
          return FaceTecCancelButtonCustomization.ButtonLocation.TOP_LEFT;
        case "DISABLED":
          return FaceTecCancelButtonCustomization.ButtonLocation.DISABLED;
        case "CUSTOM":
          return FaceTecCancelButtonCustomization.ButtonLocation.CUSTOM;
        default:
          return defaultLocation;
      }
    } catch (NullPointerException | JSONException e) {
      return defaultLocation;
    }
  }
}

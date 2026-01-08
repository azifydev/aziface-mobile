package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facetec.sdk.FaceTecCancelButtonCustomization;

import org.json.JSONException;
import org.json.JSONObject;

public class Image {
  private static ReactApplicationContext reactContext;
  private final JSONObject theme;

  public Image(ReactApplicationContext context) {
    reactContext = context;

    this.theme = new Theme().getTarget("image");
  }

  public int getImg(String key, int defaultImage) {
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

  public FaceTecCancelButtonCustomization.ButtonLocation getButtonLocation() {
    final FaceTecCancelButtonCustomization.ButtonLocation defaultLocation = FaceTecCancelButtonCustomization.ButtonLocation.TOP_RIGHT;
    try {
      final String cancelLocation = this.theme.getString("cancelLocation");

      switch (cancelLocation) {
        case "TOP_RIGHT":
          return FaceTecCancelButtonCustomization.ButtonLocation.TOP_RIGHT;
        case "TOP_LEFT":
          return FaceTecCancelButtonCustomization.ButtonLocation.TOP_LEFT;
        case "DISABLED":
          return FaceTecCancelButtonCustomization.ButtonLocation.DISABLED;
        default:
          return defaultLocation;
      }
    } catch (NullPointerException | JSONException e) {
      return defaultLocation;
    }
  }
}

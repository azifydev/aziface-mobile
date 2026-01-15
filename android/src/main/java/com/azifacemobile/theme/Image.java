package com.azifacemobile.theme;

import android.graphics.Rect;

import com.azifacemobile.theme.abstracts.ImageStyle;
import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facetec.sdk.FaceTecCancelButtonCustomization;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class Image extends ImageStyle {
  private final static String KEY = "image";
  private final JSONObject target;
  private boolean isPosition;

  public Image(ReactApplicationContext context) {
    super(context, KEY);

    this.target = new Theme().getTarget(KEY);
    this.isPosition = false;
  }

  public Image(ReactApplicationContext context, JSONObject target) {
    super(context, target);

    this.target = target;
    this.isPosition = false;
  }

  public Image(ReactApplicationContext context, JSONObject target, String key) {
    super(context, target, key);

    this.target = target;
    this.isPosition = false;
  }

  private boolean getBoolean(String key, boolean defaultValue) {
    try {
      return this.target.getBoolean(key);
    } catch (NullPointerException | JSONException e) {
      return defaultValue;
    }
  }

  public boolean getShowBranding() {
    return this.getBoolean("isShowBranding", true);
  }

  public boolean getHideForCameraPermissions() {
    return this.getBoolean("isHideForCameraPermissions", false);
  }

  @Nullable
  public Rect getButtonPosition() {
    try {
      final JSONObject cancelPosition = this.target.getJSONObject("cancelPosition");

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
      final String cancelLocation = this.isPosition ? "CUSTOM" : this.target.getString("cancelLocation");

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

package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;
import com.facetec.sdk.FaceTecCancelButtonCustomization.ButtonLocation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class FaceTec {
  private final int DEFAULT_RADIUS = 20;

  private Boolean exists(String key) {
    return Config.Theme == null || !Config.Theme.hasKey(key) || Config.Theme.isNull(key);
  }

  private Boolean exists(JSONObject theme, String key) {
    return theme == null || !theme.has(key) || theme.isNull(key);
  }

  public int getBorderRadius(String key) {
    if (this.exists(key)) {
      return this.DEFAULT_RADIUS;
    }

    final int borderRadius = Config.Theme.getInt(key);
    return borderRadius < 0 ? this.DEFAULT_RADIUS : borderRadius;
  }

  public int getBorderRadius(JSONObject theme, String key) {
    try {
      if (this.exists(theme, key)) {
        return this.DEFAULT_RADIUS;
      }

      final int borderRadius = theme.getInt(key);
      return borderRadius < 0 ? this.DEFAULT_RADIUS : borderRadius;
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return this.DEFAULT_RADIUS;
    }
  }

  public ButtonLocation getButtonLocation(String key) {
    final ButtonLocation defaultLocation = ButtonLocation.TOP_RIGHT;
    if (this.exists(key)) {
      return defaultLocation;
    }

    final String buttonLocation = Config.Theme.getString(key);
    if (buttonLocation == null) {
      return defaultLocation;
    }
    if (buttonLocation.isEmpty()) {
      return defaultLocation;
    }

    switch (buttonLocation) {
      case "TOP_RIGHT":
        return ButtonLocation.TOP_RIGHT;
      case "TOP_LEFT":
        return ButtonLocation.TOP_LEFT;
      case "DISABLED":
        return ButtonLocation.DISABLED;
      default:
        return defaultLocation;
    }
  }
}

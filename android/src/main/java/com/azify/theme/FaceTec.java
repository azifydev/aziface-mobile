package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;
import com.azify.utils.Theme;
import com.facetec.sdk.FaceTecCancelButtonCustomization.ButtonLocation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class FaceTec {
  private final Theme theme;

  public FaceTec() {
    this.theme = new Theme();
  }

  public int getBorderRadius(JSONObject theme, String key) {
    final int defaultBorderRadius = 20;
    try {
      if (this.theme.exists(theme, key)) {
        return defaultBorderRadius;
      }

      final int borderRadius = theme.getInt(key);
      return borderRadius < 0 ? defaultBorderRadius : borderRadius;
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return defaultBorderRadius;
    }
  }

  public ButtonLocation getButtonLocation(String key) {
    final ButtonLocation defaultLocation = ButtonLocation.TOP_RIGHT;
    if (this.theme.exists(key)) {
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

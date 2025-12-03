package com.azifacemobile.theme;

import com.azifacemobile.utils.Theme;
import com.facetec.sdk.FaceTecCancelButtonCustomization.ButtonLocation;

import org.json.JSONException;
import org.json.JSONObject;

public class General {
  private final Theme theme;

  public General() {
    this.theme = new Theme();
  }

  public int getBorderRadius(JSONObject theme, String key) {
    final int defaultBorderRadius = 20;
    try {
      if (!this.theme.exists(theme, key)) {
        return defaultBorderRadius;
      }

      final int borderRadius = theme.getInt(key);
      return borderRadius < 0 ? defaultBorderRadius : borderRadius;
    } catch (JSONException e) {
      return defaultBorderRadius;
    }
  }

  public ButtonLocation getButtonLocation(String key) {
    final ButtonLocation defaultLocation = ButtonLocation.TOP_RIGHT;
    if (!this.theme.exists(key)) {
      return defaultLocation;
    }
    Object value = com.azifacemobile.theme.Theme.Style.get(key);
    final String buttonLocation = (value instanceof String) ? (String) value : null;
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

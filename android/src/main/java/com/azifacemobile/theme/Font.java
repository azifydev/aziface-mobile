package com.azifacemobile.theme;

import android.graphics.Typeface;

import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nullable;

public class Font {
  private static ReactApplicationContext reactContext;
  private final Theme theme;

  public Font(ReactApplicationContext context) {
    reactContext = context;

    this.theme = new Theme();
  }

  @Nullable
  public Typeface getTypography(JSONObject theme, String key) {
    try {
      if (!this.theme.exists(theme, key)) {
        return null;
      }

      final String filename = theme.getString(key);

      return Typeface.createFromAsset(reactContext.getAssets(), "fonts/" + filename);
    } catch (JSONException | RuntimeException e) {
      return null;
    }
  }
}

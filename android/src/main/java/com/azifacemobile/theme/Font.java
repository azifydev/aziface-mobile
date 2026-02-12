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
  private Typeface getAsset(@Nullable String filename) {
    try {
      if (filename == null) return null;

      return Typeface.createFromAsset(reactContext.getAssets(), "fonts/" + filename);
    } catch (RuntimeException e) {
      return null;
    }
  }

  @Nullable
  public Typeface getTypography(JSONObject theme, String key) {
    final @Nullable String globalFontFamily = this.theme.getGlobalFontFamily();

    try {
      if (!this.theme.exists(theme, key)) {
        return this.getAsset(globalFontFamily);
      }

      final String filename = theme.getString(key);
      return this.getAsset(filename);
    } catch (JSONException e) {
      return this.getAsset(globalFontFamily);
    }
  }
}

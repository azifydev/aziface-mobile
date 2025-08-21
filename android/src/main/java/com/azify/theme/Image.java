package com.azify.theme;

import com.azify.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

public class Image {
  private static ReactApplicationContext reactContext;
  private final Theme theme;

  public Image(ReactApplicationContext context) {
    reactContext = context;

    this.theme = new Theme();
  }

  public int getImage(String key, int defaultImage) {
    try {
      final JSONObject theme = this.theme.getTarget("image");

      if (!this.theme.exists(theme, key) || reactContext == null) {
        return defaultImage;
      }

      final String imageName = theme.getString(key);

      if (imageName.isEmpty()) {
        return defaultImage;
      }

      final String packageName = reactContext.getPackageName();
      final int resourceId = reactContext.getResources().getIdentifier(imageName, "drawable", packageName);
      return resourceId == 0 ? defaultImage : resourceId;
    } catch (JSONException e) {
      return defaultImage;
    }
  }
}

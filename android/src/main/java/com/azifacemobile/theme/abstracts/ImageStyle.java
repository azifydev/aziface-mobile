package com.azifacemobile.theme.abstracts;

import com.azifacemobile.utils.Theme;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ImageStyle {
  private static ReactApplicationContext reactContext;
  private final JSONObject target;

  public ImageStyle(ReactApplicationContext context, String key) {
    reactContext = context;

    this.target = new Theme().getTarget(key);
  }

  public ImageStyle(ReactApplicationContext context, JSONObject target) {
    reactContext = context;

    this.target = target;
  }

  public ImageStyle(ReactApplicationContext context, JSONObject target, String key) {
    reactContext = context;

    this.target = new Theme().getTarget(target, key);
  }

  public int getSource(String key) {
    try {
      if (reactContext == null) {
        return 0;
      }

      final String imageName = this.target.getString(key);

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
    final int resourceId = this.getSource(key);
    return resourceId == 0 ? defaultImage : resourceId;
  }
}

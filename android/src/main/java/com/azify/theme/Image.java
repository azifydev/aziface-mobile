package com.azify.theme;

import com.azify.processors.Config;
import com.facebook.react.bridge.ReactApplicationContext;

public class Image {
  private static ReactApplicationContext reactContext;

  public Image(ReactApplicationContext context) {
    reactContext = context;
  }

  private Boolean exists(String key) {
    return Config.Theme == null || !Config.Theme.hasKey(key) || Config.Theme.isNull(key);
  }

  public int getImage(String key, int defaultImage) {
    if (this.exists(key) || reactContext == null) {
      return defaultImage;
    }

    final String imageName = Config.Theme.getString(key);

    if (imageName == null) {
      return defaultImage;
    }
    if (imageName.isEmpty()) {
      return defaultImage;
    }

    final String packageName = reactContext.getPackageName();
    final int resourceId = reactContext.getResources().getIdentifier(imageName, "drawable", packageName);
    return resourceId == 0 ? defaultImage : resourceId;
  }
}

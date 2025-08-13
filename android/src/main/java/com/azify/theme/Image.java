package com.azify.theme;

import android.util.Log;

import com.azify.processors.Config;
import com.facebook.react.bridge.ReactApplicationContext;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Image {
  private static ReactApplicationContext reactContext;
  private JSONObject target;

  public Image(ReactApplicationContext context) {
    reactContext = context;
    this.target = this.getTarget();
  }

  private Boolean exists(String key) {
    return this.target == null || !this.target.has(key) || this.target.isNull(key);
  }

  private JSONObject getTarget() {
    try {
      final JSONObject theme = new JSONObject(Config.Theme.toHashMap());
      return theme.getJSONObject("image");
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return null;
    }
  }

  public int getImage(String key, int defaultImage) {
    try {
      if (this.exists(key) || reactContext == null) {
        return defaultImage;
      }

      final String imageName = this.target.getString(key);

      if (imageName.isEmpty()) {
        return defaultImage;
      }

      final String packageName = reactContext.getPackageName();
      final int resourceId = reactContext.getResources().getIdentifier(imageName, "drawable", packageName);
      return resourceId == 0 ? defaultImage : resourceId;
    } catch (JSONException e) {
      Log.d("Aziface", Objects.requireNonNull(e.getMessage()));
      return defaultImage;
    }
  }
}

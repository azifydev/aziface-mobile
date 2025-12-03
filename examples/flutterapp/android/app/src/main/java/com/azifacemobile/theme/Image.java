package com.azifacemobile.theme;

import android.content.Context;

import com.azifacemobile.utils.Theme;

import org.json.JSONException;
import org.json.JSONObject;

public class Image {
    private final Context context;
    private final Theme theme;

    public Image(Context context) {
        this.context = context;
        this.theme = new Theme();
    }

    public int getImage(String key, int defaultImage) {
        try {
            final JSONObject theme = this.theme.getTarget("image");

            if (!this.theme.exists(theme, key) || context == null) {
                return defaultImage;
            }

            final String imageName = theme.getString(key);

            if (imageName.isEmpty()) {
                return defaultImage;
            }

      final String packageName = context.getPackageName();
      final int resourceId = context.getResources().getIdentifier(imageName, "drawable", packageName);
            return resourceId == 0 ? defaultImage : resourceId;
        } catch (JSONException e) {
            return defaultImage;
        }
    }
}

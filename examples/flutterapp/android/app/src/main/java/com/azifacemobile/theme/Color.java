package com.azifacemobile.theme;
import com.azifacemobile.utils.Theme;
import org.json.JSONException;
import org.json.JSONObject;


public class Color {
    private static final String DEFAULT_COLOR = "#ffffff";
    private final Theme theme;

    public Color() {
        this.theme = new Theme();
    }

    private String parseColor(String color) {
        final boolean isRRGGBBAA = color.length() == 9;
        final boolean isRGBA = color.length() == 5;
        final boolean isRGB = color.length() == 4;

        if (isRRGGBBAA) {
            color = "#" + color.substring(9 - 2) + color.substring(1, 9 - 2);
        } else if (isRGBA) {
            char red = color.charAt(1);
            char green = color.charAt(2);
            char blue = color.charAt(3);
            char alpha = color.charAt(4);
            color = "#" + alpha + "0" + red + red + green + green + blue + blue;
        } else if (isRGB) {
            char red = color.charAt(1);
            char green = color.charAt(2);
            char blue = color.charAt(3);
            color = "#" + red + red + green + green + blue + blue;
        }

        return color;
    }

    private int parseColor(String key, int defaultColor) {
        Object value = com.azifacemobile.theme.Theme.Style.get(key);
        String color = (value instanceof String) ? (String) value : null;

        if (color == null) {
            return defaultColor;
        }

        color = this.parseColor(color);

        return color.isEmpty() ? defaultColor : android.graphics.Color.parseColor(color);
    }

    private int parseColor(JSONObject theme, String key, int defaultColor) {
        try {
            String color = theme.getString(key);
            color = this.parseColor(color);

            return color.isEmpty() ? defaultColor : android.graphics.Color.parseColor(color);
        } catch (JSONException e) {
            return defaultColor;
        }
    }

    public int getColor(String key) {
        final int defaultColor = android.graphics.Color.parseColor(DEFAULT_COLOR);
        if (!this.theme.exists(key)) {
            return defaultColor;
        }

        return this.parseColor(key, defaultColor);
    }

    public int getColor(JSONObject theme, String key) {
        final int defaultColor = android.graphics.Color.parseColor(DEFAULT_COLOR);
        if (!this.theme.exists(theme, key)) {
            return defaultColor;
        }

        return this.parseColor(theme, key, defaultColor);
    }

    public int getColor(String key, String defaultColor) {
        final int color = android.graphics.Color.parseColor(defaultColor);
        if (!this.theme.exists(key)) {
            return color;
        }

        return this.parseColor(key, color);
    }

    public int getColor(JSONObject theme, String key, String defaultColor) {
        final int color = android.graphics.Color.parseColor(defaultColor);
        if (!this.theme.exists(theme, key)) {
            return color;
        }

        return this.parseColor(theme, key, color);
    }
}

package com.azifacemobile.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Theme {

    public Boolean exists(String key) {
        Map<String, Object> style = com.azifacemobile.theme.Theme.Style;
        return style != null && style.containsKey(key) && style.get(key) != null;
    }

    public Boolean exists(JSONObject theme, String key) {
        return theme != null && theme.has(key) && !theme.isNull(key);
    }

    public JSONObject getTarget(String key) {
        try {
            if (!this.exists(key)) {
                return null;
            }

            Map<String, Object> style = com.azifacemobile.theme.Theme.Style;
            JSONObject theme = new JSONObject(style);
            return theme.getJSONObject(key);
        } catch (JSONException e) {
            return null;
        }
    }

    public JSONObject getTarget(JSONObject theme, String key) {
        try {
            if (!this.exists(theme, key)) {
                return null;
            }

            return theme.getJSONObject(key);
        } catch (JSONException e) {
            return null;
        }
    }
}

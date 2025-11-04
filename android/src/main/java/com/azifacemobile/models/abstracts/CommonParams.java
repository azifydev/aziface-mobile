package com.azifacemobile.models.abstracts;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class CommonParams {
  private final JSONObject target;

  public CommonParams(JSONObject target) {
    this.target = target;
  }

  public <T> T defaultIfNull(T value, T defaultValue) {
    return value != null ? value : defaultValue;
  }

  @Nullable
  public <T> T getParam(String field, Class<T> type) {
    if (this.target == null) return null;

    try {
      Object value = this.target.get(field);
      return type.cast(value);
    } catch (JSONException | ClassCastException e) {
      return null;
    }
  }
}

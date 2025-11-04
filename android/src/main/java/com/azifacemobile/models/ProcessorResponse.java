package com.azifacemobile.models;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONObject;

public class ProcessorResponse {
  private final WritableMap object;

  public ProcessorResponse() {
    this.object = Arguments.createMap();

    this.reset();
  }

  private void parseJSON(JSONObject target) {
    final ProcessorData processorData = new ProcessorData(target);

    final WritableMap data = processorData.getMap();
    final String responseBlob = data.getString("responseBlob");

    this.object.putBoolean("isSuccess", responseBlob != null);
    this.object.putMap("data", data);
    this.object.putMap("error", null);
  }

  public Boolean getSuccess() {
    return this.object.getBoolean("isSuccess");
  }

  public ReadableMap getData() {
    return this.object.getMap("data");
  }

  public void setData(JSONObject target) {
    this.parseJSON(target);
  }

  public WritableMap getMap() {
    return this.object.copy();
  }

  public void setError(String message, String code) {
    this.reset();

    final ProcessorError processorError = new ProcessorError(message, code);

    this.object.putMap("error", processorError.getMap());
  }

  public void reset() {
    this.object.putBoolean("isSuccess", false);
    this.object.putMap("data", null);
    this.object.putMap("error", null);
  }
}

package com.azifacemobile.models;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

public class ProcessorError {
  private final WritableMap object;

  public ProcessorError(String message, String code) {
    this.object = Arguments.createMap();

    this.object.putString("message", message);
    this.object.putString("code", code);
  }

  public WritableMap getMap() {
    return this.object.copy();
  }
}

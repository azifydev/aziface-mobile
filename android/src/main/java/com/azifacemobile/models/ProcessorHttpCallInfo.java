package com.azifacemobile.models;

import com.azifacemobile.models.abstracts.CommonParams;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONObject;

public class ProcessorHttpCallInfo extends CommonParams {
  private final WritableMap object;

  public ProcessorHttpCallInfo(JSONObject target) {
    super(target);

    this.object = Arguments.createMap();

    this.parseJSON();
  }

  private void parseJSON() {
    Integer epochSecond = this.getParam("epochSecond", Integer.class);
    String tid = this.getParam("tid", String.class);
    String path = this.getParam("path", String.class);
    String requestMethod = this.getParam("requestMethod", String.class);
    String date = this.getParam("date", String.class);

    this.object.putInt("epochSecond", this.defaultIfNull(epochSecond, -1));
    this.object.putString("tid", tid);
    this.object.putString("path", path);
    this.object.putString("requestMethod", requestMethod);
    this.object.putString("date", date);
  }

  public WritableMap getMap() {
    return this.object.copy();
  }
}

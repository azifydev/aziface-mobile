package com.azifacemobile.models;

import com.azifacemobile.models.abstracts.CommonParams;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONObject;

public class ProcessorAdditionalSessionData extends CommonParams {
  private final WritableMap object;

  public ProcessorAdditionalSessionData(JSONObject target) {
    super(target);

    this.object = Arguments.createMap();

    this.parseJSON();
  }

  private void parseJSON() {
    String platform = this.getParam( "platform", String.class);
    String appID = this.getParam("appID", String.class);
    String installationID = this.getParam("installationID", String.class);
    String deviceModel = this.getParam("deviceModel", String.class);
    String deviceSDKVersion = this.getParam("deviceSDKVersion", String.class);
    String userAgent = this.getParam("userAgent", String.class);
    String sessionID = this.getParam("sessionID", String.class);

    this.object.putString("platform", platform);
    this.object.putString("appID", appID);
    this.object.putString("installationID", installationID);
    this.object.putString("deviceModel", deviceModel);
    this.object.putString("deviceSDKVersion", deviceSDKVersion);
    this.object.putString("userAgent", userAgent);
    this.object.putString("sessionID", sessionID);
  }

  public WritableMap getMap() {
    return this.object.copy();
  }
}

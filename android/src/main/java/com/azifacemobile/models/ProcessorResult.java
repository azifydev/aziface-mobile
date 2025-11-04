package com.azifacemobile.models;

import com.azifacemobile.models.abstracts.CommonParams;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONObject;

public class ProcessorResult extends CommonParams {
  private final WritableMap object;

  public ProcessorResult(JSONObject target) {
    super(target);

    this.object = Arguments.createMap();

    this.parseJSON();
  }

  private void parseJSON() {
    Integer ageV2GroupEnumInt = this.getParam("ageV2GroupEnumInt", Integer.class);
    Boolean livenessProven = this.getParam("livenessProven", Boolean.class);
    String auditTrailImage = this.getParam("auditTrailImage", String.class);
    Integer matchLevel = this.getParam("matchLevel", Integer.class);

    this.object.putInt("ageV2GroupEnumInt", this.defaultIfNull(ageV2GroupEnumInt, -1));
    this.object.putBoolean("livenessProven", Boolean.TRUE.equals(livenessProven));
    this.object.putString("auditTrailImage", auditTrailImage);
    this.object.putInt("matchLevel", this.defaultIfNull(matchLevel, -1));
  }

  public WritableMap getMap() {
    return this.object.copy();
  }
}

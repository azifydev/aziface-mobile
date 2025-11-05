package com.azifacemobile.models;

import androidx.annotation.Nullable;

import com.azifacemobile.models.abstracts.JSONParams;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONObject;

public class ProcessorData extends JSONParams {
  private final WritableMap object;

  public ProcessorData(JSONObject target) {
    super(target);

    this.object = Arguments.createMap();

    this.parseJSON();
  }

  @Nullable
  private WritableMap parseJSONToHttpCallInfo(@Nullable JSONObject target) {
    if (target == null) return null;

    final ProcessorHttpCallInfo processorHttpCallInfo = new ProcessorHttpCallInfo(target);
    return processorHttpCallInfo.getMap();
  }

  @Nullable
  private WritableMap parseJSONToAdditionalSessionData(@Nullable JSONObject target) {
    if (target == null) return null;

    final ProcessorAdditionalSessionData processorAdditionalSessionData = new ProcessorAdditionalSessionData(target);
    return processorAdditionalSessionData.getMap();
  }

  @Nullable
  private WritableMap parseJSONToResult(@Nullable JSONObject target) {
    if (target == null) return null;

    final ProcessorResult processorResult = new ProcessorResult(target);
    return processorResult.getMap();
  }

  @Nullable
  private WritableMap parseJSONToIDScanResultsSoFar(@Nullable JSONObject target) {
    if (target == null) return null;

    final ProcessorIDScanResultsSoFar processorIDScanResultsSoFar = new ProcessorIDScanResultsSoFar(target);
    return processorIDScanResultsSoFar.getMap();
  }

  private void parseJSON() {
    Boolean didError = this.getParam("didError", Boolean.class);
    String responseBlob = this.getParam("responseBlob", String.class);
    JSONObject httpCallInfo = this.getParam("httpCallInfo", JSONObject.class);
    String externalDatabaseRefID = this.getParam("externalDatabaseRefID", String.class);
    JSONObject additionalSessionData = this.getParam("additionalSessionData", JSONObject.class);
    JSONObject result = this.getParam("result", JSONObject.class);
    String idScanSessionId = this.getParam("idScanSessionId", String.class);
    JSONObject idScanResultsSoFar = this.getParam("idScanResultsSoFar", JSONObject.class);
    ReadableMap serverInfo = this.getParam("serverInfo", ReadableMap.class);

    this.object.putBoolean("didError", Boolean.TRUE.equals(didError));
    this.object.putString("responseBlob", responseBlob);
    this.object.putMap("httpCallInfo", this.parseJSONToHttpCallInfo(httpCallInfo));
    this.object.putString("externalDatabaseRefID", externalDatabaseRefID);
    this.object.putMap("additionalSessionData", this.parseJSONToAdditionalSessionData(additionalSessionData));
    this.object.putMap("result", this.parseJSONToResult(result));
    this.object.putString("idScanSessionId", idScanSessionId);
    this.object.putMap("idScanResultsSoFar", this.parseJSONToIDScanResultsSoFar(idScanResultsSoFar));
    this.object.putMap("serverInfo", serverInfo);
  }

  public WritableMap getMap() {
    return this.object.copy();
  }
}

package com.azify.utils;

import android.util.Log;

import com.azify.processors.Config;
import com.facebook.react.bridge.ReadableMap;

import java.util.Map;

public class CommonParams {
  private final Map<String, Object> params;

  public CommonParams(Map<String, Object> params) {
    this.params = params;
  }

  private String getParam(String key) {
    Object value = this.params.get(key);
    return value != null ? value.toString() : null;
  }

  private String getParam(String parent, String key) {
    Map<String, String> parentValue = (Map<String, String>) this.params.get(parent);
    if (parentValue == null) {
      return null;
    }

    return parentValue.get(key);
  }

  public Boolean isDeveloper() {
    String developerMode = this.getParam("isDeveloperMode");

    if (developerMode != null) {
      return developerMode.equalsIgnoreCase("true");
    }

    return false;
  }

  public void setHeaders(ReadableMap headers) {
    Config.setHeaders(headers);
  }

  public void buildProcessorPathURL() {
    Config.setProcessorPathURL("base", this.getParam("pathUrl", "base"));
    Config.setProcessorPathURL("match", this.getParam("pathUrl", "match"));
  }

  public void build() {
    try {
      Config.setDevice(this.getParam("device"));
      Config.setUrl(this.getParam("url"));
      Config.setProcessId(this.getParam("processId"));
      Config.setKey(this.getParam("key"));
      Config.setProductionKeyText(this.getParam("productionKey"));
    } catch (Exception error) {
      Log.d("Aziface - SDK", "Error while setting FaceTecSDK configuration!");
    }
  }
}

package com.azify.utils;

import com.azify.processors.Config;
import com.facebook.react.bridge.ReadableMap;

import java.util.Map;

public class CommonParams {
  private final Map<String, Object> params;

  public CommonParams(ReadableMap params) {
    this.params = params == null ? null : params.toHashMap();
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

  public Boolean isNull() {
    return this.params == null;
  }

  public void setHeaders(ReadableMap headers) {
    Config.setHeaders(headers);
  }

  public void buildProcessorPathURL() {
    if (!this.isNull()) {
      Config.setProcessorPathURL("base", this.getParam("pathUrl", "base"));
      Config.setProcessorPathURL("match", this.getParam("pathUrl", "match"));
    }
  }

  public void build() {
    if (!this.isNull()) {
      Config.setDevice(this.getParam("device"));
      Config.setUrl(this.getParam("url"));
      Config.setProcessId(this.getParam("processId"));
      Config.setKey(this.getParam("key"));
      Config.setProductionKeyText(this.getParam("productionKey"));
    }
  }
}

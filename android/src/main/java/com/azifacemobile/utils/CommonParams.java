package com.azifacemobile.utils;

import com.azifacemobile.Config;
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

  private Boolean isDevelopment() {
    String isDev = this.getParam("isDevelopment");
    return isDev != null && isDev.equalsIgnoreCase("true");
  }

  public Boolean isNull() {
    return this.params == null;
  }

  public void setHeaders(ReadableMap headers) {
    Config.setHeaders(headers);
  }

  public void build() {
    if (!this.isNull()) {
      Config.setDeviceKeyIdentifier(this.getParam("deviceKeyIdentifier"));
      Config.setBaseUrl(this.getParam("baseUrl"));
      Config.setIsDevelopment(this.isDevelopment());
    }
  }
}

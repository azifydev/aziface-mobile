package com.azifacemobile.utils;

import androidx.annotation.Nullable;

import com.azifacemobile.Config;

import java.util.Map;

public class CommonParams {
    private final Map<String, Object> params;

    public CommonParams(Map<String, Object> params) {
        this.params = params;
    }

    private String getParam(String key) {
        if (this.params == null) return null;
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

    public void setHeaders(@Nullable Map<String, Object> headers) {
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

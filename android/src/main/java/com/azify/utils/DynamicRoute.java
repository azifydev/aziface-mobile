package com.azify.utils;

import com.azify.processors.Config;

public class DynamicRoute {

  public String getPathUrl(String target, String defaultUrl) {
    String processorPathUrl = Config.ProcessorPathURL.get(target);

    if (processorPathUrl == null) {
      return defaultUrl;
    } else if (processorPathUrl.trim().isEmpty()) {
      return defaultUrl;
    }

    if (processorPathUrl.contains("/:")) {
      processorPathUrl = processorPathUrl.replaceAll("/:([a-zA-Z0-9_]+)", "/" + Config.ProcessId);
    }

    return processorPathUrl;
  }
}

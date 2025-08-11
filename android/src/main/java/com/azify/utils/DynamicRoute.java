package com.azify.utils;

import com.azify.processors.Config;

public class DynamicRoute {

  private String getPathUrl(String target, String defaultPathUrl) {
    String processorPathUrl = Config.ProcessorPathURL.get(target);

    if (processorPathUrl == null) {
      return defaultPathUrl;
    } else if (processorPathUrl.trim().isEmpty()) {
      return defaultPathUrl;
    }

    if (processorPathUrl.contains("/:")) {
      processorPathUrl = processorPathUrl.replaceAll("/:([a-zA-Z0-9_]+)", "/" + Config.ProcessId);
    }

    return processorPathUrl;
  }

  public String getPathUrlEnrollment3d(String target) {
    return this.getPathUrl(target, "/Process/" + Config.ProcessId + "/Enrollment3d");
  }

  public String getPathUrlIdScanOnly(String target) {
    return this.getPathUrl(target, "/Process/" + Config.ProcessId + "/idscan-only");
  }

  public String getPathUrlMatch3d2dIdScan(String target) {
    return this.getPathUrl(target, "/Process/" + Config.ProcessId + "/Match3d2dIdScan");
  }

  public String getPathUrlMatch3d3d(String target) {
    return this.getPathUrl(target, "/Process/" + Config.ProcessId + "/Match3d3d");
  }
}

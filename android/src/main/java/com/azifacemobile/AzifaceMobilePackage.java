package com.azifacemobile;

import androidx.annotation.NonNull;

import com.facebook.react.BaseReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.HashMap;
import java.util.Map;

public class AzifaceMobilePackage extends BaseReactPackage {
  @Override
  public NativeModule getModule(String name, @NonNull ReactApplicationContext reactContext) {
    if (name.equals(AzifaceMobileModule.NAME)) {
      return new AzifaceMobileModule(reactContext);
    }
    return null;
  }

  @NonNull
  @Override
  public ReactModuleInfoProvider getReactModuleInfoProvider() {
    return new ReactModuleInfoProvider() {
      @NonNull
      @Override
      public Map<String, ReactModuleInfo> getReactModuleInfos() {
        Map<String, ReactModuleInfo> moduleInfos = new HashMap<>();
        moduleInfos.put(AzifaceMobileModule.NAME, new ReactModuleInfo(
          AzifaceMobileModule.NAME,
          AzifaceMobileModule.NAME,
          false, // canOverrideExistingModule
          false, // needsEagerInit
          false, // isCxxModule
          true   // isTurboModule
        ));

        return moduleInfos;
      }
    };
  }
}

package com.azify;

import static java.util.UUID.randomUUID;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.azify.theme.Theme;
import com.azify.theme.Vocal;
import com.azify.utils.CommonParams;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;

import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facetec.sdk.*;

@ReactModule(name = AzifaceModule.NAME)
public class AzifaceModule extends ReactContextBaseJavaModule {
  private static final String EXTERNAL_ID = "android_azify_app_";
  public static final String NAME = "AzifaceModule";
  public static String demonstrationExternalDatabaseRefID = "";
  public static Theme AziTheme;
  public Boolean isInitialized = false;
  public FaceTecSDKInstance sdkInstance;
  ReactApplicationContext reactContext;

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  public AzifaceModule(ReactApplicationContext context) {
    this.reactContext = context;

    AziTheme = new Theme(context);
    FaceTecSDK.preload(context);
  }

  @ReactMethod
  public void initialize(ReadableMap params, ReadableMap headers, Promise promise) {
    CommonParams parameters = new CommonParams(params);

    if (parameters.isNull()) {
      this.isInitialized = false;
      this.sendEvent("onInitialize", false);
      promise.reject("Parameters aren't provided", "ParamsNotProvided");
      return;
    }

    parameters.setHeaders(headers);
    parameters.build();

    if (!Config.isEmpty()) {
      FaceTecSDK.initializeWithSessionRequest(this.getActivity(), Config.DeviceKeyIdentifier, new SessionRequestProcessor(), new FaceTecSDK.InitializeCallback() {
        @Override
        public void onSuccess(@NonNull FaceTecSDKInstance sdkInstance) {
          isInitialized = true;
          onFaceTecSDKInitializationSuccess(sdkInstance);
          sendEvent("onInitialize", true);
          promise.resolve(true);
        }

        @Override
        public void onError(@NonNull FaceTecInitializationError error) {
          isInitialized = false;
          sendEvent("onInitialize", false);
          promise.resolve(false);
        }
      });
    } else {
      this.isInitialized = false;
      this.sendEvent("onInitialize", false);
      promise.reject("Configuration aren't provided", "ConfigNotProvided");
      return;
    }

    this.setTheme(Theme.Style);
  }

  @ReactMethod
  public void liveness(ReadableMap data, Promise promise) {
    if (!this.isInitialized) {
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    if (this.getActivity() == null) {
      promise.reject("AziFace SDK not found Activity!", "NotFoundActivity");
      return;
    }

    demonstrationExternalDatabaseRefID = "";
    sdkInstance.start3DLiveness(this.getActivity(), new SessionRequestProcessor());
  }

  @ReactMethod
  public void enroll(ReadableMap data, Promise promise) {
    if (!this.isInitialized) {
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    if (this.getActivity() == null) {
      promise.reject("AziFace SDK not found Activity!", "NotFoundActivity");
      return;
    }

    demonstrationExternalDatabaseRefID = EXTERNAL_ID + randomUUID();
    sdkInstance.start3DLiveness(this.getActivity(), new SessionRequestProcessor());
  }

  @ReactMethod
  public void verify(ReadableMap data, Promise promise) {
    if (!this.isInitialized) {
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    if (this.getActivity() == null) {
      promise.reject("AziFace SDK not found Activity!", "NotFoundActivity");
      return;
    }

    if (demonstrationExternalDatabaseRefID.isEmpty()) {
      promise.reject("AziFace SDK not found external ID!", "NotFoundExternalID");
      return;
    }

    sdkInstance.start3DLivenessThen3DFaceMatch(this.getActivity(), new SessionRequestProcessor());
  }

  @ReactMethod
  public void photoIDMatch(ReadableMap data, Promise promise) {
    if (!this.isInitialized) {
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    if (this.getActivity() == null) {
      promise.reject("AziFace SDK not found Activity!", "NotFoundActivity");
      return;
    }

    demonstrationExternalDatabaseRefID = EXTERNAL_ID + randomUUID();
    sdkInstance.start3DLivenessThen3D2DPhotoIDMatch(this.getActivity(), new SessionRequestProcessor());
  }

  @ReactMethod
  public void photoIDScanOnly(ReadableMap data, Promise promise) {
    if (!this.isInitialized) {
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    if (this.getActivity() == null) {
      promise.reject("AziFace SDK not found Activity!", "NotFoundActivity");
      return;
    }

    sdkInstance.startIDScanOnly(this.getActivity(), new SessionRequestProcessor());
  }

  @ReactMethod
  public void setTheme(ReadableMap theme) {
    Theme.setTheme(theme);
  }

  @ReactMethod
  public void addListener(String eventName) {
  }

  @ReactMethod
  public void removeListeners(Integer count) {
  }

  private void onFaceTecSDKInitializationSuccess(FaceTecSDKInstance sdkInstance) {
    this.sdkInstance = sdkInstance;

    Vocal.setOCRLocalization(this.reactContext);

    Vocal.setVocalGuidanceSoundFiles();
    Vocal.setUpVocalGuidancePlayers(this);
  }

  public void onVocalGuidanceSettingsButtonPressed() {
    Vocal.setVocalGuidanceMode(this);
  }

  public Context getContext() {
    return this.reactContext.getApplicationContext();
  }

  public Activity getActivity() {
    return this.reactContext.getCurrentActivity();
  }

  public void sendEvent(@NonNull String eventName, @Nullable Object eventValue) {
    this.reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(eventName, eventValue);
  }
}


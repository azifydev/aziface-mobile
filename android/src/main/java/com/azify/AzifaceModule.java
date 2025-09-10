package com.azify;

import static java.util.UUID.randomUUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.azify.errors.AzifaceError;
import com.azify.theme.Theme;
import com.azify.theme.Vocal;
import com.azify.utils.CommonParams;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;

import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facetec.sdk.*;

@ReactModule(name = AzifaceModule.NAME)
public class AzifaceModule extends ReactContextBaseJavaModule implements ActivityEventListener {
  private static final String EXTERNAL_ID = "android_azify_app_";
  public static final String NAME = "AzifaceModule";
  public static String DemonstrationExternalDatabaseRefID = "";
  private final AzifaceError error;
  public Boolean isInitialized = false;
  public Boolean isEnabled = false;
  public FaceTecSDKInstance sdkInstance;
  public Promise promiseResult;
  ReactApplicationContext reactContext;

  public AzifaceModule(ReactApplicationContext context) {
    context.addActivityEventListener(this);

    this.reactContext = context;
    this.error = new AzifaceError(this);

    FaceTecSDK.preload(context);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @Override
  public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
    FaceTecSessionResult sessionResult = sdkInstance.getActivitySessionResult(requestCode, resultCode, data);
    assert sessionResult != null;

    final FaceTecSessionStatus status = sessionResult.getStatus();
    final boolean isCompleted = status == FaceTecSessionStatus.SESSION_COMPLETED;
    if (!isCompleted) {
      DemonstrationExternalDatabaseRefID = "";
    }

    final boolean isError = this.error.isError(status);
    if (isError) {
      final String message = this.error.getErrorMessage(status);
      final String code = this.error.getErrorCode(status);
      this.promiseResult.reject(message, code);
    } else {
      if (this.isEnabled) {
        Vocal.setUpVocalGuidancePlayers(this);
        this.isEnabled = false;
      }

      this.promiseResult.resolve(true);
    }
  }

  @Override
  public void onNewIntent(Intent intent) {
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
    }
  }

  @ReactMethod
  public void liveness(ReadableMap data, Promise promise) {
    if (this.getActivity() == null) {
      this.sendEvent("onError", true);
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      this.sendEvent("onError", true);
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    this.setPromiseResult(promise);
    this.sendOpenEvent();

    DemonstrationExternalDatabaseRefID = "";
    sdkInstance.start3DLiveness(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void enroll(ReadableMap data, Promise promise) {
    if (this.getActivity() == null) {
      this.sendEvent("onError", true);
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      this.sendEvent("onError", true);
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    this.setPromiseResult(promise);
    this.sendOpenEvent();

    DemonstrationExternalDatabaseRefID = EXTERNAL_ID + randomUUID();
    sdkInstance.start3DLiveness(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void authenticate(ReadableMap data, Promise promise) {
    if (this.getActivity() == null) {
      this.sendEvent("onError", true);
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      this.sendEvent("onError", true);
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    if (DemonstrationExternalDatabaseRefID.isEmpty()) {
      this.sendEvent("onError", true);
      promise.reject("User isn't authenticated! You must enroll first!", "NotAuthenticated");
      return;
    }

    this.setPromiseResult(promise);
    this.sendOpenEvent();

    sdkInstance.start3DLivenessThen3DFaceMatch(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void photoIDMatch(ReadableMap data, Promise promise) {
    if (this.getActivity() == null) {
      this.sendEvent("onError", true);
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      this.sendEvent("onError", true);
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    this.setPromiseResult(promise);
    this.sendOpenEvent();

    DemonstrationExternalDatabaseRefID = EXTERNAL_ID + randomUUID();
    sdkInstance.start3DLivenessThen3D2DPhotoIDMatch(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void photoIDScanOnly(ReadableMap data, Promise promise) {
    if (this.getActivity() == null) {
      this.sendEvent("onError", true);
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      this.sendEvent("onError", true);
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    this.setPromiseResult(promise);
    this.sendOpenEvent();

    sdkInstance.startIDScanOnly(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void setTheme(ReadableMap style) {
    Theme.setStyle(style);

    this.updateTheme();
  }

  @ReactMethod
  public void addListener(String eventName) {
  }

  @ReactMethod
  public void removeListeners(Integer count) {
  }

  @ReactMethod
  public void vocal() {
    this.updateTheme();

    this.isEnabled = !this.isEnabled;
    if (this.isEnabled) {
      Vocal.setUpVocalGuidancePlayers(this);
    }
    
    Vocal.setVocalGuidanceMode(this);
  }

  private void updateTheme() {
    final Theme theme = new Theme(this.reactContext);
    Config.currentCustomization = Config.retrieveConfigurationCustomization(theme);
    Theme.updateTheme();
  }

  private void onFaceTecSDKInitializationSuccess(FaceTecSDKInstance sdkInstance) {
    this.sdkInstance = sdkInstance;

    final Theme theme = new Theme(this.reactContext);
    Config.currentCustomization = Config.retrieveConfigurationCustomization(theme);
    Theme.setTheme();

    Vocal.setOCRLocalization(this.reactContext);
    Vocal.setVocalGuidanceSoundFiles();
    Vocal.setUpVocalGuidancePlayers(this);
  }

  private void setPromiseResult(Promise promise) {
    this.promiseResult = promise;
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

  public void sendOpenEvent() {
    this.sendEvent("onOpen", true);
    this.sendEvent("onClose", false);
    this.sendEvent("onCancel", false);
    this.sendEvent("onError", false);
  }
}


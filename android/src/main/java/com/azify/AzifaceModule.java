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
  public static Theme AziTheme;
  private final AzifaceError error;
  public Boolean isInitialized = false;
  public FaceTecSDKInstance sdkInstance;
  public Promise promiseResult;
  ReactApplicationContext reactContext;

  public AzifaceModule(ReactApplicationContext context) {
    context.addActivityEventListener(this);

    this.reactContext = context;
    this.error = new AzifaceError(this);

    AziTheme = new Theme(context);
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

    FaceTecSessionStatus status = sessionResult.getStatus();
    boolean isCompleted = status == FaceTecSessionStatus.SESSION_COMPLETED;
    if (!isCompleted) {
      DemonstrationExternalDatabaseRefID = "";
    }

    boolean isError = this.error.isError(status);
    if (isError) {
      this.promiseResult.reject(this.error.getErrorMessage(status), this.error.getErrorCode(status));
    } else {
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
      return;
    }

    this.setTheme(Theme.Style);
  }

  @ReactMethod
  public void liveness(ReadableMap data, Promise promise) {
    if (this.getActivity() == null) {
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
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
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
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
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    if (DemonstrationExternalDatabaseRefID.isEmpty()) {
      promise.reject("User isn't authenticated! You must enroll first!", "NotAuthenticated");
      return;
    }

    this.setPromiseResult(promise);

    sdkInstance.start3DLivenessThen3DFaceMatch(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void photoIDMatch(ReadableMap data, Promise promise) {
    if (this.getActivity() == null) {
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
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
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    this.setPromiseResult(promise);
    this.sendOpenEvent();

    sdkInstance.startIDScanOnly(this.getActivity(), new SessionRequestProcessor(data));
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

  // @ReactMethod
  // public void activeVocal() {
  //   Vocal.setVocalGuidanceMode(this);
  // }

  private void onFaceTecSDKInitializationSuccess(FaceTecSDKInstance sdkInstance) {
    this.sdkInstance = sdkInstance;

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


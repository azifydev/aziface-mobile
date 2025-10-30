package com.azifacemobile;

import static java.util.UUID.randomUUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.azifacemobile.errors.AzifaceError;
import com.azifacemobile.theme.Theme;
import com.azifacemobile.theme.Vocal;
import com.azifacemobile.utils.CommonParams;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;

import com.facetec.sdk.*;

@ReactModule(name = AzifaceMobileModule.NAME)
public class AzifaceMobileModule extends NativeAzifaceMobileSpec implements ActivityEventListener {
  private static final String EXTERNAL_ID = "android_azify_app_";
  public static final String NAME = "AzifaceMobile";
  private static Boolean isRunning = false;
  public static String DemonstrationExternalDatabaseRefID = "";
  private final AzifaceError error;
  public Boolean isInitialized = false;
  public Boolean isEnabled = false;
  public FaceTecSDKInstance sdkInstance;
  public Promise promiseResult;
  ReactApplicationContext reactContext;

  public AzifaceMobileModule(ReactApplicationContext context) {
    super(context);

    context.addActivityEventListener(this);

    this.reactContext = context;
    this.error = new AzifaceError(this);

    FaceTecSDK.preload(context);
  }

  @NonNull
  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public void onActivityResult(@NonNull Activity activity, int requestCode, int resultCode, Intent data) {
    FaceTecSessionResult sessionResult = sdkInstance.getActivitySessionResult(requestCode, resultCode, data);
    assert sessionResult != null;

    final FaceTecSessionStatus status = sessionResult.getStatus();
    final boolean isCompleted = status == FaceTecSessionStatus.SESSION_COMPLETED;
    if (!isCompleted) {
      DemonstrationExternalDatabaseRefID = "";
    }

    isRunning = false;
    final boolean isError = this.error.isError(status);
    if (isError) {
      final String message = this.error.getErrorMessage(status);
      final String code = this.error.getErrorCode(status);
      this.promiseResult.reject(message, code);
    } else {
      if (this.isEnabled) {
        Vocal.setUpVocalGuidancePlayers(this);
        this.isEnabled = false;

        this.onVocal(false);
      }

      this.promiseResult.resolve(true);
    }
  }

  @Override
  public void onNewIntent(@NonNull Intent intent) {
  }

  @ReactMethod
  public void initialize(ReadableMap params, ReadableMap headers, Promise promise) {
    if (isRunning) return;

    isRunning = true;
    CommonParams parameters = new CommonParams(params);

    if (parameters.isNull()) {
      this.isInitialized = false;
      this.onInitialize(false);
      isRunning = false;
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
          onInitialize(true);
          onVocal(false);
          isRunning = false;
          promise.resolve(true);
        }

        @Override
        public void onError(@NonNull FaceTecInitializationError error) {
          isInitialized = false;
          onInitialize(false);
          onVocal(false);
          isRunning = false;
          promise.resolve(false);
        }
      });
    } else {
      this.isInitialized = false;
      this.onInitialize(false);
      isRunning = false;
      promise.reject("Configuration aren't provided", "ConfigNotProvided");
    }
  }

  @ReactMethod
  public void liveness(ReadableMap data, Promise promise) {
    if (isRunning) return;

    isRunning = true;

    if (this.getActivity() == null) {
      this.onError(true);
      isRunning = false;
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      this.onError(true);
      isRunning = false;
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
    if (isRunning) return;

    isRunning = true;

    if (this.getActivity() == null) {
      this.onError(true);
      isRunning = false;
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      this.onError(true);
      isRunning = false;
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
    if (isRunning) return;

    isRunning = true;

    if (this.getActivity() == null) {
      this.onError(true);
      isRunning = false;
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      this.onError(true);
      isRunning = false;
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    if (DemonstrationExternalDatabaseRefID.isEmpty()) {
      this.onError(true);
      isRunning = false;
      promise.reject("User isn't authenticated! You must enroll first!", "NotAuthenticated");
      return;
    }

    this.setPromiseResult(promise);
    this.sendOpenEvent();

    sdkInstance.start3DLivenessThen3DFaceMatch(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void photoIDMatch(ReadableMap data, Promise promise) {
    if (isRunning) return;

    isRunning = true;

    if (this.getActivity() == null) {
      this.onError(true);
      isRunning = false;
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      this.onError(true);
      isRunning = false;
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
    if (isRunning) return;

    isRunning = true;

    if (this.getActivity() == null) {
      this.onError(true);
      isRunning = false;
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      this.onError(true);
      isRunning = false;
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    this.setPromiseResult(promise);
    this.sendOpenEvent();

    sdkInstance.startIDScanOnly(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void setTheme(ReadableMap style) {
    if (isRunning) return;

    isRunning = true;
    Theme.setStyle(style);

    this.updateTheme();
    isRunning = false;
  }

  @ReactMethod
  public void vocal() {
    /**
     * TODO: Fix crash when device is muted.
     *
     * Current workaround is to check if device is muted and skip vocal guidance
     * toggle in that case.
     */
    final boolean isMuted = Vocal.isDeviceMuted(this);

    if (isRunning || isMuted) {
      this.onVocal(this.isEnabled);
      return;
    };

    isRunning = true;
    this.updateTheme();

    this.isEnabled = !this.isEnabled;
    if (this.isEnabled) {
      Vocal.setUpVocalGuidancePlayers(this);
    }

    Vocal.setVocalGuidanceMode(this);

    this.onVocal(this.isEnabled);
    isRunning = false;
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

  private void sendOpenEvent() {
    this.onOpen(true);
    this.onClose(false);
    this.onCancel(false);
    this.onError(false);
  }

  public Context getContext() {
    return this.reactContext.getApplicationContext();
  }

  public Activity getActivity() {
    return this.reactContext.getCurrentActivity();
  }

  public void onInitialize(Boolean value) {
    this.emitOnInitialize(value);
  }

  public void onOpen(Boolean value) {
    this.emitOnOpen(value);
  }

  public void onClose(Boolean value) {
    this.emitOnClose(value);
  }

  public void onCancel(Boolean value) {
    this.emitOnCancel(value);
  }

  public void onError(Boolean value) {
    this.emitOnError(value);
  }

  public void onVocal(Boolean value) {
    this.emitOnVocal(value);
  }
}

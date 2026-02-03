package com.azifacemobile;

import static java.util.UUID.randomUUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

import com.azifacemobile.errors.AzifaceError;
import com.azifacemobile.i18n.Localization;
import com.azifacemobile.strings.DynamicStrings;
import com.azifacemobile.theme.Theme;
import com.azifacemobile.theme.Vocal;
import com.azifacemobile.utils.CommonParams;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;

import com.facetec.sdk.*;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Locale;

@ReactModule(name = AzifaceMobileModule.NAME)
public class AzifaceMobileModule extends NativeAzifaceMobileSpec implements ActivityEventListener {
  private static final String EXTERNAL_ID = "android_azify_app_";
  private static final Localization I18n = Localization.DEFAULT;
  private static final DynamicStrings Strings = new DynamicStrings();
  public static final String NAME = "AzifaceMobile";
  private static Boolean IsRunning = false;
  public static String DemonstrationExternalDatabaseRefID = "";
  private final AzifaceError error;
  private WritableMap response;
  public Boolean isInitialized = false;
  public Boolean isEnabled = false;
  public FaceTecSDKInstance sdkInstance;
  public Promise promise;
  ReactApplicationContext reactContext;

  public AzifaceMobileModule(ReactApplicationContext context) {
    super(context);

    context.addActivityEventListener(this);

    this.reactContext = context;
    this.error = new AzifaceError(this);
    this.response = Arguments.createMap();

    this.setupI18n();

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

    if (this.error.isError(status)) {
      final String message = this.error.getErrorMessage(status);
      final String code = this.error.getErrorCode(status);

      this.onProcessorError(message, code);
    } else {
      if (this.isEnabled) {
        Vocal.setUpVocalGuidancePlayers(this);
        this.isEnabled = false;

        this.onVocal(false);
      }

      assert SessionRequestProcessor.Response != null;
      this.onProcessorSuccess(SessionRequestProcessor.Response);
    }

    this.promise.resolve(this.getStringifyResponse());
  }

  @Override
  public void onNewIntent(@NonNull Intent intent) {
  }

  @ReactMethod
  public void initialize(ReadableMap params, ReadableMap headers, Promise promise) {
    if (IsRunning) return;

    IsRunning = true;
    CommonParams parameters = new CommonParams(params);

    if (parameters.isNull()) {
      this.isInitialized = false;
      this.onInitialize(false);

      IsRunning = false;

      promise.reject("Parameters aren't provided", "ParamsNotProvided");
      return;
    }

    parameters.setHeaders(headers);
    parameters.build();

    if (!Config.isEmpty()) {
      this.setupI18n();

      FaceTecSDK.initializeWithSessionRequest(this.getActivity(), Config.DeviceKeyIdentifier, new SessionRequestProcessor(), new FaceTecSDK.InitializeCallback() {
        @Override
        public void onSuccess(@NonNull FaceTecSDKInstance sdkInstance) {
          onInitializationSuccess(sdkInstance);
          promise.resolve(true);
        }

        @Override
        public void onError(@NonNull FaceTecInitializationError error) {
          onInitializationError();
          promise.resolve(false);
        }
      });
    } else {
      this.isInitialized = false;
      this.onInitialize(false);

      IsRunning = false;

      promise.reject("Configuration aren't provided", "ConfigNotProvided");
    }
  }

  @ReactMethod
  public void liveness(ReadableMap data, Promise promise) {
    if (IsRunning) return;

    IsRunning = true;

    if (this.getActivity() == null) {
      this.onProcessorError("AziFace SDK not found target View!", "NotFoundTargetView");
      promise.resolve(this.getStringifyResponse());
      return;
    }

    if (!this.isInitialized) {
      this.onProcessorError("AziFace SDK doesn't initialized!", "NotInitialized");
      promise.resolve(this.getStringifyResponse());
      return;
    }

    this.setPromise(promise);
    this.sendOpenEvent();

    DemonstrationExternalDatabaseRefID = "";
    sdkInstance.start3DLiveness(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void enroll(ReadableMap data, Promise promise) {
    if (IsRunning) return;

    IsRunning = true;

    if (this.getActivity() == null) {
      this.onProcessorError("AziFace SDK not found target View!", "NotFoundTargetView");
      promise.resolve(this.getStringifyResponse());
      return;
    }

    if (!this.isInitialized) {
      this.onProcessorError("AziFace SDK doesn't initialized!", "NotInitialized");
      promise.resolve(this.getStringifyResponse());
      return;
    }

    this.setPromise(promise);
    this.sendOpenEvent();

    DemonstrationExternalDatabaseRefID = EXTERNAL_ID + randomUUID();
    sdkInstance.start3DLiveness(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void authenticate(ReadableMap data, Promise promise) {
    if (IsRunning) return;

    IsRunning = true;

    if (this.getActivity() == null) {
      this.onProcessorError("AziFace SDK not found target View!", "NotFoundTargetView");
      promise.resolve(this.getStringifyResponse());
      return;
    }

    if (!this.isInitialized) {
      this.onProcessorError("AziFace SDK doesn't initialized!", "NotInitialized");
      promise.resolve(this.getStringifyResponse());
      return;
    }

    if (DemonstrationExternalDatabaseRefID.isEmpty()) {
      this.onProcessorError("User isn't authenticated! You must enroll first!", "NotAuthenticated");
      promise.resolve(this.getStringifyResponse());
      return;
    }

    this.setPromise(promise);
    this.sendOpenEvent();

    sdkInstance.start3DLivenessThen3DFaceMatch(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void photoIDMatch(ReadableMap data, Promise promise) {
    if (IsRunning) return;

    IsRunning = true;

    if (this.getActivity() == null) {
      this.onProcessorError("AziFace SDK not found target View!", "NotFoundTargetView");
      promise.resolve(this.getStringifyResponse());
      return;
    }

    if (!this.isInitialized) {
      this.onProcessorError("AziFace SDK doesn't initialized!", "NotInitialized");
      promise.resolve(this.getStringifyResponse());
      return;
    }

    this.setPromise(promise);
    this.sendOpenEvent();

    DemonstrationExternalDatabaseRefID = EXTERNAL_ID + randomUUID();
    sdkInstance.start3DLivenessThen3D2DPhotoIDMatch(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void photoIDScanOnly(ReadableMap data, Promise promise) {
    if (IsRunning) return;

    IsRunning = true;

    if (this.getActivity() == null) {
      this.onProcessorError("AziFace SDK not found target View!", "NotFoundTargetView");
      promise.resolve(this.getStringifyResponse());
      return;
    }

    if (!this.isInitialized) {
      this.onProcessorError("AziFace SDK doesn't initialized!", "NotInitialized");
      promise.resolve(this.getStringifyResponse());
      return;
    }


    this.setPromise(promise);
    this.sendOpenEvent();

    sdkInstance.startIDScanOnly(this.getActivity(), new SessionRequestProcessor(data));
  }

  @ReactMethod
  public void setLocale(String locale) {
    I18n.setLocale(locale);

    this.setupI18n();
  }

  @ReactMethod
  public void setDynamicStrings(ReadableMap strings) {
    Strings.setStrings(strings)
      .load()
      .build();
  }

  @ReactMethod
  public void setTheme(ReadableMap style) {
    Theme.setStyle(style);

    this.updateTheme();
  }

  @ReactMethod
  public void vocal() {
    final boolean isMuted = Vocal.isDeviceMuted(this);

    if (IsRunning || isMuted) {
      if (isMuted) {
        this.isEnabled = false;
      }

      this.onVocal(this.isEnabled);
      return;
    }

    IsRunning = true;
    this.updateTheme();

    this.isEnabled = !this.isEnabled;
    if (this.isEnabled) {
      Vocal.setUpVocalGuidancePlayers(this);
    }

    Vocal.setVocalGuidanceMode(this);

    this.onVocal(this.isEnabled);
    IsRunning = false;
  }

  private void updateTheme() {
    final Theme theme = new Theme(this.reactContext);
    Config.currentCustomization = Config.retrieveConfigurationCustomization(theme);
    Theme.updateTheme();
  }

  private void setupI18n() {
    Locale locale = new Locale(I18n.getLocale());

    final Resources resources = this.reactContext.getBaseContext().getResources();
    final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
    Configuration configuration = resources.getConfiguration();
    configuration.setLocale(locale);

    resources.updateConfiguration(configuration, displayMetrics);

    this.reactContext.getApplicationContext().getResources().updateConfiguration(configuration, displayMetrics);
  }

  private void onInitializationSuccess(FaceTecSDKInstance sdkInstance) {
    this.isInitialized = true;
    this.sdkInstance = sdkInstance;

    final Theme theme = new Theme(this.reactContext);
    Config.currentCustomization = Config.retrieveConfigurationCustomization(theme);
    Theme.setTheme();

    Vocal.setOCRLocalization(this.reactContext);
    Vocal.setVocalGuidanceSoundFiles();
    Vocal.setUpVocalGuidancePlayers(this);

    this.onInitialize(true);
    this.onVocal(false);

    IsRunning = false;
  }

  private void onInitializationError() {
    this.isInitialized = false;
    this.onInitialize(false);
    this.onVocal(false);

    IsRunning = false;
  }

  private void onProcessorSuccess(JSONObject object) {
    this.response = Arguments.createMap();
    this.response.putBoolean("isSuccess", true);
    this.response.putMap("data", this.convertJsonToWritableMap(object));
    this.response.putMap("error", null);

    IsRunning = false;
  }

  private void onProcessorError(String message, String code) {
    WritableMap error = Arguments.createMap();
    error.putString("message", message);
    error.putString("code", code);

    this.response = Arguments.createMap();
    this.response.putBoolean("isSuccess", false);
    this.response.putMap("data", null);
    this.response.putMap("error", error);

    this.onError(true);

    IsRunning = false;
  }

  private String getStringifyResponse() {
    Gson gson = new Gson();

    return gson.toJson(this.response.toHashMap());
  }

  private WritableMap convertJsonToWritableMap(JSONObject jsonObject) {
    WritableMap map = new WritableNativeMap();
    Iterator<String> iterator = jsonObject.keys();

    while (iterator.hasNext()) {
      String key = iterator.next();
      Object value = null;

      try {
        value = jsonObject.get(key);
      } catch (JSONException ignored) {}

      if (value instanceof JSONObject) {
        map.putMap(key, this.convertJsonToWritableMap((JSONObject) value));
      } else if (value instanceof Boolean) {
        map.putBoolean(key, (Boolean) value);
      } else if (value instanceof Integer) {
        map.putInt(key, (Integer) value);
      } else if (value instanceof Double) {
        map.putDouble(key, (Double) value);
      } else if (value instanceof String)  {
        map.putString(key, (String) value);
      } else {
        map.putNull(key);
      }
    }

    return map;
  }

  private void setPromise(Promise promise) {
    this.promise = promise;
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

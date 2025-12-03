package com.azifacemobile;

import static java.util.UUID.randomUUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import com.azifacemobile.errors.AzifaceError;
import com.azifacemobile.theme.Theme;
import com.azifacemobile.theme.Vocal;
import com.azifacemobile.utils.CommonParams;
import com.facetec.sdk.*;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class AzifaceMobileModule{
  private static final String EXTERNAL_ID = "android_azify_app_";
  public static final String NAME = "AzifaceMobile";
  private static Boolean IsRunning = false;
  public static String DemonstrationExternalDatabaseRefID = "";
  private final AzifaceError error;
  private Map<String, Object> response;
  public Boolean isInitialized = false;
  public Boolean isEnabled = false;
  public FaceTecSDKInstance sdkInstance;
  public SimplePromise promise;
  public Context context;
  private AzifaceEventListener listener;


  public AzifaceMobileModule(Context context) {
    this.context = context;
    this.error = new AzifaceError(this);
    this.response = new HashMap<>();
    FaceTecSDK.preload(context);
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
        Vocal.setUpVocalGuidancePlayers(this.context);
        this.isEnabled = false;

        this.onVocal(false);
      }

      assert SessionRequestProcessor.Response != null;
      this.onProcessorSuccess(SessionRequestProcessor.Response);
    }

    this.promise.resolve(true);
  }

  public void initialize(Map<String, Object> params, Map<String, Object> headers, SimplePromise promise) {
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
      FaceTecSDK.initializeWithSessionRequest((Activity) context, Config.DeviceKeyIdentifier, new SessionRequestProcessor(), new FaceTecSDK.InitializeCallback() {
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

  public void enroll(Map<String, Object> data, SimplePromise promise) {
    if (IsRunning) return;

    IsRunning = true;

    if ((Activity) context == null) {
      this.onProcessorError("AziFace SDK not found target View!", "NotFoundTargetView");
      promise.reject("AziFace SDK not found target View!", "NotFoundTargetView");
      return;
    }

    if (!this.isInitialized) {
      this.onProcessorError("AziFace SDK doesn't initialized!", "NotInitialized");
      promise.reject("AziFace SDK doesn't initialized!", "NotInitialized");
      return;
    }

    this.setPromise(promise);
    this.sendOpenEvent();

    DemonstrationExternalDatabaseRefID = EXTERNAL_ID + randomUUID();
    sdkInstance.start3DLiveness((Activity) context, new SessionRequestProcessor(data));
  }

  private void onInitializationSuccess(FaceTecSDKInstance sdkInstance) {
    this.isInitialized = true;
    this.sdkInstance = sdkInstance;

    final Theme theme = new Theme(this.context);
    Config.currentCustomization = Config.retrieveConfigurationCustomization(theme);
    Theme.setTheme();

    Vocal.setOCRLocalization(this.context);
    Vocal.setVocalGuidanceSoundFiles();
    Vocal.setUpVocalGuidancePlayers(this.context);

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
    IsRunning = false;
    this.promise.resolve(true);
  }

  private void onProcessorError(String message, String code) {
    IsRunning = false;
    this.promise.reject(code,message);
  }

  private void setPromise(SimplePromise promise) {
    this.promise = promise;
  }

  private void sendOpenEvent() {
    this.onOpen(true);
    this.onClose(false);
    this.onCancel(false);
    this.onError(false);
  }

  public Context getContext() {
    return this.context.getApplicationContext();
  }

  public interface SimplePromise {
    void resolve(Boolean result);
    void reject(String code, String message);
  }

  public interface AzifaceEventListener {
    void onInitialize(Boolean value);
    void onOpen(Boolean value);
    void onClose(Boolean value);
    void onCancel(Boolean value);
    void onError(Boolean value);
    void onVocal(Boolean value);
  }

  public void onInitialize(Boolean value) {
    if (listener != null) listener.onInitialize(value);
    Log.d(NAME, "onInitialize: " + value);
  }

  public void onOpen(Boolean value) {
    if (listener != null) listener.onOpen(value);
    Log.d(NAME, "onOpen: " + value);
  }

  public void onClose(Boolean value) {
    if (listener != null) listener.onClose(value);
    Log.d(NAME, "onClose: " + value);
  }

  public void onCancel(Boolean value) {
    if (listener != null) listener.onCancel(value);
    Log.d(NAME, "onCancel: " + value);
  }

  public void onError(Boolean value) {
    if (listener != null) listener.onError(value);
    Log.e(NAME, "onError: " + value);
  }
  public void onVocal(Boolean value) {
    if (listener != null) listener.onVocal(value);
    Log.d(NAME, "onVocal: " + value);
  }
}

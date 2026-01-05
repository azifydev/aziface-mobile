# 🧩 Aziface Mobile SDK Migration Guide for Flutter

This document provides a step-by-step guide to migrating the Aziface Mobile SDK, originally developed for React Native, into a Flutter project.

The goal of this guide is to demonstrate how to manually integrate the SDK into a Flutter environment, ensuring proper functionality of native modules and the removal of unnecessary dependencies (such as Facebook integrations).

| Platform    | Documentation Status |
| ----------- | -------------------- |
| **Android** | ✅ Completed         |
| **iOS**     | 🚧 In Progress       |

## 🤖 Android Migration Guide

### 📁 1. Cloning the Repository

Access the official SDK repository:
🔗 [aziface-mobile](https://github.com/azifydev/aziface-mobile)

Clone the project with the command:

```bash
git clone https://github.com/azifydev/aziface-mobile.git
```

Alternatively, you can download the .zip file and extract it locally.

### 📦 2. Copying Required Structures

After cloning the repository, copy the following folders to your Flutter project (in the Android section):

#### a) Native Code (Java/Kotlin)

Copy the folder: `aziface-mobile/android/src/main/java/com/azifacemobile`
Paste it into your Flutter project at: `android/app/src/main/java/com`

**Note** ⚠️ If your project uses Kotlin and does not have a java folder, you can still follow the same steps — just adjust the reference in Gradle (see section 5. Technical Notes).

#### b) Assets

Copy the folder: `aziface-mobile/android/src/main/assets`
Paste it into: `android/app/src/main`

#### c) Resources (res)

Copy the folder: `aziface-mobile/android/src/main/res`
Paste it into: `android/app/src/main`

### ⚙️ 3. Gradle Configuration

Open the file: `android/app/build.gradle` or `android/app/build.gradle.kts` depending on your build language (Groovy or Kotlin), and make the following changes.

##### Repositories

```groovy
repositories {
	maven { url = "https://jitpack.io" }
	google()
	mavenCentral()
}
```

##### Dependencies

```groovy
dependencies {
	implementation("androidx.appcompat:appcompat:1.7.1")
	implementation("androidx.core:core-ktx:1.17.0")
	implementation("androidx.annotation:annotation:1.9.1")
	implementation("com.github.azifydev:facetec-aar:v1.1.0")
	implementation("com.squareup.okhttp3:okhttp:5.3.0")
	implementation("com.squareup.okio:okio:3.16.2")
}
```

### 🧰 4. Gradle Properties Configuration

Make sure the following properties are defined in your `gradle.properties` file:

```ini
android.useAndroidX=true
android.enableJetifier=true
```

### 🧩 5. Technical Notes

#### 🧾 a) Projects Using Kotlin

If your project uses Kotlin and doesn’t contain a `java` directory, create it under `src/main`, and add the following snippet to your `app/build.gradle.kts` file:

```kotlin
android {
  // ...
	sourceSets["main"].java.srcDirs("src/main/java")
}
```

#### 🛰️ b) facetec-aar Dependency Issues

If you encounter issues when downloading the [facetec-aar](https://jitpack.io/#azifydev/facetec-aar) dependency, add the following snippet to your
`settings.gradle` or `settings.gradle.kts`:

```groovy
repositories {
	maven { url = uri("https://jitpack.io") }
	mavenCentral()
	google()
	gradlePluginPortal()
}
```

**Note** ⚠️ Ensure that `jitpack.io` is accessible from your environment.

### 🚫 6. Removing Facebook References

Since the original project included Facebook-related components that are no longer required for the Flutter version, remove all imports and references to Facebook SDKs.

After cleaning up these references, clear the build cache and rebuild the project:

```bash
cd android
./gradlew clean
```

### 🧠 7. Migrated Class Structure

In the Flutter migration, Java/Kotlin classes are used as native modules accessed through Flutter Platform Channels.

##### The folder structure should look similar to the following:

```
android/
	app/
		src/
			main/
				java/
					com/
						azifacemobile/
							AzifaceMobileModule.java
							AzifaceMobilePackage.java
							AzifaceFlutterPlugin.java
ios/
lib/
	aziface_sdk.dart
	main.dart
...
```

##### Important Note:

Some classes may require adjustments — for example, replace instances of `ReadableMap` with `Map<String, Object>` or `Map<String, String>`.

##### The folder structure of Android files:

`SimplePromise.java`

```java
package com.azifacemobile;

public interface SimplePromise {
	void resolve(Object value);
	void reject(String code, String message);
}
```

`AzifaceEventListener.java`

```java
package com.azifacemobile;
public interface AzifaceEventListener {
	void onInitialize(Boolean value);
	void onOpen(Boolean value);
	void onClose(Boolean value);
	void onCancel(Boolean value);
	void onError(Boolean value);
	void onVocal(Boolean value);
}
```

`AzifaceMobileModule.java`

```java
package com.azifacemobile;

import static java.util.UUID.randomUUID;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.azifacemobile.errors.AzifaceError;
import com.azifacemobile.theme.Theme;
import com.azifacemobile.theme.Vocal;
import com.azifacemobile.utils.CommonParams;
import com.facetec.sdk.FaceTecSDK;
import com.facetec.sdk.FaceTecSDKInstance;
import com.facetec.sdk.FaceTecSessionResult;
import com.facetec.sdk.FaceTecSessionStatus;
import com.facetec.sdk.FaceTecInitializationError;

import java.util.Map;

public class AzifaceMobileModule {
	private static final String TAG = "AzifaceMobile";
	private static final String EXTERNAL_ID = "android_azify_app_";

	private static Boolean isRunning = false;
	public static String DemonstrationExternalDatabaseRefID = "";

	private final Context context;
	private final AzifaceError error;
	private FaceTecSDKInstance sdkInstance;
	private boolean isInitialized = false;
	private boolean isEnabled = false;

	public interface SimplePromise {
		void resolve(Object result);
		void reject(String code, String message);
	}

	public interface AzifaceEventListener {
		void onInitialize(boolean value);
		void onOpen(boolean value);
		void onClose(boolean value);
		void onCancel(boolean value);
		void onError(boolean value);
		void onVocal(boolean value);
	}

	private AzifaceEventListener listener;

	public AzifaceMobileModule(Context context) {
		this.context = context;
		this.error = new AzifaceError(this);
		FaceTecSDK.preload(context);
	}

	public void setEventListener(AzifaceEventListener listener) {
		this.listener = listener;
	}

	public void initialize(Map<String, Object> params, Map<String, Object> headers, SimplePromise promise) {
		if (isRunning) return;
		isRunning = true;

		CommonParams parameters = new CommonParams(params);
		if (parameters.isNull()) {
			isInitialized = false;
			onInitialize(false);
			isRunning = false;
			promise.reject("ParamsNotProvided", "Parameters aren't provided");
			return;
		}

		parameters.setHeaders(headers);
		parameters.build();

		if (!Config.isEmpty()) {
			FaceTecSDK.initializeWithSessionRequest(
				(Activity) context,
				Config.DeviceKeyIdentifier,
				new SessionRequestProcessor(),
				new FaceTecSDK.InitializeCallback() {
					@Override
					public void onSuccess(@NonNull FaceTecSDKInstance instance) {
						sdkInstance = instance;
						isInitialized = true;
						onFaceTecSDKInitializationSuccess(instance);
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
						promise.reject("InitError", error.toString());
					}
				});
		} else {
			isInitialized = false;
			onInitialize(false);
			isRunning = false;
			promise.reject("ConfigNotProvided", "Configuration aren't provided");
		}
	}

	public void enroll(Map<String, Object> data, SimplePromise promise) {
		if (!canStart(promise)) return;

		DemonstrationExternalDatabaseRefID = EXTERNAL_ID + randomUUID();
		sendOpenEvent();

		sdkInstance.start3DLiveness((Activity) context, new SessionRequestProcessor(data));
		promise.resolve(true);
		isRunning = false;
	}

	private boolean canStart(SimplePromise promise) {
		if (isRunning) return false;

		isRunning = true;
		if (!isInitialized) {
			onError(true);
			promise.reject("NotInitialized", "SDK not initialized!");
			isRunning = false;
			return false;
		}

		if (!(context instanceof Activity)) {
			onError(true);
			promise.reject("NotFoundTargetView", "Target activity not found!");
			isRunning = false;
			return false;
		}

		return true;
	}

	private void onFaceTecSDKInitializationSuccess(FaceTecSDKInstance instance) {
		this.sdkInstance = instance;
		Theme theme = new Theme(context);
		Config.currentCustomization = Config.retrieveConfigurationCustomization(theme);
		Theme.setTheme();

		Vocal.setOCRLocalization(context);
		Vocal.setVocalGuidanceSoundFiles();
		Vocal.setUpVocalGuidancePlayers(context);
	}

	private void sendOpenEvent() {
		onOpen(true);
		onClose(false);
		onCancel(false);
		onError(false);
	}

	public void onInitialize(boolean value) {
		if (listener != null) listener.onInitialize(value);
		Log.d(TAG, "onInitialize: " + value);
	}

	public void onOpen(boolean value) {
		if (listener != null) listener.onOpen(value);
		Log.d(TAG, "onOpen: " + value);
	}

	public void onClose(boolean value) {
		if (listener != null) listener.onClose(value);
		Log.d(TAG, "onClose: " + value);
	}

	public void onCancel(boolean value) {
		if (listener != null) listener.onCancel(value);
		Log.d(TAG, "onCancel: " + value);
	}

	public void onError(boolean value) {
		if (listener != null) listener.onError(value);
		Log.e(TAG, "onError: " + value);
	}

	public void onVocal(boolean value) {
		if (listener != null) listener.onVocal(value);
		Log.d(TAG, "onVocal: " + value);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (sdkInstance == null) return;

		FaceTecSessionResult sessionResult = sdkInstance.getActivitySessionResult(requestCode, resultCode, data);
		if (sessionResult == null) return;

		FaceTecSessionStatus status = sessionResult.getStatus();
		boolean isCompleted = status == FaceTecSessionStatus.SESSION_COMPLETED;

		Log.d(TAG, "FaceTec session result: " + status.name());
		if (!isCompleted) DemonstrationExternalDatabaseRefID = "";

		boolean isError = error.isError(status);
		if (isError) {
			String message = error.getErrorMessage(status);
			String code = error.getErrorCode(status);
			Log.e(TAG, "Erro FaceTec: " + code + " - " + message);
		} else {
			Log.d(TAG, "FaceTec session completed successfully.");
		}
	}
}

```

`AzifaceMobilePackage.java`

```java
package com.azifacemobile;

import android.content.Context;

public class AzifaceMobilePackage {
	private final AzifaceMobileModule module;

	public AzifaceMobilePackage(Context context) {
		this.module = new AzifaceMobileModule(context);
	}

	public AzifaceMobileModule getModule() {
		return module;
	}
}

```

`AzifaceFlutterPlugin.java`

```java
package com.azifacemobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.ActivityResultListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Flutter plugin that connects to the AzifaceMobileModule.
 */
public class AzifaceFlutterPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler, ActivityAware, ActivityResultListener {
	private MethodChannel channel;
	private Activity activity;
	private Context context;
	private AzifaceMobileModule module;

	@Override
	public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
		context = binding.getApplicationContext();
		channel = new MethodChannel(binding.getBinaryMessenger(), "aziface_mobile");
		channel.setMethodCallHandler(this);
		module = new AzifaceMobileModule(context);
	}

	@Override
	public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
		switch (call.method) {
			case "initialize":
				Map<String, Object> params = call.argument("params");
				Map<String, Object> headers = call.argument("headers");
				module.initialize(params, headers, new SimplePromiseResult(result));
				break;

			case "enroll":
				module.enroll(call.argument("data"), new SimplePromiseResult(result));
				break;
			// ADD other cases for authenticate, liveness, photoIDMatch, photoIDScanOnly, setTheme, vocal...
			default:
				result.notImplemented();
		}
	}

	@Override
	public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
		if (module != null) {
			module.onActivityResult(requestCode, resultCode, data);
			return true;
		}
		return false;
	}

	@Override
	public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
		this.activity = binding.getActivity();
		binding.addActivityResultListener(this);
	}

	@Override
	public void onDetachedFromActivity() {
		activity = null;
	}

	@Override
	public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
		onAttachedToActivity(binding);
	}

	@Override
	public void onDetachedFromActivityForConfigChanges() {
		onDetachedFromActivity();
	}

	@Override
	public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
		channel.setMethodCallHandler(null);
	}

	private static class SimplePromiseResult implements AzifaceMobileModule.SimplePromise {
		private final MethodChannel.Result result;

		SimplePromiseResult(MethodChannel.Result result) {
			this.result = result;
		}

		@Override
		public void resolve(Object value) {
			result.success(value);
		}

		@Override
		public void reject(String code, String message) {
			result.error(code, message, null);
		}
	}
}

```

`MainActivity.kt`

```kotlin

package com.[YOUR_PACKAGE_NAME]

import android.content.Intent
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import com.azifacemobile.AzifaceMobileModule
import com.azifacemobile.AzifaceMobileModule.SimplePromise

class MainActivity : FlutterActivity() {
	private val CHANNEL = "com.azify/sdk"
	private lateinit var aziface: AzifaceMobileModule

	override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
		super.configureFlutterEngine(flutterEngine)

		aziface = AzifaceMobileModule(this)

		MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
			.setMethodCallHandler { call, result ->
					when (call.method) {
							"initialize" -> {
									val params = call.argument<Map<String, Any>>("params") ?: emptyMap()
									val headers = call.argument<Map<String, Any>>("headers") ?: emptyMap()

									aziface.initialize(params, headers, object : SimplePromise {
											override fun resolve(value: Any?) {
													result.success(true)
											}

											override fun reject(code: String, message: String) {
													result.error(code, message, null)
											}
									})
							}

							"enroll" -> {
									val data = call.argument<Map<String, Any>>("data") ?: emptyMap()
									aziface.enroll(data, object : SimplePromise {
											override fun resolve(value: Any?) {
													result.success(true)
											}

											override fun reject(code: String, message: String) {
													result.error(code, message, null)
											}
									})
							}
							// Add authenticate, liveness, photoIDMatch, photoIDScanOnly, setTheme, vocal...
							else -> result.notImplemented()
					}
			}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		aziface.onActivityResult(requestCode, resultCode, data)
	}
}


```

##### The Dart side structure:

`aziface_sdk.dart`

```dart
import 'package:flutter/services.dart';

class AzifaceSDK {
  static const _channel = MethodChannel('com.azify/sdk');

  static Future<bool> initialize(
    Map<String, dynamic> params,
    Map<String, dynamic> headers,
  ) async {
    final bool result = await _channel.invokeMethod('initialize', {
      'params': params,
      'headers': headers,
    });
    return result;
  }

  static Future<bool> enroll(Map<String, dynamic> data) async {
    final bool result = await _channel.invokeMethod('enroll', {'data': data});
    return result;
  }

	// Add other methods for authenticate, liveness, photoIDMatch, photoIDScanOnly, setTheme, vocal...
}

```

`main.dart`

```dart
import 'package:flutter/material.dart';
import 'aziface_sdk.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: const Text('Azify FaceTec Example')),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(
                onPressed: () async {
                  bool ok = await AzifaceSDK.initialize(
                    {
                      "DeviceKeyIdentifier": "YOUR_DEVICE_KEY_IDENTIFIER",
                      "BaseUrl":"YOUR_BASE_URL",
                      "IsDevelopment": false,
                    },
                    {
                     'x-token-bearer': 'YOUR_X_TOKEN_BEARER',
                    'x-api-key': 'YOUR_X_API_KEY',
                    'clientInfo': 'YOUR_CLIENT_INFO',
                    'contentType': 'YOUR_CONTENT_TYPE',
                    'device': 'YOUR_DEVICE',
                    'deviceid': 'YOUR_DEVICE_ID',
                    'deviceip': 'YOUR_DEVICE_IP',
                    'locale': 'YOUR_LOCALE',
                    'xForwardedFor': 'YOUR_X_FORWARDED_FOR',
                    'user-agent': 'YOUR_USER_AGENT',
                    'x-only-raw-analysis': '1',
                    },
                  );
                  debugPrint("Initialize: $ok");
                },
                child: const Text('Initialize SDK'),
              ),
              const SizedBox(height: 16),
              ElevatedButton(
                onPressed: () async {
                  bool ok = await AzifaceSDK.enroll({});
                  debugPrint("Enroll: $ok");
                },
                child: const Text('Start Enroll'),
              ),

              // Add authenticate, photoIDMatch, photoIDScanOnly...
            ],
          ),
        ),
      ),
    );
  }
}

```

### ✅ 8. Testing the Integration

Once all steps are complete, you should test the SDK integration to ensure everything works correctly.

1. Connect an Android device or start an emulator.

2. In your terminal, navigate to your Flutter project directory.

3. Run the following command:

```bash
flutter run
```

**Note**: f you encounter a release build error related to asset packaging, verify your Gradle configuration and ensure the following section is present:

```groovy
  buildTypes {
    release {
      crunchPngs false
      // ...
    }
    // ...
  }
```

### ✅ 9. Conclusion

After completing all the steps above, your Flutter project will be properly integrated, with all dependencies, assets, and native modules configured — and all unnecessary references (like the Facebook SDK) removed.

You can now use Aziface’s biometric and FaceTec functionalities directly from Flutter.

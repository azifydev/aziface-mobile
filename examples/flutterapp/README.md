# Flutter App

## A basic Flutter App

##### This example shows how to initialize the Aziface Mobile SDK and start an enrollment flow using Flutter's native communication layer (MethodChannel).

Example for lib/:

aziface_sdk.dart:

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
}

```

main.dart:

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
                      "deviceKeyIdentifier": "YOUR_DEVICE_KEY_IDENTIFIER",
                      "baseUrl": "YOUR_BASE_URL",
                      "isDevelopment": false,
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
              const SizedBox(height: 16),
            ],
          ),
        ),
      ),
    );
  }
}

```

📝 Notes
Replace all YOUR\_\* values with valid credentials provided by your backend or Azify platform.

The headers and params are required for authentication, device tracking, and other metadata. Refer to the official documentation if you are targeting production.

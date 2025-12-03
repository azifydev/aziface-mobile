package com.example.flutterapp

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
                            override fun resolve(value: Boolean?) {
                                result.success(true)
                            }

                            override fun reject( message: String, code: String) {
                                result.error(code, message, null)
                            }
                        })
                    }

                    "enroll" -> {
                        val data = call.argument<Map<String, Any>>("data") ?: emptyMap()
                        aziface.enroll(data, object : SimplePromise {
                            override fun resolve(value: Boolean?) {
                                result.success(true)
                            }

                            override fun reject(message: String, code: String) {
                                result.error(code, message, null)
                            }
                        })
                    }
                    else -> result.notImplemented()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        aziface.onActivityResult(requestCode, resultCode, data)
    }
}

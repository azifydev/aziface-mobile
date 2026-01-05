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

import java.util.Map;

public class AzifaceFlutterPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler, ActivityAware, ActivityResultListener {
    private MethodChannel channel;
    private Activity activity;
    private Context context;
    private AzifaceMobileModule module;

    public  Activity getActivity(){
      return this.activity;
    }

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

        public void resolve(Boolean value) {
            result.success(value);
        }


        public void reject(String code, String message) {
            result.error(code, message, null);
        }
    }
}

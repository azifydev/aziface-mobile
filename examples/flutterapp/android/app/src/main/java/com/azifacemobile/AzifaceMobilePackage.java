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

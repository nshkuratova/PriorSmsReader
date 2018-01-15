package com.example.nikashkuratova.smsreader.utils;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class PriorApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}

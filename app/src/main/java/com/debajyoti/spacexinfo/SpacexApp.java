package com.debajyoti.spacexinfo;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.debajyoti.spacexinfo.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.fabric.sdk.android.Fabric;

public class SpacexApp extends DaggerApplication {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Fabric.with(context, new Crashlytics());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent
                .builder()
                .application(SpacexApp.this)
                .build();
    }

    public static Context getContext() {
        return context;
    }
}

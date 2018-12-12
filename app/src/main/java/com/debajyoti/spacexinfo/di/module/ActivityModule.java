package com.debajyoti.spacexinfo.di.module;

import com.debajyoti.spacexinfo.view.detail.MissionDetailActivity;
import com.debajyoti.spacexinfo.view.detail.RocketDetailActivity;
import com.debajyoti.spacexinfo.view.main.MainActivity;
import com.debajyoti.spacexinfo.view.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract SplashActivity contributeSplashActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract RocketDetailActivity contributeRocketDetailActivity();

    @ContributesAndroidInjector
    abstract MissionDetailActivity contributeMissionDetailActivity();
}

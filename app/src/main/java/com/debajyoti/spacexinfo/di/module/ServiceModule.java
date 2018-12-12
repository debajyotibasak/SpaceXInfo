package com.debajyoti.spacexinfo.di.module;

import com.debajyoti.spacexinfo.services.FetchLaunchesJob;
import com.debajyoti.spacexinfo.view.widget.SpacexService;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract FetchLaunchesJob contributeFetchLaunchesJobService();

    @ContributesAndroidInjector
    abstract SpacexService contributeSpacexService();
}

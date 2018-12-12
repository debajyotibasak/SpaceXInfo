package com.debajyoti.spacexinfo.di.module;

import com.debajyoti.spacexinfo.view.widget.SpacexWidget;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BroadcastReceiverModule {

    @ContributesAndroidInjector
    abstract SpacexWidget contributeSpacexWidget();
}

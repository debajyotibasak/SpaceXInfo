package com.debajyoti.spacexinfo.di.module;

import com.debajyoti.spacexinfo.view.main.InfoFragment;
import com.debajyoti.spacexinfo.view.main.LatestFragment;
import com.debajyoti.spacexinfo.view.main.MissionFragment;
import com.debajyoti.spacexinfo.view.main.RocketFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract LatestFragment contributeLatestFragment();

    @ContributesAndroidInjector
    abstract MissionFragment contributeMissionFragment();

    @ContributesAndroidInjector
    abstract RocketFragment contributeRocketFragment();

    @ContributesAndroidInjector
    abstract InfoFragment contributeInfoFragment();
}

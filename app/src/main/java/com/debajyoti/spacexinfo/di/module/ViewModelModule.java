package com.debajyoti.spacexinfo.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.debajyoti.spacexinfo.di.interfaces.ViewModelKey;
import com.debajyoti.spacexinfo.factory.ViewModelFactory;
import com.debajyoti.spacexinfo.view.MainViewModel;
import com.debajyoti.spacexinfo.view.MissionDetailViewModel;
import com.debajyoti.spacexinfo.view.RocketDetailViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MissionDetailViewModel.class)
    abstract ViewModel bindMissionViewModel(MissionDetailViewModel missionDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RocketDetailViewModel.class)
    abstract ViewModel bindRocketViewModel(RocketDetailViewModel rocketDetailViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}

package com.debajyoti.spacexinfo.view;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.debajyoti.spacexinfo.api.model.Rocket;
import com.debajyoti.spacexinfo.repo.SpacexRepo;
import com.debajyoti.spacexinfo.utils.SharedPreferenceHelper;

import javax.inject.Inject;

public class RocketDetailViewModel extends ViewModel {

    private SpacexRepo spacexRepo;
    private MutableLiveData<Rocket> rocketLiveData = new MutableLiveData<>();

    @Inject
    RocketDetailViewModel(SpacexRepo repo) {
        this.spacexRepo = repo;

        repo.getRocketById(SharedPreferenceHelper.getSharedPreferenceInt("rId"))
                .observeForever(movieEntity -> rocketLiveData.setValue(movieEntity));
    }

    public MutableLiveData<Rocket> getRocketById() {
        return rocketLiveData;
    }
}

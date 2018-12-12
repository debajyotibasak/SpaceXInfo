package com.debajyoti.spacexinfo.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.debajyoti.spacexinfo.api.model.PastLaunch;
import com.debajyoti.spacexinfo.repo.SpacexRepo;
import com.debajyoti.spacexinfo.utils.SharedPreferenceHelper;

import javax.inject.Inject;

public class MissionDetailViewModel extends ViewModel {

    private SpacexRepo spacexRepo;
    private MutableLiveData<PastLaunch> missionLiveData = new MutableLiveData<>();

    @Inject
    MissionDetailViewModel(SpacexRepo repo) {
        this.spacexRepo = repo;

        repo.getPastLaunchById(SharedPreferenceHelper.getSharedPreferenceInt("mId"))
                .observeForever(movieEntity -> missionLiveData.setValue(movieEntity));
    }

    public MutableLiveData<PastLaunch> getMissionById() {
        return missionLiveData;
    }

    public LiveData<Integer> getRocketId(String rocketId){
        return spacexRepo.getRocketId(rocketId);
    }
}

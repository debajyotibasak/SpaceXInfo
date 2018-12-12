package com.debajyoti.spacexinfo.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.debajyoti.spacexinfo.api.model.Info;
import com.debajyoti.spacexinfo.api.model.LatestLaunch;
import com.debajyoti.spacexinfo.api.model.NextLaunch;
import com.debajyoti.spacexinfo.api.model.PastLaunch;
import com.debajyoti.spacexinfo.api.model.Rocket;
import com.debajyoti.spacexinfo.repo.SpacexRepo;
import com.debajyoti.spacexinfo.utils.ApiResponse;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private SpacexRepo spacexRepo;

    @Inject
    MainViewModel(SpacexRepo repo) {
        this.spacexRepo = repo;
    }

    public LiveData<ApiResponse<Info>> getInfo() {
        return spacexRepo.fetchInfo();
    }

    public LiveData<Info> getInfoFromDb() {
        return spacexRepo.getInfoFromDb();
    }

    public LiveData<ApiResponse<List<Rocket>>> getRockets() {
        return spacexRepo.fetchRocket();
    }

    public LiveData<List<Rocket>> getRocketFromDb() {
        return spacexRepo.getRocketFromDb();
    }

    public LiveData<ApiResponse<List<PastLaunch>>> getPastLaunches() {
        return spacexRepo.fetchPastLaunches();
    }

    public LiveData<List<PastLaunch>> getPastLaunchesFromDb() {
        return spacexRepo.getPastLaunchesFromDb();
    }

    public LiveData<ApiResponse<LatestLaunch>> getLatestLaunch() {
        return spacexRepo.fetchLatestLaunch();
    }

    public LiveData<List<PastLaunch>> getPastFiveLaunchesFromDb() {
        return spacexRepo.getPastFiveLaunchesFromDb();
    }

    public LiveData<LatestLaunch> getLatestLaunchesFromDb() {
        return spacexRepo.getLatestLaunchFromDb();
    }

    public LiveData<ApiResponse<NextLaunch>> getNextLaunch() {
        return spacexRepo.fetchNextLaunch();
    }

    public LiveData<NextLaunch> getNextLaunchFromDb() {
        return spacexRepo.getNextLaunchFromDb();
    }
}

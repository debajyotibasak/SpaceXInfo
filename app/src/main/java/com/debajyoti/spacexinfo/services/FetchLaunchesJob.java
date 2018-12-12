package com.debajyoti.spacexinfo.services;

import com.debajyoti.spacexinfo.repo.SpacexRepo;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.SimpleJobService;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class FetchLaunchesJob extends SimpleJobService {

    @Inject
    SpacexRepo spacexRepo;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public int onRunJob(JobParameters job) {
        spacexRepo.fetchLaunch();
        return RESULT_SUCCESS;
    }
}

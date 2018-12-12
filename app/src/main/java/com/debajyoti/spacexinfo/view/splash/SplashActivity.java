package com.debajyoti.spacexinfo.view.splash;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.debajyoti.spacexinfo.R;
import com.debajyoti.spacexinfo.repo.PreferencesRepo;
import com.debajyoti.spacexinfo.services.LaunchJobUtilities;
import com.debajyoti.spacexinfo.utils.AppConstants;
import com.debajyoti.spacexinfo.utils.AppUtils;
import com.debajyoti.spacexinfo.utils.SharedPreferenceHelper;
import com.debajyoti.spacexinfo.view.MainViewModel;
import com.debajyoti.spacexinfo.view.main.MainActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SplashActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    PreferencesRepo preferencesRepo;

    private MainViewModel mainViewModel;
    private ProgressBar progressBar;
    private View snackBarView;
    private Handler handler = new Handler();
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LaunchJobUtilities.scheduleTemplateFetch(this);
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        initViews();
        loadInfo();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress);
        snackBarView = findViewById(android.R.id.content);
    }

    private void loadInfo() {
        progressBar.setVisibility(View.VISIBLE);
        if (AppUtils.isNetworkAvailable()) {
            if (SharedPreferenceHelper.getSharedPreferenceLong(AppConstants.INFO_API_TIMESTAMP, 0L) == 0) {
                mainViewModel.getInfo().observe(this, info -> {
                    if (info != null) {
                        if (info.getResponse() != null) {
                            getRockets();
                        } else if (info.getT() != null) {
                            Toast.makeText(this, "Some Error Occurred please try again", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "ERROR: " + info.getT().toString());
                            Crashlytics.log(Log.DEBUG, TAG, info.getT().toString());
                        }
                    }
                });
            } else {
                getRockets();
            }
        } else {
            AppUtils.setSnackBar(snackBarView, "Internet Connectivity Issue. Please try again");
        }
    }

    private void getRockets() {
        if (AppUtils.isNetworkAvailable()) {
            if (SharedPreferenceHelper.getSharedPreferenceLong(AppConstants.ROCKET_API_TIMESTAMP, 0L) == 0) {
                mainViewModel.getRockets().observe(this, rockets -> {
                    if (rockets != null) {
                        if (rockets.getResponse() != null) {
                            getPastLaunches();
                        } else if (rockets.getT() != null) {
                            Toast.makeText(this, "Some Error Occurred please try again", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "ERROR: " + rockets.getT().toString());
                            Crashlytics.log(Log.DEBUG, TAG, rockets.getT().toString());
                        }
                    }
                });
            } else {
                getPastLaunches();
            }
        } else {
            AppUtils.setSnackBar(snackBarView, "Internet Connectivity Issue. Please try again");
        }
    }

    private void getPastLaunches() {
        if (AppUtils.isNetworkAvailable()) {
            if (AppUtils.shouldFetchData(
                    System.currentTimeMillis(),
                    SharedPreferenceHelper.getSharedPreferenceLong(AppConstants.PAST_LAUNCHES_API_TIMESTAMP, 0L))) {

                mainViewModel.getPastLaunches().observe(this, pastLaunches -> {
                    if (pastLaunches != null) {
                        if (pastLaunches.getResponse() != null) {
                            getLatestLaunch();
                        } else if (pastLaunches.getT() != null) {
                            Toast.makeText(this, "Some Error Occurred please try again", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "ERROR: " + pastLaunches.getT().toString());
                            Crashlytics.log(Log.DEBUG, TAG, pastLaunches.getT().toString());
                        }
                    }
                });
            } else {
                getLatestLaunch();
            }
        } else {
            AppUtils.setSnackBar(snackBarView, "Internet Connectivity Issue. Please try again");
        }
    }

    private void getLatestLaunch() {
        if (AppUtils.isNetworkAvailable()) {
            if (AppUtils.shouldFetchData(
                    System.currentTimeMillis(),
                    SharedPreferenceHelper.getSharedPreferenceLong(AppConstants.LATEST_LAUNCH_API_TIMESTAMP, 0L))) {

                mainViewModel.getLatestLaunch().observe(this, latestLaunch -> {
                    if (latestLaunch != null) {
                        if (latestLaunch.getResponse() != null) {
                            mainViewModel.getPastFiveLaunchesFromDb().observe(this, pastLaunches -> {
                                if (pastLaunches != null) {
                                    preferencesRepo.deleteLaunches();
                                    preferencesRepo.saveLaunchesWidget(pastLaunches);
                                }
                            });
                            getNextLaunch();
                        } else if (latestLaunch.getT() != null) {
                            Toast.makeText(this, "Some Error Occurred please try again", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "ERROR: " + latestLaunch.getT().toString());
                            Crashlytics.log(Log.DEBUG, TAG, latestLaunch.getT().toString());
                        }
                    }
                });
            } else {
                getNextLaunch();
            }
        } else {
            AppUtils.setSnackBar(snackBarView, "Internet Connectivity Issue. Please try again");
        }
    }

    private void getNextLaunch() {
        if (AppUtils.isNetworkAvailable()) {
            if (AppUtils.shouldFetchData(
                    System.currentTimeMillis(),
                    SharedPreferenceHelper.getSharedPreferenceLong(AppConstants.NEXT_LAUNCH_API_TIMESTAMP, 0L))) {

                mainViewModel.getNextLaunch().observe(this, nextLaunch -> {
                    if (nextLaunch != null) {
                        if (nextLaunch.getResponse() != null) {
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(SplashActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        } else if (nextLaunch.getT() != null) {
                            Toast.makeText(this, "Some Error Occurred please try again", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "ERROR: " + nextLaunch.getT().toString());
                            Crashlytics.log(Log.DEBUG, TAG, nextLaunch.getT().toString());
                        }
                    }
                });
            } else {
                handler.postDelayed(() -> {
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }, 1000);
            }
        } else {
            AppUtils.setSnackBar(snackBarView, "Internet Connectivity Issue. Please try again");
        }
    }
}

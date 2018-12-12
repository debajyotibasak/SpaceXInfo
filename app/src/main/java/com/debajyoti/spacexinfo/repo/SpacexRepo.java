package com.debajyoti.spacexinfo.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.debajyoti.spacexinfo.api.ApiInterface;
import com.debajyoti.spacexinfo.api.model.Info;
import com.debajyoti.spacexinfo.api.model.LatestLaunch;
import com.debajyoti.spacexinfo.api.model.NextLaunch;
import com.debajyoti.spacexinfo.api.model.PastLaunch;
import com.debajyoti.spacexinfo.api.model.Rocket;
import com.debajyoti.spacexinfo.db.dao.InfoDao;
import com.debajyoti.spacexinfo.db.dao.LatestLaunchDao;
import com.debajyoti.spacexinfo.db.dao.NextLaunchDao;
import com.debajyoti.spacexinfo.db.dao.PastLaunchesDao;
import com.debajyoti.spacexinfo.db.dao.RocketsDao;
import com.debajyoti.spacexinfo.utils.ApiResponse;
import com.debajyoti.spacexinfo.utils.AppConstants;
import com.debajyoti.spacexinfo.utils.AppExecutor;
import com.debajyoti.spacexinfo.utils.SharedPreferenceHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class SpacexRepo {

    private final ApiInterface apiInterface;
    private final InfoDao infoDao;
    private final RocketsDao rocketsDao;
    private final PastLaunchesDao pastLaunchesDao;
    private final NextLaunchDao nextLaunchDao;
    private final LatestLaunchDao latestLaunchDao;
    private final AppExecutor executor;

    private static final String TAG = SpacexRepo.class.getSimpleName();

    @Inject
    PreferencesRepo preferencesRepo;

    public SpacexRepo(ApiInterface apiInterface,
                      InfoDao infoDao,
                      RocketsDao rocketsDao,
                      PastLaunchesDao pastLaunchesDao,
                      NextLaunchDao nextLaunchDao,
                      LatestLaunchDao latestLaunchDao,
                      AppExecutor executor) {

        this.apiInterface = apiInterface;
        this.infoDao = infoDao;
        this.rocketsDao = rocketsDao;
        this.pastLaunchesDao = pastLaunchesDao;
        this.nextLaunchDao = nextLaunchDao;
        this.latestLaunchDao = latestLaunchDao;
        this.executor = executor;
    }

    public LiveData<ApiResponse<Info>> fetchInfo() {
        final MutableLiveData<ApiResponse<Info>> liveData = new MutableLiveData<>();
        Call<Info> call = apiInterface.getInfo();
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(@NonNull Call<Info> call, @NonNull Response<Info> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(new ApiResponse<>(response.body()));
                    if (response.body().getName() != null && response.body().getName().length() > 0) {
                        liveData.observeForever(
                                info -> executor.diskIO().execute(() -> {
                                    infoDao.deleteInfo();
                                    infoDao.insertInfo(response.body());
                                    SharedPreferenceHelper.setSharedPreferenceLong(AppConstants.INFO_API_TIMESTAMP, System.currentTimeMillis());
                                })
                        );
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Info> call, @NonNull Throwable t) {
                liveData.setValue(new ApiResponse<>(t));
            }
        });
        return liveData;
    }

    public LiveData<Info> getInfoFromDb() {
        return infoDao.getInfoFromDb();
    }

    public LiveData<ApiResponse<List<Rocket>>> fetchRocket() {
        final MutableLiveData<ApiResponse<List<Rocket>>> liveData = new MutableLiveData<>();
        Call<List<Rocket>> call = apiInterface.getRockets();
        call.enqueue(new Callback<List<Rocket>>() {
            @Override
            public void onResponse(@NonNull Call<List<Rocket>> call, @NonNull Response<List<Rocket>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(new ApiResponse<>(response.body()));
                    if (!response.body().isEmpty()) {
                        liveData.observeForever(
                                info -> executor.diskIO().execute(() -> {
                                    rocketsDao.insertRocketList(response.body());
                                    SharedPreferenceHelper.setSharedPreferenceLong(AppConstants.ROCKET_API_TIMESTAMP, System.currentTimeMillis());
                                })
                        );
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Rocket>> call, @NonNull Throwable t) {
                liveData.setValue(new ApiResponse<>(t));
            }
        });
        return liveData;
    }

    public LiveData<List<Rocket>> getRocketFromDb() {
        return rocketsDao.getRocketFromDb();
    }

    public LiveData<ApiResponse<List<PastLaunch>>> fetchPastLaunches() {
        final MutableLiveData<ApiResponse<List<PastLaunch>>> liveData = new MutableLiveData<>();
        Call<List<PastLaunch>> call = apiInterface.getPastLaunches();
        call.enqueue(new Callback<List<PastLaunch>>() {
            @Override
            public void onResponse(@NonNull Call<List<PastLaunch>> call, @NonNull Response<List<PastLaunch>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(new ApiResponse<>(response.body()));
                    if (!response.body().isEmpty()) {
                        liveData.observeForever(
                                info -> executor.diskIO().execute(() -> {
                                    pastLaunchesDao.insertPastLaunches(response.body());
                                    SharedPreferenceHelper.setSharedPreferenceLong(AppConstants.PAST_LAUNCHES_API_TIMESTAMP, System.currentTimeMillis());
                                })
                        );
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PastLaunch>> call, @NonNull Throwable t) {
                liveData.setValue(new ApiResponse<>(t));
            }
        });
        return liveData;
    }

    public LiveData<List<PastLaunch>> getPastLaunchesFromDb() {
        return pastLaunchesDao.getPastLaunchesFromDb();
    }

    public LiveData<List<PastLaunch>> getPastFiveLaunchesFromDb() {
        return pastLaunchesDao.getPastfiveLaunchesFromDb();
    }


    public LiveData<ApiResponse<LatestLaunch>> fetchLatestLaunch() {
        final MutableLiveData<ApiResponse<LatestLaunch>> liveData = new MutableLiveData<>();
        Call<LatestLaunch> call = apiInterface.getLatestLaunch();
        call.enqueue(new Callback<LatestLaunch>() {
            @Override
            public void onResponse(@NonNull Call<LatestLaunch> call, @NonNull Response<LatestLaunch> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(new ApiResponse<>(response.body()));
                    if (response.body().getFlightNumber() > 0) {
                        liveData.observeForever(
                                info -> executor.diskIO().execute(() -> {
                                    latestLaunchDao.deleteLatestLaunch();
                                    latestLaunchDao.insertLatestLaunch(response.body());
                                    SharedPreferenceHelper.setSharedPreferenceLong(AppConstants.LATEST_LAUNCH_API_TIMESTAMP, System.currentTimeMillis());
                                })
                        );
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LatestLaunch> call, @NonNull Throwable t) {
                liveData.setValue(new ApiResponse<>(t));
            }
        });
        return liveData;
    }

    public LiveData<LatestLaunch> getLatestLaunchFromDb() {
        return latestLaunchDao.getLatestLaunchFromDb();
    }

    public LiveData<ApiResponse<NextLaunch>> fetchNextLaunch() {
        final MutableLiveData<ApiResponse<NextLaunch>> liveData = new MutableLiveData<>();
        Call<NextLaunch> call = apiInterface.getNextLaunch();
        call.enqueue(new Callback<NextLaunch>() {
            @Override
            public void onResponse(@NonNull Call<NextLaunch> call, @NonNull Response<NextLaunch> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(new ApiResponse<>(response.body()));
                    if (response.body().getFlightNumber() > 0) {
                        liveData.observeForever(
                                info -> executor.diskIO().execute(() -> {
                                    nextLaunchDao.deleteNextLaunch();
                                    nextLaunchDao.insertNextLaunch(response.body());
                                    SharedPreferenceHelper.setSharedPreferenceLong(AppConstants.NEXT_LAUNCH_API_TIMESTAMP, System.currentTimeMillis());
                                })
                        );
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<NextLaunch> call, @NonNull Throwable t) {
                liveData.setValue(new ApiResponse<>(t));
            }
        });
        return liveData;
    }

    public LiveData<NextLaunch> getNextLaunchFromDb() {
        return nextLaunchDao.getNexttLaunchFromDb();
    }

    public LiveData<PastLaunch> getPastLaunchById(int missionId) {
        return pastLaunchesDao.getPastLaunchById(missionId);
    }

    public LiveData<Rocket> getRocketById(int rocketId) {
        return rocketsDao.getRocketById(rocketId);
    }

    public LiveData<Integer> getRocketId(String rocketId) {
        return rocketsDao.getRocketId(rocketId);
    }

    public void fetchLaunch() {
        Call<List<PastLaunch>> call = apiInterface.getPastLaunches();
        call.enqueue(new Callback<List<PastLaunch>>() {
            @Override
            public void onResponse(@NonNull Call<List<PastLaunch>> call, @NonNull Response<List<PastLaunch>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (!response.body().isEmpty()) {
                        executor.diskIO().execute(() -> {
                            pastLaunchesDao.insertPastLaunches(response.body());
                            SharedPreferenceHelper.setSharedPreferenceLong(AppConstants.PAST_LAUNCHES_API_TIMESTAMP, System.currentTimeMillis());
                        });
                        latestLaunch();
                        Log.d(TAG, "Firebase Job Dispatcher JOB DONE");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PastLaunch>> call, @NonNull Throwable t) {
                latestLaunch();
                Log.e(TAG, "Firebase Job Dispatcher JOB ERROR", t);
            }
        });
    }

    private void latestLaunch() {
        Call<LatestLaunch> call = apiInterface.getLatestLaunch();
        call.enqueue(new Callback<LatestLaunch>() {
            @Override
            public void onResponse(@NonNull Call<LatestLaunch> call, @NonNull Response<LatestLaunch> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getFlightNumber() > 0) {
                        executor.diskIO().execute(() -> {
                            latestLaunchDao.deleteLatestLaunch();
                            latestLaunchDao.insertLatestLaunch(response.body());
                            SharedPreferenceHelper.setSharedPreferenceLong(AppConstants.LATEST_LAUNCH_API_TIMESTAMP, System.currentTimeMillis());
                        });
                        pastLaunch();
                        Log.d(TAG, "Firebase Job Dispatcher JOB DONE");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LatestLaunch> call, @NonNull Throwable t) {
                pastLaunch();
                Log.e(TAG, "Firebase Job Dispatcher ERROR", t);
            }
        });
    }

    private void pastLaunch() {
        Call<NextLaunch> call = apiInterface.getNextLaunch();
        call.enqueue(new Callback<NextLaunch>() {
            @Override
            public void onResponse(@NonNull Call<NextLaunch> call, @NonNull Response<NextLaunch> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getFlightNumber() > 0) {
                        executor.diskIO().execute(() -> {
                            nextLaunchDao.deleteNextLaunch();
                            nextLaunchDao.insertNextLaunch(response.body());
                            SharedPreferenceHelper.setSharedPreferenceLong(AppConstants.NEXT_LAUNCH_API_TIMESTAMP, System.currentTimeMillis());
                        });
                        Log.d(TAG, "Firebase Job Dispatcher JOB DONE");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<NextLaunch> call, @NonNull Throwable t) {
                Log.e(TAG, "Firebase Job Dispatcher ERROR", t);
            }
        });
    }
}

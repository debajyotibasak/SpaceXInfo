package com.debajyoti.spacexinfo.api;

import com.debajyoti.spacexinfo.api.model.Info;
import com.debajyoti.spacexinfo.api.model.LatestLaunch;
import com.debajyoti.spacexinfo.api.model.NextLaunch;
import com.debajyoti.spacexinfo.api.model.PastLaunch;
import com.debajyoti.spacexinfo.api.model.Rocket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.debajyoti.spacexinfo.utils.AppConstants.INFO_ENDPOINT;
import static com.debajyoti.spacexinfo.utils.AppConstants.LATEST_LAUNCH_ENDPOINT;
import static com.debajyoti.spacexinfo.utils.AppConstants.NEXT_LAUNCH_ENDPOINT;
import static com.debajyoti.spacexinfo.utils.AppConstants.PAST_LAUNCHES_ENDPOINT;
import static com.debajyoti.spacexinfo.utils.AppConstants.ROCKETS_ENDPOINT;

public interface ApiInterface {

    @GET(INFO_ENDPOINT)
    Call<Info> getInfo();

    @GET(ROCKETS_ENDPOINT)
    Call<List<Rocket>> getRockets();

    @GET(PAST_LAUNCHES_ENDPOINT)
    Call<List<PastLaunch>> getPastLaunches();

    @GET(LATEST_LAUNCH_ENDPOINT)
    Call<LatestLaunch> getLatestLaunch();

    @GET(NEXT_LAUNCH_ENDPOINT)
    Call<NextLaunch> getNextLaunch();
}

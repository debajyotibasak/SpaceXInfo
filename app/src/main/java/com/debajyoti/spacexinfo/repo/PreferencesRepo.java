package com.debajyoti.spacexinfo.repo;

import android.content.SharedPreferences;

import com.debajyoti.spacexinfo.api.model.PastLaunch;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.debajyoti.spacexinfo.utils.AppConstants.WIDGET_PREFIX;

@Singleton
public class PreferencesRepo {

    private SharedPreferences prefs;
    private Gson gson;
    private Type type;

    @Inject
    public PreferencesRepo(SharedPreferences prefs, Gson gson, Type type) {
        this.prefs = prefs;
        this.gson = gson;
        this.type = type;
    }

    public void saveLaunchesWidget(List<PastLaunch> launches) {
        prefs.edit()
                .putString(WIDGET_PREFIX, gson.toJson(launches, type))
                .apply();
    }

    public List<PastLaunch> getLaunchesWidget() {
        String pastLaunchesJson = prefs.getString(WIDGET_PREFIX, null);
        if (pastLaunchesJson == null) return null;
        return gson.fromJson(pastLaunchesJson, type);
    }

    public void deleteLaunches() {
        prefs.edit()
                .remove(WIDGET_PREFIX)
                .apply();
    }

}

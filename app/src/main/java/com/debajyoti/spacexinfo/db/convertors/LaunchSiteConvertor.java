package com.debajyoti.spacexinfo.db.convertors;

import android.arch.persistence.room.TypeConverter;

import com.debajyoti.spacexinfo.api.model.Height;
import com.debajyoti.spacexinfo.api.model.LaunchSite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class LaunchSiteConvertor {

    @TypeConverter
    public String fromLaunchSite(LaunchSite launchSite) {
        if (launchSite == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<LaunchSite>() {}.getType();
        return gson.toJson(launchSite, type);
    }

    @TypeConverter
    public LaunchSite toLaunchSite(String launchSite) {
        if (launchSite == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<LaunchSite>() {}.getType();
        return gson.fromJson(launchSite, type);
    }
}

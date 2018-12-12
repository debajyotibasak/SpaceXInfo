package com.debajyoti.spacexinfo.db.convertors;

import android.arch.persistence.room.TypeConverter;

import com.debajyoti.spacexinfo.api.model.Height;
import com.debajyoti.spacexinfo.api.model.LaunchLinks;
import com.debajyoti.spacexinfo.api.model.Mass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class LaunchLinksConvertor {

    @TypeConverter
    public String fromLaunchLinks(LaunchLinks launchLinks) {
        if (launchLinks == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<LaunchLinks>() {}.getType();
        return gson.toJson(launchLinks, type);
    }

    @TypeConverter
    public LaunchLinks tolaunchLinks(String launchLinks) {
        if (launchLinks == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<LaunchLinks>() {}.getType();
        return gson.fromJson(launchLinks, type);
    }
}

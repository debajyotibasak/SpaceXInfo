package com.debajyoti.spacexinfo.db.convertors;

import android.arch.persistence.room.TypeConverter;

import com.debajyoti.spacexinfo.api.model.Height;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class HeightConvertor {

    @TypeConverter
    public String fromHeight(Height height) {
        if (height == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Height>() {}.getType();
        return gson.toJson(height, type);
    }

    @TypeConverter
    public Height toHeight(String height) {
        if (height == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Height>() {}.getType();
        return gson.fromJson(height, type);
    }
}

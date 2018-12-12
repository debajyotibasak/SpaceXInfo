package com.debajyoti.spacexinfo.db.convertors;

import android.arch.persistence.room.TypeConverter;

import com.debajyoti.spacexinfo.api.model.RocketId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class RocketIdConvertor {

    @TypeConverter
    public String fromRocketId(RocketId rocketId) {
        if (rocketId == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<RocketId>() {}.getType();
        return gson.toJson(rocketId, type);
    }

    @TypeConverter
    public RocketId toMass(String rocketId) {
        if (rocketId == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<RocketId>() {}.getType();
        return gson.fromJson(rocketId, type);
    }
}

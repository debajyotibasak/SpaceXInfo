package com.debajyoti.spacexinfo.db.convertors;

import android.arch.persistence.room.TypeConverter;

import com.debajyoti.spacexinfo.api.model.Diameter;
import com.debajyoti.spacexinfo.api.model.Headquarters;
import com.debajyoti.spacexinfo.api.model.Height;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class HeadquarterConvertor {

    @TypeConverter
    public String fromHeadquarter(Headquarters headquarters) {
        if (headquarters == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Headquarters>() {}.getType();
        return gson.toJson(headquarters, type);
    }

    @TypeConverter
    public Headquarters toHeadquarter(String headquarter) {
        if (headquarter == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Headquarters>() {}.getType();
        return gson.fromJson(headquarter, type);
    }
}

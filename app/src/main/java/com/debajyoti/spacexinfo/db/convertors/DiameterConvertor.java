package com.debajyoti.spacexinfo.db.convertors;

import android.arch.persistence.room.TypeConverter;

import com.debajyoti.spacexinfo.api.model.Diameter;
import com.debajyoti.spacexinfo.api.model.Height;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class DiameterConvertor {

    @TypeConverter
    public String fromDiameter(Diameter diameter) {
        if (diameter == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Diameter>() {}.getType();
        return gson.toJson(diameter, type);
    }

    @TypeConverter
    public Diameter toDiameter(String diameter) {
        if (diameter == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Diameter>() {}.getType();
        return gson.fromJson(diameter, type);
    }
}

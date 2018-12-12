package com.debajyoti.spacexinfo.db.convertors;

import android.arch.persistence.room.TypeConverter;

import com.debajyoti.spacexinfo.api.model.Height;
import com.debajyoti.spacexinfo.api.model.Mass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MassConvertor {

    @TypeConverter
    public String fromMass(Mass mass) {
        if (mass == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Mass>() {}.getType();
        return gson.toJson(mass, type);
    }

    @TypeConverter
    public Mass toMass(String mass) {
        if (mass == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Mass>() {}.getType();
        return gson.fromJson(mass, type);
    }
}

package com.debajyoti.spacexinfo.db.convertors;

import android.arch.persistence.room.TypeConverter;

import com.debajyoti.spacexinfo.api.model.FirstStage;
import com.debajyoti.spacexinfo.api.model.Height;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class FirstStageConvertor {

    @TypeConverter
    public String fromFirstStage(FirstStage firstStage) {
        if (firstStage == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<FirstStage>() {}.getType();
        return gson.toJson(firstStage, type);
    }

    @TypeConverter
    public FirstStage toFirstStage(String firstStage) {
        if (firstStage == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<FirstStage>() {}.getType();
        return gson.fromJson(firstStage, type);
    }
}

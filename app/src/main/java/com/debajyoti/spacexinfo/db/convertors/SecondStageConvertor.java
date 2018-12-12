package com.debajyoti.spacexinfo.db.convertors;

import android.arch.persistence.room.TypeConverter;

import com.debajyoti.spacexinfo.api.model.Height;
import com.debajyoti.spacexinfo.api.model.SecondStage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SecondStageConvertor {

    @TypeConverter
    public String fromSecondStage(SecondStage secondStage) {
        if (secondStage == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<SecondStage>() {}.getType();
        return gson.toJson(secondStage, type);
    }

    @TypeConverter
    public SecondStage toSecondStage(String secondStage) {
        if (secondStage == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<SecondStage>() {}.getType();
        return gson.fromJson(secondStage, type);
    }
}

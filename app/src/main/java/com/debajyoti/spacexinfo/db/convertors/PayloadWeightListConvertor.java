package com.debajyoti.spacexinfo.db.convertors;

import android.arch.persistence.room.TypeConverter;

import com.debajyoti.spacexinfo.api.model.Height;
import com.debajyoti.spacexinfo.api.model.PayLoadWeight;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PayloadWeightListConvertor {

    @TypeConverter
    public String fromPayloadWeightList(List<PayLoadWeight> payloadList) {
        if (payloadList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PayLoadWeight>>() {}.getType();
        return gson.toJson(payloadList, type);
    }

    @TypeConverter
    public List<PayLoadWeight> toPayloadWeightList(String payloadList) {
        if (payloadList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<PayLoadWeight>>() {}.getType();
        return gson.fromJson(payloadList, type);
    }
}

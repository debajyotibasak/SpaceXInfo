package com.debajyoti.spacexinfo.db.convertors;

import android.arch.persistence.room.TypeConverter;

import com.debajyoti.spacexinfo.api.model.Height;
import com.debajyoti.spacexinfo.api.model.Links;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class LinkConvertor {

    @TypeConverter
    public String fromLink(Links links) {
        if (links == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Links>() {}.getType();
        return gson.toJson(links, type);
    }

    @TypeConverter
    public Links toLink(String links) {
        if (links == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Links>() {}.getType();
        return gson.fromJson(links, type);
    }
}

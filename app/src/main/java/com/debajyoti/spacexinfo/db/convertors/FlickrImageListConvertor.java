package com.debajyoti.spacexinfo.db.convertors;

import android.arch.persistence.room.TypeConverter;

import com.debajyoti.spacexinfo.api.model.Height;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FlickrImageListConvertor {

    @TypeConverter
    public String fromFlickrImageList(List<String> imageList) {
        if (imageList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.toJson(imageList, type);
    }

    @TypeConverter
    public List<String> toFlickrImageList(String imageList) {
        if (imageList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(imageList, type);
    }
}

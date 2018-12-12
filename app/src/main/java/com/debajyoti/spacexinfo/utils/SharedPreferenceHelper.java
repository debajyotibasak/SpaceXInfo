package com.debajyoti.spacexinfo.utils;

import android.content.SharedPreferences;

import com.debajyoti.spacexinfo.SpacexApp;

public class SharedPreferenceHelper {

    public static void setSharedPreferenceInt(String key, int value) {
        SharedPreferences settings = SpacexApp.getContext().getSharedPreferences(AppConstants.PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getSharedPreferenceInt(String key) {
        SharedPreferences settings = SpacexApp.getContext().getSharedPreferences(AppConstants.PREF, 0);
        return settings.getInt(key, 0);
    }

    public static void setSharedPreferenceString(String key, String value) {
        SharedPreferences settings = SpacexApp.getContext().getSharedPreferences(AppConstants.PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getSharedPreferenceString(String key, String defValue) {
        SharedPreferences settings = SpacexApp.getContext().getSharedPreferences(AppConstants.PREF, 0);
        return settings.getString(key, defValue);
    }

    public static void setSharedPreferenceLong(String key, long value) {
        SharedPreferences settings = SpacexApp.getContext().getSharedPreferences(AppConstants.PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static long getSharedPreferenceLong(String key, long defValue) {
        SharedPreferences settings = SpacexApp.getContext().getSharedPreferences(AppConstants.PREF, 0);
        return settings.getLong(key, defValue);
    }

    public static boolean contains(String key) {
        SharedPreferences settings = SpacexApp.getContext().getSharedPreferences(AppConstants.PREF, 0);
        return settings.contains(key);
    }

}
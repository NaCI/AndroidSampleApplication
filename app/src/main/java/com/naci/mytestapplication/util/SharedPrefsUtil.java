package com.naci.mytestapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPrefsUtil {

    private SharedPreferences preferences;
    private Gson gson;

    @Inject
    public SharedPrefsUtil(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        gson = new Gson();
    }

    public SharedPrefsUtil(Context context, String prefsName) {
        preferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void putString(String key, String value) {
        preferences.edit().putString(key, gson.toJson(value)).apply();
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public void putObject(String key, Object value) {
        preferences.edit().putString(key, gson.toJson(value)).apply();
    }

    public <T> T getObject(String key, Class<T> cls) {
        String string = preferences.getString(key, "");
        return gson.fromJson(string, cls);
    }

    public void putBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public void clear(String key) {
        preferences.edit().putString(key, "").apply();
    }

    public void clearAll() {
        preferences.edit().clear().apply();
    }

}

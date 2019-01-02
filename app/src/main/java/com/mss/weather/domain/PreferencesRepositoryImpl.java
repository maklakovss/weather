package com.mss.weather.domain;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class PreferencesRepositoryImpl implements PreferencesRepository {

    private static final String NAME_PREF = "weather_pref";
    private static final String DEFAULT_CITY_KEY = "default_city_key";

    private final SharedPreferences sharedPreferences;

    @Inject
    public PreferencesRepositoryImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE);
    }

    @Override
    public String getDefaultCityName() {
        return sharedPreferences.getString(DEFAULT_CITY_KEY, "");
    }

    @Override
    public void setDefaultCityName(String cityName) {
        sharedPreferences.edit().putString(DEFAULT_CITY_KEY, cityName).apply();
    }
}

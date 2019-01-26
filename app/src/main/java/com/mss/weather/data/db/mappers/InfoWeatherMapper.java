package com.mss.weather.data.db.mappers;

import android.support.annotation.NonNull;

import com.mss.weather.data.db.models.InfoWeatherDB;
import com.mss.weather.domain.models.InfoWeather;

public class InfoWeatherMapper {

    @NonNull
    public static InfoWeather mapInfoWeatherDbToInfoWeather(@NonNull final InfoWeatherDB infoWeatherDB) {
        final InfoWeather infoWeather = new InfoWeather();
        infoWeather.setCityID(infoWeatherDB.getCityID());
        infoWeather.setDateState(infoWeatherDB.getDateState());
        return infoWeather;
    }

    @NonNull
    public static InfoWeatherDB mapInfoWeatherToInfoWeatherDB(@NonNull final InfoWeather infoWeather) {
        final InfoWeatherDB infoWeatherDB = new InfoWeatherDB();
        infoWeatherDB.setCityID(infoWeather.getCityID());
        infoWeatherDB.setDateState(infoWeather.getDateState());
        return infoWeatherDB;
    }
}

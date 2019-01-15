package com.mss.weather.data.db.mappers;

import com.mss.weather.data.db.models.InfoWeatherDB;
import com.mss.weather.domain.models.InfoWeather;

public class InfoWeatherMapper {

    public static InfoWeather mapInfoWeatherDbToInfoWeather(InfoWeatherDB infoWeatherDB) {
        InfoWeather infoWeather = new InfoWeather();
        infoWeather.setCityID(infoWeatherDB.getCityID());
        infoWeather.setDateState(infoWeatherDB.getDateState());
        return infoWeather;
    }

    public static InfoWeatherDB mapInfoWeatherToInfoWeatherDB(InfoWeather infoWeather) {
        InfoWeatherDB infoWeatherDB = new InfoWeatherDB();
        infoWeatherDB.setCityID(infoWeather.getCityID());
        infoWeatherDB.setDateState(infoWeather.getDateState());
        return null;
    }
}

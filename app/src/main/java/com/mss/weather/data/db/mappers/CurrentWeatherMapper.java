package com.mss.weather.data.db.mappers;

import android.support.annotation.NonNull;

import com.mss.weather.data.db.models.CurrentWeatherDB;
import com.mss.weather.domain.models.CurrentWeather;

public class CurrentWeatherMapper {

    @NonNull
    public static CurrentWeather mapCurrentWeatherDbToCurrentWeather(@NonNull final CurrentWeatherDB currentWeatherDB) {
        final CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setCityID(currentWeatherDB.getCityID());
        currentWeather.setCloudcover(currentWeatherDB.getCloudcover());
        currentWeather.setDate(currentWeatherDB.getDate());
        currentWeather.setFeelsLikeC(currentWeatherDB.getFeelsLikeC());
        currentWeather.setFeelsLikeF(currentWeatherDB.getFeelsLikeF());
        currentWeather.setHumidity(currentWeatherDB.getHumidity());
        currentWeather.setObservationTime(currentWeatherDB.getObservationTime());
        currentWeather.setPrecipMM(currentWeatherDB.getPrecipMM());
        currentWeather.setPressure(currentWeatherDB.getPressure());
        currentWeather.setTempC(currentWeatherDB.getTempC());
        currentWeather.setTempF(currentWeatherDB.getTempF());
        currentWeather.setVisibility(currentWeatherDB.getVisibility());
        currentWeather.setWeatherCode(currentWeatherDB.getWeatherCode());
        currentWeather.setWeatherDesc(currentWeatherDB.getWeatherDesc());
        currentWeather.setWeatherDescLocalLanguage(currentWeatherDB.getWeatherDescLocalLanguage());
        currentWeather.setWeatherIconUrl(currentWeatherDB.getWeatherIconUrl());
        currentWeather.setWinddir16Point(currentWeatherDB.getWinddir16Point());
        currentWeather.setWinddirDegree(currentWeatherDB.getWinddirDegree());
        currentWeather.setWindspeedKmph(currentWeatherDB.getWindspeedKmph());
        currentWeather.setWindspeedMiles(currentWeatherDB.getWindspeedMiles());
        return currentWeather;
    }

    @NonNull
    public static CurrentWeatherDB mapInfoWeatherDBToInfoWeather(@NonNull final CurrentWeather currentWeather) {
        final CurrentWeatherDB currentWeatherDB = new CurrentWeatherDB();
        currentWeatherDB.setCityID(currentWeather.getCityID());
        currentWeatherDB.setCloudcover(currentWeather.getCloudcover());
        currentWeatherDB.setDate(currentWeather.getDate());
        currentWeatherDB.setFeelsLikeC(currentWeather.getFeelsLikeC());
        currentWeatherDB.setFeelsLikeF(currentWeather.getFeelsLikeF());
        currentWeatherDB.setHumidity(currentWeather.getHumidity());
        currentWeatherDB.setObservationTime(currentWeather.getObservationTime());
        currentWeatherDB.setPrecipMM(currentWeather.getPrecipMM());
        currentWeatherDB.setPressure(currentWeather.getPressure());
        currentWeatherDB.setTempC(currentWeather.getTempC());
        currentWeatherDB.setTempF(currentWeather.getTempF());
        currentWeatherDB.setVisibility(currentWeather.getVisibility());
        currentWeatherDB.setWeatherCode(currentWeather.getWeatherCode());
        currentWeatherDB.setWeatherDesc(currentWeather.getWeatherDesc());
        currentWeatherDB.setWeatherDescLocalLanguage(currentWeather.getWeatherDescLocalLanguage());
        currentWeatherDB.setWeatherIconUrl(currentWeather.getWeatherIconUrl());
        currentWeatherDB.setWinddir16Point(currentWeather.getWinddir16Point());
        currentWeatherDB.setWinddirDegree(currentWeather.getWinddirDegree());
        currentWeatherDB.setWindspeedKmph(currentWeather.getWindspeedKmph());
        currentWeatherDB.setWindspeedMiles(currentWeather.getWindspeedMiles());
        return currentWeatherDB;
    }
}

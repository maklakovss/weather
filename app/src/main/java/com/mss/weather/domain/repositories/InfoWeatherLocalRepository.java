package com.mss.weather.domain.repositories;

import com.mss.weather.domain.models.InfoWeather;

public interface InfoWeatherLocalRepository {

    InfoWeather getInfoWeatherById(String cityId);

    void deleteInfoWeather(InfoWeather infoWeather);

    void updateOrInsertInfoWeather(InfoWeather infoWeather);
}

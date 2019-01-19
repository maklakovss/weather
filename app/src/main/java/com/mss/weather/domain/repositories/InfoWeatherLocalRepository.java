package com.mss.weather.domain.repositories;

import com.mss.weather.domain.models.InfoWeather;

public interface InfoWeatherLocalRepository {

    InfoWeather getInfoWeatherById(String cityId);

    void deleteInfoWeather(String cityId);

    void updateOrInsertInfoWeather(InfoWeather infoWeather);
}

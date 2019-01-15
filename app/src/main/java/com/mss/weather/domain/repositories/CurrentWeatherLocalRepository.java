package com.mss.weather.domain.repositories;

import com.mss.weather.domain.models.CurrentWeather;

public interface CurrentWeatherLocalRepository {

    CurrentWeather getInfoWeatherById(String cityId);

    void deleteInfoWeather(CurrentWeather currentWeather);

    void updateOrInsertInfoWeather(CurrentWeather currentWeather);

}

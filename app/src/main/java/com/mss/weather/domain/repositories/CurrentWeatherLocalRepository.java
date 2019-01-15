package com.mss.weather.domain.repositories;

import com.mss.weather.domain.models.CurrentWeather;

public interface CurrentWeatherLocalRepository {

    CurrentWeather getCurrentWeatherById(String cityId);

    void deleteCurrentWeather(CurrentWeather currentWeather);

    void updateOrInsertCurrentWeather(CurrentWeather currentWeather);

}

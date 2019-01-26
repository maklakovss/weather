package com.mss.weather.domain.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.domain.models.CurrentWeather;

public interface CurrentWeatherLocalRepository {

    @Nullable
    CurrentWeather getCurrentWeatherById(@NonNull final String cityId);

    void deleteCurrentWeather(@NonNull final String cityId);

    void updateOrInsertCurrentWeather(@NonNull final CurrentWeather currentWeather);

}

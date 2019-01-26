package com.mss.weather.domain.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.domain.models.InfoWeather;

public interface InfoWeatherLocalRepository {

    @Nullable
    InfoWeather getInfoWeatherById(@NonNull final String cityId);

    void deleteInfoWeather(@NonNull final String cityId);

    void updateOrInsertInfoWeather(@NonNull final InfoWeather infoWeather);
}

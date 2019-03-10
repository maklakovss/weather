package com.mss.weather.domain.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.domain.models.PastHourWeather;

import java.util.List;

public interface PastHourWeatherLocalRepository {

    @Nullable
    List<PastHourWeather> getPastHourWeathers(@NonNull final String cityId,
                                              int yearFrom,
                                              int yearTo,
                                              int monthFrom,
                                              int monthTo,
                                              int dayFrom,
                                              int dayTo,
                                              int hourFrom,
                                              int hourTo);

    void deletePastHourWeatherByCityID(@NonNull final String cityId);

    void updateOrInsertPastHourWeather(@NonNull final List<PastHourWeather> pastHourWeathers);
}

package com.mss.weather.domain.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.domain.models.DayWeather;

import java.util.Date;
import java.util.List;

public interface DayWeatherLocalRepository {

    @Nullable
    List<DayWeather> getDayWeathersByCityId(@NonNull final String cityId, @NonNull final Date date);

    void deleteDayWeatherByCityID(@NonNull final String cityId);

    void deleteOldDayWeatherByCityID(@NonNull final String cityId, @NonNull final Date date);

    void updateOrInsertDayWeather(@NonNull final List<DayWeather> dayWeathers);

}

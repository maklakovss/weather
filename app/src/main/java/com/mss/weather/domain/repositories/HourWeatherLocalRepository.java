package com.mss.weather.domain.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.domain.models.HourWeather;

import java.util.Date;
import java.util.List;

public interface HourWeatherLocalRepository {

    @Nullable
    List<HourWeather> getHourWeathersByCityId(@NonNull final String cityId, @NonNull final Date date, boolean isPast);

    void deleteHourWeatherByCityID(@NonNull final String cityId);

    void deleteOldHourWeatherByCityID(@NonNull final String cityId, @NonNull final Date date);

    void updateOrInsertHourWeather(@NonNull final List<HourWeather> hourWeathers, boolean isPast);
}

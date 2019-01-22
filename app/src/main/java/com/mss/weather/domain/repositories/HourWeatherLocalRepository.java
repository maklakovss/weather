package com.mss.weather.domain.repositories;

import com.mss.weather.domain.models.HourWeather;

import java.util.Date;
import java.util.List;

public interface HourWeatherLocalRepository {

    List<HourWeather> getHourWeathersByCityId(String cityId, Date date);

    void deleteHourWeatherByCityID(String cityId);

    void deleteOldHourWeatherByCityID(String cityId, Date date);

    void updateOrInsertHourWeather(List<HourWeather> hourWeathers);

}

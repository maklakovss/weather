package com.mss.weather.domain.repositories;

import com.mss.weather.domain.models.DayWeather;

import java.util.Date;
import java.util.List;

public interface DayWeatherLocalRepository {

    List<DayWeather> getDayWeathersByCityId(String cityId, Date date);

    void deleteDayWeatherByCityID(String cityId);

    void deleteOldDayWeatherByCityID(String cityId, Date date);

    void updateOrInsertDayWeather(List<DayWeather> dayWeathers);

}

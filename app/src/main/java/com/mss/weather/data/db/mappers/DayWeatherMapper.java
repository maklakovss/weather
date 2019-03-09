package com.mss.weather.data.db.mappers;

import android.support.annotation.NonNull;

import com.mss.weather.data.db.models.DayWeatherDB;
import com.mss.weather.domain.models.DayWeather;

import java.util.ArrayList;
import java.util.List;

public class DayWeatherMapper {

    @NonNull
    public static List<DayWeatherDB> mapDayWeathersToDayWeatherDBs(@NonNull final List<DayWeather> dayWeathers, boolean isPast) {
        final List<DayWeatherDB> dayWeatherDBs = new ArrayList<>();
        for (DayWeather dayWeather : dayWeathers) {
            dayWeatherDBs.add(DayWeatherMapper.mapDayWeatherToDayWeatherDB(dayWeather, isPast));
        }
        return dayWeatherDBs;
    }

    @NonNull
    public static DayWeather mapDayWeatherDBToDayWeather(@NonNull final DayWeatherDB dayWeatherDB) {
        final DayWeather dayWeather = new DayWeather();
        dayWeather.setCityID(dayWeatherDB.getCityID());
        dayWeather.setDate(dayWeatherDB.getDate());
        dayWeather.setMaxTempC(dayWeatherDB.getMaxTempC());
        dayWeather.setMaxTempF(dayWeatherDB.getMaxTempF());
        dayWeather.setMinTempC(dayWeatherDB.getMinTempC());
        dayWeather.setMinTempF(dayWeatherDB.getMinTempF());
        dayWeather.setMaxWeatherCode(dayWeatherDB.getMaxWeatherCode());
        dayWeather.setMaxWeatherIconUrl(dayWeatherDB.getMaxWeatherIconUrl());
        dayWeather.setMaxWindspeedKmph(dayWeatherDB.getMaxWindspeedKmph());
        dayWeather.setMaxWindspeedMiles(dayWeatherDB.getMaxWindspeedMiles());
        dayWeather.setMinWindspeedKmph(dayWeatherDB.getMinWindspeedKmph());
        dayWeather.setMinWindspeedMiles(dayWeatherDB.getMinWindspeedMiles());
        dayWeather.setMoonIllumination(dayWeatherDB.getMoonIllumination());
        dayWeather.setMoonPhase(dayWeatherDB.getMoonPhase());
        dayWeather.setMoonrise(dayWeatherDB.getMoonrise());
        dayWeather.setMoonset(dayWeatherDB.getMoonset());
        dayWeather.setSunHour(dayWeatherDB.getSunHour());
        dayWeather.setSunrise(dayWeatherDB.getSunrise());
        dayWeather.setSunset(dayWeatherDB.getSunset());
        dayWeather.setTotalSnowCm(dayWeatherDB.getTotalSnowCm());
        dayWeather.setUvIndex(dayWeatherDB.getUvIndex());
        return dayWeather;
    }

    @NonNull
    public static List<DayWeather> mapDayWeatherDBsToDayWeathers(@NonNull final List<DayWeatherDB> dayWeatherDBs) {
        final List<DayWeather> dayWeathers = new ArrayList<>();
        for (DayWeatherDB dayWeatherDB : dayWeatherDBs) {
            dayWeathers.add(DayWeatherMapper.mapDayWeatherDBToDayWeather(dayWeatherDB));
        }
        return dayWeathers;
    }

    @NonNull
    public static DayWeatherDB mapDayWeatherToDayWeatherDB(@NonNull final DayWeather dayWeather, boolean isPast) {
        final DayWeatherDB dayWeatherDB = new DayWeatherDB();
        dayWeatherDB.setId(dayWeather.getCityID() + " " + dayWeather.getDate().toString());
        dayWeatherDB.setPast(isPast);
        dayWeatherDB.setCityID(dayWeather.getCityID());
        dayWeatherDB.setDate(dayWeather.getDate());
        dayWeatherDB.setMaxTempC(dayWeather.getMaxTempC());
        dayWeatherDB.setMaxTempF(dayWeather.getMaxTempF());
        dayWeatherDB.setMinTempC(dayWeather.getMinTempC());
        dayWeatherDB.setMinTempF(dayWeather.getMinTempF());
        dayWeatherDB.setMaxWeatherCode(dayWeather.getMaxWeatherCode());
        dayWeatherDB.setMaxWeatherIconUrl(dayWeather.getMaxWeatherIconUrl());
        dayWeatherDB.setMaxWindspeedKmph(dayWeather.getMaxWindspeedKmph());
        dayWeatherDB.setMaxWindspeedMiles(dayWeather.getMaxWindspeedMiles());
        dayWeatherDB.setMinWindspeedKmph(dayWeather.getMinWindspeedKmph());
        dayWeatherDB.setMinWindspeedMiles(dayWeather.getMinWindspeedMiles());
        dayWeatherDB.setMoonIllumination(dayWeather.getMoonIllumination());
        dayWeatherDB.setMoonPhase(dayWeather.getMoonPhase());
        dayWeatherDB.setMoonrise(dayWeather.getMoonrise());
        dayWeatherDB.setMoonset(dayWeather.getMoonset());
        dayWeatherDB.setSunHour(dayWeather.getSunHour());
        dayWeatherDB.setSunrise(dayWeather.getSunrise());
        dayWeatherDB.setSunset(dayWeather.getSunset());
        dayWeatherDB.setTotalSnowCm(dayWeather.getTotalSnowCm());
        dayWeatherDB.setUvIndex(dayWeather.getUvIndex());
        return dayWeatherDB;
    }
}

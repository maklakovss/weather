package com.mss.weather.data.db.mappers;

import android.support.annotation.NonNull;

import com.mss.weather.data.db.models.DayWeatherDB;
import com.mss.weather.domain.models.DayWeather;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class DayWeatherMapper {

    @NonNull
    public static List<DayWeatherDB> mapDayWeathersToMapWeatherDBs(List<DayWeather> dayWeathers) {
        List<DayWeatherDB> dayWeatherDBs = new ArrayList<>();
        for (DayWeather dayWeather : dayWeathers) {
            dayWeatherDBs.add(DayWeatherMapper.mapDayWeatherToDayWeatherDB(dayWeather));
        }
        return dayWeatherDBs;
    }

    public static DayWeather mapDayWeatherDBToDayWeather(DayWeatherDB dayWeatherDB) {
        DayWeather dayWeather = new DayWeather();
        dayWeather.setCityID(dayWeatherDB.getCityID());
        dayWeather.setDate(dayWeatherDB.getDate());
        dayWeather.setMaxTempC(dayWeatherDB.getMaxTempC());
        dayWeather.setMaxTempF(dayWeatherDB.getMaxTempF());
        dayWeather.setMinTempC(dayWeatherDB.getMinTempC());
        dayWeather.setMinTempF(dayWeatherDB.getMinTempF());
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
    public static List<DayWeather> mapDayWeatherDBsToMapWeathers(RealmResults<DayWeatherDB> dayWeatherDBs) {
        List<DayWeather> dayWeathers = new ArrayList<>();
        for (DayWeatherDB dayWeatherDB : dayWeatherDBs) {
            dayWeathers.add(DayWeatherMapper.mapDayWeatherDBToDayWeather(dayWeatherDB));
        }
        return dayWeathers;
    }


    public static DayWeatherDB mapDayWeatherToDayWeatherDB(DayWeather dayWeather) {
        DayWeatherDB dayWeatherDB = new DayWeatherDB();
        dayWeatherDB.setId(dayWeather.getCityID() + " " + dayWeather.getDate().toString());
        dayWeatherDB.setCityID(dayWeather.getCityID());
        dayWeatherDB.setDate(dayWeather.getDate());
        dayWeatherDB.setMaxTempC(dayWeather.getMaxTempC());
        dayWeatherDB.setMaxTempF(dayWeather.getMaxTempF());
        dayWeatherDB.setMinTempC(dayWeather.getMinTempC());
        dayWeatherDB.setMinTempF(dayWeather.getMinTempF());
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

package com.mss.weather.data.db.mappers;

import android.support.annotation.NonNull;

import com.mss.weather.data.db.models.HourWeatherDB;
import com.mss.weather.domain.models.HourWeather;

import java.util.ArrayList;
import java.util.List;

public class HourWeatherMapper {

    @NonNull
    public static List<HourWeather> mapHourWeatherDBsToHourWeathers(@NonNull final List<HourWeatherDB> hourWeatherDBS) {
        final List<HourWeather> hourWeathers = new ArrayList<>();
        for (HourWeatherDB hourWeatherDB : hourWeatherDBS) {
            hourWeathers.add(mapHourWeatherDBToHourWeather(hourWeatherDB));
        }
        return hourWeathers;
    }

    @NonNull
    private static HourWeather mapHourWeatherDBToHourWeather(@NonNull final HourWeatherDB hourWeatherDB) {
        final HourWeather hourWeather = new HourWeather();
        hourWeather.setCityID(hourWeatherDB.getCityID());
        hourWeather.setDate(hourWeatherDB.getDate());
        hourWeather.setCloudcover(hourWeatherDB.getCloudcover());
        hourWeather.setDewPointC(hourWeatherDB.getDewPointC());
        hourWeather.setDewPointF(hourWeatherDB.getDewPointF());
        hourWeather.setFeelsLikeC(hourWeatherDB.getFeelsLikeC());
        hourWeather.setFeelsLikeF(hourWeatherDB.getFeelsLikeF());
        hourWeather.setHeatIndexC(hourWeatherDB.getHeatIndexC());
        hourWeather.setHeatIndexF(hourWeatherDB.getHeatIndexF());
        hourWeather.setHumidity(hourWeatherDB.getHumidity());
        hourWeather.setLangRu(hourWeatherDB.getLangRu());
        hourWeather.setPrecipMM(hourWeatherDB.getPrecipMM());
        hourWeather.setPressure(hourWeatherDB.getPressure());
        hourWeather.setTempC(hourWeatherDB.getTempC());
        hourWeather.setTempF(hourWeatherDB.getTempF());
        hourWeather.setVisibility(hourWeatherDB.getVisibility());
        hourWeather.setWeatherCode(hourWeatherDB.getWeatherCode());
        hourWeather.setWeatherDesc(hourWeatherDB.getWeatherDesc());
        hourWeather.setWeatherIconUrl(hourWeatherDB.getWeatherIconUrl());
        hourWeather.setWindChillC(hourWeatherDB.getWindChillC());
        hourWeather.setWindChillF(hourWeatherDB.getWindChillF());
        hourWeather.setWinddir16Point(hourWeatherDB.getWinddir16Point());
        hourWeather.setWinddirDegree(hourWeatherDB.getWinddirDegree());
        hourWeather.setWindGustKmph(hourWeatherDB.getWindGustKmph());
        hourWeather.setWindGustMiles(hourWeatherDB.getWindGustMiles());
        hourWeather.setWindspeedKmph(hourWeatherDB.getWindspeedKmph());
        hourWeather.setWindspeedMiles(hourWeatherDB.getWindspeedMiles());
        hourWeather.setChanceoffog(hourWeatherDB.getChanceoffog());
        hourWeather.setChanceoffrost(hourWeatherDB.getChanceoffrost());
        hourWeather.setChanceofhightemp(hourWeatherDB.getChanceofhightemp());
        hourWeather.setChanceofovercast(hourWeatherDB.getChanceofovercast());
        hourWeather.setChanceofrain(hourWeatherDB.getChanceofrain());
        hourWeather.setChanceofremdry(hourWeatherDB.getChanceofremdry());
        hourWeather.setChanceofsnow(hourWeatherDB.getChanceofsnow());
        hourWeather.setChanceofsunshine(hourWeatherDB.getChanceofsunshine());
        hourWeather.setChanceofthunder(hourWeatherDB.getChanceofthunder());
        hourWeather.setChanceofwindy(hourWeatherDB.getChanceofwindy());
        return hourWeather;
    }

    @NonNull
    public static List<HourWeatherDB> mapHourWeathersToHourWeatherDBs(@NonNull final List<HourWeather> hourWeathers, boolean isPast) {
        final List<HourWeatherDB> hourWeatherDBs = new ArrayList<>();
        for (HourWeather hourWeather : hourWeathers) {
            hourWeatherDBs.add(mapHourWeatherToHourWeatherDB(hourWeather, isPast));
        }
        return hourWeatherDBs;
    }

    @NonNull
    private static HourWeatherDB mapHourWeatherToHourWeatherDB(@NonNull final HourWeather hourWeather, boolean isPast) {
        final HourWeatherDB hourWeatherDB = new HourWeatherDB();
        hourWeatherDB.setId(hourWeather.getCityID() + " " + hourWeather.getDate().toString());
        hourWeatherDB.setPast(isPast);
        hourWeatherDB.setCityID(hourWeather.getCityID());
        hourWeatherDB.setDate(hourWeather.getDate());
        hourWeatherDB.setCloudcover(hourWeather.getCloudcover());
        hourWeatherDB.setDewPointC(hourWeather.getDewPointC());
        hourWeatherDB.setDewPointF(hourWeather.getDewPointF());
        hourWeatherDB.setFeelsLikeC(hourWeather.getFeelsLikeC());
        hourWeatherDB.setFeelsLikeF(hourWeather.getFeelsLikeF());
        hourWeatherDB.setHeatIndexC(hourWeather.getHeatIndexC());
        hourWeatherDB.setHeatIndexF(hourWeather.getHeatIndexF());
        hourWeatherDB.setHumidity(hourWeather.getHumidity());
        hourWeatherDB.setLangRu(hourWeather.getLangRu());
        hourWeatherDB.setPrecipMM(hourWeather.getPrecipMM());
        hourWeatherDB.setPressure(hourWeather.getPressure());
        hourWeatherDB.setTempC(hourWeather.getTempC());
        hourWeatherDB.setTempF(hourWeather.getTempF());
        hourWeatherDB.setVisibility(hourWeather.getVisibility());
        hourWeatherDB.setWeatherCode(hourWeather.getWeatherCode());
        hourWeatherDB.setWeatherDesc(hourWeather.getWeatherDesc());
        hourWeatherDB.setWeatherIconUrl(hourWeather.getWeatherIconUrl());
        hourWeatherDB.setWindChillC(hourWeather.getWindChillC());
        hourWeatherDB.setWindChillF(hourWeather.getWindChillF());
        hourWeatherDB.setWinddir16Point(hourWeather.getWinddir16Point());
        hourWeatherDB.setWinddirDegree(hourWeather.getWinddirDegree());
        hourWeatherDB.setWindGustKmph(hourWeather.getWindGustKmph());
        hourWeatherDB.setWindGustMiles(hourWeather.getWindGustMiles());
        hourWeatherDB.setWindspeedKmph(hourWeather.getWindspeedKmph());
        hourWeatherDB.setWindspeedMiles(hourWeather.getWindspeedMiles());
        hourWeatherDB.setChanceoffog(hourWeather.getChanceoffog());
        hourWeatherDB.setChanceoffrost(hourWeather.getChanceoffrost());
        hourWeatherDB.setChanceofhightemp(hourWeather.getChanceofhightemp());
        hourWeatherDB.setChanceofovercast(hourWeather.getChanceofovercast());
        hourWeatherDB.setChanceofrain(hourWeather.getChanceofrain());
        hourWeatherDB.setChanceofremdry(hourWeather.getChanceofremdry());
        hourWeatherDB.setChanceofsnow(hourWeather.getChanceofsnow());
        hourWeatherDB.setChanceofsunshine(hourWeather.getChanceofsunshine());
        hourWeatherDB.setChanceofthunder(hourWeather.getChanceofthunder());
        hourWeatherDB.setChanceofwindy(hourWeather.getChanceofwindy());
        return hourWeatherDB;
    }
}

package com.mss.weather.data.network.mappers;

import com.mss.weather.data.network.models.CurrentCondition;
import com.mss.weather.data.network.models.Weather;
import com.mss.weather.data.network.models.WeatherResponse;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.CurrentWeather;
import com.mss.weather.domain.models.DayWeather;
import com.mss.weather.domain.models.InfoWeather;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherResponseToWeatherInfo {

    public static InfoWeather mapWeatherResponseToWeatherInfo(WeatherResponse weatherResponse, City city) {
        InfoWeather infoWeather = new InfoWeather();
        infoWeather.setCityID(city.getId());
        infoWeather.setDateState(new Date());
        if (weatherResponse.getData() != null
                && weatherResponse.getData().getWeather() != null
                && weatherResponse.getData().getWeather().size() > 0) {
            infoWeather.setDays(mapWeathersToWeatherDays(weatherResponse.getData().getWeather(), city));
        }
        if (weatherResponse.getData() != null
                && weatherResponse.getData().getCurrentCondition() != null
                && weatherResponse.getData().getCurrentCondition().size() > 0) {
            infoWeather.setCurrentWeather(mapCurrentConditionalToWeatherCurrent(weatherResponse.getData().getCurrentCondition().get(0), city));
        }
        return infoWeather;
    }

    private static List<DayWeather> mapWeathersToWeatherDays(List<Weather> weathers, City city) {
        List<DayWeather> dayWeathers = new ArrayList<>();
        return dayWeathers;
    }

    private static CurrentWeather mapCurrentConditionalToWeatherCurrent(CurrentCondition currentCondition, City city) {
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setCityID(city.getId());
        currentWeather.setDate(new Date());
        currentWeather.setObservationTime(TimeStringToDate.parseTimeString(currentCondition.getObservationTime()));
        currentWeather.setTempC(currentCondition.getTempC());
        currentWeather.setTempF(currentCondition.getTempF());
        currentWeather.setWeatherCode(currentCondition.getWeatherCode());
        if (currentCondition.getWeatherIconUrl() != null
                && currentCondition.getWeatherIconUrl().size() > 0)
            currentWeather.setWeatherIconUrl(currentCondition.getWeatherIconUrl().get(0).getValue());
        if (currentCondition.getWeatherDesc() != null
                && currentCondition.getWeatherDesc().size() > 0)
            currentWeather.setWeatherDesc(currentCondition.getWeatherDesc().get(0).getValue());
        if (currentCondition.getLangRu() != null
                && currentCondition.getLangRu().size() > 0)
            currentWeather.setWeatherDescLocalLanguage(currentCondition.getLangRu().get(0).getValue());
        currentWeather.setWindspeedMiles(currentCondition.getWindspeedMiles());
        currentWeather.setWindspeedKmph(currentCondition.getWindspeedKmph());
        currentWeather.setWinddirDegree(currentCondition.getWinddirDegree());
        currentWeather.setWinddir16Point(currentCondition.getWinddir16Point());
        currentWeather.setPrecipMM(currentCondition.getPrecipMM());
        currentWeather.setHumidity(currentCondition.getHumidity());
        currentWeather.setVisibility(currentCondition.getVisibility());
        currentWeather.setPressure(currentCondition.getPressure());
        currentWeather.setCloudcover(currentCondition.getCloudcover());
        currentWeather.setFeelsLikeC(currentCondition.getFeelsLikeC());
        currentWeather.setFeelsLikeF(currentCondition.getFeelsLikeF());

        return currentWeather;
    }
}
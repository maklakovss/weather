package com.mss.weather.data.network.mappers;

import com.mss.weather.data.network.models.Astronomy;
import com.mss.weather.data.network.models.CurrentCondition;
import com.mss.weather.data.network.models.Hourly;
import com.mss.weather.data.network.models.Weather;
import com.mss.weather.data.network.models.WeatherResponse;
import com.mss.weather.domain.models.ChanceWeather;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.CurrentWeather;
import com.mss.weather.domain.models.DayWeather;
import com.mss.weather.domain.models.HourWeather;
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
        for (Weather weather : weathers) {
            dayWeathers.add(weatherToDayWeather(weather, city));
        }
        return dayWeathers;
    }

    private static DayWeather weatherToDayWeather(Weather weather, City city) {
        DayWeather dayWeather = new DayWeather();
        dayWeather.setCityID(city.getId());
        dayWeather.setDate(DateStringToDate.parseDateString(weather.getDate()));
        dayWeather.setMaxTempC(weather.getMaxTempC());
        dayWeather.setMaxTempF(weather.getMaxTempF());
        dayWeather.setMinTempC(weather.getMinTempC());
        dayWeather.setMinTempF(weather.getMinTempF());
        if (weather.getAstronomy() != null
                && weather.getAstronomy().size() > 0) {
            Astronomy astronomy = weather.getAstronomy().get(0);
            dayWeather.setMoonIllumination(astronomy.getMoonIllumination());
            dayWeather.setMoonPhase(astronomy.getMoonPhase());
            dayWeather.setMoonrise(TimeStringToDate.parseTimeString(astronomy.getMoonrise()));
            dayWeather.setMoonset(TimeStringToDate.parseTimeString(astronomy.getMoonset()));
            dayWeather.setSunrise(TimeStringToDate.parseTimeString(astronomy.getSunrise()));
            dayWeather.setSunset(TimeStringToDate.parseTimeString(astronomy.getSunset()));
        }
        dayWeather.setSunHour(weather.getSunHour());
        dayWeather.setTotalSnowCm(weather.getTotalSnowCm());
        dayWeather.setUvIndex(weather.getUvIndex());
        if (weather.getHourly() != null
                && weather.getHourly().size() > 0)
            dayWeather.setHourly(mapHourlyListToHourWeatherList(weather.getHourly(), city, dayWeather.getDate()));
        return dayWeather;
    }

    private static List<HourWeather> mapHourlyListToHourWeatherList(List<Hourly> hourly, City city, Date date) {
        List<HourWeather> hourWeathers = new ArrayList<>();
        for (Hourly hour : hourly) {
            hourWeathers.add(mapHourlyToHourWeather(hour, city, date));
        }
        return hourWeathers;
    }

    private static HourWeather mapHourlyToHourWeather(Hourly hour, City city, Date date) {
        HourWeather hourWeather = new HourWeather();

        hourWeather.setCityID(city.getId());
        Date hourDate = (Date) date.clone();
        hourDate.setHours(hour.getTime());
        hourWeather.setDate(hourDate);

        hourWeather.setCloudcover(hour.getCloudcover());
        hourWeather.setDewPointC(hour.getDewPointC());
        hourWeather.setDewPointF(hour.getDewPointF());
        hourWeather.setFeelsLikeC(hour.getFeelsLikeC());
        hourWeather.setFeelsLikeF(hour.getFeelsLikeF());
        hourWeather.setHeatIndexC(hour.getHeatIndexC());
        hourWeather.setHeatIndexF(hour.getHeatIndexF());
        hourWeather.setHumidity(hour.getHumidity());
        hourWeather.setPrecipMM(hour.getPrecipMM());
        hourWeather.setPressure(hour.getPressure());
        hourWeather.setTempC(hour.getTempC());
        hourWeather.setTempF(hour.getTempF());
        hourWeather.setVisibility(hour.getVisibility());
        hourWeather.setWeatherCode(hour.getWeatherCode());
        hourWeather.setWindChillC(hour.getWindChillC());
        hourWeather.setWindChillF(hour.getWindChillF());
        hourWeather.setWinddir16Point(hour.getWinddir16Point());
        hourWeather.setWinddirDegree(hour.getWinddirDegree());
        hourWeather.setWindGustKmph(hour.getWindGustKmph());
        hourWeather.setWindGustMiles(hour.getWindGustMiles());
        hourWeather.setWindspeedKmph(hour.getWindspeedKmph());
        hourWeather.setWindspeedMiles(hour.getWindspeedMiles());

        if (hour.getWeatherIconUrl() != null
                && hour.getWeatherIconUrl().size() > 0) {
            hourWeather.setWeatherIconUrl(hour.getWeatherIconUrl().get(0).getValue());
        }

        if (hour.getWeatherDesc() != null
                && hour.getWeatherDesc().size() > 0) {
            hourWeather.setWeatherDesc(hour.getWeatherDesc().get(0).getValue());
        }

        if (hour.getLangRu() != null
                && hour.getLangRu().size() > 0) {
            hourWeather.setLangRu(hour.getLangRu().get(0).getValue());
        }

        ChanceWeather chanceWeather = new ChanceWeather();
        chanceWeather.setCityID(city.getId());
        chanceWeather.setDate(hourDate);
        chanceWeather.setChanceoffog(hour.getChanceoffog());
        chanceWeather.setChanceoffrost(hour.getChanceoffrost());
        chanceWeather.setChanceofhightemp(hour.getChanceofhightemp());
        chanceWeather.setChanceofovercast(hour.getChanceofovercast());
        chanceWeather.setChanceofrain(hour.getChanceofrain());
        chanceWeather.setChanceofremdry(hour.getChanceofremdry());
        chanceWeather.setChanceofsnow(hour.getChanceofsnow());
        chanceWeather.setChanceofsunshine(hour.getChanceofsunshine());
        chanceWeather.setChanceofthunder(hour.getChanceofthunder());
        chanceWeather.setChanceofwindy(hour.getChanceofwindy());
        hourWeather.setChanceWeather(chanceWeather);

        return hourWeather;
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
        currentWeather.setHumidity((int) currentCondition.getHumidity());
        currentWeather.setVisibility(currentCondition.getVisibility());
        currentWeather.setPressure(currentCondition.getPressure());
        currentWeather.setCloudcover(currentCondition.getCloudcover());
        currentWeather.setFeelsLikeC(currentCondition.getFeelsLikeC());
        currentWeather.setFeelsLikeF(currentCondition.getFeelsLikeF());

        return currentWeather;
    }
}
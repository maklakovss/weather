package com.mss.weather.data.network.mappers;

import android.support.annotation.NonNull;

import com.mss.weather.data.network.models.Astronomy;
import com.mss.weather.data.network.models.CurrentCondition;
import com.mss.weather.data.network.models.Hourly;
import com.mss.weather.data.network.models.Weather;
import com.mss.weather.data.network.models.WeatherResponse;
import com.mss.weather.data.network.utils.DateStringToDate;
import com.mss.weather.data.network.utils.TimeStringToDate;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.CurrentWeather;
import com.mss.weather.domain.models.DayWeather;
import com.mss.weather.domain.models.HourWeather;
import com.mss.weather.domain.models.InfoWeather;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeatherResponseMapper {

    @NonNull
    public static InfoWeather mapWeatherResponseToWeatherInfo(@NonNull final WeatherResponse weatherResponse, @NonNull final City city) {
        final InfoWeather infoWeather = new InfoWeather();
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

    @NonNull
    private static List<DayWeather> mapWeathersToWeatherDays(@NonNull final List<Weather> weathers, @NonNull final City city) {
        final List<DayWeather> dayWeathers = new ArrayList<>();
        for (final Weather weather : weathers) {
            if (weather != null)
                dayWeathers.add(weatherToDayWeather(weather, city));
        }
        return dayWeathers;
    }

    @NonNull
    private static DayWeather weatherToDayWeather(@NonNull final Weather weather, @NonNull final City city) {
        final DayWeather dayWeather = new DayWeather();
        dayWeather.setCityID(city.getId());
        dayWeather.setDate(DateStringToDate.parseDateString(weather.getDate()));
        dayWeather.setMaxTempC(weather.getMaxTempC());
        dayWeather.setMaxTempF(weather.getMaxTempF());
        dayWeather.setMinTempC(weather.getMinTempC());
        dayWeather.setMinTempF(weather.getMinTempF());
        if (weather.getAstronomy() != null
                && weather.getAstronomy().size() > 0
                && weather.getAstronomy().get(0) != null) {
            final Astronomy astronomy = weather.getAstronomy().get(0);
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
                && weather.getHourly().size() > 0
                && weather.getHourly().get(0) != null) {
            dayWeather.setHourly(mapHourlyListToHourWeatherList(weather.getHourly(), city, dayWeather.getDate()));
            updateMaxWeather(dayWeather);
        }
        return dayWeather;
    }

    private static void updateMaxWeather(@NonNull final DayWeather dayWeather) {
        dayWeather.setMaxWeatherCode(0);
        dayWeather.setMaxWindspeedMiles(dayWeather.getHourly().get(0).getWindspeedMiles());
        dayWeather.setMaxWindspeedKmph(dayWeather.getHourly().get(0).getWindspeedKmph());
        dayWeather.setMinWindspeedMiles(dayWeather.getHourly().get(0).getWindspeedMiles());
        dayWeather.setMinWindspeedKmph(dayWeather.getHourly().get(0).getWindspeedKmph());

        for (final HourWeather hourWeather : dayWeather.getHourly()) {
            if (hourWeather != null) {
                if (dayWeather.getMaxWeatherCode() < hourWeather.getWeatherCode()) {
                    dayWeather.setMaxWeatherCode(hourWeather.getWeatherCode());
                    dayWeather.setMaxWeatherIconUrl(hourWeather.getWeatherIconUrl());
                }
                if (dayWeather.getMaxWindspeedKmph() < hourWeather.getWindspeedKmph()) {
                    dayWeather.setMaxWindspeedKmph(hourWeather.getWindspeedKmph());
                    dayWeather.setMaxWindspeedMiles(hourWeather.getWindspeedMiles());
                }
                if (dayWeather.getMinWindspeedKmph() > hourWeather.getWindspeedKmph()) {
                    dayWeather.setMinWindspeedKmph(hourWeather.getWindspeedKmph());
                    dayWeather.setMinWindspeedMiles(hourWeather.getWindspeedMiles());
                }
            }
        }
    }

    @NonNull
    private static List<HourWeather> mapHourlyListToHourWeatherList(@NonNull final List<Hourly> hourly, @NonNull final City city, @NonNull final Date date) {
        final List<HourWeather> hourWeathers = new ArrayList<>();
        for (final Hourly hour : hourly) {
            if (hour != null)
                hourWeathers.add(mapHourlyToHourWeather(hour, city, date));
        }
        return hourWeathers;
    }

    @NonNull
    private static HourWeather mapHourlyToHourWeather(@NonNull final Hourly hour, @NonNull final City city, @NonNull final Date date) {
        final HourWeather hourWeather = new HourWeather();

        hourWeather.setCityID(city.getId());

        final Calendar hourDate = Calendar.getInstance();
        hourDate.setTime(date);
        hourDate.set(Calendar.HOUR, hour.getTime() / 100);
        hourDate.set(Calendar.MINUTE, hour.getTime() % 100);
        hourWeather.setDate(hourDate.getTime());

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
        hourWeather.setChanceoffog(hour.getChanceoffog());
        hourWeather.setChanceoffrost(hour.getChanceoffrost());
        hourWeather.setChanceofhightemp(hour.getChanceofhightemp());
        hourWeather.setChanceofovercast(hour.getChanceofovercast());
        hourWeather.setChanceofrain(hour.getChanceofrain());
        hourWeather.setChanceofremdry(hour.getChanceofremdry());
        hourWeather.setChanceofsnow(hour.getChanceofsnow());
        hourWeather.setChanceofsunshine(hour.getChanceofsunshine());
        hourWeather.setChanceofthunder(hour.getChanceofthunder());
        hourWeather.setChanceofwindy(hour.getChanceofwindy());

        if (hour.getWeatherIconUrl() != null
                && hour.getWeatherIconUrl().size() > 0
                && hour.getWeatherIconUrl().get(0) != null)
            hourWeather.setWeatherIconUrl(hour.getWeatherIconUrl().get(0).getValue());

        if (hour.getWeatherDesc() != null
                && hour.getWeatherDesc().size() > 0
                && hour.getWeatherDesc().get(0) != null)
            hourWeather.setWeatherDesc(hour.getWeatherDesc().get(0).getValue());

        if (hour.getLangRu() != null
                && hour.getLangRu().size() > 0
                && hour.getLangRu().get(0) != null)
            hourWeather.setLangRu(hour.getLangRu().get(0).getValue());

        return hourWeather;
    }

    @NonNull
    private static CurrentWeather mapCurrentConditionalToWeatherCurrent(@NonNull final CurrentCondition currentCondition, @NonNull final City city) {
        final CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setCityID(city.getId());
        currentWeather.setDate(new Date());
        currentWeather.setObservationTime(TimeStringToDate.parseTimeString(currentCondition.getObservationTime()));
        currentWeather.setTempC(currentCondition.getTempC());
        currentWeather.setTempF(currentCondition.getTempF());
        currentWeather.setWeatherCode(currentCondition.getWeatherCode());
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

        if (currentCondition.getWeatherIconUrl() != null
                && currentCondition.getWeatherIconUrl().size() > 0
                && currentCondition.getWeatherIconUrl().get(0) != null)
            currentWeather.setWeatherIconUrl(currentCondition.getWeatherIconUrl().get(0).getValue());

        if (currentCondition.getWeatherDesc() != null
                && currentCondition.getWeatherDesc().size() > 0
                && currentCondition.getWeatherDesc().get(0) != null)
            currentWeather.setWeatherDesc(currentCondition.getWeatherDesc().get(0).getValue());

        if (currentCondition.getLangRu() != null
                && currentCondition.getLangRu().size() > 0
                && currentCondition.getLangRu().get(0) != null)
            currentWeather.setWeatherDescLocalLanguage(currentCondition.getLangRu().get(0).getValue());

        return currentWeather;
    }
}
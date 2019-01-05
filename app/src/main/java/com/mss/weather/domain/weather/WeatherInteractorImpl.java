package com.mss.weather.domain.weather;

import android.support.annotation.NonNull;

import com.mss.weather.domain.city.CityRepository;
import com.mss.weather.domain.city.models.City;
import com.mss.weather.presentation.view.models.WeatherData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;

public class WeatherInteractorImpl implements WeatherInteractor {

    @Inject
    CityRepository cityRepository;

    private List<City> cityList;

    private City currentCity;
    private OnCurrentCityChanged onCurrentCityChanged;
    private OnCityUpdated onCityUpdated;

    @Inject
    public WeatherInteractorImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getListCities() {
        if (cityList == null) {
            loadCities();
        }
        return cityList;
    }

    @Override
    public City getCurrentCity() {
        return currentCity;
    }

    @Override
    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
        if (onCurrentCityChanged != null) {
            onCurrentCityChanged.onChanged(currentCity);
        }
    }

    @Override
    public WeatherData getWeatherByCity(City citySettings) {
        WeatherData weatherData = getWeatherData(citySettings, new Date());
        return weatherData;
    }

    @NonNull
    private WeatherData getWeatherData(City citySettings, Date date) {
        WeatherData weatherData = new WeatherData();
        weatherData.setCityName(citySettings.getAreaName());
        weatherData.setWeatherDate(date);
        weatherData.getSunrise().setHours(6);
        weatherData.getSunrise().setMinutes(0);
        weatherData.getSunrise().setSeconds(0);
        weatherData.getSunset().setHours(23);
        weatherData.getSunset().setMinutes(0);
        weatherData.getSunset().setSeconds(0);
        weatherData.setCloudsPercent(30);
        weatherData.setCloudsDescription("переменная облачность");
        weatherData.setTemp(25);
        weatherData.setTempMin(23);
        weatherData.setTempMax(27);
        weatherData.setPressure(760);
        weatherData.setHumidity(30);
        weatherData.setWindSpeed(5);
        weatherData.setWindDeg(90);
        weatherData.setSnow(10);
        weatherData.setRain(0);
        return weatherData;
    }

    @Override
    public List<WeatherData> getWeatherList(City citySettings) {
        List<WeatherData> weatherDataList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 10; i++) {
            calendar.add(Calendar.DATE, 1);
            weatherDataList.add(getWeatherData(citySettings, calendar.getTime()));
        }
        return weatherDataList;
    }

    private void loadCities() {
        cityList = new ArrayList<>();
    }

    public void setOnCurrentCityChanged(OnCurrentCityChanged onCurrentCityChanged) {
        this.onCurrentCityChanged = onCurrentCityChanged;
    }

    @Override
    public void setOnOnCityUpdated(OnCityUpdated onCityUpdated) {
        this.onCityUpdated = onCityUpdated;
    }

    @Override
    public void addCity(City citySettings) {
        cityList.add(citySettings);
    }

    @Override
    public void saveSettings(City citySettings) {
        if (onCityUpdated != null) {
            onCityUpdated.onUpdated(citySettings);
        }
    }

    @Override
    public Maybe<List<City>> getAutoCompleteLocations(String searchTemplate) {
        return cityRepository.getCities(searchTemplate);
    }

}

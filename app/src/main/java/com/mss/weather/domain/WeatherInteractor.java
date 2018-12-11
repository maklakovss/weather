package com.mss.weather.domain;

import com.mss.weather.presentation.view.models.CitySettings;
import com.mss.weather.presentation.view.models.WeatherData;

import java.util.List;

public interface WeatherInteractor {

    List<CitySettings> getListCities();

    String getCurrentCityName();

    void setCurrentCityName(String currentCityName);

    WeatherData getWeatherByCity(String cityName);

    List<WeatherData> getWeatherList(String cityName);

    void setOnCurrentCityChanged(OnCurrentCityChanged onCurrentCityChanged);

    CitySettings getCitySettings(String cityName);

    public interface OnCurrentCityChanged {
        void onChanged(String currentCityName);
    }

}

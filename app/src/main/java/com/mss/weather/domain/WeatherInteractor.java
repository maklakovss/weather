package com.mss.weather.domain;

import com.mss.weather.presentation.view.models.CitySettings;
import com.mss.weather.presentation.view.models.WeatherData;

import java.util.List;

public interface WeatherInteractor {

    List<CitySettings> getListCities();

    CitySettings getCurrentCity();

    void setCurrentCity(CitySettings currentCity);

    WeatherData getWeatherByCity(CitySettings cityName);

    List<WeatherData> getWeatherList(CitySettings cityName);

    void setOnCurrentCityChanged(OnCurrentCityChanged onCurrentCityChanged);

    void addCity(CitySettings s);

    void saveSettings(CitySettings citySettings);

    public interface OnCurrentCityChanged {
        void onChanged(CitySettings currentCity);
    }

}

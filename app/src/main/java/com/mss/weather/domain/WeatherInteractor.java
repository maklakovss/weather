package com.mss.weather.domain;

import com.mss.weather.presentation.view.models.CitySettings;
import com.mss.weather.presentation.view.models.WeatherData;

import java.util.List;

public interface WeatherInteractor {

    List<CitySettings> getListCities();

    String getCurrentCityName();

    void setCurrentCityName(String currentCityName);

    WeatherData getWeatherByCity(String cityName);

    void setOnCurrentCityChanged(OnCurrentCityChanged onCurrentCityChanged);

    public interface OnCurrentCityChanged {
        void onChanged(String currentCityName);
    }

}

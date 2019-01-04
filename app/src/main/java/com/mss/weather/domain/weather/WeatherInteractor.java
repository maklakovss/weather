package com.mss.weather.domain.weather;

import com.mss.weather.domain.city.models.City;
import com.mss.weather.presentation.view.models.WeatherData;

import java.util.List;

public interface WeatherInteractor {

    List<City> getListCities();

    City getCurrentCity();

    void setCurrentCity(City currentCity);

    WeatherData getWeatherByCity(City cityName);

    List<WeatherData> getWeatherList(City cityName);

    void setOnCurrentCityChanged(OnCurrentCityChanged onCurrentCityChanged);

    void setOnOnCityUpdated(OnCityUpdated onCityUpdated);

    void addCity(City s);

    void saveSettings(City citySettings);

    public interface OnCurrentCityChanged {
        void onChanged(City currentCity);
    }

    public interface OnCityUpdated {
        void onUpdated(City currentCity);
    }

}

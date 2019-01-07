package com.mss.weather.domain.weather;

import com.mss.weather.domain.city.models.City;
import com.mss.weather.presentation.view.models.WeatherData;

import java.util.List;

import io.reactivex.Maybe;

public interface WeatherInteractor {

    List<City> getListCities();

    City getCurrentCity();

    void setCurrentCity(City currentCity);

    WeatherData getWeatherByCity(City cityName);

    List<WeatherData> getWeatherList(City cityName);

    void setOnCurrentCityChanged(OnCurrentCityChanged onCurrentCityChanged);

    void addCity(City s);

    Maybe<List<City>> getAutoCompleteLocations(String searchTemplate);

    void deleteCity(City city);

    Maybe<List<City>> getLocationsByCoordinate(double latitude, double longitude);

    public interface OnCurrentCityChanged {
        void onChanged(City currentCity);
    }
}
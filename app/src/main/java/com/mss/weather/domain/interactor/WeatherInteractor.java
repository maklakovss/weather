package com.mss.weather.domain.interactor;

import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.domain.models.Position;

import java.util.List;

import io.reactivex.Maybe;

public interface WeatherInteractor {

    List<City> getListCities();

    City getCurrentCity();

    void setCurrentCity(City currentCity);

    void addCity(City city);

    void deleteCity(City city);

    Maybe<List<City>> getAutoCompleteLocations(String searchTemplate);

    Maybe<List<City>> getLocationsByPosition(Position position);

    Maybe<Position> getPosition();

    Maybe<InfoWeather> getWeatherInfo(City city);

    InfoWeather getLocalWeatherInfo(City city);
}

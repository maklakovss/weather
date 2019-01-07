package com.mss.weather.domain.city;

import com.mss.weather.domain.city.models.City;

import java.util.List;

import io.reactivex.Maybe;

public interface WeatherRepository {

    Maybe<List<City>> getAutoCompleteCities(String startWith);

    List<City> getCities();

    void addCity(City city);

    void deleteCity(City city);

    Maybe<List<City>> getCitiesByCoordinate(double latitude, double longitude);
}

package com.mss.weather.domain.weather;

import com.mss.weather.domain.sensors.models.Position;
import com.mss.weather.domain.weather.models.City;

import java.util.List;

import io.reactivex.Maybe;

public interface WeatherRepository {

    Maybe<List<City>> getAutoCompleteCities(String startWith);

    Maybe<List<City>> getCitiesByCoordinate(Position position);

    List<City> getCities();

    void addCity(City city);

    void deleteCity(City city);

    City getCityById(String id);

    String getLastCityId();

    void setLastCityId(String lastCityID);
}

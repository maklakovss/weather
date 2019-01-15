package com.mss.weather.domain.repositories;

import com.mss.weather.domain.models.City;

import java.util.List;

public interface CityLocalRepository {

    List<City> getCities();

    void addCity(City city);

    void deleteCity(City city);

    City getCityById(String id);

    String getLastCityId();

    void setLastCityId(String lastCityID);
}

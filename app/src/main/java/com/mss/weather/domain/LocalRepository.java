package com.mss.weather.domain;

import com.mss.weather.domain.models.City;

import java.util.List;

public interface LocalRepository {

    List<City> getCities();

    void addCity(City city);

    void deleteCity(City city);

    City getCityById(String id);

    String getLastCityId();

    void setLastCityId(String lastCityID);
}

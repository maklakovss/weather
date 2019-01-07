package com.mss.weather.domain.weather;

import com.mss.weather.domain.weather.models.City;

import java.util.List;

public interface LocalRepository {

    List<City> getCities();

    void addCity(City city);

    void deleteCity(City city);

    City getCityById(String id);

    String getLastCityId();

    void setLastCityId(String lastCityID);
}

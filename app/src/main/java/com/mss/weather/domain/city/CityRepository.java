package com.mss.weather.domain.city;

import com.mss.weather.domain.city.models.City;

import io.reactivex.Observable;

public interface CityRepository {
    Observable<City> getCities(String startWith);
}

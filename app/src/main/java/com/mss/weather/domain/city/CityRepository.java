package com.mss.weather.domain.city;

import com.mss.weather.domain.city.models.City;

import java.util.List;

import io.reactivex.Maybe;

public interface CityRepository {
    Maybe<List<City>> getCities(String startWith);
}

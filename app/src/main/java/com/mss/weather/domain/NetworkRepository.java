package com.mss.weather.domain;

import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.Position;
import com.mss.weather.domain.models.WeatherInfo;

import java.util.List;

import io.reactivex.Maybe;

public interface NetworkRepository {

    Maybe<List<City>> getAutoCompleteCities(String startWith);

    Maybe<List<City>> getCitiesByCoordinate(Position position);

    Maybe<WeatherInfo> getWeatherInfo(City city);
}

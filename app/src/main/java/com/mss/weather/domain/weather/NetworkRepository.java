package com.mss.weather.domain.weather;

import com.mss.weather.domain.sensors.models.Position;
import com.mss.weather.domain.weather.models.City;

import java.util.List;

import io.reactivex.Maybe;

public interface NetworkRepository {

    Maybe<List<City>> getAutoCompleteCities(String startWith);

    Maybe<List<City>> getCitiesByCoordinate(Position position);
}

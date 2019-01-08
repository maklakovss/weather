package com.mss.weather.domain;

import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.Position;

import java.util.List;

import io.reactivex.Maybe;

public interface NetworkRepository {

    Maybe<List<City>> getAutoCompleteCities(String startWith);

    Maybe<List<City>> getCitiesByCoordinate(Position position);
}

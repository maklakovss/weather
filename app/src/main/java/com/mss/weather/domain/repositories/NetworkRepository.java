package com.mss.weather.domain.repositories;

import android.support.annotation.NonNull;

import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.domain.models.Position;

import java.util.List;

import io.reactivex.Maybe;

public interface NetworkRepository {

    @NonNull
    Maybe<List<City>> getAutoCompleteCities(@NonNull final String startWith);

    @NonNull
    Maybe<List<City>> getCitiesByCoordinate(@NonNull final Position position);

    @NonNull
    Maybe<InfoWeather> getWeatherInfo(@NonNull final City city);
}

package com.mss.weather.domain.repositories;

import android.support.annotation.NonNull;

import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.domain.models.Position;

import java.util.Date;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface NetworkRepository {

    @NonNull
    Maybe<List<City>> getAutoCompleteCities(@NonNull final String startWith);

    @NonNull
    Maybe<List<City>> getCitiesByCoordinate(@NonNull final Position position);

    @NonNull
    Maybe<InfoWeather> getWeatherInfo(@NonNull final City city);

    Observable<InfoWeather> getPastWeatherInfo(@NonNull final City city, @NonNull final Date dateFrom, @NonNull final Date dateTo);
}

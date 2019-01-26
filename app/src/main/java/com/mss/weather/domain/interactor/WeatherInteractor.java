package com.mss.weather.domain.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.domain.models.Position;

import java.util.List;

import io.reactivex.Maybe;

public interface WeatherInteractor {

    @NonNull
    List<City> getListCities();

    @Nullable
    City getCurrentCity();

    void setCurrentCity(@NonNull final City currentCity);

    void addCity(@NonNull final City city);

    void deleteCity(@NonNull final City city);

    @NonNull
    Maybe<List<City>> getAutoCompleteLocations(@NonNull final String searchTemplate);

    @NonNull
    Maybe<List<City>> getLocationsByPosition(@NonNull final Position position);

    @NonNull
    Maybe<Position> getPosition();

    @NonNull
    Maybe<InfoWeather> getWeatherInfo(@NonNull final City city);

    @Nullable
    InfoWeather getLocalWeatherInfo(@NonNull final City city);

    @Nullable
    City getCityById(@NonNull final String cityId);
}

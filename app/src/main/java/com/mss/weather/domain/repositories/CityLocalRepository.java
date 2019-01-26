package com.mss.weather.domain.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.domain.models.City;

import java.util.List;

public interface CityLocalRepository {

    @NonNull
    List<City> getCities();

    void addCity(@NonNull final City city);

    void deleteCity(@NonNull final City city);

    @Nullable
    City getCityById(@NonNull final String id);

    @Nullable
    String getLastCityId();

    void setLastCityId(@NonNull final String lastCityID);
}

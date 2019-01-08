package com.mss.weather.domain.interactor;

import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.Position;
import com.mss.weather.domain.models.WeatherInfo;

import java.util.List;

import io.reactivex.Maybe;

public interface WeatherInteractor {

    List<City> getListCities();

    City getCurrentCity();

    void setCurrentCity(City currentCity);

    void setOnCurrentCityChanged(OnCurrentCityChanged onCurrentCityChanged);

    void addCity(City city);

    void deleteCity(City city);

    Maybe<List<City>> getAutoCompleteLocations(String searchTemplate);

    Maybe<List<City>> getLocationsByPosition(Position position);

    Maybe<Position> getPosition();

    Maybe<WeatherInfo> getWeatherInfo(City city);

    public interface OnCurrentCityChanged {
        void onChanged(City currentCity);
    }
}

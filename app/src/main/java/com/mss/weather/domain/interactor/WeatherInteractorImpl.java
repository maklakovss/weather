package com.mss.weather.domain.interactor;

import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.domain.models.Position;
import com.mss.weather.domain.repositories.CityLocalRepository;
import com.mss.weather.domain.repositories.NetworkRepository;
import com.mss.weather.domain.repositories.SensorsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;

public class WeatherInteractorImpl implements WeatherInteractor {

    NetworkRepository networkRepository;
    CityLocalRepository cityLocalRepository;
    SensorsRepository sensorsRepository;

    private List<City> cityList;

    private City currentCity;

    @Inject
    public WeatherInteractorImpl(NetworkRepository networkRepository,
                                 CityLocalRepository cityLocalRepository,
                                 SensorsRepository sensorsRepository) {
        this.networkRepository = networkRepository;
        this.cityLocalRepository = cityLocalRepository;
        this.sensorsRepository = sensorsRepository;
    }

    @Override
    public List<City> getListCities() {
        if (cityList == null) {
            loadCities();
            String lastCityId = cityLocalRepository.getLastCityId();
            if (!lastCityId.equals("")) {
                currentCity = cityLocalRepository.getCityById(lastCityId);
            }
        }
        return cityList;
    }

    @Override
    public City getCurrentCity() {
        return currentCity;
    }

    @Override
    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
        cityLocalRepository.setLastCityId(currentCity.getId());
    }

    private void loadCities() {
        cityList = cityLocalRepository.getCities();
    }

    @Override
    public void addCity(City city) {
        cityList.add(city);
        cityLocalRepository.addCity(city);
    }

    @Override
    public Maybe<List<City>> getAutoCompleteLocations(String searchTemplate) {
        return networkRepository.getAutoCompleteCities(searchTemplate);
    }

    @Override
    public void deleteCity(City city) {
        cityList.remove(city);
        cityLocalRepository.deleteCity(city);
    }

    @Override
    public Maybe<List<City>> getLocationsByPosition(Position position) {
        return networkRepository.getCitiesByCoordinate(position);
    }

    @Override
    public Maybe<Position> getPosition() {
        return sensorsRepository.getPosition();
    }

    @Override
    public Maybe<InfoWeather> getWeatherInfo(City city) {
        return networkRepository.getWeatherInfo(city);
    }

}

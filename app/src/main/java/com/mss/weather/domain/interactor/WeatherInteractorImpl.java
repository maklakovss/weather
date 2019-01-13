package com.mss.weather.domain.interactor;

import com.mss.weather.domain.LocalRepository;
import com.mss.weather.domain.NetworkRepository;
import com.mss.weather.domain.SensorsRepository;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.Position;
import com.mss.weather.domain.models.WeatherInfo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;

public class WeatherInteractorImpl implements WeatherInteractor {

    NetworkRepository networkRepository;
    LocalRepository localRepository;
    SensorsRepository sensorsRepository;

    private List<City> cityList;

    private City currentCity;

    @Inject
    public WeatherInteractorImpl(NetworkRepository networkRepository,
                                 LocalRepository localRepository,
                                 SensorsRepository sensorsRepository) {
        this.networkRepository = networkRepository;
        this.localRepository = localRepository;
        this.sensorsRepository = sensorsRepository;
    }

    @Override
    public List<City> getListCities() {
        if (cityList == null) {
            loadCities();
            String lastCityId = localRepository.getLastCityId();
            if (!lastCityId.equals("")) {
                currentCity = localRepository.getCityById(lastCityId);
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
        localRepository.setLastCityId(currentCity.getId());
    }

    private void loadCities() {
        cityList = localRepository.getCities();
    }

    @Override
    public void addCity(City city) {
        cityList.add(city);
        localRepository.addCity(city);
    }

    @Override
    public Maybe<List<City>> getAutoCompleteLocations(String searchTemplate) {
        return networkRepository.getAutoCompleteCities(searchTemplate);
    }

    @Override
    public void deleteCity(City city) {
        cityList.remove(city);
        localRepository.deleteCity(city);
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
    public Maybe<WeatherInfo> getWeatherInfo(City city) {
        return networkRepository.getWeatherInfo(city);
    }

}

package com.mss.weather.domain.interactor;

import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.domain.models.Position;
import com.mss.weather.domain.repositories.CityLocalRepository;
import com.mss.weather.domain.repositories.CurrentWeatherLocalRepository;
import com.mss.weather.domain.repositories.DayWeatherLocalRepository;
import com.mss.weather.domain.repositories.InfoWeatherLocalRepository;
import com.mss.weather.domain.repositories.NetworkRepository;
import com.mss.weather.domain.repositories.SensorsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;

public class WeatherInteractorImpl implements WeatherInteractor {

    private final NetworkRepository networkRepository;
    private final CityLocalRepository cityLocalRepository;
    private final InfoWeatherLocalRepository infoWeatherLocalRepository;
    private final CurrentWeatherLocalRepository currentWeatherLocalRepository;
    private final DayWeatherLocalRepository dayWeatherLocalRepository;
    private final SensorsRepository sensorsRepository;

    private List<City> cityList;

    private City currentCity;

    @Inject
    public WeatherInteractorImpl(NetworkRepository networkRepository,
                                 CityLocalRepository cityLocalRepository,
                                 InfoWeatherLocalRepository infoWeatherLocalRepository,
                                 CurrentWeatherLocalRepository currentWeatherLocalRepository,
                                 DayWeatherLocalRepository dayWeatherLocalRepository,
                                 SensorsRepository sensorsRepository) {
        this.networkRepository = networkRepository;
        this.cityLocalRepository = cityLocalRepository;
        this.infoWeatherLocalRepository = infoWeatherLocalRepository;
        this.currentWeatherLocalRepository = currentWeatherLocalRepository;
        this.dayWeatherLocalRepository = dayWeatherLocalRepository;
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
        Maybe<InfoWeather> infoWeatherMaybe = networkRepository.getWeatherInfo(city)
                .doOnSuccess(infoWeather -> {
                    infoWeatherLocalRepository.updateOrInsertInfoWeather(infoWeather);
                    currentWeatherLocalRepository.updateOrInsertCurrentWeather(infoWeather.getCurrentWeather());
                    dayWeatherLocalRepository.updateOrInsertDayWeather(infoWeather.getDays());
                });
        return infoWeatherMaybe;
    }

    @Override
    public InfoWeather getLocalWeatherInfo(City city) {
        InfoWeather infoWeather = infoWeatherLocalRepository.getInfoWeatherById(city.getId());
        if (infoWeather != null) {
            infoWeather.setCurrentWeather(currentWeatherLocalRepository.getCurrentWeatherById(city.getId()));
            infoWeather.setDays(dayWeatherLocalRepository.getDayWeathersByCityId(city.getId()));
        }
        return infoWeather;
    }

}

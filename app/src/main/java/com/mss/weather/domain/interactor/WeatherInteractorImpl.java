package com.mss.weather.domain.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.DayWeather;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.domain.models.Position;
import com.mss.weather.domain.repositories.CityLocalRepository;
import com.mss.weather.domain.repositories.CurrentWeatherLocalRepository;
import com.mss.weather.domain.repositories.DayWeatherLocalRepository;
import com.mss.weather.domain.repositories.HourWeatherLocalRepository;
import com.mss.weather.domain.repositories.InfoWeatherLocalRepository;
import com.mss.weather.domain.repositories.NetworkRepository;
import com.mss.weather.domain.repositories.SensorsRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;

public class WeatherInteractorImpl implements WeatherInteractor {

    private final NetworkRepository networkRepository;
    private final CityLocalRepository cityLocalRepository;
    private final InfoWeatherLocalRepository infoWeatherLocalRepository;
    private final CurrentWeatherLocalRepository currentWeatherLocalRepository;
    private final DayWeatherLocalRepository dayWeatherLocalRepository;
    private final HourWeatherLocalRepository hourWeatherLocalRepository;
    private final SensorsRepository sensorsRepository;

    private List<City> cityList = null;

    private City currentCity = null;

    @Inject
    public WeatherInteractorImpl(@NonNull final NetworkRepository networkRepository,
                                 @NonNull final CityLocalRepository cityLocalRepository,
                                 @NonNull final InfoWeatherLocalRepository infoWeatherLocalRepository,
                                 @NonNull final CurrentWeatherLocalRepository currentWeatherLocalRepository,
                                 @NonNull final DayWeatherLocalRepository dayWeatherLocalRepository,
                                 @NonNull final HourWeatherLocalRepository hourWeatherLocalRepository,
                                 @NonNull final SensorsRepository sensorsRepository) {

        this.networkRepository = networkRepository;
        this.cityLocalRepository = cityLocalRepository;
        this.infoWeatherLocalRepository = infoWeatherLocalRepository;
        this.currentWeatherLocalRepository = currentWeatherLocalRepository;
        this.dayWeatherLocalRepository = dayWeatherLocalRepository;
        this.hourWeatherLocalRepository = hourWeatherLocalRepository;
        this.sensorsRepository = sensorsRepository;
    }

    @NonNull
    @Override
    public List<City> getListCities() {
        if (cityList == null) {
            loadCities();
            final String lastCityId = cityLocalRepository.getLastCityId();
            if (!lastCityId.equals("")) {
                currentCity = cityLocalRepository.getCityById(lastCityId);
            }
        }
        return cityList;
    }

    @Nullable
    @Override
    public City getCurrentCity() {
        return currentCity;
    }

    @Override
    public void setCurrentCity(@NonNull final City currentCity) {
        this.currentCity = currentCity;
        cityLocalRepository.setLastCityId(currentCity.getId());
    }

    private void loadCities() {
        cityList = cityLocalRepository.getCities();
    }

    @Override
    public void addCity(@NonNull final City city) {
        cityList.add(city);
        cityLocalRepository.addCity(city);
    }

    @NonNull
    @Override
    public Maybe<List<City>> getAutoCompleteLocations(@NonNull final String searchTemplate) {
        return networkRepository.getAutoCompleteCities(searchTemplate);
    }

    @Override
    public void deleteCity(@NonNull final City city) {
        cityList.remove(city);
        cityLocalRepository.deleteCity(city);
        infoWeatherLocalRepository.deleteInfoWeather(city.getId());
        currentWeatherLocalRepository.deleteCurrentWeather(city.getId());
        dayWeatherLocalRepository.deleteDayWeatherByCityID(city.getId());
        hourWeatherLocalRepository.deleteHourWeatherByCityID(city.getId());
    }

    @NonNull
    @Override
    public Maybe<List<City>> getLocationsByPosition(@NonNull final Position position) {
        return networkRepository.getCitiesByCoordinate(position);
    }

    @NonNull
    @Override
    public Maybe<Position> getPosition() {
        return sensorsRepository.getPosition();
    }

    @NonNull
    @Override
    public Maybe<InfoWeather> getWeatherInfo(@NonNull final City city) {
        return networkRepository.getWeatherInfo(city)
                .doOnSuccess(infoWeather -> {
                    infoWeatherLocalRepository.updateOrInsertInfoWeather(infoWeather);
                    currentWeatherLocalRepository.updateOrInsertCurrentWeather(infoWeather.getCurrentWeather());
                    dayWeatherLocalRepository.deleteOldDayWeatherByCityID(infoWeather.getCityID(), getCurrentDate());
                    hourWeatherLocalRepository.deleteOldHourWeatherByCityID(infoWeather.getCityID(), getCurrentDate());
                    dayWeatherLocalRepository.updateOrInsertDayWeather(infoWeather.getDays());
                    for (DayWeather day : infoWeather.getDays()) {
                        hourWeatherLocalRepository.updateOrInsertHourWeather(day.getHourly());
                    }
                });
    }

    @Nullable
    @Override
    public InfoWeather getLocalWeatherInfo(@NonNull final City city) {
        final InfoWeather infoWeather = infoWeatherLocalRepository.getInfoWeatherById(city.getId());
        if (infoWeather != null) {
            infoWeather.setCurrentWeather(currentWeatherLocalRepository.getCurrentWeatherById(city.getId()));
            infoWeather.setDays(dayWeatherLocalRepository.getDayWeathersByCityId(city.getId(), getCurrentDate()));
            for (DayWeather day : infoWeather.getDays()) {
                day.setHourly(hourWeatherLocalRepository.getHourWeathersByCityId(city.getId(), day.getDate()));
            }
        }
        return infoWeather;
    }

    @Nullable
    @Override
    public City getCityById(String cityId) {
        return cityLocalRepository.getCityById(cityId);
    }

    @NonNull
    @Override
    public Maybe<InfoWeather> getWeatherStatistics(final @NonNull City city, @NonNull final Date dateFrom, @NonNull final Date dateTo) {
        return networkRepository.getPastWeatherInfo(city, dateFrom, dateTo);
    }

    @NonNull
    private Date getCurrentDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.AM_PM, 0);
        return calendar.getTime();
    }

}

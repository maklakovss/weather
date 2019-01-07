package com.mss.weather.domain.interactor;

import android.support.annotation.NonNull;

import com.mss.weather.domain.sensors.SensorsRepository;
import com.mss.weather.domain.sensors.models.Position;
import com.mss.weather.domain.weather.LocalRepository;
import com.mss.weather.domain.weather.NetworkRepository;
import com.mss.weather.domain.weather.models.City;
import com.mss.weather.presentation.view.models.WeatherData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;

public class WeatherInteractorImpl implements WeatherInteractor {

    NetworkRepository networkRepository;
    LocalRepository localRepository;
    SensorsRepository sensorsRepository;

    private List<City> cityList;

    private City currentCity;
    private OnCurrentCityChanged onCurrentCityChanged;

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
        if (onCurrentCityChanged != null) {
            onCurrentCityChanged.onChanged(currentCity);
        }
    }

    @Override
    public WeatherData getWeatherByCity(City city) {
        WeatherData weatherData = getWeatherData(city, new Date());
        return weatherData;
    }

    @NonNull
    private WeatherData getWeatherData(City city, Date date) {
        WeatherData weatherData = new WeatherData();
        weatherData.setCityName(city.getAreaName());
        weatherData.setWeatherDate(date);
        weatherData.getSunrise().setHours(6);
        weatherData.getSunrise().setMinutes(0);
        weatherData.getSunrise().setSeconds(0);
        weatherData.getSunset().setHours(23);
        weatherData.getSunset().setMinutes(0);
        weatherData.getSunset().setSeconds(0);
        weatherData.setCloudsPercent(30);
        weatherData.setCloudsDescription("переменная облачность");
        weatherData.setTemp(25);
        weatherData.setTempMin(23);
        weatherData.setTempMax(27);
        weatherData.setPressure(760);
        weatherData.setHumidity(30);
        weatherData.setWindSpeed(5);
        weatherData.setWindDeg(90);
        weatherData.setSnow(10);
        weatherData.setRain(0);
        return weatherData;
    }

    @Override
    public List<WeatherData> getWeatherList(City city) {
        List<WeatherData> weatherDataList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 10; i++) {
            calendar.add(Calendar.DATE, 1);
            weatherDataList.add(getWeatherData(city, calendar.getTime()));
        }
        return weatherDataList;
    }

    private void loadCities() {
        cityList = localRepository.getCities();
    }

    public void setOnCurrentCityChanged(OnCurrentCityChanged onCurrentCityChanged) {
        this.onCurrentCityChanged = onCurrentCityChanged;
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

}

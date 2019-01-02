package com.mss.weather.domain;

import android.support.annotation.NonNull;

import com.mss.weather.presentation.view.models.CitySettings;
import com.mss.weather.presentation.view.models.WeatherData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class WeatherInteractorImpl implements WeatherInteractor {

    private final PreferencesRepository preferencesRepository;

    private List<CitySettings> citySettingsList;
    private CitySettings currentCity;
    private OnCurrentCityChanged onCurrentCityChanged;
    private OnCityUpdated onCityUpdated;

    @Inject
    public WeatherInteractorImpl(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }

    @Override
    public List<CitySettings> getListCities() {
        if (citySettingsList == null) {
            loadCities();
        }
        return citySettingsList;
    }

    @Override
    public CitySettings getCurrentCity() {
        return currentCity;
    }

    @Override
    public void setCurrentCity(CitySettings currentCity) {
        this.currentCity = currentCity;
        saveDefaultCity(currentCity.getName());
        if (onCurrentCityChanged != null) {
            onCurrentCityChanged.onChanged(currentCity);
        }
    }

    private void saveDefaultCity(String name) {
        preferencesRepository.setDefaultCityName(name);
    }

    @Override
    public WeatherData getWeatherByCity(CitySettings citySettings) {
        WeatherData weatherData = getWeatherData(citySettings, new Date());
        return weatherData;
    }

    @NonNull
    private WeatherData getWeatherData(CitySettings citySettings, Date date) {
        WeatherData weatherData = new WeatherData();
        weatherData.setCityName(citySettings.getName());
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
    public List<WeatherData> getWeatherList(CitySettings citySettings) {
        List<WeatherData> weatherDataList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 10; i++) {
            calendar.add(Calendar.DATE, 1);
            weatherDataList.add(getWeatherData(citySettings, calendar.getTime()));
        }
        return weatherDataList;
    }

    private void loadCities() {
        citySettingsList = new ArrayList<>();
        citySettingsList.add(new CitySettings("Москва"));
        citySettingsList.add(new CitySettings("Санкт-Петербург"));
        citySettingsList.add(new CitySettings("Тюмень"));
        loadDefaultCity();
    }

    private void loadDefaultCity() {
        String defaultCity = preferencesRepository.getDefaultCityName();
        for (int i = 0; i < citySettingsList.size(); i++) {
            if (defaultCity.equals(citySettingsList.get(i).getName())) {
                currentCity = citySettingsList.get(i);
            }
        }
    }

    public void setOnCurrentCityChanged(OnCurrentCityChanged onCurrentCityChanged) {
        this.onCurrentCityChanged = onCurrentCityChanged;
    }

    @Override
    public void setOnOnCityUpdated(OnCityUpdated onCityUpdated) {
        this.onCityUpdated = onCityUpdated;
    }

    @Override
    public void addCity(CitySettings citySettings) {
        citySettingsList.add(citySettings);
    }

    @Override
    public void saveSettings(CitySettings citySettings) {
        if (onCityUpdated != null) {
            onCityUpdated.onUpdated(citySettings);
        }
    }

}

package com.mss.weather.domain;

import com.mss.weather.presentation.view.models.CitySettings;
import com.mss.weather.presentation.view.models.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class WeatherInteractorImpl implements WeatherInteractor {

    private List<CitySettings> citySettingsList;
    private String currentCityName;
    private OnCurrentCityChanged onCurrentCityChanged;


    @Override
    public List<CitySettings> getListCities() {
        if (citySettingsList == null) {
            loadCities();
        }
        return citySettingsList;
    }

    @Override
    public String getCurrentCityName() {
        return currentCityName;
    }

    @Override
    public void setCurrentCityName(String currentCityName) {
        this.currentCityName = currentCityName;
        if (onCurrentCityChanged != null) {
            onCurrentCityChanged.onChanged(currentCityName);
        }
    }

    @Override
    public WeatherData getWeatherByCity(String cityName) {
        WeatherData weatherData = new WeatherData();
        weatherData.setCityName(cityName);
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

    private void loadCities() {
        citySettingsList = new ArrayList<>();
        citySettingsList.add(new CitySettings("Москва"));
        citySettingsList.add(new CitySettings("Санкт-Петербург"));
        citySettingsList.add(new CitySettings("Тюмень"));
        currentCityName = citySettingsList.get(0).getName();
    }

    public void setOnCurrentCityChanged(OnCurrentCityChanged onCurrentCityChanged) {
        this.onCurrentCityChanged = onCurrentCityChanged;
    }

}

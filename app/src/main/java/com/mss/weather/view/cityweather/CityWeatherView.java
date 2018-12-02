package com.mss.weather.view.cityweather;

import com.arellomobile.mvp.MvpView;
import com.mss.weather.view.models.WeatherData;

public interface CityWeatherView extends MvpView {
    void showWeather(WeatherData weatherData);
}

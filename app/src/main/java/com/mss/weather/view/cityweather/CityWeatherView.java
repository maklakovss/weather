package com.mss.weather.view.cityweather;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.mss.weather.view.models.WeatherData;

public interface CityWeatherView extends MvpView {

    void showWeather(@NonNull final WeatherData weatherData);
}

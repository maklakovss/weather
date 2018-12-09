package com.mss.weather.presentation.view.cityweather;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.mss.weather.presentation.view.models.WeatherData;

public interface CityWeatherView extends MvpView {

    void showWeather(@NonNull final WeatherData weatherData);
}

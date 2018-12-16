package com.mss.weather.presentation.view.cityweather;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.mss.weather.presentation.view.models.WeatherData;

import java.util.List;

public interface CityWeatherView extends MvpView {

    void showWeather(@NonNull final WeatherData weatherData);

    void showWeatherList(List<WeatherData> weatherList);
}

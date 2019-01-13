package com.mss.weather.presentation.view.currentweather;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.WeatherCurrent;
import com.mss.weather.domain.models.WeatherDay;

import java.util.List;

public interface CurrentWeatherView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showCity(@NonNull final City city);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showCurrentWeather(@NonNull final WeatherCurrent weatherCurrent);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showWeatherList(@NonNull List<WeatherDay> weatherDays);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress(boolean visible);

}

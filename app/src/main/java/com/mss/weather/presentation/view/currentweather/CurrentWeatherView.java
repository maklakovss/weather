package com.mss.weather.presentation.view.currentweather;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.CurrentWeather;
import com.mss.weather.domain.models.DayWeather;

import java.util.Date;
import java.util.List;

public interface CurrentWeatherView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showCity(@NonNull final City city);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showCurrentWeather(@NonNull final CurrentWeather currentWeather);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showWeatherList(@NonNull final List<DayWeather> dayWeathers);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void clearCurrentWeather();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void clearWeatherList();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress(boolean visible);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showDay(@NonNull final String cityID, @NonNull final Date date);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showHistory(String cityID);
}

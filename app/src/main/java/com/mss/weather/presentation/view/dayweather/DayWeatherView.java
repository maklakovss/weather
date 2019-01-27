package com.mss.weather.presentation.view.dayweather;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.DayWeather;
import com.mss.weather.domain.models.HourWeather;

import java.util.List;

public interface DayWeatherView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showCity(@NonNull final City city);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showDayWeather(@NonNull final DayWeather dayWeather);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showHoursWeatherList(@NonNull List<HourWeather> hourWeathers);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void clearDayWeather();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void clearHoursWeatherList();
}

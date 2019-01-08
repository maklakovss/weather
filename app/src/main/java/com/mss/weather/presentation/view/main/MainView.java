package com.mss.weather.presentation.view.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void closeDrawer();

    @StateStrategyType(SkipStrategy.class)
    void showAddCity();

    @StateStrategyType(SkipStrategy.class)
    void showWeather();

    @StateStrategyType(SkipStrategy.class)
    void showCityList();
}

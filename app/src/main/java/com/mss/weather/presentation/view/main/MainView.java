package com.mss.weather.presentation.view.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void closeDrawer();

//    @StateStrategyType(AddToEndSingleStrategy.class)
//    void showFragment();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showAddCity();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showCurrentWeather();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showCityList();
}

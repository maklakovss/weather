package com.mss.weather.presentation.view.listcities;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface ListCitiesView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateList(String[] cities);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setCurrentCity(int checkedCity);

    @StateStrategyType(SkipStrategy.class)
    void showWeather();

    void showSettings();
}

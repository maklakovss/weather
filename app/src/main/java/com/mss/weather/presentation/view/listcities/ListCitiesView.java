package com.mss.weather.presentation.view.listcities;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

public interface ListCitiesView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateList(List<String> cities);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setCurrentCity(int checkedCity);

    @StateStrategyType(SkipStrategy.class)
    void showWeather();

    void showSettings();
}

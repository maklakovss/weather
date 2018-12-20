package com.mss.weather.presentation.view.listcities;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mss.weather.presentation.view.models.CitySettings;

import java.util.List;

public interface ListCitiesView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateList(List<CitySettings> cities);

//    @StateStrategyType(AddToEndSingleStrategy.class)
//    void setCurrentCity(int checkedCity);

    @StateStrategyType(SkipStrategy.class)
    void showWeather();

    @StateStrategyType(SkipStrategy.class)
    void showSettings();

    @StateStrategyType(SkipStrategy.class)
    void updateCity(int position);
}

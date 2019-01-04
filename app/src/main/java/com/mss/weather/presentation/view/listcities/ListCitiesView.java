package com.mss.weather.presentation.view.listcities;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mss.weather.domain.city.models.City;

import java.util.List;

public interface ListCitiesView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateList(List<City> cities);

//    @StateStrategyType(AddToEndSingleStrategy.class)
//    void setCurrentCity(int checkedCity);

    @StateStrategyType(SkipStrategy.class)
    void showWeather();

    @StateStrategyType(SkipStrategy.class)
    void showCity();

    @StateStrategyType(SkipStrategy.class)
    void updateCity(int position);
}

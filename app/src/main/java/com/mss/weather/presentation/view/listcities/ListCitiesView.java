package com.mss.weather.presentation.view.listcities;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mss.weather.domain.models.City;

import java.util.List;

public interface ListCitiesView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void updateList(List<City> cities);

    @StateStrategyType(SkipStrategy.class)
    void showWeather();

    @StateStrategyType(SkipStrategy.class)
    void showSelectCity();

    @StateStrategyType(SkipStrategy.class)
    void updateCity(int position);
}

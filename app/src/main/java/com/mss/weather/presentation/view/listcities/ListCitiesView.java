package com.mss.weather.presentation.view.listcities;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mss.weather.domain.models.City;

import java.util.List;

public interface ListCitiesView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateList(@NonNull final List<City> cities);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showWeather();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showSelectCity();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void updateCity(int position);
}

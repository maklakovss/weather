package com.mss.weather.presentation.view.selectcity;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mss.weather.domain.models.City;

import java.util.List;

public interface SelectCityView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showCities(@NonNull List<City> cities);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void clearCities();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress(boolean visible);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void back();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void hideKeyboard();
}

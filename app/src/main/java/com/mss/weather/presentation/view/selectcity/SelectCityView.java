package com.mss.weather.presentation.view.selectcity;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.mss.weather.domain.models.City;

import java.util.List;

public interface SelectCityView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showCities(@NonNull List<City> cities);

    @StateStrategyType(SkipStrategy.class)
    void showProgress(boolean visible);

    @StateStrategyType(SkipStrategy.class)
    void back();

}

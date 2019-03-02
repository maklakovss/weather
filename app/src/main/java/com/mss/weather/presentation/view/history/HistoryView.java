package com.mss.weather.presentation.view.history;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.github.mikephil.charting.data.LineData;
import com.mss.weather.domain.models.City;

public interface HistoryView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showCity(@NonNull final City city);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void clearStatistics();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void addStatistics(@NonNull final LineData lineData);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress(boolean visible);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void fillSpiners(Integer[] years, String[] months, Integer[] hours);
}

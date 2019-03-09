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
    void showCityInfo(@NonNull final City city);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void fillSpiners(@NonNull final String[] parameters, @NonNull final Integer[] years, @NonNull final String[] months, @NonNull final Integer[] hours);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void clearChartData();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setChartDate(@NonNull final LineData lineData);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showChart(boolean visible);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress(boolean visible);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgressLog(boolean visible);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setProgressLogText(@NonNull final String logText);
}

package com.mss.weather.presentation.view.history;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.github.mikephil.charting.data.Entry;
import com.mss.weather.domain.models.City;

import java.util.List;

public interface HistoryView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showCity(@NonNull final City city);

    void clearStatistics();

    void addStatistics(@NonNull final List<Entry> entryList, int colorLine, int colorValue, @NonNull final String label);
}

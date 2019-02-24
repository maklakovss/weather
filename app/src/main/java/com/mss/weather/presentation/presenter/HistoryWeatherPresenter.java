package com.mss.weather.presentation.presenter;

import android.graphics.Color;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.mikephil.charting.data.Entry;
import com.mss.weather.domain.interactor.WeatherInteractor;
import com.mss.weather.domain.models.City;
import com.mss.weather.presentation.view.history.HistoryView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class HistoryWeatherPresenter extends MvpPresenter<HistoryView> {
    private final WeatherInteractor weatherInteractor;

    @Inject
    public HistoryWeatherPresenter(@NonNull final WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void onCreate(@NonNull final String cityId) {
        final City city = weatherInteractor.getCityById(cityId);
        if (city != null) {
            getViewState().showCity(city);
        }
        getViewState().clearStatistics();

        List<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < 12; i++) {
            entries.add(new Entry(i, i));
        }
        getViewState().addStatistics(entries, Color.RED, Color.BLUE, "label");
    }
}

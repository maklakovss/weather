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
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

        Random random = new Random();

        List<Float>[] months = new List[12];
        for (int m = 0; m < 12; m++) {
            months[m] = new ArrayList<>(24 * 31);
            for (int i = 0; i < 24 * 31; i++) {
                months[m].add(random.nextFloat());
            }
            Collections.sort(months[m]);
        }

        final float countSegments = 9;

        for (int j = 0; j <= countSegments; j++) {
            List<Entry> entries = new ArrayList<Entry>();
            for (int i = 0; i < 12; i++) {
                entries.add(new Entry(i, months[i].get(Math.max(0, (int) (months[i].size() * (j / countSegments) - 1)))));
            }
            getViewState().addStatistics(entries, Color.rgb(0, (int) (95 + Math.abs(j - (countSegments + 1) / 2f) * 320 / countSegments), 0), Color.BLUE, "label");
        }
    }
}

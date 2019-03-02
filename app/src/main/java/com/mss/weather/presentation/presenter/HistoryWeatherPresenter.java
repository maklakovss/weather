package com.mss.weather.presentation.presenter;

import android.graphics.Color;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.mss.weather.domain.interactor.WeatherInteractor;
import com.mss.weather.domain.models.City;
import com.mss.weather.presentation.view.history.CustomFillFormatter;
import com.mss.weather.presentation.view.history.HistoryView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
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
        getViewState().showProgress(true);

        Integer[] years = new Integer[]{2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019};
        Integer[] hours = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        DateFormatSymbols.getInstance(Locale.getDefault()).getMonths();
        String[] months = DateFormatSymbols.getInstance(Locale.getDefault()).getMonths();
        String[] monthsAddAll = new String[13];
        for (int i = 0; i < months.length; i++) {
            monthsAddAll[i + 1] = months[i];
        }
        monthsAddAll[0] = "All";
        getViewState().fillSpiners(years, monthsAddAll, hours);

        final City city = weatherInteractor.getCityById(cityId);
        if (city != null) {
            getViewState().showCity(city);
        }

        getViewState().clearStatistics();

        Random random = new Random();
        final int count = 12 * 31;
        List<Float>[] days = new List[count];
        for (int m = 0; m < count; m++) {
            days[m] = new ArrayList<>(240);
            for (int i = 0; i < 240; i++) {
                days[m].add(random.nextFloat());
            }
            Collections.sort(days[m]);
        }

        final float countSegments = 9;

        LineData lineData = new LineData();

        for (int j = 0; j <= countSegments; j++) {
            List<Entry> entries = new ArrayList<Entry>();
            for (int i = 0; i < count; i++) {
                entries.add(new Entry(i, days[i].get(Math.max(0, (int) (days[i].size() * (j / countSegments) - 1)))));
            }
            LineDataSet dataSet = new LineDataSet(entries, "");
            int color = Color.rgb(0, (int) (95 + Math.abs(j - (countSegments + 1) / 2f) * 320 / countSegments), 0);
            dataSet.setColor(color);
            dataSet.setFillColor(color);
            dataSet.setDrawCircles(false);
            if (lineData.getDataSetCount() > 0) {
                dataSet.setFillAlpha(255);
                dataSet.setDrawFilled(true);
                final LineDataSet lastDataset = (LineDataSet) lineData.getDataSetByIndex(lineData.getDataSetCount() - 1);
                dataSet.setFillFormatter(new CustomFillFormatter(lastDataset));
            }
            lineData.addDataSet(dataSet);
            getViewState().addStatistics(lineData);
        }
        getViewState().showProgress(false);
    }

    public void clickApply(String selectedParameter) {

    }
}

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
import com.mss.weather.domain.models.DayWeather;
import com.mss.weather.domain.models.HourWeather;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.presentation.view.history.CustomFillFormatter;
import com.mss.weather.presentation.view.history.HistoryView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;

@InjectViewState
public class HistoryWeatherPresenter extends MvpPresenter<HistoryView> {

    private final WeatherInteractor weatherInteractor;

    final private static int COUNT_YEARS = 10;
    final private float COUNT_SEGMENTS = 9;
    private Integer[] years;
    private String[] months;
    private Integer[] hours;
    private City city;

    @Inject
    public HistoryWeatherPresenter(@NonNull final WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
        initRanges();
    }

    public void onCreate(@NonNull final String cityId, String[] parameters) {
        getViewState().showProgress(true);
        getViewState().fillSpiners(parameters, years, months, hours);

        city = weatherInteractor.getCityById(cityId);
        if (city != null) {
            getViewState().showCity(city);
        }
        getViewState().clearStatistics();
        getViewState().showProgress(false);
    }

    public void clickApply(int selectedParameter, int selectedYearFrom, int selectedYearTo, int selectedHourFrom, int selectedHourTo, int selectedMonth) {
        getViewState().showProgress(true);

        int selectedMonthFrom = 1;
        int selectedMonthTo = 12;
        if (selectedMonth != 0) {
            selectedMonthFrom = selectedMonth;
            selectedMonthTo = selectedMonth;
        }
        Calendar dateFrom = null;
        Calendar dateTo = null;
        dateFrom = Calendar.getInstance();
        dateFrom.set(years[selectedYearFrom], selectedMonthFrom, 1);
        dateTo = (Calendar) dateFrom.clone();
        dateTo.add(Calendar.MONTH, 1);
        dateTo.add(Calendar.DAY_OF_MONTH, -1);

        weatherInteractor.getWeatherStatistics(city, dateFrom.getTime(), dateTo.getTime())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(infoWeather -> onSuccess(infoWeather), e -> stopProgressOnError(e));

//        final Maybe<List<List<Float>>> maybe = Maybe.create(e -> {
//            List<List<Float>> statistics = new ArrayList<>(365);
//            Calendar dateFrom = null;
//            Calendar dateTo = null;
//            for (int year = selectedYearFrom; year <= selectedYearTo; year++) {
//                for (int month = selectedMonthFrom; month <= selectedMonthTo; month++) {
//                    if (dateFrom == null) {
//                        dateFrom = Calendar.getInstance();
//                        dateFrom.set(year, month, 1);
//                        dateTo = (Calendar) dateFrom.clone();
//                    } else {
//                        dateFrom = dateTo;
//                    }
//                    dateTo.roll(Calendar.MONTH, 1);
//                    InfoWeather infoWeather = networkRepository.getPastWeatherInfo(city, dateFrom.getTime(), dateTo.getTime());
//                    for (DayWeather day : infoWeather.getDays()) {
//                        List<Float> dayStatistics = new ArrayList<>(24);
//                        for (HourWeather hourWeather : day.getHourly()) {
//                            dayStatistics.add(new Float(hourWeather.getTempC()));
//                        }
//                        statistics.add(dayStatistics);
//                    }
//                }
//            }
//            e.onSuccess(statistics);
//        });
//        return maybe;
    }

    private void stopProgressOnError(Throwable e) {
        getViewState().clearStatistics();
        getViewState().showProgress(false);
    }

    private void onSuccess(InfoWeather infoWeather) {
        List<List<Float>> statistics = new ArrayList<>(365);
        for (DayWeather day : infoWeather.getDays()) {
            List<Float> dayStatistics = new ArrayList<>(24);
            for (HourWeather hourWeather : day.getHourly()) {
                dayStatistics.add(new Float(hourWeather.getTempC()));
            }
            statistics.add(dayStatistics);
        }

        getViewState().showStatistics(mapStatisticsToLineData(statistics));
        getViewState().showProgress(false);
    }

    private LineData mapStatisticsToLineData(List<List<Float>> statistics) {

        LineData lineData = new LineData();

        for (int j = 0; j <= COUNT_SEGMENTS; j++) {
            List<Entry> entries = new ArrayList<Entry>();
            for (int i = 0; i < statistics.size(); i++) {
                List<Float> dayStatistics = statistics.get(i);
                Collections.sort(dayStatistics);
                entries.add(new Entry(i, dayStatistics.get(Math.max(0, (int) (dayStatistics.size() * (j / COUNT_SEGMENTS) - 1)))));
            }
            LineDataSet dataSet = new LineDataSet(entries, "");
            int color = Color.rgb(0, (int) (95 + Math.abs(j - (COUNT_SEGMENTS + 1) / 2f) * 320 / COUNT_SEGMENTS), 0);
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
        }
        return lineData;
    }

    private void initRanges() {
        initYears();
        initHours();
        initMonth();
    }

    private void initMonth() {
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        months = new String[13];
        months[0] = "All";
        for (int i = 0; i < 12; i++) {
            calendar.set(Calendar.MONTH, i);
            months[i + 1] = monthFormat.format(calendar.getTime());
        }
    }

    private void initHours() {
        hours = new Integer[24];
        for (int i = 0; i < hours.length; i++) {
            hours[i] = i;
        }
    }

    private void initYears() {
        Calendar calendar = Calendar.getInstance();
        final int currentYear = calendar.get(Calendar.YEAR);
        years = new Integer[COUNT_YEARS];
        for (int i = 0; i < COUNT_YEARS; i++) {
            years[i] = currentYear - COUNT_YEARS + i + 1;
        }
    }

    @Data
    private static class DateRange {
        Date dateFrom;
        Date dateTo;

        public DateRange(Date dateFrom, Date dateTo) {
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
        }
    }
}

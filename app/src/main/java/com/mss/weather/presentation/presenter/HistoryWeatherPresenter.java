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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;

@InjectViewState
public class HistoryWeatherPresenter extends MvpPresenter<HistoryView> {

    private static final SimpleDateFormat dayOfYearFormat = new SimpleDateFormat("MMdd");

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
            selectedMonthFrom = selectedMonth - 1;
            selectedMonthTo = selectedMonth - 1;
        }

        List<DateRange> dateRangeList = getDateRangeList(years[selectedYearFrom], selectedMonthFrom, years[selectedYearTo], selectedMonthTo);

        Observable.fromIterable(dateRangeList)
                .concatMap(dateRange -> weatherInteractor.getWeatherStatistics(city, dateRange.dateFrom, dateRange.dateTo))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(infoWeather -> System.out.println(infoWeather.getDays().size()))
                .toList()
                .subscribe(infoWeathers -> onSuccess(infoWeathers), e -> stopProgressOnError(e));
    }

    private List<DateRange> getDateRangeList(int selectedYearFrom, int selectedMonthFrom, int selectedYearTo, int selectedMonthTo) {
        final List<DateRange> result = new ArrayList<>((selectedMonthTo - selectedMonthFrom + 1) * (selectedYearTo - selectedYearFrom + 1));
        for (int year = selectedYearFrom; year <= selectedYearTo; year++) {
            for (int month = selectedMonthFrom; month <= selectedMonthTo; month++) {
                Calendar dateFrom = null;
                Calendar dateTo = null;
                dateFrom = Calendar.getInstance();
                dateFrom.set(year, month, 1);
                dateTo = (Calendar) dateFrom.clone();
                dateTo.add(Calendar.MONTH, 1);
                dateTo.add(Calendar.DAY_OF_MONTH, -1);
                result.add(new DateRange(dateFrom.getTime(), dateTo.getTime()));
            }
        }
        return result;
    }

    private void stopProgressOnError(Throwable e) {
        getViewState().clearStatistics();
        getViewState().showProgress(false);
    }

    private void onSuccess(List<InfoWeather> infoWeathers) {
        HashMap<String, List<Float>> days = new HashMap<>();
        for (InfoWeather infoWeather : infoWeathers) {
            for (DayWeather day : infoWeather.getDays()) {
                String dayOfYear = dayOfYearFormat.format(day.getDate());
                List<Float> dayStatistics = days.get(dayOfYear);
                if (dayStatistics == null) {
                    dayStatistics = new ArrayList<>(24);
                }
                for (HourWeather hourWeather : day.getHourly()) {
                    dayStatistics.add(new Float(hourWeather.getTempC()));
                }
                days.put(dayOfYear, dayStatistics);
            }
        }

        getViewState().showStatistics(mapStatisticsToLineData(days));
        getViewState().showProgress(false);
    }

    private LineData mapStatisticsToLineData(HashMap<String, List<Float>> statistics) {

        tuneData(statistics);


        String[] days = new String[statistics.keySet().size()];
        int ii = 0;
        for (String day : statistics.keySet()) {
            days[ii++] = day;

        }

        Arrays.sort(days);
        LineData lineData = new LineData();
        for (int j = 0; j <= COUNT_SEGMENTS; j++) {
            List<Entry> entries = new ArrayList<Entry>();

            int i = 0;
            for (String day : days) {
                List<Float> statistic = statistics.get(day);
                entries.add(new Entry(i, statistic.get(Math.max(0, (int) (statistic.size() * (j / COUNT_SEGMENTS) - 1)))));
                i++;
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

    private void tuneData(HashMap<String, List<Float>> statistics) {
        Random random = new Random();
        for (List<Float> statistic : statistics.values()) {
            Collections.sort(statistic);
            Float previousValue = Float.MIN_VALUE;
            for (int i = 0; i < statistic.size(); i++) {
                Float value = statistic.get(i);
                if (previousValue.equals(value)) {
                    value += random.nextFloat() - 0.5f;
                    statistic.set(i, value);
                } else {
                    previousValue = value;
                }
            }
            Collections.sort(statistic);
        }
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

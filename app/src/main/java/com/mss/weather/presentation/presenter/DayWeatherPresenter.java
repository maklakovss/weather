package com.mss.weather.presentation.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.domain.interactor.WeatherInteractor;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.DayWeather;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.presentation.view.dayweather.DayWeatherView;

import java.util.Date;

import javax.inject.Inject;

@InjectViewState
public class DayWeatherPresenter extends MvpPresenter<DayWeatherView> {

    private final WeatherInteractor weatherInteractor;

    @Inject
    public DayWeatherPresenter(@NonNull final WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void onCreate(@NonNull final String cityId, @NonNull final Date date) {
        final City city = weatherInteractor.getCityById(cityId);
        if (city != null) {
            getViewState().showCity(city);

            final InfoWeather infoWeather = weatherInteractor.getLocalWeatherInfo(city);
            if (infoWeather != null) {
                DayWeather dayWeather = null;
                for (final DayWeather day : infoWeather.getDays()) {
                    if (day.getDate().equals(date)) {
                        dayWeather = day;
                        break;
                    }
                }
                if (dayWeather != null) {
                    getViewState().showDayWeather(dayWeather);
                    getViewState().showHoursWeatherList(dayWeather.getHourly());
                } else {
                    getViewState().clearDayWeather();
                    getViewState().clearHoursWeatherList();
                }
            }
        }
    }
}

package com.mss.weather.presentation.presenter;

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

    private WeatherInteractor weatherInteractor;

    @Inject
    public DayWeatherPresenter(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void onCreate(String cityId, Date date) {
        City city = weatherInteractor.getCityById(cityId);
        if (city != null) {
            getViewState().showCity(city);

            InfoWeather infoWeather = weatherInteractor.getLocalWeatherInfo(city);
            if (infoWeather != null) {
                DayWeather dayWeather = null;
                for (DayWeather day : infoWeather.getDays()) {
                    if (day.getDate().equals(date)) {
                        dayWeather = day;
                        break;
                    }
                }
                if (dayWeather != null) {
                    getViewState().showDayWeather(dayWeather);
                    getViewState().showHoursWeatherList(dayWeather.getHourly());
                }
            }
        }
    }
}

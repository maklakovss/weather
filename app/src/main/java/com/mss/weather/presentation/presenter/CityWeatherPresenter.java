package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.di.MyApplication;
import com.mss.weather.domain.WeatherInteractor;
import com.mss.weather.presentation.view.cityweather.CityWeatherView;

import javax.inject.Inject;

@InjectViewState
public class CityWeatherPresenter extends MvpPresenter<CityWeatherView> {

    @Inject
    WeatherInteractor weatherInteractor;

    public CityWeatherPresenter() {
        MyApplication.getApplicationComponent().inject(this);
        weatherInteractor.setOnCurrentCityChanged(new WeatherInteractor.OnCurrentCityChanged() {
            @Override
            public void onChanged(String currentCityName) {
                needData();
            }
        });
    }

    public void needData() {
        getViewState().showWeather(weatherInteractor.getWeatherByCity(weatherInteractor.getCurrentCityName()));
    }
}

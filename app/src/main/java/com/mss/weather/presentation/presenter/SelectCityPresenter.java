package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.di.MyApplication;
import com.mss.weather.domain.city.models.City;
import com.mss.weather.domain.weather.WeatherInteractor;
import com.mss.weather.presentation.view.selectcity.SelectCityView;

import javax.inject.Inject;

@InjectViewState
public class SelectCityPresenter extends MvpPresenter<SelectCityView> {

    @Inject
    WeatherInteractor weatherInteractor;

    public SelectCityPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public void needSettings() {
        getViewState().showCity(weatherInteractor.getCurrentCity());
    }

    public void saveSettings(City city) {
        weatherInteractor.saveSettings(city);
    }

    public City getCurrentCity() {
        return weatherInteractor.getCurrentCity();
    }
}

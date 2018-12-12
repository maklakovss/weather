package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.di.MyApplication;
import com.mss.weather.domain.WeatherInteractor;
import com.mss.weather.presentation.view.citysettings.CitySettingsView;
import com.mss.weather.presentation.view.models.CitySettings;

import javax.inject.Inject;

@InjectViewState
public class CitySettingsPresenter extends MvpPresenter<CitySettingsView> {

    @Inject
    WeatherInteractor weatherInteractor;

    public CitySettingsPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public void needSettings() {
        getViewState().showSettings(weatherInteractor.getCurrentCity());
    }

    public void saveSettings(CitySettings citySettings) {
        weatherInteractor.saveSettings(citySettings);
    }

    public CitySettings getCurrentSettings() {
        return weatherInteractor.getCurrentCity();
    }
}

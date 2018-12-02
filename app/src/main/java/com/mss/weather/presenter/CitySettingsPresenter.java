package com.mss.weather.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.view.citysettings.CitySettingsView;
import com.mss.weather.view.models.CitySettings;

@InjectViewState
public class CitySettingsPresenter extends MvpPresenter<CitySettingsView> {

    CitySettings settings;

    public void needSettings() {
        if (settings == null) {
            //получаем дефолтные настройки
            settings = new CitySettings();
            settings.setName("");
            settings.setShowClouds(true);
            settings.setShowHumidity(true);
            settings.setShowPressure(true);
            settings.setShowRain(true);
            settings.setShowSnow(true);
            settings.setShowSunrise(true);
            settings.setShowSunset(true);
            settings.setShowTemp(true);
            settings.setShowTempRange(true);
            settings.setShowWindSpeed(true);
            settings.setShowWindDeg(true);
        }
        getViewState().showSettings(settings);
    }

}

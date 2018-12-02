package com.mss.weather.view.citysettings;

import com.arellomobile.mvp.MvpView;
import com.mss.weather.view.models.CitySettings;

public interface CitySettingsView extends MvpView {
    void showSettings(CitySettings citySettings);
}

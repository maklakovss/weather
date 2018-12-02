package com.mss.weather.view.citysettings;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.mss.weather.view.models.CitySettings;

public interface CitySettingsView extends MvpView {
    void showSettings(@NonNull final CitySettings citySettings);
}

package com.mss.weather.presentation.view.citysettings;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.mss.weather.presentation.view.models.CitySettings;

public interface CitySettingsView extends MvpView {

    void showSettings(@NonNull final CitySettings citySettings);
}

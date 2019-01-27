package com.mss.weather.presentation.view.main;

import android.support.annotation.NonNull;

import java.util.Date;

public interface WeatherFragmentsNavigator {

    void showCurrentWeather();

    void showAddCity();

    void back();

    void hideKeyboard();

    void showDayWeather(@NonNull String cityID, @NonNull Date date);
}

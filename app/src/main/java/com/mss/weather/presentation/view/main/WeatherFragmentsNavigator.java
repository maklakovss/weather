package com.mss.weather.presentation.view.main;

import java.util.Date;

public interface WeatherFragmentsNavigator {

    void showCurrentWeather();

    void showAddCity();

    void back();

    void hideKeyboard();

    void showDayWeather(String cityID, Date date);
}

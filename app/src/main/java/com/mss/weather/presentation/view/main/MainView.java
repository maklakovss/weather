package com.mss.weather.presentation.view.main;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {

    void closeDrawer();

    void showAddCity();

    void showWeather();

    void showCityList();
}

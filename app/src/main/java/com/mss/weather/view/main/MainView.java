package com.mss.weather.view.main;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {

    void showWeather(String cityName);
}

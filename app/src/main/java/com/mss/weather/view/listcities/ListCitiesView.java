package com.mss.weather.view.listcities;

import com.arellomobile.mvp.MvpView;

public interface ListCitiesView extends MvpView {

    void updateList(String[] cities);

    void setCurrentCity(int checkedCity);
}

package com.mss.weather.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.view.listcities.ListCitiesView;
import com.mss.weather.view.models.CitySettings;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class ListCitiesPresenter extends MvpPresenter<ListCitiesView> {
    private List<CitySettings> citySettingsList;

    public ListCitiesPresenter() {
        citySettingsList = new ArrayList<>();
    }
}

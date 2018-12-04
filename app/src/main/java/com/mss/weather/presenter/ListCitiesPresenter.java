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
        citySettingsList.add(new CitySettings("Москва"));
        citySettingsList.add(new CitySettings("Санкт-Петербург"));
        citySettingsList.add(new CitySettings("Тюмень"));
    }

    public void needCities() {
        String[] cities = new String[citySettingsList.size()];
        for (int i = 0; i < citySettingsList.size(); i++) {
            cities[i] = citySettingsList.get(i).getName();
        }
        getViewState().updateList(cities);
    }
}

package com.mss.weather.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.view.listcities.ListCitiesView;
import com.mss.weather.view.models.CitySettings;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class ListCitiesPresenter extends MvpPresenter<ListCitiesView> {

    private List<CitySettings> citySettingsList;
    private int checkedCity;

    private void loadCities() {
        citySettingsList = new ArrayList<>();
        citySettingsList.add(new CitySettings("Москва"));
        citySettingsList.add(new CitySettings("Санкт-Петербург"));
        citySettingsList.add(new CitySettings("Тюмень"));
        checkedCity = 0;
    }

    public void needCities() {
        loadCities();
        getViewState().updateList(listCitiesToStringArray());
        getViewState().setCurrentCity(checkedCity);
    }

    @NonNull
    private String[] listCitiesToStringArray() {
        String[] cities = new String[citySettingsList.size()];
        for (int i = 0; i < citySettingsList.size(); i++) {
            cities[i] = citySettingsList.get(i).getName();
        }
        return cities;
    }

    public void setCheckedCity(int checkedCity) {
        this.checkedCity = checkedCity;
    }
}

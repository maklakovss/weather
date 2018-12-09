package com.mss.weather.presentation.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.di.MyApplication;
import com.mss.weather.domain.WeatherInteractor;
import com.mss.weather.presentation.view.listcities.ListCitiesView;
import com.mss.weather.presentation.view.models.CitySettings;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class ListCitiesPresenter extends MvpPresenter<ListCitiesView> {

    @Inject
    WeatherInteractor weatherInteractor;

    public ListCitiesPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public void needCities() {
        List<CitySettings> citySettingsList = weatherInteractor.getListCities();
        getViewState().updateList(listCitiesToStringArray(citySettingsList));
        for (int i = 0; i < citySettingsList.size(); i++) {
            if (citySettingsList.get(i).getName().equals(weatherInteractor.getCurrentCityName())) {
                getViewState().setCurrentCity(i);
                break;
            }
        }
    }

    public void onClickCity(int checkedCity) {
        weatherInteractor.setCurrentCityName(weatherInteractor.getListCities().get(checkedCity).getName());
        getViewState().showWeather();
    }

    @NonNull
    private String[] listCitiesToStringArray(List<CitySettings> listCities) {
        String[] cities = new String[listCities.size()];
        for (int i = 0; i < listCities.size(); i++) {
            cities[i] = listCities.get(i).getName();
        }
        return cities;
    }

}

package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.di.MyApplication;
import com.mss.weather.domain.WeatherInteractor;
import com.mss.weather.presentation.view.listcities.ListCitiesView;
import com.mss.weather.presentation.view.models.CitySettings;

import java.util.ArrayList;
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
        List<String> cityNamesList = listCitySettingsToListString(weatherInteractor.getListCities());
        getViewState().updateList(cityNamesList);
        for (int i = 0; i < cityNamesList.size(); i++) {
            if (cityNamesList.get(i).equals(weatherInteractor.getCurrentCityName())) {
                getViewState().setCurrentCity(i);
                break;
            }
        }
    }

    private List<String> listCitySettingsToListString(List<CitySettings> listCities) {
        List<String> namesList = new ArrayList<>();
        for (CitySettings listCity : listCities) {
            namesList.add(listCity.getName());
        }
        return namesList;
    }

    public void onClickCity(int checkedCity) {
        weatherInteractor.setCurrentCityName(weatherInteractor.getListCities().get(checkedCity).getName());
        getViewState().showWeather();
    }

    public void onClickAdd() {
        getViewState().showSettings();
    }

    public void onLongClickCity(int i) {
        getViewState().showSettings();
    }
}

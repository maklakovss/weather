package com.mss.weather.presentation.presenter;

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
        List<CitySettings> cityNamesList = weatherInteractor.getListCities();
        getViewState().updateList(cityNamesList);
        for (int i = 0; i < cityNamesList.size(); i++) {
            if (cityNamesList.get(i).equals(weatherInteractor.getCurrentCity())) {
                getViewState().setCurrentCity(i);
                break;
            }
        }
    }

    public void onClickCity(int checkedCity) {
        weatherInteractor.setCurrentCity(weatherInteractor.getListCities().get(checkedCity));
        getViewState().showWeather();
    }

    public void onClickAdd() {
        CitySettings newCity = new CitySettings("");
        weatherInteractor.addCity(newCity);
        weatherInteractor.setCurrentCity(newCity);
        needCities();
        getViewState().showSettings();
    }

    public void onLongClickCity(int i) {
        getViewState().showSettings();
    }
}

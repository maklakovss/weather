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
        weatherInteractor.setOnOnCityUpdated(new WeatherInteractor.OnCityUpdated() {
            @Override
            public void onUpdated(CitySettings currentCity) {
                getViewState().updateCity(weatherInteractor.getListCities().indexOf(currentCity));
            }
        });
    }

    public void needCities() {
        List<CitySettings> cityNamesList = weatherInteractor.getListCities();
        getViewState().updateList(cityNamesList);
        getViewState().setCurrentCity(cityNamesList.indexOf(weatherInteractor.getCurrentCity()));
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

package com.mss.weather.presentation.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.domain.interactor.WeatherInteractor;
import com.mss.weather.domain.models.City;
import com.mss.weather.presentation.view.listcities.ListCitiesView;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class ListCitiesPresenter extends MvpPresenter<ListCitiesView> {

    private final WeatherInteractor weatherInteractor;

    private List<City> cities;

    @Inject
    public ListCitiesPresenter(@NonNull final WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void needCities() {
        if (cities == null)
            cities = weatherInteractor.getListCities();
        getViewState().updateList(cities);
    }

    public void onClickCity(int checkedCity) {
        weatherInteractor.setCurrentCity(weatherInteractor.getListCities().get(checkedCity));
        getViewState().showWeather();
    }

    public void onClickAdd() {
        getViewState().showSelectCity();
    }

    public void deleteCity(int position) {
        weatherInteractor.deleteCity(weatherInteractor.getListCities().get(position));
        getViewState().updateCity(position);
    }
}

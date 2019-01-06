package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.MyApplication;
import com.mss.weather.domain.weather.WeatherInteractor;
import com.mss.weather.presentation.view.listcities.ListCitiesView;

import javax.inject.Inject;

@InjectViewState
public class ListCitiesPresenter extends MvpPresenter<ListCitiesView> {

    @Inject
    WeatherInteractor weatherInteractor;

    public ListCitiesPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public void needCities() {
        getViewState().updateList(weatherInteractor.getListCities());
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

package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.di.MyApplication;
import com.mss.weather.domain.city.models.City;
import com.mss.weather.domain.weather.WeatherInteractor;
import com.mss.weather.presentation.view.listcities.ListCitiesView;

import javax.inject.Inject;

@InjectViewState
public class ListCitiesPresenter extends MvpPresenter<ListCitiesView> {

    @Inject
    WeatherInteractor weatherInteractor;

    public ListCitiesPresenter() {
        MyApplication.getApplicationComponent().inject(this);
        weatherInteractor.setOnOnCityUpdated(new WeatherInteractor.OnCityUpdated() {
            @Override
            public void onUpdated(City currentCity) {
                getViewState().updateCity(weatherInteractor.getListCities().indexOf(currentCity));
            }
        });
    }

    public void needCities() {
        getViewState().updateList(weatherInteractor.getListCities());
    }

    public void onClickCity(int checkedCity) {
        weatherInteractor.setCurrentCity(weatherInteractor.getListCities().get(checkedCity));
        getViewState().showWeather();
    }

    public void onClickAdd() {
        City newCity = new City("");
        weatherInteractor.addCity(newCity);
        weatherInteractor.setCurrentCity(newCity);
        needCities();
        getViewState().showCity();
    }

    public void onLongClickCity(int i) {
        getViewState().showCity();
    }

}

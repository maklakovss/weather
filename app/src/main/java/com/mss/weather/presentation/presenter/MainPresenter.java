package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.domain.interactor.WeatherInteractor;
import com.mss.weather.domain.models.City;
import com.mss.weather.presentation.view.main.MainView;

import javax.inject.Inject;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private WeatherInteractor weatherInteractor;

    @Inject
    public MainPresenter(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void onNavigationItemSelected(int itemId) {
        getViewState().closeDrawer();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showCityList();
        if (weatherInteractor.getListCities().size() == 0) {
            getViewState().showAddCity();
        } else {
            City lastCity = weatherInteractor.getCurrentCity();
            if (lastCity != null) {
                getViewState().showCurrentWeather();
            }
        }
    }

    public void onStart() {
    }

    public void onStop() {

    }
}

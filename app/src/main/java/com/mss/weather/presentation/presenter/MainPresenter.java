package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.MyApplication;
import com.mss.weather.domain.weather.WeatherInteractor;
import com.mss.weather.presentation.view.main.MainView;

import javax.inject.Inject;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    @Inject
    WeatherInteractor weatherInteractor;

    public MainPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public void onNavigationItemSelected(int itemId) {
        getViewState().closeDrawer();
    }

    public void onStart() {
        getViewState().showCityList();
        if (weatherInteractor.getListCities().size() == 0) {
            getViewState().showAddCity();
        }
    }

    public void onStop() {

    }
}
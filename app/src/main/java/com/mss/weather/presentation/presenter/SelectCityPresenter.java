package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.di.MyApplication;
import com.mss.weather.domain.city.models.City;
import com.mss.weather.domain.weather.WeatherInteractor;
import com.mss.weather.presentation.view.selectcity.SelectCityView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SelectCityPresenter extends MvpPresenter<SelectCityView> {

    @Inject
    WeatherInteractor weatherInteractor;

    public SelectCityPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public void searchClicked(String searchTemplate) {
        weatherInteractor.getAutoCompleteLocations(searchTemplate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cities -> getViewState().showCities(cities));
    }

    public City getCurrentCity() {
        return weatherInteractor.getCurrentCity();
    }
}

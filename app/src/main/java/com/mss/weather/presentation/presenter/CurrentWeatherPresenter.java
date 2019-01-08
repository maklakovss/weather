package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.MyApplication;
import com.mss.weather.domain.interactor.WeatherInteractor;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.WeatherInfo;
import com.mss.weather.presentation.view.currentweather.CurrentWeatherView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CurrentWeatherPresenter extends MvpPresenter<CurrentWeatherView> {

    @Inject
    WeatherInteractor weatherInteractor;

    public CurrentWeatherPresenter() {
        MyApplication.getApplicationComponent().inject(this);
        weatherInteractor.setOnCurrentCityChanged(new WeatherInteractor.OnCurrentCityChanged() {
            @Override
            public void onChanged(City currentCityName) {
                needData();
            }
        });
    }

    public void needData() {
        getViewState().showCity(weatherInteractor.getCurrentCity());
        weatherInteractor.getWeatherInfo(weatherInteractor.getCurrentCity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherInfo -> onSuccess(weatherInfo));
    }

    private void onSuccess(WeatherInfo weatherInfo) {
        getViewState().showCurrentWeather(weatherInfo.getWeatherCurrent());
        getViewState().showWeatherList(weatherInfo.getDays());
    }
}

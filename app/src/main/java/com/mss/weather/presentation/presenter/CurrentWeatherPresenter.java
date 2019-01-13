package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.domain.interactor.WeatherInteractor;
import com.mss.weather.domain.models.WeatherInfo;
import com.mss.weather.presentation.view.currentweather.CurrentWeatherView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CurrentWeatherPresenter extends MvpPresenter<CurrentWeatherView> {

    private WeatherInteractor weatherInteractor;
    private WeatherInfo weatherInfo;

    @Inject
    public CurrentWeatherPresenter(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void onCreate() {
        if (weatherInfo == null
                || !weatherInfo.getCityID().equals((weatherInteractor.getCurrentCity().getId()))) {
            updateWeather();
        }
    }

    private void updateWeather() {
        getViewState().showCity(weatherInteractor.getCurrentCity());
        startProgress();
        weatherInteractor.getWeatherInfo(weatherInteractor.getCurrentCity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherInfo -> onSuccess(weatherInfo), e -> stopProgressOnError());
    }

    private void startProgress() {
        getViewState().showProgress(true);
    }

    private void stopProgressOnError() {
        getViewState().showProgress(false);
    }

    private void onSuccess(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
        getViewState().showCurrentWeather(weatherInfo.getWeatherCurrent());
        getViewState().showWeatherList(weatherInfo.getDays());
        getViewState().showProgress(false);
    }
}

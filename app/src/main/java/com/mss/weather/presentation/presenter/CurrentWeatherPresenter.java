package com.mss.weather.presentation.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.domain.interactor.WeatherInteractor;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.presentation.view.currentweather.CurrentWeatherView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CurrentWeatherPresenter extends MvpPresenter<CurrentWeatherView> {

    private final WeatherInteractor weatherInteractor;
    private InfoWeather infoWeather;

    @Inject
    public CurrentWeatherPresenter(@NonNull final WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void onCreate() {
        if (infoWeather == null
                || !infoWeather.getCityID().equals((weatherInteractor.getCurrentCity().getId()))) {
            updateWeather();
        }
    }

    private void updateWeather() {
        getViewState().showCity(weatherInteractor.getCurrentCity());
        showInfoWeather(weatherInteractor.getLocalWeatherInfo(weatherInteractor.getCurrentCity()));
        startProgress();
        weatherInteractor.getWeatherInfo(weatherInteractor.getCurrentCity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherInfo -> onSuccess(weatherInfo), e -> stopProgressOnError(e));
    }

    private void startProgress() {
        getViewState().showProgress(true);
    }

    private void stopProgressOnError(@NonNull final Throwable e) {
        getViewState().showProgress(false);
    }

    private void onSuccess(@NonNull final InfoWeather infoWeather) {
        this.infoWeather = infoWeather;
        showInfoWeather(infoWeather);
        getViewState().showProgress(false);
    }

    private void showInfoWeather(@NonNull final InfoWeather infoWeather) {
        this.infoWeather = infoWeather;
        if (infoWeather != null) {
            getViewState().showCurrentWeather(infoWeather.getCurrentWeather());
            getViewState().showWeatherList(infoWeather.getDays());
        }
    }

    private void clearInfoWeather() {
        getViewState().clearCurrentWeather();
        getViewState().clearWeatherList();
    }

    public void onClick(int position) {
        getViewState().showDay(infoWeather.getCityID(), infoWeather.getDays().get(position).getDate());
    }
}

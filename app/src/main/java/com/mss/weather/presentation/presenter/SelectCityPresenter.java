package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.domain.interactor.WeatherInteractor;
import com.mss.weather.domain.models.City;
import com.mss.weather.presentation.view.selectcity.SelectCityView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SelectCityPresenter extends MvpPresenter<SelectCityView> {

    private WeatherInteractor weatherInteractor;

    private List<City> autoCompleteCities;

    @Inject
    public SelectCityPresenter(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void searchClicked(String searchTemplate) {
        clearAndStartProgress();
        weatherInteractor.getAutoCompleteLocations(searchTemplate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cities -> onSuccess(cities),
                        e -> stopProgressOnError());
    }

    public void onClickCity(int position) {
        if (autoCompleteCities != null && autoCompleteCities.size() > position) {
            weatherInteractor.addCity(autoCompleteCities.get(position));
            getViewState().back();
        }
    }

    public void locationClick() {
        clearAndStartProgress();
        weatherInteractor.getPosition()
                .subscribe(
                        position -> {
                            weatherInteractor.getLocationsByPosition(position)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(cities -> onSuccess(cities)
                                            , e -> stopProgressOnError());
                        },
                        e -> {
                            stopProgressOnError();
                        });
    }

    private void clearAndStartProgress() {
        autoCompleteCities = new ArrayList<>();
        getViewState().showCities(autoCompleteCities);
        getViewState().showProgress(true);
    }

    private void onSuccess(List<City> cities) {
        autoCompleteCities = cities;
        getViewState().showCities(cities);
        getViewState().showProgress(false);
    }

    private void stopProgressOnError() {
        autoCompleteCities = null;
        getViewState().showProgress(false);
    }

}

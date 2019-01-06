package com.mss.weather.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.MyApplication;
import com.mss.weather.domain.city.models.City;
import com.mss.weather.domain.weather.WeatherInteractor;
import com.mss.weather.presentation.view.selectcity.SelectCityView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SelectCityPresenter extends MvpPresenter<SelectCityView> {

    @Inject
    WeatherInteractor weatherInteractor;

    List<City> autoCompleteCities;

    public SelectCityPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public void searchClicked(String searchTemplate) {
        getViewState().showProgress(true);
        weatherInteractor.getAutoCompleteLocations(searchTemplate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cities -> {
                            autoCompleteCities = cities;
                            getViewState().showCities(cities);
                            getViewState().showProgress(false);
                        },
                        e -> {
                            autoCompleteCities = null;
                            getViewState().showProgress(false);
                        });
    }

    public void onClickCity(int position) {
        if (autoCompleteCities != null && autoCompleteCities.size() > position) {
            weatherInteractor.addCity(autoCompleteCities.get(position));
            getViewState().back();
        }
    }
}

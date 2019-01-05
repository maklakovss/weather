package com.mss.weather.presentation.view.selectcity;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.mss.weather.domain.city.models.City;

import java.util.List;

public interface SelectCityView extends MvpView {

    void showCities(@NonNull List<City> cities);

    void showProgress(boolean visible);

}

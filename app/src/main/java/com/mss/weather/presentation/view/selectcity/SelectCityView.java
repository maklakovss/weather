package com.mss.weather.presentation.view.selectcity;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.mss.weather.domain.city.models.City;

public interface SelectCityView extends MvpView {

    void showCity(@NonNull final City citySettings);
}

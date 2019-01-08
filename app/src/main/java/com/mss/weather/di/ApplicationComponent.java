package com.mss.weather.di;

import com.mss.weather.presentation.presenter.CurrentWeatherPresenter;
import com.mss.weather.presentation.presenter.ListCitiesPresenter;
import com.mss.weather.presentation.presenter.MainPresenter;
import com.mss.weather.presentation.presenter.SelectCityPresenter;
import com.mss.weather.presentation.view.currentweather.CurrentWeatherFragment;
import com.mss.weather.presentation.view.listcities.ListCitiesFragment;
import com.mss.weather.presentation.view.selectcity.SelectCityFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    //presenters

    void inject(ListCitiesPresenter settingsPresenter);

    void inject(CurrentWeatherPresenter currentWeatherPresenter);

    void inject(SelectCityPresenter selectCityPresenter);

    void inject(MainPresenter mainPresenter);


    //fragments

    void inject(SelectCityFragment addCityFragment);

    void inject(CurrentWeatherFragment currentWeatherFragment);

    void inject(ListCitiesFragment listCitiesFragment);
}

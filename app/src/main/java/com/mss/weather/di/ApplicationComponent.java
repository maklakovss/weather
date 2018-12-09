package com.mss.weather.di;

import com.mss.weather.presentation.presenter.CitySettingsPresenter;
import com.mss.weather.presentation.presenter.CityWeatherPresenter;
import com.mss.weather.presentation.presenter.ListCitiesPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    //presenters
    void inject(ListCitiesPresenter settingsPresenter);

    void inject(CityWeatherPresenter cityWeatherPresenter);

    void inject(CitySettingsPresenter citySettingsPresenter);
}

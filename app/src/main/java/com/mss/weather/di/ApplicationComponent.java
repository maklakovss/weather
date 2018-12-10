package com.mss.weather.di;

import com.mss.weather.presentation.presenter.CitySettingsPresenter;
import com.mss.weather.presentation.presenter.CityWeatherPresenter;
import com.mss.weather.presentation.presenter.ListCitiesPresenter;
import com.mss.weather.presentation.view.citysettings.CitySettingsFragment;
import com.mss.weather.presentation.view.cityweather.CityWeatherFragment;
import com.mss.weather.presentation.view.listcities.ListCitiesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    //presenters
    void inject(ListCitiesPresenter settingsPresenter);

    void inject(CityWeatherPresenter cityWeatherPresenter);

    void inject(CitySettingsPresenter citySettingsPresenter);

    //fragments
    void inject(CitySettingsFragment citySettingsFragment);

    void inject(CityWeatherFragment cityWeatherFragment);

    void inject(ListCitiesFragment listCitiesFragment);
}

package com.mss.weather.di;

import com.mss.weather.domain.WeatherInteractor;
import com.mss.weather.domain.WeatherInteractorImpl;
import com.mss.weather.presentation.presenter.CitySettingsPresenter;
import com.mss.weather.presentation.presenter.CityWeatherPresenter;
import com.mss.weather.presentation.presenter.ListCitiesPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Singleton
    @Provides
    public WeatherInteractor provideWeatherInteractor() {
        return new WeatherInteractorImpl();
    }

    @Singleton
    @Provides
    public CitySettingsPresenter provideCitySettingsPresenter() {
        return new CitySettingsPresenter();
    }

    @Singleton
    @Provides
    public CityWeatherPresenter provideCityWeatherPresenter() {
        return new CityWeatherPresenter();
    }

    @Singleton
    @Provides
    public ListCitiesPresenter provideListCitiesPresenter() {
        return new ListCitiesPresenter();
    }

}

package com.mss.weather.di;

import com.mss.weather.domain.weather.WeatherInteractor;
import com.mss.weather.domain.weather.WeatherInteractorImpl;
import com.mss.weather.presentation.presenter.CityWeatherPresenter;
import com.mss.weather.presentation.presenter.ListCitiesPresenter;
import com.mss.weather.presentation.presenter.SelectCityPresenter;

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
    public SelectCityPresenter provideCitySettingsPresenter() {
        return new SelectCityPresenter();
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

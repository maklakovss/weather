package com.mss.weather.di;

import android.content.Context;

import com.mss.weather.data.repositories.WeatherRepositoryImpl;
import com.mss.weather.data.sensors.SensorsRepositoryImpl;
import com.mss.weather.domain.interactor.WeatherInteractor;
import com.mss.weather.domain.interactor.WeatherInteractorImpl;
import com.mss.weather.domain.sensors.SensorsRepository;
import com.mss.weather.domain.weather.WeatherRepository;
import com.mss.weather.presentation.presenter.CityWeatherPresenter;
import com.mss.weather.presentation.presenter.ListCitiesPresenter;
import com.mss.weather.presentation.presenter.SelectCityPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    final private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public SensorsRepository provideSensorsRepository() {
        return new SensorsRepositoryImpl(context);
    }

    @Singleton
    @Provides
    public WeatherInteractor provideWeatherInteractor(WeatherRepository weatherRepository, SensorsRepository sensorsRepository) {
        return new WeatherInteractorImpl(weatherRepository, sensorsRepository);
    }

    @Singleton
    @Provides
    public SelectCityPresenter provideSelectCityPresenter() {
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

    @Singleton
    @Provides
    public WeatherRepository provideWeatherRepository() {
        return new WeatherRepositoryImpl();
    }

}

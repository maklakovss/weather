package com.mss.weather.di;

import android.content.Context;

import com.mss.weather.domain.PreferencesRepository;
import com.mss.weather.domain.PreferencesRepositoryImpl;
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

    private Context appContext;

    public ApplicationModule(Context context) {
        appContext = context;
    }

    @Singleton
    @Provides
    public WeatherInteractor provideWeatherInteractor(PreferencesRepository preferencesRepository) {
        return new WeatherInteractorImpl(preferencesRepository);
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

    @Singleton
    @Provides
    public Context provideAppContext() {
        return appContext;
    }

    @Singleton
    @Provides
    public PreferencesRepository providePreferencesRepository(Context context) {
        return new PreferencesRepositoryImpl(context);
    }

}

package com.mss.weather.di;

import com.mss.weather.domain.WeatherInteractor;
import com.mss.weather.domain.WeatherInteractorImpl;

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

}

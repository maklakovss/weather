package com.mss.weather.di;

import android.content.Context;

import com.mss.weather.data.db.LocalRepositoryImpl;
import com.mss.weather.data.network.NetworkRepositoryImpl;
import com.mss.weather.data.sensors.SensorsRepositoryImpl;
import com.mss.weather.domain.LocalRepository;
import com.mss.weather.domain.NetworkRepository;
import com.mss.weather.domain.SensorsRepository;
import com.mss.weather.domain.interactor.WeatherInteractor;
import com.mss.weather.domain.interactor.WeatherInteractorImpl;
import com.mss.weather.presentation.presenter.CurrentWeatherPresenter;
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
    public WeatherInteractor provideWeatherInteractor(NetworkRepository networkRepository,
                                                      LocalRepository localRepository,
                                                      SensorsRepository sensorsRepository) {
        return new WeatherInteractorImpl(networkRepository, localRepository, sensorsRepository);
    }

    @Singleton
    @Provides
    public SelectCityPresenter provideSelectCityPresenter() {
        return new SelectCityPresenter();
    }

    @Singleton
    @Provides
    public CurrentWeatherPresenter provideCityWeatherPresenter() {
        return new CurrentWeatherPresenter();
    }

    @Singleton
    @Provides
    public ListCitiesPresenter provideListCitiesPresenter() {
        return new ListCitiesPresenter();
    }

    @Singleton
    @Provides
    public NetworkRepository provideNetworkRepository() {
        return new NetworkRepositoryImpl();
    }

    @Singleton
    @Provides
    public LocalRepository provideLocalRepository() {
        return new LocalRepositoryImpl();
    }

}

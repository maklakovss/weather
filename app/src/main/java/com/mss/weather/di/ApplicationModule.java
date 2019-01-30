package com.mss.weather.di;

import com.mss.weather.data.db.repositories.CityLocalRepositoryImpl;
import com.mss.weather.data.db.repositories.CurrentWeatherLocalRepositoryImpl;
import com.mss.weather.data.db.repositories.DayWeatherLocalRepositoryImpl;
import com.mss.weather.data.db.repositories.HourWeatherLocalRepositoryImpl;
import com.mss.weather.data.db.repositories.InfoWeatherLocalRepositoryImpl;
import com.mss.weather.data.network.NetworkRepositoryImpl;
import com.mss.weather.data.sensors.SensorsRepositoryImpl;
import com.mss.weather.domain.interactor.WeatherInteractor;
import com.mss.weather.domain.interactor.WeatherInteractorImpl;
import com.mss.weather.domain.repositories.CityLocalRepository;
import com.mss.weather.domain.repositories.CurrentWeatherLocalRepository;
import com.mss.weather.domain.repositories.DayWeatherLocalRepository;
import com.mss.weather.domain.repositories.HourWeatherLocalRepository;
import com.mss.weather.domain.repositories.InfoWeatherLocalRepository;
import com.mss.weather.domain.repositories.NetworkRepository;
import com.mss.weather.domain.repositories.SensorsRepository;
import com.mss.weather.presentation.presenter.CurrentWeatherPresenter;
import com.mss.weather.presentation.presenter.DayWeatherPresenter;
import com.mss.weather.presentation.presenter.ListCitiesPresenter;
import com.mss.weather.presentation.presenter.MainPresenter;
import com.mss.weather.presentation.presenter.SelectCityPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Singleton
    @Provides
    public WeatherInteractor provideWeatherInteractor(NetworkRepository networkRepository,
                                                      CityLocalRepository cityLocalRepository,
                                                      InfoWeatherLocalRepository infoWeatherLocalRepository,
                                                      CurrentWeatherLocalRepository currentWeatherLocalRepository,
                                                      DayWeatherLocalRepository dayWeatherLocalRepository,
                                                      HourWeatherLocalRepository hourWeatherLocalRepository,
                                                      SensorsRepository sensorsRepository) {
        return new WeatherInteractorImpl(networkRepository,
                cityLocalRepository,
                infoWeatherLocalRepository,
                currentWeatherLocalRepository,
                dayWeatherLocalRepository,
                hourWeatherLocalRepository, sensorsRepository);
    }

    @Singleton
    @Provides
    public CurrentWeatherPresenter provideCityWeatherPresenter(WeatherInteractor weatherInteractor) {
        return new CurrentWeatherPresenter(weatherInteractor);
    }

    @Singleton
    @Provides
    public DayWeatherPresenter provideDayWeatherPresenter(WeatherInteractor weatherInteractor) {
        return new DayWeatherPresenter(weatherInteractor);
    }

    @Singleton
    @Provides
    public ListCitiesPresenter provideListCitiesPresenter(WeatherInteractor weatherInteractor) {
        return new ListCitiesPresenter(weatherInteractor);
    }

    @Singleton
    @Provides
    public MainPresenter provideMainPresenter(WeatherInteractor weatherInteractor) {
        return new MainPresenter(weatherInteractor);
    }

    @Singleton
    @Provides
    public SelectCityPresenter provideSelectCityPresenter(WeatherInteractor weatherInteractor) {
        return new SelectCityPresenter(weatherInteractor);
    }

    @Singleton
    @Provides
    public SensorsRepository provideSensorsRepository() {
        return new SensorsRepositoryImpl();
    }

    @Singleton
    @Provides
    public NetworkRepository provideNetworkRepository() {
        return new NetworkRepositoryImpl();
    }

    @Singleton
    @Provides
    public CityLocalRepository provideCityLocalLocalRepository() {
        return new CityLocalRepositoryImpl();
    }

    @Singleton
    @Provides
    public InfoWeatherLocalRepository provideInfoWeatherLocalRepository() {
        return new InfoWeatherLocalRepositoryImpl();
    }

    @Singleton
    @Provides
    public CurrentWeatherLocalRepository provideCurrentWeatherLocalRepository() {
        return new CurrentWeatherLocalRepositoryImpl();
    }

    @Singleton
    @Provides
    public DayWeatherLocalRepository provideDayWeatherLocalRepository() {
        return new DayWeatherLocalRepositoryImpl();
    }

    @Singleton
    @Provides
    public HourWeatherLocalRepository provideHourWeatherLocalRepository() {
        return new HourWeatherLocalRepositoryImpl();
    }
}

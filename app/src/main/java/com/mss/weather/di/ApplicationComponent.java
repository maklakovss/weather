package com.mss.weather.di;

import com.mss.weather.presentation.view.currentweather.CurrentWeatherFragment;
import com.mss.weather.presentation.view.dayweather.DayWeatherFragment;
import com.mss.weather.presentation.view.history.HistoryFragment;
import com.mss.weather.presentation.view.listcities.ListCitiesFragment;
import com.mss.weather.presentation.view.main.MainActivity;
import com.mss.weather.presentation.view.selectcity.SelectCityFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    //fragments

    void inject(SelectCityFragment addCityFragment);

    void inject(CurrentWeatherFragment currentWeatherFragment);

    void inject(ListCitiesFragment listCitiesFragment);

    void inject(DayWeatherFragment dayWeatherFragment);

    void inject(HistoryFragment historyFragment);


    //activity

    void inject(MainActivity mainActivity);
}

package com.mss.weather.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mss.weather.R;
import com.mss.weather.presenter.MainActivityPresenter;
import com.mss.weather.view.cityweather.CityWeatherFragment;
import com.mss.weather.view.listcities.ListCitiesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements WeatherFragmentsNavigator, MainView {

    @InjectPresenter
    MainActivityPresenter mainActivityPresenter;

    @BindView(R.id.flMain)
    FrameLayout flMain;

    @Nullable
    @BindView(R.id.flDetails)
    FrameLayout flDetails;

    ListCitiesFragment listCitiesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        listCitiesFragment = (ListCitiesFragment) getSupportFragmentManager()
                .findFragmentByTag(getString(R.string.list_fragment_tag));
        if (listCitiesFragment == null) {
            listCitiesFragment = new ListCitiesFragment();
            listCitiesFragment.setRetainInstance(true);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flMain, listCitiesFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void setCurrentCity(String cityName, boolean clicked) {
        mainActivityPresenter.setCurrentCity(cityName);
        CityWeatherFragment cityWeatherFragment = getWeatherFragment(clicked);
        if (cityWeatherFragment != null) {
            cityWeatherFragment.changeCity(cityName);
        }
    }

    @Override
    public void addCity() {
//TODO
    }

    private CityWeatherFragment getWeatherFragment(boolean clicked) {
        CityWeatherFragment cityWeatherFragment = (CityWeatherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.flDetails);
        if (cityWeatherFragment == null) {
            if (flDetails == null && clicked) {
                cityWeatherFragment = new CityWeatherFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flMain, cityWeatherFragment);  // замена фрагмента
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack("");
                ft.commit();
            } else if (flDetails != null) {
                cityWeatherFragment = new CityWeatherFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flDetails, cityWeatherFragment);  // замена фрагмента
                ft.commit();
            }
        }
        return cityWeatherFragment;
    }


    @Override
    public void showWeather(String cityName) {
        CityWeatherFragment cityWeatherFragment = getWeatherFragment(false);
        if (cityWeatherFragment != null) {
            cityWeatherFragment.changeCity(cityName);
        }
    }
}

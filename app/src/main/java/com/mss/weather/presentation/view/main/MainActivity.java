package com.mss.weather.presentation.view.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.mss.weather.R;
import com.mss.weather.presentation.view.citysettings.CitySettingsFragment;
import com.mss.weather.presentation.view.cityweather.CityWeatherFragment;
import com.mss.weather.presentation.view.listcities.ListCitiesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements WeatherFragmentsNavigator {

    private static final String CITY_LIST_TAG = "CITY_LIST_TAG";
    private static final String WEATHER_TAG = "WEATHER_TAG";
    private static final String SETTINGS_TAG = "SETTINGS_TAG";

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
        initListCitiesFragment();
        if (flDetails != null) {
            createWeatherFragmentInDetailFrame();
        } else {
            removeCityWeatherFragment();
            removeCitySettingsFragment();
        }
    }

    @Override
    public void showCitySettings() {
        CitySettingsFragment citySettingsFragment = getCitySettingsFragment();
        if (citySettingsFragment == null) {
            if (flDetails == null) {
                citySettingsFragment = createCitySettingsFragmentInMainFrame();
            } else {
                citySettingsFragment = createCitySettingsFragmentInDetailFrame();
            }
        }
    }

    @Override
    public void showWeather() {
        CityWeatherFragment cityWeatherFragment = getCityWeatherFragment();
        if (cityWeatherFragment == null) {
            if (flDetails == null) {
                cityWeatherFragment = createWeatherFragmentInMainFrame();
            } else {
                cityWeatherFragment = createWeatherFragmentInDetailFrame();
            }
        }
    }

    private void initListCitiesFragment() {
        listCitiesFragment = (ListCitiesFragment) getSupportFragmentManager()
                .findFragmentByTag(getString(R.string.list_fragment_tag));
        if (listCitiesFragment == null) {
            createListCitiesFragmentInMainFrame();
        }
    }

    private void createListCitiesFragmentInMainFrame() {
        listCitiesFragment = ListCitiesFragment.newInstance();
        listCitiesFragment.setRetainInstance(true);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flMain, listCitiesFragment, CITY_LIST_TAG);
        fragmentTransaction.commit();
    }

    private CityWeatherFragment getCityWeatherFragment() {
        return (CityWeatherFragment) getSupportFragmentManager()
                .findFragmentByTag(WEATHER_TAG);
    }

    @NonNull
    private CityWeatherFragment createWeatherFragmentInDetailFrame() {
        final CityWeatherFragment cityWeatherFragment = CityWeatherFragment.newInstance();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flDetails, cityWeatherFragment, WEATHER_TAG);
        ft.commit();
        return cityWeatherFragment;
    }

    @NonNull
    private CityWeatherFragment createWeatherFragmentInMainFrame() {
        final CityWeatherFragment cityWeatherFragment = CityWeatherFragment.newInstance();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain, cityWeatherFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("");
        ft.commit();
        return cityWeatherFragment;
    }

    private void removeCityWeatherFragment() {
        final CityWeatherFragment cityWeatherFragment = getCityWeatherFragment();
        if (cityWeatherFragment != null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(cityWeatherFragment);
            ft.commit();
        }
    }

    private CitySettingsFragment getCitySettingsFragment() {
        return (CitySettingsFragment) getSupportFragmentManager()
                .findFragmentByTag(SETTINGS_TAG);
    }

    private CitySettingsFragment createCitySettingsFragmentInMainFrame() {
        final CitySettingsFragment citySettingsFragment = CitySettingsFragment.newInstance();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain, citySettingsFragment, SETTINGS_TAG);
        ft.commit();
        return citySettingsFragment;
    }

    private CitySettingsFragment createCitySettingsFragmentInDetailFrame() {
        final CitySettingsFragment citySettingsFragment = CitySettingsFragment.newInstance();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flDetails, citySettingsFragment, SETTINGS_TAG);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("");
        ft.commit();
        return citySettingsFragment;
    }

    private void removeCitySettingsFragment() {
        final CitySettingsFragment citySettingsFragment = getCitySettingsFragment();
        if (citySettingsFragment != null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(citySettingsFragment);
            ft.commit();
        }
    }
}

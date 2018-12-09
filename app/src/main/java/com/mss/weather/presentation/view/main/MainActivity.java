package com.mss.weather.presentation.view.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.mss.weather.R;
import com.mss.weather.presentation.view.cityweather.CityWeatherFragment;
import com.mss.weather.presentation.view.listcities.ListCitiesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements WeatherFragmentsNavigator {

    private static final String CITY_LIST_TAG = "CITY_LIST_TAG";
    private static final String WEATHER_TAG = "WEATHER_TAG";

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
            final CityWeatherFragment cityWeatherFragment = getCityWeatherFragment();
            if (cityWeatherFragment != null) {
                final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.remove(cityWeatherFragment);
                ft.commit();
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
        listCitiesFragment = new ListCitiesFragment();
        listCitiesFragment.setRetainInstance(true);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flMain, listCitiesFragment, CITY_LIST_TAG);
        fragmentTransaction.commit();
    }

    private CityWeatherFragment getCityWeatherFragment() {
        CityWeatherFragment cityWeatherFragment = (CityWeatherFragment) getSupportFragmentManager()
                .findFragmentByTag(WEATHER_TAG);
        return cityWeatherFragment;
    }

    @Override
    public void showAddCity() {
//TODO
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

    @NonNull
    private CityWeatherFragment createWeatherFragmentInDetailFrame() {
        final CityWeatherFragment cityWeatherFragment = new CityWeatherFragment();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flDetails, cityWeatherFragment, WEATHER_TAG);
        ft.commit();
        return cityWeatherFragment;
    }

    @NonNull
    private CityWeatherFragment createWeatherFragmentInMainFrame() {
        final CityWeatherFragment cityWeatherFragment = new CityWeatherFragment();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain, cityWeatherFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("");
        ft.commit();
        return cityWeatherFragment;
    }

}

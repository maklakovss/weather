package com.mss.weather.presentation.view.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.mss.weather.MyIntentService;
import com.mss.weather.R;
import com.mss.weather.presentation.view.citysettings.CitySettingsFragment;
import com.mss.weather.presentation.view.cityweather.CityWeatherFragment;
import com.mss.weather.presentation.view.listcities.ListCitiesFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements WeatherFragmentsNavigator, NavigationView.OnNavigationItemSelectedListener {

    private static final String CITY_LIST_TAG = "CITY_LIST_TAG";
    private static final String WEATHER_TAG = "WEATHER_TAG";
    private static final String SETTINGS_TAG = "SETTINGS_TAG";

    @BindView(R.id.flMain)
    FrameLayout flMain;
    @Nullable
    @BindView(R.id.flDetails)
    FrameLayout flDetails;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    ListCitiesFragment listCitiesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

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
        ft.addToBackStack("");
        ft.commit();
        return citySettingsFragment;
    }

    private CitySettingsFragment createCitySettingsFragmentInDetailFrame() {
        final CitySettingsFragment citySettingsFragment = CitySettingsFragment.newInstance();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flDetails, citySettingsFragment, SETTINGS_TAG);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class WeatherServiceResultReceiver<T> extends ResultReceiver {

        public WeatherServiceResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultCode == MyIntentService.RESULT_SUCCESS) {
                ArrayList listCities = resultData.getStringArrayList(MyIntentService.RESULT_CITIES_NAME_LIST_KEY);
            }
            super.onReceiveResult(resultCode, resultData);
        }
    }

}

package com.mss.weather.presentation.view.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mss.weather.MyApplication;
import com.mss.weather.R;
import com.mss.weather.presentation.presenter.MainPresenter;
import com.mss.weather.presentation.view.currentweather.CurrentWeatherFragment;
import com.mss.weather.presentation.view.listcities.ListCitiesFragment;
import com.mss.weather.presentation.view.selectcity.SelectCityFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView, WeatherFragmentsNavigator, NavigationView.OnNavigationItemSelectedListener {

    private static final String CITY_LIST_TAG = "CITY_LIST_TAG";
    private static final String WEATHER_TAG = "WEATHER_TAG";
    private static final String SETTINGS_TAG = "SETTINGS_TAG";

    @Inject
    @InjectPresenter
    MainPresenter mainPresenter;

    @BindView(R.id.flMain)
    FrameLayout flMain;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    ListCitiesFragment listCitiesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyApplication.getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return mainPresenter;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.onStart();
    }

    @Override
    protected void onStop() {
        mainPresenter.onStop();
        super.onStop();
    }

    @Override
    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showAddCity() {
        SelectCityFragment addCityFragment = getAddCityFragment();
        if (addCityFragment == null)
            addCityFragment = createAddCityFragment();
    }

    @Override
    public void showWeather() {
        CurrentWeatherFragment currentWeatherFragment = getCityWeatherFragment();
        if (currentWeatherFragment == null)
            currentWeatherFragment = createCityWeatherFragment();
    }

    @Override
    public void showCityList() {
        listCitiesFragment = (ListCitiesFragment) getSupportFragmentManager()
                .findFragmentByTag(getString(R.string.list_fragment_tag));
        if (listCitiesFragment == null)
            createListCitiesFragment();
    }

    private void createListCitiesFragment() {
        listCitiesFragment = ListCitiesFragment.newInstance();
        listCitiesFragment.setRetainInstance(true);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flMain, listCitiesFragment, CITY_LIST_TAG);
        fragmentTransaction.commit();
    }

    private CurrentWeatherFragment getCityWeatherFragment() {
        return (CurrentWeatherFragment) getSupportFragmentManager()
                .findFragmentByTag(WEATHER_TAG);
    }

    @NonNull
    private CurrentWeatherFragment createCityWeatherFragment() {
        final CurrentWeatherFragment currentWeatherFragment = CurrentWeatherFragment.newInstance();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain, currentWeatherFragment);
        ft.addToBackStack("");
        ft.commit();
        return currentWeatherFragment;
    }

    private SelectCityFragment getAddCityFragment() {
        return (SelectCityFragment) getSupportFragmentManager()
                .findFragmentByTag(SETTINGS_TAG);
    }

    private SelectCityFragment createAddCityFragment() {
        final SelectCityFragment addCityFragment = SelectCityFragment.newInstance();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain, addCityFragment, SETTINGS_TAG);
        ft.addToBackStack("");
        ft.commit();
        return addCityFragment;
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
        mainPresenter.onNavigationItemSelected(menuItem.getItemId());
        return true;
    }

    @Override
    public void back() {
        getSupportFragmentManager().popBackStack();
    }

    public void hideKeyboard() {
        final View view = getCurrentFocus();
        if (view != null) {
            final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } else {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
    }
}

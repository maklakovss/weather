package com.mss.weather.presentation.view.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import com.mss.weather.presentation.view.dayweather.DayWeatherFragment;
import com.mss.weather.presentation.view.listcities.ListCitiesFragment;

import java.util.Date;

import javax.inject.Inject;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView, WeatherFragmentsNavigator, NavigationView.OnNavigationItemSelectedListener {

    private static final String CITY_LIST_TAG = "CITY_LIST_TAG";
    private static final String WEATHER_TAG = "WEATHER_TAG";
    private static final String DAY_TAG = "DAY_TAG";
    private static final String SETTINGS_TAG = "SETTINGS_TAG";

    @Inject
    @InjectPresenter
    MainPresenter mainPresenter;

    NavController navController;

    @BindView(R.id.flMain)
    FrameLayout flMain;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private ListCitiesFragment listCitiesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyApplication.getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        ButterKnife.bind(this);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        setSupportActionBar(toolbar);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
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
    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showAddCity() {
        navController.navigate(R.id.action_listCitiesFragment_to_selectCityFragment);
    }

    @Override
    public void showCurrentWeather() {
        navController.navigate(R.id.action_listCitiesFragment_to_currentWeatherFragment);
    }

    @Override
    public void showDayWeather(String cityID, Date date) {
        final Bundle bundle = new Bundle();
        bundle.putLong(DayWeatherFragment.DATE_KEY, date.getTime());
        bundle.putString(DayWeatherFragment.CITY_ID_KEY, cityID);
        navController.navigate(R.id.action_currentWeatherFragment_to_dayWeatherFragment, bundle);
    }

    @Override
    public void showHistory(String cityID) {
        final Bundle bundle = new Bundle();
        bundle.putString(DayWeatherFragment.CITY_ID_KEY, cityID);
        navController.navigate(R.id.action_currentWeatherFragment_to_historyFragment, bundle);
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

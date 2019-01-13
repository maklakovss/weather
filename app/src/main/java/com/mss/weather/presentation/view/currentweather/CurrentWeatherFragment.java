package com.mss.weather.presentation.view.currentweather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mss.weather.MyApplication;
import com.mss.weather.R;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.WeatherCurrent;
import com.mss.weather.domain.models.WeatherDay;
import com.mss.weather.presentation.presenter.CurrentWeatherPresenter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CurrentWeatherFragment extends MvpAppCompatFragment implements CurrentWeatherView {

    @Inject
    @InjectPresenter
    CurrentWeatherPresenter currentWeatherPresenter;

    @BindView(R.id.tvCityName)
    TextView tvCityName;
    @BindView(R.id.tvSunrise)
    TextView tvSunrise;
    @BindView(R.id.tvSunset)
    TextView tvSunset;
    @BindView(R.id.tvCloudPercent)
    TextView tvCloudPercent;
    @BindView(R.id.tvCloudDescription)
    TextView tvCloudDescription;
    @BindView(R.id.llCloud)
    LinearLayout llCloud;
    @BindView(R.id.tvTemp)
    TextView tvTemp;
    @BindView(R.id.tvTempMin)
    TextView tvTempMin;
    @BindView(R.id.tvTempMax)
    TextView tvTempMax;
    @BindView(R.id.llTempRange)
    LinearLayout llTempRange;
    @BindView(R.id.tvHumidity)
    TextView tvHumidity;
    @BindView(R.id.llHumidity)
    LinearLayout llHumidity;
    @BindView(R.id.tvPressure)
    TextView tvPressure;
    @BindView(R.id.llPressure)
    LinearLayout llPressure;
    @BindView(R.id.tvWind)
    TextView tvWind;
    @BindView(R.id.tvWindDeg)
    TextView tvWindDeg;
    @BindView(R.id.llWind)
    LinearLayout llWind;
    @BindView(R.id.tvRainfall)
    TextView tvRainfall;
    @BindView(R.id.llRainfall)
    LinearLayout llRainfall;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.rvWeatherList)
    RecyclerView rvWeatherList;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private Unbinder binder;

    public static CurrentWeatherFragment newInstance() {
        return new CurrentWeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setRetainInstance(true);
        View layout = inflater.inflate(R.layout.fragment_weather, container, false);
        binder = ButterKnife.bind(this, layout);
        rvWeatherList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvWeatherList.setLayoutManager(linearLayoutManager);
        currentWeatherPresenter.onCreate();
        return layout;
    }

    @Override
    public void showCity(@NonNull final City city) {
        tvCityName.setText(city.getAreaName());
    }

    @Override
    public void showCurrentWeather(@NonNull final WeatherCurrent weatherCurrent) {

        final SimpleDateFormat formatterDateTime = new SimpleDateFormat("dd.MM.YYYY HH:mm", Locale.getDefault());
        tvDate.setText(formatterDateTime.format(weatherCurrent.getDate()));

        final SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm", Locale.getDefault());
//        tvSunrise.setText(formatterTime.format(weatherCurrent.getSunrise()));
//        tvSunset.setText(formatterTime.format(weatherCurrent.getSunset()));

        tvTemp.setText(String.valueOf((int) weatherCurrent.getTempC()));
//        tvTempMin.setText(String.valueOf((int) weatherCurrent.getTempMin()));
//        tvTempMax.setText(String.valueOf((int) weatherCurrent.getTempMax()));
        tvCloudPercent.setText(String.valueOf(weatherCurrent.getCloudcover()));
        tvCloudDescription.setText(weatherCurrent.getWeatherDescLocalLanguage());
        tvHumidity.setText(String.valueOf(weatherCurrent.getHumidity()));
        tvPressure.setText(String.valueOf(weatherCurrent.getPressure()));
        tvWind.setText(String.valueOf(weatherCurrent.getWindspeedKmph()));
        tvWindDeg.setText(String.valueOf(weatherCurrent.getWinddirDegree()));
//        if (weatherCurrent.getSnow() > 0) {
//            tvRainfall.setText(String.valueOf(weatherCurrent.getSnow()));
//        } else if (weatherCurrent.getRain() > 0) {
//            tvRainfall.setText(String.valueOf(weatherCurrent.getRain()));
//        }
    }

    @Override
    public void showWeatherList(List<WeatherDay> weatherDays) {
        rvWeatherList.setAdapter(new WeatherListAdapter(weatherDays));
    }

    @Override
    public void showProgress(boolean visible) {
        if (visible)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        binder.unbind();
        super.onDestroyView();
    }

    @ProvidePresenter
    public CurrentWeatherPresenter providePresenter() {
        if (currentWeatherPresenter == null)
            MyApplication.getApplicationComponent().inject(this);
        return currentWeatherPresenter;
    }
}

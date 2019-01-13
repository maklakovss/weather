package com.mss.weather.presentation.view.currentweather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mss.weather.MyApplication;
import com.mss.weather.R;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.CurrentWeather;
import com.mss.weather.domain.models.DayWeather;
import com.mss.weather.presentation.presenter.CurrentWeatherPresenter;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CurrentWeatherFragment extends MvpAppCompatFragment implements CurrentWeatherView {
    private static final SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.YYYY", Locale.getDefault());
    private static final SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm", Locale.getDefault());

    @Inject
    @InjectPresenter
    CurrentWeatherPresenter currentWeatherPresenter;

    @BindView(R.id.tvCityName)
    TextView tvCityName;
    @BindView(R.id.tvCloudPercent)
    TextView tvCloudPercent;
    @BindView(R.id.tvCloudDescription)
    TextView tvCloudDescription;
    @BindView(R.id.tvTemp)
    TextView tvTemp;
    @BindView(R.id.tvFeelsLikeC)
    TextView tvFeelsLikeC;
    @BindView(R.id.tvHumidity)
    TextView tvHumidity;
    @BindView(R.id.tvPressure)
    TextView tvPressure;
    @BindView(R.id.tvWind)
    TextView tvWind;
    @BindView(R.id.tvWindDeg)
    TextView tvWindDeg;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.rvWeatherList)
    RecyclerView rvWeatherList;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.ivWeatherIcon)
    ImageView ivWeatherIcon;

    private Unbinder binder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        MyApplication.getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

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
    public void showCurrentWeather(@NonNull final CurrentWeather currentWeather) {

        if (currentWeather.getDate() != null)
            tvDate.setText(formatterDate.format(currentWeather.getDate()));
        if (currentWeather.getObservationTime() != null)
            tvTime.setText(formatterTime.format(currentWeather.getObservationTime()));

        tvTemp.setText(String.valueOf((int) currentWeather.getTempC()));
        tvFeelsLikeC.setText(String.valueOf((int) currentWeather.getFeelsLikeC()));
        tvCloudPercent.setText(String.valueOf(currentWeather.getCloudcover()));
        tvCloudDescription.setText(currentWeather.getWeatherDescLocalLanguage());
        tvHumidity.setText(String.valueOf(currentWeather.getHumidity()));
        tvPressure.setText(String.valueOf(Math.round(currentWeather.getPressure() / 1.333)));
        tvWind.setText(String.valueOf(currentWeather.getWindspeedKmph()));
        tvWindDeg.setText(String.valueOf(currentWeather.getWinddir16Point()));
        if (currentWeather.getWeatherIconUrl() != null) {
            Picasso.with(this.getContext())
                    .load(currentWeather.getWeatherIconUrl())
                    .into(ivWeatherIcon);
        } else {
            ivWeatherIcon.setImageURI(null);
        }

    }

    @Override
    public void showWeatherList(List<DayWeather> dayWeathers) {
        rvWeatherList.setAdapter(new WeatherListAdapter(dayWeathers));
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
        return currentWeatherPresenter;
    }
}

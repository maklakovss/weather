package com.mss.weather.presentation.view.cityweather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mss.weather.R;
import com.mss.weather.di.MyApplication;
import com.mss.weather.presentation.presenter.CityWeatherPresenter;
import com.mss.weather.presentation.view.models.WeatherData;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CityWeatherFragment extends MvpAppCompatFragment implements CityWeatherView {

    @Inject
    @InjectPresenter
    CityWeatherPresenter cityWeatherPresenter;

    @BindView(R.id.tvCityName)
    TextView tvCityName;
    @BindView(R.id.tvSunrise)
    TextView tvSunrise;
    @BindView(R.id.llSunrise)
    LinearLayout llSunrise;
    @BindView(R.id.tvSunset)
    TextView tvSunset;
    @BindView(R.id.llSunset)
    LinearLayout llSunset;
    @BindView(R.id.tvCloudPercent)
    TextView tvCloudPercent;
    @BindView(R.id.tvCloudDescription)
    TextView tvCloudDescription;
    @BindView(R.id.llCloud)
    LinearLayout llCloud;
    @BindView(R.id.tvTemp)
    TextView tvTemp;
    @BindView(R.id.llTemp)
    LinearLayout llTemp;
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

    private Unbinder binder;

    public static CityWeatherFragment newInstance() {
        return new CityWeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_weather, container, false);
        binder = ButterKnife.bind(this, layout);
        cityWeatherPresenter.needData();
        return layout;
    }

    @Override
    public void showWeather(@NonNull final WeatherData weatherData) {
        tvCityName.setText(weatherData.getCityName());
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        tvSunrise.setText(formatter.format(weatherData.getSunrise()));
        tvSunset.setText(formatter.format(weatherData.getSunset()));
        tvTemp.setText(String.valueOf(weatherData.getTemp()));
        tvTempMin.setText(String.valueOf(weatherData.getTempMin()));
        tvTempMax.setText(String.valueOf(weatherData.getTempMax()));
        tvCloudPercent.setText(String.valueOf(weatherData.getCloudsPercent()));
        tvCloudDescription.setText(weatherData.getCloudsDescription());
        tvHumidity.setText(String.valueOf(weatherData.getHumidity()));
        tvPressure.setText(String.valueOf(weatherData.getPressure()));
        tvWind.setText(String.valueOf(weatherData.getWindSpeed()));
        tvWindDeg.setText(String.valueOf(weatherData.getWindDeg()));
        if (weatherData.getSnow() > 0) {
            tvRainfall.setText(String.valueOf(weatherData.getSnow()));
        } else if (weatherData.getRain() > 0) {
            tvRainfall.setText(String.valueOf(weatherData.getRain()));
        }
    }

    @Override
    public void onDestroyView() {
        binder.unbind();
        super.onDestroyView();
    }

    @ProvidePresenter
    public CityWeatherPresenter providePresenter() {
        if (cityWeatherPresenter == null)
            MyApplication.getApplicationComponent().inject(this);
        return cityWeatherPresenter;
    }
}

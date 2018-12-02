package com.mss.weather.view.cityweather;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mss.weather.R;
import com.mss.weather.presenter.CityWeatherPresenter;
import com.mss.weather.view.models.WeatherData;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CityWeatherActivity extends MvpAppCompatActivity implements CityWeatherView {

    @InjectPresenter
    CityWeatherPresenter cityWeatherPresenter;

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

    Unbinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        binder = ButterKnife.bind(this);
        cityWeatherPresenter.needData();
    }

    @Override
    public void showWeather(WeatherData weatherData) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
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
    protected void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }
}

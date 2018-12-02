package com.mss.weather.view.citysettings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mss.weather.R;
import com.mss.weather.presenter.CitySettingsPresenter;
import com.mss.weather.view.cityweather.CityWeatherActivity;
import com.mss.weather.view.models.CitySettings;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CitySettingsActivity extends AppCompatActivity implements CitySettingsView {

    @InjectPresenter
    CitySettingsPresenter citySettingsPresenter;

    @BindView(R.id.etCity)
    EditText etCity;
    @BindView(R.id.cbShowSunrise)
    CheckBox cbShowSunrise;
    @BindView(R.id.cbShowSunset)
    CheckBox cbShowSunset;
    @BindView(R.id.cbShowTemp)
    CheckBox cbShowTemp;
    @BindView(R.id.cbShowTempRange)
    CheckBox cbShowTempRange;
    @BindView(R.id.cbShowHumidity)
    CheckBox cbShowHumidity;
    @BindView(R.id.cbShowPressure)
    CheckBox cbShowPressure;
    @BindView(R.id.cbShowWindSpeed)
    CheckBox cbShowWindSpeed;
    @BindView(R.id.cbShowWindDeg)
    CheckBox cbShowWindDeg;
    @BindView(R.id.cbRainfall)
    CheckBox cbRainfall;
    @BindView(R.id.button)
    Button button;

    Unbinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        binder = ButterKnife.bind(this);
    }

    @Override
    public void showSettings(CitySettings citySettings) {

    }

    @OnClick(R.id.button)
    void saveClick(View view) {
        Intent intent = new Intent(this, CityWeatherActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }

}

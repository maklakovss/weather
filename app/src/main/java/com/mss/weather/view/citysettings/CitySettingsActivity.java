package com.mss.weather.view.citysettings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mss.weather.R;
import com.mss.weather.presenter.CitySettingsPresenter;
import com.mss.weather.view.models.CitySettings;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CitySettingsActivity extends AppCompatActivity implements CitySettingsView {

    @InjectPresenter
    CitySettingsPresenter citySettingsPresenter;

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

    @Override
    protected void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }

}

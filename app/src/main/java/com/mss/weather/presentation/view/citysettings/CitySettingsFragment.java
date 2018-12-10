package com.mss.weather.presentation.view.citysettings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mss.weather.R;
import com.mss.weather.di.MyApplication;
import com.mss.weather.presentation.presenter.CitySettingsPresenter;
import com.mss.weather.presentation.view.models.CitySettings;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CitySettingsFragment extends MvpAppCompatFragment implements CitySettingsView {

    @Inject
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

    public CitySettingsFragment() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_settings, container, false);
        binder = ButterKnife.bind(this, layout);
        citySettingsPresenter.needSettings();
        return layout;
    }

    @Override
    public void showSettings(@NonNull final CitySettings citySettings) {
        etCity.setText(citySettings.getName());
        cbShowSunrise.setChecked(citySettings.isShowSunrise());
        cbShowSunset.setChecked(citySettings.isShowSunset());
        cbShowTemp.setChecked(citySettings.isShowTemp());
        cbShowTempRange.setChecked(citySettings.isShowTempRange());
        cbShowPressure.setChecked(citySettings.isShowPressure());
        cbShowWindSpeed.setChecked(citySettings.isShowWindSpeed());
        cbShowWindDeg.setChecked(citySettings.isShowWindDeg());
        cbRainfall.setChecked(citySettings.isShowRainfall());
    }

    @OnClick(R.id.button)
    void saveClick(View view) {
        //final Intent intent = new Intent(this, CityWeatherFragment.class);
        //startActivity(intent);
    }

    @Override
    public void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }

    @ProvidePresenter
    public CitySettingsPresenter providePresenter() {
        return citySettingsPresenter;
    }
}

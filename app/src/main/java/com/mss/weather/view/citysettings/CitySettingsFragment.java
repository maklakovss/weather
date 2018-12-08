package com.mss.weather.view.citysettings;

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
import com.mss.weather.R;
import com.mss.weather.presenter.CitySettingsPresenter;
import com.mss.weather.view.models.CitySettings;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CitySettingsFragment extends MvpAppCompatFragment implements CitySettingsView {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_weather, container, false);
        binder = ButterKnife.bind(this, layout);
        return layout;
    }

    @Override
    public void showSettings(@NonNull final CitySettings citySettings) {

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

}

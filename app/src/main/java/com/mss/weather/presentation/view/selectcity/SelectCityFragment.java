package com.mss.weather.presentation.view.selectcity;

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
import com.mss.weather.domain.city.models.City;
import com.mss.weather.presentation.presenter.SelectCityPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SelectCityFragment extends MvpAppCompatFragment implements SelectCityView {

    @Inject
    @InjectPresenter
    SelectCityPresenter selectCityPresenter;

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
    @BindView(R.id.cbShowRainfall)
    CheckBox cbShowRainfall;
    @BindView(R.id.button)
    Button button;

    Unbinder binder;

    public static SelectCityFragment newInstance() {
        return new SelectCityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_settings, container, false);
        binder = ButterKnife.bind(this, layout);
        selectCityPresenter.needSettings();
        return layout;
    }

    @Override
    public void showCity(@NonNull final City city) {
        etCity.setText(city.getAreaName());
    }

    @OnClick(R.id.button)
    void saveClick(View view) {
        City city = selectCityPresenter.getCurrentCity();
        city.setAreaName(etCity.getText().toString());
        selectCityPresenter.saveSettings(city);
    }

    @Override
    public void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }

    @ProvidePresenter
    public SelectCityPresenter providePresenter() {
        if (selectCityPresenter == null)
            MyApplication.getApplicationComponent().inject(this);
        return selectCityPresenter;
    }
}

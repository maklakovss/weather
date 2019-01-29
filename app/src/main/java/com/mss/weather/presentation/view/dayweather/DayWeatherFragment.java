package com.mss.weather.presentation.view.dayweather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mss.weather.MyApplication;
import com.mss.weather.R;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.DayWeather;
import com.mss.weather.domain.models.HourWeather;
import com.mss.weather.presentation.presenter.DayWeatherPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DayWeatherFragment extends MvpAppCompatFragment implements DayWeatherView {

    private static final SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.YYYY", Locale.getDefault());
    private static final SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm", Locale.getDefault());

    public static final String DATE_KEY = "DATE_KEY";
    public static final String CITY_ID_KEY = "CITY_ID_KEY";

    @Inject
    @InjectPresenter
    DayWeatherPresenter dayWeatherPresenter;

    @BindView(R.id.tvCityName)
    TextView tvCityName;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvSunrise)
    TextView tvSunrise;
    @BindView(R.id.tvSunset)
    TextView tvSunset;
    @BindView(R.id.tvSunHour)
    TextView tvSunHour;
    @BindView(R.id.tvUvIndexLabel)
    TextView tvUvIndexLabel;
    @BindView(R.id.tvUvIndex)
    TextView tvUvIndex;
    @BindView(R.id.tvMoonrise)
    TextView tvMoonrise;
    @BindView(R.id.tvMoonset)
    TextView tvMoonset;
    @BindView(R.id.tvMoonIllumination)
    TextView tvMoonIllumination;
    @BindView(R.id.tvMoonPhase)
    TextView tvMoonPhase;
    @BindView(R.id.rvWeatherList)
    RecyclerView rvHourWeatherList;

    private Unbinder binder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        MyApplication.getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setRetainInstance(true);
        final View layout = inflater.inflate(R.layout.fragment_day_weather, container, false);
        binder = ButterKnife.bind(this, layout);
        rvHourWeatherList.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHourWeatherList.setLayoutManager(linearLayoutManager);

        final Date date = new Date(getArguments().getLong(DATE_KEY));
        final String cityID = getArguments().getString(CITY_ID_KEY);
        dayWeatherPresenter.onCreate(cityID, date);

        return layout;
    }

    @Override
    public void showCity(@NonNull final City city) {
        tvCityName.setText(city.getAreaName());
    }

    @Override
    public void showDayWeather(@NonNull final DayWeather dayWeather) {
        if (dayWeather.getDate() != null)
            tvDate.setText(formatterDate.format(dayWeather.getDate()));
        else
            tvDate.setText("");

        if (dayWeather.getSunrise() != null)
            tvSunrise.setText(formatterTime.format(dayWeather.getSunrise()));
        else
            tvSunrise.setText("");

        if (dayWeather.getSunset() != null)
            tvSunset.setText(formatterTime.format(dayWeather.getSunset()));
        else
            tvSunset.setText("");

        if (dayWeather.getMoonrise() != null) {
            tvMoonrise.setText(formatterTime.format(dayWeather.getMoonrise()));
        } else {
            tvMoonrise.setText("");
        }

        if (dayWeather.getMoonset() != null) {
            tvMoonset.setText(formatterTime.format(dayWeather.getMoonset()));
        } else {
            tvMoonset.setText("");
        }

        tvSunHour.setText(String.valueOf(dayWeather.getSunHour()));
        tvUvIndex.setText(String.valueOf(dayWeather.getUvIndex()));
        tvMoonPhase.setText(dayWeather.getMoonPhase());
        tvMoonIllumination.setText(String.valueOf(dayWeather.getMoonIllumination()));
    }

    @Override
    public void showHoursWeatherList(@NonNull final List<HourWeather> hourWeathers) {
        rvHourWeatherList.setAdapter(new HoursListAdapter(hourWeathers));
    }

    @Override
    public void clearDayWeather() {
        tvDate.setText("");
        tvSunrise.setText("");
        tvSunset.setText("");
        tvMoonrise.setText("");
        tvMoonset.setText("");
        tvSunHour.setText("");
        tvUvIndex.setText("");
        tvMoonPhase.setText("");
        tvMoonIllumination.setText("");
    }

    @Override
    public void clearHoursWeatherList() {
        rvHourWeatherList.setAdapter(null);
    }

    @Override
    public void onDestroyView() {
        binder.unbind();
        super.onDestroyView();
    }

    @ProvidePresenter
    DayWeatherPresenter providePresenter() {
        return dayWeatherPresenter;
    }
}

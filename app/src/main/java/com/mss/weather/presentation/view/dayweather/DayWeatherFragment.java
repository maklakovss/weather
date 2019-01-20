package com.mss.weather.presentation.view.dayweather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DayWeatherFragment extends MvpAppCompatFragment implements DayWeatherView {

    public static final String DATE_KEY = "DATE_KEY";
    public static final String CITY_ID_KEY = "CITY_ID_KEY";

    @Inject
    @InjectPresenter
    DayWeatherPresenter dayWeatherPresenter;

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
    @BindView(R.id.ivWeatherIcon)
    ImageView ivWeatherIcon;

    private Unbinder binder;

    public static DayWeatherFragment newInstance(String cityID, Date date) {
        final DayWeatherFragment dayWeatherFragment = new DayWeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(DATE_KEY, date.getTime());
        bundle.putString(CITY_ID_KEY, cityID);
        dayWeatherFragment.setArguments(bundle);
        return dayWeatherFragment;
    }

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
        View layout = inflater.inflate(R.layout.fragment_day_weather, container, false);
        binder = ButterKnife.bind(this, layout);
        rvWeatherList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvWeatherList.setLayoutManager(linearLayoutManager);

        Date date = new Date(getArguments().getLong(DATE_KEY));
        String cityID = getArguments().getString(CITY_ID_KEY);
        dayWeatherPresenter.onCreate(cityID, date);

        return layout;
    }

    @Override
    public void showCity(@NonNull final City city) {
        tvCityName.setText(city.getAreaName());
    }

    @Override
    public void showDayWeather(DayWeather dayWeather) {
        if (dayWeather != null) {
//            if (dayWeather.getDate() != null)
//                tvDate.setText(formatterDate.format(dayWeather.getDate()));
//            if (dayWeather.getObservationTime() != null)
//                tvTime.setText(formatterTime.format(dayWeather.getObservationTime()));
//
//            tvTemp.setText(String.valueOf((int) dayWeather.getTempC()));
//            tvFeelsLikeC.setText(String.valueOf((int) dayWeather.getFeelsLikeC()));
//            tvCloudPercent.setText(String.valueOf(dayWeather.getCloudcover()));
//            tvCloudDescription.setText(dayWeather.getWeatherDescLocalLanguage());
//            tvHumidity.setText(String.valueOf(dayWeather.getHumidity()));
//            tvPressure.setText(String.valueOf(Math.round(dayWeather.getPressure() / 1.333)));
//            tvWind.setText(String.valueOf(dayWeather.getWindspeedKmph()));
//            tvWindDeg.setText(String.valueOf(dayWeather.getWinddir16Point()));
//            if (dayWeather.getWeatherIconUrl() != null) {
//                Picasso.with(this.getContext())
//                        .load(dayWeather.getWeatherIconUrl())
//                        .into(ivWeatherIcon);
//            } else {
//                ivWeatherIcon.setImageURI(null);
//            }
        } else {
            tvDate.setText("");
            tvTime.setText("");
            tvTemp.setText("");
            tvFeelsLikeC.setText("");
            tvCloudPercent.setText("");
            tvCloudDescription.setText("");
            tvHumidity.setText("");
            tvPressure.setText("");
            tvWind.setText("");
            tvWindDeg.setText("");
            ivWeatherIcon.setImageURI(null);
        }

    }

    @Override
    public void showHoursWeatherList(List<HourWeather> hourWeathers) {
        if (hourWeathers != null) {
            rvWeatherList.setAdapter(new HoursListAdapter(hourWeathers));
        } else {
            rvWeatherList.setAdapter(null);
        }
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

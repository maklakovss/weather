package com.mss.weather.presentation.view.history;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.mss.weather.MyApplication;
import com.mss.weather.R;
import com.mss.weather.domain.models.City;
import com.mss.weather.presentation.presenter.HistoryWeatherPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HistoryFragment extends MvpAppCompatFragment implements HistoryView {

    public static final String CITY_ID_KEY = "CITY_ID_KEY";

    @Inject
    @InjectPresenter
    HistoryWeatherPresenter historyWeatherPresenter;

    @BindView(R.id.tvCityName)
    TextView tvCityName;
    @BindView(R.id.lcHistoryChart)
    LineChart lcHistoryChart;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.spParameter)
    Spinner spParameter;
    @BindView(R.id.spYearFrom)
    Spinner spYearFrom;
    @BindView(R.id.spYearTo)
    Spinner spYearTo;
    @BindView(R.id.spHourFrom)
    Spinner spHourFrom;
    @BindView(R.id.spHourTo)
    Spinner spHourTo;
    @BindView(R.id.spMonth)
    Spinner spMonth;

    private Unbinder binder;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        MyApplication.getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreateView(inflater, container, savedInstanceState);
        final View layout = inflater.inflate(R.layout.fragment_history, container, false);
        binder = ButterKnife.bind(this, layout);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.parametrs_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spParameter.setAdapter(adapter);

        final String cityID = getArguments().getString(CITY_ID_KEY);
        historyWeatherPresenter.onCreate(cityID);

        return layout;
    }

    @OnClick(R.id.btnApply)
    public void onClick(View view) {
        historyWeatherPresenter.clickApply((String) spParameter.getSelectedItem());
    }


    @Override
    public void showCity(@NonNull final City city) {
        tvCityName.setText(city.getAreaName());
    }

    @Override
    public void clearStatistics() {
        lcHistoryChart.clear();
    }

    @Override
    public void addStatistics(@NonNull final LineData lineData) {
        lcHistoryChart.setData(lineData);
        lcHistoryChart.setRenderer(new CustomFillLineLegendRenderer(lcHistoryChart, lcHistoryChart.getAnimator(), lcHistoryChart.getViewPortHandler()));
        lcHistoryChart.invalidate();
    }

    @Override
    public void showProgress(boolean visible) {
        if (visible)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void fillSpiners(Integer[] years, String[] months, Integer[] hours) {
        ArrayAdapter<Integer> adapterYears = new ArrayAdapter<Integer>(this.getContext(),
                android.R.layout.simple_spinner_item, years);
        adapterYears.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spYearFrom.setAdapter(adapterYears);
        spYearFrom.setSelection(0);
        spYearTo.setAdapter(adapterYears);
        spYearTo.setSelection(years.length - 1);

        ArrayAdapter<Integer> adapterHours = new ArrayAdapter<Integer>(this.getContext(),
                android.R.layout.simple_spinner_item, hours);
        adapterHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHourFrom.setAdapter(adapterHours);
        spHourFrom.setSelection(0);
        spHourTo.setAdapter(adapterHours);
        spHourTo.setSelection(hours.length - 1);

        ArrayAdapter<String> adapterMonths = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, months);
        adapterMonths.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMonth.setAdapter(adapterMonths);

    }

    @ProvidePresenter
    HistoryWeatherPresenter providePresenter() {
        return historyWeatherPresenter;
    }

    @Override
    public void onDestroyView() {
        binder.unbind();
        super.onDestroyView();
    }
}

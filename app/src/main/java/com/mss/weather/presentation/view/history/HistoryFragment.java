package com.mss.weather.presentation.view.history;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.mss.weather.MyApplication;
import com.mss.weather.R;
import com.mss.weather.domain.models.City;
import com.mss.weather.presentation.presenter.HistoryWeatherPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
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

        final String cityID = getArguments().getString(CITY_ID_KEY);
        historyWeatherPresenter.onCreate(cityID);

        return layout;
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
    public void addStatistics(@NonNull final List<Entry> entryList, int colorLine, int colorValue, @NonNull final String label) {
        LineData lineData = lcHistoryChart.getData();
        if (lineData == null) {
            lineData = new LineData();
        }
        LineDataSet dataSet = new LineDataSet(entryList, label);
        dataSet.setColor(colorLine);
        dataSet.setFillColor(colorLine);
        dataSet.setDrawCircles(false);
        if (lineData.getDataSetCount() > 0) {
            dataSet.setFillAlpha(255);
            dataSet.setDrawFilled(true);
            final LineDataSet lastDataset = (LineDataSet) lineData.getDataSetByIndex(lineData.getDataSetCount() - 1);
            dataSet.setFillFormatter(new FillFormatter(lastDataset));
        }
        lineData.addDataSet(dataSet);
        lcHistoryChart.setData(lineData);
        lcHistoryChart.setRenderer(new FillLineLegendRenderer(lcHistoryChart, lcHistoryChart.getAnimator(), lcHistoryChart.getViewPortHandler()));
        lcHistoryChart.invalidate();
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

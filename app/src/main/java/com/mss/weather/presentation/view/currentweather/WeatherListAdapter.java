package com.mss.weather.presentation.view.currentweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mss.weather.R;
import com.mss.weather.domain.models.DayWeather;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {

    final static SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM", Locale.getDefault());
    final static SimpleDateFormat formatterDayOfWeek = new SimpleDateFormat("EEEE", Locale.getDefault());
    private List<DayWeather> weatherDataList;

    public WeatherListAdapter(List<DayWeather> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.weather_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DayWeather dayWeather = weatherDataList.get(i);
        viewHolder.tvDate.setText(formatterDate.format(dayWeather.getDate()));
        viewHolder.tvDayOfWeek.setText(formatterDayOfWeek.format(dayWeather.getDate()));
        viewHolder.tvTempMin.setText(String.valueOf(dayWeather.getMinTempC()));
        viewHolder.tvTempMax.setText(String.valueOf(dayWeather.getMaxTempC()));

    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvDayOfWeek)
        TextView tvDayOfWeek;
        @BindView(R.id.tvTempMin)
        TextView tvTempMin;
        @BindView(R.id.tvTempMax)
        TextView tvTempMax;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

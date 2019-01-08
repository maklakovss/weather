package com.mss.weather.presentation.view.currentweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mss.weather.R;
import com.mss.weather.domain.models.WeatherDay;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {

    final SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM.YYYY", Locale.getDefault());
    private List<WeatherDay> weatherDataList;

    public WeatherListAdapter(List<WeatherDay> weatherDataList) {
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
        WeatherDay weatherDay = weatherDataList.get(i);
        viewHolder.tvPeriod.setText(formatterDate.format(weatherDay.getDate()));
        viewHolder.tvTempMin.setText(String.valueOf(weatherDay.getMintempC()));
        viewHolder.tvTempMax.setText(String.valueOf(weatherDay.getMaxtempC()));

    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvPeriod)
        TextView tvPeriod;
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

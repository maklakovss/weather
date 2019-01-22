package com.mss.weather.presentation.view.dayweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mss.weather.R;
import com.mss.weather.domain.models.HourWeather;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HoursListAdapter extends RecyclerView.Adapter<HoursListAdapter.ViewHolder> {

    final static SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm", Locale.getDefault());

    private List<HourWeather> hourWeathers;

    public HoursListAdapter(List<HourWeather> hourWeathers) {
        this.hourWeathers = hourWeathers;
    }

    @NonNull
    @Override
    public HoursListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_hour, viewGroup, false);
        HoursListAdapter.ViewHolder viewHolder = new HoursListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HoursListAdapter.ViewHolder viewHolder, int i) {
        HourWeather hourWeather = hourWeathers.get(i);
        viewHolder.tvHour.setText(formatterTime.format(hourWeather.getDate()));
        //viewHolder.tvDayOfWeek.setText(formatterDayOfWeek.format(hourWeather.getDate()));
        //viewHolder.tvTempMin.setText(String.valueOf(hourWeather.getMinTempC()));
    }

    @Override
    public int getItemCount() {
        return hourWeathers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvHour)
        TextView tvHour;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

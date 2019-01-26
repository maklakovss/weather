package com.mss.weather.presentation.view.dayweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mss.weather.R;
import com.mss.weather.domain.models.HourWeather;
import com.squareup.picasso.Picasso;

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
        viewHolder.tvTemp.setText(String.valueOf(hourWeather.getTempC()));
        viewHolder.tvFeels.setText(String.valueOf(hourWeather.getFeelsLikeC()));
        viewHolder.tvWind.setText(String.valueOf(hourWeather.getWindspeedKmph() * 1000 / 3600));
        viewHolder.tvWinddir16Point.setText(hourWeather.getWinddir16Point());
        viewHolder.tvHumidity.setText(String.valueOf(hourWeather.getHumidity()));
        viewHolder.tvPrecip.setText(String.valueOf(hourWeather.getPrecipMM()));

        if (hourWeather.getWeatherIconUrl() != null) {
            Picasso.with(viewHolder.itemView.getContext())
                    .load(hourWeather.getWeatherIconUrl())
                    .into(viewHolder.ivWeatherIcon);
        } else {
            viewHolder.ivWeatherIcon.setImageURI(null);
        }
        //viewHolder.tvDayOfWeek.setText(formatterDayOfWeek.format(hourWeather.getDate()));
        //viewHolder.tvTempMin.setText(String.valueOf(hourWeather.getMinTempC()));
    }

    @Override
    public int getItemCount() {
        return hourWeathers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivWeatherIcon)
        ImageView ivWeatherIcon;
        @BindView(R.id.tvHour)
        TextView tvHour;
        @BindView(R.id.tvTemp)
        TextView tvTemp;
        @BindView(R.id.tvFeels)
        TextView tvFeels;
        @BindView(R.id.tvWind)
        TextView tvWind;
        @BindView(R.id.tvWinddir16Point)
        TextView tvWinddir16Point;
        @BindView(R.id.tvHumidity)
        TextView tvHumidity;
        @BindView(R.id.tvPrecip)
        TextView tvPrecip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

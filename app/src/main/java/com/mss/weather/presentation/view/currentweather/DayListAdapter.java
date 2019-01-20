package com.mss.weather.presentation.view.currentweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mss.weather.R;
import com.mss.weather.domain.models.DayWeather;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.ViewHolder> {

    final static SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM", Locale.getDefault());
    final static SimpleDateFormat formatterDayOfWeek = new SimpleDateFormat("EEEE", Locale.getDefault());

    private List<DayWeather> dayWeathers;
    private OnItemClickListener onItemClickListener;

    public DayListAdapter(List<DayWeather> dayWeathers) {
        this.dayWeathers = dayWeathers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_day, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DayWeather dayWeather = dayWeathers.get(i);
        viewHolder.tvDate.setText(formatterDate.format(dayWeather.getDate()));
        viewHolder.tvDayOfWeek.setText(formatterDayOfWeek.format(dayWeather.getDate()));
        viewHolder.tvTempMin.setText(String.valueOf(dayWeather.getMinTempC()));
        viewHolder.tvTempMax.setText(String.valueOf(dayWeather.getMaxTempC()));
        viewHolder.tvWindMin.setText(String.valueOf(dayWeather.getMinWindspeedKmph()));
        viewHolder.tvWindMax.setText(String.valueOf(dayWeather.getMaxWindspeedKmph()));
        if (dayWeather.getMaxWeatherIconUrl() != null) {
            Picasso.with(viewHolder.itemView.getContext())
                    .load(dayWeather.getMaxWeatherIconUrl())
                    .into(viewHolder.ivWeatherIcon);
        } else {
            viewHolder.ivWeatherIcon.setImageURI(null);
        }
    }

    @Override
    public int getItemCount() {
        return dayWeathers.size();
    }

    public void setOnItemClickListener(DayListAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
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
        @BindView(R.id.tvWindMin)
        TextView tvWindMin;
        @BindView(R.id.tvWindMax)
        TextView tvWindMax;
        @BindView(R.id.ivWeatherIcon)
        ImageView ivWeatherIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.ibDetailDay)
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}

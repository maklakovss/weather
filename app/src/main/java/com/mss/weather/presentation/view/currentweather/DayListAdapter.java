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

    private final static SimpleDateFormat formatterDate = new SimpleDateFormat("dd.MM", Locale.getDefault());
    private final static SimpleDateFormat formatterDayOfWeek = new SimpleDateFormat("EEEE", Locale.getDefault());

    private final List<DayWeather> dayWeathers;

    private OnItemClickListener onItemClickListener;

    public DayListAdapter(List<DayWeather> dayWeathers) {
        this.dayWeathers = dayWeathers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_day, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final DayWeather dayWeather = dayWeathers.get(i);
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
        void onItemClick(@NonNull final View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvDate)
        private TextView tvDate;
        @BindView(R.id.tvDayOfWeek)
        private TextView tvDayOfWeek;
        @BindView(R.id.tvTempMin)
        private TextView tvTempMin;
        @BindView(R.id.tvTempMax)
        private TextView tvTempMax;
        @BindView(R.id.tvWindMin)
        private TextView tvWindMin;
        @BindView(R.id.tvWindMax)
        private TextView tvWindMax;
        @BindView(R.id.ivWeatherIcon)
        private ImageView ivWeatherIcon;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.ibDetailDay)
        public void onClick(@NonNull final View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}

package com.mss.weather.presentation.view.listcities;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mss.weather.R;
import com.mss.weather.domain.models.City;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

    private final List<City> citiesList;
    private OnItemClickListener onItemClickListener;

    public CitiesAdapter(@NonNull final List<City> cities) {
        citiesList = cities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_city, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvCityName.setText(citiesList.get(i).getAreaName());
        viewHolder.tvRegion.setText(citiesList.get(i).getRegion());
        viewHolder.tvCountry.setText(citiesList.get(i).getCountry());
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.clContainer)
        ConstraintLayout clContainer;
        @BindView(R.id.tvCityName)
        TextView tvCityName;
        @BindView(R.id.tvRegion)
        TextView tvRegion;
        @BindView(R.id.tvCountry)
        TextView tvCountry;
        @BindView(R.id.cvItem)
        CardView cvItem;

        ViewHolder(@NonNull final View view) {
            super(view);
            ButterKnife.bind(this, view);
            cvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(@NonNull final View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
        }
    }
}

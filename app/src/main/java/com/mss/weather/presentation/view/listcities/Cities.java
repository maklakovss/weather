package com.mss.weather.presentation.view.listcities;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mss.weather.R;
import com.mss.weather.domain.weather.models.City;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Cities extends RecyclerView.Adapter<Cities.ViewHolder> {

    private List<City> citiesList;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public Cities(List<City> cities/*, Context context*/) {
//        this.context = context;
        citiesList = cities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.city_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvCityName.setText(citiesList.get(i).getAreaName());
//        viewHolder.cvItem.setCardBackgroundColor(
//                focusedItem == i ?
//                        context.getResources().getColor(R.color.colorAccent)
//                        : context.getResources().getColor(R.color.colorCard));
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvCityName)
        TextView tvCityName;
        @BindView(R.id.cvItem)
        CardView cvItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            cvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    updateFocusedItem(getAdapterPosition());
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
            cvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (onItemLongClickListener != null) {
                        onItemLongClickListener.onItemLongClick(view, getAdapterPosition());
                    }
                    return true;
                }
            });
        }

//        private void updateFocusedItem(int newFocusedItem) {
//            notifyItemChanged(focusedItem);
//            focusedItem = newFocusedItem;
//            notifyItemChanged(focusedItem);
//        }
    }
}

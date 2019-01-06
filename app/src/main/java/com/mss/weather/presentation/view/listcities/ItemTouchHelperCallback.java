package com.mss.weather.presentation.view.listcities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public class ItemTouchHelperCallback extends android.support.v7.widget.helper.ItemTouchHelper.Callback {

    private final DismissItemTouchHelper helper;

    public ItemTouchHelperCallback(DismissItemTouchHelper helper) {
        this.helper = helper;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = android.support.v7.widget.helper.ItemTouchHelper.START | android.support.v7.widget.helper.ItemTouchHelper.END;
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        helper.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
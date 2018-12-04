package com.mss.weather.view.listcities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.arellomobile.mvp.MvpFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mss.weather.R;
import com.mss.weather.presenter.ListCitiesPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ListCitiesFragment extends MvpFragment implements ListCitiesView {

    @InjectPresenter
    ListCitiesPresenter listCitiesPresenter;

    @BindView(R.id.rvCitiesList)
    RecyclerView rvCitiesList;
    @BindView(R.id.btnAddCity)
    ImageButton btnAddCity;

    private Unbinder binder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.activity_list_cities, container, false);
        binder = ButterKnife.bind(this, layout);
        return layout;
    }

    @OnClick(R.id.btnAddCity)
    void AddClick(View view) {
        //Intent intent = new Intent(this, CitySettingsActivity.class);
        //startActivity(intent);
    }

    @Override
    public void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }
}

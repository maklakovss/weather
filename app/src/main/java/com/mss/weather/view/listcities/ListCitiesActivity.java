package com.mss.weather.view.listcities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mss.weather.R;
import com.mss.weather.presenter.ListCitiesPresenter;
import com.mss.weather.view.citysettings.CitySettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ListCitiesActivity extends MvpAppCompatActivity implements ListCitiesView {

    @InjectPresenter
    ListCitiesPresenter listCitiesPresenter;

    @BindView(R.id.rvCitiesList)
    RecyclerView rvCitiesList;
    @BindView(R.id.btnAddCity)
    ImageButton btnAddCity;

    private Unbinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cities);
        binder = ButterKnife.bind(this);
    }

    @OnClick(R.id.btnAddCity)
    void AddClick(View view) {
        Intent intent = new Intent(this, CitySettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }
}

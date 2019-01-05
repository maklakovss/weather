package com.mss.weather.presentation.view.listcities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mss.weather.R;
import com.mss.weather.di.MyApplication;
import com.mss.weather.domain.city.models.City;
import com.mss.weather.presentation.presenter.ListCitiesPresenter;
import com.mss.weather.presentation.view.main.WeatherFragmentsNavigator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ListCitiesFragment extends MvpAppCompatFragment implements ListCitiesView {

    @Inject
    @InjectPresenter
    ListCitiesPresenter listCitiesPresenter;

    @BindView(R.id.rvCitiesList)
    RecyclerView rvCitiesList;
    @BindView(R.id.btnAddCity)
    FloatingActionButton btnAddCity;

    private Unbinder binder;

    private WeatherFragmentsNavigator weatherFragmentsNavigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        weatherFragmentsNavigator = (WeatherFragmentsNavigator) context;
    }

    @Override
    public void onDetach() {
        weatherFragmentsNavigator = null;
        super.onDetach();
    }

    public static ListCitiesFragment newInstance() {
        return new ListCitiesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_list_cities, container, false);
        binder = ButterKnife.bind(this, layout);

        rvCitiesList.setItemAnimator(new DefaultItemAnimator());
        rvCitiesList.setHasFixedSize(true);
        rvCitiesList.setLayoutManager(new LinearLayoutManager(getContext()));

        listCitiesPresenter.needCities();

        return layout;
    }

    @OnClick(R.id.btnAddCity)
    void AddClick(View view) {
        listCitiesPresenter.onClickAdd();
    }

    @Override
    public void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }

    @Override
    public void updateList(List<City> cities) {
        final CitiesAdapter citiesAdapter = new CitiesAdapter(cities);
        citiesAdapter.setOnItemClickListener(new CitiesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                listCitiesPresenter.onClickCity(position);
            }
        });
        citiesAdapter.setOnItemLongClickListener(new CitiesAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                listCitiesPresenter.onLongClickCity(position);
            }
        });
        rvCitiesList.setAdapter(citiesAdapter);
    }

    @Override
    public void showWeather() {
        if (weatherFragmentsNavigator != null) {
            weatherFragmentsNavigator.showWeather();
        }
    }

    @Override
    public void showCity() {
        if (weatherFragmentsNavigator != null)
            weatherFragmentsNavigator.showCity();
    }

    @Override
    public void updateCity(int position) {
        rvCitiesList.getAdapter().notifyItemChanged(position);
    }

    @ProvidePresenter
    public ListCitiesPresenter providePresenter() {
        if (listCitiesPresenter == null)
            MyApplication.getApplicationComponent().inject(this);
        return listCitiesPresenter;
    }
}

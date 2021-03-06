package com.mss.weather.presentation.view.listcities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mss.weather.MyApplication;
import com.mss.weather.R;
import com.mss.weather.domain.models.City;
import com.mss.weather.presentation.presenter.ListCitiesPresenter;
import com.mss.weather.presentation.view.main.WeatherFragmentsNavigator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ListCitiesFragment extends MvpAppCompatFragment implements ListCitiesView, DismissItemTouchHelper {

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
    public void onCreate(Bundle savedInstanceState) {
        MyApplication.getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setRetainInstance(true);
        final View layout = inflater.inflate(R.layout.fragment_list_cities, container, false);
        binder = ButterKnife.bind(this, layout);

        rvCitiesList.setItemAnimator(new DefaultItemAnimator());
        rvCitiesList.setHasFixedSize(true);
        rvCitiesList.setLayoutManager(new LinearLayoutManager(getContext()));

        listCitiesPresenter.needCities();

        return layout;
    }

    @ProvidePresenter
    public ListCitiesPresenter providePresenter() {
        return listCitiesPresenter;
    }

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

    @Override
    public void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }

    @OnClick(R.id.btnAddCity)
    void AddClick(View view) {
        listCitiesPresenter.onClickAdd();
    }

    @Override
    public void updateList(List<City> cities) {
        final CitiesAdapter citiesAdapterAdapter = new CitiesAdapter(cities);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rvCitiesList);

        citiesAdapterAdapter.setOnItemClickListener(new CitiesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                listCitiesPresenter.onClickCity(position);
            }
        });

        rvCitiesList.setAdapter(citiesAdapterAdapter);
    }

    @Override
    public void updateCity(int position) {
        rvCitiesList.getAdapter().notifyItemRemoved(position);
    }

    @Override
    public void showWeather() {
        if (weatherFragmentsNavigator != null) {
            weatherFragmentsNavigator.showCurrentWeather();
        }
    }

    @Override
    public void showSelectCity() {
        if (weatherFragmentsNavigator != null)
            weatherFragmentsNavigator.showAddCity();
    }

    @Override
    public void onItemDismiss(int position) {
        listCitiesPresenter.deleteCity(position);
    }
}

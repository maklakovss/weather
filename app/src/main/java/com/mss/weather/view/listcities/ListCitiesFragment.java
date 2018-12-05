package com.mss.weather.view.listcities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mss.weather.R;
import com.mss.weather.presenter.ListCitiesPresenter;
import com.mss.weather.view.main.WeatherFragmentsInteractor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ListCitiesFragment extends MvpAppCompatFragment implements ListCitiesView {

    @InjectPresenter
    ListCitiesPresenter listCitiesPresenter;

    @BindView(R.id.lvCitiesList)
    ListView lvCitiesList;
    @BindView(R.id.btnAddCity)
    ImageButton btnAddCity;

    private Unbinder binder;

    private WeatherFragmentsInteractor weatherFragmentsInteractor;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        weatherFragmentsInteractor = (WeatherFragmentsInteractor) context;
    }

    @Override
    public void onDetach() {
        weatherFragmentsInteractor = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_list_cities, container, false);
        binder = ButterKnife.bind(this, layout);
        lvCitiesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listCitiesPresenter.setCheckedCity(i);
                if (weatherFragmentsInteractor != null)
                    weatherFragmentsInteractor.setCurrentCity(lvCitiesList.getAdapter().getItem(i).toString(), true);
            }
        });
        listCitiesPresenter.needCities();
        return layout;
    }

    @OnClick(R.id.btnAddCity)
    void AddClick(View view) {
        if (weatherFragmentsInteractor != null)
            weatherFragmentsInteractor.addCity();
    }

    @Override
    public void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }

    @Override
    public void updateList(String[] cities, int checkedItem) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, cities);
        lvCitiesList.setAdapter(adapter);
        lvCitiesList.setItemChecked(checkedItem, true);
        if (weatherFragmentsInteractor != null)
            weatherFragmentsInteractor.setCurrentCity(lvCitiesList.getAdapter().getItem(checkedItem).toString(), false);
    }
}

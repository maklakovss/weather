package com.mss.weather.view.listcities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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
import com.mss.weather.view.cityweather.CityWeatherFragment;

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
                refreshWeatherFragment(i);
            }
        });
        listCitiesPresenter.needCities();
        return layout;
    }

    private void refreshWeatherFragment(int i) {
        CityWeatherFragment cityWeatherFragment = getWeatherFragment();
        if (cityWeatherFragment != null) {
            cityWeatherFragment.changeCity(lvCitiesList.getAdapter().getItem(i).toString());
        }
    }

    private CityWeatherFragment getWeatherFragment() {
        CityWeatherFragment cityWeatherFragment = (CityWeatherFragment) getFragmentManager()
                .findFragmentById(R.id.weather);
        if (cityWeatherFragment == null) {
            cityWeatherFragment = new CityWeatherFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.flMainFrame, cityWeatherFragment);  // замена фрагмента
            ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack("");
            ft.commit();
        }
        return cityWeatherFragment;
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

    @Override
    public void updateList(String[] cities, int checkedItem) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, cities);
        lvCitiesList.setAdapter(adapter);
        lvCitiesList.setItemChecked(checkedItem, true);
        //refreshWeatherFragment(checkedItem);
    }
}

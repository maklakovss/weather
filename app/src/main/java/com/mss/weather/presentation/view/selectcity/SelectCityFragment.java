package com.mss.weather.presentation.view.selectcity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mss.weather.R;
import com.mss.weather.di.MyApplication;
import com.mss.weather.domain.city.models.City;
import com.mss.weather.presentation.presenter.SelectCityPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SelectCityFragment extends MvpAppCompatFragment implements SelectCityView {

    @Inject
    @InjectPresenter
    SelectCityPresenter selectCityPresenter;

    Unbinder binder;
    @BindView(R.id.tilSearchTemplate)
    TextInputLayout tilSearchTemplate;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    @BindView(R.id.rvAutoCompleteList)
    RecyclerView rvAutoCompleteList;

    public static SelectCityFragment newInstance() {
        return new SelectCityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View layout = inflater.inflate(R.layout.fragment_select_city, container, false);
        binder = ButterKnife.bind(this, layout);

        rvAutoCompleteList.setItemAnimator(new DefaultItemAnimator());
        rvAutoCompleteList.setHasFixedSize(true);
        rvAutoCompleteList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAutoCompleteList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));



        return layout;
    }

    @Override
    public void showCities(@NonNull final List<City> cities) {
        final SelectCityAdapter citiesAdapter = new SelectCityAdapter(cities);
        citiesAdapter.setOnItemClickListener(new SelectCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //listCitiesPresenter.onClickCity(position);
            }
        });
        rvAutoCompleteList.setAdapter(citiesAdapter);
    }

    @OnClick(R.id.btnSearch)
    void searchClick(View view) {
        selectCityPresenter.searchClicked(tilSearchTemplate.getEditText().getText().toString());
    }

    @ProvidePresenter
    public SelectCityPresenter providePresenter() {
        if (selectCityPresenter == null)
            MyApplication.getApplicationComponent().inject(this);
        return selectCityPresenter;
    }

    @Override
    public void onDestroyView() {
        binder.unbind();
        super.onDestroyView();
    }
}

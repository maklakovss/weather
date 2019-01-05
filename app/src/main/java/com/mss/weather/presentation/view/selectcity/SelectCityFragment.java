package com.mss.weather.presentation.view.selectcity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.mss.weather.R;
import com.mss.weather.di.MyApplication;
import com.mss.weather.domain.city.models.City;
import com.mss.weather.presentation.presenter.SelectCityPresenter;
import com.mss.weather.presentation.view.main.WeatherFragmentsNavigator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SelectCityFragment extends MvpAppCompatFragment implements SelectCityView, TextView.OnEditorActionListener, SelectCityAdapter.OnItemClickListener {

    @Inject
    @InjectPresenter
    SelectCityPresenter selectCityPresenter;

    Unbinder binder;
    @BindView(R.id.tilSearchTemplate)
    TextInputLayout tilSearchTemplate;
    @BindView(R.id.rvAutoCompleteList)
    RecyclerView rvAutoCompleteList;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private WeatherFragmentsNavigator weatherFragmentsNavigator;

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

        tilSearchTemplate.getEditText().setOnEditorActionListener(this);

        return layout;
    }

    @ProvidePresenter
    public SelectCityPresenter providePresenter() {
        if (selectCityPresenter == null)
            MyApplication.getApplicationComponent().inject(this);
        return selectCityPresenter;
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
    public void onDestroyView() {
        binder.unbind();
        super.onDestroyView();
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH
                || (keyEvent != null && keyEvent.getAction() == KeyEvent.ACTION_DOWN))
            selectCityPresenter.searchClicked(textView.getText().toString());
        return false;
    }

    @Override
    public void showCities(@NonNull final List<City> cities) {
        final SelectCityAdapter citiesAdapter = new SelectCityAdapter(cities);
        citiesAdapter.setOnItemClickListener(this);
        rvAutoCompleteList.setAdapter(citiesAdapter);
    }

    @Override
    public void showProgress(boolean visible) {
        if (visible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void back() {
        weatherFragmentsNavigator.back();
    }

    @Override
    public void onItemClick(View view, int position) {
        selectCityPresenter.onClickCity(position);
    }
}

package com.mss.weather.presentation.view.selectcity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
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
import com.mss.weather.MyApplication;
import com.mss.weather.R;
import com.mss.weather.domain.city.models.City;
import com.mss.weather.presentation.presenter.SelectCityPresenter;
import com.mss.weather.presentation.view.main.WeatherFragmentsNavigator;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.LOCATION_SERVICE;

public class SelectCityFragment extends MvpAppCompatFragment implements SelectCityView, TextView.OnEditorActionListener, SelectCityAdapter.OnItemClickListener {

    private static final int PERMISSION_REQUEST_CODE = 10;
    private LocationManager locationManager;

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

    @OnClick(R.id.btnLocation)
    public void onViewClicked() {
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            requestLocation();
        } else {
            requestLocationPermissions();
        }
    }

    private void requestLocationPermissions() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(), Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length == 1 &&
                    (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                requestLocation();
            }
        }
    }


    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                selectCityPresenter.locationClick(location.getLatitude(), location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        }, Looper.myLooper());

    }
}

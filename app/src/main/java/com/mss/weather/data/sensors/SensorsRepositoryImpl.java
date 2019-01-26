package com.mss.weather.data.sensors;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mss.weather.MyApplication;
import com.mss.weather.domain.models.Position;
import com.mss.weather.domain.repositories.SensorsRepository;

import java.security.NoSuchProviderException;

import io.reactivex.Maybe;

import static android.content.Context.LOCATION_SERVICE;

public class SensorsRepositoryImpl implements SensorsRepository {

    @NonNull
    @SuppressLint("MissingPermission")
    @Override
    public Maybe<Position> getPosition() {
        final Maybe<Position> positionMaybe = Maybe.create(e -> {
            final LocationManager locationManager = (LocationManager) MyApplication.getContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    final Position position = new Position();
                    position.setLatitude(location.getLatitude());
                    position.setLongitude(location.getLongitude());
                    e.onSuccess(position);
                    e.onComplete();
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {
                    e.onError(new NoSuchProviderException(provider));
                }
            }, null);
        });

        return positionMaybe;
    }
}

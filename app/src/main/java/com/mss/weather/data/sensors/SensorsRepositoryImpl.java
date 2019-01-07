package com.mss.weather.data.sensors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.mss.weather.domain.sensors.SensorsRepository;
import com.mss.weather.domain.sensors.models.Position;

import java.security.NoSuchProviderException;

import javax.inject.Inject;

import io.reactivex.Maybe;

import static android.content.Context.LOCATION_SERVICE;

public class SensorsRepositoryImpl implements SensorsRepository {

    final private Context context;

    @Inject
    public SensorsRepositoryImpl(Context context) {
        this.context = context;
    }

    @SuppressLint("MissingPermission")
    @Override
    public Maybe<Position> getPosition() {
        Maybe<Position> positionMaybe = Maybe.create(e -> {
            LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Position position = new Position();
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

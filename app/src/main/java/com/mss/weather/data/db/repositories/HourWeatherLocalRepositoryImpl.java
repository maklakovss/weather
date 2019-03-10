package com.mss.weather.data.db.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.domain.models.HourWeather;
import com.mss.weather.domain.repositories.HourWeatherLocalRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class HourWeatherLocalRepositoryImpl implements HourWeatherLocalRepository {

    @Nullable
    @Override
    public List<HourWeather> getHourWeathersByCityId(@NonNull final String cityId, @NonNull final Date date, boolean isPast) {
        final Calendar endDate = Calendar.getInstance();
        endDate.setTime(date);
        endDate.roll(Calendar.DATE, 1);

        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<HourWeather> results = realm.where(HourWeather.class)
                .equalTo("isPast", isPast)
                .equalTo("cityID", cityId)
                .greaterThanOrEqualTo("date", date)
                .lessThan("date", endDate.getTime())
                .sort("date")
                .findAll();

        List<HourWeather> hourWeathers = null;
        if (results != null) {
            hourWeathers = realm.copyFromRealm(results);
        }
        realm.close();
        return hourWeathers;
    }

    @Override
    public void deleteHourWeatherByCityID(@NonNull final String cityId) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<HourWeather> hourWeatherRealmResults = realm.where(HourWeather.class)
                .equalTo("cityID", cityId)
                .findAll();
        if (hourWeatherRealmResults != null) {
            realm.beginTransaction();
            hourWeatherRealmResults.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }

    @Override
    public void deleteOldHourWeatherByCityID(@NonNull final String cityId, @NonNull final Date date) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        final RealmResults<HourWeather> hourWeatherRealmResults = realm.where(HourWeather.class)
                .equalTo("isPast", false)
                .equalTo("cityID", cityId)
                .lessThan("date", date)
                .findAll();
        hourWeatherRealmResults.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void updateOrInsertHourWeather(@NonNull final List<HourWeather> hourWeathers, boolean isPast) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(hourWeathers);
        realm.commitTransaction();
        realm.close();
    }
}

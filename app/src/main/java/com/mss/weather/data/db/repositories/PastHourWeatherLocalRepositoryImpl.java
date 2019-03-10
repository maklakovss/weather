package com.mss.weather.data.db.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.domain.models.PastHourWeather;
import com.mss.weather.domain.repositories.PastHourWeatherLocalRepository;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

class PastHourWeatherLocalRepositoryImpl implements PastHourWeatherLocalRepository {

    @Nullable
    @Override
    public List<PastHourWeather> getPastHourWeathers(@NonNull String cityId, int yearFrom, int yearTo, int monthFrom, int monthTo, int dayFrom, int dayTo, int hourFrom, int hourTo) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<PastHourWeather> pastHourWeathers = realm.where(PastHourWeather.class)
                .equalTo("cityID", cityId)
                .greaterThanOrEqualTo("year", yearFrom)
                .lessThanOrEqualTo("year", yearTo)
                .greaterThanOrEqualTo("month", monthFrom)
                .lessThanOrEqualTo("month", monthTo)
                .greaterThanOrEqualTo("day", dayFrom)
                .lessThanOrEqualTo("day", dayTo)
                .greaterThanOrEqualTo("hour", hourFrom)
                .lessThanOrEqualTo("hour", hourTo)
                .sort("id")
                .findAll();

        realm.close();
        return pastHourWeathers;
    }

    @Override
    public void deletePastHourWeatherByCityID(@NonNull String cityId) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<PastHourWeather> pastHourWeatherDBRealmResults = realm.where(PastHourWeather.class)
                .equalTo("cityID", cityId)
                .findAll();
        if (pastHourWeatherDBRealmResults != null) {
            realm.beginTransaction();
            pastHourWeatherDBRealmResults.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }

    @Override
    public void updateOrInsertPastHourWeather(@NonNull List<PastHourWeather> pastHourWeathers) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(pastHourWeathers);
        realm.commitTransaction();
        realm.close();
    }
}

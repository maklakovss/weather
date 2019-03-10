package com.mss.weather.data.db.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.data.db.mappers.HourWeatherMapper;
import com.mss.weather.data.db.models.HourWeatherDB;
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
        final RealmResults<HourWeatherDB> hourWeatherDBS = realm.where(HourWeatherDB.class)
                .equalTo("isPast", isPast)
                .equalTo("cityID", cityId)
                .greaterThanOrEqualTo("date", date)
                .lessThan("date", endDate.getTime())
                .sort("date")
                .findAll();

        List<HourWeather> dayWeathers = null;
        if (hourWeatherDBS != null) {
            dayWeathers = HourWeatherMapper.mapHourWeatherDBsToHourWeathers(hourWeatherDBS);
        }
        realm.close();
        return dayWeathers;
    }

    @Override
    public void deleteHourWeatherByCityID(@NonNull final String cityId) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<HourWeatherDB> hourWeatherDBRealmResults = realm.where(HourWeatherDB.class)
                .equalTo("cityID", cityId)
                .findAll();
        if (hourWeatherDBRealmResults != null) {
            realm.beginTransaction();
            hourWeatherDBRealmResults.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }

    @Override
    public void deleteOldHourWeatherByCityID(@NonNull final String cityId, @NonNull final Date date) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        final RealmResults<HourWeatherDB> hourWeatherDBRealmResults = realm.where(HourWeatherDB.class)
                .equalTo("isPast", false)
                .equalTo("cityID", cityId)
                .lessThan("date", date)
                .findAll();
        hourWeatherDBRealmResults.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void updateOrInsertHourWeather(@NonNull final List<HourWeather> hourWeathers, boolean isPast) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(HourWeatherMapper.mapHourWeathersToHourWeatherDBs(hourWeathers, isPast));
        realm.commitTransaction();
        realm.close();
    }
}

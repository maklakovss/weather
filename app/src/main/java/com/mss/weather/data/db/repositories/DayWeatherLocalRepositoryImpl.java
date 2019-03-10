package com.mss.weather.data.db.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.data.db.mappers.DayWeatherMapper;
import com.mss.weather.data.db.models.DayWeatherDB;
import com.mss.weather.domain.models.DayWeather;
import com.mss.weather.domain.repositories.DayWeatherLocalRepository;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DayWeatherLocalRepositoryImpl implements DayWeatherLocalRepository {

    @Nullable
    @Override
    public List<DayWeather> getDayWeathersByCityId(@NonNull final String cityId, @NonNull final Date date, boolean isPast) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<DayWeatherDB> dayWeatherDBs = realm.where(DayWeatherDB.class)
                .equalTo("isPast", isPast)
                .equalTo("cityID", cityId)
                .greaterThanOrEqualTo("date", date)
                .sort("date")
                .findAll();

        List<DayWeather> dayWeathers = null;
        if (dayWeatherDBs != null) {
            dayWeathers = DayWeatherMapper.mapDayWeatherDBsToDayWeathers(dayWeatherDBs);
        }
        realm.close();
        return dayWeathers;
    }

    @Nullable
    @Override
    public List<DayWeather> getDayWeathersByCityId(@NonNull final String cityId, @NonNull final Date dateFrom, Date dateTo, boolean isPast) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<DayWeatherDB> dayWeatherDBs = realm.where(DayWeatherDB.class)
                .equalTo("isPast", isPast)
                .equalTo("cityID", cityId)
                .greaterThanOrEqualTo("date", dateFrom)
                .lessThanOrEqualTo("date", dateTo)
                .sort("date")
                .findAll();

        List<DayWeather> dayWeathers = null;
        if (dayWeatherDBs != null) {
            dayWeathers = DayWeatherMapper.mapDayWeatherDBsToDayWeathers(dayWeatherDBs);
        }
        realm.close();
        return dayWeathers;
    }

    @Override
    public void deleteDayWeatherByCityID(@NonNull final String cityId) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<DayWeatherDB> dayWeatherDBRealmResults = realm.where(DayWeatherDB.class)
                .equalTo("cityID", cityId)
                .findAll();
        if (dayWeatherDBRealmResults != null) {
            realm.beginTransaction();
            dayWeatherDBRealmResults.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }

    @Override
    public void deleteOldDayWeatherByCityID(@NonNull final String cityId, @NonNull final Date date) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        final RealmResults<DayWeatherDB> dayWeatherDBs = realm.where(DayWeatherDB.class)
                .equalTo("isPast", false)
                .equalTo("cityID", cityId)
                .lessThan("date", date)
                .findAll();
        dayWeatherDBs.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void updateOrInsertDayWeather(@NonNull final List<DayWeather> dayWeathers, boolean isPast) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(DayWeatherMapper.mapDayWeathersToDayWeatherDBs(dayWeathers, isPast));
        realm.commitTransaction();
        realm.close();
    }
}

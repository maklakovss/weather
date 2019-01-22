package com.mss.weather.data.db.repositories;

import com.mss.weather.data.db.mappers.DayWeatherMapper;
import com.mss.weather.data.db.models.DayWeatherDB;
import com.mss.weather.domain.models.DayWeather;
import com.mss.weather.domain.repositories.DayWeatherLocalRepository;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DayWeatherLocalRepositoryImpl implements DayWeatherLocalRepository {

    @Override
    public List<DayWeather> getDayWeathersByCityId(String cityId, Date date) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<DayWeatherDB> dayWeatherDBs = realm.where(DayWeatherDB.class)
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

    @Override
    public void deleteDayWeatherByCityID(String cityId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<DayWeatherDB> dayWeatherDBRealmResults = realm.where(DayWeatherDB.class)
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
    public void deleteOldDayWeatherByCityID(String cityId, Date date) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<DayWeatherDB> dayWeatherDBs = realm.where(DayWeatherDB.class)
                .equalTo("cityID", cityId)
                .lessThan("date", date)
                .findAll();
        dayWeatherDBs.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void updateOrInsertDayWeather(List<DayWeather> dayWeathers) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(DayWeatherMapper.mapDayWeathersToDayWeatherDBs(dayWeathers));
        realm.commitTransaction();
        realm.close();
    }
}

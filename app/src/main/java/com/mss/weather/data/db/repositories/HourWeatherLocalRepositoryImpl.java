package com.mss.weather.data.db.repositories;

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
    @Override
    public List<HourWeather> getHourWeathersByCityId(String cityId, Date date) {
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(date);
        endDate.roll(Calendar.DATE, 1);

        Realm realm = Realm.getDefaultInstance();
        RealmResults<HourWeatherDB> hourWeatherDBS = realm.where(HourWeatherDB.class)
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
    public void deleteHourWeatherByCityID(String cityId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<HourWeatherDB> hourWeatherDBRealmResults = realm.where(HourWeatherDB.class)
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
    public void deleteOldHourWeatherByCityID(String cityId, Date date) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<HourWeatherDB> hourWeatherDBRealmResults = realm.where(HourWeatherDB.class)
                .equalTo("cityID", cityId)
                .lessThan("date", date)
                .findAll();
        hourWeatherDBRealmResults.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void updateOrInsertHourWeather(List<HourWeather> hourWeathers) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(HourWeatherMapper.mapHourWeathersToHourWeatherDBs(hourWeathers));
        realm.commitTransaction();
        realm.close();
    }
}

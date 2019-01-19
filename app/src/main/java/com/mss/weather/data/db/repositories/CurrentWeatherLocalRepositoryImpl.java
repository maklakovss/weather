package com.mss.weather.data.db.repositories;

import com.mss.weather.data.db.mappers.CurrentWeatherMapper;
import com.mss.weather.data.db.models.CurrentWeatherDB;
import com.mss.weather.domain.models.CurrentWeather;
import com.mss.weather.domain.repositories.CurrentWeatherLocalRepository;

import io.realm.Realm;

public class CurrentWeatherLocalRepositoryImpl implements CurrentWeatherLocalRepository {

    @Override
    public CurrentWeather getCurrentWeatherById(String cityId) {
        Realm realm = Realm.getDefaultInstance();
        CurrentWeatherDB currentWeatherDB = realm.where(CurrentWeatherDB.class)
                .equalTo("cityID", cityId)
                .findFirst();
        CurrentWeather currentWeather = null;
        if (currentWeatherDB != null)
            currentWeather = CurrentWeatherMapper.mapCurrentWeatherDbToCurrentWeather(currentWeatherDB);
        realm.close();
        return currentWeather;
    }

    @Override
    public void deleteCurrentWeather(String cityId) {
        Realm realm = Realm.getDefaultInstance();
        CurrentWeatherDB currentWeatherDB = realm.where(CurrentWeatherDB.class)
                .equalTo("cityID", cityId)
                .findFirst();
        if (currentWeatherDB != null) {
            realm.beginTransaction();
            currentWeatherDB.deleteFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }

    @Override
    public void updateOrInsertCurrentWeather(CurrentWeather currentWeather) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(CurrentWeatherMapper.mapInfoWeatherDBToInfoWeather(currentWeather));
        realm.commitTransaction();
        realm.close();
    }
}

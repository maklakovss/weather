package com.mss.weather.data.db.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mss.weather.data.db.mappers.InfoWeatherMapper;
import com.mss.weather.data.db.models.InfoWeatherDB;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.domain.repositories.InfoWeatherLocalRepository;

import io.realm.Realm;

public class InfoWeatherLocalRepositoryImpl implements InfoWeatherLocalRepository {

    @Nullable
    @Override
    public InfoWeather getInfoWeatherById(@NonNull final String cityId) {
        final Realm realm = Realm.getDefaultInstance();
        final InfoWeatherDB infoWeatherDB = realm.where(InfoWeatherDB.class)
                .equalTo("cityID", cityId)
                .findFirst();
        InfoWeather infoWeather = null;
        if (infoWeatherDB != null)
            infoWeather = InfoWeatherMapper.mapInfoWeatherDbToInfoWeather(infoWeatherDB);
        realm.close();
        return infoWeather;
    }

    @Override
    public void deleteInfoWeather(@NonNull final String cityId) {
        final Realm realm = Realm.getDefaultInstance();
        final InfoWeatherDB infoWeatherDB = realm.where(InfoWeatherDB.class)
                .equalTo("cityID", cityId)
                .findFirst();
        if (infoWeatherDB != null) {
            realm.beginTransaction();
            infoWeatherDB.deleteFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }

    @Override
    public void updateOrInsertInfoWeather(@NonNull final InfoWeather infoWeather) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(InfoWeatherMapper.mapInfoWeatherToInfoWeatherDB(infoWeather));
        realm.commitTransaction();
        realm.close();
    }
}

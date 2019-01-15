package com.mss.weather.data.db.repositories;

import com.mss.weather.data.db.mappers.InfoWeatherMapper;
import com.mss.weather.data.db.models.InfoWeatherDB;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.domain.repositories.InfoWeatherLocalRepository;

import io.realm.Realm;

class InfoWeatherLocalRepositoryImpl implements InfoWeatherLocalRepository {

    @Override
    public InfoWeather getInfoWeatherById(String cityId) {
        Realm realm = Realm.getDefaultInstance();
        InfoWeatherDB infoWeatherDB = realm.where(InfoWeatherDB.class)
                .equalTo("cityID", cityId)
                .findFirst();
        InfoWeather infoWeather = null;
        if (infoWeatherDB != null)
            infoWeather = InfoWeatherMapper.mapInfoWeatherDbToInfoWeather(infoWeatherDB);
        realm.close();
        return infoWeather;
    }

    @Override
    public void deleteInfoWeather(InfoWeather infoWeather) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(InfoWeatherDB.class)
                .equalTo("cityID", infoWeather.getCityID())
                .findFirst()
                .deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void updateOrInsertInfoWeather(InfoWeather infoWeather) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(InfoWeatherMapper.mapInfoWeatherToInfoWeatherDB(infoWeather));
        realm.commitTransaction();
        realm.close();
    }
}

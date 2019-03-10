package com.mss.weather.data.db.repositories;

import android.support.annotation.NonNull;

import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.Settings;
import com.mss.weather.domain.repositories.CityLocalRepository;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class CityLocalRepositoryImpl implements CityLocalRepository {

    private final static String[] locationSortFields = new String[]{"country", "region", "areaName"};
    private final static Sort[] locationSortOrders = new Sort[]{Sort.ASCENDING, Sort.ASCENDING, Sort.ASCENDING};

    @Override
    public City getCityById(@NonNull final String id) {
        final Realm realm = Realm.getDefaultInstance();
        final City result = realm.where(City.class)
                .equalTo("id", id)
                .findFirst();
        City city = null;
        if (result != null) {
            city = realm.copyFromRealm(result);
        }
        realm.close();
        return city;
    }

    @Override
    @NonNull
    public List<City> getCities() {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<City> results = realm
                .where(City.class)
                .sort(locationSortFields, locationSortOrders)
                .findAll();
        List<City> cities = realm.copyFromRealm(results);
        realm.close();
        return cities;
    }

    @Override
    public void addCity(@NonNull final City city) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(city);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void deleteCity(@NonNull final City city) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(City.class)
                .equalTo("id", city.getId())
                .findAll()
                .deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @NonNull
    @Override
    public String getLastCityId() {
        final Realm realm = Realm.getDefaultInstance();
        final Settings settings = realm.where(Settings.class)
                .equalTo("id", 1)
                .findFirst();
        String lastCityId = "";
        if (settings != null)
            lastCityId = settings.getLastCityId();
        realm.close();
        return lastCityId;
    }

    @Override
    public void setLastCityId(@NonNull final String lastCityID) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Settings settingsDB = realm.where(Settings.class)
                .equalTo("id", 1)
                .findFirst();
        if (settingsDB == null) {
            settingsDB = new Settings();
        }
        settingsDB.setLastCityId(lastCityID);
        realm.insertOrUpdate(settingsDB);
        realm.commitTransaction();
        realm.close();
    }
}

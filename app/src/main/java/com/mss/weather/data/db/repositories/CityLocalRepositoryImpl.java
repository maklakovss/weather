package com.mss.weather.data.db.repositories;

import android.support.annotation.NonNull;

import com.mss.weather.data.db.mappers.CityMapper;
import com.mss.weather.data.db.models.CityDB;
import com.mss.weather.data.db.models.SettingsDB;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.repositories.CityLocalRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class CityLocalRepositoryImpl implements CityLocalRepository {

    private final static String[] locationSortFields = new String[]{"country", "region", "areaName"};
    private final static Sort[] locationSortOrders = new Sort[]{Sort.ASCENDING, Sort.ASCENDING, Sort.ASCENDING};

    @Override
    public City getCityById(@NonNull String id) {
        Realm realm = Realm.getDefaultInstance();
        CityDB cityDB = realm.where(CityDB.class)
                .equalTo("id", id)
                .findFirst();
        City city = null;
        if (cityDB != null)
            city = CityMapper.mapCityDbToCity(cityDB);
        realm.close();
        return city;
    }

    @Override
    @NonNull
    public List<City> getCities() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<CityDB> results = realm
                .where(CityDB.class)
                .sort(locationSortFields, locationSortOrders)
                .findAll();

        List<City> cities = new ArrayList<>(results.size());
        for (CityDB result : results) {
            cities.add(CityMapper.mapCityDbToCity(result));
        }
        realm.close();
        return cities;
    }

    @Override
    public void addCity(@NonNull City city) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(CityMapper.mapCityToCityDb(city));
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void deleteCity(@NonNull City city) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(CityDB.class)
                .equalTo("id", city.getId())
                .findFirst()
                .deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public String getLastCityId() {
        Realm realm = Realm.getDefaultInstance();
        SettingsDB settingsDB = realm.where(SettingsDB.class)
                .equalTo("id", 1)
                .findFirst();
        String lastCityId = "";
        if (settingsDB != null)
            lastCityId = settingsDB.getLasCityId();
        realm.close();
        return lastCityId;
    }

    @Override
    public void setLastCityId(String lastCityID) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        SettingsDB settingsDB = realm.where(SettingsDB.class)
                .equalTo("id", 1)
                .findFirst();
        if (settingsDB == null) {
            settingsDB = new SettingsDB();
        }
        settingsDB.setLasCityId(lastCityID);
        realm.insertOrUpdate(settingsDB);
        realm.commitTransaction();
        realm.close();
    }
}

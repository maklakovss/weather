package com.mss.weather.data.db;

import android.support.annotation.NonNull;

import com.mss.weather.data.db.model.CityDB;
import com.mss.weather.data.db.model.SettingsDB;
import com.mss.weather.domain.LocalRepository;
import com.mss.weather.domain.models.City;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class LocalRepositoryImpl implements LocalRepository {

    private final static String[] locationSortFields = new String[]{"country", "region", "areaName"};
    private final static Sort[] locationSortOrders = new Sort[]{Sort.ASCENDING, Sort.ASCENDING, Sort.ASCENDING};

    @Override
    @NonNull
    public City getCityById(@NonNull String id) {
        Realm realm = Realm.getDefaultInstance();
        realm.where(CityDB.class)
                .equalTo("id", id)
                .findFirst();
        City city = mapCityDbToCity(realm.where(CityDB.class)
                .equalTo("id", id)
                .findFirst());
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
            cities.add(mapCityDbToCity(result));
        }
        realm.close();
        return cities;
    }

    @Override
    public void addCity(@NonNull City city) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(mapCityToCityDb(city));
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

    @NonNull
    private City mapCityDbToCity(@NonNull CityDB cityDB) {
        City city = new City();
        city.setId(cityDB.getId());
        city.setAreaName(cityDB.getAreaName());
        city.setRegion(cityDB.getRegion());
        city.setCountry(cityDB.getCountry());
        city.setTimeZone(cityDB.getTimeZone());
        city.setTimeOffset(cityDB.getTimeOffset());
        city.setPopulation(cityDB.getPopulation());
        city.setLongitude(cityDB.getLongitude());
        city.setLatitude(cityDB.getLatitude());
        return city;
    }

    @NonNull
    private CityDB mapCityToCityDb(@NonNull City city) {
        CityDB cityDB = new CityDB();
        cityDB.setId(city.getId());
        cityDB.setAreaName(city.getAreaName());
        cityDB.setRegion(city.getRegion());
        cityDB.setCountry(city.getCountry());
        cityDB.setTimeZone(city.getTimeZone());
        cityDB.setTimeOffset(city.getTimeOffset());
        cityDB.setPopulation(city.getPopulation());
        cityDB.setLongitude(city.getLongitude());
        cityDB.setLatitude(city.getLatitude());
        return cityDB;
    }
}

package com.mss.weather.data.db;

import android.support.annotation.NonNull;

import com.mss.weather.domain.city.models.City;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmRepository {

    private final static String[] locationSortFields = new String[]{"country", "region", "areaName"};
    private final static Sort[] locationSortOrders = new Sort[]{Sort.ASCENDING, Sort.ASCENDING, Sort.ASCENDING};

    @NonNull
    public City getCityById(@NonNull String id) {
        return mapCityDbToCity(Realm.getDefaultInstance()
                .where(CityDB.class)
                .equalTo("id", id)
                .findFirst());
    }

    @NonNull
    public List<City> getAllCities() {
        RealmResults<CityDB> results = Realm.getDefaultInstance()
                .where(CityDB.class)
                .sort(locationSortFields, locationSortOrders)
                .findAll();

        List<City> cities = new ArrayList<>(results.size());
        for (CityDB result : results) {
            cities.add(mapCityDbToCity(result));
        }
        return cities;
    }

    public void insertCity(@NonNull City city) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(mapCityToCityDb(city));
        realm.commitTransaction();
    }

    public void deleteCity(@NonNull City city) {
        Realm realm = Realm.getDefaultInstance();
        realm.where(CityDB.class)
                .equalTo("id", city.getId())
                .findFirst()
                .deleteFromRealm();
        realm.commitTransaction();
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

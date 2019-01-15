package com.mss.weather.data.db.mappers;

import android.support.annotation.NonNull;

import com.mss.weather.data.db.models.CityDB;
import com.mss.weather.domain.models.City;

public class CityMapper {
    @NonNull
    public static City mapCityDbToCity(@NonNull CityDB cityDB) {
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
    public static CityDB mapCityToCityDb(@NonNull City city) {
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

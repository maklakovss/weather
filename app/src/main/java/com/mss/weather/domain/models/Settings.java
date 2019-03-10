package com.mss.weather.domain.models;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class Settings implements RealmModel {

    @PrimaryKey
    private int id = 1;

    private String lastCityId;

    public String getLastCityId() {
        return lastCityId;
    }

    public void setLastCityId(String lastCityId) {
        this.lastCityId = lastCityId;
    }
}

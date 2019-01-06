package com.mss.weather.data.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class CityDB extends RealmObject {
    @PrimaryKey
    private String id;
    private String areaName;
    private String country;
    private String region;
    private float latitude;
    private float longitude;
    private long population;
    private float timeOffset;
    private String timeZone;
}
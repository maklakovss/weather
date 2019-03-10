package com.mss.weather.data.db.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class DayWeatherDB extends RealmObject {

    @PrimaryKey
    private String id;

    @Index
    private boolean isPast;
    @Index
    private String cityID;
    @Index
    private Date date;

    private Date sunrise;
    private Date sunset;
    private Date moonrise;
    private Date moonset;
    private String moonPhase;
    private int moonIllumination;
    private int maxTempC;
    private int maxTempF;
    private int minTempC;
    private int minTempF;
    private int maxWeatherCode;
    private String maxWeatherIconUrl;
    private int minWindspeedMiles;
    private int minWindspeedKmph;
    private int maxWindspeedMiles;
    private int maxWindspeedKmph;
    private float totalSnowCm;
    private float sunHour;
    private int uvIndex;
}

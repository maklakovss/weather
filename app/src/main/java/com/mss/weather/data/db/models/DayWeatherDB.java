package com.mss.weather.data.db.models;

import java.util.Date;
import java.util.List;

import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class DayWeatherDB {

    @PrimaryKey
    private String id;

    private String cityID;
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
    private float totalSnowCm;
    private float sunHour;
    private int uvIndex;
    private List<HourWeatherDB> hourly;
}

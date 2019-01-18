package com.mss.weather.data.db.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class CurrentWeatherDB extends RealmObject {

    @PrimaryKey
    private String cityID;

    private Date date;

    private Date observationTime;
    private int tempC;
    private int tempF;
    private int weatherCode;
    private String weatherIconUrl;
    private String weatherDesc;
    private String weatherDescLocalLanguage;
    private int windspeedMiles;
    private int windspeedKmph;
    private int winddirDegree;
    private String winddir16Point;
    private float precipMM;
    private int humidity;
    private int visibility;
    private int pressure;
    private int cloudcover;
    private int feelsLikeC;
    private int feelsLikeF;
}

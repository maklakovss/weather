package com.mss.weather.data.db.models;

import java.util.Date;

import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class HourWeatherDB {

    @PrimaryKey
    private String id;

    private String cityID;
    private Date date;

    private int tempC;
    private int tempF;
    private int windspeedMiles;
    private int windspeedKmph;
    private int winddirDegree;
    private String winddir16Point;
    private int weatherCode;
    private String weatherIconUrl;
    private String weatherDesc;
    private String langRu = null;
    private float precipMM;
    private int humidity;
    private int visibility;
    private int pressure;
    private int cloudcover;
    private int heatIndexC;
    private int heatIndexF;
    private int dewPointC;
    private int dewPointF;
    private int windChillC;
    private int windChillF;
    private int windGustMiles;
    private int windGustKmph;
    private int feelsLikeC;
    private int feelsLikeF;
    private ChanceWeatherDB chanceWeather;
}
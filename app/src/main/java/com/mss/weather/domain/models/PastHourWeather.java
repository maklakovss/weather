package com.mss.weather.domain.models;

import lombok.Data;

@Data
public class PastHourWeather {
    private String cityID;
    private Integer year;
    private Integer month;
    private Integer hour;

    private int tempC;
    private int windSpeedKmph;
    private float precipMM;
    private int humidity;
    private int visibility;
    private int pressure;
    private int cloudCover;
    private int heatIndexC;
    private int dewPointC;
    private int windChillC;
    private int windGustKmph;
    private int feelsLikeC;
}

package com.mss.weather.domain.models;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class DayWeather {
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
    private int maxWeatherCode;
    private String maxWeatherIconUrl;
    private int minWindspeedMiles;
    private int minWindspeedKmph;
    private int maxWindspeedMiles;
    private int maxWindspeedKmph;
    private float totalSnowCm;
    private float sunHour;
    private int uvIndex;
    private List<HourWeather> hourly;
}

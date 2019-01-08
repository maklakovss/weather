package com.mss.weather.domain.models;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class WeatherDay {
    private String cityID;
    private Date date;

    private Date sunrise;
    private Date sunset;
    private Date moonrise;
    private Date moonset;
    private String moonPhase;
    private int moonIllumination;
    private int maxtempC;
    private int maxtempF;
    private int mintempC;
    private int mintempF;
    private float totalSnowCm;
    private float sunHour;
    private int uvIndex;
    private List<WeatherHour> hourly;
}

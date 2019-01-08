package com.mss.weather.domain.models;

import java.util.Date;

import lombok.Data;

@Data
public class WeatherChance {
    private String cityID;
    private Date date;

    private int chanceofrain;
    private int chanceofremdry;
    private int chanceofwindy;
    private int chanceofovercast;
    private int chanceofsunshine;
    private int chanceoffrost;
    private int chanceofhightemp;
    private int chanceoffog;
    private int chanceofsnow;
    private int chanceofthunder;
}

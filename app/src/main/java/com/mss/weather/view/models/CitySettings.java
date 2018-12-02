package com.mss.weather.view.models;

import lombok.Data;

@Data
public class CitySettings {
    String name;
    boolean showSunrise;
    boolean showSunset;
    boolean showClouds;
    boolean showTemp;
    boolean showTempRange;
    boolean showHumidity;
    boolean showPressure;
    boolean showWindSpeed;
    boolean showWindDeg;
    boolean showSnow;
    boolean showRain;
}

package com.mss.weather.presentation.view.models;

import lombok.Data;

@Data
public class CitySettings {
    String name = "";
    boolean showSunrise = true;
    boolean showSunset = true;
    boolean showClouds = true;
    boolean showTemp = true;
    boolean showTempRange = true;
    boolean showHumidity = true;
    boolean showPressure = true;
    boolean showWindSpeed = true;
    boolean showWindDeg = true;
    boolean showSnow = true;
    boolean showRain = true;

    public CitySettings(String name) {
        this.name = name;
    }

    public CitySettings() {
    }
}

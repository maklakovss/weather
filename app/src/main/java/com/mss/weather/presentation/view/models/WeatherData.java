package com.mss.weather.presentation.view.models;

import java.util.Date;

import lombok.Data;

@Data
public class WeatherData {
    String cityName;
    Date sunrise = new Date();
    Date sunset = new Date();
    float cloudsPercent;
    String cloudsDescription;
    float temp;
    float tempMin;
    float tempMax;
    float humidity;
    float pressure;
    float windSpeed;
    float windDeg;
    float snow;
    float rain;
}

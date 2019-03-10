package com.mss.weather.domain.models;

import android.support.annotation.NonNull;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class PastHourWeather implements RealmModel {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String cityID;

    @NonNull
    private int year;

    @NonNull
    private int month;

    @NonNull
    private int day;

    @NonNull
    private int hour;

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

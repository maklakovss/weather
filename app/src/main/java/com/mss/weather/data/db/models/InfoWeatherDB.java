package com.mss.weather.data.db.models;

import java.util.Date;
import java.util.List;

import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class InfoWeatherDB {

    @PrimaryKey
    private String id;

    private Date dateState;
    private String cityID;

    private List<DayWeatherDB> days;
    private CurrentWeatherDB currentWeather;
}

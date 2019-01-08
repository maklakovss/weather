package com.mss.weather.domain.models;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class WeatherInfo {
    private Date dateState;
    private String cityID;

    private List<WeatherDay> days;
    private WeatherCurrent weatherCurrent;
}

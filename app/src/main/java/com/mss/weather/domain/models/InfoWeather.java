package com.mss.weather.domain.models;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class InfoWeather {
    private Date dateState;
    private String cityID;

    private List<DayWeather> days;
    private CurrentWeather currentWeather;
}

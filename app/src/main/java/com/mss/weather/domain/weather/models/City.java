package com.mss.weather.domain.weather.models;

import lombok.Data;

@Data
public class City {
    String id;
    String areaName;
    String country;
    String region;
    float latitude;
    float longitude;
    long population;
    float timeOffset;
    String timeZone;

    public City(String areaName) {
        this.areaName = areaName;
    }

    public City() {
        areaName = "";
    }
}

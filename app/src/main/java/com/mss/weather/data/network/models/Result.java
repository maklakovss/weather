
package com.mss.weather.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Result {

    @SerializedName("areaName")
    @Expose
    private List<AreaName> areaName = null;
    @SerializedName("country")
    @Expose
    private List<Country> country = null;
    @SerializedName("region")
    @Expose
    private List<Region> region = null;
    @SerializedName("latitude")
    @Expose
    private float latitude;
    @SerializedName("longitude")
    @Expose
    private float longitude;
    @SerializedName("population")
    @Expose
    private long population;
    @SerializedName("weatherUrl")
    @Expose
    private List<WeatherUrl> weatherUrl = null;
    @SerializedName("timezone")
    @Expose
    private TimeZone timezone;

}

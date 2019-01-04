
package com.mss.weather.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Result {

    @SerializedName("areaName")
    @Expose
    public List<AreaName> areaName = null;
    @SerializedName("country")
    @Expose
    public List<Country> country = null;
    @SerializedName("region")
    @Expose
    public List<Region> region = null;
    @SerializedName("latitude")
    @Expose
    public float latitude;
    @SerializedName("longitude")
    @Expose
    public float longitude;
    @SerializedName("population")
    @Expose
    public long population;
    @SerializedName("weatherUrl")
    @Expose
    public List<WeatherUrl> weatherUrl = null;
    @SerializedName("timezone")
    @Expose
    public Timezone timezone;

}

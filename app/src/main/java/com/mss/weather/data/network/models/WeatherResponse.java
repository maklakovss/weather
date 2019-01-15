
package com.mss.weather.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@lombok.Data
public class WeatherResponse {

    @SerializedName("data")
    @Expose
    private Data data;

}

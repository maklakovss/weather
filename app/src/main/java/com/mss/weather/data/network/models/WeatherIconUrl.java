
package com.mss.weather.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class WeatherIconUrl {

    @SerializedName("value")
    @Expose
    private String value;

}

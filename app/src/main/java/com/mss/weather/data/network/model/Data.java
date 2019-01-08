
package com.mss.weather.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@lombok.Data
public class Data {

    @SerializedName("request")
    @Expose
    private List<Request> request = null;
    @SerializedName("current_condition")
    @Expose
    private List<CurrentCondition> currentCondition = null;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;

}

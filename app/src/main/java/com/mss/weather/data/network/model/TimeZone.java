
package com.mss.weather.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class TimeZone {

    @SerializedName("offset")
    @Expose
    private float offset;
    @SerializedName("zone")
    @Expose
    private String zone;

}


package com.mss.weather.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Weather {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("astronomy")
    @Expose
    private List<Astronomy> astronomy = null;
    @SerializedName("maxtempC")
    @Expose
    private int maxTempC;
    @SerializedName("maxtempF")
    @Expose
    private int maxTempF;
    @SerializedName("mintempC")
    @Expose
    private int minTempC;
    @SerializedName("mintempF")
    @Expose
    private int minTempF;
    @SerializedName("totalSnow_cm")
    @Expose
    private float totalSnowCm;
    @SerializedName("sunHour")
    @Expose
    private float sunHour;
    @SerializedName("uvIndex")
    @Expose
    private int uvIndex;
    @SerializedName("hourly")
    @Expose
    private List<Hourly> hourly = null;

}

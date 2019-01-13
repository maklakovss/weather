
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
    private String maxTempC;
    @SerializedName("maxtempF")
    @Expose
    private String maxTempF;
    @SerializedName("mintempC")
    @Expose
    private String minTempC;
    @SerializedName("mintempF")
    @Expose
    private String minTempF;
    @SerializedName("totalSnow_cm")
    @Expose
    private String totalSnowCm;
    @SerializedName("sunHour")
    @Expose
    private String sunHour;
    @SerializedName("uvIndex")
    @Expose
    private String uvIndex;
    @SerializedName("hourly")
    @Expose
    private List<Hourly> hourly = null;

}

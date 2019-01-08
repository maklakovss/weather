
package com.mss.weather.data.network.model;

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
    private String maxtempC;
    @SerializedName("maxtempF")
    @Expose
    private String maxtempF;
    @SerializedName("mintempC")
    @Expose
    private String mintempC;
    @SerializedName("mintempF")
    @Expose
    private String mintempF;
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

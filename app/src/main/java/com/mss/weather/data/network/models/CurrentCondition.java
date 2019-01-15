
package com.mss.weather.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class CurrentCondition {

    @SerializedName("observation_time")
    @Expose
    private String observationTime;
    @SerializedName("temp_C")
    @Expose
    private int tempC;
    @SerializedName("temp_F")
    @Expose
    private int tempF;
    @SerializedName("weatherCode")
    @Expose
    private int weatherCode;
    @SerializedName("weatherIconUrl")
    @Expose
    private List<WeatherIconUrl> weatherIconUrl = null;
    @SerializedName("weatherDesc")
    @Expose
    private List<WeatherDesc> weatherDesc = null;
    @SerializedName("lang_ru")
    @Expose
    private List<LangRu> langRu = null;
    @SerializedName("windspeedMiles")
    @Expose
    private int windspeedMiles;
    @SerializedName("windspeedKmph")
    @Expose
    private int windspeedKmph;
    @SerializedName("winddirDegree")
    @Expose
    private int winddirDegree;
    @SerializedName("winddir16Point")
    @Expose
    private String winddir16Point;
    @SerializedName("precipMM")
    @Expose
    private float precipMM;
    @SerializedName("humidity")
    @Expose
    private float humidity;
    @SerializedName("visibility")
    @Expose
    private int visibility;
    @SerializedName("pressure")
    @Expose
    private int pressure;
    @SerializedName("cloudcover")
    @Expose
    private int cloudcover;
    @SerializedName("FeelsLikeC")
    @Expose
    private int feelsLikeC;
    @SerializedName("FeelsLikeF")
    @Expose
    private int feelsLikeF;

}

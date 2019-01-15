
package com.mss.weather.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Hourly {

    @SerializedName("time")
    @Expose
    private int time;
    @SerializedName("tempC")
    @Expose
    private int tempC;
    @SerializedName("tempF")
    @Expose
    private int tempF;
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
    @SerializedName("precipMM")
    @Expose
    private float precipMM;
    @SerializedName("humidity")
    @Expose
    private int humidity;
    @SerializedName("visibility")
    @Expose
    private int visibility;
    @SerializedName("pressure")
    @Expose
    private int pressure;
    @SerializedName("cloudcover")
    @Expose
    private int cloudcover;
    @SerializedName("HeatIndexC")
    @Expose
    private int heatIndexC;
    @SerializedName("HeatIndexF")
    @Expose
    private int heatIndexF;
    @SerializedName("DewPointC")
    @Expose
    private int dewPointC;
    @SerializedName("DewPointF")
    @Expose
    private int dewPointF;
    @SerializedName("WindChillC")
    @Expose
    private int windChillC;
    @SerializedName("WindChillF")
    @Expose
    private int windChillF;
    @SerializedName("WindGustMiles")
    @Expose
    private int windGustMiles;
    @SerializedName("WindGustKmph")
    @Expose
    private int windGustKmph;
    @SerializedName("FeelsLikeC")
    @Expose
    private int feelsLikeC;
    @SerializedName("FeelsLikeF")
    @Expose
    private int feelsLikeF;
    @SerializedName("chanceofrain")
    @Expose
    private int chanceofrain;
    @SerializedName("chanceofremdry")
    @Expose
    private int chanceofremdry;
    @SerializedName("chanceofwindy")
    @Expose
    private int chanceofwindy;
    @SerializedName("chanceofovercast")
    @Expose
    private int chanceofovercast;
    @SerializedName("chanceofsunshine")
    @Expose
    private int chanceofsunshine;
    @SerializedName("chanceoffrost")
    @Expose
    private int chanceoffrost;
    @SerializedName("chanceofhightemp")
    @Expose
    private int chanceofhightemp;
    @SerializedName("chanceoffog")
    @Expose
    private int chanceoffog;
    @SerializedName("chanceofsnow")
    @Expose
    private int chanceofsnow;
    @SerializedName("chanceofthunder")
    @Expose
    private int chanceofthunder;

}

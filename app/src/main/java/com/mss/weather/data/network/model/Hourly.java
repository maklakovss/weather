
package com.mss.weather.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Hourly {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("tempC")
    @Expose
    private String tempC;
    @SerializedName("tempF")
    @Expose
    private String tempF;
    @SerializedName("windspeedMiles")
    @Expose
    private String windspeedMiles;
    @SerializedName("windspeedKmph")
    @Expose
    private String windspeedKmph;
    @SerializedName("winddirDegree")
    @Expose
    private String winddirDegree;
    @SerializedName("winddir16Point")
    @Expose
    private String winddir16Point;
    @SerializedName("weatherCode")
    @Expose
    private String weatherCode;
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
    private String precipMM;
    @SerializedName("humidity")
    @Expose
    private String humidity;
    @SerializedName("visibility")
    @Expose
    private String visibility;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("cloudcover")
    @Expose
    private String cloudcover;
    @SerializedName("HeatIndexC")
    @Expose
    private String heatIndexC;
    @SerializedName("HeatIndexF")
    @Expose
    private String heatIndexF;
    @SerializedName("DewPointC")
    @Expose
    private String dewPointC;
    @SerializedName("DewPointF")
    @Expose
    private String dewPointF;
    @SerializedName("WindChillC")
    @Expose
    private String windChillC;
    @SerializedName("WindChillF")
    @Expose
    private String windChillF;
    @SerializedName("WindGustMiles")
    @Expose
    private String windGustMiles;
    @SerializedName("WindGustKmph")
    @Expose
    private String windGustKmph;
    @SerializedName("FeelsLikeC")
    @Expose
    private String feelsLikeC;
    @SerializedName("FeelsLikeF")
    @Expose
    private String feelsLikeF;
    @SerializedName("chanceofrain")
    @Expose
    private String chanceofrain;
    @SerializedName("chanceofremdry")
    @Expose
    private String chanceofremdry;
    @SerializedName("chanceofwindy")
    @Expose
    private String chanceofwindy;
    @SerializedName("chanceofovercast")
    @Expose
    private String chanceofovercast;
    @SerializedName("chanceofsunshine")
    @Expose
    private String chanceofsunshine;
    @SerializedName("chanceoffrost")
    @Expose
    private String chanceoffrost;
    @SerializedName("chanceofhightemp")
    @Expose
    private String chanceofhightemp;
    @SerializedName("chanceoffog")
    @Expose
    private String chanceoffog;
    @SerializedName("chanceofsnow")
    @Expose
    private String chanceofsnow;
    @SerializedName("chanceofthunder")
    @Expose
    private String chanceofthunder;

}

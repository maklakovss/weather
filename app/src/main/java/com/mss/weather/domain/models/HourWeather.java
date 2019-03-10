package com.mss.weather.domain.models;

import java.util.Date;

import io.realm.RealmModel;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class HourWeather implements RealmModel {

    @PrimaryKey
    private String id;

    @Index
    private boolean isPast;
    @Index
    private String cityID;
    @Index
    private Date date;

    private int tempC;
    private int tempF;
    private int windspeedMiles;
    private int windspeedKmph;
    private int winddirDegree;
    private String winddir16Point;
    private int weatherCode;
    private String weatherIconUrl;
    private String weatherDesc;
    private String langRu = null;
    private float precipMM;
    private int humidity;
    private int visibility;
    private int pressure;
    private int cloudcover;
    private int heatIndexC;
    private int heatIndexF;
    private int dewPointC;
    private int dewPointF;
    private int windChillC;
    private int windChillF;
    private int windGustMiles;
    private int windGustKmph;
    private int feelsLikeC;
    private int feelsLikeF;
    private int chanceofrain;
    private int chanceofremdry;
    private int chanceofwindy;
    private int chanceofovercast;
    private int chanceofsunshine;
    private int chanceoffrost;
    private int chanceofhightemp;
    private int chanceoffog;
    private int chanceofsnow;
    private int chanceofthunder;

    private void updateId() {
        if (getCityID() != null && getDate() != null)
            setId(getCityID() + " " + getDate().toString());

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPast() {
        return isPast;
    }

    public void setPast(boolean past) {
        isPast = past;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
        updateId();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        updateId();
    }

    public int getTempC() {
        return tempC;
    }

    public void setTempC(int tempC) {
        this.tempC = tempC;
    }

    public int getTempF() {
        return tempF;
    }

    public void setTempF(int tempF) {
        this.tempF = tempF;
    }

    public int getWindspeedMiles() {
        return windspeedMiles;
    }

    public void setWindspeedMiles(int windspeedMiles) {
        this.windspeedMiles = windspeedMiles;
    }

    public int getWindspeedKmph() {
        return windspeedKmph;
    }

    public void setWindspeedKmph(int windspeedKmph) {
        this.windspeedKmph = windspeedKmph;
    }

    public int getWinddirDegree() {
        return winddirDegree;
    }

    public void setWinddirDegree(int winddirDegree) {
        this.winddirDegree = winddirDegree;
    }

    public String getWinddir16Point() {
        return winddir16Point;
    }

    public void setWinddir16Point(String winddir16Point) {
        this.winddir16Point = winddir16Point;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(String weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getLangRu() {
        return langRu;
    }

    public void setLangRu(String langRu) {
        this.langRu = langRu;
    }

    public float getPrecipMM() {
        return precipMM;
    }

    public void setPrecipMM(float precipMM) {
        this.precipMM = precipMM;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(int cloudcover) {
        this.cloudcover = cloudcover;
    }

    public int getHeatIndexC() {
        return heatIndexC;
    }

    public void setHeatIndexC(int heatIndexC) {
        this.heatIndexC = heatIndexC;
    }

    public int getHeatIndexF() {
        return heatIndexF;
    }

    public void setHeatIndexF(int heatIndexF) {
        this.heatIndexF = heatIndexF;
    }

    public int getDewPointC() {
        return dewPointC;
    }

    public void setDewPointC(int dewPointC) {
        this.dewPointC = dewPointC;
    }

    public int getDewPointF() {
        return dewPointF;
    }

    public void setDewPointF(int dewPointF) {
        this.dewPointF = dewPointF;
    }

    public int getWindChillC() {
        return windChillC;
    }

    public void setWindChillC(int windChillC) {
        this.windChillC = windChillC;
    }

    public int getWindChillF() {
        return windChillF;
    }

    public void setWindChillF(int windChillF) {
        this.windChillF = windChillF;
    }

    public int getWindGustMiles() {
        return windGustMiles;
    }

    public void setWindGustMiles(int windGustMiles) {
        this.windGustMiles = windGustMiles;
    }

    public int getWindGustKmph() {
        return windGustKmph;
    }

    public void setWindGustKmph(int windGustKmph) {
        this.windGustKmph = windGustKmph;
    }

    public int getFeelsLikeC() {
        return feelsLikeC;
    }

    public void setFeelsLikeC(int feelsLikeC) {
        this.feelsLikeC = feelsLikeC;
    }

    public int getFeelsLikeF() {
        return feelsLikeF;
    }

    public void setFeelsLikeF(int feelsLikeF) {
        this.feelsLikeF = feelsLikeF;
    }

    public int getChanceofrain() {
        return chanceofrain;
    }

    public void setChanceofrain(int chanceofrain) {
        this.chanceofrain = chanceofrain;
    }

    public int getChanceofremdry() {
        return chanceofremdry;
    }

    public void setChanceofremdry(int chanceofremdry) {
        this.chanceofremdry = chanceofremdry;
    }

    public int getChanceofwindy() {
        return chanceofwindy;
    }

    public void setChanceofwindy(int chanceofwindy) {
        this.chanceofwindy = chanceofwindy;
    }

    public int getChanceofovercast() {
        return chanceofovercast;
    }

    public void setChanceofovercast(int chanceofovercast) {
        this.chanceofovercast = chanceofovercast;
    }

    public int getChanceofsunshine() {
        return chanceofsunshine;
    }

    public void setChanceofsunshine(int chanceofsunshine) {
        this.chanceofsunshine = chanceofsunshine;
    }

    public int getChanceoffrost() {
        return chanceoffrost;
    }

    public void setChanceoffrost(int chanceoffrost) {
        this.chanceoffrost = chanceoffrost;
    }

    public int getChanceofhightemp() {
        return chanceofhightemp;
    }

    public void setChanceofhightemp(int chanceofhightemp) {
        this.chanceofhightemp = chanceofhightemp;
    }

    public int getChanceoffog() {
        return chanceoffog;
    }

    public void setChanceoffog(int chanceoffog) {
        this.chanceoffog = chanceoffog;
    }

    public int getChanceofsnow() {
        return chanceofsnow;
    }

    public void setChanceofsnow(int chanceofsnow) {
        this.chanceofsnow = chanceofsnow;
    }

    public int getChanceofthunder() {
        return chanceofthunder;
    }

    public void setChanceofthunder(int chanceofthunder) {
        this.chanceofthunder = chanceofthunder;
    }
}

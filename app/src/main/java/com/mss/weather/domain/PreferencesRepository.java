package com.mss.weather.domain;

public interface PreferencesRepository {

    String getDefaultCityName();

    void setDefaultCityName(String cityName);
}

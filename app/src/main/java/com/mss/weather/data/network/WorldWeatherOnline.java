package com.mss.weather.data.network;

import com.mss.weather.data.network.model.response.CitiesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WorldWeatherOnline {
    @GET("premium/v1/search.ashx")
    Observable<CitiesResponse> getCities(@Query("q") String startWith, @Query("key") String key, @Query("format") String format);
}

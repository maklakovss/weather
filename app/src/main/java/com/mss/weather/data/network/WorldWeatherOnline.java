package com.mss.weather.data.network;

import com.mss.weather.data.network.model.CitiesResponse;
import com.mss.weather.data.network.model.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WorldWeatherOnline {

    @GET("premium/v1/search.ashx")
    Observable<CitiesResponse> getCities(@Query("q") String startWith,
                                         @Query("num_of_results") int numOfResults,
                                         @Query("key") String key,
                                         @Query("format") String format);

    @GET("premium/v1/weather.ashx")
    Observable<WeatherResponse> getWeather(@Query("q") String query,
                                           @Query("key") String key,
                                           @Query("format") String format,
                                           @Query("num_of_days") int numOfDays,
                                           @Query("fx") String allowsWeather,
                                           @Query("cc") String allowCurrentWeather,
                                           @Query("mca") String allowMonthlyClimate,
                                           @Query("includelocation") String includeLocation,
                                           @Query("tp") int timePeriod,
                                           @Query("showlocaltime") String showLocalTime,
                                           @Query("lang") String language);

    @GET("premium/v1/past-weather.ashx")
    Observable<WeatherResponse> getPastWeather(@Query("q") String query,
                                               @Query("key") String key,
                                               @Query("format") String format,
                                               @Query("date") String startDate,
                                               @Query("enddate") String endDate,
                                               @Query("includelocation") String includeLocation,
                                               @Query("tp") int timePeriod,
                                               @Query("lang") String language);
}

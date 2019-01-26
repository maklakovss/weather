package com.mss.weather.data.network;

import android.support.annotation.NonNull;

import com.mss.weather.data.network.models.CitiesResponse;
import com.mss.weather.data.network.models.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WorldWeatherOnline {

    @NonNull
    @GET("premium/v1/search.ashx")
    Observable<CitiesResponse> getCities(@NonNull @Query("q") final String startWith,
                                         @Query("num_of_results") int numOfResults,
                                         @NonNull @Query("key") final String key,
                                         @NonNull @Query("format") String format);

    @NonNull
    @GET("premium/v1/weather.ashx")
    Observable<WeatherResponse> getWeather(@NonNull @Query("q") final String query,
                                           @NonNull @Query("key") final String key,
                                           @NonNull @Query("format") final String format,
                                           @Query("num_of_days") int numOfDays,
                                           @NonNull @Query("fx") final String allowsWeather,
                                           @NonNull @Query("cc") final String allowCurrentWeather,
                                           @NonNull @Query("mca") final String allowMonthlyClimate,
                                           @NonNull @Query("includelocation") final String includeLocation,
                                           @Query("tp") int timePeriod,
                                           @NonNull @Query("showlocaltime") final String showLocalTime,
                                           @NonNull @Query("lang") final String language);

    @NonNull
    @GET("premium/v1/past-weather.ashx")
    Observable<WeatherResponse> getPastWeather(@NonNull @Query("q") final String query,
                                               @NonNull @Query("key") final String key,
                                               @NonNull @Query("format") final String format,
                                               @NonNull @Query("date") final String startDate,
                                               @NonNull @Query("enddate") final String endDate,
                                               @NonNull @Query("includelocation") final String includeLocation,
                                               @Query("tp") int timePeriod,
                                               @NonNull @Query("lang") final String language);
}

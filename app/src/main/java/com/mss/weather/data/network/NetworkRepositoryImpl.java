package com.mss.weather.data.network;

import android.support.annotation.NonNull;

import com.mss.weather.BuildConfig;
import com.mss.weather.data.network.mappers.CitiesResponseMapper;
import com.mss.weather.data.network.mappers.WeatherResponseMapper;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.domain.models.Position;
import com.mss.weather.domain.repositories.NetworkRepository;

import java.util.List;

import io.reactivex.Maybe;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkRepositoryImpl implements NetworkRepository {

    private static final String BASE_URL = "http://api.worldweatheronline.com/";
    private static final String FORMAT = "json";
    private static final String KEY = "db8d42d95fb54c8eaf6140043192402";

    private final WorldWeatherOnline worldWeatherOnline;

    public NetworkRepositoryImpl() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();

        worldWeatherOnline = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
                .create(WorldWeatherOnline.class);
    }


    @NonNull
    @Override
    public Maybe<List<City>> getAutoCompleteCities(@NonNull final String startWith) {
        return worldWeatherOnline.getCities(startWith, 200, KEY, FORMAT)
                .map(CitiesResponseMapper::mapCitiesResponseToCity)
                .firstElement();
    }

    @NonNull
    @Override
    public Maybe<List<City>> getCitiesByCoordinate(@NonNull final Position position) {
        final String query = String.valueOf(position.getLatitude()) + "," + String.valueOf(position.getLongitude());
        return worldWeatherOnline.getCities(query, 200, KEY, FORMAT)
                .map(CitiesResponseMapper::mapCitiesResponseToCity)
                .firstElement();
    }

    @NonNull
    @Override
    public Maybe<InfoWeather> getWeatherInfo(@NonNull final City city) {
        return worldWeatherOnline.getWeather(city.getId(),
                KEY,
                FORMAT,
                14,
                "yes",
                "yes",
                "no",
                "no",
                1,
                "no",
                "ru")
                .map(weatherResponse -> WeatherResponseMapper.mapWeatherResponseToWeatherInfo(weatherResponse, city))
                .firstElement();
    }
}

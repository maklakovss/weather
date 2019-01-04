package com.mss.weather.data.repositories;

import com.mss.weather.BuildConfig;
import com.mss.weather.data.network.WorldWeatherOnline;
import com.mss.weather.data.network.model.response.CitiesResponse;
import com.mss.weather.domain.city.CityRepository;
import com.mss.weather.domain.city.models.City;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

class CityRepositoryImpl implements CityRepository {

    private static final String BASE_URL = "http://api.worldweatheronline.com/";
    private static final String FORMAT = "json";
    private static final String KEY = "fcb691d5d4c64b45a8b124513182112";

    private WorldWeatherOnline worldWeatherOnline;

    public CityRepositoryImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory
                .createWithScheduler(Schedulers.io());

        worldWeatherOnline = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
                .create(WorldWeatherOnline.class);
    }

    private static City mapCitiesResponseToCity(CitiesResponse citiesResponse) {
        final City city = new City();
        city.setAreaName(citiesResponse.getSearchApi().getResult().get(0).getAreaName().get(0).getValue());
        return city;
    }

    @Override
    public Observable<City> getCities(String startWith) {
        return worldWeatherOnline.getCities(startWith, KEY, FORMAT)
                .map(CityRepositoryImpl::mapCitiesResponseToCity);
    }
}

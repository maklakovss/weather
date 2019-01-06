package com.mss.weather.data.repositories;

import com.mss.weather.BuildConfig;
import com.mss.weather.data.db.RealmRepository;
import com.mss.weather.data.network.WorldWeatherOnline;
import com.mss.weather.data.network.model.response.CitiesResponse;
import com.mss.weather.data.network.model.response.Result;
import com.mss.weather.domain.city.WeatherRepository;
import com.mss.weather.domain.city.models.City;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepositoryImpl implements WeatherRepository {

    private static final String BASE_URL = "http://api.worldweatheronline.com/";
    private static final String FORMAT = "json";
    private static final String KEY = "fcb691d5d4c64b45a8b124513182112";

    private WorldWeatherOnline worldWeatherOnline;
    private RealmRepository realmRepository;

    public WeatherRepositoryImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .build();

        worldWeatherOnline = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
                .create(WorldWeatherOnline.class);

        realmRepository = new RealmRepository();
    }

    private static List<City> mapCitiesResponseToCity(CitiesResponse citiesResponse) {
        final List<City> cities = new ArrayList<>();
        if (citiesResponse != null && citiesResponse.getSearchApi() != null && citiesResponse.getSearchApi().getResult() != null)
            for (Result result : citiesResponse.getSearchApi().getResult()) {
                final City city = new City();
                if (result.getAreaName().size() > 0)
                    city.setAreaName(result.getAreaName().get(0).getValue());
                if (result.getCountry().size() > 0)
                    city.setCountry(result.getCountry().get(0).getValue());
                if (result.getRegion().size() > 0)
                    city.setRegion(result.getRegion().get(0).getValue());
                city.setLatitude(result.getLatitude());
                city.setLongitude(result.getLongitude());
                city.setPopulation(result.getPopulation());
                if (result.getTimezone() != null) {
                    city.setTimeOffset(result.getTimezone().getOffset());
                    city.setTimeZone(result.getTimezone().getZone());
                }
                if (result.getWeatherUrl().size() > 0)
                    city.setId(getIdFromUrl(result.getWeatherUrl().get(0).getValue()));
                cities.add(city);
            }
        return cities;
    }

    private static String getIdFromUrl(String url) {
        return url.substring(url.indexOf("=") + 1);
    }

    @Override
    public Maybe<List<City>> getAutoCompleteCities(String startWith) {
        return worldWeatherOnline.getCities(startWith, KEY, FORMAT)
                .map(WeatherRepositoryImpl::mapCitiesResponseToCity)
                .firstElement();
    }

    @Override
    public List<City> getCities() {
        return realmRepository.getAllCities();
    }

    @Override
    public void addCity(City city) {
        realmRepository.insertCity(city);
    }

    @Override
    public void deleteCity(City city) {
        realmRepository.deleteCity(city);
    }
}

package com.mss.weather.data.network;

import com.mss.weather.BuildConfig;
import com.mss.weather.data.network.models.CitiesResponse;
import com.mss.weather.data.network.models.CurrentCondition;
import com.mss.weather.data.network.models.Result;
import com.mss.weather.data.network.models.Weather;
import com.mss.weather.data.network.models.WeatherResponse;
import com.mss.weather.domain.NetworkRepository;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.CurrentWeather;
import com.mss.weather.domain.models.DayWeather;
import com.mss.weather.domain.models.InfoWeather;
import com.mss.weather.domain.models.Position;

import java.util.ArrayList;
import java.util.Date;
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
    private static final String KEY = "fcb691d5d4c64b45a8b124513182112";

    private WorldWeatherOnline worldWeatherOnline;

    public NetworkRepositoryImpl() {
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

    private static InfoWeather mapWeatherResponseToWeatherInfo(WeatherResponse weatherResponse, City city) {
        InfoWeather infoWeather = new InfoWeather();
        infoWeather.setCityID(city.getId());
        infoWeather.setDateState(new Date());
        if (weatherResponse.getData() != null
                && weatherResponse.getData().getWeather() != null
                && weatherResponse.getData().getWeather().size() > 0) {
            infoWeather.setDays(mapWeathersToWeatherDays(weatherResponse.getData().getWeather(), city));
        }
        if (weatherResponse.getData() != null
                && weatherResponse.getData().getCurrentCondition() != null
                && weatherResponse.getData().getCurrentCondition().size() > 0) {
            infoWeather.setCurrentWeather(mapCurrentConditionalToWeatherCurrent(weatherResponse.getData().getCurrentCondition().get(0), city));
        }
        return infoWeather;
    }

    private static List<DayWeather> mapWeathersToWeatherDays(List<Weather> weathers, City city) {
        List<DayWeather> dayWeathers = new ArrayList<>();
        return dayWeathers;
    }

    private static CurrentWeather mapCurrentConditionalToWeatherCurrent(CurrentCondition currentCondition, City city) {
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setCityID(city.getId());
        currentWeather.setDate(new Date());
        //currentWeather.setObservationTime(currentCondition.getObservationTime());
        currentWeather.setTempC(currentCondition.getTempC());
        currentWeather.setTempF(currentCondition.getTempF());
        currentWeather.setWeatherCode(currentCondition.getWeatherCode());
        //currentWeather.setWeatherIconUrl(currentCondition.getWeatherIconUrl());
        //currentWeather.setWeatherDesc(currentCondition.getWeatherDesc());
        //currentWeather.setWeatherDescLocalLanguage(currentCondition.getLangRu());
        currentWeather.setWindspeedMiles(currentCondition.getWindspeedMiles());
        currentWeather.setWindspeedKmph(currentCondition.getWindspeedKmph());
        currentWeather.setWinddirDegree(currentCondition.getWinddirDegree());
        currentWeather.setWinddir16Point(currentCondition.getWinddir16Point());
        currentWeather.setPrecipMM(currentCondition.getPrecipMM());
        currentWeather.setHumidity(currentCondition.getHumidity());
        currentWeather.setVisibility(currentCondition.getVisibility());
        currentWeather.setPressure(currentCondition.getPressure());
        currentWeather.setCloudcover(currentCondition.getCloudcover());
        currentWeather.setFeelsLikeC(currentCondition.getFeelsLikeC());
        currentWeather.setFeelsLikeF(currentCondition.getFeelsLikeF());

        return currentWeather;
    }

    @Override
    public Maybe<List<City>> getAutoCompleteCities(String startWith) {
        return worldWeatherOnline.getCities(startWith, 200, KEY, FORMAT)
                .map(NetworkRepositoryImpl::mapCitiesResponseToCity)
                .firstElement();
    }

    @Override
    public Maybe<List<City>> getCitiesByCoordinate(Position position) {
        final String query = String.valueOf(position.getLatitude()) + "," + String.valueOf(position.getLongitude());
        return worldWeatherOnline.getCities(query, 200, KEY, FORMAT)
                .map(NetworkRepositoryImpl::mapCitiesResponseToCity)
                .firstElement();
    }

    @Override
    public Maybe<InfoWeather> getWeatherInfo(City city) {
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
                .map(weatherResponse -> mapWeatherResponseToWeatherInfo(weatherResponse, city))
                .firstElement();
    }
}

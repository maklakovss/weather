package com.mss.weather.data.network;

import com.mss.weather.BuildConfig;
import com.mss.weather.data.network.model.CitiesResponse;
import com.mss.weather.data.network.model.CurrentCondition;
import com.mss.weather.data.network.model.Result;
import com.mss.weather.data.network.model.Weather;
import com.mss.weather.data.network.model.WeatherResponse;
import com.mss.weather.domain.NetworkRepository;
import com.mss.weather.domain.models.City;
import com.mss.weather.domain.models.Position;
import com.mss.weather.domain.models.WeatherCurrent;
import com.mss.weather.domain.models.WeatherDay;
import com.mss.weather.domain.models.WeatherInfo;

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

    private static WeatherInfo mapWeatherResponseToWeatherInfo(WeatherResponse weatherResponse, City city) {
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setCityID(city.getId());
        weatherInfo.setDateState(new Date());
        if (weatherResponse.getData() != null
                && weatherResponse.getData().getWeather() != null
                && weatherResponse.getData().getWeather().size() > 0) {
            weatherInfo.setDays(mapWeathersToWeatherDays(weatherResponse.getData().getWeather(), city));
        }
        if (weatherResponse.getData() != null
                && weatherResponse.getData().getCurrentCondition() != null
                && weatherResponse.getData().getCurrentCondition().size() > 0) {
            weatherInfo.setWeatherCurrent(mapCurrentConditionalToWeatherCurrent(weatherResponse.getData().getCurrentCondition().get(0), city));
        }
        return weatherInfo;
    }

    private static List<WeatherDay> mapWeathersToWeatherDays(List<Weather> weathers, City city) {
        List<WeatherDay> weatherDays = new ArrayList<>();
        return weatherDays;
    }

    private static WeatherCurrent mapCurrentConditionalToWeatherCurrent(CurrentCondition currentCondition, City city) {
        WeatherCurrent weatherCurrent = new WeatherCurrent();
        weatherCurrent.setCityID(city.getId());
        weatherCurrent.setDate(new Date());
        //weatherCurrent.setObservationTime(currentCondition.getObservationTime());
        weatherCurrent.setTempC(currentCondition.getTempC());
        weatherCurrent.setTempF(currentCondition.getTempF());
        weatherCurrent.setWeatherCode(currentCondition.getWeatherCode());
        //weatherCurrent.setWeatherIconUrl(currentCondition.getWeatherIconUrl());
        //weatherCurrent.setWeatherDesc(currentCondition.getWeatherDesc());
        //weatherCurrent.setWeatherDescLocalLanguage(currentCondition.getLangRu());
        weatherCurrent.setWindspeedMiles(currentCondition.getWindspeedMiles());
        weatherCurrent.setWindspeedKmph(currentCondition.getWindspeedKmph());
        weatherCurrent.setWinddirDegree(currentCondition.getWinddirDegree());
        weatherCurrent.setWinddir16Point(currentCondition.getWinddir16Point());
        weatherCurrent.setPrecipMM(currentCondition.getPrecipMM());
        weatherCurrent.setHumidity(currentCondition.getHumidity());
        weatherCurrent.setVisibility(currentCondition.getVisibility());
        weatherCurrent.setPressure(currentCondition.getPressure());
        weatherCurrent.setCloudcover(currentCondition.getCloudcover());
        weatherCurrent.setFeelsLikeC(currentCondition.getFeelsLikeC());
        weatherCurrent.setFeelsLikeF(currentCondition.getFeelsLikeF());

        return weatherCurrent;
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
    public Maybe<WeatherInfo> getWeatherInfo(City city) {
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

package com.mss.weather.data.network.mappers;

import android.support.annotation.NonNull;

import com.mss.weather.data.network.models.CitiesResponse;
import com.mss.weather.data.network.models.Result;
import com.mss.weather.domain.models.City;

import java.util.ArrayList;
import java.util.List;

public class CitiesResponseMapper {

    @NonNull
    public static List<City> mapCitiesResponseToCity(@NonNull final CitiesResponse citiesResponse) {
        final List<City> cities = new ArrayList<>();
        if (citiesResponse.getSearchApi() != null && citiesResponse.getSearchApi().getResult() != null)
            for (final Result result : citiesResponse.getSearchApi().getResult()) {
                final City city = new City();
                if (result.getAreaName().size() > 0 && result.getAreaName().get(0) != null)
                    city.setAreaName(result.getAreaName().get(0).getValue());
                if (result.getCountry().size() > 0 && result.getCountry().get(0) != null)
                    city.setCountry(result.getCountry().get(0).getValue());
                if (result.getRegion().size() > 0 && result.getRegion().get(0) != null)
                    city.setRegion(result.getRegion().get(0).getValue());
                city.setLatitude(result.getLatitude());
                city.setLongitude(result.getLongitude());
                city.setPopulation(result.getPopulation());
                if (result.getTimezone() != null) {
                    city.setTimeOffset(result.getTimezone().getOffset());
                    city.setTimeZone(result.getTimezone().getZone());
                }
                if (result.getWeatherUrl().size() > 0 && result.getWeatherUrl().get(0) != null)
                    city.setId(getIdFromUrl(result.getWeatherUrl().get(0).getValue()));
                cities.add(city);
            }
        return cities;
    }

    @NonNull
    private static String getIdFromUrl(@NonNull final String url) {
        return url.substring(url.indexOf("=") + 1);
    }
}

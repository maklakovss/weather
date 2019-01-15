package com.mss.weather.data.network.mappers;

import com.mss.weather.data.network.models.CitiesResponse;
import com.mss.weather.data.network.models.Result;
import com.mss.weather.domain.models.City;

import java.util.ArrayList;
import java.util.List;

public class CitiesResponseToCity {

    public static List<City> mapCitiesResponseToCity(CitiesResponse citiesResponse) {
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
}

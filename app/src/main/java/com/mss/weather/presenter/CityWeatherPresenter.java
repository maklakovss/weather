package com.mss.weather.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mss.weather.view.cityweather.CityWeatherView;
import com.mss.weather.view.models.WeatherData;

@InjectViewState
public class CityWeatherPresenter extends MvpPresenter<CityWeatherView> {

    private WeatherData weatherData;

    public void needData(String cityName) {
        if (weatherData == null || !cityName.equals(weatherData.getCityName())) {
            //тут запрашиваем данные с сервера
            weatherData = new WeatherData();
            weatherData.setCityName(cityName);
            weatherData.getSunrise().setHours(6);
            weatherData.getSunrise().setMinutes(0);
            weatherData.getSunrise().setSeconds(0);
            weatherData.getSunset().setHours(23);
            weatherData.getSunset().setMinutes(0);
            weatherData.getSunset().setSeconds(0);
            weatherData.setCloudsPercent(30);
            weatherData.setCloudsDescription("переменная облачность");
            weatherData.setTemp(25);
            weatherData.setTempMin(23);
            weatherData.setTempMax(27);
            weatherData.setPressure(760);
            weatherData.setHumidity(30);
            weatherData.setWindSpeed(5);
            weatherData.setWindDeg(90);
            weatherData.setSnow(10);
            weatherData.setRain(0);
        }
        getViewState().showWeather(weatherData);
    }
}

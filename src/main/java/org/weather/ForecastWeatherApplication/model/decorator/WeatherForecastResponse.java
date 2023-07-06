package org.weather.ForecastWeatherApplication.model.decorator;

import java.util.List;

public class WeatherForecastResponse {

    public List<DayWiseWeather> weatherList;

    public WeatherForecastResponse() {
    }

    public List<DayWiseWeather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<DayWiseWeather> weatherList) {
        this.weatherList = weatherList;
    }
}

package org.weather.ForecastWeatherApplication.model.decorator;

public abstract class WeatherDecorator extends Weather{
    Weather weather;

    public abstract Double getMaxTemperature();
    public abstract Double getMinTemperature();
    public abstract String getDay();
}

package org.weather.ForecastWeatherApplication.builder;

import org.springframework.stereotype.Component;
import org.weather.ForecastWeatherApplication.model.decorator.DayWiseWeather;
import org.weather.ForecastWeatherApplication.model.decorator.WeatherForecastResponse;

import java.util.List;

@Component
public class WeatherForecastResponseBuilder {
    public WeatherForecastResponse responseBuilder(List<DayWiseWeather> dayWiseWeatherForecastList, String transactionId, String cityName, String metricUnit){
        WeatherForecastResponse weatherForecastResponse = new WeatherForecastResponse();
//        weatherForecastResponse.setTransactionId(transactionId);
//        weatherForecastResponse.setStatus(HttpStatus.OK.value());
//        weatherForecastResponse.setTemperatureMeasurement(getMeasurementUnit(metricUnit));
//        weatherForecastResponse.setCityName(cityName);
//        weatherForecastResponse.setTimeStamp(Instant.now().toString());
        weatherForecastResponse.setWeatherList(dayWiseWeatherForecastList);
        return weatherForecastResponse;

    }

    private String getMeasurementUnit(String metricUnit){
        if(metricUnit.equalsIgnoreCase("metric")){
            return "Celsius";
        }else if(metricUnit.equalsIgnoreCase("imperial")){
            return "Fahrenheit ";
        }else
            return "Kelvin";
    }
}
